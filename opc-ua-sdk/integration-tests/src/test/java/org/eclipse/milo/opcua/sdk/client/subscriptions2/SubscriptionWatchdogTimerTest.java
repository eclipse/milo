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

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SubscriptionWatchdogTimerTest extends AbstractClientServerTest {

    @Test
    void subscriptionWatchdogTimerCallback() throws UaException, InterruptedException {
        var subscription = new OpcUaSubscription(client);
        subscription.setTargetKeepAliveInterval(2000.0);
        subscription.create();

        var monitoredItem = OpcUaMonitoredItem.newDataItem(NodeIds.Server_ServerStatus_State);
        subscription.addMonitoredItem(monitoredItem);
        subscription.synchronizeMonitoredItems();

        var latch = new CountDownLatch(1);

        subscription.setSubscriptionListener(new OpcUaSubscription.SubscriptionListener() {
            @Override
            public void onWatchdogTimerElapsed(OpcUaSubscription subscription) {
                System.out.println(
                    "onWatchdogTimerElapsed() id=" +
                        subscription.getSubscriptionId().orElseThrow()
                );

                latch.countDown();
            }
        });

        UInteger subscriptionId = subscription.getSubscriptionId().orElseThrow();
        server.getSubscriptions().get(subscriptionId).deleteSubscription();

        assertTrue(latch.await(5, TimeUnit.SECONDS));

        subscription.delete();
    }

}
