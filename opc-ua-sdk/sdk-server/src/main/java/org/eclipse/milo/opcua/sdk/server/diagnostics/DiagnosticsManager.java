/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.SessionListener;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceFilter;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedAddressSpace;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.SimpleAddressSpaceFilter;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionDiagnosticsObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionsDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ServerDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.ComplexVariableNodeAttributeDelegates;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagnosticsManager extends ManagedAddressSpace {

    private ServerDiagnosticsObject serverDiagnosticsObject;

    public DiagnosticsManager(OpcUaServer server) {
        super(server);
    }

    @Override
    protected void onStartup() {
        super.onStartup();

        ServerDiagnosticsTypeNode serverDiagnosticsTypeNode = (ServerDiagnosticsTypeNode) getServer()
            .getAddressSpaceManager()
            .getManagedNode(Identifiers.Server_ServerDiagnostics)
            .orElseThrow(() -> new NoSuchElementException("NodeId: " + Identifiers.Server_ServerDiagnostics));

        serverDiagnosticsObject = new ServerDiagnosticsObject(serverDiagnosticsTypeNode);
        serverDiagnosticsObject.startup();
    }

    @Override
    protected void onShutdown() {
        super.onShutdown();

        if (serverDiagnosticsObject != null) {
            serverDiagnosticsObject.shutdown();
            serverDiagnosticsObject = null;
        }
    }

    @Override
    public AddressSpaceFilter getFilter() {
        return SimpleAddressSpaceFilter.create(
            nodeId ->
                getNodeManager().containsNode(nodeId)
        );
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {

    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {

    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {

    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {

    }

    class ServerDiagnosticsObject extends AbstractLifecycle {

        private ServerDiagnosticsSummaryVariable serverDiagnosticsSummaryVariable;

        private final ServerDiagnosticsTypeNode node;

        public ServerDiagnosticsObject(ServerDiagnosticsTypeNode node) {
            this.node = node;
        }

        @Override
        protected void onStartup() {
            serverDiagnosticsSummaryVariable = new ServerDiagnosticsSummaryVariable(
                node.getServerDiagnosticsSummaryNode()
            );

            serverDiagnosticsSummaryVariable.startup();

        }

        @Override
        protected void onShutdown() {
            if (serverDiagnosticsSummaryVariable != null) {
                serverDiagnosticsSummaryVariable.shutdown();
                serverDiagnosticsSummaryVariable = null;
            }
        }

    }

    class SessionsDiagnosticsSummaryObject extends AbstractLifecycle {

        // HasComponent SessionDiagnosticsArray
        // HasComponent SessionSecurityDiagnosticsArray
        // HasComponent SessionDiagnosticsObjectType (per Session)

        private final Map<NodeId, SessionDiagnosticsObject> sessionDiagnosticsObjects = Maps.newConcurrentMap();

        private SessionListener sessionListener;

        @Override
        protected void onStartup() {
            getServer().getSessionManager().addSessionListener(sessionListener = new SessionListener() {
                @Override
                public void onSessionCreated(Session session) {
                    // TODO diagnostics: pass summaryNode
                    SessionDiagnosticsObject sdo = new SessionDiagnosticsObject(session, null);

                    sessionDiagnosticsObjects.put(session.getSessionId(), sdo);

                    sdo.startup();
                }

                @Override
                public void onSessionClosed(Session session) {
                    SessionDiagnosticsObject sdo = sessionDiagnosticsObjects.remove(session.getSessionId());

                    if (sdo != null) { sdo.shutdown(); }
                }
            });
        }

        @Override
        protected void onShutdown() {
            if (sessionListener != null) {
                getServer().getSessionManager().removeSessionListener(sessionListener);
            }
        }

    }

    // ServerDiagnosticsType -> SessionDiagnosticsSummaryType -> SessionDiagnosticsObjectType (per session)
    class SessionDiagnosticsObject extends AbstractLifecycle {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private SessionDiagnosticsObjectTypeNode node;

        private final Session session;
        private final SessionsDiagnosticsSummaryTypeNode summaryNode;

        public SessionDiagnosticsObject(Session session, SessionsDiagnosticsSummaryTypeNode summaryNode) {
            this.session = session;
            this.summaryNode = summaryNode;
        }

        @Override
        protected void onStartup() {
            try {
                String name = String.format(
                    "%s (%s)",
                    session.getSessionName(),
                    session.getSessionId()
                );

                node = (SessionDiagnosticsObjectTypeNode) getNodeFactory().createNode(
                    new NodeId(1, UUID.randomUUID()),
                    Identifiers.SessionDiagnosticsObjectType,
                    false
                );
                node.setBrowseName(new QualifiedName(1, name));
                node.setDisplayName(LocalizedText.english(name));

                getNodeManager().addNode(node);
                summaryNode.addComponent(node);

                node.getSessionDiagnosticsNode().setAttributeDelegate(new AttributeDelegate() {
                    @Override
                    public DataValue getValue(AttributeContext context, VariableNode node) {
                        ExtensionObject encodedValue = ExtensionObject.encode(
                            getServer().getSerializationContext(),
                            session.getSessionDiagnostics()
                                .getSessionDiagnosticsDataType()
                        );

                        return new DataValue(new Variant(encodedValue));
                    }
                });

                node.getSessionSecurityDiagnosticsNode().setAttributeDelegate(new AttributeDelegate() {
                    @Override
                    public DataValue getValue(AttributeContext context, VariableNode node) {
                        ExtensionObject encodedValue = ExtensionObject.encode(
                            getServer().getSerializationContext(),
                            session.getSessionSecurityDiagnostics()
                                .getSessionSecurityDiagnosticsDataType()
                        );

                        return new DataValue(new Variant(encodedValue));
                    }
                });

                node.getSubscriptionDiagnosticsArrayNode().setAttributeDelegate(new AttributeDelegate() {
                    @Override
                    public DataValue getValue(AttributeContext context, VariableNode node) {
                        ExtensionObject[] encodedValues = session.getSubscriptionManager()
                            .getSubscriptions()
                            .stream()
                            .map(s ->
                                ExtensionObject.encode(
                                    getServer().getSerializationContext(),
                                    s.getSubscriptionDiagnostics()
                                        .getSubscriptionDiagnosticsDataType()
                                ))
                            .toArray(ExtensionObject[]::new);

                        return new DataValue(new Variant(encodedValues));
                    }
                });
            } catch (UaException e) {
                logger.error("Error starting SessionDiagnosticsObject: {}", e.getMessage(), e);
            }
        }

        @Override
        protected void onShutdown() {
            if (node != null) {
                node.delete();
            }
        }

    }

    class ServerDiagnosticsSummaryVariable extends AbstractLifecycle {

        private final ServerDiagnosticsSummaryTypeNode node;

        ServerDiagnosticsSummaryVariable(ServerDiagnosticsSummaryTypeNode node) {
            this.node = node;
        }

        @Override
        protected void onStartup() {
            ComplexVariableNodeAttributeDelegates.install(
                node,
                ServerDiagnosticsSummaryDataType.class,
                this::getValue
            );
        }

        @Override
        protected void onShutdown() {
            node.setAttributeDelegate(AttributeDelegate.DEFAULT);
        }

        private ServerDiagnosticsSummaryDataType getValue() {
            return getServer().getDiagnosticsSummary().getServerDiagnosticsSummaryDataType();
        }

    }

}
