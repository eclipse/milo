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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.DialogConditionType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class DialogConditionNode extends ConditionNode implements DialogConditionType {
    public DialogConditionNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                               LocalizedText displayName, LocalizedText description, UInteger writeMask,
                               UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public DialogConditionNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                               LocalizedText displayName, LocalizedText description, UInteger writeMask,
                               UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getLastResponseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.LAST_RESPONSE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Integer getLastResponse() {
        Optional<Integer> propertyValue = getProperty(DialogConditionType.LAST_RESPONSE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setLastResponse(Integer value) {
        setProperty(DialogConditionType.LAST_RESPONSE, value);
    }

    @Override
    public PropertyNode getCancelResponseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.CANCEL_RESPONSE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Integer getCancelResponse() {
        Optional<Integer> propertyValue = getProperty(DialogConditionType.CANCEL_RESPONSE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setCancelResponse(Integer value) {
        setProperty(DialogConditionType.CANCEL_RESPONSE, value);
    }

    @Override
    public PropertyNode getPromptNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.PROMPT);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public LocalizedText getPrompt() {
        Optional<LocalizedText> propertyValue = getProperty(DialogConditionType.PROMPT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setPrompt(LocalizedText value) {
        setProperty(DialogConditionType.PROMPT, value);
    }

    @Override
    public PropertyNode getOkResponseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.OK_RESPONSE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Integer getOkResponse() {
        Optional<Integer> propertyValue = getProperty(DialogConditionType.OK_RESPONSE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setOkResponse(Integer value) {
        setProperty(DialogConditionType.OK_RESPONSE, value);
    }

    @Override
    public PropertyNode getResponseOptionSetNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.RESPONSE_OPTION_SET);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public LocalizedText[] getResponseOptionSet() {
        Optional<LocalizedText[]> propertyValue = getProperty(DialogConditionType.RESPONSE_OPTION_SET);
        return propertyValue.orElse(null);
    }

    @Override
    public void setResponseOptionSet(LocalizedText[] value) {
        setProperty(DialogConditionType.RESPONSE_OPTION_SET, value);
    }

    @Override
    public PropertyNode getDefaultResponseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.DEFAULT_RESPONSE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Integer getDefaultResponse() {
        Optional<Integer> propertyValue = getProperty(DialogConditionType.DEFAULT_RESPONSE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setDefaultResponse(Integer value) {
        setProperty(DialogConditionType.DEFAULT_RESPONSE, value);
    }

    @Override
    public UaMethodNode getRespondMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Respond", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
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
    public TwoStateVariableNode getDialogStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "DialogState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getDialogState() {
        Optional<VariableNode> component = getVariableComponent("DialogState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setDialogState(LocalizedText value) {
        getVariableComponent("DialogState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
