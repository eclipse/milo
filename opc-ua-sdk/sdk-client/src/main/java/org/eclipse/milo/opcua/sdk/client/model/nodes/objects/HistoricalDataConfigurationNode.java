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

package org.eclipse.milo.opcua.sdk.client.model.nodes.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.sdk.client.model.nodes.variables.PropertyNode;
import org.eclipse.milo.opcua.sdk.client.model.types.objects.HistoricalDataConfigurationType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;


public class HistoricalDataConfigurationNode extends BaseObjectNode implements HistoricalDataConfigurationType {

    public HistoricalDataConfigurationNode(OpcUaClient client, NodeId nodeId) {
        super(client, nodeId);
    }

    @Override
    public CompletableFuture<PropertyNode> stepped() {
        return getPropertyNode(HistoricalDataConfigurationType.STEPPED.getBrowseName());
    }

    @Override
    public CompletableFuture<Boolean> getStepped() {
        return getProperty(HistoricalDataConfigurationType.STEPPED);
    }

    @Override
    public CompletableFuture<StatusCode> setStepped(Boolean value) {
        return setProperty(HistoricalDataConfigurationType.STEPPED, value);
    }

    @Override
    public CompletableFuture<PropertyNode> definition() {
        return getPropertyNode(HistoricalDataConfigurationType.DEFINITION.getBrowseName());
    }

    @Override
    public CompletableFuture<String> getDefinition() {
        return getProperty(HistoricalDataConfigurationType.DEFINITION);
    }

    @Override
    public CompletableFuture<StatusCode> setDefinition(String value) {
        return setProperty(HistoricalDataConfigurationType.DEFINITION, value);
    }

    @Override
    public CompletableFuture<PropertyNode> maxTimeInterval() {
        return getPropertyNode(HistoricalDataConfigurationType.MAX_TIME_INTERVAL.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getMaxTimeInterval() {
        return getProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL);
    }

    @Override
    public CompletableFuture<StatusCode> setMaxTimeInterval(Double value) {
        return setProperty(HistoricalDataConfigurationType.MAX_TIME_INTERVAL, value);
    }

    @Override
    public CompletableFuture<PropertyNode> minTimeInterval() {
        return getPropertyNode(HistoricalDataConfigurationType.MIN_TIME_INTERVAL.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getMinTimeInterval() {
        return getProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL);
    }

    @Override
    public CompletableFuture<StatusCode> setMinTimeInterval(Double value) {
        return setProperty(HistoricalDataConfigurationType.MIN_TIME_INTERVAL, value);
    }

    @Override
    public CompletableFuture<PropertyNode> exceptionDeviation() {
        return getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION.getBrowseName());
    }

    @Override
    public CompletableFuture<Double> getExceptionDeviation() {
        return getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION);
    }

    @Override
    public CompletableFuture<StatusCode> setExceptionDeviation(Double value) {
        return setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION, value);
    }

    @Override
    public CompletableFuture<PropertyNode> exceptionDeviationFormat() {
        return getPropertyNode(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT.getBrowseName());
    }

    @Override
    public CompletableFuture<ExceptionDeviationFormat> getExceptionDeviationFormat() {
        return getProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT);
    }

    @Override
    public CompletableFuture<StatusCode> setExceptionDeviationFormat(ExceptionDeviationFormat value) {
        return setProperty(HistoricalDataConfigurationType.EXCEPTION_DEVIATION_FORMAT, value);
    }

    @Override
    public CompletableFuture<PropertyNode> startOfArchive() {
        return getPropertyNode(HistoricalDataConfigurationType.START_OF_ARCHIVE.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getStartOfArchive() {
        return getProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE);
    }

    @Override
    public CompletableFuture<StatusCode> setStartOfArchive(DateTime value) {
        return setProperty(HistoricalDataConfigurationType.START_OF_ARCHIVE, value);
    }

    @Override
    public CompletableFuture<PropertyNode> startOfOnlineArchive() {
        return getPropertyNode(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE.getBrowseName());
    }

    @Override
    public CompletableFuture<DateTime> getStartOfOnlineArchive() {
        return getProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE);
    }

    @Override
    public CompletableFuture<StatusCode> setStartOfOnlineArchive(DateTime value) {
        return setProperty(HistoricalDataConfigurationType.START_OF_ONLINE_ARCHIVE, value);
    }


    @Override
    public CompletableFuture<AggregateConfigurationNode> aggregateConfiguration() {
        return getObjectComponent(QualifiedName.parse("0:AggregateConfiguration"))
            .thenApply(AggregateConfigurationNode.class::cast);
    }

    @Override
    public CompletableFuture<FolderNode> aggregateFunctions() {
        return getObjectComponent(QualifiedName.parse("0:AggregateFunctions"))
            .thenApply(FolderNode.class::cast);
    }

}