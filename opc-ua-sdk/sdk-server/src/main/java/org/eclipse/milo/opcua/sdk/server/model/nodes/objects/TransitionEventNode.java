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

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.StateVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.TransitionVariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.TransitionEventType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:TransitionEventType")
public class TransitionEventNode extends BaseEventNode implements TransitionEventType {

    public TransitionEventNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public LocalizedText getTransition() {
        Optional<VariableNode> component = getVariableComponent("Transition");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public TransitionVariableNode getTransitionNode() {
        Optional<VariableNode> component = getVariableComponent("Transition");

        return component.map(node -> (TransitionVariableNode) node).orElse(null);
    }

    @Override
    public void setTransition(LocalizedText value) {
        getVariableComponent("Transition")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public LocalizedText getFromState() {
        Optional<VariableNode> component = getVariableComponent("FromState");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public StateVariableNode getFromStateNode() {
        Optional<VariableNode> component = getVariableComponent("FromState");

        return component.map(node -> (StateVariableNode) node).orElse(null);
    }

    @Override
    public void setFromState(LocalizedText value) {
        getVariableComponent("FromState")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    @Override
    public LocalizedText getToState() {
        Optional<VariableNode> component = getVariableComponent("ToState");

        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    @Override
    public StateVariableNode getToStateNode() {
        Optional<VariableNode> component = getVariableComponent("ToState");

        return component.map(node -> (StateVariableNode) node).orElse(null);
    }

    @Override
    public void setToState(LocalizedText value) {
        getVariableComponent("ToState")
            .ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

}
