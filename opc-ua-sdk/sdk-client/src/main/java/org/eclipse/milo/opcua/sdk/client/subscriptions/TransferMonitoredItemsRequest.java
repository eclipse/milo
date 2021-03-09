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
import java.util.Optional;

public class TransferMonitoredItemsRequest {

    private final List<MonitoredItemTransferRequest> monitoredItems;
    private final ItemTransferredCallback itemTransferCallback;

    public TransferMonitoredItemsRequest(
        List<MonitoredItemTransferRequest> monitoredItems,
        ItemTransferredCallback itemTransferCallback
    ) {

        this.monitoredItems = monitoredItems;
        this.itemTransferCallback = itemTransferCallback;
    }

    List<MonitoredItemTransferRequest> getMonitoredItems() {
        return monitoredItems;
    }

    Optional<ItemTransferredCallback> getItemTransferCallback() {
        return Optional.ofNullable(itemTransferCallback);
    }

}



