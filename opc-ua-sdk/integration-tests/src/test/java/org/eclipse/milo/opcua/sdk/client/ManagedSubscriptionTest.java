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


import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.ManagedDataItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.ManagedEventItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DeadbandType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.DataChangeFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManagedSubscriptionTest extends AbstractSubscriptionTest {

    @Test
    public void createAndDeleteDataItem() throws UaException {
        ManagedDataItem dataItem = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );

        assertTrue(dataItem.getStatusCode().isGood());
        assertTrue(subscription.deleteDataItem(dataItem).isGood());
    }

    @Test
    public void createAndDeleteEventItem() throws UaException {
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
        assertTrue(subscription.deleteEventItem(eventItem).isGood());
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
    public void defaultSamplingInterval() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(subscription.getDefaultSamplingInterval(), dataItem1.getMonitoredItem().getRequestedSamplingInterval());
        assertEquals(subscription.getDefaultSamplingInterval(), dataItem1.getMonitoredItem().getRevisedSamplingInterval());

        subscription.setDefaultSamplingInterval(100.0);
        ManagedDataItem dataItem2 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(subscription.getDefaultSamplingInterval(), dataItem2.getMonitoredItem().getRequestedSamplingInterval());
        assertEquals(subscription.getDefaultSamplingInterval(), dataItem2.getMonitoredItem().getRevisedSamplingInterval());

        subscription.setDefaultSamplingInterval(5000.0);
        ManagedDataItem dataItem3 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(subscription.getDefaultSamplingInterval(), dataItem3.getMonitoredItem().getRequestedSamplingInterval());
        assertEquals(subscription.getDefaultSamplingInterval(), dataItem3.getMonitoredItem().getRevisedSamplingInterval());
    }

    @Test
    public void defaultQueueSize() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(subscription.getDefaultQueueSize(), dataItem1.getMonitoredItem().getRequestedQueueSize());
        assertEquals(subscription.getDefaultQueueSize(), dataItem1.getMonitoredItem().getRevisedQueueSize());

        subscription.setDefaultQueueSize(uint(subscription.getDefaultQueueSize().intValue() + 1));
        ManagedDataItem dataItem2 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(subscription.getDefaultQueueSize(), dataItem2.getMonitoredItem().getRequestedQueueSize());
        assertEquals(subscription.getDefaultQueueSize(), dataItem2.getMonitoredItem().getRevisedQueueSize());
    }

    @Test
    public void defaultDataFilter() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(subscription.getDefaultDataFilter(), dataItem1.getMonitoredItem().getMonitoringFilter());

        subscription.setDefaultDataFilter(
            new DataChangeFilter(
                DataChangeTrigger.StatusValueTimestamp,
                uint(DeadbandType.None.getValue()),
                0.0
            )
        );

        ManagedDataItem dataItem2 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );

        assertEquals(subscription.getDefaultDataFilter(), dataItem2.getMonitoredItem().getMonitoringFilter());
    }

    @Test
    public void defaultTimestamp() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(subscription.getDefaultTimestamps(), dataItem1.getMonitoredItem().getTimestamps());

        subscription.setDefaultTimestamps(TimestampsToReturn.Neither);
        ManagedDataItem dataItem2 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(subscription.getDefaultTimestamps(), dataItem2.getMonitoredItem().getTimestamps());
    }

    @Test
    public void dataChangeListener() throws UaException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);

        subscription.addChangeListener(new ManagedSubscription.ChangeListener() {
            @Override
            public void onDataReceived(List<ManagedDataItem> dataItems, List<DataValue> dataValues) {
                if (dataItems.get(0).getNodeId().equals(Identifiers.Server_ServerStatus_State)) {
                    latch.countDown();
                }
            }
        });

        subscription.addDataChangeListener(
            (dataItems, dataValues) -> {
                if (dataItems.get(0).getNodeId().equals(Identifiers.Server_ServerStatus_State)) {
                    latch.countDown();
                }
            }
        );

        ManagedDataItem dataItem = subscription.createDataItem(Identifiers.Server_ServerStatus_State);
        assertTrue(dataItem.getStatusCode().isGood());

        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }

    @Test
    public void eventChangeListener() throws UaException, InterruptedException {
        final CountDownLatch latch1 = new CountDownLatch(1);
        final CountDownLatch latch2 = new CountDownLatch(1);

        subscription.addChangeListener(new ManagedSubscription.ChangeListener() {
            @Override
            public void onEventReceived(List<ManagedEventItem> eventItems, List<Variant[]> eventFields) {
                if (eventItems.get(0).getNodeId().equals(Identifiers.Server)) {
                    latch1.countDown();
                }
            }
        });

        subscription.addEventChangeListener(
            (eventItems, eventFields) -> {
                if (eventItems.get(0).getNodeId().equals(Identifiers.Server)) {
                    latch2.countDown();
                }
            }
        );

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

        assertTrue(latch1.await(5, TimeUnit.SECONDS));
        assertTrue(latch2.await(5, TimeUnit.SECONDS));
    }

}
