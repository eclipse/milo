/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;

public interface HistoricalDataConfigurationType extends BaseObjectType {
    QualifiedProperty<Boolean> STEPPED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Stepped",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<String> DEFINITION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Definition",
        NodeId.parse("ns=0;i=12"),
        ValueRanks.Scalar,
        String.class
    );

    QualifiedProperty<Double> MAX_TIME_INTERVAL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MaxTimeInterval",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<Double> MIN_TIME_INTERVAL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MinTimeInterval",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<Double> EXCEPTION_DEVIATION = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ExceptionDeviation",
        NodeId.parse("ns=0;i=11"),
        ValueRanks.Scalar,
        Double.class
    );

    QualifiedProperty<ExceptionDeviationFormat> EXCEPTION_DEVIATION_FORMAT = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "ExceptionDeviationFormat",
        NodeId.parse("ns=0;i=890"),
        ValueRanks.Scalar,
        ExceptionDeviationFormat.class
    );

    QualifiedProperty<DateTime> START_OF_ARCHIVE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StartOfArchive",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    QualifiedProperty<DateTime> START_OF_ONLINE_ARCHIVE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "StartOfOnlineArchive",
        NodeId.parse("ns=0;i=294"),
        ValueRanks.Scalar,
        DateTime.class
    );

    CompletableFuture<? extends PropertyType> getSteppedNode();

    CompletableFuture<Boolean> getStepped();

    CompletableFuture<StatusCode> setStepped(Boolean value);

    CompletableFuture<? extends PropertyType> getDefinitionNode();

    CompletableFuture<String> getDefinition();

    CompletableFuture<StatusCode> setDefinition(String value);

    CompletableFuture<? extends PropertyType> getMaxTimeIntervalNode();

    CompletableFuture<Double> getMaxTimeInterval();

    CompletableFuture<StatusCode> setMaxTimeInterval(Double value);

    CompletableFuture<? extends PropertyType> getMinTimeIntervalNode();

    CompletableFuture<Double> getMinTimeInterval();

    CompletableFuture<StatusCode> setMinTimeInterval(Double value);

    CompletableFuture<? extends PropertyType> getExceptionDeviationNode();

    CompletableFuture<Double> getExceptionDeviation();

    CompletableFuture<StatusCode> setExceptionDeviation(Double value);

    CompletableFuture<? extends PropertyType> getExceptionDeviationFormatNode();

    CompletableFuture<ExceptionDeviationFormat> getExceptionDeviationFormat();

    CompletableFuture<StatusCode> setExceptionDeviationFormat(ExceptionDeviationFormat value);

    CompletableFuture<? extends PropertyType> getStartOfArchiveNode();

    CompletableFuture<DateTime> getStartOfArchive();

    CompletableFuture<StatusCode> setStartOfArchive(DateTime value);

    CompletableFuture<? extends PropertyType> getStartOfOnlineArchiveNode();

    CompletableFuture<DateTime> getStartOfOnlineArchive();

    CompletableFuture<StatusCode> setStartOfOnlineArchive(DateTime value);

    CompletableFuture<? extends AggregateConfigurationType> getAggregateConfigurationNode();

    CompletableFuture<? extends FolderType> getAggregateFunctionsNode();
}
