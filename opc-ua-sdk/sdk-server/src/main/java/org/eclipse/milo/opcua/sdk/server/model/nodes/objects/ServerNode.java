/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.ServerStatusNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ServerType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.structured.ServerStatusDataType;

public class ServerNode extends BaseObjectNode implements ServerType {
  public ServerNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                    LocalizedText displayName, LocalizedText description, UInteger writeMask,
                    UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public ServerNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                    LocalizedText displayName, LocalizedText description, UInteger writeMask,
                    UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getServerArrayNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerType.SERVER_ARRAY);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String[] getServerArray() {
    Optional<String[]> propertyValue = getProperty(ServerType.SERVER_ARRAY);
    return propertyValue.orElse(null);
  }

  public void setServerArray(String[] value) {
    setProperty(ServerType.SERVER_ARRAY, value);
  }

  public PropertyNode getNamespaceArrayNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerType.NAMESPACE_ARRAY);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String[] getNamespaceArray() {
    Optional<String[]> propertyValue = getProperty(ServerType.NAMESPACE_ARRAY);
    return propertyValue.orElse(null);
  }

  public void setNamespaceArray(String[] value) {
    setProperty(ServerType.NAMESPACE_ARRAY, value);
  }

  public PropertyNode getServiceLevelNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerType.SERVICE_LEVEL);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public UByte getServiceLevel() {
    Optional<UByte> propertyValue = getProperty(ServerType.SERVICE_LEVEL);
    return propertyValue.orElse(null);
  }

  public void setServiceLevel(UByte value) {
    setProperty(ServerType.SERVICE_LEVEL, value);
  }

  public PropertyNode getAuditingNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerType.AUDITING);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Boolean getAuditing() {
    Optional<Boolean> propertyValue = getProperty(ServerType.AUDITING);
    return propertyValue.orElse(null);
  }

  public void setAuditing(Boolean value) {
    setProperty(ServerType.AUDITING, value);
  }

  public PropertyNode getEstimatedReturnTimeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerType.ESTIMATED_RETURN_TIME);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public DateTime getEstimatedReturnTime() {
    Optional<DateTime> propertyValue = getProperty(ServerType.ESTIMATED_RETURN_TIME);
    return propertyValue.orElse(null);
  }

  public void setEstimatedReturnTime(DateTime value) {
    setProperty(ServerType.ESTIMATED_RETURN_TIME, value);
  }

  public ServerStatusNode getServerStatusNode() {
    Optional<VariableNode> component = getVariableComponent("http://opcfoundation.org/UA/", "ServerStatus");
    return (ServerStatusNode) component.orElse(null);
  }

  public ServerStatusDataType getServerStatus() {
    Optional<VariableNode> component = getVariableComponent("ServerStatus");
    return component.map(node -> (ServerStatusDataType) node.getValue().getValue().getValue()).orElse(null);
  }

  public void setServerStatus(ServerStatusDataType value) {
    getVariableComponent("ServerStatus").ifPresent(n -> n.setValue(new DataValue(new Variant(value))));
  }

  public ServerCapabilitiesNode getServerCapabilitiesNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ServerCapabilities");
    return (ServerCapabilitiesNode) component.orElse(null);
  }

  public ServerDiagnosticsNode getServerDiagnosticsNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ServerDiagnostics");
    return (ServerDiagnosticsNode) component.orElse(null);
  }

  public VendorServerInfoNode getVendorServerInfoNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "VendorServerInfo");
    return (VendorServerInfoNode) component.orElse(null);
  }

  public ServerRedundancyNode getServerRedundancyNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ServerRedundancy");
    return (ServerRedundancyNode) component.orElse(null);
  }

  public NamespacesNode getNamespacesNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "Namespaces");
    return (NamespacesNode) component.orElse(null);
  }
}
