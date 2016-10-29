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

package org.eclipse.milo.opcua.sdk.client.nodes.attached;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.attached.UaReferenceTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class AttachedReferenceTypeNode extends AttachedNode implements UaReferenceTypeNode {

    public AttachedReferenceTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<DataValue> readIsAbstract() {
        return readAttribute(AttributeId.IsAbstract);
    }

    @Override
    public CompletableFuture<DataValue> readSymmetric() {
        return readAttribute(AttributeId.Symmetric);
    }

    @Override
    public CompletableFuture<DataValue> readInverseName() {
        return readAttribute(AttributeId.InverseName);
    }

    @Override
    public CompletableFuture<StatusCode> writeIsAbstract(DataValue value) {
        return writeAttribute(AttributeId.IsAbstract, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeSymmetric(DataValue value) {
        return writeAttribute(AttributeId.Symmetric, value);
    }

    @Override
    public CompletableFuture<StatusCode> writeInverseName(DataValue value) {
        return writeAttribute(AttributeId.InverseName, value);
    }

}
