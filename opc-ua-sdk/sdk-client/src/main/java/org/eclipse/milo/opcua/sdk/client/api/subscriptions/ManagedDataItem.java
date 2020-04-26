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
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.subscriptions.OpcUaMonitoredItem;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.structured.ReadValueId;

public class ManagedDataItem {

    private final OpcUaClient client;
    private final OpcUaMonitoredItem item;

    ManagedDataItem(OpcUaClient client, OpcUaMonitoredItem item) {
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

    public void setSamplingInterval(double samplingInterval) {

    }

    public void addDataValueListener(Consumer<DataValue> dataValueListener) {
        addDataValueListener((context, item, value) -> dataValueListener.accept(value));
    }

    public void addDataValueListener(BiConsumer<ManagedDataItem, DataValue> dataValueListener) {
        addDataValueListener((context, item, value) -> dataValueListener.accept(item, value));
    }

    public void addDataValueListener(DataValueListener dataValueListener) {

    }

    public StatusCode delete() {
        return null;
    }

    public CompletableFuture<StatusCode> deleteAsync() {
        return null;
    }

    public interface DataValueListener {

        void onDataValueReceived(SerializationContext context, ManagedDataItem item, DataValue value);

    }

}
