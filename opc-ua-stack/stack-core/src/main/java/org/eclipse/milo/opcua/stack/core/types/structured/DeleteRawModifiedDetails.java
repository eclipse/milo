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
public class DeleteRawModifiedDetails extends HistoryUpdateDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=686");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=688");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=687");

    private final Boolean isDeleteModified;

    private final DateTime startTime;

    private final DateTime endTime;

    public DeleteRawModifiedDetails(NodeId nodeId, Boolean isDeleteModified, DateTime startTime,
                                    DateTime endTime) {
        super(nodeId);
        this.isDeleteModified = isDeleteModified;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public Boolean getIsDeleteModified() {
        return isDeleteModified;
    }

    public DateTime getStartTime() {
        return startTime;
    }

    public DateTime getEndTime() {
        return endTime;
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteRawModifiedDetails> {
        @Override
        public Class<DeleteRawModifiedDetails> getType() {
            return DeleteRawModifiedDetails.class;
        }

        @Override
        public DeleteRawModifiedDetails decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            Boolean isDeleteModified = decoder.readBoolean("IsDeleteModified");
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");
            return new DeleteRawModifiedDetails(nodeId, isDeleteModified, startTime, endTime);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DeleteRawModifiedDetails value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeBoolean("IsDeleteModified", value.getIsDeleteModified());
            encoder.writeDateTime("StartTime", value.getStartTime());
            encoder.writeDateTime("EndTime", value.getEndTime());
        }
    }
}
