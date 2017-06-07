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
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("AttributeOperand")
public class AttributeOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.AttributeOperand;
    public static final NodeId BinaryEncodingId = Identifiers.AttributeOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AttributeOperand_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final String _alias;
    protected final RelativePath _browsePath;
    protected final UInteger _attributeId;
    protected final String _indexRange;

    public AttributeOperand() {
        super();
        this._nodeId = null;
        this._alias = null;
        this._browsePath = null;
        this._attributeId = null;
        this._indexRange = null;
    }

    public AttributeOperand(NodeId _nodeId, String _alias, RelativePath _browsePath, UInteger _attributeId, String _indexRange) {
        super();
        this._nodeId = _nodeId;
        this._alias = _alias;
        this._browsePath = _browsePath;
        this._attributeId = _attributeId;
        this._indexRange = _indexRange;
    }

    public NodeId getNodeId() { return _nodeId; }

    public String getAlias() { return _alias; }

    public RelativePath getBrowsePath() { return _browsePath; }

    public UInteger getAttributeId() { return _attributeId; }

    public String getIndexRange() { return _indexRange; }

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
            .add("Alias", _alias)
            .add("BrowsePath", _browsePath)
            .add("AttributeId", _attributeId)
            .add("IndexRange", _indexRange)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<AttributeOperand> {
        @Override
        public AttributeOperand decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            String _alias = reader.readString();
            RelativePath _browsePath = (RelativePath) context.decode(RelativePath.BinaryEncodingId, reader);
            UInteger _attributeId = reader.readUInt32();
            String _indexRange = reader.readString();

            return new AttributeOperand(_nodeId, _alias, _browsePath, _attributeId, _indexRange);
        }

        @Override
        public void encode(SerializationContext context, AttributeOperand value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._nodeId);
            writer.writeString(value._alias);
            context.encode(RelativePath.BinaryEncodingId, value._browsePath, writer);
            writer.writeUInt32(value._attributeId);
            writer.writeString(value._indexRange);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<AttributeOperand> {
        @Override
        public AttributeOperand decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            String _alias = reader.readString("Alias");
            RelativePath _browsePath = (RelativePath) context.decode(RelativePath.XmlEncodingId, reader);
            UInteger _attributeId = reader.readUInt32("AttributeId");
            String _indexRange = reader.readString("IndexRange");

            return new AttributeOperand(_nodeId, _alias, _browsePath, _attributeId, _indexRange);
        }

        @Override
        public void encode(SerializationContext context, AttributeOperand encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeString("Alias", encodable._alias);
            context.encode(RelativePath.XmlEncodingId, encodable._browsePath, writer);
            writer.writeUInt32("AttributeId", encodable._attributeId);
            writer.writeString("IndexRange", encodable._indexRange);
        }
    }

}
