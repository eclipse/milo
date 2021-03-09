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
public class ReadProcessedDetails extends HistoryReadDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=650");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=652");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=651");

    private final DateTime startTime;

    private final DateTime endTime;

    private final Double processingInterval;

    private final NodeId[] aggregateType;

    private final AggregateConfiguration aggregateConfiguration;

    public ReadProcessedDetails(DateTime startTime, DateTime endTime, Double processingInterval,
                                NodeId[] aggregateType, AggregateConfiguration aggregateConfiguration) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.processingInterval = processingInterval;
        this.aggregateType = aggregateType;
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

    public DateTime getEndTime() {
        return endTime;
    }

    public Double getProcessingInterval() {
        return processingInterval;
    }

    public NodeId[] getAggregateType() {
        return aggregateType;
    }

    public AggregateConfiguration getAggregateConfiguration() {
        return aggregateConfiguration;
    }

    public static final class Codec extends GenericDataTypeCodec<ReadProcessedDetails> {
        @Override
        public Class<ReadProcessedDetails> getType() {
            return ReadProcessedDetails.class;
        }

        @Override
        public ReadProcessedDetails decode(SerializationContext context, UaDecoder decoder) {
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");
            Double processingInterval = decoder.readDouble("ProcessingInterval");
            NodeId[] aggregateType = decoder.readNodeIdArray("AggregateType");
            AggregateConfiguration aggregateConfiguration = (AggregateConfiguration) decoder.readStruct("AggregateConfiguration", AggregateConfiguration.TYPE_ID);
            return new ReadProcessedDetails(startTime, endTime, processingInterval, aggregateType, aggregateConfiguration);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ReadProcessedDetails value) {
            encoder.writeDateTime("StartTime", value.getStartTime());
            encoder.writeDateTime("EndTime", value.getEndTime());
            encoder.writeDouble("ProcessingInterval", value.getProcessingInterval());
            encoder.writeNodeIdArray("AggregateType", value.getAggregateType());
            encoder.writeStruct("AggregateConfiguration", value.getAggregateConfiguration(), AggregateConfiguration.TYPE_ID);
        }
    }
}
