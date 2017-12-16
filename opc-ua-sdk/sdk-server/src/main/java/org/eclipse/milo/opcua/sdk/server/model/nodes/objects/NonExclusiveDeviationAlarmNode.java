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
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.NonExclusiveDeviationAlarmType;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class NonExclusiveDeviationAlarmNode extends NonExclusiveLimitAlarmNode implements NonExclusiveDeviationAlarmType {
    public NonExclusiveDeviationAlarmNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public NonExclusiveDeviationAlarmNode(ServerNodeMap nodeMap, NodeId nodeId,
                                          QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                          UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    public PropertyNode getSetpointNodeNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(NonExclusiveDeviationAlarmType.SETPOINT_NODE);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public NodeId getSetpointNode() {
        Optional<NodeId> propertyValue = getProperty(NonExclusiveDeviationAlarmType.SETPOINT_NODE);
        return propertyValue.orElse(null);
    }

    public void setSetpointNode(NodeId value) {
        setProperty(NonExclusiveDeviationAlarmType.SETPOINT_NODE, value);
    }
}
