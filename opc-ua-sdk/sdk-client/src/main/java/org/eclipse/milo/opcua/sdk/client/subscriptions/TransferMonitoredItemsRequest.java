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
import java.util.function.BiConsumer;

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;

public class TransferMonitoredItemsRequest {

    private final List<MonitoredItemTransferRequest> monitoredItems;
    private final BiConsumer<UaMonitoredItem, Integer> itemTransferCallback;

    public TransferMonitoredItemsRequest(
        List<MonitoredItemTransferRequest> monitoredItems,
        BiConsumer<UaMonitoredItem, Integer> itemTransferCallback
    ) {

        this.monitoredItems = monitoredItems;
        this.itemTransferCallback = itemTransferCallback;
    }

    List<MonitoredItemTransferRequest> getMonitoredItems() {
        return monitoredItems;
    }

    Optional<BiConsumer<UaMonitoredItem, Integer>> getBiConsumer() {
        return Optional.ofNullable(itemTransferCallback);
    }

    Optional<UaSubscription.ItemTransferredCallback> getItemTransferCallback() {
        if (itemTransferCallback == null) return Optional.empty();
        else {
            return Optional.of((serializationContext, item, index) -> itemTransferCallback.accept(item, index));
        }
    }

}



