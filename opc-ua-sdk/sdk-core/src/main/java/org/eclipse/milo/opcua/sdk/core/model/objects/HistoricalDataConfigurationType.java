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

package org.eclipse.milo.opcua.sdk.core.model.objects;

import org.eclipse.milo.opcua.sdk.core.model.BasicProperty;
import org.eclipse.milo.opcua.sdk.core.model.Property;
import org.eclipse.milo.opcua.sdk.core.model.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
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

    Boolean getStepped();

    PropertyType getSteppedNode();

    void setStepped(Boolean value);

    String getDefinition();

    PropertyType getDefinitionNode();

    void setDefinition(String value);

    Double getMaxTimeInterval();

    PropertyType getMaxTimeIntervalNode();

    void setMaxTimeInterval(Double value);

    Double getMinTimeInterval();

    PropertyType getMinTimeIntervalNode();

    void setMinTimeInterval(Double value);

    Double getExceptionDeviation();

    PropertyType getExceptionDeviationNode();

    void setExceptionDeviation(Double value);

    ExceptionDeviationFormat getExceptionDeviationFormat();

    PropertyType getExceptionDeviationFormatNode();

    void setExceptionDeviationFormat(ExceptionDeviationFormat value);

    DateTime getStartOfArchive();

    PropertyType getStartOfArchiveNode();

    void setStartOfArchive(DateTime value);

    DateTime getStartOfOnlineArchive();

    PropertyType getStartOfOnlineArchiveNode();

    void setStartOfOnlineArchive(DateTime value);

    AggregateConfigurationType getAggregateConfigurationNode();

    FolderType getAggregateFunctionsNode();

}
