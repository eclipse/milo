/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.core.Reference;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.FileType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class FileNode extends BaseObjectNode implements FileType {
    public FileNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                    LocalizedText displayName, LocalizedText description, UInteger writeMask,
                    UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public FileNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                    LocalizedText displayName, LocalizedText description, UInteger writeMask,
                    UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getUserWritableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.USER_WRITABLE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getUserWritable() {
        Optional<Boolean> propertyValue = getProperty(FileType.USER_WRITABLE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setUserWritable(Boolean value) {
        setProperty(FileType.USER_WRITABLE, value);
    }

    @Override
    public PropertyNode getWritableNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.WRITABLE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getWritable() {
        Optional<Boolean> propertyValue = getProperty(FileType.WRITABLE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setWritable(Boolean value) {
        setProperty(FileType.WRITABLE, value);
    }

    @Override
    public PropertyNode getMimeTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.MIME_TYPE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getMimeType() {
        Optional<String> propertyValue = getProperty(FileType.MIME_TYPE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMimeType(String value) {
        setProperty(FileType.MIME_TYPE, value);
    }

    @Override
    public PropertyNode getOpenCountNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.OPEN_COUNT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public UShort getOpenCount() {
        Optional<UShort> propertyValue = getProperty(FileType.OPEN_COUNT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setOpenCount(UShort value) {
        setProperty(FileType.OPEN_COUNT, value);
    }

    @Override
    public PropertyNode getSizeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FileType.SIZE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public ULong getSize() {
        Optional<ULong> propertyValue = getProperty(FileType.SIZE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setSize(ULong value) {
        setProperty(FileType.SIZE, value);
    }

    @Override
    public UaMethodNode getWriteMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Write", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getGetPositionMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "GetPosition", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getSetPositionMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "SetPosition", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getCloseMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Close", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getReadMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Read", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getOpenMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Open", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }
}
