/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.variables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.core.ValueRank;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.Lifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.SessionListener;
import org.eclipse.milo.opcua.sdk.server.api.NodeManager;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionDiagnosticsVariableTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionDiagnosticsDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionDiagnosticsVariableArray extends AbstractLifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<SessionDiagnosticsVariable> sessionDiagnosticsVariables =
        Collections.synchronizedList(new ArrayList<>());

    private SessionListener sessionListener;

    private final OpcUaServer server;
    private final NodeFactory nodeFactory;
    private final NodeManager<UaNode> nodeManager;

    private final SessionDiagnosticsArrayTypeNode node;

    public SessionDiagnosticsVariableArray(SessionDiagnosticsArrayTypeNode node) {
        this.node = node;

        this.server = node.getNodeContext().getServer();
        this.nodeFactory = new NodeFactory(node.getNodeContext());
        this.nodeManager = node.getNodeManager();
    }

    @Override
    protected void onStartup() {
        node.getFilterChain().addLast(AttributeFilters.getValue(ctx -> {
            ExtensionObject[] xos = ExtensionObject.encodeArray(
                server.getSerializationContext(),
                server.getSessionManager()
                    .getAllSessions()
                    .stream()
                    .map(s ->
                        s.getSessionDiagnostics()
                            .getSessionDiagnosticsDataType()
                    )
                    .toArray(SessionDiagnosticsDataType[]::new)
            );
            return new DataValue(new Variant(xos));
        }));

        server.getSessionManager().getAllSessions().forEach(this::createSessionDiagnosticsVariable);

        server.getSessionManager().addSessionListener(sessionListener = new SessionListener() {
            @Override
            public void onSessionCreated(Session session) {
                createSessionDiagnosticsVariable(session);
            }

            @Override
            public void onSessionClosed(Session session) {
                for (int i = 0; i < sessionDiagnosticsVariables.size(); i++) {
                    SessionDiagnosticsVariable v = sessionDiagnosticsVariables.get(i);
                    if (v.getSession().getSessionId().equals(session.getSessionId())) {
                        sessionDiagnosticsVariables.remove(i);
                        v.shutdown();
                        break;
                    }
                }
            }
        });
    }

    private void createSessionDiagnosticsVariable(Session session) {
        try {
            int index = sessionDiagnosticsVariables.size();
            String name = node.getBrowseName().getName() + "[" + index + "]";
            String id = Util.buildBrowseNamePath(node) + "[" + index + "]";
            NodeId elementNodeId = node.getNodeId().withId(id);

            SessionDiagnosticsVariableTypeNode elementNode =
                (SessionDiagnosticsVariableTypeNode) nodeFactory.createNode(
                    elementNodeId,
                    Identifiers.SessionDiagnosticsVariableType
                );

            elementNode.setBrowseName(new QualifiedName(
                node.getBrowseName().getNamespaceIndex(),
                name
            ));
            elementNode.setDisplayName(new LocalizedText(
                node.getDisplayName().getLocale(),
                name
            ));
            elementNode.setArrayDimensions(null);
            elementNode.setValueRank(ValueRank.Scalar.getValue());
            elementNode.setDataType(Identifiers.SubscriptionDiagnosticsDataType);
            elementNode.setAccessLevel(AccessLevel.toValue(AccessLevel.READ_ONLY));
            elementNode.setUserAccessLevel(AccessLevel.toValue(AccessLevel.READ_ONLY));

            elementNode.addReference(new Reference(
                elementNode.getNodeId(),
                Identifiers.HasComponent,
                node.getNodeId().expanded(),
                Reference.Direction.INVERSE
            ));
            nodeManager.addNode(elementNode);

            SessionDiagnosticsVariable sessionDiagnosticsVariable =
                new SessionDiagnosticsVariable(elementNode, session);
            sessionDiagnosticsVariable.startup();

            sessionDiagnosticsVariables.add(sessionDiagnosticsVariable);
        } catch (UaException e) {
            logger.warn(
                "Failed to create SessionDiagnosticsVariableTypeNode for session id={}",
                session.getSessionId(), e
            );
        }
    }

    @Override
    protected void onShutdown() {
        if (sessionListener != null) {
            server.getSessionManager().removeSessionListener(sessionListener);
            sessionListener = null;
        }

        sessionDiagnosticsVariables.forEach(Lifecycle::shutdown);

        node.delete();
    }

}
