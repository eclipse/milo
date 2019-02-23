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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.ServerCapabilitiesType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.structured.SignedSoftwareCertificate;

public class ServerCapabilitiesNode extends BaseObjectNode implements ServerCapabilitiesType {
  public ServerCapabilitiesNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public ServerCapabilitiesNode(UaNodeContext context, NodeId nodeId, QualifiedName browseName,
                                LocalizedText displayName, LocalizedText description, UInteger writeMask,
                                UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getServerProfileArrayNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.SERVER_PROFILE_ARRAY);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String[] getServerProfileArray() {
    Optional<String[]> propertyValue = getProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY);
    return propertyValue.orElse(null);
  }

  public void setServerProfileArray(String[] value) {
    setProperty(ServerCapabilitiesType.SERVER_PROFILE_ARRAY, value);
  }

  public PropertyNode getLocaleIdArrayNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.LOCALE_ID_ARRAY);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String[] getLocaleIdArray() {
    Optional<String[]> propertyValue = getProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY);
    return propertyValue.orElse(null);
  }

  public void setLocaleIdArray(String[] value) {
    setProperty(ServerCapabilitiesType.LOCALE_ID_ARRAY, value);
  }

  public PropertyNode getMinSupportedSampleRateNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Double getMinSupportedSampleRate() {
    Optional<Double> propertyValue = getProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE);
    return propertyValue.orElse(null);
  }

  public void setMinSupportedSampleRate(Double value) {
    setProperty(ServerCapabilitiesType.MIN_SUPPORTED_SAMPLE_RATE, value);
  }

  public PropertyNode getMaxBrowseContinuationPointsNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public UShort getMaxBrowseContinuationPoints() {
    Optional<UShort> propertyValue = getProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS);
    return propertyValue.orElse(null);
  }

  public void setMaxBrowseContinuationPoints(UShort value) {
    setProperty(ServerCapabilitiesType.MAX_BROWSE_CONTINUATION_POINTS, value);
  }

  public PropertyNode getMaxQueryContinuationPointsNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public UShort getMaxQueryContinuationPoints() {
    Optional<UShort> propertyValue = getProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS);
    return propertyValue.orElse(null);
  }

  public void setMaxQueryContinuationPoints(UShort value) {
    setProperty(ServerCapabilitiesType.MAX_QUERY_CONTINUATION_POINTS, value);
  }

  public PropertyNode getMaxHistoryContinuationPointsNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public UShort getMaxHistoryContinuationPoints() {
    Optional<UShort> propertyValue = getProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS);
    return propertyValue.orElse(null);
  }

  public void setMaxHistoryContinuationPoints(UShort value) {
    setProperty(ServerCapabilitiesType.MAX_HISTORY_CONTINUATION_POINTS, value);
  }

  public PropertyNode getSoftwareCertificatesNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.SOFTWARE_CERTIFICATES);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public SignedSoftwareCertificate[] getSoftwareCertificates() {
    Optional<SignedSoftwareCertificate[]> propertyValue = getProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES);
    return propertyValue.orElse(null);
  }

  public void setSoftwareCertificates(SignedSoftwareCertificate[] value) {
    setProperty(ServerCapabilitiesType.SOFTWARE_CERTIFICATES, value);
  }

  public PropertyNode getMaxArrayLengthNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_ARRAY_LENGTH);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public UInteger getMaxArrayLength() {
    Optional<UInteger> propertyValue = getProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH);
    return propertyValue.orElse(null);
  }

  public void setMaxArrayLength(UInteger value) {
    setProperty(ServerCapabilitiesType.MAX_ARRAY_LENGTH, value);
  }

  public PropertyNode getMaxStringLengthNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_STRING_LENGTH);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public UInteger getMaxStringLength() {
    Optional<UInteger> propertyValue = getProperty(ServerCapabilitiesType.MAX_STRING_LENGTH);
    return propertyValue.orElse(null);
  }

  public void setMaxStringLength(UInteger value) {
    setProperty(ServerCapabilitiesType.MAX_STRING_LENGTH, value);
  }

  public PropertyNode getMaxByteStringLengthNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public UInteger getMaxByteStringLength() {
    Optional<UInteger> propertyValue = getProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH);
    return propertyValue.orElse(null);
  }

  public void setMaxByteStringLength(UInteger value) {
    setProperty(ServerCapabilitiesType.MAX_BYTE_STRING_LENGTH, value);
  }

  public OperationLimitsNode getOperationLimitsNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "OperationLimits");
    return (OperationLimitsNode) component.orElse(null);
  }

  public FolderNode getModellingRulesNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "ModellingRules");
    return (FolderNode) component.orElse(null);
  }

  public FolderNode getAggregateFunctionsNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "AggregateFunctions");
    return (FolderNode) component.orElse(null);
  }
}
