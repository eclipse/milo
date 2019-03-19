/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.api.services;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.server.api.DataItem;
import org.eclipse.milo.opcua.sdk.server.api.EventItem;
import org.eclipse.milo.opcua.sdk.server.api.MonitoredItem;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

public interface MonitoredItemServices {

    /**
     * A {@link DataItem} is being created for a Node managed by this {@link MonitoredItemServices}.
     * <p>
     * This is a chance to revise the requested queue size and/or sampling interval.
     * <p>
     * The sampling interval has already been revised to fit within the configured server limits and to be at least the
     * value of the Minimum Sampling Interval attribute for the Node if it was present.
     *
     * @param itemToMonitor             the item that will be monitored.
     * @param requestedQueueSize        the requested queue size.
     * @param requestedSamplingInterval the requested sampling interval.
     * @param revisionCallback          the callback to invoke to revise the sampling interval and queue size.
     */
    default void onCreateDataItem(
        @SuppressWarnings("unused") ReadValueId itemToMonitor,
        Double requestedSamplingInterval,
        UInteger requestedQueueSize,
        BiConsumer<Double, UInteger> revisionCallback) {

        revisionCallback.accept(requestedSamplingInterval, requestedQueueSize);
    }

    /**
     * A {@link DataItem} is being modified for a Node managed by this {@link MonitoredItemServices}.
     * <p>
     * This is a chance to revise the requested queue size and/or sampling interval.
     * <p>
     * The sampling interval has already been revised to fit within the configured server limits and to be at least the
     * value of the Minimum Sampling Interval attribute for the Node if it was present.
     *
     * @param itemToModify              the item that will be modified.
     * @param requestedQueueSize        the requested queue size.
     * @param requestedSamplingInterval the requested sampling interval.
     * @param revisionCallback          the callback to invoke to revise the sampling interval and queue size.
     */
    default void onModifyDataItem(
        @SuppressWarnings("unused") ReadValueId itemToModify,
        Double requestedSamplingInterval,
        UInteger requestedQueueSize,
        BiConsumer<Double, UInteger> revisionCallback) {

        revisionCallback.accept(requestedSamplingInterval, requestedQueueSize);
    }

    /**
     * An {@link EventItem} is being created for a Node managed by this {@link MonitoredItemServices}.
     * <p>
     * This is a chance to revise the requested queue size.
     *
     * @param itemToMonitor      the item that will be monitored.
     * @param requestedQueueSize the requested queue size.
     * @param revisionCallback   the callback to invoke to revise the queue size.
     */
    default void onCreateEventItem(
        @SuppressWarnings("unused") ReadValueId itemToMonitor,
        UInteger requestedQueueSize,
        Consumer<UInteger> revisionCallback) {

        revisionCallback.accept(requestedQueueSize);
    }

    /**
     * An {@link EventItem} is being modified for a Node managed by this {@link MonitoredItemServices}.
     * <p>
     * This is a chance to revise the requested queue size.
     *
     * @param itemToModify       the item that will be modified.
     * @param requestedQueueSize the requested queue size.
     * @param revisionCallback   the callback to invoke to revise the queue size.
     */
    default void onModifyEventItem(
        @SuppressWarnings("unused") ReadValueId itemToModify,
        UInteger requestedQueueSize,
        Consumer<UInteger> revisionCallback) {

        revisionCallback.accept(requestedQueueSize);
    }

    /**
     * {@link DataItem}s have been created for nodes belonging to this {@link MonitoredItemServices}.
     * <p>
     * If sampling is enabled for this item, it is expected that a best-effort will be made to update the item's value
     * at the sampling rate.
     *
     * @param dataItems the {@link DataItem}s that were created.
     */
    void onDataItemsCreated(List<DataItem> dataItems);

    /**
     * {@link DataItem}s have been modified for nodes belonging to this {@link MonitoredItemServices}.
     * <p>
     * Check to see if the sampling rate has changed or if sampling has been enabled or disabled.
     *
     * @param dataItems the {@link DataItem}s that were modified.
     */
    void onDataItemsModified(List<DataItem> dataItems);

    /**
     * {@link DataItem}s have been deleted for nodes belonging to this {@link MonitoredItemServices}.
     * <p>
     * Updates to this item should cease and any references to it should be removed.
     *
     * @param dataItems the {@link DataItem}s that were deleted.
     */
    void onDataItemsDeleted(List<DataItem> dataItems);

    /**
     * {@link EventItem}s have been created for nodes belonging to this {@link MonitoredItemServices}.
     *
     * @param eventItems the {@link EventItem}s that were created.
     */
    default void onEventItemsCreated(List<EventItem> eventItems) {}

    /**
     * {@link EventItem}s have been modified for nodes belonging to this {@link MonitoredItemServices}.
     *
     * @param eventItems the {@link EventItem}s that were modified.
     */
    default void onEventItemsModified(List<EventItem> eventItems) {}

    /**
     * {@link EventItem}s have been deleted for nodes belonging to this {@link MonitoredItemServices}.
     *
     * @param eventItems the {@link EventItem}s that were deleted.
     */
    default void onEventItemsDeleted(List<EventItem> eventItems) {}

    /**
     * {@link MonitoredItem}s have had their {@link MonitoringMode} modified by a client.
     * <p>
     * Check if sampling is still enabled and react accordingly.
     *
     * @param monitoredItems The {@link MonitoredItem}s whose {@link MonitoringMode} was modified.
     */
    void onMonitoringModeChanged(List<MonitoredItem> monitoredItems);

}
