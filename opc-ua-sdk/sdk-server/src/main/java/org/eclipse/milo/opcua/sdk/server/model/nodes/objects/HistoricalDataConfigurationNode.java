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
import org.eclipse.milo.opcua.sdk.server.model.types.objects.HistoricalDataConfigurationType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;

public class HistoricalDataConfigurationNode extends BaseObjectNode implements HistoricalDataConfigurationType {
  public HistoricalDataConfigurationNode(UaNodeContext context, NodeId nodeId,
                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                         UInteger writeMask, UInteger userWriteMask) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
  }

  public HistoricalDataConfigurationNode(UaNodeContext context, NodeId nodeId,
                                         QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                         UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
    super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
  }

  public PropertyNode getSteppedNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.STEPPED);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Boolean getStepped() {
    Optional<Boolean> propertyValue = getProperty(HistoricalDataConfigurationType.STEPPED);
    return propertyValue.orElse(null);
  }

  public void setStepped(Boolean value) {
    setProperty(HistoricalDataConfigurationType.STEPPED, value);
  }

  public PropertyNode getDefinitionNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.DEFINITION);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public String getDefinition() {
    Optional<String> propertyValue = getProperty(HistoricalDataConfigurationType.DEFINITION);
    return propertyValue.orElse(null);
  }

  public void setDefinition(String value) {
    setProperty(HistoricalDataConfigurationType.DEFINITION, value);
  }

  public PropertyNode getMaxTimeIntervalNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.MAX_TIME_INTERVAL);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Double getMaxTimeInterval() {
    Optional<Double> propertyValue = getProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL);
    return propertyValue.orElse(null);
  }

  public void setMaxTimeInterval(Double value) {
    setProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL, value);
  }

  public PropertyNode getMinTimeIntervalNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.MIN_TIME_INTERVAL);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Double getMinTimeInterval() {
    Optional<Double> propertyValue = getProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL);
    return propertyValue.orElse(null);
  }

  public void setMinTimeInterval(Double value) {
    setProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL, value);
  }

  public PropertyNode getExceptionDeviationNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public Double getExceptionDeviation() {
    Optional<Double> propertyValue = getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION);
    return propertyValue.orElse(null);
  }

  public void setExceptionDeviation(Double value) {
    setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION, value);
  }

  public PropertyNode getExceptionDeviationFormatNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public ExceptionDeviationFormat getExceptionDeviationFormat() {
    Optional<ExceptionDeviationFormat> propertyValue = getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT);
    return propertyValue.orElse(null);
  }

  public void setExceptionDeviationFormat(ExceptionDeviationFormat value) {
    setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT, value);
  }

  public PropertyNode getStartOfArchiveNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.START_OF_ARCHIVE);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public DateTime getStartOfArchive() {
    Optional<DateTime> propertyValue = getProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE);
    return propertyValue.orElse(null);
  }

  public void setStartOfArchive(DateTime value) {
    setProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE, value);
  }

  public PropertyNode getStartOfOnlineArchiveNode() {
    Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE);
    return (PropertyNode) propertyNode.orElse(null);
  }

  public DateTime getStartOfOnlineArchive() {
    Optional<DateTime> propertyValue = getProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE);
    return propertyValue.orElse(null);
  }

  public void setStartOfOnlineArchive(DateTime value) {
    setProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE, value);
  }

  public AggregateConfigurationNode getAggregateConfigurationNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "AggregateConfiguration");
    return (AggregateConfigurationNode) component.orElse(null);
  }

  public FolderNode getAggregateFunctionsNode() {
    Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "AggregateFunctions");
    return (FolderNode) component.orElse(null);
  }
}
