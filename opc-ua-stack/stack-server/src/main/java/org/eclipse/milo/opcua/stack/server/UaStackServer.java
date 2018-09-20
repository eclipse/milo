/*
 * Copyright (c) 2018 Kevin Herron
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

package org.eclipse.milo.opcua.stack.server;

import java.net.InetAddress;
import java.net.URI;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterServerRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Lazy;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.eclipse.milo.opcua.stack.server.services.AttributeHistoryServiceSet;
import org.eclipse.milo.opcua.stack.server.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.server.services.DiscoveryServiceSet;
import org.eclipse.milo.opcua.stack.server.services.MethodServiceSet;
import org.eclipse.milo.opcua.stack.server.services.MonitoredItemServiceSet;
import org.eclipse.milo.opcua.stack.server.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.server.services.QueryServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequestHandler;
import org.eclipse.milo.opcua.stack.server.services.SessionServiceSet;
import org.eclipse.milo.opcua.stack.server.services.SubscriptionServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ViewServiceSet;
import org.eclipse.milo.opcua.stack.server.transport.SocketServerManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;

public class UaStackServer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<Class<? extends UaRequestMessage>, ServiceRequestHandler>
        serviceRequestHandlers = Maps.newConcurrentMap();

    private final Lazy<ApplicationDescription> applicationDescription = new Lazy<>();
    private final Lazy<ImmutableList<EndpointDescription>> endpointDescriptions = new Lazy<>();

    private final AtomicLong channelIds = new AtomicLong();
    private final AtomicLong tokenIds = new AtomicLong();

    private final UaStackServerConfig config;

    public UaStackServer(UaStackServerConfig config) {
        this.config = config;

        addServiceSet(new DefaultDiscoveryServiceSet());

        addServiceSet(new AttributeServiceSet() {});
        addServiceSet(new MethodServiceSet() {});
        addServiceSet(new MonitoredItemServiceSet() {});
        addServiceSet(new NodeManagementServiceSet() {});
        addServiceSet(new QueryServiceSet() {});
        addServiceSet(new SessionServiceSet() {});
        addServiceSet(new SubscriptionServiceSet() {});
        addServiceSet(new ViewServiceSet() {});
    }

    public UaStackServerConfig getConfig() {
        return config;
    }

    public CompletableFuture<UaStackServer> startup() {
        List<CompletableFuture<Unit>> futures = new ArrayList<>();

        config.getEndpoints().forEach(endpoint -> {
            logger.info(
                "Binding endpoint {} to {} [{}/{}]",
                endpoint.getEndpointUrl(),
                endpoint.getBindAddress(),
                endpoint.getSecurityPolicy(),
                endpoint.getSecurityMode());

            futures.add(
                SocketServerManager.get()
                    .bind(endpoint, UaStackServer.this)
                    .exceptionally(ex -> Unit.VALUE)
            );
        });

        return FutureUtils.sequence(futures)
            .thenApply(u -> UaStackServer.this);
    }

    public CompletableFuture<UaStackServer> shutdown() {
        List<CompletableFuture<Unit>> futures = new ArrayList<>();

        config.getEndpoints().forEach(endpoint ->
            futures.add(
                SocketServerManager.get()
                    .unbind(endpoint, UaStackServer.this)
                    .exceptionally(ex -> Unit.VALUE)
            )
        );

        return FutureUtils.sequence(futures)
            .thenApply(u -> UaStackServer.this);
    }

    public void onServiceRequest(ServiceRequest serviceRequest) {
        logger.trace("onServiceRequest({})", serviceRequest);

        Class<? extends UaRequestMessage> requestClass = serviceRequest.getRequest().getClass();
        ServiceRequestHandler handler = serviceRequestHandlers.get(requestClass);

        try {
            if (handler != null) {
                handler.handle(serviceRequest);
            } else {
                serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
            }
        } catch (UaException e) {
            serviceRequest.setServiceFault(e);
        } catch (Throwable t) {
            logger.error("Uncaught Throwable executing ServiceRequestHandler: {}", handler, t);
            serviceRequest.setServiceFault(StatusCodes.Bad_InternalError);
        }
    }

    // TODO this doesn't belong here
    public long getNextChannelId() {
        return channelIds.incrementAndGet();
    }

    // TODO this doesn't belong here
    public long getNextTokenId() {
        return tokenIds.incrementAndGet();
    }

    public ApplicationDescription getApplicationDescription() {
        return applicationDescription.getOrCompute(() -> {
            List<String> discoveryUrls = config.getEndpoints()
                .stream()
                .map(EndpointConfiguration::getEndpointUrl)
                .collect(Collectors.toList());

            return new ApplicationDescription(
                config.getApplicationUri(),
                config.getProductUri(),
                config.getApplicationName(),
                ApplicationType.Server,
                null,
                null,
                a(discoveryUrls, String.class)
            );
        });
    }

    public ImmutableList<EndpointDescription> getEndpointDescriptions() {
        return endpointDescriptions.getOrCompute(
            () ->
                ImmutableList.<EndpointDescription>builder()
                    .addAll(
                        config.getEndpoints()
                            .stream()
                            .map(this::transformEndpoint)
                            .iterator()
                    )
                    .build()
        );
    }

    private ByteString certificateByteString(@Nullable X509Certificate certificate) {
        if (certificate != null) {
            try {
                return ByteString.of(certificate.getEncoded());
            } catch (CertificateEncodingException e) {
                logger.error("Error decoding certificate.", e);
                return ByteString.NULL_VALUE;
            }
        } else {
            return ByteString.NULL_VALUE;
        }
    }

    private EndpointDescription transformEndpoint(EndpointConfiguration endpoint) {
        return new EndpointDescription(
            endpoint.getEndpointUrl(),
            getApplicationDescription(),
            certificateByteString(endpoint.getCertificate()),
            endpoint.getSecurityMode(),
            endpoint.getSecurityPolicy().getSecurityPolicyUri(),
            a(endpoint.getTokenPolicies(), UserTokenPolicy.class),
            endpoint.getTransportProfile().getUri(),
            ubyte(getSecurityLevel(endpoint.getSecurityPolicy(), endpoint.getSecurityMode()))
        );
    }

    private static short getSecurityLevel(SecurityPolicy securityPolicy, MessageSecurityMode securityMode) {
        short securityLevel = 0;

        switch (securityPolicy) {
            case Aes256_Sha256_RsaPss:
            case Basic256Sha256:
                securityLevel |= 0x08;
                break;
            case Aes128_Sha256_RsaOaep:
                securityLevel |= 0x04;
                break;
            case Basic256:
            case Basic128Rsa15:
                securityLevel |= 0x01;
                break;
            case None:
            default:
                break;
        }

        switch (securityMode) {
            case SignAndEncrypt:
                securityLevel |= 0x80;
                break;
            case Sign:
                securityLevel |= 0x40;
                break;
            default:
                securityLevel |= 0x20;
                break;
        }

        return securityLevel;
    }

    public <T extends UaRequestMessage> void addRequestHandler(
        Class<T> requestClass,
        ServiceRequestHandler requestHandler) {

        serviceRequestHandlers.put(requestClass, requestHandler);
    }

    public void addServiceSet(AttributeServiceSet serviceSet) {
        addRequestHandler(ReadRequest.class, serviceSet::onRead);
        addRequestHandler(WriteRequest.class, serviceSet::onWrite);
    }

    public void addServiceSet(AttributeHistoryServiceSet serviceSet) {
        addRequestHandler(HistoryReadRequest.class, serviceSet::onHistoryRead);
        addRequestHandler(HistoryUpdateRequest.class, serviceSet::onHistoryUpdate);
    }

    public void addServiceSet(DiscoveryServiceSet serviceSet) {
        addRequestHandler(GetEndpointsRequest.class, serviceSet::onGetEndpoints);
        addRequestHandler(FindServersRequest.class, serviceSet::onFindServers);
        addRequestHandler(RegisterServerRequest.class, serviceSet::onRegisterServer);
    }

    public void addServiceSet(QueryServiceSet serviceSet) {
        addRequestHandler(QueryFirstRequest.class, serviceSet::onQueryFirst);
        addRequestHandler(QueryNextRequest.class, serviceSet::onQueryNext);
    }

    public void addServiceSet(MethodServiceSet serviceSet) {
        addRequestHandler(CallRequest.class, serviceSet::onCall);
    }

    public void addServiceSet(MonitoredItemServiceSet serviceSet) {
        addRequestHandler(CreateMonitoredItemsRequest.class, serviceSet::onCreateMonitoredItems);
        addRequestHandler(ModifyMonitoredItemsRequest.class, serviceSet::onModifyMonitoredItems);
        addRequestHandler(DeleteMonitoredItemsRequest.class, serviceSet::onDeleteMonitoredItems);
        addRequestHandler(SetMonitoringModeRequest.class, serviceSet::onSetMonitoringMode);
        addRequestHandler(SetTriggeringRequest.class, serviceSet::onSetTriggering);
    }

    public void addServiceSet(NodeManagementServiceSet serviceSet) {
        addRequestHandler(AddNodesRequest.class, serviceSet::onAddNodes);
        addRequestHandler(DeleteNodesRequest.class, serviceSet::onDeleteNodes);
        addRequestHandler(AddReferencesRequest.class, serviceSet::onAddReferences);
        addRequestHandler(DeleteReferencesRequest.class, serviceSet::onDeleteReferences);
    }

    public void addServiceSet(SessionServiceSet serviceSet) {
        addRequestHandler(CreateSessionRequest.class, serviceSet::onCreateSession);
        addRequestHandler(ActivateSessionRequest.class, serviceSet::onActivateSession);
        addRequestHandler(CloseSessionRequest.class, serviceSet::onCloseSession);
        addRequestHandler(CancelRequest.class, serviceSet::onCancel);
    }

    public void addServiceSet(SubscriptionServiceSet serviceSet) {
        addRequestHandler(CreateSubscriptionRequest.class, serviceSet::onCreateSubscription);
        addRequestHandler(ModifySubscriptionRequest.class, serviceSet::onModifySubscription);
        addRequestHandler(DeleteSubscriptionsRequest.class, serviceSet::onDeleteSubscriptions);
        addRequestHandler(TransferSubscriptionsRequest.class, serviceSet::onTransferSubscriptions);
        addRequestHandler(SetPublishingModeRequest.class, serviceSet::onSetPublishingMode);
        addRequestHandler(PublishRequest.class, serviceSet::onPublish);
        addRequestHandler(RepublishRequest.class, serviceSet::onRepublish);
    }

    public void addServiceSet(ViewServiceSet serviceSet) {
        addRequestHandler(BrowseRequest.class, serviceSet::onBrowse);
        addRequestHandler(BrowseNextRequest.class, serviceSet::onBrowseNext);
        addRequestHandler(TranslateBrowsePathsToNodeIdsRequest.class, serviceSet::onTranslateBrowsePaths);
        addRequestHandler(RegisterNodesRequest.class, serviceSet::onRegisterNodes);
        addRequestHandler(UnregisterNodesRequest.class, serviceSet::onUnregisterNodes);
    }

    private class DefaultDiscoveryServiceSet implements DiscoveryServiceSet {
        @Override
        public void onGetEndpoints(ServiceRequest serviceRequest) {
            GetEndpointsRequest request = (GetEndpointsRequest) serviceRequest.getRequest();

            List<String> profileUris = request.getProfileUris() != null ?
                newArrayList(request.getProfileUris()) :
                new ArrayList<>();

            List<EndpointDescription> allEndpoints = getEndpointDescriptions()
                .stream()
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
                logger.debug("Unable to create URI.", e);
                return false;
            }
        }

        @Override
        public void onFindServers(ServiceRequest serviceRequest) {
            FindServersRequest request = (FindServersRequest) serviceRequest.getRequest();

            List<String> serverUris = request.getServerUris() != null ?
                newArrayList(request.getServerUris()) :
                new ArrayList<>();

            List<ApplicationDescription> applicationDescriptions =
                newArrayList(getFilteredApplicationDescription(request.getEndpointUrl()));

            applicationDescriptions = applicationDescriptions.stream()
                .filter(ad -> filterServerUris(ad, serverUris))
                .collect(toList());

            FindServersResponse response = new FindServersResponse(
                serviceRequest.createResponseHeader(),
                a(applicationDescriptions, ApplicationDescription.class)
            );

            serviceRequest.setResponse(response);
        }

        private ApplicationDescription getFilteredApplicationDescription(String endpointUrl) {
            List<String> allDiscoveryUrls = config.getEndpoints()
                .stream()
                .map(EndpointConfiguration::getEndpointUrl)
                .collect(Collectors.toList());

            List<String> matchingDiscoveryUrls = allDiscoveryUrls.stream()
                .filter(discoveryUrl -> {
                    try {
                        String requestedHost = new URI(endpointUrl).parseServerAuthority().getHost();
                        String discoveryHost = new URI(discoveryUrl).parseServerAuthority().getHost();

                        logger.debug("requestedHost={}, discoveryHost={}", requestedHost, discoveryHost);

                        return requestedHost.equalsIgnoreCase(discoveryHost);
                    } catch (Throwable e) {
                        logger.debug("Unable to create URI.", e);
                        return false;
                    }
                })
                .collect(toList());

            if (matchingDiscoveryUrls.isEmpty()) {
                matchingDiscoveryUrls = allDiscoveryUrls.stream()
                    .filter(discoveryUrl -> {
                        try {
                            String requestedHost = new URI(endpointUrl).parseServerAuthority().getHost();
                            String discoveryHost = new URI(discoveryUrl).parseServerAuthority().getHost();
                            InetAddress requestedHostAddress = InetAddress.getByName(requestedHost);
                            InetAddress discoveryHostAddress = InetAddress.getByName(discoveryHost);

                            logger.debug(
                                "requestedHostAddress={}, discoveryHostAddress={}",
                                requestedHost, discoveryHost);

                            return requestedHostAddress.equals(discoveryHostAddress);
                        } catch (Throwable e) {
                            logger.debug("Unable to create URI.", e);
                            return false;
                        }
                    })
                    .collect(toList());
            }

            logger.debug("Matching discovery URLs: {}", matchingDiscoveryUrls);

            return new ApplicationDescription(
                config.getApplicationUri(),
                config.getProductUri(),
                config.getApplicationName(),
                ApplicationType.Server,
                null, null,
                matchingDiscoveryUrls.isEmpty() ?
                    a(allDiscoveryUrls, String.class) :
                    a(matchingDiscoveryUrls, String.class)
            );
        }

        private boolean filterServerUris(ApplicationDescription ad, List<String> serverUris) {
            return serverUris.size() == 0 || serverUris.contains(ad.getApplicationUri());
        }

    }

}
