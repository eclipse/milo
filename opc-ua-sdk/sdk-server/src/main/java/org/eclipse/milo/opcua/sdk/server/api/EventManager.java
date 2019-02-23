/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
