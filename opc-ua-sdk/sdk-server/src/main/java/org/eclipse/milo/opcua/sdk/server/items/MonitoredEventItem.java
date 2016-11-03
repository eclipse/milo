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

package org.eclipse.milo.opcua.sdk.server.items;

import org.eclipse.milo.opcua.sdk.server.api.EventItem;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.BaseEventType;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFieldList;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class MonitoredEventItem extends BaseMonitoredItem<Variant[]> implements EventItem {

    private volatile EventFilter filter;

    public MonitoredEventItem(
        UInteger id,
        UInteger subscriptionId,
        ReadValueId readValueId,
        MonitoringMode monitoringMode,
        TimestampsToReturn timestamps,
        UInteger clientHandle,
        double samplingInterval,
        UInteger queueSize,
        boolean discardOldest,
        ExtensionObject filter) throws UaException {

        super(id, subscriptionId, readValueId, monitoringMode,
            timestamps, clientHandle, samplingInterval, queueSize, discardOldest);

        installFilter(filter);
    }

    @Override
    public void setEvent(BaseEventType event) {
        // TODO Apply EventFilter...

        Variant[] variants = new Variant[]{
            new Variant(event.getEventId()),
            new Variant(event.getEventType()),
            new Variant(event.getSourceNode()),
            new Variant(event.getSourceNode()),
            new Variant(event.getTime())
        };

        enqueue(variants);
    }

    @Override
    protected void enqueue(Variant[] value) {
        if (queueSize < queue.maxSize()) {
            queue.add(value);
        } else {
            if (getQueueSize() > 1) {
                // TODO Send an EventQueueOverflowEventType...
            }
            if (discardOldest) {
                queue.add(value);
            } else {
                queue.set(queue.maxSize() - 1, value);
            }
        }
    }

    @Override
    public ExtensionObject getFilterResult() {
        return null;
    }

    @Override
    protected void installFilter(ExtensionObject filterXo) throws UaException {

    }

    @Override
    protected EventFieldList wrapQueueValue(Variant[] value) {
        return new EventFieldList(uint(getClientHandle()), value);
    }

    @Override
    public boolean isSamplingEnabled() {
        return getMonitoringMode() != MonitoringMode.Disabled;
    }

}
