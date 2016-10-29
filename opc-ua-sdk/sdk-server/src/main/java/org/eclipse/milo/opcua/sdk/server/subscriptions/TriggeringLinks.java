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

package org.eclipse.milo.opcua.sdk.server.subscriptions;

import java.util.Map;

import com.google.common.collect.Maps;
import org.eclipse.milo.opcua.sdk.server.items.BaseMonitoredItem;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class TriggeringLinks {

    private final Map<UInteger, BaseMonitoredItem<?>> triggeredItems = Maps.newConcurrentMap();

    private final BaseMonitoredItem<?> triggeringItem;

    public TriggeringLinks(BaseMonitoredItem<?> triggeringItem) {
        this.triggeringItem = triggeringItem;
    }

    public BaseMonitoredItem<?> getTriggeringItem() {
        return triggeringItem;
    }

    public Map<UInteger, BaseMonitoredItem<?>> getTriggeredItems() {
        return triggeredItems;
    }

    public boolean isEmpty() {
        return triggeredItems.isEmpty();
    }

}
