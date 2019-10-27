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
import org.eclipse.milo.opcua.sdk.client.api.nodes.ObjectTypeNode;
import org.eclipse.milo.opcua.sdk.core.nodes.ObjectTypeNodeProperties;
import org.eclipse.milo.opcua.stack.core.AttributeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

import static org.eclipse.milo.opcua.stack.core.types.builtin.DataValue.valueOnly;

public class UaObjectTypeNode extends UaNode implements ObjectTypeNode {

    public UaObjectTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<Boolean> getIsAbstract() {
        return getAttributeOrFail(readIsAbstract());
    }

    @Override
    public CompletableFuture<StatusCode> setIsAbstract(boolean isAbstract) {
        return writeIsAbstract(valueOnly(new Variant(isAbstract)));
    }

    @Override
    public CompletableFuture<DataValue> readIsAbstract() {
        return readAttribute(AttributeId.IsAbstract);
    }

    @Override
    public CompletableFuture<StatusCode> writeIsAbstract(DataValue value) {
        return writeAttribute(AttributeId.IsAbstract, value);
    }

    /**
     * Get the value of the NodeVersion Property, if it exists.
     *
     * @return the value of the NodeVersion Property, if it exists.
     * @see ObjectTypeNodeProperties#NodeVersion
     */
    public CompletableFuture<String> getNodeVersion() {
        return getProperty(ObjectTypeNodeProperties.NodeVersion);
    }

    /**
     * Get the value of the Icon Property, if it exists.
     *
     * @return the value of the Icon Property, if it exists.
     * @see ObjectTypeNodeProperties#Icon
     */
    public CompletableFuture<ByteString> getIcon() {
        return getProperty(ObjectTypeNodeProperties.Icon);
    }

    /**
     * Set the value of the NodeVersion Property, if it exists.
     *
     * @param nodeVersion the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see ObjectTypeNodeProperties#NodeVersion
     */
    public CompletableFuture<StatusCode> setNodeVersion(String nodeVersion) {
        return setProperty(ObjectTypeNodeProperties.NodeVersion, nodeVersion);
    }

    /**
     * Set the value of the Icon Property, if it exists.
     *
     * @param icon the value to set.
     * @return a {@link CompletableFuture} that completes with the {@link StatusCode} of the write operation.
     * @see ObjectTypeNodeProperties#Icon
     */
    public CompletableFuture<StatusCode> setIcon(ByteString icon) {
        return setProperty(ObjectTypeNodeProperties.Icon, icon);
    }

}
