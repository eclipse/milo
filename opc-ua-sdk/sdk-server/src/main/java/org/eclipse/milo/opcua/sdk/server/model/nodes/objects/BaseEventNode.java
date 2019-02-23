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

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.BaseEventType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.TimeZoneDataType;

public class BaseEventNode extends BaseObjectNode implements BaseEventType {
  public BaseEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                       UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public BaseEventNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                       UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getEventIdNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.EVENT_ID);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public ByteString getEventId() {
    Optional<ByteString> propertyValue = getProperty(BaseEventType.EVENT_ID);
    return propertyValue.orElse(null);
  }

  public void setEventId(ByteString value) {
    setProperty(BaseEventType.EVENT_ID, value);
  }

  public PropertyNode getEventTypeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.EVENT_TYPE);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public NodeId getEventType() {
    Optional<NodeId> propertyValue = getProperty(BaseEventType.EVENT_TYPE);
    return propertyValue.orElse(null);
  }

  public void setEventType(NodeId value) {
    setProperty(BaseEventType.EVENT_TYPE, value);
  }

  public PropertyNode getSourceNodeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.SOURCE_NODE);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public NodeId getSourceNode() {
    Optional<NodeId> propertyValue = getProperty(BaseEventType.SOURCE_NODE);
    return propertyValue.orElse(null);
  }

  public void setSourceNode(NodeId value) {
    setProperty(BaseEventType.SOURCE_NODE, value);
  }

  public PropertyNode getSourceNameNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.SOURCE_NAME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String getSourceName() {
    Optional<String> propertyValue = getProperty(BaseEventType.SOURCE_NAME);
    return propertyValue.orElse(null);
  }

  public void setSourceName(String value) {
    setProperty(BaseEventType.SOURCE_NAME, value);
  }

  public PropertyNode getTimeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.TIME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public DateTime getTime() {
    Optional<DateTime> propertyValue = getProperty(BaseEventType.TIME);
    return propertyValue.orElse(null);
  }

  public void setTime(DateTime value) {
    setProperty(BaseEventType.TIME, value);
  }

  public PropertyNode getReceiveTimeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.RECEIVE_TIME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public DateTime getReceiveTime() {
    Optional<DateTime> propertyValue = getProperty(BaseEventType.RECEIVE_TIME);
    return propertyValue.orElse(null);
  }

  public void setReceiveTime(DateTime value) {
    setProperty(BaseEventType.RECEIVE_TIME, value);
  }

  public PropertyNode getLocalTimeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.LOCAL_TIME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public TimeZoneDataType getLocalTime() {
    Optional<TimeZoneDataType> propertyValue = getProperty(BaseEventType.LOCAL_TIME);
    return propertyValue.orElse(null);
  }

  public void setLocalTime(TimeZoneDataType value) {
    setProperty(BaseEventType.LOCAL_TIME, value);
  }

  public PropertyNode getMessageNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.MESSAGE);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public LocalizedText getMessage() {
    Optional<LocalizedText> propertyValue = getProperty(BaseEventType.MESSAGE);
    return propertyValue.orElse(null);
  }

  public void setMessage(LocalizedText value) {
    setProperty(BaseEventType.MESSAGE, value);
  }

  public PropertyNode getSeverityNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(BaseEventType.SEVERITY);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public UShort getSeverity() {
    Optional<UShort> propertyValue = getProperty(BaseEventType.SEVERITY);
    return propertyValue.orElse(null);
  }

  public void setSeverity(UShort value) {
    setProperty(BaseEventType.SEVERITY, value);
  }
}
