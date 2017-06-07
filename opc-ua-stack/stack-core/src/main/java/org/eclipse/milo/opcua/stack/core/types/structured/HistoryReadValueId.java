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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;

@UaDataType("HistoryReadValueId")
public class HistoryReadValueId implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryReadValueId;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadValueId_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadValueId_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final String _indexRange;
    protected final QualifiedName _dataEncoding;
    protected final ByteString _continuationPoint;

    public HistoryReadValueId() {
        this._nodeId = null;
        this._indexRange = null;
        this._dataEncoding = null;
        this._continuationPoint = null;
    }

    public HistoryReadValueId(NodeId _nodeId, String _indexRange, QualifiedName _dataEncoding, ByteString _continuationPoint) {
        this._nodeId = _nodeId;
        this._indexRange = _indexRange;
        this._dataEncoding = _dataEncoding;
        this._continuationPoint = _continuationPoint;
    }

    public NodeId getNodeId() { return _nodeId; }

    public String getIndexRange() { return _indexRange; }

    public QualifiedName getDataEncoding() { return _dataEncoding; }

    public ByteString getContinuationPoint() { return _continuationPoint; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", _nodeId)
            .add("IndexRange", _indexRange)
            .add("DataEncoding", _dataEncoding)
            .add("ContinuationPoint", _continuationPoint)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryReadValueId> {
        @Override
        public HistoryReadValueId decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            String _indexRange = reader.readString();
            QualifiedName _dataEncoding = reader.readQualifiedName();
            ByteString _continuationPoint = reader.readByteString();

            return new HistoryReadValueId(_nodeId, _indexRange, _dataEncoding, _continuationPoint);
        }

        @Override
        public void encode(SerializationContext context, HistoryReadValueId value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._nodeId);
            writer.writeString(value._indexRange);
            writer.writeQualifiedName(value._dataEncoding);
            writer.writeByteString(value._continuationPoint);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryReadValueId> {
        @Override
        public HistoryReadValueId decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            String _indexRange = reader.readString("IndexRange");
            QualifiedName _dataEncoding = reader.readQualifiedName("DataEncoding");
            ByteString _continuationPoint = reader.readByteString("ContinuationPoint");

            return new HistoryReadValueId(_nodeId, _indexRange, _dataEncoding, _continuationPoint);
        }

        @Override
        public void encode(SerializationContext context, HistoryReadValueId encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeString("IndexRange", encodable._indexRange);
            writer.writeQualifiedName("DataEncoding", encodable._dataEncoding);
            writer.writeByteString("ContinuationPoint", encodable._continuationPoint);
        }
    }

}
