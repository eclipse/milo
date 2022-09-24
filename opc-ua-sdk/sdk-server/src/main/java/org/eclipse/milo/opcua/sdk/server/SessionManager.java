/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.base.Objects;
import com.google.common.math.DoubleMath;
import com.google.common.primitives.Bytes;
import org.eclipse.milo.opcua.sdk.server.diagnostics.ServerDiagnosticsSummary;
import org.eclipse.milo.opcua.sdk.server.identity.IdentityValidator;
import org.eclipse.milo.opcua.sdk.server.services.ServiceAttributes;
import org.eclipse.milo.opcua.sdk.server.services2.SessionServiceSet2;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.UaRuntimeException;
import org.eclipse.milo.opcua.stack.core.channel.SecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AnonymousIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.RequestHeader;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.core.util.NonceUtil;
import org.eclipse.milo.opcua.stack.core.util.SignatureUtil;
import org.eclipse.milo.opcua.stack.server.security.ServerCertificateValidator;
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
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.Objects.requireNonNullElse;
import static org.eclipse.milo.opcua.sdk.server.services2.AbstractServiceSet.createResponseHeader;
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
    SessionServiceSet2,
    SubscriptionServiceSet,
    ViewServiceSet {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Map<NodeId, Session> createdSessions = new ConcurrentHashMap<>();
    private final Map<NodeId, Session> activeSessions = new ConcurrentHashMap<>();

    private final List<SessionListener> sessionListeners = new CopyOnWriteArrayList<>();

    /**
     * Store the last N client nonces and to make sure they aren't re-used.
     * <p>
     * This number is arbitrary; trying to prevent clients from re-using nonces is merely to satisfy the CTT.
     */
    private final List<ByteString> clientNonces = new CopyOnWriteArrayList<>();

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

    /**
     * Add {@code listener} to be notified when sessions are created.
     *
     * @param listener the {@link SessionListener} to add.
     */
    public void addSessionListener(SessionListener listener) {
        sessionListeners.add(listener);
    }

    /**
     * Remove {@code listener} from the {@link SessionListener} list.
     *
     * @param listener the {@link SessionListener} to remove.
     */
    public void removeSessionListener(SessionListener listener) {
        sessionListeners.remove(listener);
    }

    /**
     * Get a list of all the current {@link Session}s. This includes sessions that have been created but not yet
     * activated.
     *
     * @return a list of all the current {@link Session}s.
     */
    public List<Session> getAllSessions() {
        List<Session> sessions = new ArrayList<>();
        sessions.addAll(createdSessions.values());
        sessions.addAll(activeSessions.values());
        return sessions;
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
                session.close(true);

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

    public Session getSession(ServiceRequestContext context, RequestHeader requestHeader) throws UaException {
        long secureChannelId = context.getSecureChannel().getChannelId();
        NodeId authToken = requestHeader.getAuthenticationToken();

        Session session = activeSessions.get(authToken);

        if (session == null) {
            // session is either not activated or doesn't exist
            session = createdSessions.get(authToken);

            if (session != null) {
                session.close(true);

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

            return session;
        }
    }

    //region Session Services


    @Override
    public CompletableFuture<CreateSessionResponse> onCreateSession(
        ServiceRequestContext context,
        CreateSessionRequest request
    ) {

        ServerDiagnosticsSummary serverDiagnosticsSummary = server.getDiagnosticsSummary();

        try {
            CreateSessionResponse response = createSession(context, request);

            serverDiagnosticsSummary.getCumulatedSessionCount().increment();

            return CompletableFuture.completedFuture(response);
        } catch (UaException e) {
            serverDiagnosticsSummary.getRejectedSessionCount().increment();

            if (e.getStatusCode().isSecurityError()) {
                serverDiagnosticsSummary.getSecurityRejectedSessionCount().increment();
            }

            return CompletableFuture.failedFuture(e);
        }
    }

    @Override
    public CompletableFuture<ActivateSessionResponse> onActivateSession(
        ServiceRequestContext context,
        ActivateSessionRequest request
    ) {

        return null; // TODO
    }

    @Override
    public CompletableFuture<CloseSessionResponse> onCloseSession(
        ServiceRequestContext context,
        CloseSessionRequest request
    ) {

        return null; // TODO
    }

    @Override
    public CompletableFuture<CancelResponse> onCancel(
        ServiceRequestContext context,
        CancelRequest request
    ) {

        return null; // TODO
    }

    @Override
    public void onCreateSession(ServiceRequest serviceRequest) {
        ServerDiagnosticsSummary serverDiagnosticsSummary = server.getDiagnosticsSummary();

        try {
            CreateSessionResponse response = createSession(serviceRequest);

            serverDiagnosticsSummary.getCumulatedSessionCount().increment();

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
        long maxRequestMessageSize = serviceRequest.getServer().getConfig().getEncodingLimits().getMaxMessageSize();
        double revisedSessionTimeout = Math.max(
            5000,
            Math.min(server.getConfig().getLimits().getMaxSessionTimeout(), request.getRequestedSessionTimeout())
        );

        ApplicationDescription clientDescription = request.getClientDescription();

        long secureChannelId = serviceRequest.getSecureChannelId();
        EndpointDescription endpoint = serviceRequest.getEndpoint();

        SecurityPolicy securityPolicy = SecurityPolicy.fromUri(endpoint.getSecurityPolicyUri());

        EndpointDescription[] serverEndpoints = server.getEndpointDescriptions()
            .stream()
            .filter(ed -> !ed.getEndpointUrl().endsWith("/discovery"))
            .filter(ed -> endpointMatchesUrl(ed, request.getEndpointUrl()))
            .filter(ed -> Objects.equal(endpoint.getTransportProfileUri(), ed.getTransportProfileUri()))
            .map(SessionManager::stripNonEssentialFields)
            .toArray(EndpointDescription[]::new);

        if (serverEndpoints.length == 0) {
            // GetEndpoints in UaStackServer returns *all* endpoints regardless of a hostname
            // match in the endpoint URL if the result after filtering is 0 endpoints. Do the
            // same here.
            serverEndpoints = server.getEndpointDescriptions()
                .stream()
                .filter(ed -> !ed.getEndpointUrl().endsWith("/discovery"))
                .filter(ed -> Objects.equal(endpoint.getTransportProfileUri(), ed.getTransportProfileUri()))
                .map(SessionManager::stripNonEssentialFields)
                .toArray(EndpointDescription[]::new);
        }

        ByteString clientNonce = request.getClientNonce();

        if (securityPolicy != SecurityPolicy.None) {
            NonceUtil.validateNonce(clientNonce);

            if (clientNonces.contains(clientNonce)) {
                throw new UaException(StatusCodes.Bad_NonceInvalid);
            }
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

            ServerCertificateValidator certificateValidator =
                server.getConfig().getCertificateValidator();

            certificateValidator.validateCertificateChain(
                clientCertificateChain,
                clientDescription.getApplicationUri()
            );
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

            sessionListeners.forEach(l -> l.onSessionClosed(s));
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

    private CreateSessionResponse createSession(
        ServiceRequestContext context,
        CreateSessionRequest request
    ) throws UaException {

        long maxSessionCount = server.getConfig().getLimits().getMaxSessionCount().longValue();
        if (createdSessions.size() + activeSessions.size() >= maxSessionCount) {
            throw new UaException(StatusCodes.Bad_TooManySessions);
        }

        ByteString serverNonce = NonceUtil.generateNonce(32);
        NodeId authenticationToken = new NodeId(0, NonceUtil.generateNonce(32));
        long maxRequestMessageSize = server.getConfig().getEncodingLimits().getMaxMessageSize();
        double revisedSessionTimeout = Math.max(
            5000,
            Math.min(server.getConfig().getLimits().getMaxSessionTimeout(), request.getRequestedSessionTimeout())
        );

        ApplicationDescription clientDescription = request.getClientDescription();

        long secureChannelId = context.getSecureChannel().getChannelId();
        SecurityPolicy securityPolicy = context.getSecureChannel().getSecurityPolicy();
        // TODO get from ServiceRequestContext
        String transportProfileUri = TransportProfile.TCP_UASC_UABINARY.getUri();

        EndpointDescription[] serverEndpoints = server.getEndpointDescriptions()
            .stream()
            .filter(ed -> !ed.getEndpointUrl().endsWith("/discovery"))
            .filter(ed -> endpointMatchesUrl(ed, request.getEndpointUrl()))
            .filter(ed -> Objects.equal(transportProfileUri, ed.getTransportProfileUri()))
            .map(SessionManager::stripNonEssentialFields)
            .toArray(EndpointDescription[]::new);

        if (serverEndpoints.length == 0) {
            // GetEndpoints in UaStackServer returns *all* endpoints regardless of a hostname
            // match in the endpoint URL if the result after filtering is 0 endpoints. Do the
            // same here.
            serverEndpoints = server.getEndpointDescriptions()
                .stream()
                .filter(ed -> !ed.getEndpointUrl().endsWith("/discovery"))
                .filter(ed -> Objects.equal(transportProfileUri, ed.getTransportProfileUri()))
                .map(SessionManager::stripNonEssentialFields)
                .toArray(EndpointDescription[]::new);
        }

        ByteString clientNonce = request.getClientNonce();

        if (securityPolicy != SecurityPolicy.None) {
            NonceUtil.validateNonce(clientNonce);

            if (clientNonces.contains(clientNonce)) {
                throw new UaException(StatusCodes.Bad_NonceInvalid);
            }
        }

        if (securityPolicy != SecurityPolicy.None && clientNonce.isNotNull()) {
            clientNonces.add(clientNonce);
            while (clientNonces.size() > 64) {
                clientNonces.remove(0);
            }
        }

        ByteString clientCertificateBytesFromRequest = request.getClientCertificate();

        ByteString clientCertificateBytesFromSecureChannel;
        try {
            clientCertificateBytesFromSecureChannel =
                ByteString.of(context.getSecureChannel().getRemoteCertificate().getEncoded());
        } catch (CertificateEncodingException e) {
            throw new UaException(StatusCodes.Bad_SecurityChecksFailed, e);
        }

        if (securityPolicy != SecurityPolicy.None) {
            if (!Objects.equal(clientCertificateBytesFromRequest, clientCertificateBytesFromSecureChannel)) {
                throw new UaException(
                    StatusCodes.Bad_SecurityChecksFailed,
                    "certificate used to open secure channel " +
                        "differs from certificate used to create session"
                );
            }
        }

        SecurityConfiguration securityConfiguration =
            createSecurityConfiguration(context.getSecureChannel());

        if (securityPolicy != SecurityPolicy.None) {
            X509Certificate clientCertificate =
                securityConfiguration.getClientCertificate();

            List<X509Certificate> clientCertificateChain =
                securityConfiguration.getClientCertificateChain();

            if (clientCertificate == null || clientCertificateChain == null) {
                throw new UaException(
                    StatusCodes.Bad_SecurityChecksFailed,
                    "client certificate must be non-null"
                );
            }

            server.getConfig().getCertificateValidator().validateCertificateChain(
                clientCertificateChain,
                clientDescription.getApplicationUri()
            );
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
            null, // TODO
            secureChannelId,
            securityConfiguration
        );

        session.setLastNonce(serverNonce);

        session.addLifecycleListener((s, remove) -> {
            createdSessions.remove(authenticationToken);
            activeSessions.remove(authenticationToken);

            sessionListeners.forEach(l -> l.onSessionClosed(s));
        });

        createdSessions.put(authenticationToken, session);

        sessionListeners.forEach(l -> l.onSessionCreated(session));

        return new CreateSessionResponse(
            createResponseHeader(request),
            sessionId,
            authenticationToken,
            revisedSessionTimeout,
            serverNonce,
            securityConfiguration.getServerCertificateBytes(),
            serverEndpoints,
            new SignedSoftwareCertificate[0],
            serverSignature,
            uint(maxRequestMessageSize)
        );
    }

    private SecurityConfiguration createSecurityConfiguration(
        EndpointDescription endpoint,
        ByteString clientCertificateBytes
    ) throws UaException {

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
                .map(List::of)
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

    private SecurityConfiguration createSecurityConfiguration(SecureChannel secureChannel) throws UaException {
        SecurityPolicy securityPolicy = secureChannel.getSecurityPolicy();
        MessageSecurityMode securityMode = secureChannel.getMessageSecurityMode();

        X509Certificate clientCertificate = null;
        List<X509Certificate> clientCertificateChain = null;

        KeyPair keyPair = null;
        X509Certificate serverCertificate = null;
        List<X509Certificate> serverCertificateChain = null;

        if (securityPolicy != SecurityPolicy.None) {
            clientCertificate = secureChannel.getRemoteCertificate();
            clientCertificateChain = secureChannel.getRemoteCertificateChain();

            ByteString thumbprint = ByteString.of(sha1(secureChannel.getLocalCertificateBytes().bytes()));

            keyPair = server.getConfig()
                .getCertificateManager()
                .getKeyPair(thumbprint)
                .orElseThrow(() -> new UaException(StatusCodes.Bad_ConfigurationError));

            serverCertificate = server.getConfig()
                .getCertificateManager()
                .getCertificate(thumbprint)
                .orElseThrow(() -> new UaException(StatusCodes.Bad_ConfigurationError));

            serverCertificateChain = server.getConfig()
                .getCertificateManager()
                .getCertificateChain(thumbprint)
                .map(List::of)
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
        String endpointHost = EndpointUtil.getHost(requireNonNullElse(endpoint.getEndpointUrl(), ""));
        String requestedHost = EndpointUtil.getHost(requireNonNullElse(requestedEndpointUrl, ""));

        return requireNonNullElse(endpointHost, "")
            .equalsIgnoreCase(requireNonNullElse(requestedHost, ""));
    }

    /**
     * Strip the non-essential fields from an EndpointDescription and its ApplicationDescription
     * for return by the CreateSession service.
     * <p>
     * See Part 4, 5.6.6.2 for details.
     *
     * @param endpoint the {@link EndpointDescription} to strip non-essential fields from.
     * @return a new {@link EndpointDescription} with only the essential fields.
     */
    private static EndpointDescription stripNonEssentialFields(EndpointDescription endpoint) {
        // It is recommended that Servers only include the server.applicationUri, endpointUrl,
        // securityMode, securityPolicyUri, userIdentityTokens, transportProfileUri, and
        // securityLevel with all other parameters set to null. Only the recommended parameters
        // shall be verified by the client.
        ApplicationDescription applicationDescription = endpoint.getServer();
        ApplicationDescription newApplicationDescription = new ApplicationDescription(
            applicationDescription.getApplicationUri(),
            null,
            null,
            ApplicationType.Server,
            null,
            null,
            null
        );
        return new EndpointDescription(
            endpoint.getEndpointUrl(),
            newApplicationDescription,
            ByteString.NULL_VALUE,
            endpoint.getSecurityMode(),
            endpoint.getSecurityPolicyUri(),
            endpoint.getUserIdentityTokens(),
            endpoint.getTransportProfileUri(),
            endpoint.getSecurityLevel()
        );
    }

    @Override
    public void onActivateSession(ServiceRequest serviceRequest) {
        try {
            ActivateSessionResponse response = activateSession(serviceRequest);

            serviceRequest.setResponse(response);
        } catch (UaException e) {
            ServerDiagnosticsSummary serverDiagnosticsSummary = server.getDiagnosticsSummary();

            serverDiagnosticsSummary.getRejectedSessionCount().increment();

            if (e.getStatusCode().isSecurityError()) {
                serverDiagnosticsSummary.getSecurityRejectedSessionCount().increment();
            }

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
                    session.setIdentityObject(identityObject, identityToken);
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
            session.setIdentityObject(identityObject, identityToken);
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
            ByteString serverCertificateBs = securityConfiguration.getServerCertificateBytes();
            ByteString lastNonceBs = session.getLastNonce();

            try {
                byte[] dataBytes = Bytes.concat(
                    serverCertificateBs.bytesOrEmpty(),
                    lastNonceBs.bytesOrEmpty()
                );

                try {
                    SignatureUtil.verify(
                        SecurityAlgorithm.fromUri(clientSignature.getAlgorithm()),
                        securityConfiguration.getClientCertificate(),
                        dataBytes,
                        clientSignature.getSignature().bytesOrEmpty()
                    );
                } catch (UaException e) {
                    throw new UaException(StatusCodes.Bad_ApplicationSignatureInvalid, e);
                }
            } catch (UaException e) {
                // Maybe try again using the full certificate chain bytes instead

                ByteString serverCertificateChainBs = securityConfiguration.getServerCertificateChainBytes();

                if (serverCertificateBs.equals(serverCertificateChainBs)) {
                    throw e;
                } else {
                    byte[] dataBytes = Bytes.concat(
                        serverCertificateChainBs.bytesOrEmpty(),
                        lastNonceBs.bytesOrEmpty()
                    );

                    try {
                        SignatureUtil.verify(
                            SecurityAlgorithm.fromUri(clientSignature.getAlgorithm()),
                            securityConfiguration.getClientCertificate(),
                            dataBytes,
                            clientSignature.getSignature().bytesOrEmpty()
                        );
                    } catch (UaException ex) {
                        throw new UaException(StatusCodes.Bad_ApplicationSignatureInvalid, e);
                    }
                }
            }
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
    @NotNull
    private UserIdentityToken decodeIdentityToken(
        @Nullable ExtensionObject identityTokenXo,
        @Nullable UserTokenPolicy[] tokenPolicies
    ) {

        if (identityTokenXo != null && !identityTokenXo.isNull()) {
            Object identityToken = identityTokenXo.decodeOrNull(
                server.getEncodingContext()
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
