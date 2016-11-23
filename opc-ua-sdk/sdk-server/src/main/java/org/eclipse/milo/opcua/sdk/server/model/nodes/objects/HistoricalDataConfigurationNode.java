/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.model.nodes.objects;

import java.util.Optional;

import org.eclipse.milo.opcua.sdk.server.api.ServerNodeMap;
import org.eclipse.milo.opcua.sdk.server.api.nodes.ObjectNode;
import org.eclipse.milo.opcua.sdk.server.api.nodes.VariableNode;
import org.eclipse.milo.opcua.sdk.server.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.server.model.types.objects.HistoricalDataConfigurationType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;

@org.eclipse.milo.opcua.sdk.core.annotations.UaObjectNode(typeName = "0:HistoricalDataConfigurationType")
public class HistoricalDataConfigurationNode extends BaseObjectNode implements HistoricalDataConfigurationType {

    public HistoricalDataConfigurationNode(
        ServerNodeMap nodeMap,
        NodeId nodeId,
        QualifiedName browseName,
        LocalizedText displayName,
        LocalizedText description,
        UInteger writeMask,
        UInteger userWriteMask,
        UByte eventNotifier) {

        super(nodeMap, nodeId, browseName, displayName, description, writeMask, userWriteMask, eventNotifier);
    }

    @Override
    public Boolean getStepped() {
        Optional<Boolean> property = getProperty(HistoricalDataConfigurationType.STEPPED);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getSteppedNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.STEPPED.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setStepped(Boolean value) {
        setProperty(HistoricalDataConfigurationType.STEPPED, value);
    }

    @Override
    public String getDefinition() {
        Optional<String> property = getProperty(HistoricalDataConfigurationType.DEFINITION);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getDefinitionNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.DEFINITION.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setDefinition(String value) {
        setProperty(HistoricalDataConfigurationType.DEFINITION, value);
    }

    @Override
    public Double getMaxTimeInterval() {
        Optional<Double> property = getProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMaxTimeIntervalNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.MAX_TIME_INTERVAL.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMaxTimeInterval(Double value) {
        setProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL, value);
    }

    @Override
    public Double getMinTimeInterval() {
        Optional<Double> property = getProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getMinTimeIntervalNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.MIN_TIME_INTERVAL.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setMinTimeInterval(Double value) {
        setProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL, value);
    }

    @Override
    public Double getExceptionDeviation() {
        Optional<Double> property = getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getExceptionDeviationNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setExceptionDeviation(Double value) {
        setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION, value);
    }

    @Override
    public ExceptionDeviationFormat getExceptionDeviationFormat() {
        Optional<ExceptionDeviationFormat> property = getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getExceptionDeviationFormatNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setExceptionDeviationFormat(ExceptionDeviationFormat value) {
        setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT, value);
    }

    @Override
    public DateTime getStartOfArchive() {
        Optional<DateTime> property = getProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getStartOfArchiveNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.START_OF_ARCHIVE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setStartOfArchive(DateTime value) {
        setProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE, value);
    }

    @Override
    public DateTime getStartOfOnlineArchive() {
        Optional<DateTime> property = getProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE);

        return property.orElse(null);
    }

    @Override
    public PropertyNode getStartOfOnlineArchiveNode() {
        Optional<VariableNode> propertyNode = getPropertyNode(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE.getBrowseName());

        return propertyNode.map(n -> (PropertyNode) n).orElse(null);
    }

    @Override
    public void setStartOfOnlineArchive(DateTime value) {
        setProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE, value);
    }

    @Override
    public AggregateConfigurationNode getAggregateConfigurationNode() {
        Optional<ObjectNode> component = getObjectComponent("AggregateConfiguration");

        return component.map(node -> (AggregateConfigurationNode) node).orElse(null);
    }

    @Override
    public FolderNode getAggregateFunctionsNode() {
        Optional<ObjectNode> component = getObjectComponent("AggregateFunctions");

        return component.map(node -> (FolderNode) node).orElse(null);
    }

}
