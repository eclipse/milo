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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.sdk.client.subscriptions.ManagedSubscription.ChangeListener;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DeadbandType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.DataChangeFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
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

        subscription.deleteDataItem(dataItem);
        assertFalse(subscription.getDataItems().contains(dataItem));
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

        subscription.deleteEventItem(eventItem);
        assertFalse(subscription.getEventItems().contains(eventItem));
    }

    @Test
    public void createWithSamplingInterval() throws UaException {
        ReadValueId readValueId = new ReadValueId(
            Identifiers.Server_ServerStatus_State,
            AttributeId.Value.uid(),
            null,
            QualifiedName.NULL_VALUE
        );

        ManagedDataItem dataItem = subscription.createDataItem(5000.0, readValueId);

        assertEquals(dataItem.getSamplingInterval(), 5000.0);
        assertEquals(dataItem.getMonitoredItem().getRequestedSamplingInterval(), 5000.0);
        assertEquals(dataItem.getMonitoredItem().getRevisedSamplingInterval(), 5000.0);
    }

    @Test
    public void setPublishingInterval() throws UaException {
        double original = subscription.getPublishingInterval();

        double revised1 = subscription.setPublishingInterval(100.0);
        assertEquals(revised1, 100.0);

        double revised2 = subscription.setPublishingInterval(original);
        assertEquals(revised2, original);
    }

    @Test
    public void setPublishingMode() throws UaException {
        subscription.setPublishingEnabled(false);
        assertFalse(subscription.isPublishingEnabled());

        subscription.setPublishingEnabled(true);
        assertTrue(subscription.isPublishingEnabled());
    }

    @Test
    public void defaultMonitoringMode() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem1.getMonitoringMode(), MonitoringMode.Reporting);
        assertEquals(dataItem1.getMonitoredItem().getMonitoringMode(), MonitoringMode.Reporting);

        subscription.setDefaultMonitoringMode(MonitoringMode.Sampling);

        ManagedDataItem dataItem2 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem2.getMonitoringMode(), MonitoringMode.Sampling);
        assertEquals(dataItem2.getMonitoredItem().getMonitoringMode(), MonitoringMode.Sampling);

        subscription.setDefaultMonitoringMode(MonitoringMode.Disabled);

        ManagedDataItem dataItem3 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem3.getMonitoringMode(), MonitoringMode.Disabled);
        assertEquals(dataItem3.getMonitoredItem().getMonitoringMode(), MonitoringMode.Disabled);
    }

    @Test
    public void defaultSamplingInterval() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem1.getMonitoredItem().getRequestedSamplingInterval(), subscription.getDefaultSamplingInterval());
        assertEquals(dataItem1.getMonitoredItem().getRevisedSamplingInterval(), subscription.getDefaultSamplingInterval());

        subscription.setDefaultSamplingInterval(100.0);
        ManagedDataItem dataItem2 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem2.getMonitoredItem().getRequestedSamplingInterval(), subscription.getDefaultSamplingInterval());
        assertEquals(dataItem2.getMonitoredItem().getRevisedSamplingInterval(), subscription.getDefaultSamplingInterval());

        subscription.setDefaultSamplingInterval(5000.0);
        ManagedDataItem dataItem3 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem3.getMonitoredItem().getRequestedSamplingInterval(), subscription.getDefaultSamplingInterval());
        assertEquals(dataItem3.getMonitoredItem().getRevisedSamplingInterval(), subscription.getDefaultSamplingInterval());
    }

    @Test
    public void defaultQueueSize() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem1.getMonitoredItem().getRequestedQueueSize(), subscription.getDefaultQueueSize());
        assertEquals(dataItem1.getMonitoredItem().getRevisedQueueSize(), subscription.getDefaultQueueSize());

        subscription.setDefaultQueueSize(uint(subscription.getDefaultQueueSize().intValue() + 1));
        ManagedDataItem dataItem2 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem2.getMonitoredItem().getRequestedQueueSize(), subscription.getDefaultQueueSize());
        assertEquals(dataItem2.getMonitoredItem().getRevisedQueueSize(), subscription.getDefaultQueueSize());
    }

    @Test
    public void defaultDataFilter() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem1.getMonitoredItem().getMonitoringFilter(), subscription.getDefaultDataFilter());

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

        assertEquals(dataItem2.getMonitoredItem().getMonitoringFilter(), subscription.getDefaultDataFilter());
    }

    @Test
    public void defaultTimestamp() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem1.getMonitoredItem().getTimestamps(), subscription.getDefaultTimestamps());

        subscription.setDefaultTimestamps(TimestampsToReturn.Neither);
        ManagedDataItem dataItem2 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertEquals(dataItem2.getMonitoredItem().getTimestamps(), subscription.getDefaultTimestamps());
    }

    @Test
    public void defaultDiscardOldest() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertTrue(subscription.getDefaultDiscardOldest());
        assertTrue(dataItem1.getDiscardOldest());
        assertTrue(dataItem1.getMonitoredItem().getDiscardOldest());

        subscription.setDefaultDiscardOldest(false);
        assertFalse(subscription.getDefaultDiscardOldest());

        ManagedDataItem dataItem2 = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertFalse(dataItem2.getDiscardOldest());
        assertFalse(dataItem2.getMonitoredItem().getDiscardOldest());
    }

    @Test
    public void dataChangeListener() throws UaException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(2);

        subscription.addChangeListener(new ChangeListener() {
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

        subscription.addChangeListener(new ChangeListener() {
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

    @Test
    public void addRemoveChangeListener() {
        {
            ChangeListener changeListener = new ChangeListener() {
                @Override
                public void onDataReceived(List<ManagedDataItem> dataItems, List<DataValue> dataValues) {}

                @Override
                public void onEventReceived(List<ManagedEventItem> eventItems, List<Variant[]> eventFields) {}

                @Override
                public void onKeepAliveReceived() {}
            };

            subscription.addChangeListener(changeListener);

            assertTrue(subscription.removeChangeListener(changeListener));
        }

        {
            ChangeListener changeListener = subscription.addDataChangeListener((items, values) -> {});

            assertTrue(subscription.removeChangeListener(changeListener));
        }

        {
            ChangeListener changeListener = subscription.addEventChangeListener((items, events) -> {});

            assertTrue(subscription.removeChangeListener(changeListener));
        }
    }

    @Test
    public void addRemoveStatusListener() {
        ManagedSubscription.StatusListener statusListener = new ManagedSubscription.StatusListener() {
            @Override
            public void onNotificationDataLost(ManagedSubscription subscription) {}

            @Override
            public void onSubscriptionStatusChanged(ManagedSubscription subscription, StatusCode statusCode) {}

            @Override
            public void onSubscriptionTransferFailed(ManagedSubscription subscription, StatusCode statusCode) {}
        };

        subscription.addStatusListener(statusListener);

        assertTrue(subscription.removeStatusListener(statusListener));
    }

}
