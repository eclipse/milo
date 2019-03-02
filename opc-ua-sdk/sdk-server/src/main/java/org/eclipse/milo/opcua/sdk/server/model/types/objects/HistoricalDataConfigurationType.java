/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.model.types.objects;

import org.eclipse.milo.opcua.sdk.core.QualifiedProperty;
import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.server.model.types.variables.PropertyType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ExceptionDeviationFormat;

public interface HistoricalDataConfigurationType extends BaseObjectType {
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

    QualifiedProperty<Boolean> STEPPED = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Stepped",
        NodeId.parse("ns=0;i=1"),
        ValueRanks.Scalar,
        Boolean.class
    );

    QualifiedProperty<Double> MIN_TIME_INTERVAL = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "MinTimeInterval",
        NodeId.parse("ns=0;i=290"),
        ValueRanks.Scalar,
        Double.class
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

    PropertyType getStartOfArchiveNode();

    DateTime getStartOfArchive();

    void setStartOfArchive(DateTime value);

    PropertyType getStartOfOnlineArchiveNode();

    DateTime getStartOfOnlineArchive();

    void setStartOfOnlineArchive(DateTime value);

    PropertyType getExceptionDeviationNode();

    Double getExceptionDeviation();

    void setExceptionDeviation(Double value);

    PropertyType getExceptionDeviationFormatNode();

    ExceptionDeviationFormat getExceptionDeviationFormat();

    void setExceptionDeviationFormat(ExceptionDeviationFormat value);

    PropertyType getSteppedNode();

    Boolean getStepped();

    void setStepped(Boolean value);

    PropertyType getMinTimeIntervalNode();

    Double getMinTimeInterval();

    void setMinTimeInterval(Double value);

    PropertyType getDefinitionNode();

    String getDefinition();

    void setDefinition(String value);

    PropertyType getMaxTimeIntervalNode();

    Double getMaxTimeInterval();

    void setMaxTimeInterval(Double value);

    FolderType getAggregateFunctionsNode();

    AggregateConfigurationType getAggregateConfigurationNode();
}
