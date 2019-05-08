/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyTypeNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.HistoricalDataConfigurationType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;

public class HistoricalDataConfigurationTypeNode extends BaseObjectTypeNode implements HistoricalDataConfigurationType {
    public HistoricalDataConfigurationTypeNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    public CompletableFuture<PropertyTypeNode> getSteppedNode() {
        return getPropertyNode(HistoricalDataConfigurationType.STEPPED);
    }

    public CompletableFuture<Boolean> getStepped() {
        return getProperty(HistoricalDataConfigurationType.STEPPED);
    }

    public CompletableFuture<StatusCode> setStepped(Boolean value) {
        return setProperty(HistoricalDataConfigurationType.STEPPED, value);
    }

    public CompletableFuture<PropertyTypeNode> getDefinitionNode() {
        return getPropertyNode(HistoricalDataConfigurationType.DEFINITION);
    }

    public CompletableFuture<String> getDefinition() {
        return getProperty(HistoricalDataConfigurationType.DEFINITION);
    }

    public CompletableFuture<StatusCode> setDefinition(String value) {
        return setProperty(HistoricalDataConfigurationType.DEFINITION, value);
    }

    public CompletableFuture<PropertyTypeNode> getMaxTimeIntervalNode() {
        return getPropertyNode(HistoricalDataConfigurationType.MAX_TIME_INTERVAL);
    }

    public CompletableFuture<Double> getMaxTimeInterval() {
        return getProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL);
    }

    public CompletableFuture<StatusCode> setMaxTimeInterval(Double value) {
        return setProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL, value);
    }

    public CompletableFuture<PropertyTypeNode> getMinTimeIntervalNode() {
        return getPropertyNode(HistoricalDataConfigurationType.MIN_TIME_INTERVAL);
    }

    public CompletableFuture<Double> getMinTimeInterval() {
        return getProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL);
    }

    public CompletableFuture<StatusCode> setMinTimeInterval(Double value) {
        return setProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL, value);
    }

    public CompletableFuture<PropertyTypeNode> getExceptionDeviationNode() {
        return getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION);
    }

    public CompletableFuture<Double> getExceptionDeviation() {
        return getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION);
    }

    public CompletableFuture<StatusCode> setExceptionDeviation(Double value) {
        return setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION, value);
    }

    public CompletableFuture<PropertyTypeNode> getExceptionDeviationFormatNode() {
        return getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT);
    }

    public CompletableFuture<ExceptionDeviationFormat> getExceptionDeviationFormat() {
        return getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT);
    }

    public CompletableFuture<StatusCode> setExceptionDeviationFormat(ExceptionDeviationFormat value) {
        return setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT, value);
    }

    public CompletableFuture<PropertyTypeNode> getStartOfArchiveNode() {
        return getPropertyNode(HistoricalDataConfigurationType.START_OF_ARCHIVE);
    }

    public CompletableFuture<DateTime> getStartOfArchive() {
        return getProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE);
    }

    public CompletableFuture<StatusCode> setStartOfArchive(DateTime value) {
        return setProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE, value);
    }

    public CompletableFuture<PropertyTypeNode> getStartOfOnlineArchiveNode() {
        return getPropertyNode(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE);
    }

    public CompletableFuture<DateTime> getStartOfOnlineArchive() {
        return getProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE);
    }

    public CompletableFuture<StatusCode> setStartOfOnlineArchive(DateTime value) {
        return setProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE, value);
    }

    @Override
    public CompletableFuture<AggregateConfigurationTypeNode> getAggregateConfigurationNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "AggregateConfiguration").thenApply(AggregateConfigurationTypeNode.class::cast);
    }

    @Override
    public CompletableFuture<FolderTypeNode> getAggregateFunctionsNode() {
        return getObjectComponent("http://opcfoundation.org/UA/", "AggregateFunctions").thenApply(FolderTypeNode.class::cast);
    }
}
