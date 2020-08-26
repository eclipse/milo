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

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicBoolean;

import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.SessionSecurityDiagnosticsTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

import static org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.variables.Util.diagnosticValueFilter;

public class SessionSecurityDiagnosticsVariable extends AbstractLifecycle {

    private final AtomicBoolean diagnosticsEnabled = new AtomicBoolean(false);

    private final OpcUaServer server;

    private final SessionSecurityDiagnosticsTypeNode node;
    private final Session session;

    public SessionSecurityDiagnosticsVariable(SessionSecurityDiagnosticsTypeNode node, Session session) {
        this.node = node;
        this.session = session;

        this.server = node.getNodeContext().getServer();
    }

    public SessionSecurityDiagnosticsTypeNode getNode() {
        return node;
    }

    public Session getSession() {
        return session;
    }

    @Override
    protected void onStartup() {
        ServerDiagnosticsTypeNode diagnosticsNode = (ServerDiagnosticsTypeNode) server.getAddressSpaceManager()
            .getManagedNode(Identifiers.Server_ServerDiagnostics)
            .orElseThrow(() -> new NoSuchElementException("NodeId: " + Identifiers.Server_ServerDiagnostics));

        diagnosticsEnabled.set(diagnosticsNode.getEnabledFlag());

        diagnosticsNode.getEnabledFlagNode().addAttributeObserver((node, attributeId, value) -> {
            if (attributeId == AttributeId.Value) {
                DataValue dataValue = (DataValue) value;
                Object o = dataValue.getValue().getValue();
                if (o instanceof Boolean) {
                    diagnosticsEnabled.set((Boolean) o);
                }
            }
        });

        node.getFilterChain().addLast(diagnosticValueFilter(diagnosticsEnabled, ctx -> {
            ExtensionObject xo = ExtensionObject.encode(
                server.getSerializationContext(),
                session.getSessionSecurityDiagnostics()
                    .getSessionSecurityDiagnosticsDataType()
            );
            return new DataValue(new Variant(xo));
        }));

        node.getSessionIdNode().getFilterChain().addLast(diagnosticValueFilter(diagnosticsEnabled, ctx -> {
            NodeId value = session.getSessionSecurityDiagnostics().getSessionId();
            return new DataValue(new Variant(value));
        }));
        node.getClientUserIdOfSessionNode().getFilterChain().addLast(diagnosticValueFilter(diagnosticsEnabled, ctx -> {
            String value = session.getSessionSecurityDiagnostics().getClientUserIdOfSession();
            return new DataValue(new Variant(value));
        }));
        node.getClientUserIdHistoryNode().getFilterChain().addLast(diagnosticValueFilter(diagnosticsEnabled, ctx -> {
            String[] value = session.getSessionSecurityDiagnostics().getClientUserIdHistory();
            return new DataValue(new Variant(value));
        }));
        node.getAuthenticationMechanismNode().getFilterChain().addLast(
            diagnosticValueFilter(diagnosticsEnabled, ctx -> {
                String value = session.getSessionSecurityDiagnostics().getAuthenticationMechanism();
                return new DataValue(new Variant(value));
            })
        );
        node.getEncodingNode().getFilterChain().addLast(diagnosticValueFilter(diagnosticsEnabled, ctx -> {
            String value = session.getSessionSecurityDiagnostics().getEncoding();
            return new DataValue(new Variant(value));
        }));
        node.getTransportProtocolNode().getFilterChain().addLast(diagnosticValueFilter(diagnosticsEnabled, ctx -> {
            String value = session.getSessionSecurityDiagnostics().getTransportProtocol();
            return new DataValue(new Variant(value));
        }));
        node.getSecurityModeNode().getFilterChain().addLast(diagnosticValueFilter(diagnosticsEnabled, ctx -> {
            MessageSecurityMode value = session.getSessionSecurityDiagnostics().getSecurityMode();
            return new DataValue(new Variant(value));
        }));
        node.getSecurityPolicyUriNode().getFilterChain().addLast(diagnosticValueFilter(diagnosticsEnabled, ctx -> {
            String value = session.getSessionSecurityDiagnostics().getSecurityPolicyUri();
            return new DataValue(new Variant(value));
        }));
        node.getClientCertificateNode().getFilterChain().addLast(diagnosticValueFilter(diagnosticsEnabled, ctx -> {
            ByteString value = session.getSessionSecurityDiagnostics().getClientCertificate();
            return new DataValue(new Variant(value));
        }));
    }

    @Override
    protected void onShutdown() {
        node.delete();
    }

}
