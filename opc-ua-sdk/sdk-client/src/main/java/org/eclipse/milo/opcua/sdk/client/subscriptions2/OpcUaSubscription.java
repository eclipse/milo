/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions2;

import java.math.BigInteger;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcUaSubscription {

    private volatile State state = State.INITIAL;

    private ModificationDiff modificationDiff = null;

    private double requestedPublishingInterval = 1000.0;
    private UInteger requestedLifetimeCount;
    private UInteger requestedMaxKeepAliveCount;

    private double revisedPublishingInterval;
    private UInteger revisedLifetimeCount;
    private UInteger revisedMaxKeepAliveCount;
    private UInteger maxNotificationsPerPublish;
    private UByte priority;

    private boolean lifetimeAndKeepAliveCalculated = true;

    private final OpcUaClient client;

    public OpcUaSubscription(OpcUaClient client) {
        this.client = client;
    }

    public OpcUaSubscription(OpcUaClient client, double requestedPublishingInterval) {
        this.client = client;
        this.requestedPublishingInterval = requestedPublishingInterval;
    }

    /**
     * Get the client this Subscription belongs to.
     *
     * @return the {@link OpcUaClient} this Subscription belongs to.
     */
    public OpcUaClient getClient() {
        return client;
    }

    public void create() throws UaException {}

    public void delete() throws UaException {}

    public void modify() throws UaException {}

    public void setPublishingInterval(double publishingInterval) {
        if (state == State.INITIAL) {
            this.requestedPublishingInterval = publishingInterval;
        } else {
            if (modificationDiff == null) {
                modificationDiff = new ModificationDiff();
            }
            modificationDiff.requestedPublishingInterval = publishingInterval;
        }

        if (lifetimeAndKeepAliveCalculated) {
            UInteger maxKeepAliveCount = calculateMaxKeepAliveCount(publishingInterval);
            UInteger lifetimeCount = calculateLifetimeCount(maxKeepAliveCount);
            
            setMaxKeepAliveCount(maxKeepAliveCount);
            setLifetimeCount(lifetimeCount);
        }
    }

    public void setLifetimeCount(UInteger lifetimeCount) {
        if (state == State.INITIAL) {
            this.requestedLifetimeCount = lifetimeCount;
        } else {
            if (modificationDiff == null) {
                modificationDiff = new ModificationDiff();
            }
            modificationDiff.requestedLifetimeCount = lifetimeCount;
        }
    }

    public void setMaxKeepAliveCount(UInteger maxKeepAliveCount) {
        if (state == State.INITIAL) {
            this.requestedMaxKeepAliveCount = maxKeepAliveCount;
        } else {
            if (modificationDiff == null) {
                modificationDiff = new ModificationDiff();
            }
            modificationDiff.requestedMaxKeepAliveCount = maxKeepAliveCount;
        }
    }

    private UInteger calculateMaxKeepAliveCount(double publishingInterval) {
        // Send a keep-alive every 10 seconds if the publishing interval is faster than
        // 10 seconds, or every publishing interval otherwise.
        int count = (int) Math.ceil(10000.0 / Math.max(1, publishingInterval));

        return uint(Math.max(1, count));
    }

    private UInteger calculateLifetimeCount(UInteger maxKeepAliveCount) {
        // Lifetime must be 3x (or greater) the keep-alive count.
        BigInteger lifetimeCount = maxKeepAliveCount.toBigInteger()
            .multiply(BigInteger.valueOf(5))
            .min(BigInteger.valueOf(UInteger.MAX_VALUE));

        return uint(lifetimeCount.longValue());
    }

    private static class ModificationDiff {
        private Double requestedPublishingInterval;
        private UInteger requestedLifetimeCount;
        private UInteger requestedMaxKeepAliveCount;
        private UInteger maxNotificationsPerPublish;
        private UByte priority;
    }

    enum State {

        /**
         * The Subscription has been instantiated but does not exist on the server.
         */
        INITIAL,

        /**
         * The Subscription has been created on the server and has no outstanding modifications to
         * synchronize.
         */
        SYNCHRONIZED,

        /**
         * The Subscription has been created on the server but has outstanding modifications to
         * synchronize.
         */
        UNSYNCHRONIZED

    }

}
