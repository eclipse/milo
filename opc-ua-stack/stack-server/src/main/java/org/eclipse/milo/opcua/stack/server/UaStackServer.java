/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.server;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import javax.annotation.Nullable;

import com.google.common.collect.ForwardingTable;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import io.netty.channel.Channel;
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
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
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
import org.eclipse.milo.opcua.stack.server.transport.ServerChannelManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Strings.nullToEmpty;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;

public class UaStackServer {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ServiceHandlerTable serviceHandlerTable = new ServiceHandlerTable();

    private final Lazy<ApplicationDescription> applicationDescription = new Lazy<>();

    private final AtomicLong channelIds = new AtomicLong();
    private final AtomicLong tokenIds = new AtomicLong();

    private final List<Channel> channels = new CopyOnWriteArrayList<>();

    private final ServerChannelManager channelManager;

    private final UaStackServerConfig config;

    public UaStackServer(UaStackServerConfig config) {
        this.config = config;

        channelManager = new ServerChannelManager(this);

        config.getEndpoints().forEach(endpoint -> {
            String path = EndpointUtil.getPath(endpoint.getEndpointUrl());
            addServiceSet(path, new DefaultDiscoveryServiceSet());
        });
    }

    public UaStackServerConfig getConfig() {
        return config;
    }

    public CompletableFuture<UaStackServer> startup() {
        List<CompletableFuture<Unit>> futures = new ArrayList<>();

        config.getEndpoints()
            .stream()
            .sorted(Comparator.comparing(EndpointConfiguration::getTransportProfile))
            .forEach(endpoint -> {
                logger.info(
                    "Binding endpoint {} to {}:{} [{}/{}]",
                    endpoint.getEndpointUrl(),
                    endpoint.getBindAddress(),
                    endpoint.getBindPort(),
                    endpoint.getSecurityPolicy(),
                    endpoint.getSecurityMode());

                futures.add(
                    channelManager.bind(endpoint).exceptionally(ex -> {
                        logger.warn(
                            "Bind failed for endpoint {}",
                            endpoint.getEndpointUrl(), ex);

                        return Unit.VALUE;
                    })
                );
            });

        return FutureUtils.sequence(futures)
            .thenApply(u -> UaStackServer.this);
    }

    public CompletableFuture<UaStackServer> shutdown() {
        List<CompletableFuture<Unit>> futures = new ArrayList<>();

        config.getEndpoints().forEach(endpoint ->
            futures.add(
                channelManager.unbind(endpoint).exceptionally(ex -> {
                    logger.warn(
                        "Unbind failed for endpoint {}",
                        endpoint.getEndpointUrl(), ex);

                    return Unit.VALUE;
                })
            )
        );

        return FutureUtils.sequence(futures)
            .thenApply(u -> UaStackServer.this);
    }

    public void registerConnectedChannel(Channel channel) {
        channels.add(channel);
    }

    public void unregisterConnectedChannel(Channel channel) {
        channels.remove(channel);
    }

    public List<Channel> getConnectedChannels() {
        return channels;
    }

    public void onServiceRequest(String path, ServiceRequest serviceRequest) {
        logger.trace("onServiceRequest(path={}, request={})", path, serviceRequest);

        Class<? extends UaRequestMessage> requestClass = serviceRequest.getRequest().getClass();

        ServiceRequestHandler serviceHandler = serviceHandlerTable.get(path, requestClass);

        try {
            if (serviceHandler != null) {
                serviceHandler.handle(serviceRequest);
            } else {
                serviceRequest.setServiceFault(StatusCodes.Bad_ServiceUnsupported);
            }
        } catch (UaException e) {
            serviceRequest.setServiceFault(e);
        } catch (Throwable t) {
            logger.error("Uncaught Throwable executing handler: {}", serviceHandler, t);
            serviceRequest.setServiceFault(StatusCodes.Bad_InternalError);
        }
    }

    public long getNextChannelId() {
        return channelIds.incrementAndGet();
    }

    public long getNextTokenId() {
        return tokenIds.incrementAndGet();
    }

