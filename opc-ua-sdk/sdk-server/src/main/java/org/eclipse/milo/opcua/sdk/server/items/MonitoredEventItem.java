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

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.OpcUaServer;
import org.eclipse.milo.opcua.sdk.server.Session;
import org.eclipse.milo.opcua.sdk.server.api.EventItem;
import org.eclipse.milo.opcua.sdk.server.events.EventContentFilter;
import org.eclipse.milo.opcua.sdk.server.events.FilterContext;
import org.eclipse.milo.opcua.sdk.server.model.nodes.objects.BaseEventNode;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.ContentFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFieldList;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilter;
import org.eclipse.milo.opcua.stack.core.types.structured.EventFilterResult;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;
import org.eclipse.milo.opcua.stack.core.types.structured.SimpleAttributeOperand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

public class MonitoredEventItem extends BaseMonitoredItem<Variant[]> implements EventItem {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private volatile EventFilter filter;
    private volatile EventFilterResult filterResult;

    private final FilterContext filterContext;

    public MonitoredEventItem(
        OpcUaServer server,
        Session session,
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

        super(server, session, id, subscriptionId, readValueId, monitoringMode,
            timestamps, clientHandle, samplingInterval, queueSize, discardOldest);
        
        filterContext = new FilterContext() {
            @Override
            public OpcUaServer getServer() {
                return server;
            }

            @Override
            public Optional<Session> getSession() {
                return Optional.of(session);
            }
        };

        installFilter(filter);
    }

    @Override
    public void onEvent(BaseEventNode eventNode) {
        try {
            ContentFilter whereClause = filter.getWhereClause();

            boolean matches = EventContentFilter.evaluate(
                filterContext,
                whereClause,
                eventNode
            );

            if (matches) {
                SimpleAttributeOperand[] selectClauses = filter.getSelectClauses();

                if (selectClauses != null) {
                    Variant[] variants = EventContentFilter.select(
                        filterContext,
                        selectClauses,
                        eventNode
                    );

                    enqueue(variants);
                }
            }
        } catch (UaException e) {
            logger.error("Filter evaluation failed: {}", e.getMessage(), e);
        }
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
        return ExtensionObject.encode(filterResult);
    }

    @Override
    protected void installFilter(ExtensionObject filterXo) throws UaException {
        // TODO Is there a "default" EventFilter like there is for DataChangeFilter?

        Object filterObject = filterXo != null ? filterXo.decode() : null;

        if (filterObject instanceof EventFilter) {
            this.filter = (EventFilter) filterObject;

            filterResult = EventContentFilter.validate(filterContext, filter);
        } else {
            throw new UaException(StatusCodes.Bad_MonitoredItemFilterInvalid);
        }
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
