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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ExclusiveLimitStateMachineType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ExclusiveLimitStateMachineNode extends FiniteStateMachineNode implements ExclusiveLimitStateMachineType {
    public ExclusiveLimitStateMachineNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ExclusiveLimitStateMachineNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public StateNode getHighHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HighHigh");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public StateNode getHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "High");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public StateNode getLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Low");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public StateNode getLowLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LowLow");
        return component.map(node -> (StateNode) node).orElse(null);
    }

    public TransitionNode getLowLowToLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LowLowToLow");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getLowToLowLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LowToLowLow");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getHighHighToHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HighHighToHigh");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }

    public TransitionNode getHighToHighHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HighToHighHigh");
        return component.map(node -> (TransitionNode) node).orElse(null);
    }
}
