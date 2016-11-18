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

package org.eclipse.milo.opcua.sdk.client;

import java.nio.ByteBuffer;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableList;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaSubscriptionManager;
import org.eclipse.milo.opcua.stack.client.UaTcpStackClient;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.ClientSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferResult;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.util.NonceUtil;
import org.eclipse.milo.opcua.stack.core.util.SignatureUtil;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.jooq.lambda.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newCopyOnWriteArrayList;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

class ClientSessionManager {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<SessionActivityListener> listeners = newCopyOnWriteArrayList();

    private final AtomicReference<State> state = new AtomicReference<>(new Inactive());

    private final OpcUaClient client;

    ClientSessionManager(OpcUaClient client) {
        this.client = client;

        Predicate<StatusCode> sessionError = statusCode -> {
            long status = statusCode.getValue();

            return status == StatusCodes.Bad_SessionClosed ||
                status == StatusCodes.Bad_SessionIdInvalid ||
                status == StatusCodes.Bad_SessionNotActivated;
        };

        Predicate<StatusCode> secureChannelError = statusCode -> {
            long status = statusCode.getValue();

            return status == StatusCodes.Bad_SecureChannelIdInvalid ||
                status == StatusCodes.Bad_SecurityChecksFailed ||
                status == StatusCodes.Bad_TcpSecureChannelUnknown;
        };

        client.addFaultListener(serviceFault -> {
            StatusCode serviceResult = serviceFault.getResponseHeader().getServiceResult();

            if (sessionError.or(secureChannelError).test(serviceResult)) {

                logger.debug("ServiceFault: {}", serviceResult);

                State currentState = state.get();

                if (currentState instanceof Active) {
                    Creating creating = new Creating();

                    if (state.compareAndSet(currentState, creating)) {
                        OpcUaSession session = ((Active) currentState).session;

                        notifySessionInactive(session);

                        client.getStackClient().disconnect()
                            .whenCompleteAsync((v, ex) -> createSession(creating));
                    }
                }
            }
        });
    }

    void addListener(SessionActivityListener listener) {
        listeners.add(listener);
    }

    void removeListener(SessionActivityListener listener) {
        listeners.remove(listener);
    }

    CompletableFuture<OpcUaSession> getSession() {
        State currentState = state.get();

        logger.trace("getSession(), currentState={}",
            currentState.getClass().getSimpleName());

        if (currentState instanceof Inactive) {
            Creating creatingState = new Creating();

            if (state.compareAndSet(currentState, creatingState)) {
                CompletableFuture<OpcUaSession> sessionFuture = creatingState.sessionFuture;

                createSession(creatingState);

                return sessionFuture;
            } else {
                return getSession();
            }
        } else if (currentState instanceof Creating) {
            return ((Creating) currentState).sessionFuture;
        } else if (currentState instanceof Activating) {
            return ((Activating) currentState).sessionFuture;
        } else if (currentState instanceof Transferring) {
            return ((Transferring) currentState).sessionFuture;
        } else if (currentState instanceof Active) {
            return ((Active) currentState).sessionFuture;
        } else if (currentState instanceof Reactivating) {
            return ((Reactivating) currentState).sessionFuture;
        } else if (currentState instanceof Closing) {
            CompletableFuture<OpcUaSession> future = new CompletableFuture<>();

            ((Closing) currentState).closeFuture.whenCompleteAsync((oldSession, ex) ->
                getSession().whenComplete((session, ex2) -> {
                    if (session != null) future.complete(session);
                    else future.completeExceptionally(ex2);
                })
            );

            return future;
        } else {
            throw new IllegalStateException("unexpected state: " + currentState.getClass());
        }
    }

