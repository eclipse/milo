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

import org.eclipse.milo.opcua.sdk.core.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.core.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.HistoricalDataConfigurationType;
import org.eclipse.milo.opcua.sdk.server.nodes.UaNodeContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;

public class HistoricalDataConfigurationTypeNode extends BaseObjectTypeNode implements HistoricalDataConfigurationType {
    public HistoricalDataConfigurationTypeNode(UaNodeContext context, NodeId nodeId,
                                               QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                               UInteger writeMask, UInteger userWriteMask) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask);
    }

    public HistoricalDataConfigurationTypeNode(UaNodeContext context, NodeId nodeId,
                                               QualifiedName browseName, LocalizedText displayName, LocalizedText description,
                                               UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(context, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public PropertyTypeNode getSteppedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.STEPPED);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Boolean getStepped() {
        Optional<Boolean> propertyValue = getProperty(HistoricalDataConfigurationType.STEPPED);
        return propertyValue.orElse(null);
    }

    @Override
    public void setStepped(Boolean value) {
        setProperty(HistoricalDataConfigurationType.STEPPED, value);
    }

    @Override
    public PropertyTypeNode getDefinitionNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.DEFINITION);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public String getDefinition() {
        Optional<String> propertyValue = getProperty(HistoricalDataConfigurationType.DEFINITION);
        return propertyValue.orElse(null);
    }

    @Override
    public void setDefinition(String value) {
        setProperty(HistoricalDataConfigurationType.DEFINITION, value);
    }

    @Override
    public PropertyTypeNode getMaxTimeIntervalNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.MAX_TIME_INTERVAL);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Double getMaxTimeInterval() {
        Optional<Double> propertyValue = getProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMaxTimeInterval(Double value) {
        setProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL, value);
    }

    @Override
    public PropertyTypeNode getMinTimeIntervalNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.MIN_TIME_INTERVAL);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Double getMinTimeInterval() {
        Optional<Double> propertyValue = getProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL);
        return propertyValue.orElse(null);
    }

    @Override
    public void setMinTimeInterval(Double value) {
        setProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL, value);
    }

    @Override
    public PropertyTypeNode getExceptionDeviationNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public Double getExceptionDeviation() {
        Optional<Double> propertyValue = getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION);
        return propertyValue.orElse(null);
    }

    @Override
    public void setExceptionDeviation(Double value) {
        setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION, value);
    }

    @Override
    public PropertyTypeNode getExceptionDeviationFormatNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public ExceptionDeviationFormat getExceptionDeviationFormat() {
        Optional<ExceptionDeviationFormat> propertyValue = getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT);
        return propertyValue.orElse(null);
    }

    @Override
    public void setExceptionDeviationFormat(ExceptionDeviationFormat value) {
        setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT, value);
    }

    @Override
    public PropertyTypeNode getStartOfArchiveNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.START_OF_ARCHIVE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getStartOfArchive() {
        Optional<DateTime> propertyValue = getProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setStartOfArchive(DateTime value) {
        setProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE, value);
    }

    @Override
    public PropertyTypeNode getStartOfOnlineArchiveNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE);
        return (PropertyTypeNode) propertyNode.orElse(null);
    }

    @Override
    public DateTime getStartOfOnlineArchive() {
        Optional<DateTime> propertyValue = getProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE);
        return propertyValue.orElse(null);
    }

    @Override
    public void setStartOfOnlineArchive(DateTime value) {
        setProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE, value);
    }

    @Override
    public AggregateConfigurationTypeNode getAggregateConfigurationNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "AggregateConfiguration");
        return (AggregateConfigurationTypeNode) component.orElse(null);
    }

    @Override
    public FolderTypeNode getAggregateFunctionsNode() {
        Optional<ObjectNode> component = getObjectComponent("http://opcfoundation.org/UA/", "AggregateFunctions");
        return (FolderTypeNode) component.orElse(null);
    }
}
