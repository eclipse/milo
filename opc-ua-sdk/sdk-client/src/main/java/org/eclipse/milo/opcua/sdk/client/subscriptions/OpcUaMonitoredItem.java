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

package org.eclipse.milo.opcua.sdk.client.subscriptions;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class OpcUaMonitoredItem implements UaMonitoredItem {

    private volatile BiConsumer<UaMonitoredItem, DataValue> valueConsumer;
    private volatile BiConsumer<UaMonitoredItem, Variant[]> eventConsumer;

    private volatile StatusCode statusCode;
    private volatile double revisedSamplingInterval = 0.0;
    private volatile UInteger revisedQueueSize = uint(0);
    private volatile ExtensionObject filterResult;
    private volatile MonitoringMode monitoringMode = MonitoringMode.Disabled;

    private final UInteger clientHandle;
    private final ReadValueId readValueId;
    private final UInteger monitoredItemId;

    public OpcUaMonitoredItem(
        UInteger clientHandle,
        ReadValueId readValueId,
        UInteger monitoredItemId,
        StatusCode statusCode,
        double revisedSamplingInterval,
        UInteger revisedQueueSize,
        ExtensionObject filterResult,
        MonitoringMode monitoringMode) {

        this.clientHandle = clientHandle;
        this.readValueId = readValueId;
        this.monitoredItemId = monitoredItemId;
        this.statusCode = statusCode;
        this.revisedSamplingInterval = revisedSamplingInterval;
        this.revisedQueueSize = revisedQueueSize;
        this.filterResult = filterResult;
        this.monitoringMode = monitoringMode;
    }

    @Override
    public UInteger getClientHandle() {
        return clientHandle;
    }

    @Override
    public ReadValueId getReadValueId() {
        return readValueId;
    }

    @Override
    public UInteger getMonitoredItemId() {
        return monitoredItemId;
    }

    @Override
    public StatusCode getStatusCode() {
        return statusCode;
    }

    @Override
    public double getRevisedSamplingInterval() {
        return revisedSamplingInterval;
    }

    @Override
    public UInteger getRevisedQueueSize() {
        return revisedQueueSize;
    }

    @Override
    public ExtensionObject getFilterResult() {
        return filterResult;
    }

    @Override
    public MonitoringMode getMonitoringMode() {
        return monitoringMode;
    }

    @Override
    public void setValueConsumer(Consumer<DataValue> consumer) {
        this.valueConsumer = (item, value) -> consumer.accept(value);
    }

    @Override
    public void setValueConsumer(BiConsumer<UaMonitoredItem, DataValue> valueBiConsumer) {
        this.valueConsumer = valueBiConsumer;
    }

    @Override
    public void setEventConsumer(Consumer<Variant[]> consumer) {
        this.eventConsumer = (item, event) -> consumer.accept(event);
    }

    @Override
    public void setEventConsumer(BiConsumer<UaMonitoredItem, Variant[]> eventBiConsumer) {
        this.eventConsumer = eventBiConsumer;
    }

    void setStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    void setFilterResult(ExtensionObject filterResult) {
        this.filterResult = filterResult;
    }

    void setRevisedSamplingInterval(double revisedSamplingInterval) {
        this.revisedSamplingInterval = revisedSamplingInterval;
    }

    void setRevisedQueueSize(UInteger revisedQueueSize) {
        this.revisedQueueSize = revisedQueueSize;
    }

    void setMonitoringMode(MonitoringMode monitoringMode) {
        this.monitoringMode = monitoringMode;
    }

    void onValueArrived(DataValue value) {
        BiConsumer<UaMonitoredItem, DataValue> c = valueConsumer;
        if (c != null) c.accept(this, value);
    }

    void onEventArrived(Variant[] values) {
        BiConsumer<UaMonitoredItem, Variant[]> c = eventConsumer;
        if (c != null) c.accept(this, values);
    }

}
