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

package org.eclipse.milo.opcua.sdk.server.nodes;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;

public abstract class DerivedVariableNode extends UaVariableNode {

    public DerivedVariableNode(ServerNodeMap nodeMap, UaVariableNode variableNode) {

        super(nodeMap,
            variableNode.getNodeId(),
            variableNode.getBrowseName(),
            variableNode.getDisplayName(),
            variableNode.getDescription(),
            variableNode.getWriteMask(),
            variableNode.getUserWriteMask(),
            variableNode.getValue(),
            variableNode.getDataType(),
            variableNode.getValueRank(),
            variableNode.getArrayDimensions(),
            variableNode.getAccessLevel(),
            variableNode.getUserAccessLevel(),
            variableNode.getMinimumSamplingInterval(),
            variableNode.getHistorizing());

        addReferences(variableNode.getReferences());
    }

}
