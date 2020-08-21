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

import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.Lifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.SessionListener;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsArrayTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.sdk.server.nodes.filters.AttributeFilters;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
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

    private final SessionSecurityDiagnosticsArrayTypeNode node;

    public SessionSecurityDiagnosticsVariableArray(SessionSecurityDiagnosticsArrayTypeNode node) {
        this.node = node;

        this.server = node.getNodeContext().getServer();
        this.nodeFactory = new NodeFactory(node.getNodeContext());
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
