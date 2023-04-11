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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.EventFilterBuilder;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedEventItem;
import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ManagedSubscriptionEventExample implements ClientExample {

    public static void main(String[] args) throws Exception {
        ManagedSubscriptionEventExample example = new ManagedSubscriptionEventExample();

        new ClientExampleRunner(example).run();
    }

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(OpcUaClient client, CompletableFuture<OpcUaClient> future) throws Exception {
        client.connectAsync().get();

        final CountDownLatch eventLatch = new CountDownLatch(3);

        ManagedSubscription subscription = ManagedSubscription.create(client);

        subscription.addEventChangeListener((eventItems, variants) -> {
            for (int i = 0; i < eventItems.size(); i++) {
                ManagedEventItem eventItem = eventItems.get(i);
                Variant[] eventFieldValues = variants.get(i);

                logger.info("Event Received from {}", eventItem.getNodeId());
                for (int j = 0; j < eventFieldValues.length; j++) {
                    logger.info("\tvariant[{}]: {}", j, eventFieldValues[j].getValue());
                }
            }

            eventLatch.countDown();
        });

        EventFilter eventFilter = new EventFilterBuilder()
            .select(NodeIds.BaseEventType, new QualifiedName(0, "EventId"))
            .select(NodeIds.BaseEventType, new QualifiedName(0, "EventType"))
            .select(NodeIds.BaseEventType, new QualifiedName(0, "Severity"))
            .select(NodeIds.BaseEventType, new QualifiedName(0, "Time"))
            .select(NodeIds.BaseEventType, new QualifiedName(0, "Message"))
            .build();

        ManagedEventItem eventItem = subscription.createEventItem(NodeIds.Server, eventFilter);

        // wait for some events to arrive before completing
        eventLatch.await();
        eventItem.delete();
        future.complete(client);
    }

}
