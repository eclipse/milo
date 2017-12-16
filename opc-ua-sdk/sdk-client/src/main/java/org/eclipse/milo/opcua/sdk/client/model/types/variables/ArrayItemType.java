/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.sdk.client.model.types.variables;

import java.util.concurrent.CompletableFuture;

import org.eclipse.milo.opcua.sdk.core.ValueRanks;
import org.eclipse.milo.opcua.sdk.core.model.QualifiedProperty;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.AxisScaleEnumeration;
import org.eclipse.milo.opcua.stack.core.types.structured.EUInformation;
import org.eclipse.milo.opcua.stack.core.types.structured.Range;

public interface ArrayItemType extends DataItemType {
    QualifiedProperty<Range> INSTRUMENT_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "InstrumentRange",
        NodeId.parse("ns=0;i=884"),
        ValueRanks.Scalar,
        Range.class
    );

    QualifiedProperty<Range> E_U_RANGE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EURange",
        NodeId.parse("ns=0;i=884"),
        ValueRanks.Scalar,
        Range.class
    );

    QualifiedProperty<EUInformation> ENGINEERING_UNITS = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "EngineeringUnits",
        NodeId.parse("ns=0;i=887"),
        ValueRanks.Scalar,
        EUInformation.class
    );

    QualifiedProperty<LocalizedText> TITLE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "Title",
        NodeId.parse("ns=0;i=21"),
        ValueRanks.Scalar,
        LocalizedText.class
    );

    QualifiedProperty<AxisScaleEnumeration> AXIS_SCALE_TYPE = new QualifiedProperty<>(
        "http://opcfoundation.org/UA/",
        "AxisScaleType",
        NodeId.parse("ns=0;i=12077"),
        ValueRanks.Scalar,
        AxisScaleEnumeration.class
    );

    CompletableFuture<? extends PropertyType> getInstrumentRangeNode();

    CompletableFuture<Range> getInstrumentRange();

    CompletableFuture<StatusCode> setInstrumentRange(Range value);

    CompletableFuture<? extends PropertyType> getEURangeNode();

    CompletableFuture<Range> getEURange();

    CompletableFuture<StatusCode> setEURange(Range value);

    CompletableFuture<? extends PropertyType> getEngineeringUnitsNode();

    CompletableFuture<EUInformation> getEngineeringUnits();

    CompletableFuture<StatusCode> setEngineeringUnits(EUInformation value);

    CompletableFuture<? extends PropertyType> getTitleNode();

    CompletableFuture<LocalizedText> getTitle();

    CompletableFuture<StatusCode> setTitle(LocalizedText value);

    CompletableFuture<? extends PropertyType> getAxisScaleTypeNode();

    CompletableFuture<AxisScaleEnumeration> getAxisScaleType();

    CompletableFuture<StatusCode> setAxisScaleType(AxisScaleEnumeration value);
}
