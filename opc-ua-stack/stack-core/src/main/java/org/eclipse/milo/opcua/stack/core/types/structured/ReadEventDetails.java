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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ReadEventDetails extends HistoryReadDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=644");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=646");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=645");

    private final UInteger numValuesPerNode;

    private final DateTime startTime;

    private final DateTime endTime;

    private final EventFilter filter;

    public ReadEventDetails(UInteger numValuesPerNode, DateTime startTime, DateTime endTime,
                            EventFilter filter) {
        this.numValuesPerNode = numValuesPerNode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.filter = filter;
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

    public UInteger getNumValuesPerNode() {
        return numValuesPerNode;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public EventFilter getFilter() {
        return filter;
    }

    public static final class Codec extends GenericDataTypeCodec<ReadEventDetails> {
        @Override
        public Class<ReadEventDetails> getType() {
            return ReadEventDetails.class;
        }

        @Override
        public ReadEventDetails decode(SerializationContext context, UaDecoder decoder) {
            UInteger numValuesPerNode = decoder.readUInt32("NumValuesPerNode");
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");
            EventFilter filter = (EventFilter) decoder.readStruct("Filter", EventFilter.TYPE_ID);
            return new ReadEventDetails(numValuesPerNode, startTime, endTime, filter);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ReadEventDetails value) {
            encoder.writeUInt32("NumValuesPerNode", value.getNumValuesPerNode());
            encoder.writeDateTime("StartTime", value.getStartTime());
            encoder.writeDateTime("EndTime", value.getEndTime());
            encoder.writeStruct("Filter", value.getFilter(), EventFilter.TYPE_ID);
        }
    }
}
