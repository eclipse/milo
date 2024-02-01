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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
            subscription,
            NodeIds.Server_ServerStatus_CurrentTime
        );

        try {
            StatusCode result = monitoredItem.create();

            assertEquals(StatusCode.GOOD, result);
        } finally {
            monitoredItem.delete();
        }
    }

}
