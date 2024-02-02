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

import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
            subscription,
            NodeIds.Server_ServerStatus_CurrentTime
        );

        monitoredItem.create();
    }

    @Test
    void createMonitoredItem_InvalidState() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            subscription,
            NodeIds.Server_ServerStatus_CurrentTime
        );

        monitoredItem.create();

        // already created, can't create again
        assertThrows(UaException.class, monitoredItem::create);
    }

    @Test
    void deleteMonitoredItem() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            subscription,
            NodeIds.Server_ServerStatus_CurrentTime
        );

        monitoredItem.create();
        monitoredItem.delete();
    }

    @Test
    void deleteMonitoredItem_InvalidState() throws UaException, InterruptedException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            subscription,
            NodeIds.Server_ServerStatus_CurrentTime
        );

        // doesn't exist yet
        assertThrows(UaException.class, monitoredItem::delete);

        try {
            monitoredItem.deleteAsync().toCompletableFuture().get();
        } catch (ExecutionException e) {
            UaException uax = UaException.extract(e).orElseThrow();
            assertEquals(StatusCodes.Bad_InvalidState, uax.getStatusCode().getValue());
        }

        monitoredItem.create();
        monitoredItem.delete();

        // already deleted, can't delete again
        assertThrows(UaException.class, monitoredItem::delete);
    }

    @Test
    void modifyMonitoredItem_SamplingInterval() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            subscription,
            NodeIds.Server_ServerStatus_CurrentTime
        );

        monitoredItem.create();

        monitoredItem.setSamplingInterval(5000.0);
        monitoredItem.modify();

        assertEquals(5000.0, monitoredItem.getRequestedSamplingInterval());
        assertEquals(5000.0, monitoredItem.getRevisedSamplingInterval().orElseThrow());
    }

    @Test
    void modifyMonitoredItem_QueueSize() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            subscription,
            NodeIds.Server_ServerStatus_CurrentTime
        );

        monitoredItem.create();

        monitoredItem.setRequestedQueueSize(uint(10));
        monitoredItem.modify();

        assertEquals(uint(10), monitoredItem.getRequestedQueueSize());
        assertEquals(uint(10), monitoredItem.getRevisedQueueSize().orElseThrow());
    }

    @Test
    void modifyMonitoredItem_InvalidState() throws UaException {
        var monitoredItem = OpcUaMonitoredItem.newDataItem(
            subscription,
            NodeIds.Server_ServerStatus_CurrentTime
        );

        // doesn't exist yet
        assertThrows(UaException.class, monitoredItem::modify);

        monitoredItem.create();
        monitoredItem.delete();

        // already deleted, can't modify
        assertThrows(UaException.class, monitoredItem::modify);
    }

}
