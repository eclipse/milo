/*
 * Copyright (c) 2019 the Eclipse Milo Authors
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
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoredItemCreateRequest;
import org.eclipse.milo.opcua.stack.core.types.structured.MonitoringParameters;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.google.common.collect.Lists.newArrayList;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class EventSubscriptionExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        EventSubscriptionExample example = new EventSubscriptionExample();

        new ClientExampleRunner(example, true).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final AtomicLong clientHandles = new AtomicLong(1L);

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        // synchronous connect
        client.connect().get();

        // create a subscription and a monitored item
        UaSubscription subscription = client.getSubscriptionManager()
            .createSubscription(1000.0).get();

        ReadValueId readValueId = new ReadValueId(
            Identifiers.Server,
            AttributeId.EventNotifier.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        // client handle must be unique per item
        UInteger clientHandle = uint(clientHandles.getAndIncrement());

        EventFilter eventFilter = new EventFilter(
            new SimpleAttributeOperand[]{
                new SimpleAttributeOperand(
                    Identifiers.BaseEventType,
                    new QualifiedName[]{new QualifiedName(0, "EventId")},
                    AttributeId.Value.uid(),
                    null),
                new SimpleAttributeOperand(
                    Identifiers.BaseEventType,
                    new QualifiedName[]{new QualifiedName(0, "EventType")},
                    AttributeId.Value.uid(),
                    null),
                new SimpleAttributeOperand(
                    Identifiers.BaseEventType,
                    new QualifiedName[]{new QualifiedName(0, "Severity")},
                    AttributeId.Value.uid(),
                    null),
                new SimpleAttributeOperand(
                    Identifiers.BaseEventType,
                    new QualifiedName[]{new QualifiedName(0, "Time")},
                    AttributeId.Value.uid(),
                    null),
                new SimpleAttributeOperand(
                    Identifiers.BaseEventType,
                    new QualifiedName[]{new QualifiedName(0, "Message")},
                    AttributeId.Value.uid(),
                    null)
            },
            new ContentFilter(null)
        );

        MonitoringParameters parameters = new MonitoringParameters(
            clientHandle,
            0.0,
            ExtensionObject.encode(client.getSerializationContext(), eventFilter),
            uint(10),
            true
        );

        MonitoredItemCreateRequest request = new MonitoredItemCreateRequest(
            readValueId,
            MonitoringMode.Reporting,
            parameters
        );

        List<UaMonitoredItem> items = subscription
            .createMonitoredItems(TimestampsToReturn.Both, newArrayList(request)).get();

        // do something with the value updates
        UaMonitoredItem monitoredItem = items.get(0);

        final AtomicInteger eventCount = new AtomicInteger(0);

        monitoredItem.setEventConsumer((item, vs) -> {
            logger.info(
                "Event Received from {}",
                item.getReadValueId().getNodeId());

            for (int i = 0; i < vs.length; i++) {
                logger.info("\tvariant[{}]: {}", i, vs[i].getValue());
            }

            if (eventCount.incrementAndGet() == 3) {
                future.complete(client);
            }
        });
    }

}