    CompletableFuture<Unit> closeSession() {
        State currentState = state.get();

        logger.trace("closeSession(), currentState={}",
            currentState.getClass().getSimpleName());

        if (currentState instanceof Inactive) {
            return CompletableFuture.completedFuture(Unit.VALUE);
        } else if (currentState instanceof Closing) {
            return ((Closing) currentState).closeFuture
                .thenApply(s -> Unit.VALUE)
                .exceptionally(ex -> Unit.VALUE);
        } else if (currentState instanceof Creating) {
            Closing closingState = new Closing();

            if (state.compareAndSet(currentState, closingState)) {
                closeSession(closingState, ((Creating) currentState).sessionFuture);

                return closingState.closeFuture
                    .thenApply(s -> Unit.VALUE)
                    .exceptionally(ex -> Unit.VALUE);
            } else {
                return closeSession();
            }
        } else if (currentState instanceof Activating) {
            Closing closingState = new Closing();

            if (state.compareAndSet(currentState, closingState)) {
                closeSession(closingState, ((Activating) currentState).sessionFuture);

                return closingState.closeFuture
                    .thenApply(s -> Unit.VALUE)
                    .exceptionally(ex -> Unit.VALUE);
            } else {
                return closeSession();
            }
        } else if (currentState instanceof Reactivating) {
            Closing closingState = new Closing();

            if (state.compareAndSet(currentState, closingState)) {
                closeSession(closingState, ((Reactivating) currentState).sessionFuture);

                return closingState.closeFuture
                    .thenApply(s -> Unit.VALUE)
                    .exceptionally(ex -> Unit.VALUE);
            } else {
                return closeSession();
            }
        } else if (currentState instanceof Transferring) {
            Closing closingState = new Closing();

            if (state.compareAndSet(currentState, closingState)) {
                closeSession(closingState, ((Transferring) currentState).sessionFuture);

                return closingState.closeFuture
                    .thenApply(s -> Unit.VALUE)
                    .exceptionally(ex -> Unit.VALUE);
            } else {
                return closeSession();
            }
        } else if (currentState instanceof Active) {
            Closing closingState = new Closing();

            if (state.compareAndSet(currentState, closingState)) {
                closeSession(closingState, ((Active) currentState).sessionFuture);

                return closingState.closeFuture
                    .thenApply(s -> Unit.VALUE)
                    .exceptionally(ex -> Unit.VALUE);
            } else {
                return closeSession();
            }
        } else {
            throw new IllegalStateException("unexpected state: " + currentState.getClass());
        }
    }

    private void closeSession(Closing closingState, CompletableFuture<OpcUaSession> sessionFuture) {
        sessionFuture.whenComplete((session, ex) -> {
            if (session != null) {
                UaTcpStackClient stackClient = client.getStackClient();

                RequestHeader requestHeader = new RequestHeader(
                    session.getAuthenticationToken(),
                    DateTime.now(),
                    client.nextRequestHandle(),
                    uint(0),
                    null,
                    uint(5000),
                    null
                );

                CloseSessionRequest request = new CloseSessionRequest(requestHeader, true);

                logger.debug("Sending CloseSessionRequest...");

                stackClient.<CloseSessionResponse>sendRequest(request).whenCompleteAsync((csr, ex2) -> {
                    if (ex2 != null) {
                        logger.debug("CloseSession failed: {}", ex2.getMessage(), ex2);
                    } else {
                        logger.debug("Session closed: {}", session.getSessionId());
                    }

                    state.compareAndSet(closingState, new Inactive());
                    closingState.closeFuture.complete(session);
                });
            } else {
                state.compareAndSet(closingState, new Inactive());
                closingState.closeFuture.completeExceptionally(ex);
            }
        });
    }

    private void notifySessionActive(OpcUaSession session) {
        listeners.forEach(listener -> {
            try {
                listener.onSessionActive(session);
            } catch (Throwable t) {
                logger.warn("Uncaught Throwable notifying listener: {}", listener, t);
            }
        });
    }

    private void notifySessionInactive(OpcUaSession session) {
        listeners.forEach(listener -> {
            try {
                listener.onSessionInactive(session);
            } catch (Throwable t) {
                logger.warn("Uncaught Throwable notifying listener: {}", listener, t);
            }
        });
    }

