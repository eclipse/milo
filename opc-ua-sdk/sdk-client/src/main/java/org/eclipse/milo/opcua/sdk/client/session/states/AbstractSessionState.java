/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.session.states;

import java.nio.ByteBuffer;
import java.security.PrivateKey;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.Function;
import java.util.stream.Stream;

import com.codepoetics.protonpack.StreamUtils;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Bytes;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.OpcUaSession;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.sdk.client.session.Fsm;
import org.eclipse.milo.opcua.sdk.client.session.SessionFsm;
import org.eclipse.milo.opcua.sdk.client.session.events.ActivateSessionFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ActivateSessionSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CloseSessionSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.CreateSessionSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.InitializeFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.InitializeSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ReactivateFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.ReactivateSuccessEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.TransferFailureEvent;
import org.eclipse.milo.opcua.sdk.client.session.events.TransferSuccessEvent;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
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
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.NonceUtil;
import org.eclipse.milo.opcua.stack.core.util.SignatureUtil;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.jooq.lambda.tuple.Tuple2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.FutureUtils.failedFuture;

abstract class AbstractSessionState implements SessionState {

    /**
     * Max amount of time to wait for one of the requests send by the session FSM to complete.
     * Using the default 120,000ms may result in the appearance of the FSM having deadlocked or stopped working.
     */
    private static final UInteger REQUEST_TIMEOUT = uint(16000);

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionFsm.class);

    // <editor-fold desc="Create Session">
    static void createSessionAsync(Fsm fsm, CompletableFuture<OpcUaSession> sessionFuture) {
        fsm.getClient().getConfig().getExecutor().execute(() -> createSession(fsm, sessionFuture));
    }

    private static void createSession(Fsm fsm, CompletableFuture<OpcUaSession> sessionFuture) {
        OpcUaClient client = fsm.getClient();
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
            client.newRequestHeader(REQUEST_TIMEOUT),
            stackClient.getApplication(),
            serverUri,
            stackClient.getEndpointUrl(),
            client.getConfig().getSessionName().get(),
            clientNonce,
            clientCertificate,
            client.getConfig().getSessionTimeout().doubleValue(),
            client.getConfig().getMaxResponseMessageSize()
        );

        LOGGER.debug("Sending CreateSessionRequest...");

        stackClient.<CreateSessionResponse>sendRequest(request).whenCompleteAsync((response, ex) -> {
            if (response != null) {
                LOGGER.debug("CreateSession succeeded: {}", response.getSessionId());

                try {
                    EndpointDescription endpoint = stackClient.getEndpoint().orElseThrow(
                        () -> new UaException(StatusCodes.Bad_InternalError,
                            "cannot create session with no endpoint configured")
                    );

                    SecurityPolicy securityPolicy = SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri());

                    if (securityPolicy != SecurityPolicy.None) {
                        X509Certificate certificateFromResponse = CertificateUtil
                            .decodeCertificate(response.getServerCertificate().bytesOrEmpty());

                        X509Certificate certificateFromEndpoint = CertificateUtil
                            .decodeCertificate(endpoint.getServerCertificate().bytesOrEmpty());

                        if (!certificateFromResponse.equals(certificateFromEndpoint)) {
                            throw new UaException(
                                StatusCodes.Bad_SecurityChecksFailed,
                                "Certificate from CreateSessionResponse did not " +
                                    "match certificate from EndpointDescription!"
                            );
                        }

                        SignatureData serverSignature = response.getServerSignature();

                        byte[] dataBytes = Bytes.concat(
                            clientCertificate.bytesOrEmpty(),
                            clientNonce.bytesOrEmpty()
                        );

                        byte[] signatureBytes = serverSignature.getSignature().bytesOrEmpty();

                        SignatureUtil.verify(
                            SecurityAlgorithm.fromUri(serverSignature.getAlgorithm()),
                            certificateFromResponse,
                            dataBytes,
                            signatureBytes
                        );
                    }

                    fsm.fireEvent(new CreateSessionSuccessEvent(response, sessionFuture));
                } catch (UaException e) {
                    LOGGER.debug("CreateSession failed: {}", e.getMessage(), e);

                    fsm.fireEvent(new CreateSessionFailureEvent(e, sessionFuture));
                }
            } else {
                LOGGER.debug("CreateSession failed: {}", ex.getMessage(), ex);

                fsm.fireEvent(new CreateSessionFailureEvent(ex, sessionFuture));
            }
        }, stackClient.getExecutorService());
    }
    // </editor-fold>

    // <editor-fold desc="Activate Session">
    static void activateSessionAsync(
        Fsm fsm,
        CreateSessionResponse csr,
        CompletableFuture<OpcUaSession> sessionFuture) {

        fsm.getClient().getConfig().getExecutor().execute(() -> activateSession(fsm, csr, sessionFuture));
    }

    private static void activateSession(
        Fsm fsm,
        CreateSessionResponse csr,
        CompletableFuture<OpcUaSession> sessionFuture) {

        OpcUaClient client = fsm.getClient();
        UaTcpStackClient stackClient = client.getStackClient();

        CompletableFuture<ClientSecureChannel> cf = stackClient.getChannelFuture();

        CompletableFuture<ActivateSessionResponse> af = cf.thenCompose(secureChannel -> {
            try {
                EndpointDescription endpoint = stackClient.getEndpoint().orElseThrow(
                    () -> new UaException(StatusCodes.Bad_InternalError,
                        "cannot create session with no endpoint configured")
                );

                ByteString serverNonce = csr.getServerNonce();

                String[] localeIds = client.getConfig().getSessionLocaleIds();

                Tuple2<UserIdentityToken, SignatureData> tuple =
                    client.getConfig().getIdentityProvider()
                        .getIdentityToken(endpoint, serverNonce);

                UserIdentityToken userIdentityToken = tuple.v1();
                SignatureData userTokenSignature = tuple.v2();

                ActivateSessionRequest request = new ActivateSessionRequest(
                    client.newRequestHeader(csr.getAuthenticationToken(), REQUEST_TIMEOUT),
                    buildClientSignature(secureChannel, serverNonce),
                    new SignedSoftwareCertificate[0],
                    localeIds,
                    ExtensionObject.encode(userIdentityToken),
                    userTokenSignature
                );

                LOGGER.debug(
                    "Sending ActivateSessionRequest, secureChannelId={}, channel={}...",
                    secureChannel.getChannelId(), secureChannel.getChannel());

                return stackClient.sendRequest(request);
            } catch (Exception e) {
                return failedFuture(e);
            }
        });

        af.whenCompleteAsync((asr, ex) -> {
            if (asr != null) {
                OpcUaSession session = new OpcUaSession(
                    csr.getAuthenticationToken(),
                    csr.getSessionId(),
                    client.getConfig().getSessionName().get(),
                    csr.getRevisedSessionTimeout(),
                    csr.getMaxRequestMessageSize(),
                    csr.getServerCertificate(),
                    csr.getServerSoftwareCertificates()
                );

                LOGGER.debug("Session activated: {}", session);

                session.setServerNonce(asr.getServerNonce());

                fsm.fireEvent(new ActivateSessionSuccessEvent(session, sessionFuture));
            } else {
                fsm.fireEvent(new ActivateSessionFailureEvent(ex, sessionFuture));
            }
        }, stackClient.getExecutorService());
    }
    // </editor-fold>

    // <editor-fold desc="Close Session">
    static void closeSessionAsync(
        Fsm fsm,
        OpcUaSession session,
        CompletableFuture<Unit> closeFuture,
        CompletableFuture<OpcUaSession> sessionFuture) {

        fsm.getClient().getConfig().getExecutor().execute(() ->
            closeSession(fsm, session.getAuthenticationToken(), session.getSessionId(), closeFuture, sessionFuture)
        );
    }

    static void closeSessionAsync(
        Fsm fsm,
        NodeId authToken,
        NodeId sessionId,
        CompletableFuture<Unit> closeFuture,
        CompletableFuture<OpcUaSession> sessionFuture) {

        fsm.getClient().getConfig().getExecutor().execute(() ->
            closeSession(fsm, authToken, sessionId, closeFuture, sessionFuture)
        );
    }

    private static void closeSession(
        Fsm fsm,
        NodeId authToken,
        NodeId sessionId,
        CompletableFuture<Unit> closeFuture,
        CompletableFuture<OpcUaSession> sessionFuture) {

        OpcUaClient client = fsm.getClient();
        UaTcpStackClient stackClient = client.getStackClient();

        RequestHeader requestHeader = new RequestHeader(
            authToken,
            DateTime.now(),
            client.nextRequestHandle(),
            uint(0),
            null,
            uint(5000),
            null
        );

        CloseSessionRequest request = new CloseSessionRequest(requestHeader, true);

        LOGGER.debug("Sending CloseSessionRequest...");

        stackClient.<CloseSessionResponse>sendRequest(request).whenCompleteAsync((csr, ex2) -> {
            if (ex2 != null) {
                LOGGER.debug("CloseSession failed: {}", ex2.getMessage(), ex2);
            } else {
                LOGGER.debug("Session closed: {}", sessionId);
            }

            fsm.fireEvent(new CloseSessionSuccessEvent(closeFuture, sessionFuture));
        }, stackClient.getConfig().getExecutor());
    }
    // </editor-fold>

    // <editor-fold desc="Reactivate Session">
    static void reactivateSessionAsync(
        Fsm fsm,
        OpcUaSession session,
        CompletableFuture<OpcUaSession> sessionFuture) {

        fsm.getClient().getConfig().getExecutor().execute(() -> reactivateSession(fsm, session, sessionFuture));
    }

    private static void reactivateSession(
        Fsm fsm,
        OpcUaSession session,
        CompletableFuture<OpcUaSession> sessionFuture) {

        OpcUaClient client = fsm.getClient();
        UaTcpStackClient stackClient = client.getStackClient();

        Function<ClientSecureChannel, CompletionStage<ActivateSessionResponse>> activate = secureChannel -> {
            try {
                EndpointDescription endpoint = stackClient.getEndpoint().orElseThrow(
                    () -> new Exception("cannot create session with no endpoint configured"));

                Tuple2<UserIdentityToken, SignatureData> tuple =
                    client.getConfig().getIdentityProvider()
                        .getIdentityToken(endpoint, session.getServerNonce());

                UserIdentityToken userIdentityToken = tuple.v1();
                SignatureData userTokenSignature = tuple.v2();

                SignatureData clientSignature = buildClientSignature(
                    secureChannel,
                    session.getServerNonce()
                );

                ActivateSessionRequest request = new ActivateSessionRequest(
                    client.newRequestHeader(session.getAuthenticationToken(), REQUEST_TIMEOUT),
                    clientSignature,
                    new SignedSoftwareCertificate[0],
                    new String[0],
                    ExtensionObject.encode(userIdentityToken),
                    userTokenSignature
                );

                LOGGER.debug(
                    "Sending (re)ActivateSessionRequest, secureChannelId={}, channel={}...",
                    secureChannel.getChannelId(), secureChannel.getChannel());

                return stackClient.sendRequest(request);
            } catch (Exception e) {
                return failedFuture(e);
            }
        };

        stackClient.getChannelFuture().thenCompose(activate).whenCompleteAsync((asr, ex) -> {
            if (asr != null) {
                LOGGER.debug("Session reactivated: {}", session);

                session.setServerNonce(asr.getServerNonce());

                fsm.fireEvent(new ReactivateSuccessEvent(session, sessionFuture));
            } else {
                LOGGER.debug("(re)ActivateSession failed: {}", session, ex);

                fsm.fireEvent(new ReactivateFailureEvent(ex, session, sessionFuture));
            }
        }, stackClient.getExecutorService());
    }
    // </editor-fold>

    // <editor-fold desc="Transfer Subscription">
    static void transferSubscriptionsAsync(
        Fsm fsm,
        OpcUaSession session,
        CompletableFuture<OpcUaSession> sessionFuture) {

        fsm.getClient().getConfig().getExecutor().execute(
            () -> transferSubscriptions(fsm, session, sessionFuture));
    }

    private static void transferSubscriptions(
        Fsm fsm,
        OpcUaSession session,
        CompletableFuture<OpcUaSession> sessionFuture) {

        OpcUaClient client = fsm.getClient();
        UaTcpStackClient stackClient = client.getStackClient();
        OpcUaSubscriptionManager subscriptionManager = client.getSubscriptionManager();
        ImmutableList<UaSubscription> subscriptions = subscriptionManager.getSubscriptions();

        if (subscriptions.isEmpty()) {
            fsm.fireEvent(new TransferSuccessEvent(session, sessionFuture));
            return;
        }

        UInteger[] subscriptionIdsArray = subscriptions.stream()
            .map(UaSubscription::getSubscriptionId)
            .toArray(UInteger[]::new);

        TransferSubscriptionsRequest request = new TransferSubscriptionsRequest(
            client.newRequestHeader(session.getAuthenticationToken(), REQUEST_TIMEOUT),
            subscriptionIdsArray,
            true
        );

        LOGGER.debug("Sending TransferSubscriptionsRequest...");

        stackClient.<TransferSubscriptionsResponse>sendRequest(request).whenCompleteAsync((tsr, ex) -> {
            if (tsr != null) {
                List<TransferResult> results = l(tsr.getResults());

                client.getConfig().getExecutor().execute(() -> {
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
                });

                if (LOGGER.isDebugEnabled()) {
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

                    LOGGER.debug("TransferSubscriptions results: {}", Arrays.toString(ss));
                }

                fsm.fireEvent(new TransferSuccessEvent(session, sessionFuture));
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

                    LOGGER.debug("TransferSubscriptions not supported: {}", statusCode);

                    client.getConfig().getExecutor().execute(() -> {
                        // transferFailed() will remove the subscription, but that is okay
                        // because the list from getSubscriptions() above is a copy.
                        for (UaSubscription subscription : subscriptions) {
                            subscriptionManager.transferFailed(
                                subscription.getSubscriptionId(), statusCode);
                        }
                    });

                    fsm.fireEvent(new TransferSuccessEvent(session, sessionFuture));
                } else {
                    LOGGER.debug("TransferSubscriptions failed: {}", statusCode);

                    fsm.fireEvent(new TransferFailureEvent(ex, session, sessionFuture));
                }
            }
        }, stackClient.getExecutorService());
    }
    // </editor-fold>

    // <editor-fold desc="Initialize Session">
    static void initializeSessionAsync(
        Fsm fsm,
        OpcUaSession session,
        CompletableFuture<OpcUaSession> sessionFuture) {

        fsm.getClient().getConfig().getExecutor().execute(
            () -> initializeSession(fsm, session, sessionFuture));
    }

    private static void initializeSession(
        Fsm fsm,
        OpcUaSession session,
        CompletableFuture<OpcUaSession> sessionFuture) {

        List<SessionFsm.SessionInitializer> initializers = fsm.getInitializers();

        if (initializers.isEmpty()) {
            fsm.fireEvent(new InitializeSuccessEvent(session, sessionFuture));
        } else {
            UaTcpStackClient stackClient = fsm.getClient().getStackClient();

            CompletableFuture[] futures = initializers.stream()
                .map(i -> i.initialize(stackClient, session))
                .toArray(CompletableFuture[]::new);

            CompletableFuture.allOf(futures).whenCompleteAsync((v, ex) -> {
                if (ex != null) {
                    LOGGER.warn("Initialization failed: {}", session, ex);
                    fsm.fireEvent(new InitializeFailureEvent(ex, session, sessionFuture));
                } else {
                    LOGGER.debug("Initialization succeeded: {}", session);
                    fsm.fireEvent(new InitializeSuccessEvent(session, sessionFuture));
                }
            }, stackClient.getExecutorService());
        }
    }
    // </editor-fold>

    private static SignatureData buildClientSignature(
        ClientSecureChannel secureChannel, ByteString serverNonce) throws Exception {

        if (secureChannel.getSecurityPolicy() == SecurityPolicy.None) {
            return new SignatureData();
        } else {
            SecurityAlgorithm signatureAlgorithm = secureChannel.getSecurityPolicy().getAsymmetricSignatureAlgorithm();
            PrivateKey privateKey = secureChannel.getKeyPair().getPrivate();
            ByteString serverCertificate = secureChannel.getRemoteCertificateBytes();

            // Signature data is serverCert + serverNonce signed with our private key.
            byte[] serverNonceBytes = serverNonce.bytesOrEmpty();
            byte[] serverCertificateBytes = serverCertificate.bytesOrEmpty();
            byte[] dataToSign = Bytes.concat(serverCertificateBytes, serverNonceBytes);

            byte[] signature = SignatureUtil.sign(
                signatureAlgorithm,
                privateKey,
                ByteBuffer.wrap(dataToSign)
            );

            return new SignatureData(signatureAlgorithm.getUri(), ByteString.of(signature));
        }
    }

}
