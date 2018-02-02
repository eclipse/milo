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

package org.eclipse.milo.opcua.sdk.server;

import java.math.RoundingMode;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.KeyPair;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
import org.eclipse.milo.opcua.stack.core.application.services.AttributeHistoryServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.MethodServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.MonitoredItemServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.QueryServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ServiceRequest;
import org.eclipse.milo.opcua.stack.core.application.services.SessionServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.SubscriptionServiceSet;
import org.eclipse.milo.opcua.stack.core.application.services.ViewServiceSet;
import org.eclipse.milo.opcua.stack.core.channel.ServerSecureChannel;
import org.eclipse.milo.opcua.stack.core.security.SecurityAlgorithm;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ActivateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.AddReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.BrowseResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CallRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CallResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CancelResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CloseSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSessionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteReferencesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.DeleteSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.HistoryUpdateResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifyMonitoredItemsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.PublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryFirstResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.QueryNextResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RegisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.RepublishResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetMonitoringModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetPublishingModeResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.SetTriggeringResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.SignatureData;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TransferSubscriptionsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.TranslateBrowsePathsToNodeIdsResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.UnregisterNodesResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.UserIdentityToken;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.WriteResponse;
import org.eclipse.milo.opcua.stack.core.util.CertificateUtil;
import org.eclipse.milo.opcua.stack.core.util.NonceUtil;
import org.eclipse.milo.opcua.stack.core.util.SignatureUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newCopyOnWriteArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.l;

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

    private Session session(ServiceRequest<?, ?> service) throws UaException {
        long secureChannelId = service.getSecureChannel().getChannelId();
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
    public void onCreateSession(
        ServiceRequest<CreateSessionRequest, CreateSessionResponse> serviceRequest) throws UaException {

        CreateSessionRequest request = serviceRequest.getRequest();

        long maxSessionCount = server.getConfig().getLimits().getMaxSessionCount().longValue();
        if (createdSessions.size() + activeSessions.size() >= maxSessionCount) {
            serviceRequest.setServiceFault(StatusCodes.Bad_TooManySessions);
            return;
        }

        ByteString serverNonce = NonceUtil.generateNonce(32);
        NodeId authenticationToken = new NodeId(0, NonceUtil.generateNonce(32));
        long maxRequestMessageSize = serviceRequest.getServer().getChannelConfig().getMaxMessageSize();
        double revisedSessionTimeout = Math.max(
            5000,
            Math.min(MAX_SESSION_TIMEOUT_MS, request.getRequestedSessionTimeout())
        );

        ServerSecureChannel secureChannel = serviceRequest.getSecureChannel();
        SecurityPolicy securityPolicy = secureChannel.getSecurityPolicy();

        ByteString serverCertificate = serviceRequest
            .getSecureChannel().getEndpointDescription().getServerCertificate();

        SignedSoftwareCertificate[] serverSoftwareCertificates = server.getSoftwareCertificates();

        EndpointDescription[] serverEndpoints = Arrays.stream(server.getEndpointDescriptions())
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

        if (secureChannel.getSecurityPolicy() != SecurityPolicy.None) {
            X509Certificate clientCertificate = CertificateUtil
                .decodeCertificate(clientCertificateBytes.bytes());

            if (!secureChannel.getRemoteCertificate().equals(clientCertificate)) {
                throw new UaException(StatusCodes.Bad_SecurityChecksFailed,
                    "certificate used to open secure channel " +
                        "differs from certificate used to create session");
            }

            validateApplicationUri(request.getClientDescription().getApplicationUri(), clientCertificate);
        }

        // clientCertificateBytes may contain the bytes of the entire chain, but the
        // signature must be created using only the bytes of the leaf certificate,
        // so use the certificate bytes from secure channel instead.
        SignatureData serverSignature = getServerSignature(
            clientNonce,
            secureChannel.getRemoteCertificateBytes(),
            securityPolicy,
            secureChannel.getKeyPair()
        );

        NodeId sessionId = new NodeId(1, "Session:" + UUID.randomUUID());
        String sessionName = request.getSessionName();
        Duration sessionTimeout = Duration.ofMillis(DoubleMath.roundToLong(revisedSessionTimeout, RoundingMode.UP));
        Session session = new Session(server, sessionId, sessionName, sessionTimeout, secureChannel.getChannelId());
        createdSessions.put(authenticationToken, session);

        session.addLifecycleListener((s, remove) -> {
            createdSessions.remove(authenticationToken);
            activeSessions.remove(authenticationToken);
        });

        session.setLastNonce(serverNonce);

        CreateSessionResponse response = new CreateSessionResponse(
            serviceRequest.createResponseHeader(),
            sessionId,
            authenticationToken,
            revisedSessionTimeout,
            serverNonce,
            serverCertificate,
            serverEndpoints,
            serverSoftwareCertificates,
            serverSignature,
            uint(maxRequestMessageSize)
        );

        serviceRequest.setResponse(response);
    }

    private boolean endpointMatchesUrl(EndpointDescription endpoint, String endpointUrl) {
        try {
            String requestedHost = new URI(endpointUrl).parseServerAuthority().getHost();
            String endpointHost = new URI(endpoint.getEndpointUrl()).parseServerAuthority().getHost();

            return requestedHost.equalsIgnoreCase(endpointHost);
        } catch (Throwable e) {
            logger.warn("Unable to create URI.", e);
            return false;
        }
    }

    /**
     * Validate that the application URI matches the SubjectAltName URI in the given certificate.
     *
     * @param applicationUri the URI to match.
     * @param certificate    the certificate to match against.
     * @throws UaException if the certificate is invalid, does not contain a uri, or contains a uri that does not match.
     */
    private void validateApplicationUri(String applicationUri, X509Certificate certificate) throws UaException {
        try {
            Collection<List<?>> subjectAltNames = certificate.getSubjectAlternativeNames();
            if (subjectAltNames == null) subjectAltNames = Collections.emptyList();

            for (List<?> idAndValue : subjectAltNames) {
                if (idAndValue != null && idAndValue.size() == 2) {
                    if (idAndValue.get(0).equals(6)) {
                        String certificateUri = (String) idAndValue.get(1);
                        if (!applicationUri.equals(certificateUri)) {
                            String message = String.format(
                                "Certificate URI does not match. certificateUri=%s, applicationUri=%s",
                                certificateUri, applicationUri);

                            logger.warn(message);

                            throw new UaException(StatusCodes.Bad_CertificateUriInvalid, message);
                        }
                        return;
                    }
                }
            }

            String message = "Certificate does not contain a SubjectAlternativeName URI entry.";

            throw new UaException(StatusCodes.Bad_CertificateUriInvalid, message);
        } catch (CertificateParsingException e) {
            logger.warn("Error parsing client certificate.", e);

            throw new UaException(StatusCodes.Bad_CertificateInvalid);
        }
    }

    @Override
    public void onActivateSession(
        ServiceRequest<ActivateSessionRequest, ActivateSessionResponse> serviceRequest) throws UaException {

        ActivateSessionRequest request = serviceRequest.getRequest();

        ServerSecureChannel secureChannel = serviceRequest.getSecureChannel();
        long secureChannelId = secureChannel.getChannelId();
        NodeId authToken = request.getRequestHeader().getAuthenticationToken();
        List<SignedSoftwareCertificate> clientSoftwareCertificates = l(request.getClientSoftwareCertificates());

        Session session = createdSessions.get(authToken);

        if (session == null) {
            session = activeSessions.get(authToken);

            if (session == null) {
                throw new UaException(StatusCodes.Bad_SessionIdInvalid);
            } else {

                verifyClientSignature(request, secureChannel, session);

                if (session.getSecureChannelId() == secureChannelId) {
                    /*
                     * Identity change
                     */
                    Object tokenObject = request.getUserIdentityToken().decode();
                    Object identityObject = validateIdentityToken(
                        secureChannel,
                        session,
                        tokenObject,
                        request.getUserTokenSignature()
                    );
                    session.setIdentityObject(identityObject);

                    StatusCode[] results = new StatusCode[clientSoftwareCertificates.size()];
                    Arrays.fill(results, StatusCode.GOOD);

                    ByteString serverNonce = NonceUtil.generateNonce(32);

                    session.setLastNonce(serverNonce);

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
                    ByteString certificateBytes = secureChannel.getRemoteCertificateBytes();

                    if (request.getUserIdentityToken() == null || request.getUserIdentityToken().decode() == null) {
                        throw new UaException(StatusCodes.Bad_IdentityTokenInvalid, "identity token not provided");
                    }

                    Object tokenObject = request.getUserIdentityToken().decode();
                    Object identityObject = validateIdentityToken(
                        secureChannel,
                        session,
                        tokenObject,
                        request.getUserTokenSignature()
                    );

                    if (identityObject.equals(session.getIdentityObject()) &&
                        certificateBytes.equals(session.getClientCertificateBytes())) {

                        session.setSecureChannelId(secureChannelId);

                        logger.debug("Session id={} is now associated with secureChannelId={}",
                            session.getSessionId(), secureChannelId);

                        StatusCode[] results = new StatusCode[clientSoftwareCertificates.size()];
                        Arrays.fill(results, StatusCode.GOOD);

                        ByteString serverNonce = NonceUtil.generateNonce(32);

                        session.setLastNonce(serverNonce);

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

            if (request.getUserIdentityToken() == null || request.getUserIdentityToken().decode() == null) {
                throw new UaException(StatusCodes.Bad_IdentityTokenInvalid, "identity token not provided");
            }

            verifyClientSignature(request, secureChannel, session);

            Object tokenObject = request.getUserIdentityToken().decode();
            Object identityObject = validateIdentityToken(
                secureChannel,
                session,
                tokenObject,
                request.getUserTokenSignature()
            );
            session.setIdentityObject(identityObject);

            createdSessions.remove(authToken);
            activeSessions.put(authToken, session);

            session.setClientCertificateBytes(secureChannel.getRemoteCertificateBytes());

            StatusCode[] results = new StatusCode[clientSoftwareCertificates.size()];
            Arrays.fill(results, StatusCode.GOOD);

            ByteString serverNonce = NonceUtil.generateNonce(32);

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

    private void verifyClientSignature(
        ActivateSessionRequest request,
        ServerSecureChannel secureChannel,
        Session session) throws UaException {

        if (secureChannel.getSecurityPolicy() != SecurityPolicy.None) {
            SignatureData clientSignature = request.getClientSignature();

            byte[] dataBytes = Bytes.concat(
                secureChannel.getLocalCertificateBytes().bytesOrEmpty(),
                session.getLastNonce().bytesOrEmpty()
            );

            byte[] signatureBytes = clientSignature.getSignature().bytesOrEmpty();

            SignatureUtil.verify(
                SecurityAlgorithm.fromUri(clientSignature.getAlgorithm()),
                secureChannel.getRemoteCertificate(),
                dataBytes,
                signatureBytes
            );
        }
    }

    private Object validateIdentityToken(
        ServerSecureChannel channel,
        Session session,
        Object tokenObject,
        SignatureData tokenSignature) throws UaException {

        IdentityValidator identityValidator = server.getConfig().getIdentityValidator();
        UserTokenPolicy tokenPolicy = validatePolicyId(tokenObject);

        if (tokenObject instanceof UserIdentityToken) {
            return identityValidator.validateIdentityToken(
                channel,
                session,
                (UserIdentityToken) tokenObject,
                tokenPolicy,
                tokenSignature
            );
        } else {
            throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
        }
    }

    private UserTokenPolicy validatePolicyId(Object tokenObject) throws UaException {
        if (tokenObject instanceof UserIdentityToken) {
            UserIdentityToken token = (UserIdentityToken) tokenObject;
            String policyId = token.getPolicyId();

            for (UserTokenPolicy policy : server.getUserTokenPolicies()) {
                if (Objects.equal(policyId, policy.getPolicyId())) {
                    return policy;
                }
            }

            throw new UaException(StatusCodes.Bad_IdentityTokenInvalid, "policy not found: " + policyId);
        } else {
            throw new UaException(StatusCodes.Bad_IdentityTokenInvalid);
        }
    }

    @Override
    public void onCloseSession(ServiceRequest<CloseSessionRequest, CloseSessionResponse> service) throws UaException {
        long secureChannelId = service.getSecureChannel().getChannelId();
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
    public void onCancel(ServiceRequest<CancelRequest, CancelResponse> service) throws UaException {
        session(service).onCancel(service);
    }

    private SignatureData getServerSignature(ByteString clientNonce,
                                             ByteString clientCertificate,
                                             SecurityPolicy securityPolicy,
                                             KeyPair keyPair) throws UaException {

        if (clientNonce.isNull() || clientCertificate.isNull() || keyPair == null) {
            return new SignatureData(null, null);
        }

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
    //endregion

    //region Attribute Services
    @Override
    public void onRead(ServiceRequest<ReadRequest, ReadResponse> service) throws UaException {
        Session session = session(service);

        session.getAttributeServices().onRead(service);
    }

    @Override
    public void onWrite(ServiceRequest<WriteRequest, WriteResponse> service) throws UaException {
        Session session = session(service);

        session.getAttributeServices().onWrite(service);
    }

    @Override
    public void onHistoryRead(ServiceRequest<HistoryReadRequest, HistoryReadResponse> service) throws UaException {
        Session session = session(service);

        session.getAttributeHistoryServices().onHistoryRead(service);
    }

    @Override
    public void onHistoryUpdate(
        ServiceRequest<HistoryUpdateRequest, HistoryUpdateResponse> service) throws UaException {

        Session session = session(service);

        session.getAttributeHistoryServices().onHistoryUpdate(service);
    }
    //endregion

    //region View Services
    @Override
    public void onBrowse(ServiceRequest<BrowseRequest, BrowseResponse> service) throws UaException {
        Session session = session(service);

        session.getViewServices().onBrowse(service);
    }

    @Override
    public void onBrowseNext(ServiceRequest<BrowseNextRequest, BrowseNextResponse> service) throws UaException {
        Session session = session(service);

        session.getViewServices().onBrowseNext(service);
    }

    @Override
    public void onTranslateBrowsePaths(
        ServiceRequest<TranslateBrowsePathsToNodeIdsRequest, TranslateBrowsePathsToNodeIdsResponse> service)
        throws UaException {

        Session session = session(service);

        session.getViewServices().onTranslateBrowsePaths(service);
    }

    @Override
    public void onRegisterNodes(
        ServiceRequest<RegisterNodesRequest, RegisterNodesResponse> service) throws UaException {

        Session session = session(service);

        session.getViewServices().onRegisterNodes(service);
    }

    @Override
    public void onUnregisterNodes(
        ServiceRequest<UnregisterNodesRequest, UnregisterNodesResponse> service) throws UaException {

        Session session = session(service);

        session.getViewServices().onUnregisterNodes(service);
    }
    //endregion

    //region NodeManagement Services
    @Override
    public void onAddNodes(ServiceRequest<AddNodesRequest, AddNodesResponse> service) throws UaException {
        Session session = session(service);

        session.getNodeManagementServices().onAddNodes(service);
    }

    @Override
    public void onAddReferences(
        ServiceRequest<AddReferencesRequest, AddReferencesResponse> service) throws UaException {

        Session session = session(service);

        session.getNodeManagementServices().onAddReferences(service);
    }

    @Override
    public void onDeleteNodes(ServiceRequest<DeleteNodesRequest, DeleteNodesResponse> service) throws UaException {
        Session session = session(service);

        session.getNodeManagementServices().onDeleteNodes(service);
    }

    @Override
    public void onDeleteReferences(
        ServiceRequest<DeleteReferencesRequest, DeleteReferencesResponse> service) throws UaException {

        Session session = session(service);

        session.getNodeManagementServices().onDeleteReferences(service);
    }
    //endregion

    //region Subscription Services
    @Override
    public void onCreateSubscription(
        ServiceRequest<CreateSubscriptionRequest, CreateSubscriptionResponse> service) throws UaException {

        Session session = session(service);

        session.getSubscriptionServices().onCreateSubscription(service);
    }

    @Override
    public void onModifySubscription(
        ServiceRequest<ModifySubscriptionRequest, ModifySubscriptionResponse> service) throws UaException {

        Session session = session(service);

        session.getSubscriptionServices().onModifySubscription(service);
    }

    @Override
    public void onSetPublishingMode(
        ServiceRequest<SetPublishingModeRequest, SetPublishingModeResponse> service) throws UaException {

        Session session = session(service);

        session.getSubscriptionServices().onSetPublishingMode(service);
    }

    @Override
    public void onPublish(ServiceRequest<PublishRequest, PublishResponse> service) throws UaException {
        Session session = session(service);

        session.getSubscriptionServices().onPublish(service);
    }

    @Override
    public void onRepublish(ServiceRequest<RepublishRequest, RepublishResponse> service) throws UaException {
        Session session = session(service);

        session.getSubscriptionServices().onRepublish(service);
    }

    @Override
    public void onTransferSubscriptions(
        ServiceRequest<TransferSubscriptionsRequest, TransferSubscriptionsResponse> service) throws UaException {

        Session session = session(service);

        session.getSubscriptionServices().onTransferSubscriptions(service);
    }

    @Override
    public void onDeleteSubscriptions(
        ServiceRequest<DeleteSubscriptionsRequest, DeleteSubscriptionsResponse> service) throws UaException {

        Session session = session(service);

        session.getSubscriptionServices().onDeleteSubscriptions(service);
    }
    //endregion

    //region MonitoredItem Services
    @Override
    public void onCreateMonitoredItems(
        ServiceRequest<CreateMonitoredItemsRequest, CreateMonitoredItemsResponse> service) throws UaException {

        Session session = session(service);

        session.getMonitoredItemServices().onCreateMonitoredItems(service);
    }

    @Override
    public void onModifyMonitoredItems(
        ServiceRequest<ModifyMonitoredItemsRequest, ModifyMonitoredItemsResponse> service) throws UaException {

        Session session = session(service);

        session.getMonitoredItemServices().onModifyMonitoredItems(service);
    }

    @Override
    public void onSetMonitoringMode(
        ServiceRequest<SetMonitoringModeRequest, SetMonitoringModeResponse> service) throws UaException {

        Session session = session(service);

        session.getMonitoredItemServices().onSetMonitoringMode(service);
    }

    @Override
    public void onSetTriggering(
        ServiceRequest<SetTriggeringRequest, SetTriggeringResponse> service) throws UaException {

        Session session = session(service);

        session.getMonitoredItemServices().onSetTriggering(service);
    }

    @Override
    public void onDeleteMonitoredItems(
        ServiceRequest<DeleteMonitoredItemsRequest, DeleteMonitoredItemsResponse> service) throws UaException {

        Session session = session(service);

        session.getMonitoredItemServices().onDeleteMonitoredItems(service);
    }
    //endregion

    //region Method Services
    @Override
    public void onCall(ServiceRequest<CallRequest, CallResponse> service) throws UaException {
        Session session = session(service);

        session.getMethodServices().onCall(service);
    }
    //endregion

    //region Query Services
    @Override
    public void onQueryFirst(ServiceRequest<QueryFirstRequest, QueryFirstResponse> service) throws UaException {
        Session session = session(service);

        session.getQueryServices().onQueryFirst(service);
    }

    @Override
    public void onQueryNext(ServiceRequest<QueryNextRequest, QueryNextResponse> service) throws UaException {
        Session session = session(service);

        session.getQueryServices().onQueryNext(service);
    }
    //endregion

}
