/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.objects;

import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.variables.ServerDiagnosticsSummaryVariable;
import org.eclipse.milo.opcua.sdk.server.diagnostics.wrappers.variables.SubscriptionDiagnosticsVariableArray;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.base.Preconditions.checkNotNull;

public class ServerDiagnosticsObject extends AbstractLifecycle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private SubscriptionDiagnosticsVariableArray subscriptionDiagnosticsVariableArray;
    private SessionsDiagnosticsSummaryObject sessionsDiagnosticsSummaryObject;

    private final OpcUaServer server;

    private final ServerDiagnosticsTypeNode node;
    private ServerDiagnosticsSummaryVariable serverDiagnosticsSummary;

    public ServerDiagnosticsObject(ServerDiagnosticsTypeNode node) {
        checkNotNull(node, "ServerDiagnosticsTypeNode");
        this.node = node;

        this.server = node.getNodeContext().getServer();
    }

    @Override
    protected synchronized void onStartup() {
        logger.debug("ServerDiagnosticsNode onStartup()");

        configureServerDiagnosticsSummary();

        configureSubscriptionDiagnosticsArray();

        configureSessionDiagnosticsSummary();
    }

    @Override
    protected synchronized void onShutdown() {
        logger.debug("ServerDiagnosticsNode onShutdown()");

        serverDiagnosticsSummary.shutdown();
        subscriptionDiagnosticsVariableArray.shutdown();
        sessionsDiagnosticsSummaryObject.shutdown();

        node.delete();
    }

    private void configureServerDiagnosticsSummary() {
        serverDiagnosticsSummary = new ServerDiagnosticsSummaryVariable(
            server,
            node.getServerDiagnosticsSummaryNode()
        );
        serverDiagnosticsSummary.startup();
    }

    private void configureSubscriptionDiagnosticsArray() {
        subscriptionDiagnosticsVariableArray = new SubscriptionDiagnosticsVariableArray(
            node.getSubscriptionDiagnosticsArrayNode()
        );
        subscriptionDiagnosticsVariableArray.startup();
    }

    private void configureSessionDiagnosticsSummary() {
        sessionsDiagnosticsSummaryObject = new SessionsDiagnosticsSummaryObject(
            node.getSessionsDiagnosticsSummaryNode()
        );
        sessionsDiagnosticsSummaryObject.startup();
    }

}
