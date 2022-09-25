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
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

import com.google.common.collect.Sets;
import com.google.common.eventbus.EventBus;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceManager;
import org.eclipse.milo.opcua.sdk.server.api.EventListener;
import org.eclipse.milo.opcua.sdk.server.api.EventNotifier;
import org.eclipse.milo.opcua.sdk.server.api.config.OpcUaServerConfig;
import org.eclipse.milo.opcua.sdk.server.diagnostics.ServerDiagnosticsSummary;
import org.eclipse.milo.opcua.sdk.server.model.ObjectTypeInitializer;
import org.eclipse.milo.opcua.sdk.server.model.VariableTypeInitializer;
import org.eclipse.milo.opcua.sdk.server.model.objects.BaseEventTypeNode;
import org.eclipse.milo.opcua.sdk.server.namespaces.OpcUaNamespace;
import org.eclipse.milo.opcua.sdk.server.namespaces.ServerNamespace;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.EventFactory;
import org.eclipse.milo.opcua.sdk.server.services2.Service;
import org.eclipse.milo.opcua.sdk.server.services2.impl.DefaultAttributeServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.impl.DefaultMethodServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.impl.DefaultMonitoredItemServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.impl.DefaultNodeManagementServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.impl.DefaultSubscriptionServiceSet2;
import org.eclipse.milo.opcua.sdk.server.services2.impl.DefaultViewServiceSet2;
import org.eclipse.milo.opcua.sdk.server.subscriptions.Subscription;
import org.eclipse.milo.opcua.stack.core.BuiltinReferenceType;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.ReferenceType;
import org.eclipse.milo.opcua.stack.core.ServerTable;
import org.eclipse.milo.opcua.stack.core.Stack;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.channel.messages.ErrorMessage;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateManager;
import org.eclipse.milo.opcua.stack.core.security.CertificateValidator;
import org.eclipse.milo.opcua.stack.core.security.SecurityPolicy;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.UaRequestMessageType;
import org.eclipse.milo.opcua.stack.core.types.UaResponseMessageType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.EndpointDescription;
import org.eclipse.milo.opcua.stack.core.util.EndpointUtil;
import org.eclipse.milo.opcua.stack.core.util.ManifestUtil;
import org.eclipse.milo.opcua.stack.server.UaStackServer;
import org.eclipse.milo.opcua.stack.server.services.AttributeHistoryServiceSet;
import org.eclipse.milo.opcua.stack.server.services.AttributeServiceSet;
import org.eclipse.milo.opcua.stack.server.services.MethodServiceSet;
import org.eclipse.milo.opcua.stack.server.services.MonitoredItemServiceSet;
import org.eclipse.milo.opcua.stack.server.services.NodeManagementServiceSet;
import org.eclipse.milo.opcua.stack.server.services.SessionServiceSet;
import org.eclipse.milo.opcua.stack.server.services.SubscriptionServiceSet;
import org.eclipse.milo.opcua.stack.server.services.ViewServiceSet;
import org.eclipse.milo.opcua.stack.transport.server.ServerApplication;
import org.eclipse.milo.opcua.stack.transport.server.ServiceRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private final Map<NodeId, ReferenceType> referenceTypes = new ConcurrentHashMap<>();

    private final Map<UInteger, Subscription> subscriptions = new ConcurrentHashMap<>();

    private final AtomicLong monitoredItemCount = new AtomicLong(0L);

    private final AddressSpaceManager addressSpaceManager = new AddressSpaceManager(this);
    private final SessionManager sessionManager = new SessionManager(this);
    private final ObjectTypeManager objectTypeManager = new ObjectTypeManager();
    private final VariableTypeManager variableTypeManager = new VariableTypeManager();

    private final Set<NodeId> registeredViews = Sets.newConcurrentHashSet();

    private final ServerDiagnosticsSummary diagnosticsSummary = new ServerDiagnosticsSummary(this);

    private final AtomicLong secureChannelIds = new AtomicLong();
    private final AtomicLong secureChannelTokenIds = new AtomicLong();

    private final EventBus eventBus = new EventBus("server");
    private final EventFactory eventFactory = new EventFactory(this);
    private final EventNotifier eventNotifier = new ServerEventNotifier();

    private final UaStackServer stackServer;

    private final OpcUaNamespace opcUaNamespace;
    private final ServerNamespace serverNamespace;

    private final OpcUaServerConfig config;

    public OpcUaServer(OpcUaServerConfig config) {
        this.config = config;

        stackServer = new UaStackServer(config);

        Stream<String> paths = stackServer.getConfig().getEndpoints()
            .stream()
            .map(e -> EndpointUtil.getPath(e.getEndpointUrl()))
            .distinct();

        paths.filter(path -> !path.endsWith("/discovery")).forEach(path -> {
            stackServer.addServiceSet(path, (AttributeServiceSet) sessionManager);
            stackServer.addServiceSet(path, (AttributeHistoryServiceSet) sessionManager);
            stackServer.addServiceSet(path, (MethodServiceSet) sessionManager);
            stackServer.addServiceSet(path, (MonitoredItemServiceSet) sessionManager);
            stackServer.addServiceSet(path, (NodeManagementServiceSet) sessionManager);
            stackServer.addServiceSet(path, (SessionServiceSet) sessionManager);
            stackServer.addServiceSet(path, (SubscriptionServiceSet) sessionManager);
            stackServer.addServiceSet(path, (ViewServiceSet) sessionManager);
        });

        paths.filter(path -> !path.endsWith("/discovery")).forEach(path -> {
            addServiceSet(path, new DefaultAttributeServiceSet2(OpcUaServer.this));
            addServiceSet(path, new DefaultMethodServiceSet2(OpcUaServer.this));
            addServiceSet(path, new DefaultMonitoredItemServiceSet2(OpcUaServer.this));
            addServiceSet(path, new DefaultNodeManagementServiceSet2(OpcUaServer.this));
            addServiceSet(path, sessionManager);
            addServiceSet(path, new DefaultSubscriptionServiceSet2(OpcUaServer.this));
            addServiceSet(path, new DefaultViewServiceSet2(OpcUaServer.this));
        });

        ObjectTypeInitializer.initialize(stackServer.getNamespaceTable(), objectTypeManager);

        VariableTypeInitializer.initialize(stackServer.getNamespaceTable(), variableTypeManager);

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

        return stackServer.startup()
            .thenApply(s -> OpcUaServer.this);
    }

    public CompletableFuture<OpcUaServer> shutdown() {
        serverNamespace.shutdown();
        opcUaNamespace.shutdown();

        eventFactory.shutdown();

        subscriptions.values()
            .forEach(Subscription::deleteSubscription);

        return stackServer.shutdown()
            .thenApply(s -> OpcUaServer.this);
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
        return stackServer.getDataTypeManager();
    }

    public EncodingManager getEncodingManager() {
        return stackServer.getEncodingManager();
    }

    public NamespaceTable getNamespaceTable() {
        return stackServer.getNamespaceTable();
    }

    public ServerTable getServerTable() {
        return stackServer.getServerTable();
    }

    public EncodingContext getEncodingContext() {
        return stackServer.getEncodingContext();
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
        return stackServer.getConfig().getCertificateManager().getKeyPair(thumbprint);
    }

    public Optional<X509Certificate> getCertificate(ByteString thumbprint) {
        return stackServer.getConfig().getCertificateManager().getCertificate(thumbprint);
    }

    public Optional<X509Certificate[]> getCertificateChain(ByteString thumbprint) {
        return stackServer.getConfig().getCertificateManager().getCertificateChain(thumbprint);
    }

    public ExecutorService getExecutorService() {
        return stackServer.getConfig().getExecutor();
    }

    public ScheduledExecutorService getScheduledExecutorService() {
        return config.getScheduledExecutorService();
    }

    @Override
    public List<EndpointDescription> getEndpointDescriptions() {
        return stackServer.getEndpointDescriptions();
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

        ServiceHandler serviceHandler = service != null ?
            getServiceHandler(path, service) : null;

        if (serviceHandler != null) {
            return serviceHandler.handle(context, requestMessage);
        } else {
            // TODO ServiceFault Bad_NotImplemented
        }
        return null;
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
