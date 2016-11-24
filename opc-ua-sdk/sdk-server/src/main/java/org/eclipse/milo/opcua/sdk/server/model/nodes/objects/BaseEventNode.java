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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.BaseEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:BaseEventType")
public class BaseEventNode extends BaseObjectNode implements BaseEventType {

    public BaseEventNode(
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
    public ByteString getEventId() {
        Optional<ByteString> property = getProperty(BaseEventType.EVENT_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEventIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.EVENT_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEventId(ByteString value) {
        setProperty(BaseEventType.EVENT_ID, value);
    }

    @Override
    public NodeId getEventType() {
        Optional<NodeId> property = getProperty(BaseEventType.EVENT_TYPE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getEventTypeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.EVENT_TYPE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setEventType(NodeId value) {
        setProperty(BaseEventType.EVENT_TYPE, value);
    }

    @Override
    public NodeId getSourceNode() {
        Optional<NodeId> property = getProperty(BaseEventType.SOURCE_NODE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSourceNodeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.SOURCE_NODE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setSourceNode(NodeId value) {
        setProperty(BaseEventType.SOURCE_NODE, value);
    }

    @Override
    public String getSourceName() {
        Optional<String> property = getProperty(BaseEventType.SOURCE_NAME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSourceNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.SOURCE_NAME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setSourceName(String value) {
        setProperty(BaseEventType.SOURCE_NAME, value);
    }

    @Override
    public DateTime getTime() {
        Optional<DateTime> property = getProperty(BaseEventType.TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setTime(DateTime value) {
        setProperty(BaseEventType.TIME, value);
    }

    @Override
    public DateTime getReceiveTime() {
        Optional<DateTime> property = getProperty(BaseEventType.RECEIVE_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getReceiveTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.RECEIVE_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setReceiveTime(DateTime value) {
        setProperty(BaseEventType.RECEIVE_TIME, value);
    }

    @Override
    public TimeZoneDataType getLocalTime() {
        Optional<TimeZoneDataType> property = getProperty(BaseEventType.LOCAL_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLocalTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.LOCAL_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLocalTime(TimeZoneDataType value) {
        setProperty(BaseEventType.LOCAL_TIME, value);
    }

    @Override
    public LocalizedText getMessage() {
        Optional<LocalizedText> property = getProperty(BaseEventType.MESSAGE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMessageNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.MESSAGE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMessage(LocalizedText value) {
        setProperty(BaseEventType.MESSAGE, value);
    }

    @Override
    public UShort getSeverity() {
        Optional<UShort> property = getProperty(BaseEventType.SEVERITY);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSeverityNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.SEVERITY.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setSeverity(UShort value) {
        setProperty(BaseEventType.SEVERITY, value);
    }

}
