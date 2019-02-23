/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.nodes;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.api.nodes.ReferenceTypeNode;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

import static org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.valueOnly;

public class UaReferenceTypeNode extends UaNode implements ReferenceTypeNode {

    public UaReferenceTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<Boolean> getIsAbstract() {
        return getAttributeOrFail(readIsAbstract());
    }

    @Override
    public CompletableFuture<Boolean> getSymmetric() {
        return getAttributeOrFail(readSymmetric());
    }

    @Override
    public CompletableFuture<LocalizedText> getInverseName() {
        return getAttributeOrFail(readInverseName());
    }

    @Override
    public CompletableFuture<StatusCode> setIsAbstract(boolean isAbstract) {
        return writeIsAbstract(valueOnly(new Variant(isAbstract)));
    }

    @Override
    public CompletableFuture<StatusCode> setSymmetric(boolean symmetric) {
        return writeSymmetric(valueOnly(new Variant(symmetric)));
    }

    @Override
    public CompletableFuture<StatusCode> setInverseName(LocalizedText inverseName) {
        return writeInverseName(valueOnly(new Variant(inverseName)));
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
