/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.examples.client;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OldSubscriptionExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        OldSubscriptionExample example = new OldSubscriptionExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        client.connect();

        // create a subscription @ 1000ms
        UaSubscription subscription = client.getSubscriptionManager().createSubscription(1000.0).get();

        // subscribe to the Value attribute of the server's CurrentTime node
        ReadValueId readValueId = new ReadValueId(
            NodeIds.Server_ServerStatus_CurrentTime,
            AttributeId.Value.uid(), null, QualifiedName.NULL_VALUE
        );

        // IMPORTANT: client handle must be unique per item within the context of a subscription.
        // You are not required to use the UaSubscription's client handle sequence; it is provided as a convenience.
        // Your application is free to assign client handles by whatever means necessary.
        UInteger clientHandle = subscription.nextClientHandle();

        MonitoringParameters parameters = new MonitoringParameters(
            clientHandle,
            1000.0,     // sampling interval
            null,       // filter, null means use default
            uint(10),   // queue size
            true        // discard oldest
        );

        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
            readValueId,
            MonitoringMode.Reporting,
            parameters
        );

        // when creating items in MonitoringMode.Reporting this callback is where each item needs to have its
        // value/event consumer hooked up. The alternative is to create the item in sampling mode, hook up the
        // consumer after the creation call completes, and then change the mode for all items to reporting.
        UaSubscription.ItemCreationCallback onItemCreated =
            (item, id) -> item.setValueConsumer(this::onSubscriptionValue);

        List<UaMonitoredItem> items = subscription.createMonitoredItems(
            TimestampsToReturn.Both,
            List.of(request),
            onItemCreated
        ).get();

        for (UaMonitoredItem item : items) {
            if (item.getStatusCode().isGood()) {
                logger.info("item created for nodeId={}", item.getReadValueId().getNodeId());
            } else {
                logger.warn(
                    "failed to create item for nodeId={} (status={})",
                    item.getReadValueId().getNodeId(), item.getStatusCode());
            }
        }

        // let the example run for 5 seconds then terminate
        Thread.sleep(5000);
        future.complete(client);
    }

    private void onSubscriptionValue(UaMonitoredItem item, DataValue value) {
        logger.info(
            "subscription value received: item={}, value={}",
            item.getReadValueId().getNodeId(), value.getValue());
    }

}
