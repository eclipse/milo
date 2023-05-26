/*
 * Copyright (c) 2023 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions2;

import java.math.BigInteger;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.CreateSubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.types.structured.ModifySubscriptionResponse;
import org.eclipse.milo.opcua.stack.core.util.Unit;
import org.jetbrains.annotations.Nullable;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcUaSubscription {

    private static final double DEFAULT_PUBLISHING_INTERVAL = 1000.0;
    private static final UInteger DEFAULT_MAX_NOTIFICATIONS_PER_PUBLISH = uint(65535);
    private static final UByte DEFAULT_PRIORITY = ubyte(0);

    private State state = State.INITIAL;

    private ModificationDiff diff = null;

    private Double requestedPublishingInterval = DEFAULT_PUBLISHING_INTERVAL;
    private UInteger requestedMaxKeepAliveCount = calculateMaxKeepAliveCount(requestedPublishingInterval);
    private UInteger requestedLifetimeCount = calculateLifetimeCount(requestedMaxKeepAliveCount);
    private UInteger maxNotificationsPerPublish = DEFAULT_MAX_NOTIFICATIONS_PER_PUBLISH;
    private UByte priority = DEFAULT_PRIORITY;

    private UInteger subscriptionId;
    private Double revisedPublishingInterval;
    private UInteger revisedLifetimeCount;
    private UInteger revisedMaxKeepAliveCount;

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

    public void create() throws UaException {
        try {
            createAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public synchronized CompletableFuture<Unit> createAsync() {
        if (state == State.INITIAL) {
            if (requestedMaxKeepAliveCount == null) {
                requestedMaxKeepAliveCount = calculateMaxKeepAliveCount(requestedPublishingInterval);
            }
            if (requestedLifetimeCount == null) {
                requestedLifetimeCount = calculateLifetimeCount(requestedMaxKeepAliveCount);
            }

            CompletableFuture<CreateSubscriptionResponse> future = client.createSubscriptionAsync(
                requestedPublishingInterval,
                requestedLifetimeCount,
                requestedMaxKeepAliveCount,
                maxNotificationsPerPublish,
                true,
                priority
            );

            return future.thenCompose(response -> {
                synchronized (OpcUaSubscription.this) {
                    subscriptionId = response.getSubscriptionId();
                    revisedPublishingInterval = response.getRevisedPublishingInterval();
                    revisedLifetimeCount = response.getRevisedLifetimeCount();
                    revisedMaxKeepAliveCount = response.getRevisedMaxKeepAliveCount();

                    state = State.SYNCHRONIZED;
                }

                return CompletableFuture.completedFuture(Unit.VALUE);
            });
        } else {
            return CompletableFuture.completedFuture(Unit.VALUE);
        }
    }

    public void delete() throws UaException {
        try {
            deleteAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        } finally {
            // TODO should there be a State.DELETED? or should subscription's be re-usable?
            state = State.INITIAL;
        }
    }

    public CompletableFuture<Unit> deleteAsync() {
        return null; // TODO
    }

    public void modify() throws UaException {
        try {
            modifyAsync().get();
        } catch (ExecutionException | InterruptedException e) {
            throw UaException.extract(e)
                .orElse(new UaException(StatusCodes.Bad_UnexpectedError, e));
        }
    }

    public synchronized CompletableFuture<Unit> modifyAsync() {
        if (state == State.INITIAL) {
            return CompletableFuture.failedFuture(new UaException(StatusCodes.Bad_InvalidState));
        } else if (state == State.SYNCHRONIZED) {
            return CompletableFuture.completedFuture(Unit.VALUE);
        } else {
            assert diff != null;

            CompletableFuture<ModifySubscriptionResponse> future = client.modifySubscriptionAsync(
                subscriptionId,
                diff.requestedPublishingInterval().orElse(revisedPublishingInterval),
                diff.requestedLifetimeCount().orElse(revisedLifetimeCount),
                diff.requestedMaxKeepAliveCount().orElse(revisedMaxKeepAliveCount),
                diff.maxNotificationsPerPublish().orElse(maxNotificationsPerPublish),
                diff.priority().orElse(priority)
            );

            return future.thenCompose(response -> {
                synchronized (OpcUaSubscription.this) {
                    requestedPublishingInterval = diff.requestedPublishingInterval().orElse(requestedPublishingInterval);
                    requestedLifetimeCount = diff.requestedLifetimeCount().orElse(requestedLifetimeCount);
                    requestedMaxKeepAliveCount = diff.requestedMaxKeepAliveCount().orElse(requestedMaxKeepAliveCount);
                    maxNotificationsPerPublish = diff.maxNotificationsPerPublish().orElse(maxNotificationsPerPublish);
                    priority = diff.priority().orElse(priority);

                    revisedPublishingInterval = response.getRevisedPublishingInterval();
                    revisedLifetimeCount = response.getRevisedLifetimeCount();
                    revisedMaxKeepAliveCount = response.getRevisedMaxKeepAliveCount();

                    diff = null;
                    state = State.SYNCHRONIZED;
                }

                return CompletableFuture.completedFuture(Unit.VALUE);
            });
        }
    }

    public State getState() {
        return state;
    }

    public Double getRequestedPublishingInterval() {
        return requestedPublishingInterval;
    }

    public UInteger getRequestedLifetimeCount() {
        return requestedLifetimeCount;
    }

    public UInteger getRequestedMaxKeepAliveCount() {
        return requestedMaxKeepAliveCount;
    }

    /**
     * Get the SubscriptionId assigned to this Subscription by the Server.
     * <p>
     * The SubscriptionId is available only after the Subscription has been created.
     *
     * @return the SubscriptionId assigned to this Subscription by the Server.
     */
    public Optional<UInteger> getSubscriptionId() {
        return Optional.ofNullable(subscriptionId);
    }

    /**
     * Get the revised PublishingInterval from the most recent create or modify operation.
     * <p>
     * The revised PublishingInterval is available only after the Subscription has been created
     * or modified.
     *
     * @return the revised PublishingInterval from the most recent create or modify operation.
     */
    public Optional<Double> getRevisedPublishingInterval() {
        return Optional.ofNullable(revisedPublishingInterval);
    }

    /**
     * Get the revised LifetimeCount from the most recent create or modify operation.
     * <p>
     * The revised LifetimeCount is available only after the Subscription has been created or
     * modified.
     *
     * @return the revised LifetimeCount from the most recent create or modify operation.
     */
    public Optional<UInteger> getRevisedLifetimeCount() {
        return Optional.ofNullable(revisedLifetimeCount);
    }

    /**
     * Get the revised MaxKeepAliveCount from the most recent create or modify operation.
     * <p>
     * The revised MaxKeepAliveCount is available only after the Subscription has been created
     * or modified.
     *
     * @return the revised MaxKeepAliveCount from the most recent create or modify operation.
     */
    public Optional<UInteger> getRevisedMaxKeepAliveCount() {
        return Optional.ofNullable(revisedMaxKeepAliveCount);
    }

    /**
     * Get the Subscription's Priority setting.
     * <p>
     * The Server does not revise this setting, so the value reflects the most recently requested
     * by a create or modify operation.
     *
     * @return the Subscription's Priority setting.
     */
    public UByte getPriority() {
        return priority;
    }

    /**
     * Get the Subscription's MaxNotificationsPerPublish setting.
     * <p>
     * The Server does not revise this setting, so the value reflects the most recently requested
     * by a create or modify operation.
     *
     * @return the Subscription's MaxNotificationsPerPublish setting.
     */
    public UInteger getMaxNotificationsPerPublish() {
        return maxNotificationsPerPublish;
    }

    public synchronized void setPublishingInterval(Double publishingInterval) {
        if (state == State.INITIAL) {
            this.requestedPublishingInterval = publishingInterval;
        } else {
            if (diff == null) {
                diff = new ModificationDiff();
            }
            diff.requestedPublishingInterval = publishingInterval;

            state = State.UNSYNCHRONIZED;
        }

        if (lifetimeAndKeepAliveCalculated) {
            UInteger maxKeepAliveCount = calculateMaxKeepAliveCount(publishingInterval);
            UInteger lifetimeCount = calculateLifetimeCount(maxKeepAliveCount);

            setMaxKeepAliveCount(maxKeepAliveCount);
            setLifetimeCount(lifetimeCount);
        }
    }

    public synchronized void setLifetimeCount(UInteger lifetimeCount) {
        if (state == State.INITIAL) {
            this.requestedLifetimeCount = lifetimeCount;
        } else {
            if (diff == null) {
                diff = new ModificationDiff();
            }
            diff.requestedLifetimeCount = lifetimeCount;

            state = State.UNSYNCHRONIZED;
        }
    }

    public synchronized void setMaxKeepAliveCount(UInteger maxKeepAliveCount) {
        if (state == State.INITIAL) {
            this.requestedMaxKeepAliveCount = maxKeepAliveCount;
        } else {
            if (diff == null) {
                diff = new ModificationDiff();
            }
            diff.requestedMaxKeepAliveCount = maxKeepAliveCount;

            state = State.UNSYNCHRONIZED;
        }
    }

    public synchronized void setPriority(UByte priority) {
        if (state == State.INITIAL) {
            this.priority = priority;
        } else {
            if (diff == null) {
                diff = new ModificationDiff();
            }
            diff.priority = priority;

            state = State.UNSYNCHRONIZED;
        }
    }

    public synchronized void setMaxNotificationsPerPublish(UInteger maxNotificationsPerPublish) {
        if (state == State.INITIAL) {
            this.maxNotificationsPerPublish = maxNotificationsPerPublish;
        } else {
            if (diff == null) {
                diff = new ModificationDiff();
            }
            diff.maxNotificationsPerPublish = maxNotificationsPerPublish;

            state = State.UNSYNCHRONIZED;
        }
    }

    private static UInteger calculateMaxKeepAliveCount(double publishingInterval) {
        // Send a keep-alive every 10 seconds if the publishing interval is faster than
        // 10 seconds, or every publishing interval otherwise.
        int count = (int) Math.ceil(10000.0 / Math.max(1, publishingInterval));

        return uint(Math.max(1, count));
    }

    private static UInteger calculateLifetimeCount(UInteger maxKeepAliveCount) {
        // Lifetime must be 3x (or greater) the keep-alive count.
        BigInteger lifetimeCount = maxKeepAliveCount.toBigInteger()
            .multiply(BigInteger.valueOf(5))
            .min(BigInteger.valueOf(UInteger.MAX_VALUE));

        return uint(lifetimeCount.longValue());
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", OpcUaSubscription.class.getSimpleName() + "[", "]")
            .add("subscriptionId=" + subscriptionId)
            .add("state=" + state)
            .toString();
    }

    private static class ModificationDiff {
        private @Nullable Double requestedPublishingInterval;
        private @Nullable UInteger requestedLifetimeCount;
        private @Nullable UInteger requestedMaxKeepAliveCount;
        private @Nullable UInteger maxNotificationsPerPublish;
        private @Nullable UByte priority;

        private Optional<Double> requestedPublishingInterval() {
            return Optional.ofNullable(requestedPublishingInterval);
        }

        private Optional<UInteger> requestedLifetimeCount() {
            return Optional.ofNullable(requestedLifetimeCount);
        }

        private Optional<UInteger> requestedMaxKeepAliveCount() {
            return Optional.ofNullable(requestedMaxKeepAliveCount);
        }

        private Optional<UInteger> maxNotificationsPerPublish() {
            return Optional.ofNullable(maxNotificationsPerPublish);
        }

        private Optional<UByte> priority() {
            return Optional.ofNullable(priority);
        }
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
