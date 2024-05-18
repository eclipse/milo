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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManagedEventItemTest extends AbstractManagedItemTest {

    private final EventFilter eventFilter = new EventFilter(
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

    @Override
    protected ManagedEventItem createManagedItem() throws UaException {
        return subscription.createEventItem(Identifiers.Server, eventFilter);
    }

    @Test
    public void getStatusCode() throws UaException {
        ManagedEventItem dataItem1 = createManagedItem();
        assertTrue(dataItem1.getStatusCode().isGood());

        ManagedEventItem dataItem2 = subscription.createEventItem(
            NodeId.parse("ns=2;s=FooBarDoesNotExist"),
            eventFilter
        );
        assertEquals(dataItem2.getStatusCode().getValue(), StatusCodes.Bad_NodeIdUnknown);
    }

    @Test
    public void eventValueListener() throws UaException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);

        ManagedEventItem eventItem = subscription.createEventItem(
            Identifiers.Server,
            eventFilter,
            item -> {
                item.addEventValueListener((i, value) -> latch.countDown());

                ManagedEventItem.EventValueListener eventValueListener = (i, value) -> latch.countDown();
                item.addEventValueListener(eventValueListener);
            }
        );

        assertTrue(eventItem.getStatusCode().isGood());

        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void addRemoveEventValueListener() throws UaException {
        ManagedEventItem eventItem = subscription.createEventItem(Identifiers.Server, eventFilter);

        ManagedEventItem.EventValueListener listener = (item, value) -> {};

        eventItem.addEventValueListener(listener);
        assertTrue(eventItem.removeEventValueListener(listener));

        listener = eventItem.addEventValueListener(value -> {});
        assertTrue(eventItem.removeEventValueListener(listener));
    }

}
