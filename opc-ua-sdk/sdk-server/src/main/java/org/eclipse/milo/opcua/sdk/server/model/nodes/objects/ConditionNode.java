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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ConditionVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ConditionType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
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

    @Override
    public PropertyNode getConditionNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CONDITION_NAME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getConditionName() {
        Optional<String> propertyValue = getProperty(ConditionType.CONDITION_NAME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setConditionName(String value) {
        setProperty(ConditionType.CONDITION_NAME, value);
    }

    @Override
    public PropertyNode getClientUserIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CLIENT_USER_ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public String getClientUserId() {
        Optional<String> propertyValue = getProperty(ConditionType.CLIENT_USER_ID);
        return propertyValue.orElse(null);
    }

    @Override
    public void setClientUserId(String value) {
        setProperty(ConditionType.CLIENT_USER_ID, value);
    }

    @Override
    public PropertyNode getConditionClassNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CONDITION_CLASS_NAME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public LocalizedText getConditionClassName() {
        Optional<LocalizedText> propertyValue = getProperty(ConditionType.CONDITION_CLASS_NAME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setConditionClassName(LocalizedText value) {
        setProperty(ConditionType.CONDITION_CLASS_NAME, value);
    }

    @Override
    public PropertyNode getRetainNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.RETAIN);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getRetain() {
        Optional<Boolean> propertyValue = getProperty(ConditionType.RETAIN);
        return propertyValue.orElse(null);
    }

    @Override
    public void setRetain(Boolean value) {
        setProperty(ConditionType.RETAIN, value);
    }

    @Override
    public PropertyNode getConditionClassIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CONDITION_CLASS_ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public NodeId getConditionClassId() {
        Optional<NodeId> propertyValue = getProperty(ConditionType.CONDITION_CLASS_ID);
        return propertyValue.orElse(null);
    }

    @Override
    public void setConditionClassId(NodeId value) {
        setProperty(ConditionType.CONDITION_CLASS_ID, value);
    }

    @Override
    public PropertyNode getBranchIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.BRANCH_ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public NodeId getBranchId() {
        Optional<NodeId> propertyValue = getProperty(ConditionType.BRANCH_ID);
        return propertyValue.orElse(null);
    }

    @Override
    public void setBranchId(NodeId value) {
        setProperty(ConditionType.BRANCH_ID, value);
    }

    @Override
    public UaMethodNode getAddCommentMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "AddComment", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getDisableMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Disable", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getEnableMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Enable", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getConditionRefreshMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "ConditionRefresh", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public UaMethodNode getConditionRefresh2MethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "ConditionRefresh2", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public ConditionVariableNode getCommentNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "Comment");
        return (ConditionVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getComment() {
        Optional<VariableNode> component = getVariableComponent("Comment");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setComment(LocalizedText value) {
        getVariableComponent("Comment").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TwoStateVariableNode getEnabledStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "EnabledState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getEnabledState() {
        Optional<VariableNode> component = getVariableComponent("EnabledState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setEnabledState(LocalizedText value) {
        getVariableComponent("EnabledState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ConditionVariableNode getLastSeverityNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LastSeverity");
        return (ConditionVariableNode) component.orElse(null);
    }

    @Override
    public UShort getLastSeverity() {
        Optional<VariableNode> component = getVariableComponent("LastSeverity");
        return component.map(node -> (UShort) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setLastSeverity(UShort value) {
        getVariableComponent("LastSeverity").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public ConditionVariableNode getQualityNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "Quality");
        return (ConditionVariableNode) component.orElse(null);
    }

    @Override
    public StatusCode getQuality() {
        Optional<VariableNode> component = getVariableComponent("Quality");
        return component.map(node -> (StatusCode) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setQuality(StatusCode value) {
        getVariableComponent("Quality").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
