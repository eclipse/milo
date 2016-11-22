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

package org.eclipse.milo.opcua.sdk.client.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ViewNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;

import static org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.valueOnly;

public class UaViewNode extends UaNode implements ViewNode {

    public UaViewNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<Boolean> getContainsNoLoops() {
        return getAttributeOrFail(readContainsNoLoops());
    }

    @Override
    public CompletableFuture<UByte> getEventNotifier() {
        return getAttributeOrFail(readEventNotifier());
    }

    @Override
    public CompletableFuture<StatusCode> setContainsNoLoops(boolean containsNoLoops) {
        return writeContainsNoLoops(valueOnly(new Variant(containsNoLoops)));
    }

    @Override
    public CompletableFuture<StatusCode> setEventNotifier(UByte eventNotifier) {
        return writeEventNotifier(valueOnly(new Variant(eventNotifier)));
    }

    @Override
    public CompletableFuture<DataValue> readContainsNoLoops() {
        return readAttribute(AttributeId.ContainsNoLoops);
    }

    @Override
    public CompletableFuture<DataValue> readEventNotifier() {
        return readAttribute(AttributeId.EventNotifier);
    }

    @Override
    public CompletableFuture<StatusCode> writeContainsNoLoops(DataValue value) {
        return writeAttribute(AttributeId.ContainsNoLoops, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeEventNotifier(DataValue value) {
        return writeAttribute(AttributeId.EventNotifier, value);
    }

}
