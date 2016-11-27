/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ReadValueId")
public class ReadValueId implements UaStructure {

    public static final NodeId TypeId = Identifiers.ReadValueId;
    public static final NodeId BinaryEncodingId = Identifiers.ReadValueId_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadValueId_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final UInteger _attributeId;
    protected final String _indexRange;
    protected final QualifiedName _dataEncoding;

    public ReadValueId() {
        this._nodeId = null;
        this._attributeId = null;
        this._indexRange = null;
        this._dataEncoding = null;
    }

    public ReadValueId(NodeId _nodeId, UInteger _attributeId, String _indexRange, QualifiedName _dataEncoding) {
        this._nodeId = _nodeId;
        this._attributeId = _attributeId;
        this._indexRange = _indexRange;
        this._dataEncoding = _dataEncoding;
    }

    public NodeId getNodeId() { return _nodeId; }

    public UInteger getAttributeId() { return _attributeId; }

    public String getIndexRange() { return _indexRange; }

    public QualifiedName getDataEncoding() { return _dataEncoding; }

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
            .add("AttributeId", _attributeId)
            .add("IndexRange", _indexRange)
            .add("DataEncoding", _dataEncoding)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReadValueId> {
        @Override
        public ReadValueId decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            UInteger _attributeId = reader.readUInt32();
            String _indexRange = reader.readString();
            QualifiedName _dataEncoding = reader.readQualifiedName();

            return new ReadValueId(_nodeId, _attributeId, _indexRange, _dataEncoding);
        }

        @Override
        public void encode(SerializationContext context, ReadValueId encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._nodeId);
            writer.writeUInt32(encodable._attributeId);
            writer.writeString(encodable._indexRange);
            writer.writeQualifiedName(encodable._dataEncoding);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReadValueId> {
        @Override
        public ReadValueId decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            UInteger _attributeId = reader.readUInt32("AttributeId");
            String _indexRange = reader.readString("IndexRange");
            QualifiedName _dataEncoding = reader.readQualifiedName("DataEncoding");

            return new ReadValueId(_nodeId, _attributeId, _indexRange, _dataEncoding);
        }

        @Override
        public void encode(SerializationContext context, ReadValueId encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeUInt32("AttributeId", encodable._attributeId);
            writer.writeString("IndexRange", encodable._indexRange);
            writer.writeQualifiedName("DataEncoding", encodable._dataEncoding);
        }
    }

}
