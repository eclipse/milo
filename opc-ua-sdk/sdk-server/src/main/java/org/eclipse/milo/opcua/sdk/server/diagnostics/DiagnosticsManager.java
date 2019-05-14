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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.SessionListener;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionDiagnosticsObjectTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.SessionsDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SubscriptionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeContext;
import org.eclipse.milo.opcua.sdk.server.nodes.AttributeObserver;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaVariableNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.stack.core.AttributeId;
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

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;

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

            SubscriptionDiagnosticsArrayTypeNode subscriptionDiagnosticsArrayNode =
                node.getSubscriptionDiagnosticsArrayNode();

            subscriptionDiagnosticsArrayNode.setAttributeDelegate(new AttributeDelegate() {
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

                    DataValue dataValue = new DataValue(new Variant(encodedValues));

                    node.setValue(dataValue);

                    return dataValue;
                }
            });

            subscriptionDiagnosticsArrayNode.addAttributeObserver(
                new ArrayNodeAttributeObserver(subscriptionDiagnosticsArrayNode)
            );

            node.getEnabledFlagNode().setAttributeDelegate(new AttributeDelegate() {
                @Override
                public DataValue getValue(AttributeContext context, VariableNode node) {
                    return new DataValue(new Variant(true));
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
            SessionDiagnosticsArrayTypeNode sessionDiagnosticsArrayNode = node.getSessionDiagnosticsArrayNode();

            sessionDiagnosticsArrayNode.setAttributeDelegate(new AttributeDelegate() {
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

                    DataValue dataValue = new DataValue(new Variant(encodedValues));

                    node.setValue(dataValue);

                    return dataValue;
                }
            });

            sessionDiagnosticsArrayNode.addAttributeObserver(
                new ArrayNodeAttributeObserver(sessionDiagnosticsArrayNode)
            );

            SessionSecurityDiagnosticsArrayTypeNode sessionSecurityDiagnosticsArrayNode =
                node.getSessionSecurityDiagnosticsArrayNode();

            sessionSecurityDiagnosticsArrayNode.setAttributeDelegate(new AttributeDelegate() {
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

                    DataValue dataValue = new DataValue(new Variant(encodedValues));

                    node.setValue(dataValue);

                    return dataValue;
                }
            });

            sessionSecurityDiagnosticsArrayNode.addAttributeObserver(
                new ArrayNodeAttributeObserver(sessionSecurityDiagnosticsArrayNode)
            );

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

                    if (sdo != null) {
                        sdo.shutdown();
                    }
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

                SubscriptionDiagnosticsArrayTypeNode subscriptionDiagnosticsArrayNode =
                    node.getSubscriptionDiagnosticsArrayNode();

                subscriptionDiagnosticsArrayNode.setAttributeDelegate(new AttributeDelegate() {
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

                        DataValue dataValue = new DataValue(new Variant(encodedValues));

                        node.setValue(dataValue);

                        return dataValue;
                    }
                });

                subscriptionDiagnosticsArrayNode.addAttributeObserver(
                    new ArrayNodeAttributeObserver(subscriptionDiagnosticsArrayNode)
                );
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

    /**
     * An {@link AttributeObserver} that watches the value of a Node with an array value and adds/removes/updates
     * read-only child nodes for each element as necessary.
     */
    static class ArrayNodeAttributeObserver implements AttributeObserver {

        private final LinkedList<UaVariableNode> elementNodes = new LinkedList<>();

        private final UaVariableNode arrayNode;
        private final Supplier<Object> valueGetter;

        public ArrayNodeAttributeObserver(UaVariableNode arrayNode) {
            this(arrayNode, () -> arrayNode.getValue().getValue().getValue());
        }

        public ArrayNodeAttributeObserver(UaVariableNode arrayNode, Supplier<Object> valueGetter) {
            this.arrayNode = arrayNode;
            this.valueGetter = valueGetter;
        }

        @Override
        public void attributeChanged(UaNode node, AttributeId attributeId, Object value) {
            if (attributeId == AttributeId.Value) {
                Object valueObject = ((DataValue) value).getValue().getValue();

                if (valueObject == null || !valueObject.getClass().isArray()) {
                    return;
                }

                int length = Array.getLength(valueObject);

                if (elementNodes.size() < length) {
                    for (int i = elementNodes.size(); i < length; i++) {
                        final int index = i;

                        String id = buildBrowseNamePath(node) + "[" + i + "]";

                        UaVariableNode elementNode = new UaVariableNode(
                            arrayNode.getNodeContext(),
                            arrayNode.getNodeId().withId(id),
                            new QualifiedName(
                                arrayNode.getBrowseName().getNamespaceIndex(),
                                arrayNode.getBrowseName().getName() + "[" + i + "]"
                            ),
                            new LocalizedText(
                                arrayNode.getDisplayName().getLocale(),
                                arrayNode.getDisplayName().getText() + "[" + i + "]"
                            ),
                            LocalizedText.NULL_VALUE,
                            arrayNode.getWriteMask(),
                            arrayNode.getUserWriteMask()
                        ) {

                            {
                                setArrayDimensions(null);
                                setValueRank(ValueRanks.Scalar);
                                setDataType(arrayNode.getDataType());
                                setAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)));
                                setUserAccessLevel(ubyte(AccessLevel.getMask(AccessLevel.READ_ONLY)));

                                Object array = valueGetter.get();
                                Object elementValue = Array.get(array, index);
                                setValue(new DataValue(new Variant(elementValue)));
                            }

                            @Override
                            public synchronized DataValue getValue() {
                                Object array = valueGetter.get();
                                Object elementValue = Array.get(array, index);

                                return new DataValue(new Variant(elementValue));
                            }

                            @Override
                            public synchronized void setValue(DataValue value) {}

                        };

                        elementNodes.add(elementNode);

                        arrayNode.addComponent(elementNode);
                        arrayNode.getNodeManager().addNode(elementNode);
                    }
                } else if (elementNodes.size() > length) {
                    while (elementNodes.size() > length) {
                        UaVariableNode elementNode = elementNodes.removeLast();

                        arrayNode.removeComponent(elementNode);
                        arrayNode.getNodeManager().removeNode(elementNode);
                    }
                }

                for (int i = 0; i < length; i++) {
                    Object o = Array.get(valueObject, i);

                    elementNodes.get(i).setValue(new DataValue(new Variant(o)));
                }
            }
        }

        private static String buildBrowseNamePath(UaNode node) {
            return buildBrowseNamePath(node, new ArrayList<>());
        }

        private static String buildBrowseNamePath(UaNode node, List<String> browseNames) {
            if (node == null || node.getNodeId().equals(Identifiers.ObjectsFolder)) {
                Collections.reverse(browseNames);

                return String.join(".", browseNames);
            }

            browseNames.add(node.getBrowseName().toParseableString());

            Optional<Reference> referenceToParent = node.getReferences().stream()
                .filter(r -> r.isInverse() && r.subtypeOf(Identifiers.HierarchicalReferences))
                .findFirst();

            Optional<UaNode> parentNode = referenceToParent
                .flatMap(r ->
                    node.getNodeContext()
                        .getServer()
                        .getAddressSpaceManager()
                        .getManagedNode(r.getTargetNodeId())
                );

            return buildBrowseNamePath(parentNode.orElse(null), browseNames);
        }

    }

}
