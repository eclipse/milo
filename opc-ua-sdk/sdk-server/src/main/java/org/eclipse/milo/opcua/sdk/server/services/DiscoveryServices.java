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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.*;
import org.eclipse.milo.opcua.stack.server.tcp.DefaultDiscoveryService;
import org.eclipse.milo.opcua.stack.server.tcp.UaTcpStackServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class DiscoveryServices extends DefaultDiscoveryService {

	private LinkedList<RegisteredServer> registeredServers;
	private HashMap<RegisteredServer, Date> registeredServerLastSeen;

	private final boolean multicastEnabled;
	private final Logger logger = LoggerFactory.getLogger(getClass());


	private Consumer<RegisteredServer> registerServerConsumer = null;
	private final Timer checkRegistrationTimeoutTimer = new Timer();

	public DiscoveryServices(UaTcpStackServer server, boolean multicastEnabled) {
		super(server);
		registeredServers = new LinkedList<>();
		registeredServerLastSeen = new HashMap<>();
		this.multicastEnabled = multicastEnabled;
		checkRegistrationTimeoutTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				checkRegistrationTimeout();
			}
		}, 30 * 1000, 30 * 1000); // check cleanup every 30 secs
	}

	public void setRegisterServerConsumer(Consumer<RegisteredServer> registerServerConsumer) {
		this.registerServerConsumer = registerServerConsumer;
	}

	@Override
	public void onFindServersOnNetwork(
			ServiceRequest<FindServersOnNetworkRequest, FindServersOnNetworkResponse> serviceRequest) throws UaException {

		serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
	}

	public StatusCode addMulticastRecord(String host, int port, boolean addTxt, String[] capabilities) {
		return null;
	}

	public StatusCode removeMulticastRecord(String host, int port, boolean removeTxt) {
		return null;
	}

	private StatusCode processRegisterServer(RegisteredServer requestServer, ExtensionObject[] requestDiscoveryConfiguration,
											 ArrayList<StatusCode> configurationResults, ArrayList<DiagnosticInfo> diagnosticInfos) {

		// check if server already in list
		RegisteredServer registeredServer = null;


		Optional<RegisteredServer> rs = registeredServers.stream().filter(s -> s.getServerUri().compareTo(requestServer.getServerUri()) == 0).findFirst();
		if (rs.isPresent())
			registeredServer = rs.get();

		// set discovery configuration if it is in request (only RegisterServer2)
		MdnsDiscoveryConfiguration discoveryConfiguration = null;
		String mdnsName = null;
		if (requestDiscoveryConfiguration != null) {
			configurationResults.clear();
			configurationResults.ensureCapacity(requestDiscoveryConfiguration.length);
			for (ExtensionObject e : requestDiscoveryConfiguration) {
				if (discoveryConfiguration == null && e.getEncodingTypeId().equals(Identifiers.MdnsDiscoveryConfiguration)) {
					// we found a known extension object which we can convert
					discoveryConfiguration = e.decode();
					mdnsName = discoveryConfiguration.getMdnsServerName();
					configurationResults.add(StatusCode.GOOD);
				} else {
					configurationResults.add(new StatusCode(StatusCodes.Bad_NotSupported));
				}
			}
		}

		if (mdnsName == null && requestServer.getServerNames().length > 0) {
			mdnsName = requestServer.getServerNames()[0].getText();
		}

		if (mdnsName == null) {
			return new StatusCode(StatusCodes.Bad_ServerNameMissing);
		}

		if (requestServer.getDiscoveryUrls() == null || requestServer.getDiscoveryUrls().length == 0) {
			return new StatusCode(StatusCodes.Bad_DiscoveryUrlMissing);
		}

		// check semaphore
		File semaphoreFile = null;
		if (requestServer.getSemaphoreFilePath() != null && requestServer.getSemaphoreFilePath().length() > 0) {
			if (!new File(requestServer.getSemaphoreFilePath()).isFile()) {
				return new StatusCode(StatusCodes.Bad_SemaphoreFileMissing);
			}
		}

		if (multicastEnabled) {
			// publish or unpublish mDNS record
			for (int i = 0; i < requestServer.getDiscoveryUrls().length; i++) {
				URL fullDiscoveryUrl = null;
				try {
					fullDiscoveryUrl = new URL(requestServer.getDiscoveryUrls()[i]);
				} catch (MalformedURLException e) {
					return new StatusCode(StatusCodes.Bad_UnexpectedError);
				}

				if (!requestServer.getIsOnline()) {
					if (removeMulticastRecord(fullDiscoveryUrl.getHost(), fullDiscoveryUrl.getPort(), i == requestServer.getDiscoveryUrls().length).isBad()) {
						logger.warn("Could not remove mDNS record for hostname " + fullDiscoveryUrl.getHost() + ":" + fullDiscoveryUrl.getPort());
					}
				} else {
					if (addMulticastRecord(fullDiscoveryUrl.getHost(), fullDiscoveryUrl.getPort(), i == 0, discoveryConfiguration.getServerCapabilities()).isBad()) {
						logger.warn("Could not add mDNS record for hostname " + fullDiscoveryUrl.getHost() + ":" + fullDiscoveryUrl.getPort());
					}
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

			return StatusCode.GOOD;
		}

		if (registeredServer == null) {
			// this server did not yet register, create new

			logger.debug("RegisterServer called by new server: " + requestServer.getServerUri());

			registeredServer = requestServer;
			this.registeredServers.add(registeredServer);

			if (registerServerConsumer != null) {
				registerServerConsumer.accept(registeredServer);
			}
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
					logger.info("Registration of server '" + r.getServerUri() + "' is removed because the semaphore file '" +
							semaphoreFile.getAbsolutePath() + "' was removed.");
					remove = true;
				}
			} else {
				Date lastSeen = this.registeredServerLastSeen.get(r);
				if (lastSeen.before(timedOutIfBefore)) {
					logger.info("Registration of server '" + r.getServerUri() + "' is removed because its registration timed out.");
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

		ResponseHeader header = serviceRequest.createResponseHeader(processRegisterServer(serviceRequest.getRequest().getServer(), null, null, null));

		serviceRequest.setResponse(new RegisterServerResponse(header));
	}

	@Override
	public void onRegisterServer2(
			ServiceRequest<RegisterServer2Request, RegisterServer2Response> serviceRequest) throws UaException {

		ArrayList<StatusCode> configurationResults = new ArrayList<>();
		ArrayList<DiagnosticInfo> diagnosticInfos = new ArrayList<>();

		ResponseHeader header = serviceRequest.createResponseHeader(processRegisterServer(serviceRequest.getRequest().getServer(),
				serviceRequest.getRequest().getDiscoveryConfiguration(), configurationResults, diagnosticInfos));
		serviceRequest.setResponse(new RegisterServer2Response(header, configurationResults.toArray(new StatusCode[0]),
				diagnosticInfos.toArray(new DiagnosticInfo[0])));
	}

	@Override
	protected List<RegisteredServer> getRegisteredServers() {
		return registeredServers;
	}

}
