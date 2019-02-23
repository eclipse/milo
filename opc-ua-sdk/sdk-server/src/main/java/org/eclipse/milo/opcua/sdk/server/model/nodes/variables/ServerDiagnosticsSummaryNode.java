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
import org.eclipse.milo.opcua.sdk.server.model.types.variables.ServerDiagnosticsSummaryType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ServerDiagnosticsSummaryNode extends BaseDataVariableNode implements ServerDiagnosticsSummaryType {
  public ServerDiagnosticsSummaryNode(UaNodeContext context, NodeId nodeId,
                                      QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                      UInteger writeMask, UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public ServerDiagnosticsSummaryNode(UaNodeContext context, NodeId nodeId,
                                      QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                      UInteger writeMask, UInteger userWriteMask, DataValue value, NodeId dataType,
                                      Integer valueRank, UInteger[] arrayDimensions, UByte accessLevel, UByte userAccessLevel,
                                      double minimumSamplingInterval, boolean historizing) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, accessLevel, userAccessLevel, minimumSamplingInterval, historizing);
  }

  public BaseDataVariableNode getServerViewCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ServerViewCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getServerViewCount() {
    Optional<VariableNode> component = getVariableComponent("ServerViewCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setServerViewCount(UInteger value) {
    getVariableComponent("ServerViewCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCurrentSessionCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentSessionCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getCurrentSessionCount() {
    Optional<VariableNode> component = getVariableComponent("CurrentSessionCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCurrentSessionCount(UInteger value) {
    getVariableComponent("CurrentSessionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCumulatedSessionCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CumulatedSessionCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getCumulatedSessionCount() {
    Optional<VariableNode> component = getVariableComponent("CumulatedSessionCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCumulatedSessionCount(UInteger value) {
    getVariableComponent("CumulatedSessionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSecurityRejectedSessionCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SecurityRejectedSessionCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getSecurityRejectedSessionCount() {
    Optional<VariableNode> component = getVariableComponent("SecurityRejectedSessionCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSecurityRejectedSessionCount(UInteger value) {
    getVariableComponent("SecurityRejectedSessionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getRejectedSessionCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "RejectedSessionCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getRejectedSessionCount() {
    Optional<VariableNode> component = getVariableComponent("RejectedSessionCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setRejectedSessionCount(UInteger value) {
    getVariableComponent("RejectedSessionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSessionTimeoutCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionTimeoutCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getSessionTimeoutCount() {
    Optional<VariableNode> component = getVariableComponent("SessionTimeoutCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSessionTimeoutCount(UInteger value) {
    getVariableComponent("SessionTimeoutCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSessionAbortCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SessionAbortCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getSessionAbortCount() {
    Optional<VariableNode> component = getVariableComponent("SessionAbortCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSessionAbortCount(UInteger value) {
    getVariableComponent("SessionAbortCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getPublishingIntervalCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "PublishingIntervalCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getPublishingIntervalCount() {
    Optional<VariableNode> component = getVariableComponent("PublishingIntervalCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setPublishingIntervalCount(UInteger value) {
    getVariableComponent("PublishingIntervalCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCurrentSubscriptionCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CurrentSubscriptionCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getCurrentSubscriptionCount() {
    Optional<VariableNode> component = getVariableComponent("CurrentSubscriptionCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCurrentSubscriptionCount(UInteger value) {
    getVariableComponent("CurrentSubscriptionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getCumulatedSubscriptionCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "CumulatedSubscriptionCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getCumulatedSubscriptionCount() {
    Optional<VariableNode> component = getVariableComponent("CumulatedSubscriptionCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setCumulatedSubscriptionCount(UInteger value) {
    getVariableComponent("CumulatedSubscriptionCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getSecurityRejectedRequestsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "SecurityRejectedRequestsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getSecurityRejectedRequestsCount() {
    Optional<VariableNode> component = getVariableComponent("SecurityRejectedRequestsCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setSecurityRejectedRequestsCount(UInteger value) {
    getVariableComponent("SecurityRejectedRequestsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public BaseDataVariableNode getRejectedRequestsCountNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "RejectedRequestsCount");
    return (BaseDataVariableNode) component.orElse(null);
  }

  public UInteger getRejectedRequestsCount() {
    Optional<VariableNode> component = getVariableComponent("RejectedRequestsCount");
    return component.map(node -> (UInteger) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setRejectedRequestsCount(UInteger value) {
    getVariableComponent("RejectedRequestsCount").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }
}
