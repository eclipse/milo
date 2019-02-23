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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ServerConfigurationType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ServerConfigurationNode extends BaseObjectNode implements ServerConfigurationType {
  public ServerConfigurationNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public ServerConfigurationNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                 LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                 UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getServerCapabilitiesNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerConfigurationType.SERVER_CAPABILITIES);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String[] getServerCapabilities() {
    Optional<String[]> propertyValue = getProperty(ServerConfigurationType.SERVER_CAPABILITIES);
    return propertyValue.orElse(null);
  }

  public void setServerCapabilities(String[] value) {
    setProperty(ServerConfigurationType.SERVER_CAPABILITIES, value);
  }

  public PropertyNode getSupportedPrivateKeyFormatsNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String[] getSupportedPrivateKeyFormats() {
    Optional<String[]> propertyValue = getProperty(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS);
    return propertyValue.orElse(null);
  }

  public void setSupportedPrivateKeyFormats(String[] value) {
    setProperty(ServerConfigurationType.SUPPORTED_PRIVATE_KEY_FORMATS, value);
  }

  public PropertyNode getMaxTrustListSizeNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerConfigurationType.MAX_TRUST_LIST_SIZE);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public UInteger getMaxTrustListSize() {
    Optional<UInteger> propertyValue = getProperty(ServerConfigurationType.MAX_TRUST_LIST_SIZE);
    return propertyValue.orElse(null);
  }

  public void setMaxTrustListSize(UInteger value) {
    setProperty(ServerConfigurationType.MAX_TRUST_LIST_SIZE, value);
  }

  public PropertyNode getMulticastDnsEnabledNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerConfigurationType.MULTICAST_DNS_ENABLED);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Boolean getMulticastDnsEnabled() {
    Optional<Boolean> propertyValue = getProperty(ServerConfigurationType.MULTICAST_DNS_ENABLED);
    return propertyValue.orElse(null);
  }

  public void setMulticastDnsEnabled(Boolean value) {
    setProperty(ServerConfigurationType.MULTICAST_DNS_ENABLED, value);
  }

  public CertificateGroupFolderNode getCertificateGroupsNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "CertificateGroups");
    return (CertificateGroupFolderNode) component.orElse(null);
  }
}
