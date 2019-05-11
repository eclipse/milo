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
import java.util.NoSuchElementException;

import org.eclipse.milo.opcua.sdk.server.AbstractLifecycle;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.AddressSpaceFilter;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedAddressSpace;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.api.SimpleAddressSpaceFilter;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ServerDiagnosticsSummaryTypeNode;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;
import org.eclipse.milo.opcua.sdk.server.nodes.delegates.ComplexVariableNodeAttributeDelegates;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerDiagnosticsSummaryDataType;

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
