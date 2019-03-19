/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.nodes;

import org.eclipse.milo.opcua.sdk.server.nodes.delegates.AttributeDelegate;

/**
 * @see AttributeDelegate
 * @deprecated use an {@link AttributeDelegate}.
 */
@Deprecated
public abstract class DerivedVariableNode extends UaVariableNode {

    public DerivedVariableNode(UaNodeContext context, UaVariableNode variableNode) {

        super(context,
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

        variableNode.getReferences().forEach(this::addReference);
    }

}
