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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AuditWriteUpdateEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:AuditWriteUpdateEventType")
public class AuditWriteUpdateEventNode extends AuditUpdateEventNode implements AuditWriteUpdateEventType {

    public AuditWriteUpdateEventNode(
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
    public UInteger getAttributeId() {
        Optional<UInteger> property = getProperty(AuditWriteUpdateEventType.ATTRIBUTE_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getAttributeIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditWriteUpdateEventType.ATTRIBUTE_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setAttributeId(UInteger value) {
        setProperty(AuditWriteUpdateEventType.ATTRIBUTE_ID, value);
    }

    @Override
    public String getIndexRange() {
        Optional<String> property = getProperty(AuditWriteUpdateEventType.INDEX_RANGE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getIndexRangeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditWriteUpdateEventType.INDEX_RANGE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setIndexRange(String value) {
        setProperty(AuditWriteUpdateEventType.INDEX_RANGE, value);
    }

    @Override
    public Object getOldValue() {
        Optional<Object> property = getProperty(AuditWriteUpdateEventType.OLD_VALUE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getOldValueNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditWriteUpdateEventType.OLD_VALUE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setOldValue(Object value) {
        setProperty(AuditWriteUpdateEventType.OLD_VALUE, value);
    }

    @Override
    public Object getNewValue() {
        Optional<Object> property = getProperty(AuditWriteUpdateEventType.NEW_VALUE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getNewValueNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(AuditWriteUpdateEventType.NEW_VALUE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setNewValue(Object value) {
        setProperty(AuditWriteUpdateEventType.NEW_VALUE, value);
    }

}
