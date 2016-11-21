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
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ShelvedStateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:ShelvedStateMachineType")
public class ShelvedStateMachineNode extends FiniteStateMachineNode implements ShelvedStateMachineType {

    public ShelvedStateMachineNode(
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
    public Double getUnshelveTime() {
        Optional<Double> property = getProperty(ShelvedStateMachineType.UNSHELVE_TIME);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getUnshelveTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ShelvedStateMachineType.UNSHELVE_TIME.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setUnshelveTime(Double value) {
        setProperty(ShelvedStateMachineType.UNSHELVE_TIME, value);
    }

    @Override
    public StateNode getUnshelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("Unshelved");

        return component.map(node -> (StateNode) node).orElse(null);
    }

    @Override
    public StateNode getTimedShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("TimedShelved");

        return component.map(node -> (StateNode) node).orElse(null);
    }

    @Override
    public StateNode getOneShotShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("OneShotShelved");

        return component.map(node -> (StateNode) node).orElse(null);
    }

    @Override
    public TransitionNode getUnshelvedToTimedShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("UnshelvedToTimedShelved");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getUnshelvedToOneShotShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("UnshelvedToOneShotShelved");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getTimedShelvedToUnshelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("TimedShelvedToUnshelved");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getTimedShelvedToOneShotShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("TimedShelvedToOneShotShelved");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getOneShotShelvedToUnshelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("OneShotShelvedToUnshelved");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    @Override
    public TransitionNode getOneShotShelvedToTimedShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("OneShotShelvedToTimedShelved");

        return component.map(node -> (TransitionNode) node).orElse(null);
    }

}
