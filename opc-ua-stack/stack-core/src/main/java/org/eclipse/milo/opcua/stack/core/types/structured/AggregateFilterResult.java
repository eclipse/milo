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

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class AggregateFilterResult extends MonitoringFilterResult {

    public static final NodeId TypeId = Identifiers.AggregateFilterResult;
    public static final NodeId BinaryEncodingId = Identifiers.AggregateFilterResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AggregateFilterResult_Encoding_DefaultXml;

    protected final DateTime revisedStartTime;
    protected final Double revisedProcessingInterval;
    protected final AggregateConfiguration revisedAggregateConfiguration;

    public AggregateFilterResult() {
        super();
        this.revisedStartTime = null;
        this.revisedProcessingInterval = null;
        this.revisedAggregateConfiguration = null;
    }

    public AggregateFilterResult(DateTime revisedStartTime, Double revisedProcessingInterval, AggregateConfiguration revisedAggregateConfiguration) {
        super();
        this.revisedStartTime = revisedStartTime;
        this.revisedProcessingInterval = revisedProcessingInterval;
        this.revisedAggregateConfiguration = revisedAggregateConfiguration;
    }

    public DateTime getRevisedStartTime() { return revisedStartTime; }

    public Double getRevisedProcessingInterval() { return revisedProcessingInterval; }

    public AggregateConfiguration getRevisedAggregateConfiguration() { return revisedAggregateConfiguration; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RevisedStartTime", revisedStartTime)
            .add("RevisedProcessingInterval", revisedProcessingInterval)
            .add("RevisedAggregateConfiguration", revisedAggregateConfiguration)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AggregateFilterResult> {

        @Override
        public Class<AggregateFilterResult> getType() {
            return AggregateFilterResult.class;
        }

        @Override
        public AggregateFilterResult decode(UaDecoder decoder) throws UaSerializationException {
            DateTime revisedStartTime = decoder.readDateTime("RevisedStartTime");
            Double revisedProcessingInterval = decoder.readDouble("RevisedProcessingInterval");
            AggregateConfiguration revisedAggregateConfiguration = (AggregateConfiguration) decoder.readBuiltinStruct("RevisedAggregateConfiguration", AggregateConfiguration.class);

            return new AggregateFilterResult(revisedStartTime, revisedProcessingInterval, revisedAggregateConfiguration);
        }

        @Override
        public void encode(AggregateFilterResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeDateTime("RevisedStartTime", value.revisedStartTime);
            encoder.writeDouble("RevisedProcessingInterval", value.revisedProcessingInterval);
            encoder.writeBuiltinStruct("RevisedAggregateConfiguration", value.revisedAggregateConfiguration, AggregateConfiguration.class);
        }
    }

}
