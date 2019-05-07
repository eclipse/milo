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
public class DeleteAtTimeDetails extends HistoryUpdateDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=689");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=691");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=690");

    private final DateTime[] reqTimes;

    public DeleteAtTimeDetails(NodeId nodeId, DateTime[] reqTimes) {
        super(nodeId);
        this.reqTimes = reqTimes;
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

    public DateTime[] getReqTimes() {
        return reqTimes;
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteAtTimeDetails> {
        @Override
        public Class<DeleteAtTimeDetails> getType() {
            return DeleteAtTimeDetails.class;
        }

        @Override
        public DeleteAtTimeDetails decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            DateTime[] reqTimes = decoder.readDateTimeArray("ReqTimes");
            return new DeleteAtTimeDetails(nodeId, reqTimes);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DeleteAtTimeDetails value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeDateTimeArray("ReqTimes", value.getReqTimes());
        }
    }
}
