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

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditWriteUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AuditWriteUpdateEventNode extends AuditUpdateEventNode implements AuditWriteUpdateEventType {
    public AuditWriteUpdateEventNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                     LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                     UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AuditWriteUpdateEventNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                     LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                     UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getAttributeIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditWriteUpdateEventType.ATTRIBUTE_ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public UInteger getAttributeId() {
        Optional<UInteger> propertyValue = getProperty(AuditWriteUpdateEventType.ATTRIBUTE_ID);
        return propertyValue.orElse(null);
    }

    public void setAttributeId(UInteger value) {
        setProperty(AuditWriteUpdateEventType.ATTRIBUTE_ID, value);
    }

    public PropertyNode getIndexRangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditWriteUpdateEventType.INDEX_RANGE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public String getIndexRange() {
        Optional<String> propertyValue = getProperty(AuditWriteUpdateEventType.INDEX_RANGE);
        return propertyValue.orElse(null);
    }

    public void setIndexRange(String value) {
        setProperty(AuditWriteUpdateEventType.INDEX_RANGE, value);
    }

    public PropertyNode getOldValueNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditWriteUpdateEventType.OLD_VALUE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Object getOldValue() {
        Optional<Object> propertyValue = getProperty(AuditWriteUpdateEventType.OLD_VALUE);
        return propertyValue.orElse(null);
    }

    public void setOldValue(Object value) {
        setProperty(AuditWriteUpdateEventType.OLD_VALUE, value);
    }

    public PropertyNode getNewValueNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditWriteUpdateEventType.NEW_VALUE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Object getNewValue() {
        Optional<Object> propertyValue = getProperty(AuditWriteUpdateEventType.NEW_VALUE);
        return propertyValue.orElse(null);
    }

    public void setNewValue(Object value) {
        setProperty(AuditWriteUpdateEventType.NEW_VALUE, value);
    }
}
