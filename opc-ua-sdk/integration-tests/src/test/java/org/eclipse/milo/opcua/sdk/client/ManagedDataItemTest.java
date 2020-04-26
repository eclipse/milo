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
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ManagedDataItemTest extends AbstractSubscriptionTest {

    @Test
    public void delete() throws UaException {
        ManagedDataItem dataItem = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );
        assertTrue(dataItem.getStatusCode().isGood());

        StatusCode deleteResult = dataItem.delete();

        assertTrue(deleteResult.isGood());
        assertFalse(subscription.getSubscription().getMonitoredItems().contains(dataItem.getMonitoredItem()));
    }

    @Test
    public void getStatusCode() throws UaException {
        ManagedDataItem dataItem1 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        assertTrue(dataItem1.getStatusCode().isGood());

        ManagedDataItem dataItem2 = subscription.createDataItem(NodeId.parse("ns=2;s=FooBarDoesNotExist"));
        assertEquals(StatusCodes.Bad_NodeIdUnknown, dataItem2.getStatusCode().getValue());
    }

    @Test
    public void setSamplingInterval() throws Exception {
        ManagedDataItem dataItem1 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        assertEquals(subscription.getDefaultSamplingInterval(), dataItem1.getMonitoredItem().getRequestedSamplingInterval());
        assertEquals(subscription.getDefaultSamplingInterval(), dataItem1.getMonitoredItem().getRevisedSamplingInterval());

        assertEquals(5000.0, dataItem1.setSamplingInterval(5000.0));
        assertEquals(5000.0, dataItem1.getMonitoredItem().getRequestedSamplingInterval());
        assertEquals(5000.0, dataItem1.getMonitoredItem().getRevisedSamplingInterval());
    }

    @Test
    public void setSamplingIntervalBatch() throws Exception {
        ManagedDataItem dataItem1 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        ManagedDataItem dataItem2 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);

        // ModifyMonitoredItemsBatch batch = BatchManager.newModifyMonitoredItemsBatch();
        // dataItem1.setSamplingInterval(5000.0, batch);
        // dataItem2.setSamplingInterval(5000.0, batch);
        // batch.execute();

        assertEquals(5000.0, dataItem1.getMonitoredItem().getRequestedSamplingInterval());
        assertEquals(5000.0, dataItem1.getMonitoredItem().getRevisedSamplingInterval());
        assertEquals(5000.0, dataItem2.getMonitoredItem().getRequestedSamplingInterval());
        assertEquals(5000.0, dataItem2.getMonitoredItem().getRevisedSamplingInterval());

        // BatchManager.executeModifyMonitoredItemsBatch(batch -> {
        //     dataItem1.setSamplingInterval(1000.0, batch);
        //     dataItem2.setSamplingInterval(1000.0, batch);
        // });

        assertEquals(1000.0, dataItem1.getMonitoredItem().getRequestedSamplingInterval());
        assertEquals(1000.0, dataItem1.getMonitoredItem().getRevisedSamplingInterval());
        assertEquals(1000.0, dataItem2.getMonitoredItem().getRequestedSamplingInterval());
        assertEquals(1000.0, dataItem2.getMonitoredItem().getRevisedSamplingInterval());
    }

    @Test
    public void dataValueListener() throws UaException, InterruptedException {
        ManagedDataItem dataItem = subscription.createDataItem(
            Identifiers.Server_ServerStatus_CurrentTime
        );

        final CountDownLatch latch = new CountDownLatch(1);

        dataItem.addDataValueListener((item, value) -> {
            assertEquals(dataItem, item);
            latch.countDown();
        });

        assertTrue(latch.await(5, TimeUnit.SECONDS));
    }

}
