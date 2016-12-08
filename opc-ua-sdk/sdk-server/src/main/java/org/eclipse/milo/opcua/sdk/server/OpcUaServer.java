/*
 * Copyright (c) 2016 Kevin Herron, Stefan Profanter
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

package org.eclipse.milo.opcua.sdk.server;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.config.OpcUaClientConfig;
import org.eclipse.milo.opcua.sdk.client.api.identity.AnonymousProvider;
import org.eclipse.milo.opcua.sdk.core.ServerTable;
import org.eclipse.milo.opcua.sdk.server.api.AbstractServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.namespaces.OpcUaNamespace;
import org.eclipse.milo.opcua.sdk.server.namespaces.VendorNamespace;
import org.eclipse.milo.opcua.sdk.server.services.helpers.BrowseHelper.BrowseContinuationPoint;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.BuiltinReferenceType;
import org.eclipse.milo.opcua.stack.core.ReferenceType;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.application.UaStackServer;
import org.eclipse.milo.opcua.stack.core.application.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.DiscoveryServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.MethodServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.MonitoredItemServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.SessionServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.SubscriptionServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ViewServiceSet;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.MdnsDiscoveryConfiguration;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServer2Request;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisteredServer;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerOnNetwork;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.util.ManifestUtil;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Sets.newHashSet;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcUaServer {

    public static final String SDK_VERSION =
        ManifestUtil.read("X-SDK-Version").orElse("dev");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<ByteString, BrowseContinuationPoint> browseContinuationPoints = Maps.newConcurrentMap();

    private final ServerNodeMap nodeMap = new OpcUaServerNodeMap();

    private final Map<NodeId, ReferenceType> referenceTypes = Maps.newConcurrentMap();

    private final Map<UInteger, Subscription> subscriptions = Maps.newConcurrentMap();

    private final NamespaceManager namespaceManager = new NamespaceManager();
    private final SessionManager sessionManager;
    private final ServerTable serverTable = new ServerTable();

    private final UaStackServer stackServer;
    private final EventBus eventBus;

    private final OpcUaNamespace uaNamespace;
    private final VendorNamespace vendorNamespace;

    private final OpcUaServerConfig config;

    private EndpointDescription registeredWithDiscoveryServer;
    private ScheduledExecutorService periodicServerRegisterScheduler;
    private String registerSemaphoreFilePath;
    private long registerNextTryInterval;
    private long registerDefaultInterval;

    public OpcUaServer(OpcUaServerConfig config) {
        this.config = config;

        this.registeredWithDiscoveryServer = null;
        this.registerNextTryInterval = 0;

        stackServer = new UaTcpStackServer(config);

        sessionManager = new SessionManager(this);


        stackServer.addServiceSet((AttributeServiceSet) sessionManager);
        stackServer.addServiceSet((MethodServiceSet) sessionManager);
        stackServer.addServiceSet((MonitoredItemServiceSet) sessionManager);
        stackServer.addServiceSet((NodeManagementServiceSet) sessionManager);
        stackServer.addServiceSet((SessionServiceSet) sessionManager);
        stackServer.addServiceSet((SubscriptionServiceSet) sessionManager);
        stackServer.addServiceSet((ViewServiceSet) sessionManager);
        if (config.isDiscoveryServerEnabled()) {
            stackServer.addServiceSet((DiscoveryServiceSet) sessionManager);
        }

        namespaceManager.addNamespace(uaNamespace = new OpcUaNamespace(this));

        vendorNamespace = namespaceManager.registerAndAdd(
            config.getApplicationUri(),
            index -> new VendorNamespace(OpcUaServer.this, config.getApplicationUri()));

        serverTable.addUri(stackServer.getApplicationDescription().getApplicationUri());

        for (ReferenceType referenceType : BuiltinReferenceType.values()) {
            referenceTypes.put(referenceType.getNodeId(), referenceType);
        }

        String configuredHostname = config.getHostname();

        for (String bindAddress : config.getBindAddresses()) {
            Set<String> hostnames = Sets.union(
                newHashSet(configuredHostname),
                config.getHostnameResolver().apply(bindAddress)
            );

            for (String hostname : hostnames) {
                for (SecurityPolicy securityPolicy : config.getSecurityPolicies()) {
                    MessageSecurityMode messageSecurity = securityPolicy == SecurityPolicy.None ?
                        MessageSecurityMode.None : MessageSecurityMode.SignAndEncrypt;

                    String endpointUrl = endpointUrl(hostname, config.getBindPort(), config.getServerName());

                    Set<X509Certificate> certificates = config.getCertificateManager().getCertificates();

                    if (certificates.isEmpty() && securityPolicy == SecurityPolicy.None) {
                        logger.info("Binding endpoint {} to {} [{}/{}]",
                            endpointUrl, bindAddress, securityPolicy, messageSecurity);

                        stackServer.addEndpoint(endpointUrl, bindAddress, null, securityPolicy, messageSecurity);
                    } else {
                        for (X509Certificate certificate : certificates) {
                            logger.info("Binding endpoint {} to {} [{}/{}]",
                                endpointUrl, bindAddress, securityPolicy, messageSecurity);

                            stackServer.addEndpoint(
                                endpointUrl, bindAddress, certificate, securityPolicy, messageSecurity);
                        }
                    }
                }
            }
        }

        eventBus = new AsyncEventBus("server", stackServer.getExecutorService());

        logger.info("eclipse milo opc-ua stack version: {}", Stack.VERSION);
        logger.info("eclipse milo opc-ua sdk version: {}", SDK_VERSION);
    }

    public CompletableFuture<OpcUaServer> startup() {
        return stackServer.startup().whenComplete((o, throwable) -> {
            //TODO set capabilities correct
            if (config.isMulticastEnabled()) {
                sessionManager.getDiscoveryServices().addMulticastRecord(config.getApplicationName().getText(),
                        config.getBindPort(), config.getServerName(), new String[0]);
            }
        }).thenApply(ignored -> OpcUaServer.this);
    }

    public CompletableFuture<OpcUaServer> shutdown() {
        if (config.isMulticastEnabled()) {
            sessionManager.getDiscoveryServices().removeMulticastRecord(config.getHostname(),
                    config.getBindPort(), config.getServerName());
        }
        return stackServer.shutdown().thenApply(ignored -> OpcUaServer.this);
    }

    private static String endpointUrl(String hostname, int port, String serverName) {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("opc.tcp://%s:%d", hostname, port));
        if (!serverName.isEmpty()) {
            sb.append("/").append(serverName);
        }
        return sb.toString();
    }

    public OpcUaServerConfig getConfig() {
        return config;
    }

    public NamespaceManager getNamespaceManager() {
        return namespaceManager;
    }

    public ServerNodeMap getNodeMap() {
        return nodeMap;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public OpcUaNamespace getUaNamespace() {
        return uaNamespace;
    }

    public ServerTable getServerTable() {
        return serverTable;
    }

    public EventBus getEventBus() {
        return eventBus;
    }

    public Map<UInteger, Subscription> getSubscriptions() {
        return subscriptions;
    }

    public Optional<KeyPair> getKeyPair(ByteString thumbprint) {
        return stackServer.getCertificateManager().getKeyPair(thumbprint);
    }

    public Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        return stackServer.getCertificateManager().getCertificate(thumbprint);
    }

    public ExecutorService getExecutorService() {
        return stackServer.getExecutorService();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return Stack.sharedScheduledExecutor();
    }

    public ChannelConfig getChannelConfig() {
        return stackServer.getChannelConfig();
    }

    public EndpointDescription[] getEndpointDescriptions() {
        return stackServer.getEndpointDescriptions();
    }

    public List<UserTokenPolicy> getUserTokenPolicies() {
        return stackServer.getUserTokenPolicies();
    }

    public ApplicationDescription getApplicationDescription() {
        return stackServer.getApplicationDescription();
    }

    public SignedSoftwareCertificate[] getSoftwareCertificates() {
        return stackServer.getSoftwareCertificates();
    }

    public void closeSecureChannel(ServerSecureChannel secureChannel) {
        stackServer.closeSecureChannel(secureChannel);
    }

    public UaStackServer getServer() {
        return stackServer;
    }

    public Map<NodeId, ReferenceType> getReferenceTypes() {
        return referenceTypes;
    }

    public Map<ByteString, BrowseContinuationPoint> getBrowseContinuationPoints() {
        return browseContinuationPoints;
    }

    /**
     * Given a bind address resolve it to one or more hostnames to be used when building endpoints.
     *
     * @param bindAddress the bind address to resolve.
     * @return the hostnames that will be used to represent this bind address in endpoints.
     */
    public static Set<String> getHostnames(String bindAddress) {
        Set<String> hostnames = newHashSet();

        try {
            InetAddress inetAddress = InetAddress.getByName(bindAddress);

            if (inetAddress.isAnyLocalAddress()) {
                try {
                    Enumeration<NetworkInterface> nis = NetworkInterface.getNetworkInterfaces();

                    for (NetworkInterface ni : Collections.list(nis)) {
                        Collections.list(ni.getInetAddresses()).stream()
                            .filter(ia -> ia instanceof Inet4Address)
                            .forEach(ia -> {
                                hostnames.add(ia.getHostName());
                                hostnames.add(ia.getHostAddress());
                                hostnames.add(ia.getCanonicalHostName());
                            });
                    }
                } catch (SocketException e) {
                    LoggerFactory.getLogger(OpcUaServer.class)
                        .warn("Failed to NetworkInterfaces for bind address: {}", bindAddress, e);
                }
            } else {
                hostnames.add(inetAddress.getHostName());
                hostnames.add(inetAddress.getHostAddress());
                hostnames.add(inetAddress.getCanonicalHostName());
            }
        } catch (UnknownHostException e) {
            LoggerFactory.getLogger(OpcUaServer.class)
                .warn("Failed to get InetAddress for bind address: {}", bindAddress, e);
        }

        return hostnames;
    }

    private static class OpcUaServerNodeMap extends AbstractServerNodeMap {}

    public void setRegisterServerConsumer(Consumer<RegisteredServer> registerServerConsumer) {
        this.sessionManager.getDiscoveryServices().setRegisterServerConsumer(registerServerConsumer);
    }

    public void setMulticastServerConsumer(Consumer<ServerOnNetwork> consumer) {
        this.sessionManager.getDiscoveryServices().setMulticastServerConsumer(consumer);
    }

    /**
     * Register this server instance with a Discovery Server (LDS) on the given endpoint url.
     * The registering will repeated periodically to keep the registered data up to date.
     *
     * The semaphoreFilePath is optional. If the given file is deleted,
     * the server will automatically be unregistered. This could be
     * for example a pid file which is deleted if the server crashes.
     * NOTE: The path to the semaphore file must be accessible by the LDS server.
     *
     * The default register frequency is 10 minutes and the first register call is delayed by 500ms.
     *
     * @param discoveryServerUrl the endpoint URL of the LDS/Discovery Server
     * @param semaphoreFilePath optional file path to a semaphore file
     * @return true if periodic registering successfully started, false if already registered
     */
    public boolean registerWithDiscoveryServer(String discoveryServerUrl, String semaphoreFilePath) {
        return this.registerWithDiscoveryServer(discoveryServerUrl, 10 * 60 * 1000, 500, semaphoreFilePath);
    }

    /**
     * Register this server instance with a Discovery Server (LDS) on the given endpoint url.
     * The registering will repeated periodically to keep the registered data up to date.
     *
     * The semaphoreFilePath is optional. If the given file is deleted,
     * the server will automatically be unregistered. This could be
     * for example a pid file which is deleted if the server crashes.
     * NOTE: The path to the semaphore file must be accessible by the LDS server.
     *
     * @param discoveryServerUrl the endpoint URL of the LDS/Discovery Server
     * @param intervalMs interval in milliseconds of the periodic register. It should be around 10 minutes.
     * @param delayFirstRegisterMs the first call to register can be delayed to e.g. start up the server or do other
     *                             initialization stuff.
     * @param semaphoreFilePath optional file path to a semaphore file
     * @return true if periodic registering successfully started, false if already registered
     */
    public boolean registerWithDiscoveryServer(String discoveryServerUrl, long intervalMs, long delayFirstRegisterMs,
                                               String semaphoreFilePath) {
        if (registeredWithDiscoveryServer != null) {
            logger.warn("Can not register server with discovery server " + discoveryServerUrl +
                    ". Already registered with: " + registeredWithDiscoveryServer);
            return false;
        }

        this.registerSemaphoreFilePath = semaphoreFilePath;
        this.registerDefaultInterval = intervalMs;

        periodicServerRegisterScheduler = Executors.newScheduledThreadPool(2);

        periodicServerRegisterScheduler.scheduleWithFixedDelay(new PeriodicServerRegister(discoveryServerUrl),
                delayFirstRegisterMs, intervalMs, TimeUnit.MILLISECONDS);

        return true;
    }

    public CompletableFuture<StatusCode> unregisterFromDiscoveryServer() {
        if (registeredWithDiscoveryServer == null) {
            logger.warn("Can not unregister from discovery server. Not registered yet.");
            CompletableFuture<StatusCode> futureRegisterResult = new CompletableFuture<StatusCode>();
            futureRegisterResult.completeExceptionally(
                    new RuntimeException("Can not unregister from discovery server. Not registered yet."));
            return futureRegisterResult;
        }
        periodicServerRegisterScheduler.shutdown();
        return registerWithDiscoveryServer(false, registeredWithDiscoveryServer, null);
    }

    private CompletableFuture<StatusCode> registerServer2(OpcUaClient stackClient,
                                                          RegisteredServer serverToBeRegistered,
                                                          MdnsDiscoveryConfiguration mdnsDiscoveryConfig) {

        CompletableFuture<StatusCode> futureRegisterResult = new CompletableFuture<>();

        ExtensionObject[] discoveryConfig = {ExtensionObject.encode(mdnsDiscoveryConfig)};

        RegisterServer2Request registerServer2Request;
        try {
            registerServer2Request = new RegisterServer2Request(
                    stackClient.newRequestHeader(stackClient.getSession().get().getAuthenticationToken()),
                    serverToBeRegistered, discoveryConfig);
        } catch (InterruptedException | ExecutionException e) {
            futureRegisterResult.completeExceptionally(e);
            return futureRegisterResult;
        }

        // first try RegisterServer2
        stackClient.sendRequest(registerServer2Request).whenComplete((response2, ex2) -> {
            if (response2 == null) {
                futureRegisterResult.completeExceptionally(ex2);
            } else {
                futureRegisterResult.complete(response2.getResponseHeader().getServiceResult());
            }
        });

        return futureRegisterResult;
    }

    private CompletableFuture<StatusCode> registerServer(OpcUaClient stackClient,
                                                          RegisteredServer serverToBeRegistered) {

        CompletableFuture<StatusCode> futureRegisterResult = new CompletableFuture<>();
        RegisterServerRequest registerServerRequest = null;
        try {
            registerServerRequest = new RegisterServerRequest(
                    stackClient.newRequestHeader(stackClient.getSession().get().getAuthenticationToken()),
                    serverToBeRegistered);
        } catch (InterruptedException | ExecutionException e) {
            futureRegisterResult.completeExceptionally(e);
        }
        CompletableFuture<RegisterServerResponse> future = stackClient.sendRequest(registerServerRequest);
        future.whenComplete((response, ex) -> {
            if (response == null) {
                futureRegisterResult.completeExceptionally(ex);
            } else {
                futureRegisterResult.complete(response.getResponseHeader().getServiceResult());
            }
        });
        return futureRegisterResult;
    }

    private CompletableFuture<StatusCode> registerWithDiscoveryServer(boolean isRegister,
                                                                      EndpointDescription discoveryEndpoint,
                                                                      String semaphoreFilePath) {
        OpcUaClientConfig clientConfig = OpcUaClientConfig.builder()
                .setApplicationName(LocalizedText.english("eclipse milo opc-ua client"))
                .setApplicationUri("urn:eclipse:milo:examples:client")
                //.setCertificate(loader.getClientCertificate())
                //.setKeyPair(loader.getClientKeyPair())
                .setEndpoint(discoveryEndpoint)
                .setIdentityProvider(new AnonymousProvider())
                .setRequestTimeout(uint(1000))
                .build();

        CompletableFuture<StatusCode> futureRegisterResult = new CompletableFuture<StatusCode>();

        OpcUaClient stackClient = new OpcUaClient(clientConfig);
        try {
            stackClient.connect().get();
        } catch (InterruptedException | ExecutionException e) {
            futureRegisterResult.completeExceptionally(e);
            return futureRegisterResult;
        }

        LocalizedText[] serverNames = new LocalizedText[1];
        serverNames[0] = config.getApplicationName();
        ApplicationType serverType = ApplicationType.ClientAndServer;

        String[] discoveryUrls = Arrays.stream(stackServer.getEndpointDescriptions()).map(
                EndpointDescription::getEndpointUrl).distinct().toArray(String[]::new);

        RegisteredServer serverToBeRegistered = new RegisteredServer(config.getApplicationUri(), config.getProductUri(),
                serverNames, serverType, null, discoveryUrls, semaphoreFilePath, isRegister);

        //TODO set capabilities correct
        MdnsDiscoveryConfiguration mdnsDiscoveryConfig = new MdnsDiscoveryConfiguration(
                config.getApplicationName().getText(), new String[0]);


        registerServer2(stackClient, serverToBeRegistered, mdnsDiscoveryConfig).whenComplete(
            (statusCode, throwable) -> {
                if (statusCode == null) {
                    logger.error("RegisterServer2 failed with error: {}", throwable.getMessage(), throwable);
                    futureRegisterResult.completeExceptionally(throwable);
                } else if (statusCode.getValue() == StatusCodes.Bad_NotImplemented ||
                       statusCode.getValue() == StatusCodes.Bad_ServiceUnsupported) {
                    // RegisterServer2 failed, try RegisterServer
                    registerServer(stackClient, serverToBeRegistered).whenComplete((statusCode1, throwable1) -> {
                        if (statusCode1 == null) {
                            logger.error("RegisterServer failed with error: {}", throwable1.getMessage(), throwable1);
                            futureRegisterResult.completeExceptionally(throwable1);
                        } else {
                            if (statusCode1.isBad()) {
                                logger.error("RegisterServer failed with status code: {}", statusCode1);
                            }
                            futureRegisterResult.complete(statusCode1);
                        }
                    });
                } else {
                    futureRegisterResult.complete(statusCode);
                }
            });

        return futureRegisterResult;
    }

    private void retryPeriodicServerRegister(String discoveryServerUrl) {

        // reschedule server registering with backing off strategy as defined in specification.
        // first retry in 1s, then double each time, i.e. 2,4,8,... until main interval is reached.
        if (registerNextTryInterval == 0) {
            registerNextTryInterval = 1000;
        } else {
            registerNextTryInterval *= 2;
            if (registerNextTryInterval > registerDefaultInterval) {
                registerNextTryInterval = 0;
                logger.warn("Retry interval of register server reached maximum. Falling back to default retry cycle");
                return;
            }
        }

        logger.info("Retry register server in " + (registerNextTryInterval / 1000) + " seconds");

        periodicServerRegisterScheduler.schedule(new PeriodicServerRegister(discoveryServerUrl),
                registerNextTryInterval, TimeUnit.MILLISECONDS);
    }

    private class PeriodicServerRegister implements Runnable {

        String discoveryServerUrl;

        public PeriodicServerRegister(String discoveryServerUrl) {
            this.discoveryServerUrl = discoveryServerUrl;
        }

        @Override
        public void run() {

            if (registeredWithDiscoveryServer == null) {
                EndpointDescription endpoint;
                try {
                    EndpointDescription[] endpoints = UaTcpStackClient.getEndpoints(discoveryServerUrl).get();
                    endpoint = Arrays.stream(endpoints)
                            .filter(e -> e.getSecurityPolicyUri().equals(SecurityPolicy.None.getSecurityPolicyUri()))
                            .findFirst().orElseThrow(() -> new Exception("no desired endpoints returned"));
                } catch (Exception e) {
                    logger.error("Can not get endpoints from discovery server: " + e.getMessage());
                    retryPeriodicServerRegister(discoveryServerUrl);
                    return;
                }


                registeredWithDiscoveryServer = endpoint;
            }


            registerWithDiscoveryServer(true, registeredWithDiscoveryServer, registerSemaphoreFilePath)
                    .whenComplete((statusCode, ex) -> {
                        if (statusCode == null) {
                            logger.error("Could not register server with discovery server. Error {}",
                                    ex.getMessage(), ex);
                            return;
                        }
                        if (statusCode.isBad()) {
                            logger.warn("Could not register server with discovery server: " + statusCode);
                            retryPeriodicServerRegister(discoveryServerUrl);
                        } else {
                            logger.info("Successfully registered with discovery server " + discoveryServerUrl);
                        }
                    });
        }
    }

}
