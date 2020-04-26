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

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.ManagedSubscription;
import org.eclipse.milo.opcua.sdk.test.ClientServerTest;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractSubscriptionTest extends ClientServerTest {

    protected ManagedSubscription subscription;

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

}
