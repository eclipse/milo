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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ImageItemType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.AxisInformation;

public class ImageItemNode extends ArrayItemNode implements ImageItemType {
  public ImageItemNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                       UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public ImageItemNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                       LocalizedText displayName, LocalizedText description, UInteger writeMask,
                       UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                       UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                       double minimumSamplingInterval, boolean historizing) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
  }

  public PropertyNode getXAxisDefinitionNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ImageItemType.X_AXIS_DEFINITION);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public AxisInformation getXAxisDefinition() {
    Optional<AxisInformation> propertyValue = getProperty(ImageItemType.X_AXIS_DEFINITION);
    return propertyValue.orElse(null);
  }

  public void setXAxisDefinition(AxisInformation value) {
    setProperty(ImageItemType.X_AXIS_DEFINITION, value);
  }

  public PropertyNode getYAxisDefinitionNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ImageItemType.Y_AXIS_DEFINITION);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public AxisInformation getYAxisDefinition() {
    Optional<AxisInformation> propertyValue = getProperty(ImageItemType.Y_AXIS_DEFINITION);
    return propertyValue.orElse(null);
  }

  public void setYAxisDefinition(AxisInformation value) {
    setProperty(ImageItemType.Y_AXIS_DEFINITION, value);
  }
}
