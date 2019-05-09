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

public class ExclusiveLimitStateMachineTypeNode extends FiniteStateMachineTypeNode implements ExclusiveLimitStateMachineType {
    public ExclusiveLimitStateMachineTypeNode(UaNodeContext context, NodeId nodeId,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public ExclusiveLimitStateMachineTypeNode(UaNodeContext context, NodeId nodeId,
                                              QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                              UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public StateTypeNode getHighHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HighHigh");
        return (StateTypeNode) component.orElse(null);
    }

    @Override
    public StateTypeNode getHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "High");
        return (StateTypeNode) component.orElse(null);
    }

    @Override
    public StateTypeNode getLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Low");
        return (StateTypeNode) component.orElse(null);
    }

    @Override
    public StateTypeNode getLowLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LowLow");
        return (StateTypeNode) component.orElse(null);
    }

    @Override
    public TransitionTypeNode getLowLowToLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LowLowToLow");
        return (TransitionTypeNode) component.orElse(null);
    }

    @Override
    public TransitionTypeNode getLowToLowLowNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "LowToLowLow");
        return (TransitionTypeNode) component.orElse(null);
    }

    @Override
    public TransitionTypeNode getHighHighToHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HighHighToHigh");
        return (TransitionTypeNode) component.orElse(null);
    }

    @Override
    public TransitionTypeNode getHighToHighHighNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "HighToHighHigh");
        return (TransitionTypeNode) component.orElse(null);
    }
}
