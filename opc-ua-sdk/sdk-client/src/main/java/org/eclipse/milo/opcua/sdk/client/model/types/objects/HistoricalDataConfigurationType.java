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

package org.eclipse.milo.opcua.sdk.client.model.types.objects;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.client.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;


public interface HistoricalDataConfigurationType extends BaseObjectType {

    Property<Boolean> STEPPED = new BasicProperty<>(
        QualifiedName.parse("0:Stepped"),
        NodeId.parse("ns=0;i=1"),
        -1,
        Boolean.class
    );

    Property<String> DEFINITION = new BasicProperty<>(
        QualifiedName.parse("0:Definition"),
        NodeId.parse("ns=0;i=12"),
        -1,
        String.class
    );

    Property<Double> MAX_TIME_INTERVAL = new BasicProperty<>(
        QualifiedName.parse("0:MaxTimeInterval"),
        NodeId.parse("ns=0;i=290"),
        -1,
        Double.class
    );

    Property<Double> MIN_TIME_INTERVAL = new BasicProperty<>(
        QualifiedName.parse("0:MinTimeInterval"),
        NodeId.parse("ns=0;i=290"),
        -1,
        Double.class
    );

    Property<Double> EXCEPTION_DEVIATION = new BasicProperty<>(
        QualifiedName.parse("0:ExceptionDeviation"),
        NodeId.parse("ns=0;i=11"),
        -1,
        Double.class
    );

    Property<ExceptionDeviationFormat> EXCEPTION_DEVIATION_FORMAT = new BasicProperty<>(
        QualifiedName.parse("0:ExceptionDeviationFormat"),
        NodeId.parse("ns=0;i=890"),
        -1,
        ExceptionDeviationFormat.class
    );

    Property<DateTime> START_OF_ARCHIVE = new BasicProperty<>(
        QualifiedName.parse("0:StartOfArchive"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );

    Property<DateTime> START_OF_ONLINE_ARCHIVE = new BasicProperty<>(
        QualifiedName.parse("0:StartOfOnlineArchive"),
        NodeId.parse("ns=0;i=294"),
        -1,
        DateTime.class
    );


    CompletableFuture<? extends PropertyType> stepped();

    CompletableFuture<Boolean> getStepped();

    CompletableFuture<StatusCode> setStepped(Boolean value);

    CompletableFuture<? extends PropertyType> definition();

    CompletableFuture<String> getDefinition();

    CompletableFuture<StatusCode> setDefinition(String value);

    CompletableFuture<? extends PropertyType> maxTimeInterval();

    CompletableFuture<Double> getMaxTimeInterval();

    CompletableFuture<StatusCode> setMaxTimeInterval(Double value);

    CompletableFuture<? extends PropertyType> minTimeInterval();

    CompletableFuture<Double> getMinTimeInterval();

    CompletableFuture<StatusCode> setMinTimeInterval(Double value);

    CompletableFuture<? extends PropertyType> exceptionDeviation();

    CompletableFuture<Double> getExceptionDeviation();

    CompletableFuture<StatusCode> setExceptionDeviation(Double value);

    CompletableFuture<? extends PropertyType> exceptionDeviationFormat();

    CompletableFuture<ExceptionDeviationFormat> getExceptionDeviationFormat();

    CompletableFuture<StatusCode> setExceptionDeviationFormat(ExceptionDeviationFormat value);

    CompletableFuture<? extends PropertyType> startOfArchive();

    CompletableFuture<DateTime> getStartOfArchive();

    CompletableFuture<StatusCode> setStartOfArchive(DateTime value);

    CompletableFuture<? extends PropertyType> startOfOnlineArchive();

    CompletableFuture<DateTime> getStartOfOnlineArchive();

    CompletableFuture<StatusCode> setStartOfOnlineArchive(DateTime value);

    CompletableFuture<? extends AggregateConfigurationType> aggregateConfiguration();

    CompletableFuture<? extends FolderType> aggregateFunctions();


}