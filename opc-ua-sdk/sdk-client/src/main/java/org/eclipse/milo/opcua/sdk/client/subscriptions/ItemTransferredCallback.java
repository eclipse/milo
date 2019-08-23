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

import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem;
import org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaSubscription;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;

import static org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem.EventConsumer;
import static org.eclipse.milo.opcua.sdk.client.api.subscriptions.UaMonitoredItem.ValueConsumer;

public interface ItemTransferredCallback {

    /**
     * {@code item} was successfully "transferred" to its destination {@link UaSubscription}.
     * <p>
     * This is the only chance to configure a new a {@link ValueConsumer} or {@link EventConsumer} for {@code item}
     * before it starts receiving data change notifications again.
     *
     * @param serializationContext the current {@link SerializationContext}.
     * @param item                 the {@link UaMonitoredItem} that was "transferred".
     * @param index                the index of this item relative to all the items being transferred.
     */
    void onItemTransferred(SerializationContext serializationContext, UaMonitoredItem item, int index);

}
