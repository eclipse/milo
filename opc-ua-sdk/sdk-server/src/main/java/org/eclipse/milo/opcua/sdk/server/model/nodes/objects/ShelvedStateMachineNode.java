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
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ShelvedStateMachineType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaMethodNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNode;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ShelvedStateMachineNode extends FiniteStateMachineNode implements ShelvedStateMachineType {
    public ShelvedStateMachineNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ShelvedStateMachineNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyNode getUnshelveTimeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(ShelvedStateMachineType.UNSHELVE_TIME);
        return (PropertyNode) propertyNode.orElse(null);
    }

    @Override
    public Double getUnshelveTime() {
        Optional<Double> propertyValue = getProperty(ShelvedStateMachineType.UNSHELVE_TIME);
        return propertyValue.orElse(null);
    }

    @Override
    public void setUnshelveTime(Double value) {
        setProperty(ShelvedStateMachineType.UNSHELVE_TIME, value);
    }

    @Override
    public TransitionNode getTimedShelvedToOneShotShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "TimedShelvedToOneShotShelved");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public TransitionNode getOneShotShelvedToUnshelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelvedToUnshelved");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public TransitionNode getUnshelvedToTimedShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "UnshelvedToTimedShelved");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public StateNode getUnshelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Unshelved");
        return (StateNode) component.orElse(null);
    }

    @Override
    public StateNode getOneShotShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelved");
        return (StateNode) component.orElse(null);
    }

    @Override
    public StateNode getTimedShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "TimedShelved");
        return (StateNode) component.orElse(null);
    }

    @Override
    public UaMethodNode getOneShotShelveMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "OneShotShelve", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public TransitionNode getUnshelvedToOneShotShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "UnshelvedToOneShotShelved");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public UaMethodNode getTimedShelveMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "TimedShelve", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }

    @Override
    public TransitionNode getTimedShelvedToUnshelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "TimedShelvedToUnshelved");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public TransitionNode getOneShotShelvedToTimedShelvedNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "OneShotShelvedToTimedShelved");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public UaMethodNode getUnshelveMethodNode() {
        Optional<UaNode> methodNode = findNode("http://opcfoundation.org/UA/", "Unshelve", node -> node instanceof UaMethodNode, Reference.HAS_COMPONENT_PREDICATE);
        return (UaMethodNode) methodNode.orElse(null);
    }
}
