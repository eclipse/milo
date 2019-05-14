/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server;

import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.Bytes;
import org.eclipse.milo.opcua.sdk.server.diagnostics.ServerDiagnosticsSummary;
import org.eclipse.milo.opcua.sdk.server.identity.IdentityValidator;
import org.eclipse.milo.opcua.sdk.server.services.ServiceAttributes;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.CertificateValidationUtil;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.core.util.NonceUtil;
import org.eclipse.milo.opcua.stack.core.util.SignatureUtil;
import org.eclipse.milo.opcua.stack.server.services.AttributeHistoryServiceSet;
import org.eclipse.milo.opcua.stack.server.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.server.services.MethodServiceSet;
import org.eclipse.milo.opcua.stack.server.services.MonitoredItemServiceSet;
import org.eclipse.milo.opcua.stack.server.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.server.services.QueryServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.server.services.SessionServiceSet;
import org.eclipse.milo.opcua.stack.server.services.SubscriptionServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ViewServiceSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newCopyOnWriteArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;
import static org.eclipse.milo.opcua.stack.core.util.DigestUtil.sha1;

public class SessionManager implements
    AttributeServiceSet,
    AttributeHistoryServiceSet,
    MethodServiceSet,
    MonitoredItemServiceSet,
    NodeManagementServiceSet,
    QueryServiceSet,
    SessionServiceSet,
    SubscriptionServiceSet,
    ViewServiceSet {

    private static final int MAX_SESSION_TIMEOUT_MS = 120000;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<NodeId, Session> createdSessions = Maps.newConcurrentMap();
    private final Map<NodeId, Session> activeSessions = Maps.newConcurrentMap();

    private final List<SessionListener> sessionListeners = new CopyOnWriteArrayList<>();

    /**
     * Store the last N client nonces and to make sure they aren't re-used.
     * <p>
     * This number is arbitrary; trying to prevent clients from re-using nonces is merely to satisfy the CTT.
     */
    private final List<ByteString> clientNonces = newCopyOnWriteArrayList();

    private final OpcUaServer server;

    SessionManager(OpcUaServer server) {
        this.server = server;
    }

    /**
     * Kill the session identified by {@code nodeId} and optionally delete all its subscriptions.
     *
     * @param nodeId              the {@link NodeId} identifying the session to kill.
     * @param deleteSubscriptions {@code true} if all its subscriptions should be deleted as well.
     */
    public void killSession(NodeId nodeId, boolean deleteSubscriptions) {
        activeSessions.values().stream()
            .filter(s -> s.getSessionId().equals(nodeId))
            .findFirst().ifPresent(s -> s.close(deleteSubscriptions));
    }

    // TODO diagnostics: javadoc
    public void addSessionListener(SessionListener listener) {
        sessionListeners.add(listener);
    }

    // TODO diagnostics: javadoc
    public void removeSessionListener(SessionListener listener) {
        sessionListeners.remove(listener);
    }

    /**
     * Get the current session count, including session that have been created but not yet activated.
     *
     * @return the current session count, including session that have been created but not yet activated.
     */
    public UInteger getCurrentSessionCount() {
        return uint(createdSessions.size() + activeSessions.size());
    }

    private Session session(ServiceRequest service) throws UaException {
        long secureChannelId = service.getSecureChannelId();
        NodeId authToken = service.getRequest().getRequestHeader().getAuthenticationToken();

        Session session = activeSessions.get(authToken);

        if (session == null) {
            // session is either not activated or doesn't exist
            session = createdSessions.get(authToken);

            if (session != null) {
                session.getSessionDiagnostics().getUnauthorizedRequestCount().increment();
                throw new UaException(StatusCodes.Bad_SessionNotActivated);
            } else {
                throw new UaException(StatusCodes.Bad_SessionIdInvalid);
            }
        } else {
            // session exists and is activated
            if (session.getSecureChannelId() != secureChannelId) {
                session.getSessionDiagnostics().getUnauthorizedRequestCount().increment();
                throw new UaException(StatusCodes.Bad_SecureChannelIdInvalid);
            }

            session.updateLastActivity();

            service.attr(ServiceAttributes.SERVER_KEY).set(server);
            service.attr(ServiceAttributes.SESSION_KEY).set(session);

            return session;
        }
    }

    //region Session Services
    @Override
    public void onCreateSession(ServiceRequest serviceRequest) {
        ServerDiagnosticsSummary serverDiagnosticsSummary = server.getDiagnosticsSummary();

        try {
            CreateSessionResponse response = createSession(serviceRequest);

            serverDiagnosticsSummary.getCumulativeSessionCount().increment();

            serviceRequest.setResponse(response);
        } catch (UaException e) {
            serverDiagnosticsSummary.getRejectedSessionCount().increment();

            if (e.getStatusCode().isSecurityError()) {
                serverDiagnosticsSummary.getSecurityRejectedSessionCount().increment();
            }

            serviceRequest.setServiceFault(e);
        }
    }

    private CreateSessionResponse createSession(ServiceRequest serviceRequest) throws UaException {
        CreateSessionRequest request = (CreateSessionRequest) serviceRequest.getRequest();

        long maxSessionCount = server.getConfig().getLimits().getMaxSessionCount().longValue();
        if (createdSessions.size() + activeSessions.size() >= maxSessionCount) {
            throw new UaException(StatusCodes.Bad_TooManySessions);
        }

        ByteString serverNonce = NonceUtil.generateNonce(32);
        NodeId authenticationToken = new NodeId(0, NonceUtil.generateNonce(32));
        long maxRequestMessageSize = serviceRequest.getServer().getConfig().getMessageLimits().getMaxMessageSize();
        double revisedSessionTimeout = Math.max(
            5000,
            Math.min(MAX_SESSION_TIMEOUT_MS, request.getRequestedSessionTimeout())
        );

        ApplicationDescription clientDescription = request.getClientDescription();

        long secureChannelId = serviceRequest.getSecureChannelId();
        EndpointDescription endpoint = serviceRequest.getEndpoint();

        SecurityPolicy securityPolicy = SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri());

        EndpointDescription[] serverEndpoints = server.getEndpointDescriptions()
            .stream()
            .filter(ed -> endpointMatchesUrl(ed, request.getEndpointUrl()))
            .toArray(EndpointDescription[]::new);

        ByteString clientNonce = request.getClientNonce();

        if (clientNonce.isNotNull() && (clientNonce.length() < 32)) {
            throw new UaException(StatusCodes.Bad_NonceInvalid);
        }

        if (securityPolicy != SecurityPolicy.None && clientNonces.contains(clientNonce)) {
            throw new UaException(StatusCodes.Bad_NonceInvalid);
        }

        if (securityPolicy != SecurityPolicy.None && clientNonce.isNotNull()) {
            clientNonces.add(clientNonce);
            while (clientNonces.size() > 64) {
                clientNonces.remove(0);
            }
        }

        ByteString clientCertificateBytes = request.getClientCertificate();

        if (securityPolicy != SecurityPolicy.None &&
            serviceRequest.getClientCertificateBytes() != null) {

            if (!Objects.equal(clientCertificateBytes,
                serviceRequest.getClientCertificateBytes())) {

                throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                    "certificate used to open secure channel " +
                        "differs from certificate used to create session");
            }
        }

        SecurityConfiguration securityConfiguration = createSecurityConfiguration(
            endpoint,
            clientCertificateBytes
        );

        if (securityPolicy != SecurityPolicy.None) {
            X509Certificate clientCertificate =
                securityConfiguration.getClientCertificate();

            List<X509Certificate> clientCertificateChain =
                securityConfiguration.getClientCertificateChain();

            if (clientCertificate == null || clientCertificateChain == null) {
                throw new UaException(
                    StatusCodes.Bad_SecurityChecksFailed,
                    "client certificate must be non-null");
            }

            CertificateValidationUtil.validateApplicationUri(
                clientCertificate,
                clientDescription.getApplicationUri()
            );

            CertificateValidator certificateValidator =
                server.getConfig().getCertificateValidator();

            certificateValidator.validate(clientCertificate);
            certificateValidator.verifyTrustChain(clientCertificateChain);
        }

        // SignatureData must be created using only the bytes of the client
        // leaf certificate, not the bytes of the client certificate chain.
        SignatureData serverSignature = getServerSignature(
            securityPolicy,
            securityConfiguration.getKeyPair(),
            clientNonce,
            securityConfiguration.getClientCertificateBytes()
        );

        NodeId sessionId = new NodeId(1, "Session:" + UUID.randomUUID());
        String sessionName = request.getSessionName();
        Duration sessionTimeout = Duration.ofMillis(DoubleMath.roundToLong(revisedSessionTimeout, RoundingMode.UP));

        Session session = new Session(
            server,
            sessionId,
            sessionName,
            sessionTimeout,
            clientDescription,
            request.getServerUri(),
            request.getMaxResponseMessageSize(),
            endpoint,
            secureChannelId,
            securityConfiguration
        );

        session.setLastNonce(serverNonce);

        session.addLifecycleListener((s, remove) -> {
            createdSessions.remove(authenticationToken);
            activeSessions.remove(authenticationToken);

            sessionListeners.forEach(l -> l.onSessionClosed(session));
        });

        createdSessions.put(authenticationToken, session);

        sessionListeners.forEach(l -> l.onSessionCreated(session));

        return new CreateSessionResponse(
            serviceRequest.createResponseHeader(),
            sessionId,
            authenticationToken,
            revisedSessionTimeout,
            serverNonce,
            endpoint.getServerCertificate(),
            serverEndpoints,
            new SignedSoftwareCertificate[0],
            serverSignature,
            uint(maxRequestMessageSize)
        );
    }

    private SecurityConfiguration createSecurityConfiguration(
        EndpointDescription endpoint,
        ByteString clientCertificateBytes) throws UaException {

        SecurityPolicy securityPolicy = SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri());
        MessageSecurityMode securityMode = endpoint.getSecurityMode();

        X509Certificate clientCertificate = null;
        List<X509Certificate> clientCertificateChain = null;

        KeyPair keyPair = null;
        X509Certificate serverCertificate = null;
        List<X509Certificate> serverCertificateChain = null;

        if (securityPolicy != SecurityPolicy.None) {
            clientCertificate = CertificateUtil
                .decodeCertificate(clientCertificateBytes.bytes());

            clientCertificateChain = CertificateUtil
                .decodeCertificates(clientCertificateBytes.bytes());

            ByteString thumbprint = ByteString.of(sha1(endpoint.getServerCertificate().bytesOrEmpty()));

            keyPair = server
                .getConfig()
                .getCertificateManager()
                .getKeyPair(thumbprint)
                .orElseThrow(() -> new UaException(StatusCodes.Bad_ConfigurationError));

            serverCertificate = server
                .getConfig()
                .getCertificateManager()
                .getCertificate(thumbprint)
                .orElseThrow(() -> new UaException(StatusCodes.Bad_ConfigurationError));

            serverCertificateChain = server
                .getConfig()
                .getCertificateManager()
                .getCertificateChain(thumbprint)
                .map(Lists::newArrayList)
                .orElseThrow(() -> new UaException(StatusCodes.Bad_ConfigurationError));
        }

        return new SecurityConfiguration(
            securityPolicy,
            securityMode,
            keyPair,
            serverCertificate,
            serverCertificateChain,
            clientCertificate,
            clientCertificateChain
        );
    }

    private boolean endpointMatchesUrl(EndpointDescription endpoint, String requestedEndpointUrl) {
        try {
            String requestedHost = EndpointUtil.getHost(requestedEndpointUrl);
            String endpointHost = EndpointUtil.getHost(endpoint.getEndpointUrl());

            return Strings.nullToEmpty(requestedHost)
                .equalsIgnoreCase(Strings.nullToEmpty(endpointHost));
        } catch (Throwable e) {
            logger.warn("Unable to create URI.", e);
            return false;
        }
    }

    @Override
    public void onActivateSession(ServiceRequest serviceRequest) {
        try {
            ActivateSessionResponse response = activateSession(serviceRequest);

            serviceRequest.setResponse(response);
        } catch (UaException e) {
            serviceRequest.setServiceFault(e);
        }
    }

    private ActivateSessionResponse activateSession(ServiceRequest serviceRequest) throws UaException {
        ActivateSessionRequest request = (ActivateSessionRequest) serviceRequest.getRequest();

        long secureChannelId = serviceRequest.getSecureChannelId();
        NodeId authToken = request.getRequestHeader().getAuthenticationToken();
        List<SignedSoftwareCertificate> clientSoftwareCertificates = l(request.getClientSoftwareCertificates());

        Session session = createdSessions.get(authToken);

        if (session == null) {
            session = activeSessions.get(authToken);

            if (session == null) {
                throw new UaException(StatusCodes.Bad_SessionIdInvalid);
            } else {
                verifyClientSignature(session, request);

                SecurityConfiguration securityConfiguration = session.getSecurityConfiguration();

                if (session.getSecureChannelId() == secureChannelId) {
                    /*
                     * Identity change
                     */
                    Object tokenObject = request.getUserIdentityToken().decode(
                        server.getSerializationContext()
                    );

                    Object identityObject = validateIdentityToken(
                        session,
                        tokenObject,
                        request.getUserTokenSignature()
                    );

                    StatusCode[] results = new StatusCode[clientSoftwareCertificates.size()];
                    Arrays.fill(results, StatusCode.GOOD);

                    ByteString serverNonce = NonceUtil.generateNonce(32);

                    session.setClientAddress(serviceRequest.getClientAddress());
                    session.setIdentityObject(identityObject);
                    session.setLastNonce(serverNonce);
                    session.setLocaleIds(request.getLocaleIds());

                    return new ActivateSessionResponse(
                        serviceRequest.createResponseHeader(),
                        serverNonce,
                        results,
                        new DiagnosticInfo[0]
                    );
                } else {
                    /*
                     * Associate session with new secure channel if client certificate and identity token match.
                     */
                    ByteString clientCertificateBytes = serviceRequest.getClientCertificateBytes();

                    if (request.getUserIdentityToken() == null ||
                        request.getUserIdentityToken().decode(server.getSerializationContext()) == null) {

                        throw new UaException(StatusCodes.Bad_IdentityTokenInvalid, "identity token not provided");
                    }

                    Object tokenObject = request.getUserIdentityToken().decode(
                        server.getSerializationContext()
                    );

                    Object identityObject = validateIdentityToken(
                        session,
                        tokenObject,
                        request.getUserTokenSignature()
                    );

                    boolean sameIdentity = Objects.equal(
                        identityObject,
                        session.getIdentityObject()
                    );

                    boolean sameCertificate = Objects.equal(
                        clientCertificateBytes,
                        securityConfiguration.getClientCertificateBytes()
                    );

                    if (sameIdentity && sameCertificate) {
                        SecurityConfiguration newSecurityConfiguration = createSecurityConfiguration(
                            serviceRequest.getEndpoint(),
                            clientCertificateBytes
                        );

                        session.setEndpoint(serviceRequest.getEndpoint());
                        session.setSecureChannelId(secureChannelId);
                        session.setSecurityConfiguration(newSecurityConfiguration);

                        logger.debug("Session id={} is now associated with secureChannelId={}",
                            session.getSessionId(), secureChannelId);

                        StatusCode[] results = new StatusCode[clientSoftwareCertificates.size()];
                        Arrays.fill(results, StatusCode.GOOD);

                        ByteString serverNonce = NonceUtil.generateNonce(32);

                        session.setClientAddress(serviceRequest.getClientAddress());
                        session.setLastNonce(serverNonce);
                        session.setLocaleIds(request.getLocaleIds());

                        return new ActivateSessionResponse(
                            serviceRequest.createResponseHeader(),
                            serverNonce,
                            results,
                            new DiagnosticInfo[0]
                        );
                    } else {
                        throw new UaException(StatusCodes.Bad_SecurityChecksFailed);
                    }
                }
            }
        } else {
            if (secureChannelId != session.getSecureChannelId()) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed);
            }

            if (request.getUserIdentityToken() == null ||
                request.getUserIdentityToken().decode(server.getSerializationContext()) == null) {

                throw new UaException(StatusCodes.Bad_IdentityTokenInvalid, "identity token not provided");
            }

            verifyClientSignature(session, request);

            Object tokenObject = request.getUserIdentityToken().decode(
                server.getSerializationContext()
            );

            Object identityObject = validateIdentityToken(
                session,
                tokenObject,
                request.getUserTokenSignature()
            );

            createdSessions.remove(authToken);
            activeSessions.put(authToken, session);

            StatusCode[] results = new StatusCode[clientSoftwareCertificates.size()];
            Arrays.fill(results, StatusCode.GOOD);

            ByteString serverNonce = NonceUtil.generateNonce(32);

            session.setClientAddress(serviceRequest.getClientAddress());
            session.setIdentityObject(identityObject);
            session.setLocaleIds(request.getLocaleIds());
            session.setLastNonce(serverNonce);

            return new ActivateSessionResponse(
                serviceRequest.createResponseHeader(),
                serverNonce,
                results,
                new DiagnosticInfo[0]
            );
        }
    }

    private static void verifyClientSignature(Session session, ActivateSessionRequest request) throws UaException {
        SecurityConfiguration securityConfiguration = session.getSecurityConfiguration();

        if (securityConfiguration.getSecurityPolicy() != SecurityPolicy.None) {
            SignatureData clientSignature = request.getClientSignature();

            byte[] dataBytes = Bytes.concat(
                securityConfiguration.getServerCertificateBytes().bytesOrEmpty(),
                session.getLastNonce().bytesOrEmpty()
            );

            byte[] signatureBytes = clientSignature.getSignature().bytesOrEmpty();

            SignatureUtil.verify(
                SecurityAlgorithm.fromUri(clientSignature.getAlgorithm()),
                securityConfiguration.getClientCertificate(),
                dataBytes,
                signatureBytes
            );
        }
    }

    private Object validateIdentityToken(
        Session session,
        Object tokenObject,
        SignatureData tokenSignature) throws UaException {

        IdentityValidator identityValidator = server.getConfig().getIdentityValidator();
        UserTokenPolicy tokenPolicy = validatePolicyId(session, tokenObject);

        if (tokenObject instanceof UserIdentityToken) {
            return identityValidator.validateIdentityToken(
                session,
                (UserIdentityToken) tokenObject,
                tokenPolicy,
                tokenSignature
            );
        } else {
            throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
        }
    }

    /**
     * Validates the policyId on a {@link UserIdentityToken} Object is a policyId that exists on the Endpoint that
     * {@code session} is connected to.
     *
     * @param session     the current {@link Session}
     * @param tokenObject the {@link UserIdentityToken} Object from the client.
     * @return the first {@link UserTokenPolicy} on the Endpoint matching the policyId.
     * @throws UaException if the token object is invalid or no matching policy is found.
     */
    private UserTokenPolicy validatePolicyId(Session session, Object tokenObject) throws UaException {
        if (tokenObject instanceof UserIdentityToken) {
            UserIdentityToken token = (UserIdentityToken) tokenObject;
            String policyId = token.getPolicyId();

            List<UserTokenPolicy> userIdentityTokens =
                l(session.getEndpoint().getUserIdentityTokens());

            Optional<UserTokenPolicy> policy = userIdentityTokens
                .stream()
                .filter(t -> Objects.equal(policyId, t.getPolicyId()))
                .findFirst();

            return policy.orElseThrow(() ->
                new UaException(
                    StatusCodes.Bad_IdentityTokenInvalid,
                    "policy not found: " + policyId)
            );
        } else {
            throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
        }
    }

    @Override
    public void onCloseSession(ServiceRequest service) {
        try {
            CloseSessionResponse response = closeSession(service);

            service.setResponse(response);
        } catch (UaException e) {
            service.setServiceFault(e);
        }
    }

    private CloseSessionResponse closeSession(ServiceRequest service) throws UaException {
        CloseSessionRequest request = (CloseSessionRequest) service.getRequest();

        long secureChannelId = service.getSecureChannelId();
        NodeId authToken = service.getRequest().getRequestHeader().getAuthenticationToken();

        Session session = activeSessions.get(authToken);

        if (session != null) {
            if (session.getSecureChannelId() != secureChannelId) {
                throw new UaException(StatusCodes.Bad_SecureChannelIdInvalid);
            } else {
                activeSessions.remove(authToken);
                session.close(request.getDeleteSubscriptions());
                return new CloseSessionResponse(service.createResponseHeader());
            }
        } else {
            session = createdSessions.get(authToken);

            if (session == null) {
                throw new UaException(StatusCodes.Bad_SessionIdInvalid);
            } else if (session.getSecureChannelId() != secureChannelId) {
                throw new UaException(StatusCodes.Bad_SecureChannelIdInvalid);
            } else {
                createdSessions.remove(authToken);
                session.close(request.getDeleteSubscriptions());
                return new CloseSessionResponse(service.createResponseHeader());
            }
        }
    }

    @Override
    public void onCancel(ServiceRequest service) throws UaException {
        session(service).onCancel(service);
    }

    private SignatureData getServerSignature(
        SecurityPolicy securityPolicy,
        KeyPair keyPair,
        ByteString clientNonce,
        ByteString clientCertificate) throws UaException {

        if (securityPolicy == SecurityPolicy.None) {
            return new SignatureData(null, null);
        } else {
            try {
                SecurityAlgorithm algorithm = securityPolicy.getAsymmetricSignatureAlgorithm();

                byte[] data = Bytes.concat(clientCertificate.bytes(), clientNonce.bytes());

                byte[] signature = SignatureUtil.sign(
                    algorithm,
                    keyPair.getPrivate(),
                    ByteBuffer.wrap(data)
                );

                return new SignatureData(algorithm.getUri(), ByteString.of(signature));
            } catch (UaRuntimeException e) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed);
            }
        }
    }
    //endregion

    //region Attribute Services
    @Override
    public void onRead(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getReadCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getAttributeServiceSet().onRead(service);
    }

    @Override
    public void onWrite(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getWriteCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getAttributeServiceSet().onWrite(service);
    }

    @Override
    public void onHistoryRead(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getHistoryReadCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getAttributeHistoryServiceSet().onHistoryRead(service);
    }

    @Override
    public void onHistoryUpdate(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getHistoryUpdateCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getAttributeHistoryServiceSet().onHistoryUpdate(service);
    }
    //endregion

    //region View Services
    @Override
    public void onBrowse(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getBrowseCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getViewServiceSet().onBrowse(service);
    }

    @Override
    public void onBrowseNext(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getBrowseNextCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getViewServiceSet().onBrowseNext(service);
    }

    @Override
    public void onTranslateBrowsePaths(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getTranslateBrowsePathsToNodeIdsCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getViewServiceSet().onTranslateBrowsePaths(service);
    }

    @Override
    public void onRegisterNodes(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getRegisterNodesCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getViewServiceSet().onRegisterNodes(service);
    }

    @Override
    public void onUnregisterNodes(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getUnregisterNodesCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getViewServiceSet().onUnregisterNodes(service);
    }
    //endregion

    //region NodeManagement Services
    @Override
    public void onAddNodes(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getAddNodesCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getNodeManagementServiceSet().onAddNodes(service);
    }

    @Override
    public void onAddReferences(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getAddReferencesCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getNodeManagementServiceSet().onAddReferences(service);
    }

    @Override
    public void onDeleteNodes(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getDeleteNodesCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getNodeManagementServiceSet().onDeleteNodes(service);
    }

    @Override
    public void onDeleteReferences(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getDeleteReferencesCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getNodeManagementServiceSet().onDeleteReferences(service);
    }
    //endregion

    //region Subscription Services
    @Override
    public void onCreateSubscription(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getCreateSubscriptionCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getSubscriptionServiceSet().onCreateSubscription(service);
    }

    @Override
    public void onModifySubscription(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getModifySubscriptionCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getSubscriptionServiceSet().onModifySubscription(service);
    }

    @Override
    public void onSetPublishingMode(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getSetPublishingModeCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getSubscriptionServiceSet().onSetPublishingMode(service);
    }

    @Override
    public void onPublish(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getPublishCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getSubscriptionServiceSet().onPublish(service);
    }

    @Override
    public void onRepublish(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getRepublishCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getSubscriptionServiceSet().onRepublish(service);
    }

    @Override
    public void onTransferSubscriptions(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getTransferSubscriptionsCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getSubscriptionServiceSet().onTransferSubscriptions(service);
    }

    @Override
    public void onDeleteSubscriptions(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getDeleteSubscriptionsCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getSubscriptionServiceSet().onDeleteSubscriptions(service);
    }
    //endregion

    //region MonitoredItem Services
    @Override
    public void onCreateMonitoredItems(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getCreateMonitoredItemsCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getMonitoredItemServiceSet().onCreateMonitoredItems(service);
    }

    @Override
    public void onModifyMonitoredItems(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getModifyMonitoredItemsCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getMonitoredItemServiceSet().onModifyMonitoredItems(service);
    }

    @Override
    public void onSetMonitoringMode(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getSetMonitoringModeCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getMonitoredItemServiceSet().onSetMonitoringMode(service);
    }

    @Override
    public void onSetTriggering(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getSetTriggeringCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getMonitoredItemServiceSet().onSetTriggering(service);
    }

    @Override
    public void onDeleteMonitoredItems(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getDeleteMonitoredItemsCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getMonitoredItemServiceSet().onDeleteMonitoredItems(service);
    }
    //endregion

    //region Method Services
    @Override
    public void onCall(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getCallCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getMethodServiceSet().onCall(service);
    }
    //endregion

    //region Query Services
    @Override
    public void onQueryFirst(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getQueryFirstCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getQueryServiceSet().onQueryFirst(service);
    }

    @Override
    public void onQueryNext(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSessionDiagnostics().getQueryNextCount().record(service);
        session.getSessionDiagnostics().getTotalRequestCount().record(service);

        session.getQueryServiceSet().onQueryNext(service);
    }
    //endregion

}
