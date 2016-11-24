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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.DialogConditionType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:DialogConditionType")
public class DialogConditionNode extends ConditionNode implements DialogConditionType {

    public DialogConditionNode(
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
    public LocalizedText getPrompt() {
        Optional<LocalizedText> property = getProperty(DialogConditionType.PROMPT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getPromptNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.PROMPT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setPrompt(LocalizedText value) {
        setProperty(DialogConditionType.PROMPT, value);
    }

    @Override
    public LocalizedText[] getResponseOptionSet() {
        Optional<LocalizedText[]> property = getProperty(DialogConditionType.RESPONSE_OPTION_SET);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getResponseOptionSetNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.RESPONSE_OPTION_SET.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setResponseOptionSet(LocalizedText[] value) {
        setProperty(DialogConditionType.RESPONSE_OPTION_SET, value);
    }

    @Override
    public Integer getDefaultResponse() {
        Optional<Integer> property = getProperty(DialogConditionType.DEFAULT_RESPONSE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getDefaultResponseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.DEFAULT_RESPONSE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setDefaultResponse(Integer value) {
        setProperty(DialogConditionType.DEFAULT_RESPONSE, value);
    }

    @Override
    public Integer getOkResponse() {
        Optional<Integer> property = getProperty(DialogConditionType.OK_RESPONSE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getOkResponseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.OK_RESPONSE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setOkResponse(Integer value) {
        setProperty(DialogConditionType.OK_RESPONSE, value);
    }

    @Override
    public Integer getCancelResponse() {
        Optional<Integer> property = getProperty(DialogConditionType.CANCEL_RESPONSE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getCancelResponseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.CANCEL_RESPONSE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setCancelResponse(Integer value) {
        setProperty(DialogConditionType.CANCEL_RESPONSE, value);
    }

    @Override
    public Integer getLastResponse() {
        Optional<Integer> property = getProperty(DialogConditionType.LAST_RESPONSE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getLastResponseNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(DialogConditionType.LAST_RESPONSE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setLastResponse(Integer value) {
        setProperty(DialogConditionType.LAST_RESPONSE, value);
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
    public LocalizedText getDialogState() {
        Optional<VariableNode> component = getVariableComponent("DialogState");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public TwoStateVariableNode getDialogStateNode() {
        Optional<VariableNode> component = getVariableComponent("DialogState");

        return component.map(node -> (TwoStateVariableNode) node).orElse(null);
    }

    @Override
    public void setDialogState(LocalizedText value) {
        getVariableComponent("DialogState")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}
