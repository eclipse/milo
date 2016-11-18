/*
 * Copyright (c) 2016 Kevin Herron and others
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
package org.eclipse.milo.opcua.stack.server.tcp;

import org.eclipse.milo.opcua.stack.core.application.services.DiscoveryServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.structured.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;

public class DefaultDiscoveryService implements DiscoveryServiceSet {

	private UaTcpStackServer server;

	private final Logger logger = LoggerFactory.getLogger(getClass());


	public DefaultDiscoveryService(UaTcpStackServer server) {
		this.server = server;
	}


	@Override
	public void onGetEndpoints(ServiceRequest<GetEndpointsRequest, GetEndpointsResponse> serviceRequest) {
		GetEndpointsRequest request = serviceRequest.getRequest();

		List<String> profileUris = request.getProfileUris() != null ?
				newArrayList(request.getProfileUris()) :
				new ArrayList<>();

		List<EndpointDescription> allEndpoints = server.getEndpoints().stream()
				.map(server::mapEndpoint)
				.filter(ed -> filterProfileUris(ed, profileUris))
				.collect(toList());

		List<EndpointDescription> matchingEndpoints = allEndpoints.stream()
				.filter(ed -> filterEndpointUrls(ed, request.getEndpointUrl()))
				.collect(toList());

		GetEndpointsResponse response = new GetEndpointsResponse(
				serviceRequest.createResponseHeader(),
				matchingEndpoints.isEmpty() ?
						a(allEndpoints, EndpointDescription.class) :
						a(matchingEndpoints, EndpointDescription.class)
		);

		serviceRequest.setResponse(response);
	}

	private boolean filterProfileUris(EndpointDescription endpoint, List<String> profileUris) {
		return profileUris.size() == 0 || profileUris.contains(endpoint.getTransportProfileUri());
	}

	private boolean filterEndpointUrls(EndpointDescription endpoint, String endpointUrl) {
		try {
			String requestedHost = new URI(endpointUrl).parseServerAuthority().getHost();
			String endpointHost = new URI(endpoint.getEndpointUrl()).parseServerAuthority().getHost();

			return requestedHost.equalsIgnoreCase(endpointHost);
		} catch (Throwable e) {
			logger.warn("Unable to create URI.", e);
			return false;
		}
	}

	private ApplicationDescription getApplicationDescriptionFromRegisteredServer(RegisteredServer registeredServer, String[] localeIds) {
		LocalizedText serverName = null;
		if (localeIds != null && localeIds.length > 0 && registeredServer.getServerNames() != null) {
			List<String> locales = Arrays.asList(localeIds);
			List<LocalizedText> names = Arrays.asList(registeredServer.getServerNames());
			Optional<LocalizedText> found = names.stream().filter(n -> locales.contains(n.getLocale())).findFirst();
			if (found.isPresent())
				serverName = found.get();
		}
		if (serverName == null) {
			// client does not want to filter or filtered locale not found
			// we can select the most suitable on our own
			if (registeredServer.getServerNames() != null)
				serverName = registeredServer.getServerNames()[0];
			else
				serverName = new LocalizedText("en","undefined");
		}

		return new ApplicationDescription(
				registeredServer.getServerUri(),
				registeredServer.getProductUri(),
				serverName,
				registeredServer.getServerType(),
				registeredServer.getGatewayServerUri(),
				null,
				registeredServer.getDiscoveryUrls()
		);
	}

	@Override
	public void onFindServers(ServiceRequest<FindServersRequest, FindServersResponse> serviceRequest) {
		FindServersRequest request = serviceRequest.getRequest();

		ApplicationDescription selfAppDescription = getApplicationDescription(request.getEndpointUrl());


		LinkedList<ApplicationDescription> applicationDescriptions = new LinkedList<>();

		if (request.getServerUris() != null && request.getServerUris().length > 0) {
			// client wants a filtered list. So we need to filter out all unwanted entries

			List<String> wantedUris = Arrays.asList(request.getServerUris());

			if (wantedUris.contains(selfAppDescription.getApplicationUri())) {
				applicationDescriptions.add(selfAppDescription);
			}

			applicationDescriptions.addAll(this.getRegisteredServers().stream()
					.filter(r -> wantedUris.contains(r.getServerUri()))
					.map(r -> getApplicationDescriptionFromRegisteredServer(r, request.getLocaleIds()))
					.collect(Collectors.toList()));

		} else {
			// client wants the full list
			applicationDescriptions.add(selfAppDescription);
			applicationDescriptions.addAll(this.getRegisteredServers().stream()
					.map(r -> getApplicationDescriptionFromRegisteredServer(r, request.getLocaleIds())).collect(Collectors.toList()));
		}

		FindServersResponse response = new FindServersResponse(
				serviceRequest.createResponseHeader(),
				a(applicationDescriptions, ApplicationDescription.class)
		);

		serviceRequest.setResponse(response);
	}

	private ApplicationDescription getApplicationDescription(String endpointUrl) {
		List<String> allDiscoveryUrls = newArrayList(server.getDiscoveryUrls());

		List<String> matchingDiscoveryUrls = allDiscoveryUrls.stream()
				.filter(discoveryUrl -> {
					try {
						String requestedHost = new URI(endpointUrl).parseServerAuthority().getHost();
						String discoveryHost = new URI(discoveryUrl).parseServerAuthority().getHost();

						return requestedHost.equalsIgnoreCase(discoveryHost);
					} catch (Throwable e) {
						logger.warn("Unable to create URI.", e);
						return false;
					}
				})
				.collect(toList());

		return new ApplicationDescription(
				server.getConfig().getApplicationUri(),
				server.getConfig().getProductUri(),
				server.getConfig().getApplicationName(),
				ApplicationType.Server,
				null, null,
				matchingDiscoveryUrls.isEmpty() ?
						a(allDiscoveryUrls, String.class) :
						a(matchingDiscoveryUrls, String.class)
		);
	}

	protected List<RegisteredServer> getRegisteredServers() {
		return Collections.emptyList();
	}
}