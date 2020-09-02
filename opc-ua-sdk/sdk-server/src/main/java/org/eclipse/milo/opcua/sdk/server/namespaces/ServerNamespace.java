/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.namespaces;

import java.util.List;
import java.util.NoSuchElementException;

import org.eclipse.milo.opcua.sdk.core.AccessLevel;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespaceWithLifecycle;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.diagnostics.objects.ServerDiagnosticsObject;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.ServerDiagnosticsTypeNode;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;
import org.eclipse.milo.opcua.stack.core.Identifiers;

public class ServerNamespace extends ManagedNamespaceWithLifecycle {

    private final SubscriptionModel subscriptionModel;

    public ServerNamespace(OpcUaServer server) {
        super(server, server.getConfig().getApplicationUri());

        subscriptionModel = new SubscriptionModel(server, this);

        ServerDiagnosticsTypeNode serverDiagnosticsNode = (ServerDiagnosticsTypeNode) getServer()
            .getAddressSpaceManager()
            .getManagedNode(Identifiers.Server_ServerDiagnostics)
            .orElseThrow(() -> new NoSuchElementException("NodeId: " + Identifiers.Server_ServerDiagnostics));

        serverDiagnosticsNode.getEnabledFlagNode().setUserAccessLevel(
            AccessLevel.toValue(AccessLevel.READ_WRITE)
        );

        ServerDiagnosticsObject serverDiagnosticsObject = new ServerDiagnosticsObject(
            serverDiagnosticsNode, getNodeManager()
        );

        getLifecycleManager().addStartupTask(() -> VendorServerInfoNodes.add(getNodeContext()));
        getLifecycleManager().addLifecycle(subscriptionModel);
        getLifecycleManager().addLifecycle(serverDiagnosticsObject);
    }

    @Override
    public void onDataItemsCreated(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsCreated(dataItems);
    }

    @Override
    public void onDataItemsModified(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsModified(dataItems);
    }

    @Override
    public void onDataItemsDeleted(List<DataItem> dataItems) {
        subscriptionModel.onDataItemsDeleted(dataItems);
    }

    @Override
    public void onMonitoringModeChanged(List<MonitoredItem> monitoredItems) {
        subscriptionModel.onMonitoringModeChanged(monitoredItems);
    }

}
