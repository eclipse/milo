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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsTypeNode;
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
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SessionSecurityDiagnosticsVariableArray extends AbstractLifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final List<SessionSecurityDiagnosticsVariable> sessionSecurityDiagnosticsVariables =
        Collections.synchronizedList(new ArrayList<>());

    private SessionListener sessionListener;

    private final OpcUaServer server;
    private final NodeFactory nodeFactory;
    private final NodeManager<UaNode> nodeManager;

    private final SessionSecurityDiagnosticsArrayTypeNode node;

    public SessionSecurityDiagnosticsVariableArray(SessionSecurityDiagnosticsArrayTypeNode node) {
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
                        s.getSessionSecurityDiagnostics()
                            .getSessionSecurityDiagnosticsDataType()
                    )
                    .toArray(SessionSecurityDiagnosticsDataType[]::new)
            );
            return new DataValue(new Variant(xos));
        }));

        server.getSessionManager().getAllSessions().forEach(this::createSessionSecurityDiagnosticsVariable);

        server.getSessionManager().addSessionListener(sessionListener = new SessionListener() {
            @Override
            public void onSessionCreated(Session session) {
                createSessionSecurityDiagnosticsVariable(session);
            }

            @Override
            public void onSessionClosed(Session session) {
                for (int i = 0; i < sessionSecurityDiagnosticsVariables.size(); i++) {
                    SessionSecurityDiagnosticsVariable v = sessionSecurityDiagnosticsVariables.get(i);
                    if (v.getSession().getSessionId().equals(session.getSessionId())) {
                        sessionSecurityDiagnosticsVariables.remove(i);
                        v.shutdown();
                        break;
                    }
                }
            }
        });
    }

    private void createSessionSecurityDiagnosticsVariable(Session session) {
        try {
            int index = sessionSecurityDiagnosticsVariables.size();
            String name = node.getBrowseName().getName() + "[" + index + "]";
            String id = Util.buildBrowseNamePath(node) + "[" + index + "]";
            NodeId elementNodeId = node.getNodeId().withId(id);

            SessionSecurityDiagnosticsTypeNode elementNode =
                (SessionSecurityDiagnosticsTypeNode) nodeFactory.createNode(
                    elementNodeId,
                    Identifiers.SessionSecurityDiagnosticsType
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

            SessionSecurityDiagnosticsVariable sessionSecurityDiagnosticsVariable =
                new SessionSecurityDiagnosticsVariable(elementNode, session);
            sessionSecurityDiagnosticsVariable.startup();

            sessionSecurityDiagnosticsVariables.add(sessionSecurityDiagnosticsVariable);
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

        sessionSecurityDiagnosticsVariables.forEach(Lifecycle::shutdown);

        node.delete();
    }

}
