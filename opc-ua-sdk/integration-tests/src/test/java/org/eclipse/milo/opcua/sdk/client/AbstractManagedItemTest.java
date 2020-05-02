/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client;

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.ManagedItem;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.junit.jupiter.api.Test;

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

        assertFalse(subscription.getSubscription().getMonitoredItems().contains(managedItem.getMonitoredItem()));
    }

    @Test
    public void monitoringMode() throws UaException {
        ManagedItem managedItem = createManagedItem();
        assertEquals(MonitoringMode.Reporting, managedItem.getMonitoringMode());

        managedItem.setMonitoringMode(MonitoringMode.Sampling);
        assertEquals(MonitoringMode.Sampling, managedItem.getMonitoringMode());

        managedItem.setMonitoringMode(MonitoringMode.Disabled);
        assertEquals(MonitoringMode.Disabled, managedItem.getMonitoringMode());

        managedItem.setMonitoringMode(MonitoringMode.Reporting);
        assertEquals(MonitoringMode.Reporting, managedItem.getMonitoringMode());
    }

    @Test
    public void queueSize() throws UaException {
        UInteger initialQueueSize = subscription.getDefaultQueueSize();

        ManagedItem managedItem = createManagedItem();
        assertEquals(initialQueueSize, managedItem.getQueueSize());

        UInteger newQueueSize = initialQueueSize.add(1);
        assertEquals(newQueueSize, managedItem.setQueueSize(newQueueSize));
        assertEquals(newQueueSize, managedItem.getQueueSize());
    }

    @Test
    public void timestampsToReturn() throws UaException {
        TimestampsToReturn timestamps = subscription.getDefaultTimestamps();

        ManagedItem managedItem = createManagedItem();
        assertEquals(timestamps, managedItem.getTimestampsToReturn());

        TimestampsToReturn newTimestamps = TimestampsToReturn.Neither;
        assertNotEquals(timestamps, newTimestamps);

        managedItem.setTimestampsToReturn(newTimestamps);
        assertEquals(newTimestamps, managedItem.getTimestampsToReturn());
    }

    @Test
    public void discardOldest() throws UaException {
        boolean defaultDiscardOldest = subscription.getDefaultDiscardOldest();

        ManagedItem managedItem = createManagedItem();

        assertEquals(defaultDiscardOldest, managedItem.getDiscardOldest());

        managedItem.setDiscardOldest(!defaultDiscardOldest);
        assertEquals(!defaultDiscardOldest, managedItem.getDiscardOldest());
    }

}
