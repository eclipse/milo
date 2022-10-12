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

import java.security.KeyPair;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceManager;
import org.eclipse.milo.opcua.sdk.server.api.EventListener;
import org.eclipse.milo.opcua.sdk.server.api.EventNotifier;
import org.eclipse.milo.opcua.sdk.server.api.config.EndpointConfig;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.diagnostics.ServerDiagnosticsSummary;
import org.eclipse.milo.opcua.sdk.server.model.ObjectTypeInitializer;
import org.eclipse.milo.opcua.sdk.server.model.VariableTypeInitializer;
import org.eclipse.milo.opcua.sdk.server.model.objects.BaseEventTypeNode;
import org.eclipse.milo.opcua.sdk.server.namespaces.OpcUaNamespace;
import org.eclipse.milo.opcua.sdk.server.namespaces.ServerNamespace;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.EventFactory;
import org.eclipse.milo.opcua.sdk.server.services.Service;
import org.eclipse.milo.opcua.sdk.server.services.impl.DefaultAttributeServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.impl.DefaultDiscoveryServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.impl.DefaultMethodServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.impl.DefaultMonitoredItemServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.impl.DefaultNodeManagementServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.impl.DefaultSessionServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.impl.DefaultSubscriptionServiceSet;
import org.eclipse.milo.opcua.sdk.server.services.impl.DefaultViewServiceSet;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.BuiltinReferenceType;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.ReferenceType;
import org.eclipse.milo.opcua.stack.core.ServerTable;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.channel.EncodingLimits;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.encoding.DefaultEncodingManager;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.transport.TransportProfile;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.DefaultDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ApplicationDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.types.structured.UserTokenPolicy;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.core.util.Lazy;
import org.eclipse.milo.opcua.stack.core.util.ManifestUtil;
import org.eclipse.milo.opcua.stack.transport.server.OpcServerTransport;
import org.eclipse.milo.opcua.stack.transport.server.OpcServerTransportFactory;
import org.eclipse.milo.opcua.stack.transport.server.ServerApplication;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.stream.Collectors.toList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.util.ConversionUtil.a;

public class OpcUaServer extends AbstractServiceHandler implements ServerApplication {

    public static final String SDK_VERSION =
        ManifestUtil.read("X-SDK-Version").orElse("dev");