    private ApplicationDescription getApplicationDescription() {
        return applicationDescription.getOrCompute(() -> {
            List<String> discoveryUrls = config.getEndpoints()
                .stream()
                .map(EndpointConfiguration::getEndpointUrl)
                .filter(url -> url.endsWith("/discovery"))
                .collect(Collectors.toList());

            if (discoveryUrls.isEmpty()) {
                discoveryUrls = config.getEndpoints()
                    .stream()
                    .map(EndpointConfiguration::getEndpointUrl)
                    .collect(Collectors.toList());
            }

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
        return ImmutableList.<EndpointDescription>builder()
            .addAll(
                config.getEndpoints()
                    .stream()
                    .map(this::transformEndpoint)
                    .iterator()
            )
            .build();
    }

    private EndpointDescription transformEndpoint(EndpointConfiguration endpoint) {
        return new EndpointDescription(
            endpoint.getEndpointUrl(),
            getApplicationDescription(),
            certificateByteString(endpoint.getCertificate()),
            endpoint.getSecurityMode(),
            endpoint.getSecurityPolicy().getUri(),
            a(endpoint.getTokenPolicies(), UserTokenPolicy.class),
            endpoint.getTransportProfile().getUri(),
            ubyte(getSecurityLevel(endpoint.getSecurityPolicy(), endpoint.getSecurityMode()))
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

    public <T extends UaRequestMessage> void addServiceHandler(
        String path,
        Class<T> requestClass,
        ServiceRequestHandler serviceHandler) {

        logger.debug("Adding ServiceHandler for {} at {}", requestClass.getSimpleName(), path);

        serviceHandlerTable.put(path, requestClass, serviceHandler);
    }

    public <T extends UaRequestMessage> void removeServiceHandler(String path, Class<T> requestClass) {
        logger.debug("Removing ServiceHandler for {} at {}", requestClass.getSimpleName(), path);

        serviceHandlerTable.remove(path, requestClass);
    }

    public void addServiceSet(String path, AttributeServiceSet serviceSet) {
        addServiceHandler(path, ReadRequest.class, serviceSet::onRead);
        addServiceHandler(path, WriteRequest.class, serviceSet::onWrite);
    }

    public void addServiceSet(String path, AttributeHistoryServiceSet serviceSet) {
        addServiceHandler(path, HistoryReadRequest.class, serviceSet::onHistoryRead);
        addServiceHandler(path, HistoryUpdateRequest.class, serviceSet::onHistoryUpdate);
    }

    public void addServiceSet(String path, DiscoveryServiceSet serviceSet) {
        addServiceHandler(path, GetEndpointsRequest.class, serviceSet::onGetEndpoints);
        addServiceHandler(path, FindServersRequest.class, serviceSet::onFindServers);
        addServiceHandler(path, RegisterServerRequest.class, serviceSet::onRegisterServer);
    }

    public void addServiceSet(String path, QueryServiceSet serviceSet) {
        addServiceHandler(path, QueryFirstRequest.class, serviceSet::onQueryFirst);
        addServiceHandler(path, QueryNextRequest.class, serviceSet::onQueryNext);
    }

    public void addServiceSet(String path, MethodServiceSet serviceSet) {
        addServiceHandler(path, CallRequest.class, serviceSet::onCall);
    }

    public void addServiceSet(String path, MonitoredItemServiceSet serviceSet) {
        addServiceHandler(path, CreateMonitoredItemsRequest.class, serviceSet::onCreateMonitoredItems);
        addServiceHandler(path, ModifyMonitoredItemsRequest.class, serviceSet::onModifyMonitoredItems);
        addServiceHandler(path, DeleteMonitoredItemsRequest.class, serviceSet::onDeleteMonitoredItems);
        addServiceHandler(path, SetMonitoringModeRequest.class, serviceSet::onSetMonitoringMode);
        addServiceHandler(path, SetTriggeringRequest.class, serviceSet::onSetTriggering);
    }

    public void addServiceSet(String path, NodeManagementServiceSet serviceSet) {
        addServiceHandler(path, AddNodesRequest.class, serviceSet::onAddNodes);
        addServiceHandler(path, DeleteNodesRequest.class, serviceSet::onDeleteNodes);
        addServiceHandler(path, AddReferencesRequest.class, serviceSet::onAddReferences);
        addServiceHandler(path, DeleteReferencesRequest.class, serviceSet::onDeleteReferences);
    }

    public void addServiceSet(String path, SessionServiceSet serviceSet) {
        addServiceHandler(path, CreateSessionRequest.class, serviceSet::onCreateSession);
        addServiceHandler(path, ActivateSessionRequest.class, serviceSet::onActivateSession);
        addServiceHandler(path, CloseSessionRequest.class, serviceSet::onCloseSession);
        addServiceHandler(path, CancelRequest.class, serviceSet::onCancel);
    }

    public void addServiceSet(String path, SubscriptionServiceSet serviceSet) {
        addServiceHandler(path, CreateSubscriptionRequest.class, serviceSet::onCreateSubscription);
        addServiceHandler(path, ModifySubscriptionRequest.class, serviceSet::onModifySubscription);
        addServiceHandler(path, DeleteSubscriptionsRequest.class, serviceSet::onDeleteSubscriptions);
        addServiceHandler(path, TransferSubscriptionsRequest.class, serviceSet::onTransferSubscriptions);
        addServiceHandler(path, SetPublishingModeRequest.class, serviceSet::onSetPublishingMode);
        addServiceHandler(path, PublishRequest.class, serviceSet::onPublish);
        addServiceHandler(path, RepublishRequest.class, serviceSet::onRepublish);
    }

    public void addServiceSet(String path, ViewServiceSet serviceSet) {
        addServiceHandler(path, BrowseRequest.class, serviceSet::onBrowse);
        addServiceHandler(path, BrowseNextRequest.class, serviceSet::onBrowseNext);
        addServiceHandler(path, TranslateBrowsePathsToNodeIdsRequest.class, serviceSet::onTranslateBrowsePaths);
        addServiceHandler(path, RegisterNodesRequest.class, serviceSet::onRegisterNodes);
        addServiceHandler(path, UnregisterNodesRequest.class, serviceSet::onUnregisterNodes);
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
                .filter(ed -> !ed.getEndpointUrl().endsWith("/discovery"))
                .filter(ed -> filterProfileUris(ed, profileUris))
                .collect(toList());

            List<EndpointDescription> matchingEndpoints = allEndpoints.stream()
                .filter(endpoint -> filterEndpointUrls(endpoint, request.getEndpointUrl()))
                .map(endpoint ->
                    replaceApplicationDescription(
                        endpoint,
                        getFilteredApplicationDescription(request.getEndpointUrl())
                    )
                )
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
                String requestedHost = EndpointUtil.getHost(endpointUrl);
                String endpointHost = EndpointUtil.getHost(endpoint.getEndpointUrl());

                return nullToEmpty(requestedHost).equalsIgnoreCase(endpointHost);
            } catch (Throwable e) {
                logger.debug("Unable to create URI.", e);
                return false;
            }
        }

        private EndpointDescription replaceApplicationDescription(
            EndpointDescription endpoint,
            ApplicationDescription applicationDescription) {

            return new EndpointDescription(
                endpoint.getEndpointUrl(),
                applicationDescription,
                endpoint.getServerCertificate(),
                endpoint.getSecurityMode(),
                endpoint.getSecurityPolicyUri(),
                endpoint.getUserIdentityTokens(),
                endpoint.getTransportProfileUri(),
                endpoint.getSecurityLevel()
            );
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
                .filter(url -> url.endsWith("/discovery"))
                .collect(Collectors.toList());

            if (allDiscoveryUrls.isEmpty()) {
                allDiscoveryUrls = config.getEndpoints()
                    .stream()
                    .map(EndpointConfiguration::getEndpointUrl)
                    .collect(Collectors.toList());
            }

            List<String> matchingDiscoveryUrls = allDiscoveryUrls.stream()
                .filter(discoveryUrl -> {
                    try {

                        String requestedHost = EndpointUtil.getHost(endpointUrl);
                        String discoveryHost = EndpointUtil.getHost(discoveryUrl);

                        logger.debug("requestedHost={}, discoveryHost={}", requestedHost, discoveryHost);

                        return nullToEmpty(requestedHost).equalsIgnoreCase(discoveryHost);
                    } catch (Throwable e) {
                        logger.debug("Unable to create URI.", e);
                        return false;
                    }
                })
                .collect(toList());


            logger.debug("Matching discovery URLs: {}", matchingDiscoveryUrls);

            return new ApplicationDescription(
                config.getApplicationUri(),
                config.getProductUri(),
                config.getApplicationName(),
                ApplicationType.Server,
                null,
                null,
                matchingDiscoveryUrls.isEmpty() ?
                    a(allDiscoveryUrls, String.class) :
                    a(matchingDiscoveryUrls, String.class)
            );
        }

        private boolean filterServerUris(ApplicationDescription ad, List<String> serverUris) {
            return serverUris.size() == 0 || serverUris.contains(ad.getApplicationUri());
        }

    }

    private static class ServiceHandlerTable extends
        ForwardingTable<String, Class<? extends UaRequestMessage>, ServiceRequestHandler> {

        private final Table<String, Class<? extends UaRequestMessage>, ServiceRequestHandler> delegate =
            Tables.synchronizedTable(HashBasedTable.create());

        @Override
        protected Table<String, Class<? extends UaRequestMessage>, ServiceRequestHandler> delegate() {
            return delegate;
        }

    }

}
