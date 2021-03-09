/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class AggregateFilter extends MonitoringFilter implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=728");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=730");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=729");

    private final DateTime startTime;

    private final NodeId aggregateType;

    private final Double processingInterval;

    private final AggregateConfiguration aggregateConfiguration;

    public AggregateFilter(DateTime startTime, NodeId aggregateType, Double processingInterval,
                           AggregateConfiguration aggregateConfiguration) {
        this.startTime = startTime;
        this.aggregateType = aggregateType;
        this.processingInterval = processingInterval;
        this.aggregateConfiguration = aggregateConfiguration;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public NodeId getAggregateType() {
        return aggregateType;
    }

    public Double getProcessingInterval() {
        return processingInterval;
    }

    public AggregateConfiguration getAggregateConfiguration() {
        return aggregateConfiguration;
    }

    public static final class Codec extends GenericDataTypeCodec<AggregateFilter> {
        @Override
        public Class<AggregateFilter> getType() {
            return AggregateFilter.class;
        }

        @Override
        public AggregateFilter decode(SerializationContext context, UaDecoder decoder) {
            DateTime startTime = decoder.readDateTime("StartTime");
            NodeId aggregateType = decoder.readNodeId("AggregateType");
            Double processingInterval = decoder.readDouble("ProcessingInterval");
            AggregateConfiguration aggregateConfiguration = (AggregateConfiguration) decoder.readStruct("AggregateConfiguration", AggregateConfiguration.TYPE_ID);
            return new AggregateFilter(startTime, aggregateType, processingInterval, aggregateConfiguration);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, AggregateFilter value) {
            encoder.writeDateTime("StartTime", value.getStartTime());
            encoder.writeNodeId("AggregateType", value.getAggregateType());
            encoder.writeDouble("ProcessingInterval", value.getProcessingInterval());
            encoder.writeStruct("AggregateConfiguration", value.getAggregateConfiguration(), AggregateConfiguration.TYPE_ID);
        }
    }
}