    private void createSession(Creating creatingState) {
        UaTcpStackClient stackClient = client.getStackClient();

        String serverUri = stackClient.getEndpoint()
            .flatMap(e -> {
                String gatewayServerUri = e.getServer().getGatewayServerUri();
                if (gatewayServerUri != null && !gatewayServerUri.isEmpty()) {
                    return Optional.ofNullable(e.getServer().getApplicationUri());
                } else {
                    return Optional.empty();
                }
            })
            .orElse(null);

        ByteString clientNonce = NonceUtil.generateNonce(32);

        ByteString clientCertificate = stackClient.getConfig().getCertificate()
            .map(c -> {
                try {
                    return ByteString.of(c.getEncoded());
                } catch (CertificateEncodingException e) {
                    return ByteString.NULL_VALUE;
                }
            })
            .orElse(ByteString.NULL_VALUE);

        CreateSessionRequest request = new CreateSessionRequest(
            client.newRequestHeader(),
            stackClient.getApplication(),
            serverUri,
            stackClient.getEndpointUrl(),
            client.getConfig().getSessionName().get(),
            clientNonce,
            clientCertificate,
            client.getConfig().getSessionTimeout().doubleValue(),
            client.getConfig().getMaxResponseMessageSize()
        );

        logger.debug("Sending CreateSessionRequest...");

        stackClient.<CreateSessionResponse>sendRequest(request).whenCompleteAsync((csr, ex) -> {
            CompletableFuture<OpcUaSession> sessionFuture = creatingState.sessionFuture;

            if (csr != null) {
                logger.debug("Session created: {}", csr.getSessionId());

                Activating activatingState = new Activating(sessionFuture);

                if (state.compareAndSet(creatingState, activatingState)) {
                    activateSession(activatingState, csr);
                }
            } else {
                logger.debug("CreateSession failed: {}", ex.getMessage(), ex);

                state.compareAndSet(creatingState, new Inactive());
                sessionFuture.completeExceptionally(ex);
            }
        });
    }

    private void activateSession(Activating activatingState, CreateSessionResponse csr) {
        UaTcpStackClient stackClient = client.getStackClient();

        Function<ClientSecureChannel, CompletableFuture<ActivateSessionResponse>> activate = secureChannel -> {
            try {
                Channel channel = secureChannel.getChannel();

                channel.pipeline().addLast(new InactivityHandler());

                EndpointDescription endpoint = stackClient.getEndpoint()
                    .orElseThrow(() -> new Exception("cannot create session with no endpoint configured"));

                Tuple2<UserIdentityToken, SignatureData> tuple =
                    client.getConfig().getIdentityProvider()
                        .getIdentityToken(endpoint, csr.getServerNonce());

                UserIdentityToken userIdentityToken = tuple.v1();
                SignatureData userTokenSignature = tuple.v2();

                ActivateSessionRequest request = new ActivateSessionRequest(
                    client.newRequestHeader(csr.getAuthenticationToken()),
                    buildClientSignature(secureChannel, csr),
                    new SignedSoftwareCertificate[0],
                    new String[0],
                    ExtensionObject.fromStructure(userIdentityToken),
                    userTokenSignature
                );

                logger.debug(
                    "Sending ActivateSessionRequest, secureChannelId={}, channel={}...",
                    secureChannel.getChannelId(), secureChannel.getChannel());

                return stackClient.sendRequest(request);
            } catch (Exception e) {
                CompletableFuture<ActivateSessionResponse> f = new CompletableFuture<>();
                f.completeExceptionally(e);
                return f;
            }
        };

        stackClient.getChannelFuture().thenCompose(activate).whenCompleteAsync((asr, ex) -> {
            CompletableFuture<OpcUaSession> sessionFuture = activatingState.sessionFuture;

            if (asr != null) {
                logger.debug("Session activated: {}", csr.getSessionId());

                OpcUaSession session = new OpcUaSession(
                    csr.getAuthenticationToken(),
                    csr.getSessionId(),
                    client.getConfig().getSessionName().get(),
                    csr.getRevisedSessionTimeout(),
                    csr.getMaxRequestMessageSize(),
                    csr.getServerCertificate(),
                    csr.getServerSoftwareCertificates()
                );

                session.setServerNonce(asr.getServerNonce());

                OpcUaSubscriptionManager subscriptionManager = client.getSubscriptionManager();
                int subscriptionCount = subscriptionManager.getSubscriptions().size();
                boolean transferNeeded = subscriptionCount > 0;

                logger.debug(
                    "subscriptionCount={}, transferNeeded={}",
                    subscriptionCount, transferNeeded);

                if (transferNeeded) {
                    Transferring transferringState = new Transferring(sessionFuture);

                    if (state.compareAndSet(activatingState, transferringState)) {
                        transferSubscriptions(transferringState, session);
                    }
                } else {
                    state.compareAndSet(activatingState, new Active(session, sessionFuture));
                    sessionFuture.complete(session);
                }
            } else {
                logger.debug("ActivateSession failed: {}", ex.getMessage(), ex);

                state.compareAndSet(activatingState, new Inactive());
                sessionFuture.completeExceptionally(ex);
            }
        });
    }

