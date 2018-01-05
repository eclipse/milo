/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.FileType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class FileNode extends BaseObjectNode implements FileType {
    public FileNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyNode> getSizeNode() {
        return getPropertyNode(FileType.SIZE);
    }

    public CompletableFuture<ULong> getSize() {
        return getProperty(FileType.SIZE);
    }

    public CompletableFuture<StatusCode> setSize(ULong value) {
        return setProperty(FileType.SIZE, value);
    }

    public CompletableFuture<PropertyNode> getWritableNode() {
        return getPropertyNode(FileType.WRITABLE);
    }

    public CompletableFuture<Boolean> getWritable() {
        return getProperty(FileType.WRITABLE);
    }

    public CompletableFuture<StatusCode> setWritable(Boolean value) {
        return setProperty(FileType.WRITABLE, value);
    }

    public CompletableFuture<PropertyNode> getUserWritableNode() {
        return getPropertyNode(FileType.USER_WRITABLE);
    }

    public CompletableFuture<Boolean> getUserWritable() {
        return getProperty(FileType.USER_WRITABLE);
    }

    public CompletableFuture<StatusCode> setUserWritable(Boolean value) {
        return setProperty(FileType.USER_WRITABLE, value);
    }

    public CompletableFuture<PropertyNode> getOpenCountNode() {
        return getPropertyNode(FileType.OPEN_COUNT);
    }

    public CompletableFuture<UShort> getOpenCount() {
        return getProperty(FileType.OPEN_COUNT);
    }

    public CompletableFuture<StatusCode> setOpenCount(UShort value) {
        return setProperty(FileType.OPEN_COUNT, value);
    }

    public CompletableFuture<PropertyNode> getMimeTypeNode() {
        return getPropertyNode(FileType.MIME_TYPE);
    }

    public CompletableFuture<String> getMimeType() {
        return getProperty(FileType.MIME_TYPE);
    }

    public CompletableFuture<StatusCode> setMimeType(String value) {
        return setProperty(FileType.MIME_TYPE, value);
    }
}
