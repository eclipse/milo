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

@UaDataType("MethodAttributes")
public class MethodAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.MethodAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.MethodAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MethodAttributes_Encoding_DefaultXml;

    protected final Boolean _executable;
    protected final Boolean _userExecutable;

    public MethodAttributes() {
        super(null, null, null, null, null);
        this._executable = null;
        this._userExecutable = null;
    }

    public MethodAttributes(UInteger _specifiedAttributes, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, Boolean _executable, Boolean _userExecutable) {
        super(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);
        this._executable = _executable;
        this._userExecutable = _userExecutable;
    }

    public Boolean getExecutable() { return _executable; }

    public Boolean getUserExecutable() { return _userExecutable; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SpecifiedAttributes", _specifiedAttributes)
            .add("DisplayName", _displayName)
            .add("Description", _description)
            .add("WriteMask", _writeMask)
            .add("UserWriteMask", _userWriteMask)
            .add("Executable", _executable)
            .add("UserExecutable", _userExecutable)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<MethodAttributes> {
        @Override
        public MethodAttributes decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _specifiedAttributes = reader.readUInt32();
            LocalizedText _displayName = reader.readLocalizedText();
            LocalizedText _description = reader.readLocalizedText();
            UInteger _writeMask = reader.readUInt32();
            UInteger _userWriteMask = reader.readUInt32();
            Boolean _executable = reader.readBoolean();
            Boolean _userExecutable = reader.readBoolean();

            return new MethodAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask, _executable, _userExecutable);
        }

        @Override
        public void encode(SerializationContext context, MethodAttributes encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._specifiedAttributes);
            writer.writeLocalizedText(encodable._displayName);
            writer.writeLocalizedText(encodable._description);
            writer.writeUInt32(encodable._writeMask);
            writer.writeUInt32(encodable._userWriteMask);
            writer.writeBoolean(encodable._executable);
            writer.writeBoolean(encodable._userExecutable);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<MethodAttributes> {
        @Override
        public MethodAttributes decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _specifiedAttributes = reader.readUInt32("SpecifiedAttributes");
            LocalizedText _displayName = reader.readLocalizedText("DisplayName");
            LocalizedText _description = reader.readLocalizedText("Description");
            UInteger _writeMask = reader.readUInt32("WriteMask");
            UInteger _userWriteMask = reader.readUInt32("UserWriteMask");
            Boolean _executable = reader.readBoolean("Executable");
            Boolean _userExecutable = reader.readBoolean("UserExecutable");

            return new MethodAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask, _executable, _userExecutable);
        }

        @Override
        public void encode(SerializationContext context, MethodAttributes encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("SpecifiedAttributes", encodable._specifiedAttributes);
            writer.writeLocalizedText("DisplayName", encodable._displayName);
            writer.writeLocalizedText("Description", encodable._description);
            writer.writeUInt32("WriteMask", encodable._writeMask);
            writer.writeUInt32("UserWriteMask", encodable._userWriteMask);
            writer.writeBoolean("Executable", encodable._executable);
            writer.writeBoolean("UserExecutable", encodable._userExecutable);
        }
    }

}
