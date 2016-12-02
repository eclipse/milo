/*
 * Copyright (c) 2016 Stefan Profanter and others
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.Stream;

import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.services.helpers.MdnsHelper;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersOnNetworkResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.MdnsDiscoveryConfiguration;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServer2Request;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServer2Response;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisteredServer;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerOnNetwork;
import org.eclipse.milo.opcua.stack.server.tcp.DefaultDiscoveryService;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiscoveryServices extends DefaultDiscoveryService {

    private LinkedList<RegisteredServer> registeredServers;
    private HashMap<RegisteredServer, Date> registeredServerLastSeen;

    private final boolean multicastEnabled;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private MdnsHelper mdnsHelper = null;


    private Consumer<RegisteredServer> registerServerConsumer = null;

    public DiscoveryServices(UaTcpStackServer server, boolean multicastEnabled) {
        super(server);
        registeredServers = new LinkedList<>();
        registeredServerLastSeen = new HashMap<>();
        this.multicastEnabled = multicastEnabled;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkRegistrationTimeout();
            }
        }, 30 * 1000, 30 * 1000); // check cleanup every 30 secs

        if (multicastEnabled) {
            OpcUaServerConfig config = (OpcUaServerConfig) server.getConfig();

            Set<String> hostnames = new HashSet<>();
            hostnames.add(config.getHostname());

            for (String bindAddress : config.getBindAddresses()) {
                hostnames.addAll(config.getHostnameResolver().apply(bindAddress));
            }

            mdnsHelper = new MdnsHelper(hostnames);
            Thread mdnsThread = new Thread(mdnsHelper);
            mdnsThread.start();
        }
    }

    public void setRegisterServerConsumer(Consumer<RegisteredServer> registerServerConsumer) {
        this.registerServerConsumer = registerServerConsumer;
    }

    public void setMulticastServerConsumer(Consumer<ServerOnNetwork> consumer) {
        if (multicastEnabled) {
            mdnsHelper.setMulticastServerConsumer(consumer);
        }
    }

    @Override
    public void onFindServersOnNetwork(
            ServiceRequest<FindServersOnNetworkRequest, FindServersOnNetworkResponse> serviceRequest)
            throws UaException {
        if (!multicastEnabled) {
            serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
            return;
        }

        int recordCount = 0;
        if (serviceRequest.getRequest().getStartingRecordId()
                .compareTo(UInteger.valueOf(mdnsHelper.getCurrentServerOnNetworkId())) < 0) {
            recordCount = mdnsHelper.getCurrentServerOnNetworkId() -
                    serviceRequest.getRequest().getStartingRecordId().intValue();
        }

        if (serviceRequest.getRequest().getMaxRecordsToReturn().longValue() > 0) {
            recordCount = Integer.min(recordCount, serviceRequest.getRequest().getMaxRecordsToReturn().intValue());
        }

        Stream<ServerOnNetwork> filteredServers = mdnsHelper.getServerOnNetwork().stream().filter(serverOnNetwork ->
                serverOnNetwork.getRecordId().compareTo(serviceRequest.getRequest().getStartingRecordId()) >= 0)
                .limit(recordCount);

        if (serviceRequest.getRequest().getServerCapabilityFilter() != null &&
                serviceRequest.getRequest().getServerCapabilityFilter().length > 0) {

            filteredServers =
                    filteredServers.filter(serverOnNetwork -> serverOnNetwork.getServerCapabilities() == null ||
                            Arrays.asList(serverOnNetwork.getServerCapabilities())
                                    .containsAll(
                                            Arrays.asList(serviceRequest.getRequest().getServerCapabilityFilter())));
        }

        ResponseHeader header = serviceRequest.createResponseHeader();

        serviceRequest.setResponse(
                new FindServersOnNetworkResponse(header, new DateTime(mdnsHelper.getLastServerOnNetworkIdReset()),
                        filteredServers.toArray(ServerOnNetwork[]::new)));
    }

    public StatusCode addMulticastRecord(String name, int port, String path, String[] capabilities) {
        if (!multicastEnabled) {
            return new StatusCode(StatusCodes.Bad_ServiceUnsupported);
        }
        if (!mdnsHelper.addRecord(name, port, path, capabilities)) {
            return StatusCode.BAD;
        }
        return StatusCode.GOOD;
    }

    public StatusCode removeMulticastRecord(String name, int port, String path) {
        if (!multicastEnabled) {
            return new StatusCode(StatusCodes.Bad_ServiceUnsupported);
        }
        if (!mdnsHelper.removeRecord(name, port, path)) {
            return StatusCode.BAD;
        }
        return StatusCode.GOOD;
    }

    private StatusCode processRegisterServer(RegisteredServer requestServer,
                                             ExtensionObject[] requestDiscoveryConfiguration,
                                             ArrayList<StatusCode> configurationResults,
                                             ArrayList<DiagnosticInfo> diagnosticInfos) {

        // check if server already in list
        RegisteredServer registeredServer = null;


        Optional<RegisteredServer> rs =
                registeredServers.stream().filter(s -> s.getServerUri().compareTo(requestServer.getServerUri()) == 0)
                        .findFirst();
        if (rs.isPresent()) {
            registeredServer = rs.get();
        }

        // set discovery configuration if it is in request (only RegisterServer2)
        MdnsDiscoveryConfiguration discoveryConfiguration = null;
        String mdnsName = null;
        if (requestDiscoveryConfiguration != null) {
            configurationResults.clear();
            configurationResults.ensureCapacity(requestDiscoveryConfiguration.length);
            for (ExtensionObject e : requestDiscoveryConfiguration) {
                if (discoveryConfiguration == null &&
                        e.getEncodingTypeId().equals(Identifiers.MdnsDiscoveryConfiguration)) {
                    // we found a known extension object which we can convert
                    discoveryConfiguration = e.decode();
                    mdnsName = discoveryConfiguration.getMdnsServerName();
                    configurationResults.add(StatusCode.GOOD);
                } else {
                    configurationResults.add(new StatusCode(StatusCodes.Bad_NotSupported));
                }
            }
        }

        if (mdnsName == null && requestServer.getServerNames() != null && requestServer.getServerNames().length > 0) {
            mdnsName = requestServer.getServerNames()[0].getText();
        }

        if (mdnsName == null) {
            return new StatusCode(StatusCodes.Bad_ServerNameMissing);
        }

        if (requestServer.getDiscoveryUrls() == null || requestServer.getDiscoveryUrls().length == 0) {
            return new StatusCode(StatusCodes.Bad_DiscoveryUrlMissing);
        }

        // check semaphore
        if (requestServer.getSemaphoreFilePath() != null && requestServer.getSemaphoreFilePath().length() > 0) {
            if (!new File(requestServer.getSemaphoreFilePath()).isFile()) {
                return new StatusCode(StatusCodes.Bad_SemaphoreFileMissing);
            }
        }

        if (multicastEnabled) {
            // publish or unpublish mDNS record
            for (String discoveryUrl : requestServer.getDiscoveryUrls()) {
                if (!requestServer.getIsOnline()) {
                    if (!mdnsHelper.removeFromServerOnNetwork(discoveryUrl)) {
                        logger.warn("Could not remove server record for " + discoveryUrl);
                    }
                } else {
                    String[] capabilities =
                            discoveryConfiguration == null || discoveryConfiguration.getServerCapabilities() == null ?
                                    new String[0] : discoveryConfiguration.getServerCapabilities();
                    mdnsHelper.addToServerOnNetwork(requestServer.getServerNames()[0].getText(), discoveryUrl,
                            capabilities);
                }
            }
        }

        if (!requestServer.getIsOnline()) {
            // server shutting down, unregister it
            if (registeredServer == null) {
                logger.warn("Could not unregister server " + requestServer.getServerUri() + ". Not registered");
                return new StatusCode(StatusCodes.Bad_NotFound);
            }

            if (registerServerConsumer != null) {
                registerServerConsumer.accept(registeredServer);
            }

            this.registeredServers.remove(registeredServer);
            this.registeredServerLastSeen.remove(registeredServer);

            logger.info("Server successfully unregistered: " + requestServer.getServerUri());

            return StatusCode.GOOD;
        }

        if (registeredServer == null) {
            // this server did not yet register, create new

            logger.info("New Server successfully registered: " + requestServer.getServerUri());

            registeredServer = requestServer;
            this.registeredServers.add(registeredServer);

            if (registerServerConsumer != null) {
                registerServerConsumer.accept(registeredServer);
            }
        } else {
            logger.info("Server successfully re-registered: " + requestServer.getServerUri());
        }
        // update or add last seen value
        this.registeredServerLastSeen.put(registeredServer, new Date());


        return StatusCode.GOOD;
    }

    /**
     * Cleanup server registration:
     * If the semaphore file path is set, then it just checks the existence of the file.
     * When it is deleted, the registration is removed.
     * If there is no semaphore file, then the registration will be removed if it is older than 60 minutes.
     */
    private void checkRegistrationTimeout() {
        Date timedOutIfBefore = new Date(System.currentTimeMillis() - TimeUnit.MINUTES.toMillis(60));
        for (RegisteredServer r : registeredServers) {
            boolean remove = false;
            if (r.getSemaphoreFilePath() != null && r.getSemaphoreFilePath().length() > 0) {
                // check if semaphore still exists
                File semaphoreFile = new File(r.getSemaphoreFilePath());
                if (!semaphoreFile.isFile()) {
                    logger.info("Registration of server '" + r.getServerUri() +
                            "' is removed because the semaphore file '" +
                            semaphoreFile.getAbsolutePath() + "' was removed.");
                    remove = true;
                }
            } else {
                Date lastSeen = this.registeredServerLastSeen.get(r);
                if (lastSeen.before(timedOutIfBefore)) {
                    logger.info("Registration of server '" + r.getServerUri() +
                            "' is removed because its registration timed out.");
                    remove = true;
                }
            }
            if (remove) {
                this.registeredServers.remove(r);
                this.registeredServerLastSeen.remove(r);
            }
        }
    }

    @Override
    public void onRegisterServer(
            ServiceRequest<RegisterServerRequest, RegisterServerResponse> serviceRequest) throws UaException {

        ResponseHeader header = serviceRequest
                .createResponseHeader(processRegisterServer(serviceRequest.getRequest().getServer(), null, null, null));

        serviceRequest.setResponse(new RegisterServerResponse(header));
    }

    @Override
    public void onRegisterServer2(
            ServiceRequest<RegisterServer2Request, RegisterServer2Response> serviceRequest) throws UaException {

        ArrayList<StatusCode> configurationResults = new ArrayList<>();
        ArrayList<DiagnosticInfo> diagnosticInfos = new ArrayList<>();

        ResponseHeader header =
                serviceRequest.createResponseHeader(processRegisterServer(serviceRequest.getRequest().getServer(),
                        serviceRequest.getRequest().getDiscoveryConfiguration(), configurationResults,
                        diagnosticInfos));
        serviceRequest.setResponse(new RegisterServer2Response(header, configurationResults.toArray(new StatusCode[0]),
                diagnosticInfos.toArray(new DiagnosticInfo[0])));
    }

    @Override
    protected List<RegisteredServer> getRegisteredServers() {
        return registeredServers;
    }

}
