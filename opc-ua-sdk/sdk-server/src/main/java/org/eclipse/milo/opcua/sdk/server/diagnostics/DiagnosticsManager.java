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

import java.util.NoSuchElementException;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.objects.ServerDiagnosticsObject;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.factories.NodeFactory;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiagnosticsManager extends AbstractLifecycle {

    private final Logger logger = LoggerFactory.getLogger(DiagnosticsManager.class);

    private volatile boolean diagnosticsEnabled = false;
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
        ServerDiagnosticsTypeNode serverDiagnosticsNode = (ServerDiagnosticsTypeNode) getServer()
            .getAddressSpaceManager()
            .getManagedNode(Identifiers.Server_ServerDiagnostics)
            .orElseThrow(() -> new NoSuchElementException("NodeId: " + Identifiers.Server_ServerDiagnostics));

        serverDiagnosticsNode.getEnabledFlagNode().setUserAccessLevel(
            AccessLevel.toValue(AccessLevel.READ_WRITE)
        );

        serverDiagnosticsObject = new ServerDiagnosticsObject(serverDiagnosticsNode);
        serverDiagnosticsObject.startup();
    }

    @Override
    protected void onShutdown() {
        if (serverDiagnosticsObject != null) {
            serverDiagnosticsObject.shutdown();
            serverDiagnosticsObject = null;
        }
    }

}
