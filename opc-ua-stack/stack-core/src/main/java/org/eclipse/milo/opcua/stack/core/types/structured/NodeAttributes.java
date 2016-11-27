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
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("NodeAttributes")
public class NodeAttributes implements UaStructure {

    public static final NodeId TypeId = Identifiers.NodeAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.NodeAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NodeAttributes_Encoding_DefaultXml;

    protected final UInteger _specifiedAttributes;
    protected final LocalizedText _displayName;
    protected final LocalizedText _description;
    protected final UInteger _writeMask;
    protected final UInteger _userWriteMask;

    public NodeAttributes() {
        this._specifiedAttributes = null;
        this._displayName = null;
        this._description = null;
        this._writeMask = null;
        this._userWriteMask = null;
    }

    public NodeAttributes(UInteger _specifiedAttributes, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask) {
        this._specifiedAttributes = _specifiedAttributes;
        this._displayName = _displayName;
        this._description = _description;
        this._writeMask = _writeMask;
        this._userWriteMask = _userWriteMask;
    }

    public UInteger getSpecifiedAttributes() { return _specifiedAttributes; }

    public LocalizedText getDisplayName() { return _displayName; }

    public LocalizedText getDescription() { return _description; }

    public UInteger getWriteMask() { return _writeMask; }

    public UInteger getUserWriteMask() { return _userWriteMask; }

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
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<NodeAttributes> {
        @Override
        public NodeAttributes decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _specifiedAttributes = reader.readUInt32();
            LocalizedText _displayName = reader.readLocalizedText();
            LocalizedText _description = reader.readLocalizedText();
            UInteger _writeMask = reader.readUInt32();
            UInteger _userWriteMask = reader.readUInt32();

            return new NodeAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);
        }

        @Override
        public void encode(SerializationContext context, NodeAttributes encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._specifiedAttributes);
            writer.writeLocalizedText(encodable._displayName);
            writer.writeLocalizedText(encodable._description);
            writer.writeUInt32(encodable._writeMask);
            writer.writeUInt32(encodable._userWriteMask);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<NodeAttributes> {
        @Override
        public NodeAttributes decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _specifiedAttributes = reader.readUInt32("SpecifiedAttributes");
            LocalizedText _displayName = reader.readLocalizedText("DisplayName");
            LocalizedText _description = reader.readLocalizedText("Description");
            UInteger _writeMask = reader.readUInt32("WriteMask");
            UInteger _userWriteMask = reader.readUInt32("UserWriteMask");

            return new NodeAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);
        }

        @Override
        public void encode(SerializationContext context, NodeAttributes encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("SpecifiedAttributes", encodable._specifiedAttributes);
            writer.writeLocalizedText("DisplayName", encodable._displayName);
            writer.writeLocalizedText("Description", encodable._description);
            writer.writeUInt32("WriteMask", encodable._writeMask);
            writer.writeUInt32("UserWriteMask", encodable._userWriteMask);
        }
    }

}
