/*
 * Copyright (c) 2017 Kevin Herron
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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.StateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class StateMachineNode extends BaseObjectNode implements StateMachineType {
    public StateMachineNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                            LocalizedText displayName, LocalizedText description, UInteger writeMask,
                            UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public StateMachineNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                            LocalizedText displayName, LocalizedText description, UInteger writeMask,
                            UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public StateVariableNode getCurrentStateNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentState");
        return component.map(node -> (StateVariableNode) node).orElse(null);
    }

    public LocalizedText getCurrentState() {
        Optional<VariableNode> component = getVariableComponent("CurrentState");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setCurrentState(LocalizedText value) {
        getVariableComponent("CurrentState").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }

    public TransitionVariableNode getLastTransitionNode() {
        Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "LastTransition");
        return component.map(node -> (TransitionVariableNode) node).orElse(null);
    }

    public LocalizedText getLastTransition() {
        Optional<VariableNode> component = getVariableComponent("LastTransition");
        return component.map(node -> (LocalizedText) node.getValue().getValue().getValue()).orElse(null);
    }

    public void setLastTransition(LocalizedText value) {
        getVariableComponent("LastTransition").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
    }
}
