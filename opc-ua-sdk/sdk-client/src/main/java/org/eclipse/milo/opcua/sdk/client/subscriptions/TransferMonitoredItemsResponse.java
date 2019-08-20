/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.subscriptions;

import java.util.List;

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class TransferMonitoredItemsResponse {

    private final List<UaMonitoredItem> monitoredItems;
    private final List<StatusCode> statusCodes;

    public TransferMonitoredItemsResponse() {
        this.monitoredItems = null;
        this.statusCodes = null;
    }

    public TransferMonitoredItemsResponse(
        List<UaMonitoredItem> monitoredItems,
        List<StatusCode> statusCodes) {
        this.monitoredItems = monitoredItems;
        this.statusCodes = statusCodes;
    }

    List<UaMonitoredItem> getMonitoredItems() {
        return monitoredItems;
    }

    List<StatusCode> getStatusCodes() {
        return statusCodes;
    }
}
