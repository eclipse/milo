/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FileType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class FileTypeNode extends BaseObjectTypeNode implements FileType {
    public FileTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getSizeNode() {
        return getPropertyNode(FileType.SIZE);
    }

    public CompletableFuture<ULong> getSize() {
        return getProperty(FileType.SIZE);
    }

    public CompletableFuture<StatusCode> setSize(ULong value) {
        return setProperty(FileType.SIZE, value);
    }

    public CompletableFuture<PropertyTypeNode> getWritableNode() {
        return getPropertyNode(FileType.WRITABLE);
    }

    public CompletableFuture<Boolean> getWritable() {
        return getProperty(FileType.WRITABLE);
    }

    public CompletableFuture<StatusCode> setWritable(Boolean value) {
        return setProperty(FileType.WRITABLE, value);
    }

    public CompletableFuture<PropertyTypeNode> getUserWritableNode() {
        return getPropertyNode(FileType.USER_WRITABLE);
    }

    public CompletableFuture<Boolean> getUserWritable() {
        return getProperty(FileType.USER_WRITABLE);
    }

    public CompletableFuture<StatusCode> setUserWritable(Boolean value) {
        return setProperty(FileType.USER_WRITABLE, value);
    }

    public CompletableFuture<PropertyTypeNode> getOpenCountNode() {
        return getPropertyNode(FileType.OPEN_COUNT);
    }

    public CompletableFuture<UShort> getOpenCount() {
        return getProperty(FileType.OPEN_COUNT);
    }

    public CompletableFuture<StatusCode> setOpenCount(UShort value) {
        return setProperty(FileType.OPEN_COUNT, value);
    }

    public CompletableFuture<PropertyTypeNode> getMimeTypeNode() {
        return getPropertyNode(FileType.MIME_TYPE);
    }

    public CompletableFuture<String> getMimeType() {
        return getProperty(FileType.MIME_TYPE);
    }

    public CompletableFuture<StatusCode> setMimeType(String value) {
        return setProperty(FileType.MIME_TYPE, value);
    }
}