    private void reactivateSession(Reactivating reactivatingState, OpcUaSession previousSession) {
        UaTcpStackClient stackClient = client.getStackClient();

        Function<ClientSecureChannel, CompletionStage<ActivateSessionResponse>> activate = secureChannel -> {
            try {
                EndpointDescription endpoint = stackClient.getEndpoint()
                    .orElseThrow(() -> new Exception("cannot create session with no endpoint configured"));

                Tuple2<UserIdentityToken, SignatureData> tuple =
                    client.getConfig().getIdentityProvider()
                        .getIdentityToken(endpoint, previousSession.getServerNonce());

                UserIdentityToken userIdentityToken = tuple.v1();
                SignatureData userTokenSignature = tuple.v2();

                SignatureData clientSignature = buildClientSignature(
                    secureChannel,
                    previousSession.getServerCertificate(),
                    previousSession.getServerNonce()
                );

                ActivateSessionRequest request = new ActivateSessionRequest(
                    client.newRequestHeader(previousSession.getAuthenticationToken()),
                    clientSignature,
                    new SignedSoftwareCertificate[0],
                    new String[0],
                    ExtensionObject.fromStructure(userIdentityToken),
                    userTokenSignature
                );

                logger.debug(
                    "Sending (re)ActivateSessionRequest, secureChannelId={}, channel={}...",
                    secureChannel.getChannelId(), secureChannel.getChannel());

                return stackClient.sendRequest(request);
            } catch (Exception e) {
                CompletableFuture<ActivateSessionResponse> f = new CompletableFuture<>();
                f.completeExceptionally(e);
                return f;
            }
        };


        stackClient.getChannelFuture().thenCompose(activate).whenCompleteAsync((asr, ex) -> {
            CompletableFuture<OpcUaSession> sessionFuture = reactivatingState.sessionFuture;

            if (asr != null) {
                logger.debug("Session reactivated: {}", previousSession.getSessionId());

                OpcUaSession newSession = new OpcUaSession(
                    previousSession.getAuthenticationToken(),
                    previousSession.getSessionId(),
                    client.getConfig().getSessionName().get(),
                    previousSession.getSessionTimeout(),
                    previousSession.getMaxRequestSize(),
                    previousSession.getServerCertificate(),
                    previousSession.getServerSoftwareCertificates()
                );

                newSession.setServerNonce(asr.getServerNonce());

                state.compareAndSet(reactivatingState, new Active(newSession, sessionFuture));

                sessionFuture.complete(newSession);
            } else {
                logger.debug("(re)ActivateSession failed: {}", ex.getMessage(), ex);

                StatusCode statusCode = UaException.extract(ex)
                    .map(UaException::getStatusCode)
                    .orElse(StatusCode.BAD);

                if (statusCode.getValue() == StatusCodes.Bad_SessionClosed ||
                    statusCode.getValue() == StatusCodes.Bad_SessionIdInvalid ||
                    statusCode.getValue() == StatusCodes.Bad_SessionNotActivated ||
                    statusCode.getValue() == StatusCodes.Bad_SecurityChecksFailed) {

                    // A session-related error means the session is no longer valid.
                    // Create a new session re-using the current future.
                    Creating creating = new Creating(sessionFuture);

                    if (state.compareAndSet(reactivatingState, creating)) {
                        createSession(creating);
                    } else {
                        // We're no longer re-activating for whatever reason (asked to close?).
                        // The only way this sessionFuture can be completed is exceptionally.
                        sessionFuture.completeExceptionally(ex);
                    }
                } else {
                    // A non-session-related error, such as not being connected yet.
                    // Fail the current future and try again.
                    Reactivating reactivatingAgain = new Reactivating();

                    if (state.compareAndSet(reactivatingState, reactivatingAgain)) {
                        reactivateSession(reactivatingAgain, previousSession);
                    }

                    sessionFuture.completeExceptionally(ex);
                }
            }
        });
    }

