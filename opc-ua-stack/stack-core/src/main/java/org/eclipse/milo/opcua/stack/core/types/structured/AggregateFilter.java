/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.22.4">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.22.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class AggregateFilter extends MonitoringFilter implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=728");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=730");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=729");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15312");

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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 730),
            new NodeId(0, 719),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StartTime", LocalizedText.NULL_VALUE, new NodeId(0, 294), -1, null, UInteger.valueOf(0), false),
                new StructureField("AggregateType", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("ProcessingInterval", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("AggregateConfiguration", LocalizedText.NULL_VALUE, new NodeId(0, 948), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AggregateFilter> {
        @Override
        public Class<AggregateFilter> getType() {
            return AggregateFilter.class;
        }

        @Override
        public AggregateFilter decodeType(SerializationContext context, UaDecoder decoder) {
            DateTime startTime = decoder.readDateTime("StartTime");
            NodeId aggregateType = decoder.readNodeId("AggregateType");
            Double processingInterval = decoder.readDouble("ProcessingInterval");
            AggregateConfiguration aggregateConfiguration = (AggregateConfiguration) decoder.readStruct("AggregateConfiguration", AggregateConfiguration.TYPE_ID);
            return new AggregateFilter(startTime, aggregateType, processingInterval, aggregateConfiguration);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, AggregateFilter value) {
            encoder.writeDateTime("StartTime", value.getStartTime());
            encoder.writeNodeId("AggregateType", value.getAggregateType());
            encoder.writeDouble("ProcessingInterval", value.getProcessingInterval());
            encoder.writeStruct("AggregateConfiguration", value.getAggregateConfiguration(), AggregateConfiguration.TYPE_ID);
        }
    }
}
