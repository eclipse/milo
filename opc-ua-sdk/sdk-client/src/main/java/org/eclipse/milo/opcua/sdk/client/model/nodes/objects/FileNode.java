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

    @Override
    public CompletableFuture<PropertyNode> size() {
        return getPropertyNode(FileType.SIZE.getBrowseName());
    }

    @Override
    public CompletableFuture<ULong> getSize() {
        return getProperty(FileType.SIZE);
    }

    @Override
    public CompletableFuture<StatusCode> setSize(ULong value) {
        return setProperty(FileType.SIZE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> writable() {
        return getPropertyNode(FileType.WRITABLE.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getWritable() {
        return getProperty(FileType.WRITABLE);
    }

    @Override
    public CompletableFuture<StatusCode> setWritable(Boolean value) {
        return setProperty(FileType.WRITABLE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> userWritable() {
        return getPropertyNode(FileType.USER_WRITABLE.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getUserWritable() {
        return getProperty(FileType.USER_WRITABLE);
    }

    @Override
    public CompletableFuture<StatusCode> setUserWritable(Boolean value) {
        return setProperty(FileType.USER_WRITABLE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> openCount() {
        return getPropertyNode(FileType.OPEN_COUNT.getBrowseName());
    }

    @Override
    public CompletableFuture<UShort> getOpenCount() {
        return getProperty(FileType.OPEN_COUNT);
    }

    @Override
    public CompletableFuture<StatusCode> setOpenCount(UShort value) {
        return setProperty(FileType.OPEN_COUNT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> mimeType() {
        return getPropertyNode(FileType.MIME_TYPE.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getMimeType() {
        return getProperty(FileType.MIME_TYPE);
    }

    @Override
    public CompletableFuture<StatusCode> setMimeType(String value) {
        return setProperty(FileType.MIME_TYPE, value);
    }


}