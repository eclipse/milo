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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ProgramDiagnosticType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.Argument;
import org.eclipse.milo.opcua.stack.core.types.structured.StatusResult;

public class ProgramDiagnosticNode extends BaseDataVariableNode implements ProgramDiagnosticType {
  public ProgramDiagnosticNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                               LocalizedText displayName, LocalizedText description, UInteger writeMask,
                               UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public ProgramDiagnosticNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                               LocalizedText displayName, LocalizedText description, UInteger writeMask,
                               UInteger userWriteMask, DataValue value, NodeId dataType, Integer valueRank,
                               UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                               double minimumSamplingInterval, boolean historizing) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
  }

  public PropertyNode getCreateSessionIdNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.CREATE_SESSION_ID);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public NodeId getCreateSessionId() {
    Optional<NodeId> propertyValue = getProperty(ProgramDiagnosticType.CREATE_SESSION_ID);
    return propertyValue.orElse(null);
  }

  public void setCreateSessionId(NodeId value) {
    setProperty(ProgramDiagnosticType.CREATE_SESSION_ID, value);
  }

  public PropertyNode getCreateClientNameNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.CREATE_CLIENT_NAME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String getCreateClientName() {
    Optional<String> propertyValue = getProperty(ProgramDiagnosticType.CREATE_CLIENT_NAME);
    return propertyValue.orElse(null);
  }

  public void setCreateClientName(String value) {
    setProperty(ProgramDiagnosticType.CREATE_CLIENT_NAME, value);
  }

  public PropertyNode getInvocationCreationTimeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.INVOCATION_CREATION_TIME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public DateTime getInvocationCreationTime() {
    Optional<DateTime> propertyValue = getProperty(ProgramDiagnosticType.INVOCATION_CREATION_TIME);
    return propertyValue.orElse(null);
  }

  public void setInvocationCreationTime(DateTime value) {
    setProperty(ProgramDiagnosticType.INVOCATION_CREATION_TIME, value);
  }

  public PropertyNode getLastTransitionTimeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_TRANSITION_TIME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public DateTime getLastTransitionTime() {
    Optional<DateTime> propertyValue = getProperty(ProgramDiagnosticType.LAST_TRANSITION_TIME);
    return propertyValue.orElse(null);
  }

  public void setLastTransitionTime(DateTime value) {
    setProperty(ProgramDiagnosticType.LAST_TRANSITION_TIME, value);
  }

  public PropertyNode getLastMethodCallNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_CALL);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String getLastMethodCall() {
    Optional<String> propertyValue = getProperty(ProgramDiagnosticType.LAST_METHOD_CALL);
    return propertyValue.orElse(null);
  }

  public void setLastMethodCall(String value) {
    setProperty(ProgramDiagnosticType.LAST_METHOD_CALL, value);
  }

  public PropertyNode getLastMethodSessionIdNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_SESSION_ID);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public NodeId getLastMethodSessionId() {
    Optional<NodeId> propertyValue = getProperty(ProgramDiagnosticType.LAST_METHOD_SESSION_ID);
    return propertyValue.orElse(null);
  }

  public void setLastMethodSessionId(NodeId value) {
    setProperty(ProgramDiagnosticType.LAST_METHOD_SESSION_ID, value);
  }

  public PropertyNode getLastMethodInputArgumentsNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Argument[] getLastMethodInputArguments() {
    Optional<Argument[]> propertyValue = getProperty(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS);
    return propertyValue.orElse(null);
  }

  public void setLastMethodInputArguments(Argument[] value) {
    setProperty(ProgramDiagnosticType.LAST_METHOD_INPUT_ARGUMENTS, value);
  }

  public PropertyNode getLastMethodOutputArgumentsNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Argument[] getLastMethodOutputArguments() {
    Optional<Argument[]> propertyValue = getProperty(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS);
    return propertyValue.orElse(null);
  }

  public void setLastMethodOutputArguments(Argument[] value) {
    setProperty(ProgramDiagnosticType.LAST_METHOD_OUTPUT_ARGUMENTS, value);
  }

  public PropertyNode getLastMethodCallTimeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_CALL_TIME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public DateTime getLastMethodCallTime() {
    Optional<DateTime> propertyValue = getProperty(ProgramDiagnosticType.LAST_METHOD_CALL_TIME);
    return propertyValue.orElse(null);
  }

  public void setLastMethodCallTime(DateTime value) {
    setProperty(ProgramDiagnosticType.LAST_METHOD_CALL_TIME, value);
  }

  public PropertyNode getLastMethodReturnStatusNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public StatusResult getLastMethodReturnStatus() {
    Optional<StatusResult> propertyValue = getProperty(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS);
    return propertyValue.orElse(null);
  }

  public void setLastMethodReturnStatus(StatusResult value) {
    setProperty(ProgramDiagnosticType.LAST_METHOD_RETURN_STATUS, value);
  }
}
