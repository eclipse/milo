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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.DataTypeDictionaryType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class DataTypeDictionaryNode extends BaseDataVariableNode implements DataTypeDictionaryType {
  public DataTypeDictionaryNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public DataTypeDictionaryNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                                UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                double minimumSamplingInterval, boolean historizing) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
  }

  public PropertyNode getDataTypeVersionNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(DataTypeDictionaryType.DATA_TYPE_VERSION);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String getDataTypeVersion() {
    Optional<String> propertyValue = getProperty(DataTypeDictionaryType.DATA_TYPE_VERSION);
    return propertyValue.orElse(null);
  }

  public void setDataTypeVersion(String value) {
    setProperty(DataTypeDictionaryType.DATA_TYPE_VERSION, value);
  }

  public PropertyNode getNamespaceUriNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(DataTypeDictionaryType.NAMESPACE_URI);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String getNamespaceUri() {
    Optional<String> propertyValue = getProperty(DataTypeDictionaryType.NAMESPACE_URI);
    return propertyValue.orElse(null);
  }

  public void setNamespaceUri(String value) {
    setProperty(DataTypeDictionaryType.NAMESPACE_URI, value);
  }
}
