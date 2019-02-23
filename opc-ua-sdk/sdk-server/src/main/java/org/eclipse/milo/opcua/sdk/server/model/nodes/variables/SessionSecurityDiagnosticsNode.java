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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.SessionSecurityDiagnosticsType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

public class SessionSecurityDiagnosticsNode extends BaseDataVariableNode implements SessionSecurityDiagnosticsType {
  public SessionSecurityDiagnosticsNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public SessionSecurityDiagnosticsNode(UaNodeContext context, NodeId nodeId,
                                        QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                        UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                        Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                        double minimumSamplingInterval, boolean historizing) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
  }

  public BaseDataVariableNode getSessionIdNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionId");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public NodeId getSessionId() {
    Optional<VariableNode> component = getVariableComponent("SessionId");
    return component.map(node -> (NodeId) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSessionId(NodeId value) {
    getVariableComponent("SessionId").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getClientUserIdOfSessionNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ClientUserIdOfSession");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String getClientUserIdOfSession() {
    Optional<VariableNode> component = getVariableComponent("ClientUserIdOfSession");
    return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setClientUserIdOfSession(String value) {
    getVariableComponent("ClientUserIdOfSession").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getClientUserIdHistoryNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ClientUserIdHistory");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String[] getClientUserIdHistory() {
    Optional<VariableNode> component = getVariableComponent("ClientUserIdHistory");
    return component.map(node -> (String[]) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setClientUserIdHistory(String[] value) {
    getVariableComponent("ClientUserIdHistory").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getAuthenticationMechanismNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "AuthenticationMechanism");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String getAuthenticationMechanism() {
    Optional<VariableNode> component = getVariableComponent("AuthenticationMechanism");
    return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setAuthenticationMechanism(String value) {
    getVariableComponent("AuthenticationMechanism").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getEncodingNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "Encoding");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String getEncoding() {
    Optional<VariableNode> component = getVariableComponent("Encoding");
    return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setEncoding(String value) {
    getVariableComponent("Encoding").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getTransportProtocolNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "TransportProtocol");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String getTransportProtocol() {
    Optional<VariableNode> component = getVariableComponent("TransportProtocol");
    return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setTransportProtocol(String value) {
    getVariableComponent("TransportProtocol").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSecurityModeNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SecurityMode");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public MessageSecurityMode getSecurityMode() {
    Optional<VariableNode> component = getVariableComponent("SecurityMode");
    return component.map(node -> (MessageSecurityMode) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSecurityMode(MessageSecurityMode value) {
    getVariableComponent("SecurityMode").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSecurityPolicyUriNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SecurityPolicyUri");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public String getSecurityPolicyUri() {
    Optional<VariableNode> component = getVariableComponent("SecurityPolicyUri");
    return component.map(node -> (String) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSecurityPolicyUri(String value) {
    getVariableComponent("SecurityPolicyUri").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getClientCertificateNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ClientCertificate");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public ByteString getClientCertificate() {
    Optional<VariableNode> component = getVariableComponent("ClientCertificate");
    return component.map(node -> (ByteString) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setClientCertificate(ByteString value) {
    getVariableComponent("ClientCertificate").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }
}
