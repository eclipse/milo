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

import javax.annotation.Nullable;

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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("Argument")
public class Argument implements UaStructure {

    public static final NodeId TypeId = Identifiers.Argument;
    public static final NodeId BinaryEncodingId = Identifiers.Argument_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.Argument_Encoding_DefaultXml;

    protected final String _name;
    protected final NodeId _dataType;
    protected final Integer _valueRank;
    protected final UInteger[] _arrayDimensions;
    protected final LocalizedText _description;

    public Argument() {
        this._name = null;
        this._dataType = null;
        this._valueRank = null;
        this._arrayDimensions = null;
        this._description = null;
    }

    public Argument(String _name, NodeId _dataType, Integer _valueRank, UInteger[] _arrayDimensions, LocalizedText _description) {
        this._name = _name;
        this._dataType = _dataType;
        this._valueRank = _valueRank;
        this._arrayDimensions = _arrayDimensions;
        this._description = _description;
    }

    public String getName() { return _name; }

    public NodeId getDataType() { return _dataType; }

    public Integer getValueRank() { return _valueRank; }

    @Nullable
    public UInteger[] getArrayDimensions() { return _arrayDimensions; }

    public LocalizedText getDescription() { return _description; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Name", _name)
            .add("DataType", _dataType)
            .add("ValueRank", _valueRank)
            .add("ArrayDimensions", _arrayDimensions)
            .add("Description", _description)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<Argument> {
        @Override
        public Argument decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _name = reader.readString();
            NodeId _dataType = reader.readNodeId();
            Integer _valueRank = reader.readInt32();
            UInteger[] _arrayDimensions = reader.readArray(reader::readUInt32, UInteger.class);
            LocalizedText _description = reader.readLocalizedText();

            return new Argument(_name, _dataType, _valueRank, _arrayDimensions, _description);
        }

        @Override
        public void encode(SerializationContext context, Argument encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._name);
            writer.writeNodeId(encodable._dataType);
            writer.writeInt32(encodable._valueRank);
            writer.writeArray(encodable._arrayDimensions, writer::writeUInt32);
            writer.writeLocalizedText(encodable._description);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<Argument> {
        @Override
        public Argument decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _name = reader.readString("Name");
            NodeId _dataType = reader.readNodeId("DataType");
            Integer _valueRank = reader.readInt32("ValueRank");
            UInteger[] _arrayDimensions = reader.readArray("ArrayDimensions", reader::readUInt32, UInteger.class);
            LocalizedText _description = reader.readLocalizedText("Description");

            return new Argument(_name, _dataType, _valueRank, _arrayDimensions, _description);
        }

        @Override
        public void encode(SerializationContext context, Argument encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("Name", encodable._name);
            writer.writeNodeId("DataType", encodable._dataType);
            writer.writeInt32("ValueRank", encodable._valueRank);
            writer.writeArray("ArrayDimensions", encodable._arrayDimensions, writer::writeUInt32);
            writer.writeLocalizedText("Description", encodable._description);
        }
    }

}
