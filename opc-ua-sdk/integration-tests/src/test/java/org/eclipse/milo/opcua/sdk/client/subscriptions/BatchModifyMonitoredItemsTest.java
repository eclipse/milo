/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.subscriptions.BatchModifyMonitoredItems.ModifyMonitoredItemResult;
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

        List<CompletableFuture<ModifyMonitoredItemResult>> futures = new ArrayList<>();

        items.forEach(item -> {
            CompletableFuture<ModifyMonitoredItemResult> future =
                batch.add(item, b -> b.setSamplingInterval(5000.0));

            futures.add(future);
        });

        List<ModifyMonitoredItemResult> batchResults = batch.execute();

        for (int i = 0; i < 10; i++) {
            ModifyMonitoredItemResult result = batchResults.get(i);
            CompletableFuture<ModifyMonitoredItemResult> future = futures.get(i);

            assertTrue(future.isDone());

            if (i % 2 == 0) {
                assertTrue(result.isOperationResultGood());
                assertTrue(future.get().isOperationResultGood());
                assertEquals(items.get(i).getRevisedSamplingInterval(), 5000.0);
            } else {
                assertFalse(result.isOperationResultGood());
                assertFalse(future.get().isOperationResultGood());
            }
        }

        assertEquals(batch.getServiceInvocationCount(), 1);
    }

    @Test
    public void multipleTimestampsToReturn() throws UaException, InterruptedException {
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

        List<ModifyMonitoredItemResult> results = batch.execute();

        for (ModifyMonitoredItemResult result : results) {
            assertTrue(result.isServiceResultGood());
            assertTrue(result.isOperationResultGood());
        }

        assertEquals(item1.getTimestampsToReturn(), TimestampsToReturn.Server);
        assertEquals(item2.getTimestampsToReturn(), TimestampsToReturn.Both);
        assertEquals(item3.getTimestampsToReturn(), TimestampsToReturn.Neither);
        assertEquals(item4.getTimestampsToReturn(), TimestampsToReturn.Source);

        assertEquals(batch.getServiceInvocationCount(), 4);
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
        assertEquals(batch.execute().size(), 3);
        assertEquals(batch.getServiceInvocationCount(), 1);
    }

}
