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
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.Bytes;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
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

import static com.google.common.base.Strings.nullToEmpty;
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
    private final Map<NodeId, Session> inactiveSessions = Maps.newConcurrentMap();

    /**
     * Store the last N client nonces and to make sure they aren't re-used.
     * <p>
     * This number is arbitrary; trying to prevent clients from re-using nonces is merely to satisfy the CTT.
     */
    private final List<ByteString> clientNonces = newCopyOnWriteArrayList();

    private final OpcUaServer server;

    public SessionManager(OpcUaServer server) {
        this.server = server;
    }

    public List<Session> getActiveSessions() {
        return Lists.newArrayList(activeSessions.values());
    }

    public List<Session> getInactiveSessions() {
        return Lists.newArrayList(inactiveSessions.values());
    }

    public void killSession(NodeId nodeId, boolean deleteSubscriptions) {
        activeSessions.values().stream()
            .filter(s -> s.getSessionId().equals(nodeId))
            .findFirst().ifPresent(s -> s.close(deleteSubscriptions));
    }

    private Session session(ServiceRequest service) throws UaException {
        long secureChannelId = service.getSecureChannelId();
        NodeId authToken = service.getRequest().getRequestHeader().getAuthenticationToken();

        Session session = activeSessions.get(authToken);

        if (session == null) {
            session = createdSessions.remove(authToken);

            if (session == null) {
                throw new UaException(StatusCodes.Bad_SessionIdInvalid);
            } else {
                if (session.getSecureChannelId() != secureChannelId) {
                    createdSessions.put(authToken, session);
                    throw new UaException(StatusCodes.Bad_SecurityChecksFailed);
                } else {
                    throw new UaException(StatusCodes.Bad_SessionNotActivated);
                }
            }
        }

        if (session.getSecureChannelId() != secureChannelId) {
            throw new UaException(StatusCodes.Bad_SecureChannelIdInvalid);
        }

        session.updateLastActivity();

        service.attr(ServiceAttributes.SERVER_KEY).set(server);
        service.attr(ServiceAttributes.SESSION_KEY).set(session);

        return session;
    }

    //region Session Services
    @Override
    public void onCreateSession(ServiceRequest serviceRequest) throws UaException {
        CreateSessionRequest request = (CreateSessionRequest) serviceRequest.getRequest();

        long maxSessionCount = server.getConfig().getLimits().getMaxSessionCount().longValue();
        if (createdSessions.size() + activeSessions.size() >= maxSessionCount) {
            serviceRequest.setServiceFault(StatusCodes.Bad_TooManySessions);
            return;
        }

        ByteString serverNonce = NonceUtil.generateNonce(32);
        NodeId authenticationToken = new NodeId(0, NonceUtil.generateNonce(32));
        long maxRequestMessageSize = serviceRequest.getServer().getConfig().getMessageLimits().getMaxMessageSize();
        double revisedSessionTimeout = Math.max(
            5000,
            Math.min(MAX_SESSION_TIMEOUT_MS, request.getRequestedSessionTimeout())
        );

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

            String applicationUri = request.getClientDescription().getApplicationUri();

            CertificateValidationUtil.validateApplicationUri(clientCertificate, applicationUri);

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
            secureChannelId,
            endpoint,
            securityConfiguration
        );

        session.setLastNonce(serverNonce);

        session.addLifecycleListener((s, remove) -> {
            createdSessions.remove(authenticationToken);
            activeSessions.remove(authenticationToken);
        });

        createdSessions.put(authenticationToken, session);

        CreateSessionResponse response = new CreateSessionResponse(
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

        serviceRequest.setResponse(response);
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

    /**
     * @param endpoint             an {@link EndpointDescription}.
     * @param requestedEndpointUrl an endpoint URL.
     * @return {@code true} if the host in {@code endpoint} matches the host in {@code requestedEndpointUrl}.
     */
    private static boolean endpointMatchesUrl(EndpointDescription endpoint, String requestedEndpointUrl) {
        String endpointHost = EndpointUtil.getHost(nullToEmpty(endpoint.getEndpointUrl()));
        String requestedHost = EndpointUtil.getHost(nullToEmpty(requestedEndpointUrl));

        return nullToEmpty(endpointHost).equalsIgnoreCase(nullToEmpty(requestedHost));
    }

    @Override
    public void onActivateSession(ServiceRequest serviceRequest) throws UaException {

        ActivateSessionRequest request = (ActivateSessionRequest) serviceRequest.getRequest();

        // ServerSecureChannel secureChannel = serviceRequest.getSecureChannel();

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
                    UserIdentityToken identityToken = decodeIdentityToken(
                        request.getUserIdentityToken(),
                        session.getEndpoint().getUserIdentityTokens()
                    );

                    Object identityObject = validateIdentityToken(
                        session,
                        identityToken,
                        request.getUserTokenSignature()
                    );

                    StatusCode[] results = new StatusCode[clientSoftwareCertificates.size()];
                    Arrays.fill(results, StatusCode.GOOD);

                    ByteString serverNonce = NonceUtil.generateNonce(32);

                    session.setClientAddress(serviceRequest.getClientAddress());
                    session.setIdentityObject(identityObject);
                    session.setLastNonce(serverNonce);
                    session.setLocaleIds(request.getLocaleIds());

                    ActivateSessionResponse response = new ActivateSessionResponse(
                        serviceRequest.createResponseHeader(),
                        serverNonce,
                        results,
                        new DiagnosticInfo[0]
                    );

                    serviceRequest.setResponse(response);
                } else {
                    /*
                     * Associate session with new secure channel if client certificate and identity token match.
                     */
                    ByteString clientCertificateBytes = serviceRequest.getClientCertificateBytes();

                    UserIdentityToken identityToken = decodeIdentityToken(
                        request.getUserIdentityToken(),
                        session.getEndpoint().getUserIdentityTokens()
                    );

                    Object identityObject = validateIdentityToken(
                        session,
                        identityToken,
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

                        ActivateSessionResponse response = new ActivateSessionResponse(
                            serviceRequest.createResponseHeader(),
                            serverNonce,
                            results,
                            new DiagnosticInfo[0]
                        );

                        serviceRequest.setResponse(response);
                    } else {
                        throw new UaException(StatusCodes.Bad_SecurityChecksFailed);
                    }
                }
            }
        } else {
            if (secureChannelId != session.getSecureChannelId()) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed);
            }

            verifyClientSignature(session, request);

            UserIdentityToken identityToken = decodeIdentityToken(
                request.getUserIdentityToken(),
                session.getEndpoint().getUserIdentityTokens()
            );

            Object identityObject = validateIdentityToken(
                session,
                identityToken,
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

            ActivateSessionResponse response = new ActivateSessionResponse(
                serviceRequest.createResponseHeader(),
                serverNonce,
                results,
                new DiagnosticInfo[0]
            );

            serviceRequest.setResponse(response);
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

    /**
     * Decode a {@link UserIdentityToken}.
     * <p>
     * Null or empty tokens are interpreted as {@link AnonymousIdentityToken}, as per the spec.
     *
     * @param identityTokenXo the {@link ExtensionObject} to decode.
     * @param tokenPolicies   the {@link UserTokenPolicy}s from the Session's Endpoint.
     * @return a {@link UserIdentityToken} object.
     */
    @Nonnull
    private UserIdentityToken decodeIdentityToken(
        @Nullable ExtensionObject identityTokenXo,
        @Nullable UserTokenPolicy[] tokenPolicies
    ) {

        if (identityTokenXo != null && !identityTokenXo.isNull()) {
            Object identityToken = identityTokenXo.decodeOrNull(
                server.getSerializationContext()
            );

            if (identityToken instanceof UserIdentityToken) {
                return (UserIdentityToken) identityToken;
            }
        }

        String policyId = l(tokenPolicies).stream()
            .filter(p -> p.getTokenType() == UserTokenType.Anonymous)
            .findFirst()
            .map(UserTokenPolicy::getPolicyId)
            .orElse(null);

        return new AnonymousIdentityToken(policyId);
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
    public void onCloseSession(ServiceRequest service) throws UaException {
        long secureChannelId = service.getSecureChannelId();
        NodeId authToken = service.getRequest().getRequestHeader().getAuthenticationToken();

        Session session = activeSessions.get(authToken);

        if (session != null) {
            if (session.getSecureChannelId() != secureChannelId) {
                throw new UaException(StatusCodes.Bad_SecureChannelIdInvalid);
            } else {
                activeSessions.remove(authToken);
                session.onCloseSession(service);
            }
        } else {
            session = createdSessions.get(authToken);

            if (session == null) {
                throw new UaException(StatusCodes.Bad_SessionIdInvalid);
            } else if (session.getSecureChannelId() != secureChannelId) {
                throw new UaException(StatusCodes.Bad_SecureChannelIdInvalid);
            } else {
                createdSessions.remove(authToken);
                session.onCloseSession(service);
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

        session.getAttributeServiceSet().onRead(service);
    }

    @Override
    public void onWrite(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getAttributeServiceSet().onWrite(service);
    }

    @Override
    public void onHistoryRead(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getAttributeHistoryServiceSet().onHistoryRead(service);
    }

    @Override
    public void onHistoryUpdate(ServiceRequest service) throws UaException {

        Session session = session(service);

        session.getAttributeHistoryServiceSet().onHistoryUpdate(service);
    }
    //endregion

    //region View Services
    @Override
    public void onBrowse(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getViewServiceSet().onBrowse(service);
    }

    @Override
    public void onBrowseNext(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getViewServiceSet().onBrowseNext(service);
    }

    @Override
    public void onTranslateBrowsePaths(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getViewServiceSet().onTranslateBrowsePaths(service);
    }

    @Override
    public void onRegisterNodes(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getViewServiceSet().onRegisterNodes(service);
    }

    @Override
    public void onUnregisterNodes(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getViewServiceSet().onUnregisterNodes(service);
    }
    //endregion

    //region NodeManagement Services
    @Override
    public void onAddNodes(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getNodeManagementServiceSet().onAddNodes(service);
    }

    @Override
    public void onAddReferences(ServiceRequest service) throws UaException {

        Session session = session(service);

        session.getNodeManagementServiceSet().onAddReferences(service);
    }

    @Override
    public void onDeleteNodes(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getNodeManagementServiceSet().onDeleteNodes(service);
    }

    @Override
    public void onDeleteReferences(ServiceRequest service) throws UaException {

        Session session = session(service);

        session.getNodeManagementServiceSet().onDeleteReferences(service);
    }
    //endregion

    //region Subscription Services
    @Override
    public void onCreateSubscription(ServiceRequest service) throws UaException {

        Session session = session(service);

        session.getSubscriptionServiceSet().onCreateSubscription(service);
    }

    @Override
    public void onModifySubscription(ServiceRequest service) throws UaException {

        Session session = session(service);

        session.getSubscriptionServiceSet().onModifySubscription(service);
    }

    @Override
    public void onSetPublishingMode(ServiceRequest service) throws UaException {

        Session session = session(service);

        session.getSubscriptionServiceSet().onSetPublishingMode(service);
    }

    @Override
    public void onPublish(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSubscriptionServiceSet().onPublish(service);
    }

    @Override
    public void onRepublish(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSubscriptionServiceSet().onRepublish(service);
    }

    @Override
    public void onTransferSubscriptions(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSubscriptionServiceSet().onTransferSubscriptions(service);
    }

    @Override
    public void onDeleteSubscriptions(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getSubscriptionServiceSet().onDeleteSubscriptions(service);
    }
    //endregion

    //region MonitoredItem Services
    @Override
    public void onCreateMonitoredItems(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getMonitoredItemServiceSet().onCreateMonitoredItems(service);
    }

    @Override
    public void onModifyMonitoredItems(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getMonitoredItemServiceSet().onModifyMonitoredItems(service);
    }

    @Override
    public void onSetMonitoringMode(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getMonitoredItemServiceSet().onSetMonitoringMode(service);
    }

    @Override
    public void onSetTriggering(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getMonitoredItemServiceSet().onSetTriggering(service);
    }

    @Override
    public void onDeleteMonitoredItems(ServiceRequest service) throws UaException {

        Session session = session(service);

        session.getMonitoredItemServiceSet().onDeleteMonitoredItems(service);
    }
    //endregion

    //region Method Services
    @Override
    public void onCall(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getMethodServiceSet().onCall(service);
    }
    //endregion

    //region Query Services
    @Override
    public void onQueryFirst(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getQueryServiceSet().onQueryFirst(service);
    }

    @Override
    public void onQueryNext(ServiceRequest service) throws UaException {
        Session session = session(service);

        session.getQueryServiceSet().onQueryNext(service);
    }
    //endregion

}
