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

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.FiniteStateVariableType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class FiniteStateVariableNode extends StateVariableNode implements FiniteStateVariableType {
    public FiniteStateVariableNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public FiniteStateVariableNode(ServerNodeMap nodeMap, NodeId nodeId, QualifiedName browseName,
                                   LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                   UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                                   UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                   double minimumSamplingInterval, boolean historizing) {
        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
    }

    public PropertyNode getIdNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(FiniteStateVariableType.ID);
        return (PropertyNode) propertyNode.orElse(null);
    }

    public NodeId getId() {
        Optional<NodeId> propertyValue = getProperty(FiniteStateVariableType.ID);
        return propertyValue.orElse(null);
    }

    public void setId(NodeId value) {
        setProperty(FiniteStateVariableType.ID, value);
    }
}
