/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.client.api.subscriptions;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.types.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

public interface UaMonitoredItem {

    /**
     * Get the client-assigned id.
     * <p>
     * This handle is used in the subscription to match incoming values to the corresponding monitored item.
     *
     * @return the client-assigned id.
     */
    UInteger getClientHandle();

    /**
     * Get the server-assigned id.
     *
     * @return the server-assigned id.
     */
    UInteger getMonitoredItemId();

    /**
     * Get the {@link ReadValueId}.
     *
     * @return the {@link ReadValueId}.
     */
    ReadValueId getReadValueId();

    /**
     * Get the {@link StatusCode} of the last operation.
     *
     * @return the {@link StatusCode} of the last operation.
     */
    StatusCode getStatusCode();

    /**
     * Get the last requested sampling interval.
     *
     * @return the last requested sampling interval.
     */
    double getRequestedSamplingInterval();

    /**
     * Get the revised sampling interval.
     *
     * @return the revised sampling interval.
     */
    double getRevisedSamplingInterval();

    /**
     * Get the last requested queue size.
     *
     * @return the last requested queue size.
     */
    UInteger getRequestedQueueSize();

    /**
     * Get the revised queue size.
     *
     * @return the revised queue size.
     */
    UInteger getRevisedQueueSize();

    /**
     * Get the filter result {@link ExtensionObject}.
     *
     * @return the filter result {@link ExtensionObject}.
     */
    ExtensionObject getFilterResult();

    /**
     * Get the {@link MonitoringMode}.
     *
     * @return the {@link MonitoringMode}.
     */
    MonitoringMode getMonitoringMode();

    /**
     * Get the filter requested when the item was created.
     * <p>
     * May be null if no filter was requested.
     *
     * @return the filter requested when the item was created. May be null if no filter was requested.
     */
    ExtensionObject getMonitoringFilter();

    /**
     * Set the {@link Consumer} that will receive values as they arrive from the server.
     *
     * @param valueConsumer the {@link Consumer} that will receive values as they arrive from the server.
     */
    void setValueConsumer(Consumer<DataValue> valueConsumer);

    /**
     * Set a {@link BiConsumer} that will receive values as they arrive from the server.
     * <p>
     * The {@link UaMonitoredItem} in the consumer will be this item.
     *
     * @param valueConsumer the {@link BiConsumer} that will receive values as they arrive from the server.
     */
    void setValueConsumer(BiConsumer<UaMonitoredItem, DataValue> valueConsumer);

    /**
     * Set a {@link ValueConsumer} that will receive values as they arrive from the server.
     * <p>
     * The {@link UaMonitoredItem} in the consumer will be this item.
     *
     * @param valueConsumer the {@link ValueConsumer} that will receive values as they arrive from the server.
     */
    void setValueConsumer(ValueConsumer valueConsumer);

    /**
     * Set the {@link Consumer} that will receive events as they arrive from the server.
     *
     * @param eventConsumer the {@link Consumer} that will receive events as they arrive from the server.
     */
    void setEventConsumer(Consumer<Variant[]> eventConsumer);

    /**
     * Set the {@link BiConsumer} that will receive events as they arrive from the server.
     * <p>
     * The {@link UaMonitoredItem} in the consumer will be this item.
     *
     * @param eventConsumer the {@link BiConsumer} that will receive events as they arrive from the server.
     */
    void setEventConsumer(BiConsumer<UaMonitoredItem, Variant[]> eventConsumer);

    /**
     * Set the {@link EventConsumer} that will receive events as they arrive from the server.
     *
     * @param eventConsumer the {@link EventConsumer} that will receive events as they arrive from the server.
     */
    void setEventConsumer(EventConsumer eventConsumer);

    interface ValueConsumer {

        /**
         * A new value has arrived for the {@link UaMonitoredItem} {@code item}.
         *
         * @param dataTypeManager the {@link DataTypeManager} from {@link OpcUaClient#getDataTypeManager()}.
         * @param item            the {@link UaMonitoredItem} this value is for.
         * @param value           the new {@link DataValue}.
         */
        void onValueArrived(DataTypeManager dataTypeManager, UaMonitoredItem item, DataValue value);

    }

    interface EventConsumer {

        /**
         * A new event has arrived for the {@link UaMonitoredItem} {@code item}.
         *
         * @param dataTypeManager the {@link DataTypeManager} from {@link OpcUaClient#getDataTypeManager()}.
         * @param item            the {@link UaMonitoredItem} this event is for.
         * @param eventValues     the event values.
         */
        void onEventArrived(DataTypeManager dataTypeManager, UaMonitoredItem item, Variant[] eventValues);

    }

}
