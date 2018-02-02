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
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ShelvedStateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ShelvedStateMachineNode extends FiniteStateMachineNode implements ShelvedStateMachineType {
    public ShelvedStateMachineNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ShelvedStateMachineNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getUnshelveTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ShelvedStateMachineType.UNSHELVE_TIME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public Double getUnshelveTime() {
        Optional<Double> propertyValue = getProperty(ShelvedStateMachineType.UNSHELVE_TIME);
        return propertyValue.orElse(null);
    }

    public void setUnshelveTime(Double value) {
        setProperty(ShelvedStateMachineType.UNSHELVE_TIME, value);
    }

    public StateNode getUnshelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Unshelved");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public StateNode getTimedShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "TimedShelved");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public StateNode getOneShotShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelved");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public TransitionNode getUnshelvedToTimedShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "UnshelvedToTimedShelved");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getUnshelvedToOneShotShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "UnshelvedToOneShotShelved");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getTimedShelvedToUnshelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "TimedShelvedToUnshelved");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getTimedShelvedToOneShotShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "TimedShelvedToOneShotShelved");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getOneShotShelvedToUnshelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelvedToUnshelved");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getOneShotShelvedToTimedShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelvedToTimedShelved");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }
}
