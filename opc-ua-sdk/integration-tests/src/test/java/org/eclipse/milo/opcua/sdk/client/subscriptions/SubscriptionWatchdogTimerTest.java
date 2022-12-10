/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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

import org.eclipse.milo.opcua.sdk.client.subscriptions.UaSubscriptionManager.SubscriptionListener;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class SubscriptionWatchdogTimerTest extends AbstractSubscriptionTest {

    @Disabled("run this test manually")
    @Test
    public void testSubscriptionWatchdogTimer() throws UaException, InterruptedException {
        ManagedDataItem dataItem = subscription.createDataItem(
            NodeIds.Server_ServerStatus_State
        );

        assertTrue(dataItem.getStatusCode().isGood());

        CountDownLatch latch = new CountDownLatch(2);

        subscription.addStatusListener(new ManagedSubscription.StatusListener() {
            @Override
            public void onSubscriptionWatchdogTimerElapsed(ManagedSubscription subscription) {
                System.out.println("onWatchdogTimerElapsed() id=" + subscription.getSubscription().getSubscriptionId());
                latch.countDown();
            }
        });

        client.getSubscriptionManager().addSubscriptionListener(new SubscriptionListener() {
            @Override
            public void onSubscriptionWatchdogTimerElapsed(UaSubscription subscription) {
                System.out.println("onWatchdogTimerElapsed() id=" + subscription.getSubscriptionId());
                latch.countDown();
            }
        });

        UInteger subscriptionId = subscription.getSubscription().getSubscriptionId();
        server.getSubscriptions().get(subscriptionId).deleteSubscription();
        System.out.printf("deleted subscription id=%s on server...%n", subscriptionId);

        System.out.println("waiting for watchdog timer to elapse...");
        assertTrue(latch.await(15, TimeUnit.SECONDS));
    }

}
