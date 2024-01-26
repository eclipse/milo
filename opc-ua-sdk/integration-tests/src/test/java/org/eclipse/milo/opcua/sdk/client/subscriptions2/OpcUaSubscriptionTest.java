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

import org.eclipse.milo.opcua.sdk.client.subscriptions2.OpcUaSubscription.State;
import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpcUaSubscriptionTest extends AbstractClientServerTest {

    @Test
    void createSubscription() throws UaException {
        var subscription = new OpcUaSubscription(client);

        try {
            subscription.create();
            assertEquals(State.SYNCHRONIZED, subscription.getState());

            assertTrue(subscription.getSubscriptionId().isPresent());
            assertTrue(subscription.getRevisedLifetimeCount().isPresent());
            assertTrue(subscription.getRevisedMaxKeepAliveCount().isPresent());
            assertTrue(subscription.getRevisedPublishingInterval().isPresent());
        } finally {
            subscription.delete();
            assertEquals(State.INITIAL, subscription.getState());
        }
    }

    @Test
    void deleteSubscription() throws UaException {
        var subscription = new OpcUaSubscription(client);

        assertEquals(State.INITIAL, subscription.getState());
        subscription.delete(); // no-op if in INITIAL state
        assertEquals(State.INITIAL, subscription.getState());

        try {
            subscription.create();
            assertEquals(State.SYNCHRONIZED, subscription.getState());
        } finally {
            subscription.delete();
            assertEquals(State.INITIAL, subscription.getState());
        }
    }

    @Test
    void modifyPublishingInterval() throws UaException {
        var subscription = new OpcUaSubscription(client);

        try {
            subscription.create();
            assertEquals(State.SYNCHRONIZED, subscription.getState());

            Double newPublishingInterval = subscription.getRevisedPublishingInterval()
                .map(v -> v * 2.0)
                .orElseThrow();
            subscription.setPublishingInterval(newPublishingInterval);
            assertEquals(State.UNSYNCHRONIZED, subscription.getState());

            subscription.modify();
            assertEquals(State.SYNCHRONIZED, subscription.getState());
            assertEquals(newPublishingInterval, subscription.getRevisedPublishingInterval().get());
        } finally {
            subscription.delete();
            assertEquals(State.INITIAL, subscription.getState());
        }
    }

    @Test
    void modifyLifetimeCount() throws UaException {
        var subscription = new OpcUaSubscription(client);

        try {
            subscription.create();
            assertEquals(State.SYNCHRONIZED, subscription.getState());

            UInteger newLifetimeCount = subscription.getRevisedLifetimeCount()
                .map(v -> v.add(UInteger.valueOf(10)))
                .orElseThrow();
            subscription.setLifetimeCount(newLifetimeCount);
            assertEquals(State.UNSYNCHRONIZED, subscription.getState());

            subscription.modify();
            assertEquals(State.SYNCHRONIZED, subscription.getState());
            assertEquals(newLifetimeCount, subscription.getRevisedLifetimeCount().get());
        } finally {
            subscription.delete();
            assertEquals(State.INITIAL, subscription.getState());
        }
    }

    @Test
    void modifyMaxKeepAliveCount() throws UaException {
        var subscription = new OpcUaSubscription(client);

        try {
            subscription.create();
            assertEquals(State.SYNCHRONIZED, subscription.getState());

            UInteger newMaxKeepAliveCount = subscription.getRevisedMaxKeepAliveCount()
                .map(v -> v.add(UInteger.valueOf(10)))
                .orElseThrow();
            subscription.setMaxKeepAliveCount(newMaxKeepAliveCount);
            assertEquals(State.UNSYNCHRONIZED, subscription.getState());

            subscription.modify();
            assertEquals(State.SYNCHRONIZED, subscription.getState());
            assertEquals(newMaxKeepAliveCount, subscription.getRevisedMaxKeepAliveCount().get());
        } finally {
            subscription.delete();
            assertEquals(State.INITIAL, subscription.getState());
        }
    }

    @Test
    void modifyMaxNotificationsPerPublish() throws UaException {
        var subscription = new OpcUaSubscription(client);

        try {
            subscription.create();
            assertEquals(State.SYNCHRONIZED, subscription.getState());

            UInteger newMaxNotificationsPerPublish = subscription.getMaxNotificationsPerPublish()
                .add(UInteger.valueOf(10));
            subscription.setMaxNotificationsPerPublish(newMaxNotificationsPerPublish);
            assertEquals(State.UNSYNCHRONIZED, subscription.getState());

            subscription.modify();
            assertEquals(State.SYNCHRONIZED, subscription.getState());
            assertEquals(newMaxNotificationsPerPublish, subscription.getMaxNotificationsPerPublish());
        } finally {
            subscription.delete();
            assertEquals(State.INITIAL, subscription.getState());
        }
    }

    @Test
    void modifyPriority() throws UaException {
        var subscription = new OpcUaSubscription(client);

        try {
            subscription.create();
            assertEquals(State.SYNCHRONIZED, subscription.getState());

            UByte newPriority = subscription.getPriority()
                .add(UByte.valueOf(1));
            subscription.setPriority(newPriority);
            assertEquals(State.UNSYNCHRONIZED, subscription.getState());

            subscription.modify();
            assertEquals(State.SYNCHRONIZED, subscription.getState());
            assertEquals(newPriority, subscription.getPriority());
        } finally {
            subscription.delete();
            assertEquals(State.INITIAL, subscription.getState());
        }
    }

    @Test
    void lifetimeAndKeepAliveCalculation() throws UaException {
        var subscription = new OpcUaSubscription(client);

        try {
            subscription.create();
            assertEquals(State.SYNCHRONIZED, subscription.getState());

            // lifetime and keep alive are calculated
            {
                UInteger previousRequestedLifetimeCount = subscription.getRequestedLifetimeCount();
                UInteger previousMaxKeepAliveCount = subscription.getRequestedMaxKeepAliveCount();

                subscription.setPublishingInterval(60_000.0);

                subscription.modify();
                assertEquals(State.SYNCHRONIZED, subscription.getState());
                assertNotEquals(previousRequestedLifetimeCount, subscription.getRequestedLifetimeCount());
                assertNotEquals(previousMaxKeepAliveCount, subscription.getRequestedMaxKeepAliveCount());

                UInteger revisedLifetimeCount = subscription.getRevisedLifetimeCount().orElseThrow();
                UInteger revisedMaxKeepAliveCount = subscription.getRevisedMaxKeepAliveCount().orElseThrow();
                assertEquals(revisedLifetimeCount, subscription.getRequestedLifetimeCount());
                assertEquals(revisedMaxKeepAliveCount, subscription.getRequestedMaxKeepAliveCount());
            }

            // lifetime and keep alive are not calculated
            {
                subscription.setLifetimeAndKeepAliveCalculated(false);
                assertFalse(subscription.isLifetimeAndKeepAliveCalculated());

                UInteger requestedLifetimeCount = subscription.getRequestedLifetimeCount();
                UInteger requestedMaxKeepAliveCount = subscription.getRequestedMaxKeepAliveCount();
                subscription.setPublishingInterval(1000.0);

                subscription.modify();
                assertEquals(State.SYNCHRONIZED, subscription.getState());
                assertEquals(requestedLifetimeCount, subscription.getRequestedLifetimeCount());
                assertEquals(requestedMaxKeepAliveCount, subscription.getRequestedMaxKeepAliveCount());
            }
        } finally {
            subscription.delete();
            assertEquals(State.INITIAL, subscription.getState());
        }
    }

}
