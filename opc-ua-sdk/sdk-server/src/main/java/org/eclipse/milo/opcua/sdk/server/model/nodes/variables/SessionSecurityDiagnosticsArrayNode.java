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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionSecurityDiagnosticsArrayType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.SessionSecurityDiagnosticsDataType;

public class SessionSecurityDiagnosticsArrayNode extends BaseDataVariableNode implements SessionSecurityDiagnosticsArrayType {
  public SessionSecurityDiagnosticsArrayNode(UaNodeContext context, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public SessionSecurityDiagnosticsArrayNode(UaNodeContext context, NodeId nodeId,
                                             QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                             UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                             Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                             double minimumSamplingInterval, boolean historizing) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
  }

  public SessionSecurityDiagnosticsNode getSessionSecurityDiagnosticsNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionSecurityDiagnostics");
    return (SessionSecurityDiagnosticsNode) component.orElse(null);
  }

  public SessionSecurityDiagnosticsDataType getSessionSecurityDiagnostics() {
    Optional<VariableNode> component = getVariableComponent("SessionSecurityDiagnostics");
    return component.map(node -> (SessionSecurityDiagnosticsDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSessionSecurityDiagnostics(SessionSecurityDiagnosticsDataType value) {
    getVariableComponent("SessionSecurityDiagnostics").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }
}
