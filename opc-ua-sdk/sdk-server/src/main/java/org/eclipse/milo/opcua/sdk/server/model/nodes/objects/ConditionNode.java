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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ConditionVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ConditionType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:ConditionType")
public class ConditionNode extends BaseEventNode implements ConditionType {

    public ConditionNode(
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
    public NodeId getConditionClassId() {
        Optional<NodeId> property = getProperty(ConditionType.CONDITION_CLASS_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getConditionClassIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CONDITION_CLASS_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setConditionClassId(NodeId value) {
        setProperty(ConditionType.CONDITION_CLASS_ID, value);
    }

    @Override
    public LocalizedText getConditionClassName() {
        Optional<LocalizedText> property = getProperty(ConditionType.CONDITION_CLASS_NAME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getConditionClassNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CONDITION_CLASS_NAME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setConditionClassName(LocalizedText value) {
        setProperty(ConditionType.CONDITION_CLASS_NAME, value);
    }

    @Override
    public String getConditionName() {
        Optional<String> property = getProperty(ConditionType.CONDITION_NAME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getConditionNameNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CONDITION_NAME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setConditionName(String value) {
        setProperty(ConditionType.CONDITION_NAME, value);
    }

    @Override
    public NodeId getBranchId() {
        Optional<NodeId> property = getProperty(ConditionType.BRANCH_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getBranchIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.BRANCH_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setBranchId(NodeId value) {
        setProperty(ConditionType.BRANCH_ID, value);
    }

    @Override
    public Boolean getRetain() {
        Optional<Boolean> property = getProperty(ConditionType.RETAIN);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getRetainNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.RETAIN.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setRetain(Boolean value) {
        setProperty(ConditionType.RETAIN, value);
    }

    @Override
    public String getClientUserId() {
        Optional<String> property = getProperty(ConditionType.CLIENT_USER_ID);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getClientUserIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ConditionType.CLIENT_USER_ID.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setClientUserId(String value) {
        setProperty(ConditionType.CLIENT_USER_ID, value);
    }

    @Override
    public LocalizedText getEnabledState() {
        Optional<VariableNode> component = getVariableComponent("EnabledState");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public TwoStateVariableNode getEnabledStateNode() {
        Optional<VariableNode> component = getVariableComponent("EnabledState");

        return component.map(node -> (TwoStateVariableNode) node).orElse(null);
    }

    @Override
    public void setEnabledState(LocalizedText value) {
        getVariableComponent("EnabledState")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public StatusCode getQuality() {
        Optional<VariableNode> component = getVariableComponent("Quality");

        return component.map(node -> (StatusCode) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public ConditionVariableNode getQualityNode() {
        Optional<VariableNode> component = getVariableComponent("Quality");

        return component.map(node -> (ConditionVariableNode) node).orElse(null);
    }

    @Override
    public void setQuality(StatusCode value) {
        getVariableComponent("Quality")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public UShort getLastSeverity() {
        Optional<VariableNode> component = getVariableComponent("LastSeverity");

        return component.map(node -> (UShort) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public ConditionVariableNode getLastSeverityNode() {
        Optional<VariableNode> component = getVariableComponent("LastSeverity");

        return component.map(node -> (ConditionVariableNode) node).orElse(null);
    }

    @Override
    public void setLastSeverity(UShort value) {
        getVariableComponent("LastSeverity")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public LocalizedText getComment() {
        Optional<VariableNode> component = getVariableComponent("Comment");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public ConditionVariableNode getCommentNode() {
        Optional<VariableNode> component = getVariableComponent("Comment");

        return component.map(node -> (ConditionVariableNode) node).orElse(null);
    }

    @Override
    public void setComment(LocalizedText value) {
        getVariableComponent("Comment")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}
