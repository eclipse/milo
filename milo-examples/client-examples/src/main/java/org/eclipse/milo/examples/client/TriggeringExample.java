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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class TriggeringExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        TriggeringExample example = new TriggeringExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicLong clientHandles = new AtomicLong(1L);

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        client.connect();

        // create a subscription @ 1000ms
        UaSubscription subscription = client.getSubscriptionManager()
            .createSubscription(1000.0)
            .get();

        // subscribe to a static value that reports
        ReadValueId readValueId1 = new ReadValueId(
            new NodeId(2, "HelloWorld/ScalarTypes/Float"),
            AttributeId.Value.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        UaMonitoredItem reportingItem = createMonitoredItem(subscription, readValueId1, MonitoringMode.Reporting);

        // subscribe to a dynamic value that only samples
        ReadValueId readValueId2 = new ReadValueId(
            NodeIds.Server_ServerStatus_CurrentTime,
            AttributeId.Value.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        UaMonitoredItem samplingItem = createMonitoredItem(subscription, readValueId2, MonitoringMode.Sampling);

        subscription.addTriggeringLinks(reportingItem, List.of(samplingItem)).get();

        // trigger reporting of both by writing to the static item and changing its value
        client.writeValues(
            List.of(new NodeId(2, "HelloWorld/ScalarTypes/Float")),
            List.of(new DataValue(Variant.ofFloat(1.0f)))
        );

        // let the example run for 5 seconds then terminate
        Thread.sleep(5000);
        future.complete(client);
    }

    private UaMonitoredItem createMonitoredItem(
        UaSubscription subscription,
        ReadValueId readValueId,
        MonitoringMode monitoringMode
    ) throws ExecutionException, InterruptedException {

        // important: client handle must be unique per item
        UInteger clientHandle = uint(clientHandles.getAndIncrement());

        MonitoringParameters parameters = new MonitoringParameters(
            clientHandle,
            1000.0,
            null,
            uint(10),
            true
        );

        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
            readValueId,
            monitoringMode,
            parameters
        );

        UaSubscription.ItemCreationCallback onItemCreated =
            (item, id) -> item.setValueConsumer(this::onSubscriptionValue);

        List<UaMonitoredItem> items = subscription.createMonitoredItems(
            TimestampsToReturn.Both,
            List.of(request),
            onItemCreated
        ).get();

        return items.get(0);
    }

    private void onSubscriptionValue(UaMonitoredItem item, DataValue value) {
        logger.info(
            "subscription value received: item={}, value={}",
            item.getReadValueId().getNodeId(), value.getValue());
    }

}
