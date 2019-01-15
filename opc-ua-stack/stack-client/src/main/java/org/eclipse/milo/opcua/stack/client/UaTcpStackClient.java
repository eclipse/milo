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

package org.eclipse.milo.opcua.stack.client;

import java.net.ConnectException;
import java.net.URI;
import java.nio.channels.ClosedChannelException;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ConnectTimeoutException;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.HashedWheelTimer;
import io.netty.util.Timeout;
import org.eclipse.milo.opcua.stack.client.config.UaTcpStackClientConfig;
import org.eclipse.milo.opcua.stack.client.handlers.UaRequestFuture;
import org.eclipse.milo.opcua.stack.client.handlers.UaTcpClientAcknowledgeHandler;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.UaServiceFaultException;
import org.eclipse.milo.opcua.stack.core.application.UaStackClient;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.FindServersResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.GetEndpointsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ResponseHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.ServiceFault;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.ExecutionQueue;
import org.eclipse.milo.opcua.stack.core.util.LongSequence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class UaTcpStackClient implements UaStackClient {

    private static final long DEFAULT_TIMEOUT_MS = 60000;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final LongSequence requestHandles = new LongSequence(0, UInteger.MAX_VALUE);

    private final Map<UInteger, CompletableFuture<UaResponseMessage>> pending = Maps.newConcurrentMap();
    private final Map<UInteger, Timeout> timeouts = Maps.newConcurrentMap();

    private final ExecutionQueue deliveryQueue;

    private final HashedWheelTimer wheelTimer;

    private final ApplicationDescription application;

    private final ClientChannelManager channelManager;

    private final UaTcpStackClientConfig config;

    public UaTcpStackClient(UaTcpStackClientConfig config) {
        this.config = config;

        deliveryQueue = new ExecutionQueue(config.getExecutor());

        wheelTimer = config.getWheelTimer();

        application = new ApplicationDescription(
            config.getApplicationUri(),
            config.getProductUri(),
            config.getApplicationName(),
            ApplicationType.Client,
            null, null, null);

        channelManager = new ClientChannelManager(this);
    }

    public UaTcpStackClientConfig getConfig() {
        return config;
    }

    @Override
    public CompletableFuture<UaStackClient> connect() {
        CompletableFuture<UaStackClient> future = new CompletableFuture<>();

        channelManager.connect().whenComplete((ch, ex) -> {
            if (ch != null) future.complete(this);
            else future.completeExceptionally(ex);
        });

        return future;
    }

    @Override
    public CompletableFuture<UaStackClient> disconnect() {
        return channelManager.disconnect()
            .whenComplete((u, ex) -> pending.forEach((h, cf) ->
                cf.completeExceptionally(
                    new UaException(StatusCodes.Bad_Disconnect, "client disconnect")))
            )
            .thenApply(v -> UaTcpStackClient.this);
    }

    /**
     * Build a new {@link RequestHeader} using a null authentication token and a custom {@code requestTimeout}.
     *
     * @param requestTimeout the custom request timeout to use.
     * @return a new {@link RequestHeader} with a null authentication token and a custom request timeout.
     */
    public RequestHeader newRequestHeader(UInteger requestTimeout) {
        return newRequestHeader(NodeId.NULL_VALUE, requestTimeout);
    }

    /**
     * Build a new {@link RequestHeader} using {@code authToken} and a custom {@code requestTimeout}.
     *
     * @param authToken      the authentication token (from the session) to use.
     * @param requestTimeout the custom request timeout to use.
     * @return a new {@link RequestHeader}.
     */
    public RequestHeader newRequestHeader(NodeId authToken, UInteger requestTimeout) {
        return new RequestHeader(
            authToken,
            DateTime.now(),
            uint(requestHandles.getAndIncrement()),
            uint(0),
            null,
            requestTimeout,
            null
        );
    }

    /**
     * @return the next {@link UInteger} to use as a request handle.
     */
    public UInteger nextRequestHandle() {
        return uint(requestHandles.getAndIncrement());
    }

    public <T extends UaResponseMessage> CompletableFuture<T> sendRequest(UaRequestMessage request) {
        return channelManager.getChannel()
            .thenCompose(sc -> sendRequest(request, sc, true));
    }

    @SuppressWarnings("unchecked")
    private <T extends UaResponseMessage> CompletionStage<T> sendRequest(
        UaRequestMessage request,
        ClientSecureChannel sc,
        boolean firstAttempt) {

        Channel channel = sc.getChannel();

        CompletableFuture<T> future = new CompletableFuture<>();
        UaRequestFuture requestFuture = new UaRequestFuture(request);

        RequestHeader requestHeader = request.getRequestHeader();

        pending.put(requestHeader.getRequestHandle(), (CompletableFuture<UaResponseMessage>) future);

        scheduleRequestTimeout(requestHeader);

        requestFuture.getFuture().whenComplete((r, x) -> {
            if (r != null) {
                receiveResponse(r);
            } else {
                UInteger requestHandle = request.getRequestHeader().getRequestHandle();

                pending.remove(requestHandle);
                future.completeExceptionally(x);
            }
        });

        channel.writeAndFlush(requestFuture).addListener(f -> {
            if (!f.isSuccess()) {
                Throwable cause = f.cause();

                if (cause instanceof ClosedChannelException && firstAttempt) {
                    logger.debug("Channel closed; retrying...");

                    Stack.sharedScheduledExecutor().schedule(
                        () -> config.getExecutor().execute(() -> {
                            CompletableFuture<UaResponseMessage> sendAgain = channelManager
                                .getChannel()
                                .thenCompose(ch -> sendRequest(request, ch, false));

                            sendAgain.whenComplete((r, ex) -> {
                                if (r != null) {
                                    future.complete((T) r);
                                } else {
                                    future.completeExceptionally(ex);
                                }
                            });
                        }),
                        1,
                        TimeUnit.SECONDS
                    );
                } else {
                    UInteger requestHandle = request.getRequestHeader().getRequestHandle();

                    pending.remove(requestHandle);
                    future.completeExceptionally(f.cause());

                    logger.debug("Write failed, requestHandle={}", requestHandle, cause);
                }
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace(
                        "writeAndFlush succeeded for request={}, requestHandle={}",
                        request.getClass().getSimpleName(), requestHeader.getRequestHandle());
                }
            }
        });

        return future;
    }

    public void sendRequests(List<? extends UaRequestMessage> requests,
                             List<CompletableFuture<? extends UaResponseMessage>> futures) {

        Preconditions.checkArgument(requests.size() == futures.size(),
            "requests and futures parameters must be same size");

        channelManager.getChannel().whenComplete((sc, ex) -> {
            if (sc != null) {
                sendRequests(requests, futures, sc);
            } else {
                futures.forEach(f -> f.completeExceptionally(ex));
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void sendRequests(List<? extends UaRequestMessage> requests,
                              List<CompletableFuture<? extends UaResponseMessage>> futures,
                              ClientSecureChannel sc) {

        Channel channel = sc.getChannel();
        Iterator<? extends UaRequestMessage> requestIterator = requests.iterator();
        Iterator<CompletableFuture<? extends UaResponseMessage>> futureIterator = futures.iterator();

        List<UaRequestFuture> pendingRequests = new ArrayList<>(requests.size());

        while (requestIterator.hasNext() && futureIterator.hasNext()) {
            UaRequestMessage request = requestIterator.next();
            CompletableFuture<UaResponseMessage> future =
                (CompletableFuture<UaResponseMessage>) futureIterator.next();

            UaRequestFuture pendingRequest = new UaRequestFuture(request, future);
            pendingRequests.add(pendingRequest);

            RequestHeader requestHeader = request.getRequestHeader();

            pending.put(requestHeader.getRequestHandle(), future);

            scheduleRequestTimeout(requestHeader);

            pendingRequest.getFuture().thenAccept(this::receiveResponse);
        }

        channel.eventLoop().execute(() -> {
            for (UaRequestFuture pendingRequest : pendingRequests) {
                channel.write(pendingRequest).addListener(f -> {
                    if (!f.isSuccess()) {
                        UInteger requestHandle = pendingRequest
                            .getRequest().getRequestHeader().getRequestHandle();

                        CompletableFuture<?> future = pending.remove(requestHandle);
                        if (future != null) future.completeExceptionally(f.cause());

                        logger.debug("Write failed, requestHandle={}", requestHandle, f.cause());
                    }
                });
            }

            channel.flush();
        });
    }

    public CompletableFuture<ClientSecureChannel> getChannelFuture() {
        return channelManager.getChannel();
    }

    private void scheduleRequestTimeout(RequestHeader requestHeader) {
        UInteger requestHandle = requestHeader.getRequestHandle();

        long timeoutHint = requestHeader.getTimeoutHint() != null ?
            requestHeader.getTimeoutHint().longValue() : DEFAULT_TIMEOUT_MS;

        Timeout timeout = wheelTimer.newTimeout(t -> {
            if (timeouts.remove(requestHandle) != null && !t.isCancelled()) {
                CompletableFuture<UaResponseMessage> f = pending.remove(requestHandle);
                if (f != null) {
                    String message = "request timed out after " + timeoutHint + "ms";
                    f.completeExceptionally(new UaException(StatusCodes.Bad_Timeout, message));
                }
            }
        }, timeoutHint, TimeUnit.MILLISECONDS);

        timeouts.put(requestHandle, timeout);
    }

    private void receiveResponse(UaResponseMessage response) {
        deliveryQueue.submit(() -> {
            ResponseHeader header = response.getResponseHeader();
            UInteger requestHandle = header.getRequestHandle();

            CompletableFuture<UaResponseMessage> future = pending.remove(requestHandle);

            if (future != null) {
                if (header.getServiceResult().isGood()) {
                    future.complete(response);
                } else {
                    ServiceFault serviceFault;

                    if (response instanceof ServiceFault) {
                        serviceFault = (ServiceFault) response;
                    } else {
                        serviceFault = new ServiceFault(header);
                    }

                    if (logger.isDebugEnabled()) {
                        logger.debug("Received ServiceFault requestHandle={}, result={}",
                            requestHandle, serviceFault.getResponseHeader().getServiceResult());
                    }

                    future.completeExceptionally(new UaServiceFaultException(serviceFault));
                }

                Timeout timeout = timeouts.remove(requestHandle);
                if (timeout != null) timeout.cancel();
            } else {
                logger.warn("Received unmatched {} with requestHandle={}, timestamp={}",
                    response.getClass().getSimpleName(), requestHandle, response.getResponseHeader().getTimestamp());
            }
        });
    }

    @Override
    public Optional<X509Certificate> getCertificate() {
        return config.getCertificate();
    }

    @Override
    public Optional<KeyPair> getKeyPair() {
        return config.getKeyPair();
    }

    @Override
    public ChannelConfig getChannelConfig() {
        return config.getChannelConfig();
    }

    @Override
    public UInteger getChannelLifetime() {
        return config.getChannelLifetime();
    }

    @Override
    public ApplicationDescription getApplication() {
        return application;
    }

    @Override
    public Optional<EndpointDescription> getEndpoint() {
        return config.getEndpoint();
    }

    @Override
    public String getEndpointUrl() {
        return config.getEndpoint()
            .map(EndpointDescription::getEndpointUrl)
            .orElse(config.getEndpointUrl().orElse(""));
    }

    @Override
    public ExecutorService getExecutorService() {
        return config.getExecutor();
    }

    public static CompletableFuture<ClientSecureChannel> bootstrap(UaTcpStackClient client) {

        CompletableFuture<ClientSecureChannel> handshake = new CompletableFuture<>();

        ChannelInitializer<SocketChannel> initializer = new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel channel) throws Exception {
                UaTcpStackClientConfig config = client.getConfig();

                ClientSecureChannel secureChannel;

                EndpointDescription endpoint = config.getEndpoint().orElseGet(() -> {
                    String endpointUrl = config.getEndpointUrl().orElseThrow(() ->
                        new UaRuntimeException(
                            StatusCodes.Bad_ConfigurationError,
                            "no endpoint or endpoint URL configured")
                    );
                    return new EndpointDescription(
                        endpointUrl,
                        null, null,
                        MessageSecurityMode.None,
                        SecurityPolicy.None.getSecurityPolicyUri(),
                        null, null, null
                    );
                });

                SecurityPolicy securityPolicy = SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri());

                if (securityPolicy == SecurityPolicy.None) {
                    secureChannel = new ClientSecureChannel(
                        securityPolicy,
                        endpoint.getSecurityMode()
                    );
                } else {
                    KeyPair keyPair = config.getKeyPair().orElseThrow(() ->
                        new UaException(
                            StatusCodes.Bad_ConfigurationError,
                            "no KeyPair configured")
                    );

                    X509Certificate certificate = config.getCertificate().orElseThrow(() ->
                        new UaException(
                            StatusCodes.Bad_ConfigurationError,
                            "no certificate configured")
                    );

                    List<X509Certificate> certificateChain = Arrays.asList(
                        config.getCertificateChain().orElseThrow(() ->
                            new UaException(
                                StatusCodes.Bad_ConfigurationError,
                                "no certificate chain configured"))
                    );

                    X509Certificate remoteCertificate = CertificateUtil
                        .decodeCertificate(endpoint.getServerCertificate().bytes());

                    List<X509Certificate> remoteCertificateChain = CertificateUtil
                        .decodeCertificates(endpoint.getServerCertificate().bytes());

                    secureChannel = new ClientSecureChannel(
                        keyPair,
                        certificate,
                        certificateChain,
                        remoteCertificate,
                        remoteCertificateChain,
                        securityPolicy,
                        endpoint.getSecurityMode()
                    );
                }

                UaTcpClientAcknowledgeHandler acknowledgeHandler =
                    new UaTcpClientAcknowledgeHandler(client, secureChannel, handshake);

                channel.pipeline().addLast(acknowledgeHandler);
            }
        };

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(client.getConfig().getEventLoop())
            .channel(NioSocketChannel.class)
            .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(initializer);

        try {
            URI uri = new URI(client.getEndpointUrl()).parseServerAuthority();

            bootstrap.connect(uri.getHost(), uri.getPort()).addListener((ChannelFuture f) -> {
                if (!f.isSuccess()) {
                    Throwable cause = f.cause();

                    if (cause instanceof ConnectTimeoutException) {
                        handshake.completeExceptionally(
                            new UaException(StatusCodes.Bad_Timeout, f.cause()));
                    } else if (cause instanceof ConnectException) {
                        handshake.completeExceptionally(
                            new UaException(StatusCodes.Bad_ConnectionRejected, f.cause()));
                    } else {
                        handshake.completeExceptionally(cause);
                    }
                }
            });
        } catch (Throwable e) {
            UaException failure = new UaException(
                StatusCodes.Bad_TcpEndpointUrlInvalid, e);

            handshake.completeExceptionally(failure);
        }

        return handshake;
    }

    /**
     * Query the FindServers service at the given endpoint URL.
     * <p>
     * The endpoint URL(s) for each server {@link ApplicationDescription} returned can then be used in a
     * {@link #getEndpoints(String)} call to discover the endpoints for that server.
     *
     * @param endpointUrl the endpoint URL to find servers at.
     * @return the {@link ApplicationDescription}s returned by the FindServers service.
     */
    public static CompletableFuture<ApplicationDescription[]> findServers(String endpointUrl) {
        UaTcpStackClientConfig config = UaTcpStackClientConfig.builder()
            .setEndpointUrl(endpointUrl)
            .build();

        UaTcpStackClient client = new UaTcpStackClient(config);

        FindServersRequest request = new FindServersRequest(
            new RequestHeader(null, DateTime.now(), uint(1), uint(0), null, uint(5000), null),
            endpointUrl, null, null);
        
        return client.connect().thenCompose(c ->
            c.<FindServersResponse>sendRequest(request)
                .whenComplete((r, ex) -> client.disconnect())
                .thenApply(FindServersResponse::getServers)
        );
    }

    /**
     * Query the GetEndpoints service at the given endpoint URL.
     *
     * @param endpointUrl the endpoint URL to get endpoints from.
     * @return the {@link EndpointDescription}s returned by the GetEndpoints service.
     */
    public static CompletableFuture<EndpointDescription[]> getEndpoints(String endpointUrl) {
        UaTcpStackClientConfig config = UaTcpStackClientConfig.builder()
            .setEndpointUrl(endpointUrl)
            .build();

        UaTcpStackClient client = new UaTcpStackClient(config);

        GetEndpointsRequest request = new GetEndpointsRequest(
            new RequestHeader(null, DateTime.now(), uint(1), uint(0), null, uint(5000), null),
            endpointUrl, null, new String[]{Stack.UA_TCP_BINARY_TRANSPORT_URI});

        return client.connect().thenCompose(c ->
            c.<GetEndpointsResponse>sendRequest(request)
                .whenComplete((r, ex) -> client.disconnect())
                .thenApply(GetEndpointsResponse::getEndpoints)
        );
    }

}
