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

import org.eclipse.milo.opcua.sdk.server.Lifecycle;
import org.eclipse.milo.opcua.sdk.server.LifecycleManager;
import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.ManagedNamespace;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.sdk.server.util.SubscriptionModel;

@SuppressWarnings("restriction")
public class ServerNamespace extends ManagedNamespace implements Lifecycle {

    private final LifecycleManager lifecycleManager = new LifecycleManager();

    private final SubscriptionModel subscriptionModel;

    public ServerNamespace(OpcUaServer server) {
        super(server, server.getConfig().getApplicationUri());

        subscriptionModel = new SubscriptionModel(server, this);

        lifecycleManager.addLifecycle(new Lifecycle() {
            @Override
            public void startup() {
                server.getAddressSpaceManager().register(getNodeManager());
                server.getAddressSpaceManager().register(ServerNamespace.this);
            }

            @Override
            public void shutdown() {
                server.getAddressSpaceManager().unregister(getNodeManager());
                server.getAddressSpaceManager().unregister(ServerNamespace.this);
            }
        });

        lifecycleManager.addStartupTask(() -> VendorServerInfoNodes.add(getNodeContext()));

        lifecycleManager.addLifecycle(subscriptionModel);
    }

    @Override
    public void startup() {
        lifecycleManager.startup();
    }

    @Override
    public void shutdown() {
        lifecycleManager.shutdown();
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
