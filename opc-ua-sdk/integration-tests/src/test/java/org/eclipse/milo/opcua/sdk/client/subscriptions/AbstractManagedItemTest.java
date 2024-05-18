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

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.subscriptions.BatchModifyMonitoredItems.ModifyMonitoredItemResult;
import org.eclipse.milo.opcua.sdk.client.subscriptions.BatchSetMonitoringMode.SetMonitoringModeResult;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public abstract class AbstractManagedItemTest extends AbstractSubscriptionTest {

    protected abstract ManagedItem createManagedItem() throws UaException;

    @Test
    public void delete() throws UaException {
        ManagedItem managedItem = createManagedItem();
        assertTrue(managedItem.getStatusCode().isGood());

        managedItem.delete();

        //noinspection SuspiciousMethodCalls
        assertFalse(subscription.getDataItems().contains(managedItem));
        //noinspection SuspiciousMethodCalls
        assertFalse(subscription.getEventItems().contains(managedItem));

        assertFalse(subscription.getSubscription().getMonitoredItems().contains(managedItem.getMonitoredItem()));

        assertTrue(managedItem.getStatusCode().isGood());

        managedItem.delete();
        assertFalse(managedItem.getStatusCode().isGood());
    }

    @Test
    public void monitoringMode() throws UaException {
        ManagedItem managedItem = createManagedItem();
        assertEquals(managedItem.getMonitoringMode(), MonitoringMode.Reporting);

        managedItem.setMonitoringMode(MonitoringMode.Sampling);
        assertEquals(managedItem.getMonitoringMode(), MonitoringMode.Sampling);

        managedItem.setMonitoringMode(MonitoringMode.Disabled);
        assertEquals(managedItem.getMonitoringMode(), MonitoringMode.Disabled);

        managedItem.setMonitoringMode(MonitoringMode.Reporting);
        assertEquals(managedItem.getMonitoringMode(), MonitoringMode.Reporting);
    }

    @Test
    public void monitoringModeBatch() throws UaException, ExecutionException, InterruptedException {
        ManagedItem managedItem1 = createManagedItem();
        ManagedItem managedItem2 = createManagedItem();

        BatchSetMonitoringMode batch = new BatchSetMonitoringMode(subscription);
        CompletableFuture<Unit> f1 = managedItem1.setMonitoringModeAsync(MonitoringMode.Sampling, batch);
        CompletableFuture<Unit> f2 = managedItem2.setMonitoringModeAsync(MonitoringMode.Sampling, batch);

        List<SetMonitoringModeResult> results = batch.execute();

        results.forEach(result -> {
            assertTrue(result.isServiceResultGood());
            assertTrue(result.isOperationResultGood());
        });

        assertEquals(f1.get(), Unit.VALUE);
        assertEquals(f2.get(), Unit.VALUE);
        assertEquals(managedItem1.getMonitoringMode(), MonitoringMode.Sampling);
        assertEquals(managedItem2.getMonitoringMode(), MonitoringMode.Sampling);

        assertEquals(batch.getServiceInvocationCount(), 1);
    }

    @Test
    public void queueSize() throws UaException {
        UInteger initialQueueSize = subscription.getDefaultQueueSize();

        ManagedItem managedItem = createManagedItem();
        assertEquals(managedItem.getQueueSize(), initialQueueSize);

        UInteger newQueueSize = initialQueueSize.add(1);
        assertEquals(managedItem.setQueueSize(newQueueSize), newQueueSize);
        assertEquals(managedItem.getQueueSize(), newQueueSize);
    }

    @Test
    public void queueSizeBatch() throws UaException, ExecutionException, InterruptedException {
        ManagedItem managedItem1 = createManagedItem();
        ManagedItem managedItem2 = createManagedItem();

        BatchModifyMonitoredItems batch = new BatchModifyMonitoredItems(subscription);

        CompletableFuture<UInteger> f1 = managedItem1.setQueueSizeAsync(uint(10), batch);
        CompletableFuture<UInteger> f2 = managedItem2.setQueueSizeAsync(uint(20), batch);

        List<ModifyMonitoredItemResult> results = batch.execute();

        results.forEach(result -> {
            assertTrue(result.isServiceResultGood());
            assertTrue(result.isOperationResultGood());
        });

        assertEquals(f1.get(), uint(10));
        assertEquals(managedItem1.getQueueSize(), uint(10));

        assertEquals(f2.get(), uint(20));
        assertEquals(managedItem2.getQueueSize(), uint(20));

        assertEquals(batch.getServiceInvocationCount(), 1);
    }

    @Test
    public void timestampsToReturn() throws UaException {
        TimestampsToReturn timestamps = subscription.getDefaultTimestamps();

        ManagedItem managedItem = createManagedItem();
        assertEquals(managedItem.getTimestampsToReturn(), timestamps);

        TimestampsToReturn newTimestamps = TimestampsToReturn.Neither;
        assertNotEquals(timestamps, newTimestamps);

        managedItem.setTimestampsToReturn(newTimestamps);
        assertEquals(managedItem.getTimestampsToReturn(), newTimestamps);
    }

    @Test
    public void timestampsToReturnBatch() throws UaException, ExecutionException, InterruptedException {
        ManagedItem managedItem1 = createManagedItem();
        ManagedItem managedItem2 = createManagedItem();

        BatchModifyMonitoredItems batch = new BatchModifyMonitoredItems(subscription);

        CompletableFuture<Unit> f1 = managedItem1.setTimestampsToReturnAsync(TimestampsToReturn.Neither, batch);
        CompletableFuture<Unit> f2 = managedItem2.setTimestampsToReturnAsync(TimestampsToReturn.Neither, batch);

        List<ModifyMonitoredItemResult> results = batch.execute();

        results.forEach(result -> {
            assertTrue(result.isServiceResultGood());
            assertTrue(result.isOperationResultGood());
        });

        assertEquals(f1.get(), Unit.VALUE);
        assertEquals(managedItem1.getTimestampsToReturn(), TimestampsToReturn.Neither);

        assertEquals(f2.get(), Unit.VALUE);
        assertEquals(managedItem2.getTimestampsToReturn(), TimestampsToReturn.Neither);

        assertEquals(batch.getServiceInvocationCount(), 1);
    }

    @Test
    public void discardOldest() throws UaException {
        boolean defaultDiscardOldest = subscription.getDefaultDiscardOldest();

        ManagedItem managedItem = createManagedItem();

        assertEquals(managedItem.getDiscardOldest(), defaultDiscardOldest);

        managedItem.setDiscardOldest(!defaultDiscardOldest);
        assertEquals(managedItem.getDiscardOldest(), !defaultDiscardOldest);
    }

    @Test
    public void discardOldestBatch() throws UaException, ExecutionException, InterruptedException {
        ManagedItem managedItem1 = createManagedItem();
        ManagedItem managedItem2 = createManagedItem();

        BatchModifyMonitoredItems batch = new BatchModifyMonitoredItems(subscription);

        CompletableFuture<Unit> f1 = managedItem1.setDiscardOldestAsync(false, batch);
        CompletableFuture<Unit> f2 = managedItem2.setDiscardOldestAsync(false, batch);

        List<ModifyMonitoredItemResult> results = batch.execute();

        results.forEach(result -> {
            assertTrue(result.isServiceResultGood());
            assertTrue(result.isOperationResultGood());
        });

        assertEquals(f1.get(), Unit.VALUE);
        assertFalse(managedItem1.getDiscardOldest());

        assertEquals(f2.get(), Unit.VALUE);
        assertFalse(managedItem2.getDiscardOldest());

        assertEquals(batch.getServiceInvocationCount(), 1);
    }

}
