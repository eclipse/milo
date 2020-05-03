/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.subscriptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.AbstractSubscriptionTest;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.BatchModifyMonitoredItems.ModifyResult;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BatchModifyMonitoredItemsTest extends AbstractSubscriptionTest {

    @Test
    public void mixedOperationResults() throws UaException, ExecutionException, InterruptedException {
        List<OpcUaMonitoredItem> items = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            OpcUaMonitoredItem monitoredItem;
            if (i % 2 == 0) {
                monitoredItem = subscription
                    .createDataItem(Identifiers.Server_ServerStatus_CurrentTime)
                    .getMonitoredItem();
            } else {
                monitoredItem = subscription
                    .createDataItem(new NodeId(99, "DoesNotExist"))
                    .getMonitoredItem();
            }
            items.add(monitoredItem);
        }

        BatchModifyMonitoredItems batch = new BatchModifyMonitoredItems(
            client,
            subscription.getSubscription()
        );

        List<CompletableFuture<ModifyResult>> futures = new ArrayList<>();

        items.forEach(item -> {
            CompletableFuture<ModifyResult> future =
                batch.add(item, b -> b.setSamplingInterval(5000.0));

            futures.add(future);
        });

        List<ModifyResult> batchResults = batch.execute();

        for (int i = 0; i < 10; i++) {
            ModifyResult result = batchResults.get(i);
            CompletableFuture<ModifyResult> future = futures.get(i);

            assertTrue(future.isDone());

            if (i % 2 == 0) {
                assertTrue(result.isOperationResultGood());
                assertTrue(future.get().isOperationResultGood());
                assertEquals(5000.0, items.get(i).getRevisedSamplingInterval());
            } else {
                assertFalse(result.isOperationResultGood());
                assertFalse(future.get().isOperationResultGood());
            }
        }

        assertEquals(1, batch.getServiceInvocationCount());
    }

    @Test
    public void multipleTimestampsToReturn() throws UaException {
        ManagedDataItem item1 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        ManagedDataItem item2 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        ManagedDataItem item3 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        ManagedDataItem item4 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);

        item1.setTimestampsToReturn(TimestampsToReturn.Source);
        item2.setTimestampsToReturn(TimestampsToReturn.Server);
        item3.setTimestampsToReturn(TimestampsToReturn.Both);
        item4.setTimestampsToReturn(TimestampsToReturn.Neither);

        BatchModifyMonitoredItems batch = new BatchModifyMonitoredItems(
            client,
            subscription.getSubscription()
        );

        batch.add(item1.getMonitoredItem(), b -> b.setTimestamps(TimestampsToReturn.Server));
        batch.add(item2.getMonitoredItem(), b -> b.setTimestamps(TimestampsToReturn.Both));
        batch.add(item3.getMonitoredItem(), b -> b.setTimestamps(TimestampsToReturn.Neither));
        batch.add(item4.getMonitoredItem(), b -> b.setTimestamps(TimestampsToReturn.Source));

        List<ModifyResult> results = batch.execute();

        for (ModifyResult result : results) {
            assertTrue(result.isServiceResultGood());
            assertTrue(result.isOperationResultGood());
        }

        assertEquals(TimestampsToReturn.Server, item1.getTimestampsToReturn());
        assertEquals(TimestampsToReturn.Both, item2.getTimestampsToReturn());
        assertEquals(TimestampsToReturn.Neither, item3.getTimestampsToReturn());
        assertEquals(TimestampsToReturn.Source, item4.getTimestampsToReturn());

        assertEquals(4, batch.getServiceInvocationCount());
    }

    @Test
    public void operationResultCount() throws Exception {
        OpcUaMonitoredItem monitoredItem = subscription
            .createDataItem(Identifiers.Server_ServerStatus_CurrentTime)
            .getMonitoredItem();

        BatchModifyMonitoredItems batch = new BatchModifyMonitoredItems(
            client,
            subscription.getSubscription()
        );

        batch.add(monitoredItem, b -> b.setDiscardOldest(false));
        batch.add(monitoredItem, b -> b.setSamplingInterval(5000.0));
        batch.add(monitoredItem, b -> b.setTimestamps(TimestampsToReturn.Neither));

        // The number of operation results should match the number of add()
        // calls, not the number of *underlying* operation results. In this
        // test case there are 3 operations to the same item, which only
        // results in underlying modify operation but the batch should return
        // an operation result for each.
        assertEquals(3, batch.execute().size());
        assertEquals(1, batch.getServiceInvocationCount());
    }

}
