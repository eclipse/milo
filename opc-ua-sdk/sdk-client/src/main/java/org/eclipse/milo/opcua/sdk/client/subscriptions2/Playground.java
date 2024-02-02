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

import java.util.ArrayList;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions2.batching.CreateMonitoredItemBatch;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.UaException;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class Playground {

    public static void main(String[] args) throws UaException {
        var client = OpcUaClient.create("opc.tcp://milo.digitalpetri.com:62541/milo");
        client.connect();

        var subscription = new OpcUaSubscription(client);
        System.out.println(subscription);

        subscription.setPublishingInterval(250.0);
        subscription.create();

        System.out.println("Created subscription: " + subscription.getSubscriptionId().orElseThrow());

        subscription.setPublishingInterval(750.0);
        subscription.setPriority(ubyte(1));
        subscription.setMaxNotificationsPerPublish(uint(1 << 17));
        subscription.modify();

        System.out.printf("Modified subscription: publishingInterval=%s, priority=%s, maxNotificationsPerPublish=%s%n",
            subscription.getRevisedPublishingInterval().orElseThrow(),
            subscription.getPriority(),
            subscription.getMaxNotificationsPerPublish()
        );

        System.out.println(subscription);

        subscription.delete();

        System.out.println(subscription);

        client.disconnect();
    }

    private static void monitoredItems(OpcUaSubscription subscription) throws UaException {
        // create single monitored item
        {
            var monitoredItem = OpcUaMonitoredItem.newDataItem(
                subscription,
                NodeIds.Server_ServerStatus_CurrentTime
            );

            monitoredItem.setDataValueListener(
                (item, value) ->
                    System.out.printf(
                        "[%s] Received value: %s%n",
                        item.getMonitoredItemId(), value)
            );

            monitoredItem.create();
        }

        // create batch of monitored items
        {
            var monitoredItems = new ArrayList<OpcUaMonitoredItem>();
            var batch = new CreateMonitoredItemBatch();

            for (int i = 0; i < 10; i++) {
                var monitoredItem = OpcUaMonitoredItem.newDataItem(
                    subscription,
                    NodeIds.Server_ServerStatus_CurrentTime
                );

                monitoredItems.add(monitoredItem);

                monitoredItem.setDataValueListener(
                    (item, value) ->
                        System.out.printf(
                            "[%s] Received value: %s%n",
                            item.getMonitoredItemId(), value)
                );

                monitoredItem.create(batch);
            }

            // batch.execute();
        }
    }

}
