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
public class ReadRawModifiedDetails extends HistoryReadDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=647");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=649");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=648");

    private final Boolean isReadModified;

    private final DateTime startTime;

    private final DateTime endTime;

    private final UInteger numValuesPerNode;

    private final Boolean returnBounds;

    public ReadRawModifiedDetails(Boolean isReadModified, DateTime startTime, DateTime endTime,
                                  UInteger numValuesPerNode, Boolean returnBounds) {
        this.isReadModified = isReadModified;
        this.startTime = startTime;
        this.endTime = endTime;
        this.numValuesPerNode = numValuesPerNode;
        this.returnBounds = returnBounds;
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

    public Boolean getIsReadModified() {
        return isReadModified;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public UInteger getNumValuesPerNode() {
        return numValuesPerNode;
    }

    public Boolean getReturnBounds() {
        return returnBounds;
    }

    public static final class Codec extends GenericDataTypeCodec<ReadRawModifiedDetails> {
        @Override
        public Class<ReadRawModifiedDetails> getType() {
            return ReadRawModifiedDetails.class;
        }

        @Override
        public ReadRawModifiedDetails decode(SerializationContext context, UaDecoder decoder) {
            Boolean isReadModified = decoder.readBoolean("IsReadModified");
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");
            UInteger numValuesPerNode = decoder.readUInt32("NumValuesPerNode");
            Boolean returnBounds = decoder.readBoolean("ReturnBounds");
            return new ReadRawModifiedDetails(isReadModified, startTime, endTime, numValuesPerNode, returnBounds);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ReadRawModifiedDetails value) {
            encoder.writeBoolean("IsReadModified", value.getIsReadModified());
            encoder.writeDateTime("StartTime", value.getStartTime());
            encoder.writeDateTime("EndTime", value.getEndTime());
            encoder.writeUInt32("NumValuesPerNode", value.getNumValuesPerNode());
            encoder.writeBoolean("ReturnBounds", value.getReturnBounds());
        }
    }
}