    private void transferSubscriptions(Transferring transferringState, OpcUaSession session) {
        UaTcpStackClient stackClient = client.getStackClient();
        OpcUaSubscriptionManager subscriptionManager = client.getSubscriptionManager();
        ImmutableList<UaSubscription> subscriptions = subscriptionManager.getSubscriptions();

        UInteger[] subscriptionIdsArray = subscriptions.stream()
            .map(UaSubscription::getSubscriptionId)
            .toArray(UInteger[]::new);

        TransferSubscriptionsRequest request = new TransferSubscriptionsRequest(
            client.newRequestHeader(session.getAuthenticationToken()),
            subscriptionIdsArray,
            true
        );

        logger.debug("Sending TransferSubscriptionsRequest...");

        stackClient.<TransferSubscriptionsResponse>sendRequest(request).whenCompleteAsync((tsr, ex) -> {
            CompletableFuture<OpcUaSession> sessionFuture = transferringState.sessionFuture;

            if (tsr != null) {
                List<TransferResult> results = l(tsr.getResults());

                for (int i = 0; i < results.size(); i++) {
                    TransferResult result = results.get(i);

                    if (!result.getStatusCode().isGood()) {
                        UaSubscription subscription = subscriptions.get(i);

                        subscriptionManager.transferFailed(
                            subscription.getSubscriptionId(),
                            result.getStatusCode()
                        );
                    }
                }

                if (logger.isDebugEnabled()) {
                    Stream<UInteger> subscriptionIds = subscriptions.stream()
                        .map(UaSubscription::getSubscriptionId);
                    Stream<StatusCode> statusCodes = results.stream()
                        .map(TransferResult::getStatusCode);

                    String[] ss = StreamUtils.zip(
                        subscriptionIds, statusCodes,
                        (i, s) -> String.format("id=%s/%s",
                            i, StatusCodes.lookup(s.getValue())
                                .map(sa -> sa[0]).orElse(s.toString()))
                    ).toArray(String[]::new);

                    logger.debug("TransferSubscriptions results: {}", Arrays.toString(ss));
                }

                state.compareAndSet(transferringState, new Active(session, sessionFuture));
                sessionFuture.complete(session);
            } else {
                StatusCode statusCode = UaException.extract(ex)
                    .map(UaException::getStatusCode)
                    .orElse(StatusCode.BAD);

                // Bad_ServiceUnsupported is the correct response when transfers aren't supported but
                // server implementations tend to interpret the spec in their own unique way...
                if (statusCode.getValue() == StatusCodes.Bad_NotImplemented ||
                    statusCode.getValue() == StatusCodes.Bad_NotSupported ||
                    statusCode.getValue() == StatusCodes.Bad_OutOfService ||
                    statusCode.getValue() == StatusCodes.Bad_ServiceUnsupported) {

                    logger.debug("TransferSubscriptions not supported: {}", statusCode);

                    // transferFailed() will remove the subscription, but that is okay
                    // because the list from getSubscriptions() above is a copy.
                    for (UaSubscription subscription : subscriptions) {
                        subscriptionManager.transferFailed(
                            subscription.getSubscriptionId(), statusCode);
                    }

                    state.compareAndSet(transferringState, new Active(session, sessionFuture));
                    sessionFuture.complete(session);
                } else {
                    logger.debug("TransferSubscriptions failed: {}", statusCode);

                    Closing closing = new Closing();

                    if (state.compareAndSet(transferringState, closing)) {
                        closeSession(closing, completedFuture(session));

                        closing.closeFuture.whenComplete((v, ex2) ->
                            sessionFuture.completeExceptionally(ex));
                    }
                }
            }
        });
    }

