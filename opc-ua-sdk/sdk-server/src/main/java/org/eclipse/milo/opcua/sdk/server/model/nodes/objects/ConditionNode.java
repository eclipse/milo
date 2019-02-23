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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ConditionVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ConditionType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class ConditionNode extends BaseEventNode implements ConditionType {
  public ConditionNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                       UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public ConditionNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                       UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getConditionClassIdNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CONDITION_CLASS_ID);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public NodeId getConditionClassId() {
    Optional<NodeId> propertyValue = getProperty(ConditionType.CONDITION_CLASS_ID);
    return propertyValue.orElse(null);
  }

  public void setConditionClassId(NodeId value) {
    setProperty(ConditionType.CONDITION_CLASS_ID, value);
  }

  public PropertyNode getConditionClassNameNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CONDITION_CLASS_NAME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public LocalizedText getConditionClassName() {
    Optional<LocalizedText> propertyValue = getProperty(ConditionType.CONDITION_CLASS_NAME);
    return propertyValue.orElse(null);
  }

  public void setConditionClassName(LocalizedText value) {
    setProperty(ConditionType.CONDITION_CLASS_NAME, value);
  }

  public PropertyNode getConditionNameNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CONDITION_NAME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String getConditionName() {
    Optional<String> propertyValue = getProperty(ConditionType.CONDITION_NAME);
    return propertyValue.orElse(null);
  }

  public void setConditionName(String value) {
    setProperty(ConditionType.CONDITION_NAME, value);
  }

  public PropertyNode getBranchIdNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.BRANCH_ID);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public NodeId getBranchId() {
    Optional<NodeId> propertyValue = getProperty(ConditionType.BRANCH_ID);
    return propertyValue.orElse(null);
  }

  public void setBranchId(NodeId value) {
    setProperty(ConditionType.BRANCH_ID, value);
  }

  public PropertyNode getRetainNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.RETAIN);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Boolean getRetain() {
    Optional<Boolean> propertyValue = getProperty(ConditionType.RETAIN);
    return propertyValue.orElse(null);
  }

  public void setRetain(Boolean value) {
    setProperty(ConditionType.RETAIN, value);
  }

  public PropertyNode getClientUserIdNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CLIENT_USER_ID);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String getClientUserId() {
    Optional<String> propertyValue = getProperty(ConditionType.CLIENT_USER_ID);
    return propertyValue.orElse(null);
  }

  public void setClientUserId(String value) {
    setProperty(ConditionType.CLIENT_USER_ID, value);
  }

  public TwoStateVariableNode getEnabledStateNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EnabledState");
    return (TwoStateVariableNode) component.orElse(null);
  }

  public LocalizedText getEnabledState() {
    Optional<VariableNode> component = getVariableComponent("EnabledState");
    return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setEnabledState(LocalizedText value) {
    getVariableComponent("EnabledState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public ConditionVariableNode getQualityNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "Quality");
    return (ConditionVariableNode) component.orElse(null);
  }

  public StatusCode getQuality() {
    Optional<VariableNode> component = getVariableComponent("Quality");
    return component.map(node -> (StatusCode) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setQuality(StatusCode value) {
    getVariableComponent("Quality").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public ConditionVariableNode getLastSeverityNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LastSeverity");
    return (ConditionVariableNode) component.orElse(null);
  }

  public UShort getLastSeverity() {
    Optional<VariableNode> component = getVariableComponent("LastSeverity");
    return component.map(node -> (UShort) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setLastSeverity(UShort value) {
    getVariableComponent("LastSeverity").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public ConditionVariableNode getCommentNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "Comment");
    return (ConditionVariableNode) component.orElse(null);
  }

  public LocalizedText getComment() {
    Optional<VariableNode> component = getVariableComponent("Comment");
    return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setComment(LocalizedText value) {
    getVariableComponent("Comment").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }
}
