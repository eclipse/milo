/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.api.subscriptions;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

public class ManagedEventItem {

    private final OpcUaClient client;
    private final OpcUaMonitoredItem item;

    public ManagedEventItem(OpcUaClient client, OpcUaMonitoredItem item) {
        this.client = client;
        this.item = item;
    }

    public NodeId getNodeId() {
        return getReadValueId().getNodeId();
    }

    public ReadValueId getReadValueId() {
        return item.getReadValueId();
    }

    public StatusCode getStatusCode() {
        return item.getStatusCode();
    }

    public OpcUaMonitoredItem getMonitoredItem() {
        return item;
    }

    public StatusCode delete() {
        return null;
    }

    public CompletableFuture<StatusCode> deleteAsync() {
        return null;
    }

    /**
     * A callback that receives notification of new events for a {@link ManagedEventItem}.
     */
    public interface EventValueListener {

        /**
         * A new event for {@code item} has arrived.
         * <p>
         * Take care not to block unnecessarily in this callback because subscription notifications are processed
         * synchronously as a backpressure mechanism. Blocking inside this callback will prevent subsequent
         * notifications from being processed and new PublishRequests from being sent.
         *
         * @param item        the {@link ManagedEventItem} for which a new event has arrived.
         * @param eventValues the new event field values.
         */
        void onEventValueReceived(ManagedEventItem item, Variant[] eventValues);

    }

}
