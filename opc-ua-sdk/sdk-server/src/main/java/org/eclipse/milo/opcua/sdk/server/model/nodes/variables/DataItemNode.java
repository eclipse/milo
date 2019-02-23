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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.DataItemType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class DataItemNode extends BaseDataVariableNode implements DataItemType {
  public DataItemNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                      UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public DataItemNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                      LocalizedText displayName, LocalizedText description, UInteger writeMask,
                      UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                      UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                      double minimumSamplingInterval, boolean historizing) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
  }

  public PropertyNode getDefinitionNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(DataItemType.DEFINITION);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String getDefinition() {
    Optional<String> propertyValue = getProperty(DataItemType.DEFINITION);
    return propertyValue.orElse(null);
  }

  public void setDefinition(String value) {
    setProperty(DataItemType.DEFINITION, value);
  }

  public PropertyNode getValuePrecisionNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(DataItemType.VALUE_PRECISION);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Double getValuePrecision() {
    Optional<Double> propertyValue = getProperty(DataItemType.VALUE_PRECISION);
    return propertyValue.orElse(null);
  }

  public void setValuePrecision(Double value) {
    setProperty(DataItemType.VALUE_PRECISION, value);
  }
}
