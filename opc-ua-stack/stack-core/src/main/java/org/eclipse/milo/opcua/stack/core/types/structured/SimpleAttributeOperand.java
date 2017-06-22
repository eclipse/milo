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

import javax.annotation.Nullable;

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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("SimpleAttributeOperand")
public class SimpleAttributeOperand extends FilterOperand {

    public static final NodeId TypeId = Identifiers.SimpleAttributeOperand;
    public static final NodeId BinaryEncodingId = Identifiers.SimpleAttributeOperand_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SimpleAttributeOperand_Encoding_DefaultXml;

    protected final NodeId _typeDefinitionId;
    protected final QualifiedName[] _browsePath;
    protected final UInteger _attributeId;
    protected final String _indexRange;

    public SimpleAttributeOperand() {
        super();
        this._typeDefinitionId = null;
        this._browsePath = null;
        this._attributeId = null;
        this._indexRange = null;
    }

    public SimpleAttributeOperand(NodeId _typeDefinitionId, QualifiedName[] _browsePath, UInteger _attributeId, String _indexRange) {
        super();
        this._typeDefinitionId = _typeDefinitionId;
        this._browsePath = _browsePath;
        this._attributeId = _attributeId;
        this._indexRange = _indexRange;
    }

    public NodeId getTypeDefinitionId() { return _typeDefinitionId; }

    @Nullable
    public QualifiedName[] getBrowsePath() { return _browsePath; }

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
            .add("TypeDefinitionId", _typeDefinitionId)
            .add("BrowsePath", _browsePath)
            .add("AttributeId", _attributeId)
            .add("IndexRange", _indexRange)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SimpleAttributeOperand> {
        @Override
        public SimpleAttributeOperand decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _typeDefinitionId = reader.readNodeId();
            QualifiedName[] _browsePath = reader.readArray(reader::readQualifiedName, QualifiedName.class);
            UInteger _attributeId = reader.readUInt32();
            String _indexRange = reader.readString();

            return new SimpleAttributeOperand(_typeDefinitionId, _browsePath, _attributeId, _indexRange);
        }

        @Override
        public void encode(SerializationContext context, SimpleAttributeOperand value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._typeDefinitionId);
            writer.writeArray(value._browsePath, writer::writeQualifiedName);
            writer.writeUInt32(value._attributeId);
            writer.writeString(value._indexRange);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SimpleAttributeOperand> {
        @Override
        public SimpleAttributeOperand decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _typeDefinitionId = reader.readNodeId("TypeDefinitionId");
            QualifiedName[] _browsePath = reader.readArray("BrowsePath", reader::readQualifiedName, QualifiedName.class);
            UInteger _attributeId = reader.readUInt32("AttributeId");
            String _indexRange = reader.readString("IndexRange");

            return new SimpleAttributeOperand(_typeDefinitionId, _browsePath, _attributeId, _indexRange);
        }

        @Override
        public void encode(SerializationContext context, SimpleAttributeOperand encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("TypeDefinitionId", encodable._typeDefinitionId);
            writer.writeArray("BrowsePath", encodable._browsePath, writer::writeQualifiedName);
            writer.writeUInt32("AttributeId", encodable._attributeId);
            writer.writeString("IndexRange", encodable._indexRange);
        }
    }

}
