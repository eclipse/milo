/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions2;

import java.util.List;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;

public class Playground {

    public static void main(String[] args) throws UaException, InterruptedException {
        var client = OpcUaClient.create("opc.tcp://milo.digitalpetri.com:62541/milo");
        client.connect();

        var subscription = new OpcUaSubscription(client);
        subscription.create();

        subscription.setSubscriptionListener(new OpcUaSubscription.SubscriptionListener() {
            @Override
            public void onDataReceived(
                OpcUaSubscription subscription,
                List<OpcUaMonitoredItem> items, List<DataValue> values
            ) {

                for (int i = 0; i < items.size(); i++) {
                    System.out.printf(
                        "[id=%s] received value: %s%n",
                        items.get(i).getMonitoredItemId().orElse(null), values.get(i).getValue()
                    );
                }
            }

            @Override
            public void onKeepAliveReceived(OpcUaSubscription subscription) {
                System.out.printf("[id=%s] keep alive%n", subscription.getSubscriptionId().orElse(null));
            }
        });

        System.out.println(subscription);

        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            NodeIds.Server_ServerStatus_CurrentTime
        );

        monitoredItem.setDataValueListener(
            (item, value) ->
                System.out.printf(
                    "[id=%s] received value: %s%n",
                    item.getMonitoredItemId().orElse(null), value.getValue()
                )
        );

        subscription.addMonitoredItem(monitoredItem);
        subscription.synchronizeMonitoredItems();

        Thread.sleep(5_000);

        subscription.removeMonitoredItem(monitoredItem);
        subscription.synchronizeMonitoredItems();

        Thread.sleep(30_000);

        subscription.delete();
        client.disconnect();
    }

}
