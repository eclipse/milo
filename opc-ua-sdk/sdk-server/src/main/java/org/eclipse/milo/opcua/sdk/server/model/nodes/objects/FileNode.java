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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.UaNodeManager;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.FileType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:FileType")
public class FileNode extends BaseObjectNode implements FileType {

    public FileNode(
        UaNodeManager nodeManager,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeManager, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public ULong getSize() {
        Optional<ULong> property = getProperty(FileType.SIZE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSizeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.SIZE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setSize(ULong value) {
        setProperty(FileType.SIZE, value);
    }

    @Override
    public Boolean getWritable() {
        Optional<Boolean> property = getProperty(FileType.WRITABLE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getWritableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.WRITABLE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setWritable(Boolean value) {
        setProperty(FileType.WRITABLE, value);
    }

    @Override
    public Boolean getUserWritable() {
        Optional<Boolean> property = getProperty(FileType.USER_WRITABLE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getUserWritableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.USER_WRITABLE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setUserWritable(Boolean value) {
        setProperty(FileType.USER_WRITABLE, value);
    }

    @Override
    public UShort getOpenCount() {
        Optional<UShort> property = getProperty(FileType.OPEN_COUNT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getOpenCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.OPEN_COUNT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setOpenCount(UShort value) {
        setProperty(FileType.OPEN_COUNT, value);
    }

    @Override
    public String getMimeType() {
        Optional<String> property = getProperty(FileType.MIME_TYPE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMimeTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.MIME_TYPE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMimeType(String value) {
        setProperty(FileType.MIME_TYPE, value);
    }

}
