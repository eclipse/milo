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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("EnumValueType")
public class EnumValueType implements UaStructure {

    public static final NodeId TypeId = Identifiers.EnumValueType;
    public static final NodeId BinaryEncodingId = Identifiers.EnumValueType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EnumValueType_Encoding_DefaultXml;

    protected final Long _value;
    protected final LocalizedText _displayName;
    protected final LocalizedText _description;

    public EnumValueType() {
        this._value = null;
        this._displayName = null;
        this._description = null;
    }

    public EnumValueType(Long _value, LocalizedText _displayName, LocalizedText _description) {
        this._value = _value;
        this._displayName = _displayName;
        this._description = _description;
    }

    public Long getValue() { return _value; }

    public LocalizedText getDisplayName() { return _displayName; }

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
            .add("Value", _value)
            .add("DisplayName", _displayName)
            .add("Description", _description)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<EnumValueType> {
        @Override
        public EnumValueType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Long _value = reader.readInt64();
            LocalizedText _displayName = reader.readLocalizedText();
            LocalizedText _description = reader.readLocalizedText();

            return new EnumValueType(_value, _displayName, _description);
        }

        @Override
        public void encode(SerializationContext context, EnumValueType value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeInt64(value._value);
            writer.writeLocalizedText(value._displayName);
            writer.writeLocalizedText(value._description);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<EnumValueType> {
        @Override
        public EnumValueType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Long _value = reader.readInt64("Value");
            LocalizedText _displayName = reader.readLocalizedText("DisplayName");
            LocalizedText _description = reader.readLocalizedText("Description");

            return new EnumValueType(_value, _displayName, _description);
        }

        @Override
        public void encode(SerializationContext context, EnumValueType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeInt64("Value", encodable._value);
            writer.writeLocalizedText("DisplayName", encodable._displayName);
            writer.writeLocalizedText("Description", encodable._description);
        }
    }

}
