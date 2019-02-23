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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.AcknowledgeableConditionType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class AcknowledgeableConditionNode extends ConditionNode implements AcknowledgeableConditionType {
    public AcknowledgeableConditionNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public AcknowledgeableConditionNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
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

    public TwoStateVariableNode getAckedStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "AckedState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    public LocalizedText getAckedState() {
        Optional<VariableNode> component = getVariableComponent("AckedState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setAckedState(LocalizedText value) {
        getVariableComponent("AckedState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public TwoStateVariableNode getConfirmedStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ConfirmedState");
        return (TwoStateVariableNode) component.orElse(null);
    }

    public LocalizedText getConfirmedState() {
        Optional<VariableNode> component = getVariableComponent("ConfirmedState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setConfirmedState(LocalizedText value) {
        getVariableComponent("ConfirmedState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
