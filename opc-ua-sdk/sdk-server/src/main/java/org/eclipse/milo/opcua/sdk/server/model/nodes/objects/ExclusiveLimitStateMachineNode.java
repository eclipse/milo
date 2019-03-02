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

import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ExclusiveLimitStateMachineType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ExclusiveLimitStateMachineNode extends FiniteStateMachineNode implements ExclusiveLimitStateMachineType {
    public ExclusiveLimitStateMachineNode(UaNodeContext context, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ExclusiveLimitStateMachineNode(UaNodeContext context, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public StateNode getLowLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LowLow");
        return (StateNode) component.orElse(null);
    }

    @Override
    public TransitionNode getLowToLowLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LowToLowLow");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public TransitionNode getLowLowToLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LowLowToLow");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public StateNode getHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "High");
        return (StateNode) component.orElse(null);
    }

    @Override
    public StateNode getLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Low");
        return (StateNode) component.orElse(null);
    }

    @Override
    public StateNode getHighHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HighHigh");
        return (StateNode) component.orElse(null);
    }

    @Override
    public TransitionNode getHighHighToHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HighHighToHigh");
        return (TransitionNode) component.orElse(null);
    }

    @Override
    public TransitionNode getHighToHighHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HighToHighHigh");
        return (TransitionNode) component.orElse(null);
    }
}
