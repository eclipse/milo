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

import org.eclipse.milo.opcua.sdk.client.subscriptions.BatchSetMonitoringMode.SetMonitoringModeResult;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BatchSetMonitoringModeTest extends AbstractSubscriptionTest {

    @Test
    public void multipleOperations() throws UaException, InterruptedException {
        ManagedDataItem item1 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        ManagedDataItem item2 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        ManagedDataItem item3 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);

        BatchSetMonitoringMode batch = new BatchSetMonitoringMode(
            client,
            subscription.getSubscription()
        );

        batch.add(item1.getMonitoredItem(), MonitoringMode.Sampling);
        batch.add(item2.getMonitoredItem(), MonitoringMode.Sampling);
        batch.add(item3.getMonitoredItem(), MonitoringMode.Sampling);

        List<SetMonitoringModeResult> results = batch.execute();

        for (SetMonitoringModeResult result : results) {
            assertTrue(result.isServiceResultGood());
            assertTrue(result.isOperationResultGood());
        }

        assertEquals(MonitoringMode.Sampling, item1.getMonitoringMode());
        assertEquals(MonitoringMode.Sampling, item2.getMonitoringMode());
        assertEquals(MonitoringMode.Sampling, item3.getMonitoringMode());

        assertEquals(1, batch.getServiceInvocationCount());
    }

    @Test
    public void multipleServiceInvocations() throws UaException, InterruptedException {
        ManagedDataItem item1 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        ManagedDataItem item2 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);
        ManagedDataItem item3 = subscription.createDataItem(Identifiers.Server_ServerStatus_CurrentTime);

        item1.setMonitoringMode(MonitoringMode.Disabled);
        item2.setMonitoringMode(MonitoringMode.Sampling);
        item3.setMonitoringMode(MonitoringMode.Reporting);

        BatchSetMonitoringMode batch = new BatchSetMonitoringMode(
            client,
            subscription.getSubscription()
        );

        batch.add(item1.getMonitoredItem(), MonitoringMode.Sampling);
        batch.add(item2.getMonitoredItem(), MonitoringMode.Reporting);
        batch.add(item3.getMonitoredItem(), MonitoringMode.Disabled);

        List<SetMonitoringModeResult> results = batch.execute();

        for (SetMonitoringModeResult result : results) {
            assertTrue(result.isServiceResultGood());
            assertTrue(result.isOperationResultGood());
        }

        assertEquals(MonitoringMode.Sampling, item1.getMonitoringMode());
        assertEquals(MonitoringMode.Reporting, item2.getMonitoringMode());
        assertEquals(MonitoringMode.Disabled, item3.getMonitoringMode());

        assertEquals(3, batch.getServiceInvocationCount());
    }

}
