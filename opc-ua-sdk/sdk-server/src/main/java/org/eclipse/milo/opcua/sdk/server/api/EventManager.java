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

package org.eclipse.milo.opcua.sdk.server.api;

import java.util.List;

import org.eclipse.milo.opcua.sdk.server.items.MonitoredEventItem;

public interface EventManager {

    /**
     * {@link MonitoredEventItem} have been created for nodes belonging to this {#link EventManager}.
     *
     * @param eventItems the {@link MonitoredEventItem}s that were created.
     */
    void onEventItemsCreated(List<MonitoredEventItem> eventItems);

    void onEventItemsModified(List<MonitoredEventItem> eventItems);

    void onEventItemsDeleted(List<MonitoredEventItem> eventItems);

    void onMonitoringModeChanged(List<MonitoredEventItem> eventItems);

}
