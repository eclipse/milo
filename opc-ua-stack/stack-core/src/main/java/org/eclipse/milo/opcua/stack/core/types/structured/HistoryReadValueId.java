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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class HistoryReadValueId extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=635");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=637");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=636");

    private final NodeId nodeId;

    private final String indexRange;

    private final QualifiedName dataEncoding;

    private final ByteString continuationPoint;

    public HistoryReadValueId(NodeId nodeId, String indexRange, QualifiedName dataEncoding,
                              ByteString continuationPoint) {
        this.nodeId = nodeId;
        this.indexRange = indexRange;
        this.dataEncoding = dataEncoding;
        this.continuationPoint = continuationPoint;
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

    public NodeId getNodeId() {
        return nodeId;
    }

    public String getIndexRange() {
        return indexRange;
    }

    public QualifiedName getDataEncoding() {
        return dataEncoding;
    }

    public ByteString getContinuationPoint() {
        return continuationPoint;
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryReadValueId> {
        @Override
        public Class<HistoryReadValueId> getType() {
            return HistoryReadValueId.class;
        }

        @Override
        public HistoryReadValueId decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            String indexRange = decoder.readString("IndexRange");
            QualifiedName dataEncoding = decoder.readQualifiedName("DataEncoding");
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");
            return new HistoryReadValueId(nodeId, indexRange, dataEncoding, continuationPoint);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, HistoryReadValueId value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeString("IndexRange", value.getIndexRange());
            encoder.writeQualifiedName("DataEncoding", value.getDataEncoding());
            encoder.writeByteString("ContinuationPoint", value.getContinuationPoint());
        }
    }
}
