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
import org.eclipse.milo.opcua.sdk.client.api.nodes.MethodNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

import static org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.valueOnly;

public class UaMethodNode extends UaNode implements MethodNode {

    public UaMethodNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<Boolean> getExecutable() {
        return getAttributeOrFail(readExecutable());
    }

    @Override
    public CompletableFuture<Boolean> getUserExecutable() {
        return getAttributeOrFail(readUserExecutable());
    }

    @Override
    public CompletableFuture<StatusCode> setExecutable(boolean executable) {
        return writeExecutable(valueOnly(new Variant(executable)));
    }

    @Override
    public CompletableFuture<StatusCode> setUserExecutable(boolean userExecutable) {
        return writeUserExecutable(valueOnly(new Variant(userExecutable)));
    }

    @Override
    public CompletableFuture<DataValue> readExecutable() {
        return readAttribute(AttributeId.Executable);
    }

    @Override
    public CompletableFuture<DataValue> readUserExecutable() {
        return readAttribute(AttributeId.UserExecutable);
    }

    @Override
    public CompletableFuture<StatusCode> writeExecutable(DataValue value) {
        return writeAttribute(AttributeId.Executable, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeUserExecutable(DataValue value) {
        return writeAttribute(AttributeId.UserExecutable, value);
    }

}
