/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.subscriptions;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.util.Unit;

public class ManagedEventItem extends ManagedItem {

    private final CopyOnWriteArrayList<EventValueListener> eventValueListeners = new CopyOnWriteArrayList<>();

    private UaMonitoredItem.EventConsumer eventConsumer = null;

    public ManagedEventItem(OpcUaClient client, ManagedSubscription subscription, OpcUaMonitoredItem monitoredItem) {
        super(client, subscription, monitoredItem);
    }

    @Override
    public CompletableFuture<Unit> deleteAsync() {
        return subscription.deleteEventItemAsync(this);
    }

    //region EventValueListener bookkeeping

    /**
     * Add an event field value {@link Consumer} to this {@link ManagedDataItem}.
     * <p>
     * {@code consumer} will be invoked any time new event field values arrive for this item.
     * <p>
     * The Consumer is transformed into the returned {@link EventValueListener} that can later be removed.
     *
     * @param consumer an event field value {@link Consumer}.
     * @return an {@link EventValueListener} that can later be removed.
     */
    public synchronized EventValueListener addEventValueListener(Consumer<Variant[]> consumer) {
        EventValueListener eventValueListener = (item, fields) -> consumer.accept(fields);

        addEventValueListener(eventValueListener);

        return eventValueListener;
    }

    /**
     * Add an {@link EventValueListener} to this {@link ManagedEventItem}.
     * <p>
     * {code eventValueListener} will be invoked any time new event field values arrive for this item.
     *
     * @param eventValueListener the {@link EventValueListener} to add.
     */
    public synchronized void addEventValueListener(EventValueListener eventValueListener) {
        eventValueListeners.add(eventValueListener);

        if (eventConsumer == null) {
            eventConsumer = new ManagedEventConsumer();
            monitoredItem.setEventConsumer(eventConsumer);
        }
    }

    /**
     * Remove an {@link EventValueListener} from this {@link ManagedEventItem}.
     *
     * @param eventValueListener the {@link EventValueListener} to remove.
     * @return {@code true} if the listener was removed.
     */
    public synchronized boolean removeEventValueListener(EventValueListener eventValueListener) {
        boolean removed = eventValueListeners.remove(eventValueListener);

        if (eventValueListeners.isEmpty()) {
            monitoredItem.setEventConsumer((UaMonitoredItem.EventConsumer) null);
            eventConsumer = null;
        }

        return removed;
    }

    //endregion

    /**
     * A callback that receives notification of new events for a {@link ManagedEventItem}.
     */
    public interface EventValueListener {

        /**
         * A new event for {@code item} has arrived.
         * <p>
         * Take care not to block unnecessarily in this callback because subscription notifications are processed
         * synchronously as a backpressure mechanism. Blocking inside this callback will prevent subsequent
         * notifications from being processed and new PublishRequests from being sent.
         *
         * @param item        the {@link ManagedEventItem} for which a new event has arrived.
         * @param eventValues the new event field values.
         */
        void onEventValueReceived(ManagedEventItem item, Variant[] eventValues);

    }

    private class ManagedEventConsumer implements UaMonitoredItem.EventConsumer {
        @Override
        public void onEventArrived(SerializationContext context, UaMonitoredItem item, Variant[] eventValues) {
            eventValueListeners.forEach(
                eventValueListener ->
                    eventValueListener.onEventValueReceived(ManagedEventItem.this, eventValues)
            );
        }
    }

}
