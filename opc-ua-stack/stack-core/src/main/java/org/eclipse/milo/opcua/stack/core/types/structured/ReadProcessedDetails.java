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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class ReadProcessedDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadProcessedDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadProcessedDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadProcessedDetails_Encoding_DefaultXml;

    protected final DateTime startTime;
    protected final DateTime endTime;
    protected final Double processingInterval;
    protected final NodeId[] aggregateType;
    protected final AggregateConfiguration aggregateConfiguration;

    public ReadProcessedDetails() {
        super();
        this.startTime = null;
        this.endTime = null;
        this.processingInterval = null;
        this.aggregateType = null;
        this.aggregateConfiguration = null;
    }

    public ReadProcessedDetails(DateTime startTime, DateTime endTime, Double processingInterval, NodeId[] aggregateType, AggregateConfiguration aggregateConfiguration) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.processingInterval = processingInterval;
        this.aggregateType = aggregateType;
        this.aggregateConfiguration = aggregateConfiguration;
    }

    public DateTime getStartTime() { return startTime; }

    public DateTime getEndTime() { return endTime; }

    public Double getProcessingInterval() { return processingInterval; }

    @Nullable
    public NodeId[] getAggregateType() { return aggregateType; }

    public AggregateConfiguration getAggregateConfiguration() { return aggregateConfiguration; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StartTime", startTime)
            .add("EndTime", endTime)
            .add("ProcessingInterval", processingInterval)
            .add("AggregateType", aggregateType)
            .add("AggregateConfiguration", aggregateConfiguration)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReadProcessedDetails> {

        @Override
        public Class<ReadProcessedDetails> getType() {
            return ReadProcessedDetails.class;
        }

        @Override
        public ReadProcessedDetails decode(UaDecoder decoder) throws UaSerializationException {
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");
            Double processingInterval = decoder.readDouble("ProcessingInterval");
            NodeId[] aggregateType = decoder.readArray("AggregateType", decoder::readNodeId, NodeId.class);
            AggregateConfiguration aggregateConfiguration = (AggregateConfiguration) decoder.readBuiltinStruct("AggregateConfiguration", AggregateConfiguration.class);

            return new ReadProcessedDetails(startTime, endTime, processingInterval, aggregateType, aggregateConfiguration);
        }

        @Override
        public void encode(ReadProcessedDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeDateTime("StartTime", value.startTime);
            encoder.writeDateTime("EndTime", value.endTime);
            encoder.writeDouble("ProcessingInterval", value.processingInterval);
            encoder.writeArray("AggregateType", value.aggregateType, encoder::writeNodeId);
            encoder.writeBuiltinStruct("AggregateConfiguration", value.aggregateConfiguration, AggregateConfiguration.class);
        }
    }

}
