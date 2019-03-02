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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TwoStateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.NonExclusiveLimitAlarmType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class NonExclusiveLimitAlarmNode extends LimitAlarmNode implements NonExclusiveLimitAlarmType {
    public NonExclusiveLimitAlarmNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public NonExclusiveLimitAlarmNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                      UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public TwoStateVariableNode getLowStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LowState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getLowState() {
        Optional<VariableNode> component = getVariableComponent("LowState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setLowState(LocalizedText value) {
        getVariableComponent("LowState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TwoStateVariableNode getActiveStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ActiveState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getActiveState() {
        Optional<VariableNode> component = getVariableComponent("ActiveState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setActiveState(LocalizedText value) {
        getVariableComponent("ActiveState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TwoStateVariableNode getHighStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "HighState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getHighState() {
        Optional<VariableNode> component = getVariableComponent("HighState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setHighState(LocalizedText value) {
        getVariableComponent("HighState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TwoStateVariableNode getLowLowStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LowLowState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getLowLowState() {
        Optional<VariableNode> component = getVariableComponent("LowLowState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setLowLowState(LocalizedText value) {
        getVariableComponent("LowLowState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public TwoStateVariableNode getHighHighStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "HighHighState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    @Override
    public LocalizedText getHighHighState() {
        Optional<VariableNode> component = getVariableComponent("HighHighState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public void setHighHighState(LocalizedText value) {
        getVariableComponent("HighHighState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
