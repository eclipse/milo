/*
 * Copyright (c) 2016 Kevin Herron
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

import java.net.InetAddress;
import java.net.URI;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Sets;
import io.netty.channel.Channel;
import io.netty.util.AttributeKey;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.application.CertificateManager;
import org.eclipse.milo.opcua.stack.core.application.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.application.UaStackServer;
import org.eclipse.milo.opcua.stack.core.application.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.DiscoveryServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.MethodServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.MonitoredItemServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.QueryServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequestHandler;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceResponse;
import org.eclipse.milo.opcua.stack.core.application.services.SessionServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.SubscriptionServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ViewServiceSet;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.util.FutureUtils;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.eclipse.milo.opcua.stack.server.Endpoint;
import org.eclipse.milo.opcua.stack.server.config.UaTcpStackServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;

public class UaTcpStackServer implements UaStackServer {

    /**
     * The {@link AttributeKey} that maps to the {@link Channel} bound to a {@link ServerSecureChannel}.
     */
    public static final AttributeKey<Channel> BoundChannelKey = AttributeKey.valueOf("bound-channel");

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicLong channelIds = new AtomicLong();
    private final AtomicLong tokenIds = new AtomicLong();

    private final Map<Class<? extends UaRequestMessage>, ServiceRequestHandler<UaRequestMessage, UaResponseMessage>>
        handlers = Maps.newConcurrentMap();

    private final Map<Long, ServerSecureChannel> secureChannels = Maps.newConcurrentMap();

    private final ListMultimap<Long, ServiceResponse> responseQueues =
        Multimaps.synchronizedListMultimap(ArrayListMultimap.create());

    private final List<Endpoint> endpoints = Lists.newCopyOnWriteArrayList();
    private final Set<String> discoveryUrls = Sets.newConcurrentHashSet();

    private final HashedWheelTimer wheelTimer = Stack.sharedWheelTimer();
    private final Map<Long, Timeout> timeouts = Maps.newConcurrentMap();

    private final UaTcpStackServerConfig config;

    public UaTcpStackServer(UaTcpStackServerConfig config) {
        this.config = config;

        addServiceSet(new DefaultDiscoveryServiceSet());

        addServiceSet(new AttributeServiceSet() {
        });
        addServiceSet(new MethodServiceSet() {
        });
        addServiceSet(new MonitoredItemServiceSet() {
        });
        addServiceSet(new NodeManagementServiceSet() {
        });
        addServiceSet(new QueryServiceSet() {
        });
        addServiceSet(new SessionServiceSet() {
        });
        addServiceSet(new SubscriptionServiceSet() {
        });
        addServiceSet(new ViewServiceSet() {
        });
    }

    public UaTcpStackServerConfig getConfig() {
        return config;
    }

    @Override
    public CompletableFuture<UaTcpStackServer> startup() {
        Stream<CompletableFuture<Unit>> stream = endpoints.stream().map(endpoint -> {
            URI endpointUri = endpoint.getEndpointUri();
            String bindAddress = endpoint.getBindAddress().orElse(endpointUri.getHost());
            int bindPort = endpointUri.getPort();

            CompletableFuture<Unit> future = SocketServers.bindServer(this, bindAddress, bindPort);

            future.thenRun(() -> {
                logger.info("{} bound to {}:{} [{}/{}]",
                    endpoint.getEndpointUri(), bindAddress, bindPort,
                    endpoint.getSecurityPolicy(), endpoint.getMessageSecurity());

                addDiscoveryUrl(endpointUri);
            });

            return future;
        });

        return FutureUtils.sequence(stream).thenApply(v -> UaTcpStackServer.this);
    }

    private void addDiscoveryUrl(URI endpointUri) {
        String serverName = config.getServerName();

        StringBuilder discoveryUrl = new StringBuilder();

        discoveryUrl.append("opc.tcp://")
            .append(endpointUri.getHost())
            .append(":")
            .append(endpointUri.getPort());

        if (!serverName.isEmpty()) {
            discoveryUrl.append("/").append(serverName);
        }

        discoveryUrls.add(discoveryUrl.toString());
    }

    @Override
    public CompletableFuture<UaTcpStackServer> shutdown() {
        Stream<CompletableFuture<Unit>> stream = endpoints.stream().map(endpoint -> {
            URI endpointUri = endpoint.getEndpointUri();
            String bindAddress = endpoint.getBindAddress().orElse(endpointUri.getHost());
            int bindPort = endpointUri.getPort();

            return SocketServers.unbindServer(this, bindAddress, bindPort);
        });

        return FutureUtils.sequence(stream)
            .thenCompose(ignored -> {
                List<ServerSecureChannel> channels = newArrayList(secureChannels.values());

                Stream<CompletableFuture<Unit>> futures =
                    channels.stream().map(this::closeSecureChannel);

                return FutureUtils.sequence(futures);
            })
            .thenApply(ignored -> UaTcpStackServer.this);
    }

    public void receiveRequest(ServiceRequest<UaRequestMessage, UaResponseMessage> serviceRequest) {
        logger.trace("Received {} on {}.", serviceRequest, serviceRequest.getSecureChannel());

        serviceRequest.getFuture().whenComplete((response, throwable) -> {
            long requestId = serviceRequest.getRequestId();
            UaRequestMessage request = serviceRequest.getRequest();

            ServiceResponse serviceResponse = response != null ?
                new ServiceResponse(request, requestId, response) :
                new ServiceResponse(request, requestId, serviceRequest.createServiceFault(throwable));

            ServerSecureChannel secureChannel = serviceRequest.getSecureChannel();
            boolean secureChannelValid = secureChannels.containsKey(secureChannel.getChannelId());

            if (secureChannelValid) {
                Channel channel = secureChannel.attr(BoundChannelKey).get();

                if (channel != null) {
                    if (serviceResponse.isServiceFault()) {
                        logger.debug("Sending {} on {}.", serviceResponse, secureChannel);
                    } else {
                        logger.trace("Sending {} on {}.", serviceResponse, secureChannel);
                    }
                    channel.writeAndFlush(serviceResponse, channel.voidPromise());
                } else {
                    logger.trace("Queueing {} for unbound {}.", serviceResponse, secureChannel);
                    responseQueues.put(secureChannel.getChannelId(), serviceResponse);
                }
            }
        });

        Class<? extends UaRequestMessage> requestClass = serviceRequest.getRequest().getClass();
        ServiceRequestHandler<UaRequestMessage, UaResponseMessage> handler = handlers.get(requestClass);

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

    @Override
    public ApplicationDescription getApplicationDescription() {
        return new ApplicationDescription(
            config.getApplicationUri(),
            config.getProductUri(),
            config.getApplicationName(),
            ApplicationType.Server,
            null, null,
            a(newArrayList(this.discoveryUrls), String.class)
        );
    }

    public List<Endpoint> getEndpoints() {
        return endpoints;
    }

    @Override
    public EndpointDescription[] getEndpointDescriptions() {
        return getEndpoints().stream()
            .map(this::mapEndpoint)
            .toArray(EndpointDescription[]::new);
    }

    @Override
    public SignedSoftwareCertificate[] getSoftwareCertificates() {
        List<SignedSoftwareCertificate> softwareCertificates = config.getSoftwareCertificates();

        return softwareCertificates.toArray(new SignedSoftwareCertificate[softwareCertificates.size()]);
    }

    @Override
    public List<UserTokenPolicy> getUserTokenPolicies() {
        return config.getUserTokenPolicies();
    }

    public List<String> getEndpointUrls() {
        return endpoints.stream().map(e -> e.getEndpointUri().toString()).collect(toList());
    }

    public Set<String> getDiscoveryUrls() {
        return discoveryUrls;
    }

    @Override
    public CertificateManager getCertificateManager() {
        return config.getCertificateManager();
    }

    @Override
    public CertificateValidator getCertificateValidator() {
        return config.getCertificateValidator();
    }

    @Override
    public ExecutorService getExecutorService() {
        return config.getExecutor();
    }

    @Override
    public ChannelConfig getChannelConfig() {
        return config.getChannelConfig();
    }

    private long nextChannelId() {
        return channelIds.incrementAndGet();
    }

    public long nextTokenId() {
        return tokenIds.incrementAndGet();
    }

    @Override
    public ServerSecureChannel openSecureChannel() {
        ServerSecureChannel channel = new ServerSecureChannel();
        channel.setChannelId(nextChannelId());
        long channelId = channel.getChannelId();
        secureChannels.put(channelId, channel);
        return channel;
    }

    @Override
    public CompletableFuture<Unit> closeSecureChannel(ServerSecureChannel secureChannel) {
        long channelId = secureChannel.getChannelId();

        if (secureChannels.remove(channelId) != null) {
            logger.debug("Removed secure channel id={}", channelId);
        }

        Channel channel = secureChannel.attr(BoundChannelKey).get();

        if (channel != null) {
            logger.debug("Closing secure channel id={}, bound channel: {}", channelId, channel);
            CompletableFuture<Unit> closeFuture = new CompletableFuture<>();
            channel.close().addListener(future -> closeFuture.complete(Unit.VALUE));
            return closeFuture;
        } else {
            return CompletableFuture.completedFuture(Unit.VALUE);
        }
    }

    public void secureChannelIssuedOrRenewed(ServerSecureChannel secureChannel, long lifetimeMillis) {
        long channelId = secureChannel.getChannelId();

        /*
         * Cancel any existing timeouts and start a new one.
         */
        Timeout timeout = timeouts.remove(channelId);
        boolean cancelled = (timeout == null || timeout.cancel());

        if (cancelled) {
            timeout = wheelTimer.newTimeout(t ->
                closeSecureChannel(secureChannel), lifetimeMillis, TimeUnit.MILLISECONDS);

            timeouts.put(channelId, timeout);

            /*
             * If this is a reconnect there might be responses queued, so drain those.
             */
            Channel channel = secureChannel.attr(BoundChannelKey).get();

            if (channel != null) {
                List<ServiceResponse> responses = responseQueues.removeAll(channelId);

                responses.forEach(channel::write);
                channel.flush();
            }
        }
    }

    public ServerSecureChannel getSecureChannel(long channelId) {
        return secureChannels.get(channelId);
    }

    @SuppressWarnings("unchecked")
    public <T extends UaRequestMessage, U extends UaResponseMessage> void addRequestHandler(
        Class<T> requestClass, ServiceRequestHandler<T, U> requestHandler) {

        ServiceRequestHandler<UaRequestMessage, UaResponseMessage> handler =
            (ServiceRequestHandler<UaRequestMessage, UaResponseMessage>) requestHandler;

        handlers.put(requestClass, handler);
    }

    @Override
    public UaTcpStackServer addEndpoint(String endpointUri,
                                        String bindAddress,
                                        X509Certificate certificate,
                                        SecurityPolicy securityPolicy,
                                        MessageSecurityMode messageSecurity) {

        boolean invalidConfiguration = messageSecurity == MessageSecurityMode.Invalid ||
            (securityPolicy == SecurityPolicy.None && messageSecurity != MessageSecurityMode.None) ||
            (securityPolicy != SecurityPolicy.None && messageSecurity == MessageSecurityMode.None);

        if (invalidConfiguration) {
            logger.warn("Invalid configuration, ignoring: {} + {}", securityPolicy, messageSecurity);
        } else {
            try {
                URI uri = new URI(endpointUri);

                endpoints.add(new Endpoint(uri, bindAddress, certificate, securityPolicy, messageSecurity));
            } catch (Throwable e) {
                logger.warn("Invalid endpoint URI, ignoring: {}", endpointUri);
            }
        }

        return this;
    }

    private EndpointDescription mapEndpoint(Endpoint endpoint) {
        List<UserTokenPolicy> userTokenPolicies = config.getUserTokenPolicies();

        return new EndpointDescription(
            endpoint.getEndpointUri().toString(),
            getApplicationDescription(),
            certificateByteString(endpoint.getCertificate()),
            endpoint.getMessageSecurity(),
            endpoint.getSecurityPolicy().getSecurityPolicyUri(),
            userTokenPolicies.toArray(new UserTokenPolicy[userTokenPolicies.size()]),
            Stack.UA_TCP_BINARY_TRANSPORT_URI,
            ubyte(endpoint.getSecurityLevel())
        );
    }

    private ByteString certificateByteString(Optional<X509Certificate> certificate) {
        if (certificate.isPresent()) {
            try {
                return ByteString.of(certificate.get().getEncoded());
            } catch (CertificateEncodingException e) {
                logger.error("Error decoding certificate.", e);
                return ByteString.NULL_VALUE;
            }
        } else {
            return ByteString.NULL_VALUE;
        }
    }

    private class DefaultDiscoveryServiceSet implements DiscoveryServiceSet {
        @Override
        public void onGetEndpoints(ServiceRequest<GetEndpointsRequest, GetEndpointsResponse> serviceRequest) {
            GetEndpointsRequest request = serviceRequest.getRequest();

            List<String> profileUris = request.getProfileUris() != null ?
                newArrayList(request.getProfileUris()) :
                new ArrayList<>();

            List<EndpointDescription> allEndpoints = endpoints.stream()
                .map(endpoint -> {
                    List<UserTokenPolicy> userTokenPolicies = config.getUserTokenPolicies();

                    return new EndpointDescription(
                        endpoint.getEndpointUri().toString(),
                        getFilteredApplicationDescription(request.getEndpointUrl()),
                        certificateByteString(endpoint.getCertificate()),
                        endpoint.getMessageSecurity(),
                        endpoint.getSecurityPolicy().getSecurityPolicyUri(),
                        userTokenPolicies.toArray(new UserTokenPolicy[userTokenPolicies.size()]),
                        Stack.UA_TCP_BINARY_TRANSPORT_URI,
                        ubyte(endpoint.getSecurityLevel())
                    );
                })
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
        public void onFindServers(ServiceRequest<FindServersRequest, FindServersResponse> serviceRequest) {
            FindServersRequest request = serviceRequest.getRequest();

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
            List<String> allDiscoveryUrls = newArrayList(discoveryUrls);

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
