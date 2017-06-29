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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

public class HistoryReadValueId implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryReadValueId;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadValueId_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadValueId_Encoding_DefaultXml;

    protected final NodeId nodeId;
    protected final String indexRange;
    protected final QualifiedName dataEncoding;
    protected final ByteString continuationPoint;

    public HistoryReadValueId() {
        this.nodeId = null;
        this.indexRange = null;
        this.dataEncoding = null;
        this.continuationPoint = null;
    }

    public HistoryReadValueId(NodeId nodeId, String indexRange, QualifiedName dataEncoding, ByteString continuationPoint) {
        this.nodeId = nodeId;
        this.indexRange = indexRange;
        this.dataEncoding = dataEncoding;
        this.continuationPoint = continuationPoint;
    }

    public NodeId getNodeId() { return nodeId; }

    public String getIndexRange() { return indexRange; }

    public QualifiedName getDataEncoding() { return dataEncoding; }

    public ByteString getContinuationPoint() { return continuationPoint; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", nodeId)
            .add("IndexRange", indexRange)
            .add("DataEncoding", dataEncoding)
            .add("ContinuationPoint", continuationPoint)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<HistoryReadValueId> {

        @Override
        public Class<HistoryReadValueId> getType() {
            return HistoryReadValueId.class;
        }

        @Override
        public HistoryReadValueId decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            String indexRange = decoder.readString("IndexRange");
            QualifiedName dataEncoding = decoder.readQualifiedName("DataEncoding");
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");

            return new HistoryReadValueId(nodeId, indexRange, dataEncoding, continuationPoint);
        }

        @Override
        public void encode(HistoryReadValueId value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeString("IndexRange", value.indexRange);
            encoder.writeQualifiedName("DataEncoding", value.dataEncoding);
            encoder.writeByteString("ContinuationPoint", value.continuationPoint);
        }
    }

}
