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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class AggregateFilterResult extends MonitoringFilterResult implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=737");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=739");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=738");

    private final DateTime revisedStartTime;

    private final Double revisedProcessingInterval;

    private final AggregateConfiguration revisedAggregateConfiguration;

    public AggregateFilterResult(DateTime revisedStartTime, Double revisedProcessingInterval,
                                 AggregateConfiguration revisedAggregateConfiguration) {
        this.revisedStartTime = revisedStartTime;
        this.revisedProcessingInterval = revisedProcessingInterval;
        this.revisedAggregateConfiguration = revisedAggregateConfiguration;
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

    public DateTime getRevisedStartTime() {
        return revisedStartTime;
    }

    public Double getRevisedProcessingInterval() {
        return revisedProcessingInterval;
    }

    public AggregateConfiguration getRevisedAggregateConfiguration() {
        return revisedAggregateConfiguration;
    }

    public static final class Codec extends GenericDataTypeCodec<AggregateFilterResult> {
        @Override
        public Class<AggregateFilterResult> getType() {
            return AggregateFilterResult.class;
        }

        @Override
        public AggregateFilterResult decode(SerializationContext context, UaDecoder decoder) {
            DateTime revisedStartTime = decoder.readDateTime("RevisedStartTime");
            Double revisedProcessingInterval = decoder.readDouble("RevisedProcessingInterval");
            AggregateConfiguration revisedAggregateConfiguration = (AggregateConfiguration) decoder.readStruct("RevisedAggregateConfiguration", AggregateConfiguration.TYPE_ID);
            return new AggregateFilterResult(revisedStartTime, revisedProcessingInterval, revisedAggregateConfiguration);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           AggregateFilterResult value) {
            encoder.writeDateTime("RevisedStartTime", value.getRevisedStartTime());
            encoder.writeDouble("RevisedProcessingInterval", value.getRevisedProcessingInterval());
            encoder.writeStruct("RevisedAggregateConfiguration", value.getRevisedAggregateConfiguration(), AggregateConfiguration.TYPE_ID);
        }
    }
}
