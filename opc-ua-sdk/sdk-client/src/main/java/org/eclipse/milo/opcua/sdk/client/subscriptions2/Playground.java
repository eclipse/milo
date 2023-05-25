/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.UaException;

public class Playground {

    public static void main(String[] args) throws UaException {
        var client = OpcUaClient.create("opc.tcp://milo.digitalpetri.com:62541/milo");
        client.connect();

        var subscription = new OpcUaSubscription(client);
        subscription.create();

        subscription.setPublishingInterval(250.0);
        subscription.modify();

        // create single monitored item
        {
            var monitoredItem = OpcUaMonitoredItem.newDataItem(
                subscription,
                NodeIds.Server_ServerStatus_CurrentTime
            );

            monitoredItem.addDataValueListener(
                (item, value) ->
                    System.out.printf("Received value: %s%n", value)
            );

            monitoredItem.create();
        }

        // create batch of monitored items
        {
            var monitoredItems = new ArrayList<OpcUaMonitoredItem>();
            var batch = new Object();

            for (int i = 0; i < 10; i++) {
                var monitoredItem = OpcUaMonitoredItem.newDataItem(
                    subscription,
                    NodeIds.Server_ServerStatus_CurrentTime
                );

                monitoredItems.add(monitoredItem);

                monitoredItem.addDataValueListener(
                    (item, value) ->
                        System.out.printf("Received value: %s%n", value)
                );

                monitoredItem.create(batch);
            }

            // batch.execute();
        }
    }

}
