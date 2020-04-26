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


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.ManagedEventItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.sdk.test.ClientServerTest;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManagedSubscriptionTest extends ClientServerTest {

    private ManagedSubscription subscription;

    @BeforeEach
    private void createSubscription() throws UaException {
        subscription = ManagedSubscription.create(client);

        System.out.println(String.format(
            "created ManagedSubscription id=%s",
            subscription.getSubscription().getSubscriptionId()
        ));
    }

    @AfterEach
    private void deleteSubscription() throws UaException {
        if (subscription != null) {
            System.out.println(String.format(
                "deleting ManagedSubscription id=%s",
                subscription.getSubscription().getSubscriptionId()
            ));

            assertTrue(subscription.delete().isGood());
            subscription = null;
        }
    }

    @Test
    public void createAndDeleteDataItem() throws UaException {
        ManagedDataItem dataItem = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );

        assertTrue(dataItem.getStatusCode().isGood());

        assertTrue(subscription.deleteDataItem(dataItem).isGood());
    }

    @Test
    public void setPublishingInterval() throws UaException {
        double original = subscription.getPublishingInterval();

        double revised1 = subscription.setPublishingInterval(100.0);
        assertEquals(100.0, revised1);

        double revised2 = subscription.setPublishingInterval(original);
        assertEquals(original, revised2);
    }

    @Test
    public void setPublishingMode() throws UaException {
        subscription.setPublishingMode(false);
        assertFalse(subscription.isPublishingEnabled());

        subscription.setPublishingMode(true);
        assertTrue(subscription.isPublishingEnabled());
    }

    @Test
    public void testDataListener() throws UaException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        subscription.addDataListener((dataItems, dataValues) -> {
            if (dataItems.get(0).getNodeId().equals(Identifiers.Server_ServerStatus_CurrentTime)) {
                latch.countDown();
            }
        });

        ManagedDataItem dataItem = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        assertTrue(dataItem.getStatusCode().isGood());

        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void testEventListener() throws UaException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        subscription.addEventListener((eventItems, eventFields) -> {
            if (eventItems.get(0).getNodeId().equals(Identifiers.Server)) {
                latch.countDown();
            }
        });

        EventFilter eventFilter = new EventFilter(
            new SimpleAttributeOperand[]{
                new SimpleAttributeOperand(
                    Identifiers.BaseEventType,
                    new QualifiedName[]{new QualifiedName(0, "EventId")},
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

        ManagedEventItem eventItem = subscription.createEventItem(Identifiers.Server, eventFilter);
        assertTrue(eventItem.getStatusCode().isGood());

        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }

}
