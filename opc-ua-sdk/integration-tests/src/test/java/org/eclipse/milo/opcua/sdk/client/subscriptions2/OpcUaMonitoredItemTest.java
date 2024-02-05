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

import java.util.LinkedList;
import java.util.List;

import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class OpcUaMonitoredItemTest extends AbstractClientServerTest {

    private OpcUaSubscription subscription;

    @BeforeEach
    void setUp() throws UaException {
        subscription = new OpcUaSubscription(client);
        subscription.create();
    }

    @AfterEach
    void tearDown() throws UaException {
        subscription.delete();
    }

    @Test
    void createMonitoredItem() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            NodeIds.Server_ServerStatus_CurrentTime
        );

        subscription.addMonitoredItem(monitoredItem);
        List<OpcUaMonitoredItem> created = subscription.createMonitoredItems();

        assertEquals(1, created.size());
        assertEquals(monitoredItem, created.get(0));
        assertEquals(OpcUaMonitoredItem.SyncState.SYNCHRONIZED, monitoredItem.getSyncState());
    }

    @Test
    void deleteMonitoredItem() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            NodeIds.Server_ServerStatus_CurrentTime
        );

        subscription.addMonitoredItem(monitoredItem);
        List<OpcUaMonitoredItem> created = subscription.createMonitoredItems();
        assertEquals(1, created.size());
        assertEquals(monitoredItem, created.get(0));
        assertEquals(OpcUaMonitoredItem.SyncState.SYNCHRONIZED, monitoredItem.getSyncState());

        subscription.removeMonitoredItem(monitoredItem);
        List<OpcUaMonitoredItem> deleted = subscription.deleteMonitoredItems();
        assertEquals(1, deleted.size());
        assertEquals(monitoredItem, deleted.get(0));
        assertEquals(OpcUaMonitoredItem.SyncState.INITIAL, monitoredItem.getSyncState());
    }

    @Test
    void modifyMonitoredItem_SamplingInterval() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            NodeIds.Server_ServerStatus_CurrentTime
        );

        subscription.addMonitoredItem(monitoredItem);
        subscription.synchronizeMonitoredItems();

        monitoredItem.setSamplingInterval(5000.0);
        assertEquals(1, subscription.synchronizeMonitoredItems());
        assertEquals(5000.0, monitoredItem.getSamplingInterval());
        assertEquals(5000.0, monitoredItem.getRevisedSamplingInterval().orElseThrow());
    }

    @Test
    void modifyMonitoredItem_QueueSize() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            NodeIds.Server_ServerStatus_CurrentTime
        );

        subscription.addMonitoredItem(monitoredItem);
        subscription.createMonitoredItems();

        monitoredItem.setQueueSize(uint(10));
        List<OpcUaMonitoredItem> modified = subscription.modifyMonitoredItems();

        assertEquals(1, modified.size());
        assertEquals(monitoredItem, modified.get(0));
        assertEquals(uint(10), monitoredItem.getQueueSize());
        assertEquals(uint(10), monitoredItem.getRevisedQueueSize().orElseThrow());
    }

    @Test
    void modifyMonitoredItem_DiscardOldest() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            NodeIds.Server_ServerStatus_CurrentTime
        );

        subscription.addMonitoredItem(monitoredItem);
        subscription.createMonitoredItems();

        monitoredItem.setDiscardOldest(false);
        List<OpcUaMonitoredItem> modified = subscription.modifyMonitoredItems();

        assertEquals(1, modified.size());
        assertEquals(monitoredItem, modified.get(0));
        assertFalse(monitoredItem.getDiscardOldest());
    }

    @Test
    void synchronizeMonitoredItems() throws UaException {
        var monitoredItems = new LinkedList<OpcUaMonitoredItem>();

        // Create 10 and expect 10 affected during synchronization
        for (int i = 0; i < 10; i++) {
            var monitoredItem = OpcUaMonitoredItem.newDataItem(
                NodeIds.Server_ServerStatus_CurrentTime
            );

            subscription.addMonitoredItem(monitoredItem);

            monitoredItems.add(monitoredItem);
        }
        assertEquals(10, subscription.synchronizeMonitoredItems());

        // Modify 2 and expect 2 affected during synchronization
        monitoredItems.get(0).setSamplingInterval(100.0);
        monitoredItems.get(1).setSamplingInterval(100.0);
        assertEquals(2, subscription.synchronizeMonitoredItems());

        // Delete 2, modify 2, and create 3. Expect 7 affected during synchronization
        subscription.removeMonitoredItem(monitoredItems.removeLast());
        subscription.removeMonitoredItem(monitoredItems.removeLast());
        monitoredItems.get(0).setSamplingInterval(1000.0);
        monitoredItems.get(1).setSamplingInterval(1000.0);
        for (int i = 0; i < 3; i++) {
            var monitoredItem = OpcUaMonitoredItem.newDataItem(
                NodeIds.Server_ServerStatus_CurrentTime
            );

            subscription.addMonitoredItem(monitoredItem);

            monitoredItems.add(monitoredItem);
        }
        assertEquals(7, subscription.synchronizeMonitoredItems());

        // Nothing changed, expect 0 affected during synchronization
        assertEquals(0, subscription.synchronizeMonitoredItems());

        // Remove the same item 3 times, expect only 1 affected during synchronization
        OpcUaMonitoredItem toRemove = monitoredItems.removeLast();
        subscription.removeMonitoredItem(toRemove);
        subscription.removeMonitoredItem(toRemove);
        subscription.removeMonitoredItem(toRemove);
        assertEquals(1, subscription.synchronizeMonitoredItems());
    }

}