    static {
        Logger logger = LoggerFactory.getLogger(OpcUaServer.class);
        logger.info("Java version: " + System.getProperty("java.version"));
        logger.info("Eclipse Milo OPC UA Stack version: {}", Stack.VERSION);
        logger.info("Eclipse Milo OPC UA Server SDK version: {}", SDK_VERSION);
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final Lazy<ApplicationDescription> applicationDescription = new Lazy<>();

    private final Map<UInteger, Subscription> subscriptions = new ConcurrentHashMap<>();
    private final AtomicLong monitoredItemCount = new AtomicLong(0L);

    private final NamespaceTable namespaceTable = new NamespaceTable();
    private final ServerTable serverTable = new ServerTable();

    private final AddressSpaceManager addressSpaceManager = new AddressSpaceManager(this);
    private final SessionManager sessionManager = new SessionManager(this);

    private final EncodingManager encodingManager =
        DefaultEncodingManager.createAndInitialize();

    private final ObjectTypeManager objectTypeManager = new ObjectTypeManager();
    private final VariableTypeManager variableTypeManager = new VariableTypeManager();

    private final DataTypeManager dataTypeManager =
        DefaultDataTypeManager.createAndInitialize(namespaceTable);
    private final Map<NodeId, ReferenceType> referenceTypes = new ConcurrentHashMap<>();

    private final Set<NodeId> registeredViews = Sets.newConcurrentHashSet();

    private final ServerDiagnosticsSummary diagnosticsSummary = new ServerDiagnosticsSummary(this);

    private final AtomicLong secureChannelIds = new AtomicLong();
    private final AtomicLong secureChannelTokenIds = new AtomicLong();

    private final EventBus eventBus = new EventBus("server");
    private final EventFactory eventFactory = new EventFactory(this);
    private final EventNotifier eventNotifier = new ServerEventNotifier();

    private final EncodingContext encodingContext;

    private final OpcUaNamespace opcUaNamespace;
    private final ServerNamespace serverNamespace;

    private final OpcUaServerConfig config;
    private final OpcServerTransportFactory transportFactory;

    public OpcUaServer(OpcUaServerConfig config, OpcServerTransportFactory transportFactory) {
        this.config = config;
        this.transportFactory = transportFactory;

        encodingContext = new EncodingContext() {
            @Override
            public DataTypeManager getDataTypeManager() {
                return dataTypeManager;
            }

            @Override
            public EncodingManager getEncodingManager() {
                return encodingManager;
            }

            @Override
            public EncodingLimits getEncodingLimits() {
                return config.getEncodingLimits();
            }

            @Override
            public NamespaceTable getNamespaceTable() {
                return namespaceTable;
            }

            @Override
            public ServerTable getServerTable() {
                return serverTable;
            }
        };

        Stream<String> paths = config.getEndpoints()
            .stream()
            .map(e -> EndpointUtil.getPath(e.getEndpointUrl()))
            .distinct();

        paths.forEach(path -> {
            addServiceSet(path, new DefaultDiscoveryServiceSet(OpcUaServer.this));

            if (!path.endsWith("/discovery")) {
                addServiceSet(path, new DefaultAttributeServiceSet(OpcUaServer.this));
                addServiceSet(path, new DefaultMethodServiceSet(OpcUaServer.this));
                addServiceSet(path, new DefaultMonitoredItemServiceSet(OpcUaServer.this));
                addServiceSet(path, new DefaultNodeManagementServiceSet(OpcUaServer.this));
                addServiceSet(path, new DefaultSessionServiceSet(OpcUaServer.this));
                addServiceSet(path, new DefaultSubscriptionServiceSet(OpcUaServer.this));
                addServiceSet(path, new DefaultViewServiceSet(OpcUaServer.this));
            }
        });

        ObjectTypeInitializer.initialize(namespaceTable, objectTypeManager);

        VariableTypeInitializer.initialize(namespaceTable, variableTypeManager);

        opcUaNamespace = new OpcUaNamespace(this);
        opcUaNamespace.startup();

        serverNamespace = new ServerNamespace(this);
        serverNamespace.startup();

        for (ReferenceType referenceType : BuiltinReferenceType.values()) {
            referenceTypes.put(referenceType.getNodeId(), referenceType);
        }
    }

    public OpcUaServerConfig getConfig() {
        return config;
    }

    public CompletableFuture<OpcUaServer> startup() {
        eventFactory.startup();

        config.getEndpoints()
            .stream()
            .sorted(Comparator.comparing(EndpointConfig::getTransportProfile))
            .forEach(endpoint -> {
                logger.info(
                    "Binding endpoint {} to {}:{} [{}/{}]",
                    endpoint.getEndpointUrl(),
                    endpoint.getBindAddress(),
                    endpoint.getBindPort(),
                    endpoint.getSecurityPolicy(),
                    endpoint.getSecurityMode()
                );

                TransportProfile transportProfile = endpoint.getTransportProfile();
                OpcServerTransport transport = transportFactory.create(transportProfile);

                if (transport != null) {
                    try {
                        transport.bind(OpcUaServer.this, endpoint.getBindAddress(), endpoint.getBindPort());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    logger.warn("No OpcServerTransport for TransportProfile: {}", transportProfile);
                }
            });

        return CompletableFuture.completedFuture(this);
    }

    public CompletableFuture<OpcUaServer> shutdown() {
        serverNamespace.shutdown();
        opcUaNamespace.shutdown();

        eventFactory.shutdown();

        subscriptions.values()
            .forEach(Subscription::deleteSubscription);

        // TODO unbind transports?

        return CompletableFuture.completedFuture(this);
    }

    public AddressSpaceManager getAddressSpaceManager() {
        return addressSpaceManager;
    }

    public SessionManager getSessionManager() {
        return sessionManager;
    }

    public OpcUaNamespace getOpcUaNamespace() {
        return opcUaNamespace;
    }

    public ServerNamespace getServerNamespace() {
        return serverNamespace;
    }

    public DataTypeManager getDataTypeManager() {
        return dataTypeManager;
    }

    public EncodingManager getEncodingManager() {
        return encodingManager;
    }

    public NamespaceTable getNamespaceTable() {
        return namespaceTable;
    }

    public ServerTable getServerTable() {
        return serverTable;
    }

    public EncodingContext getEncodingContext() {
        return encodingContext;
    }

    public ServerDiagnosticsSummary getDiagnosticsSummary() {
        return diagnosticsSummary;
    }

    /**
     * Get an internal EventBus used to decouple communication between internal components of the
     * Server implementation.
     * <p>
     * This EventBus is not intended for use by user implementations.
     *
     * @return an internal EventBus used to decouple communication between internal components of
     * the Server implementation.
     */
    public EventBus getInternalEventBus() {
        return eventBus;
    }

    /**
     * Get the shared {@link EventFactory}.
     *
     * @return the shared {@link EventFactory}.
     */
    public EventFactory getEventFactory() {
        return eventFactory;
    }

    /**
     * Get the Server's {@link EventNotifier}.
     *
     * @return the Server's {@link EventNotifier}.
     */
    public EventNotifier getEventNotifier() {
        return eventNotifier;
    }

    public ObjectTypeManager getObjectTypeManager() {
        return objectTypeManager;
    }

    public VariableTypeManager getVariableTypeManager() {
        return variableTypeManager;
    }

    public Set<NodeId> getRegisteredViews() {
        return registeredViews;
    }

    public Map<UInteger, Subscription> getSubscriptions() {
        return subscriptions;
    }

    public AtomicLong getMonitoredItemCount() {
        return monitoredItemCount;
    }

    public Optional<KeyPair> getKeyPair(ByteString thumbprint) {
        return config.getCertificateManager().getKeyPair(thumbprint);
    }

    public Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        return config.getCertificateManager().getCertificate(thumbprint);
    }

    public Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint) {
        return config.getCertificateManager().getCertificateChain(thumbprint);
    }

