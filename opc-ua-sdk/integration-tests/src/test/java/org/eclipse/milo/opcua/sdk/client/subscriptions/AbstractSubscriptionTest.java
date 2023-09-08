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

import org.eclipse.milo.opcua.sdk.test.AbstractClientServerTest;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;


public abstract class AbstractSubscriptionTest extends AbstractClientServerTest {

    protected ManagedSubscription subscription;

    @BeforeEach
    public void createSubscription() throws UaException {
        subscription = ManagedSubscription.create(client);

        System.out.printf(
            "created ManagedSubscription id=%s%n",
            subscription.getSubscription().getSubscriptionId()
        );
    }

    @AfterEach
    public void deleteSubscription() throws UaException {
        if (subscription != null) {
            System.out.printf(
                "deleting ManagedSubscription id=%s%n",
                subscription.getSubscription().getSubscriptionId()
            );

            subscription.delete();
            subscription = null;
        }
    }

}