    private SignatureData buildClientSignature(ClientSecureChannel secureChannel, CreateSessionResponse response) {
        ByteString serverCert = response.getServerCertificate() != null ?
            response.getServerCertificate() : ByteString.NULL_VALUE;
        ByteString serverNonce = response.getServerNonce() != null ?
            response.getServerNonce() : ByteString.NULL_VALUE;

        return buildClientSignature(secureChannel, serverCert, serverNonce);
    }

    private SignatureData buildClientSignature(ClientSecureChannel secureChannel,
                                               ByteString serverCertificate,
                                               ByteString serverNonce) {

        byte[] serverNonceBytes = Optional.ofNullable(serverNonce.bytes()).orElse(new byte[0]);
        byte[] serverCertificateBytes = Optional.ofNullable(serverCertificate.bytes()).orElse(new byte[0]);

        // Signature is serverCert + serverNonce signed with our private key.
        byte[] signature = new byte[serverCertificateBytes.length + serverNonceBytes.length];
        System.arraycopy(serverCertificateBytes, 0, signature, 0, serverCertificateBytes.length);
        System.arraycopy(serverNonceBytes, 0, signature, serverCertificateBytes.length, serverNonceBytes.length);

        SecurityAlgorithm signatureAlgorithm = secureChannel.getSecurityPolicy().getAsymmetricSignatureAlgorithm();

        if (secureChannel.getSecurityPolicy() == SecurityPolicy.None) {
            return new SignatureData();
        } else {
            try {
                PrivateKey privateKey = secureChannel.getKeyPair().getPrivate();

                signature = SignatureUtil.sign(
                    signatureAlgorithm,
                    privateKey,
                    ByteBuffer.wrap(signature)
                );
            } catch (Throwable t) {
                logger.warn("Asymmetric signing failed: {}", t.getMessage(), t);
            }

            return new SignatureData(signatureAlgorithm.getUri(), ByteString.of(signature));
        }
    }

    private class InactivityHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            State currentState = state.get();

            if (currentState instanceof Active) {
                Reactivating reactivating = new Reactivating();

                if (state.compareAndSet(currentState, reactivating)) {
                    OpcUaSession session = ((Active) currentState).session;

                    notifySessionInactive(session);

                    reactivateSession(reactivating, session);
                }
            }

            super.channelInactive(ctx);
        }
    }

    private interface State {
    }

    private static class Inactive implements State {
    }

    private class Creating implements State {
        final CompletableFuture<OpcUaSession> sessionFuture;

        Creating() {
            sessionFuture = new CompletableFuture<>();

            sessionFuture.thenAccept(session -> {
                client.getSubscriptionManager().startPublishing();

                notifySessionActive(session);
            });
        }

        Creating(CompletableFuture<OpcUaSession> sessionFuture) {
            this.sessionFuture = sessionFuture;
        }

    }

    private static class Activating implements State {
        final CompletableFuture<OpcUaSession> sessionFuture;

        Activating(CompletableFuture<OpcUaSession> sessionFuture) {
            this.sessionFuture = sessionFuture;
        }
    }

    private static class Transferring implements State {
        final CompletableFuture<OpcUaSession> sessionFuture;

        Transferring(CompletableFuture<OpcUaSession> sessionFuture) {
            this.sessionFuture = sessionFuture;
        }
    }

    private static class Active implements State {
        final OpcUaSession session;
        final CompletableFuture<OpcUaSession> sessionFuture;

        Active(OpcUaSession session, CompletableFuture<OpcUaSession> sessionFuture) {
            this.session = session;
            this.sessionFuture = sessionFuture;
        }
    }

    private class Reactivating implements State {
        final CompletableFuture<OpcUaSession> sessionFuture = new CompletableFuture<>();

        Reactivating() {
            sessionFuture.thenAccept(session -> {
                client.getSubscriptionManager().startPublishing();

                notifySessionActive(session);
            });
        }
    }

    private class Closing implements State {
        final CompletableFuture<OpcUaSession> closeFuture = new CompletableFuture<>();

        Closing() {
            closeFuture.thenAccept(ClientSessionManager.this::notifySessionInactive);
        }
    }

}
