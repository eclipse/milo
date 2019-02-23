/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.variables;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.FiniteStateVariableType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class FiniteStateVariableNode extends StateVariableNode implements FiniteStateVariableType {
  public FiniteStateVariableNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public FiniteStateVariableNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                                 UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                 double minimumSamplingInterval, boolean historizing) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
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
