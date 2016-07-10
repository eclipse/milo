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

package org.eclipse.milo.opcua.stack.core.types.structured;

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("AggregateFilterResult")
public class AggregateFilterResult extends MonitoringFilterResult {

    public static final NodeId TypeId = Identifiers.AggregateFilterResult;
    public static final NodeId BinaryEncodingId = Identifiers.AggregateFilterResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AggregateFilterResult_Encoding_DefaultXml;

    protected final DateTime _revisedStartTime;
    protected final Double _revisedProcessingInterval;
    protected final AggregateConfiguration _revisedAggregateConfiguration;

    public AggregateFilterResult() {
        super();
        this._revisedStartTime = null;
        this._revisedProcessingInterval = null;
        this._revisedAggregateConfiguration = null;
    }

    public AggregateFilterResult(DateTime _revisedStartTime, Double _revisedProcessingInterval, AggregateConfiguration _revisedAggregateConfiguration) {
        super();
        this._revisedStartTime = _revisedStartTime;
        this._revisedProcessingInterval = _revisedProcessingInterval;
        this._revisedAggregateConfiguration = _revisedAggregateConfiguration;
    }

    public DateTime getRevisedStartTime() { return _revisedStartTime; }

    public Double getRevisedProcessingInterval() { return _revisedProcessingInterval; }

    public AggregateConfiguration getRevisedAggregateConfiguration() { return _revisedAggregateConfiguration; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(AggregateFilterResult aggregateFilterResult, UaEncoder encoder) {
        encoder.encodeDateTime("RevisedStartTime", aggregateFilterResult._revisedStartTime);
        encoder.encodeDouble("RevisedProcessingInterval", aggregateFilterResult._revisedProcessingInterval);
        encoder.encodeSerializable("RevisedAggregateConfiguration", aggregateFilterResult._revisedAggregateConfiguration != null ? aggregateFilterResult._revisedAggregateConfiguration : new AggregateConfiguration());
    }

    public static AggregateFilterResult decode(UaDecoder decoder) {
        DateTime _revisedStartTime = decoder.decodeDateTime("RevisedStartTime");
        Double _revisedProcessingInterval = decoder.decodeDouble("RevisedProcessingInterval");
        AggregateConfiguration _revisedAggregateConfiguration = decoder.decodeSerializable("RevisedAggregateConfiguration", AggregateConfiguration.class);

        return new AggregateFilterResult(_revisedStartTime, _revisedProcessingInterval, _revisedAggregateConfiguration);
    }

    static {
        DelegateRegistry.registerEncoder(AggregateFilterResult::encode, AggregateFilterResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(AggregateFilterResult::decode, AggregateFilterResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
