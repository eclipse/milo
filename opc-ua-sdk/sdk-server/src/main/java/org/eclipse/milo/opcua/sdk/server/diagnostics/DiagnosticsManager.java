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

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.SessionListener;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionDiagnosticsObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionsDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagnosticsManager extends AbstractLifecycle {

    private ServerDiagnosticsObject serverDiagnosticsObject;

    private final OpcUaServer server;
    private final NodeFactory nodeFactory;
    private final UaNodeManager nodeManager;

    public DiagnosticsManager(OpcUaServer server, NodeFactory nodeFactory, UaNodeManager nodeManager) {
        this.server = server;
        this.nodeFactory = nodeFactory;
        this.nodeManager = nodeManager;
    }

    public OpcUaServer getServer() {
        return server;
    }

    @Override
    protected void onStartup() {
        ServerDiagnosticsTypeNode serverDiagnosticsTypeNode = (ServerDiagnosticsTypeNode) getServer()
            .getAddressSpaceManager()
            .getManagedNode(Identifiers.Server_ServerDiagnostics)
            .orElseThrow(() -> new NoSuchElementException("NodeId: " + Identifiers.Server_ServerDiagnostics));

        serverDiagnosticsObject = new ServerDiagnosticsObject(serverDiagnosticsTypeNode);
        serverDiagnosticsObject.startup();
    }

    @Override
    protected void onShutdown() {
        if (serverDiagnosticsObject != null) {
            serverDiagnosticsObject.shutdown();
            serverDiagnosticsObject = null;
        }
    }

    class ServerDiagnosticsObject extends AbstractLifecycle {

        private SessionsDiagnosticsSummaryObject sessionsDiagnosticsSummaryObject;

        private final ServerDiagnosticsTypeNode node;

        ServerDiagnosticsObject(ServerDiagnosticsTypeNode node) {
            this.node = node;
        }

        @Override
        protected void onStartup() {
            node.getServerDiagnosticsSummaryNode().setAttributeDelegate(new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) {
                    ExtensionObject encodedValue = ExtensionObject.encode(
                        getServer().getSerializationContext(),
                        getServer().getDiagnosticsSummary()
                            .getServerDiagnosticsSummaryDataType()
                    );

                    return new DataValue(new Variant(encodedValue));
                }
            });

            node.getSubscriptionDiagnosticsArrayNode().setAttributeDelegate(new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) {
                    ExtensionObject[] encodedValues = getServer().getSubscriptions()
                        .values()
                        .stream()
                        .map(s ->
                            ExtensionObject.encode(
                                getServer().getSerializationContext(),
                                s.getSubscriptionDiagnostics()
                                    .getSubscriptionDiagnosticsDataType()
                            )
                        )
                        .toArray(ExtensionObject[]::new);

                    return new DataValue(new Variant(encodedValues));
                }
            });

            node.getEnabledFlagNode().setAttributeDelegate(new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) {
                    return null;
                }

                @Override
                public void setValue(AttributeContext context, VariableNode node, DataValue value) {

                }
            });

            sessionsDiagnosticsSummaryObject = new SessionsDiagnosticsSummaryObject(
                node.getSessionsDiagnosticsSummaryNode()
            );
            sessionsDiagnosticsSummaryObject.startup();
        }

        @Override
        protected void onShutdown() {
            if (sessionsDiagnosticsSummaryObject != null) {
                sessionsDiagnosticsSummaryObject.shutdown();
                sessionsDiagnosticsSummaryObject = null;
            }
        }

    }

    class SessionsDiagnosticsSummaryObject extends AbstractLifecycle {

        private final Map<NodeId, SessionDiagnosticsObject> sessionDiagnosticsObjects = Maps.newConcurrentMap();

        private SessionListener sessionListener;

        private final SessionsDiagnosticsSummaryTypeNode node;

        SessionsDiagnosticsSummaryObject(SessionsDiagnosticsSummaryTypeNode node) {
            this.node = node;
        }

        @Override
        protected void onStartup() {
            node.getSessionDiagnosticsArrayNode().setAttributeDelegate(new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) {
                    ExtensionObject[] encodedValues = sessionDiagnosticsObjects.values()
                        .stream()
                        .map(sdo ->
                            ExtensionObject.encode(
                                getServer().getSerializationContext(),
                                sdo.session.getSessionDiagnostics()
                                    .getSessionDiagnosticsDataType()
                            )
                        )
                        .toArray(ExtensionObject[]::new);

                    return new DataValue(new Variant(encodedValues));
                }
            });

            node.getSessionSecurityDiagnosticsArrayNode().setAttributeDelegate(new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) {
                    ExtensionObject[] encodedValues = sessionDiagnosticsObjects.values()
                        .stream()
                        .map(sdo ->
                            ExtensionObject.encode(
                                getServer().getSerializationContext(),
                                sdo.session.getSessionSecurityDiagnostics()
                                    .getSessionSecurityDiagnosticsDataType()
                            )
                        )
                        .toArray(ExtensionObject[]::new);

                    return new DataValue(new Variant(encodedValues));
                }
            });

            getServer().getSessionManager().addSessionListener(sessionListener = new SessionListener() {
                @Override
                public void onSessionCreated(Session session) {
                    SessionDiagnosticsObject sdo = new SessionDiagnosticsObject(session, node);

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

        SessionDiagnosticsObject(Session session, SessionsDiagnosticsSummaryTypeNode summaryNode) {
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

                node = (SessionDiagnosticsObjectTypeNode) nodeFactory.createNode(
                    new NodeId(0, UUID.randomUUID()),
                    Identifiers.SessionDiagnosticsObjectType,
                    false
                );
                node.setBrowseName(new QualifiedName(1, name));
                node.setDisplayName(LocalizedText.english(name));

                nodeManager.addNode(node);
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
                                )
                            )
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

}
