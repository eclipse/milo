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

import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
        assertEquals(1, subscription.synchronizeMonitoredItems());
        assertEquals(OpcUaMonitoredItem.State.SYNCHRONIZED, monitoredItem.getState());
    }

    @Test
    void deleteMonitoredItem() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            NodeIds.Server_ServerStatus_CurrentTime
        );

        subscription.addMonitoredItem(monitoredItem);
        assertEquals(1, subscription.synchronizeMonitoredItems());
        assertEquals(OpcUaMonitoredItem.State.SYNCHRONIZED, monitoredItem.getState());

        subscription.removeMonitoredItem(monitoredItem);
        assertEquals(1, subscription.synchronizeMonitoredItems());
        assertEquals(OpcUaMonitoredItem.State.INITIAL, monitoredItem.getState());
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
        assertEquals(5000.0, monitoredItem.getRequestedSamplingInterval());
        assertEquals(5000.0, monitoredItem.getRevisedSamplingInterval().orElseThrow());
    }

    @Test
    void modifyMonitoredItem_QueueSize() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            NodeIds.Server_ServerStatus_CurrentTime
        );

        subscription.addMonitoredItem(monitoredItem);
        subscription.synchronizeMonitoredItems();

        monitoredItem.setRequestedQueueSize(uint(10));
        assertEquals(1, subscription.synchronizeMonitoredItems());
        assertEquals(uint(10), monitoredItem.getRequestedQueueSize());
        assertEquals(uint(10), monitoredItem.getRevisedQueueSize().orElseThrow());
    }

    @Test
    void modifyMonitoredItem_MonitoringMode() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            NodeIds.Server_ServerStatus_CurrentTime
        );

        subscription.addMonitoredItem(monitoredItem);
        subscription.synchronizeMonitoredItems();

        monitoredItem.setMonitoringMode(MonitoringMode.Disabled);
        int total = subscription.synchronizeMonitoredItems();
        assertEquals(1, total);
        assertEquals(MonitoringMode.Disabled, monitoredItem.getMonitoringMode());
    }

}