    public ExecutorService getExecutorService() {
        return config.getExecutor();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return config.getScheduledExecutorService();
    }

    public List<EndpointDescription> getEndpointDescriptions() {
        return config.getEndpoints()
            .stream()
            .map(this::transformEndpoint)
            .collect(Collectors.toUnmodifiableList());
    }

    public Map<NodeId, ReferenceType> getReferenceTypes() {
        return referenceTypes;
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
    public Long getNextSecureChannelId() {
        return secureChannelIds.getAndIncrement();
    }

    @Override
    public Long getNextSecureChannelTokenId() {
        return secureChannelTokenIds.getAndIncrement();
    }

    /**
     * Return {@code true} if {@code requestMessage} is one of the Discovery service requests:
     * <ul>
     *     <li>FindServersRequest</li>
     *     <li>GetEndpointsRequest</li>
     *     <li>RegisterServerRequest</li>
     *     <li>FindServersOnNetworkRequest</li>
     *     <li>RegisterServer2Request</li>
     * </ul>
     *
     * @param requestMessage the {@link UaRequestMessageType} to check.
     * @return {@code true} if {@code requestMessage} is one of the Discovery service requests.
     */
    private static boolean isDiscoveryService(UaRequestMessageType requestMessage) {
        UInteger id = (UInteger) requestMessage.getTypeId().getIdentifier();

        switch (id.intValue()) {
            case 420:   // FindServers
            case 426:   // GetEndpoints
            case 435:   // RegisterServer
            case 12190: // FindServersOnNetwork
            case 12193: // RegisterServer2
                return true;

            default:
                return false;
        }
    }

    @Override
    public CompletableFuture<UaResponseMessageType> handleServiceRequest(
        ServiceRequestContext context,
        UaRequestMessageType requestMessage
    ) {

        String path = EndpointUtil.getPath(context.getEndpointUrl());

        if (context.getSecureChannel().getSecurityPolicy() == SecurityPolicy.None) {
            if (getEndpointDescriptions().stream()
                .filter(e -> EndpointUtil.getPath(e.getEndpointUrl()).equals(path))
                .noneMatch(e -> e.getSecurityPolicyUri().equals(SecurityPolicy.None.getUri()))) {

                // TODO filter on transport profile as well?

                if (!isDiscoveryService(requestMessage)) {
                    var errorMessage = new ErrorMessage(
                        StatusCodes.Bad_SecurityPolicyRejected,
                        StatusCodes.lookup(StatusCodes.Bad_SecurityPolicyRejected)
                            .map(ss -> ss[1]).orElse("")
                    );

                    context.getChannel().pipeline().fireUserEventTriggered(errorMessage);

                    // won't complete, doesn't matter, we're closing down
                    return new CompletableFuture<>();
                }
            }
        }

        Service service = Service.from(requestMessage.getTypeId());

        ServiceHandler serviceHandler = service != null ? getServiceHandler(path, service) : null;

        if (serviceHandler != null) {
            return serviceHandler.handle(context, requestMessage);
        } else {
            logger.warn("No ServiceHandler registered for path={} service={}", path, service);

            return CompletableFuture.failedFuture(new UaException(StatusCodes.Bad_NotImplemented));
        }
    }


    private EndpointDescription transformEndpoint(EndpointConfig endpoint) {
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

    private ApplicationDescription getApplicationDescription() {
        return applicationDescription.getOrCompute(() -> {
            List<String> discoveryUrls = config.getEndpoints()
                .stream()
                .map(EndpointConfig::getEndpointUrl)
                .filter(url -> url.endsWith("/discovery"))
                .distinct()
                .collect(toList());

            if (discoveryUrls.isEmpty()) {
                discoveryUrls = config.getEndpoints()
                    .stream()
                    .map(EndpointConfig::getEndpointUrl)
                    .distinct()
                    .collect(toList());
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

    private static class ServerEventNotifier implements EventNotifier {

        private final List<EventListener> eventListeners = Collections.synchronizedList(new ArrayList<>());

        @Override
        public void fire(BaseEventTypeNode event) {
            List<EventListener> toNotify;
            synchronized (eventListeners) {
                toNotify = List.copyOf(eventListeners);
            }

            toNotify.forEach(eventListener -> eventListener.onEvent(event));
        }

        @Override
        public void register(EventListener eventListener) {
            eventListeners.add(eventListener);
        }

        @Override
        public void unregister(EventListener eventListener) {
            eventListeners.remove(eventListener);
        }

    }

}
