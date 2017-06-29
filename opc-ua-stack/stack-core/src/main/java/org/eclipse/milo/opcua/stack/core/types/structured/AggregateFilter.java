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

public class AggregateFilter extends MonitoringFilter {

    public static final NodeId TypeId = Identifiers.AggregateFilter;
    public static final NodeId BinaryEncodingId = Identifiers.AggregateFilter_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AggregateFilter_Encoding_DefaultXml;

    protected final DateTime startTime;
    protected final NodeId aggregateType;
    protected final Double processingInterval;
    protected final AggregateConfiguration aggregateConfiguration;

    public AggregateFilter() {
        super();
        this.startTime = null;
        this.aggregateType = null;
        this.processingInterval = null;
        this.aggregateConfiguration = null;
    }

    public AggregateFilter(DateTime startTime, NodeId aggregateType, Double processingInterval, AggregateConfiguration aggregateConfiguration) {
        super();
        this.startTime = startTime;
        this.aggregateType = aggregateType;
        this.processingInterval = processingInterval;
        this.aggregateConfiguration = aggregateConfiguration;
    }

    public DateTime getStartTime() { return startTime; }

    public NodeId getAggregateType() { return aggregateType; }

    public Double getProcessingInterval() { return processingInterval; }

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
            .add("AggregateType", aggregateType)
            .add("ProcessingInterval", processingInterval)
            .add("AggregateConfiguration", aggregateConfiguration)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<AggregateFilter> {

        @Override
        public Class<AggregateFilter> getType() {
            return AggregateFilter.class;
        }

        @Override
        public AggregateFilter decode(UaDecoder decoder) throws UaSerializationException {
            DateTime startTime = decoder.readDateTime("StartTime");
            NodeId aggregateType = decoder.readNodeId("AggregateType");
            Double processingInterval = decoder.readDouble("ProcessingInterval");
            AggregateConfiguration aggregateConfiguration = (AggregateConfiguration) decoder.readBuiltinStruct("AggregateConfiguration", AggregateConfiguration.class);

            return new AggregateFilter(startTime, aggregateType, processingInterval, aggregateConfiguration);
        }

        @Override
        public void encode(AggregateFilter value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeDateTime("StartTime", value.startTime);
            encoder.writeNodeId("AggregateType", value.aggregateType);
            encoder.writeDouble("ProcessingInterval", value.processingInterval);
            encoder.writeBuiltinStruct("AggregateConfiguration", value.aggregateConfiguration, AggregateConfiguration.class);
        }
    }

}
