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

@UaDataType("ReferenceTypeAttributes")
public class ReferenceTypeAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.ReferenceTypeAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.ReferenceTypeAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReferenceTypeAttributes_Encoding_DefaultXml;

    protected final Boolean _isAbstract;
    protected final Boolean _symmetric;
    protected final LocalizedText _inverseName;

    public ReferenceTypeAttributes() {
        super(null, null, null, null, null);
        this._isAbstract = null;
        this._symmetric = null;
        this._inverseName = null;
    }

    public ReferenceTypeAttributes(UInteger _specifiedAttributes, LocalizedText _displayName, LocalizedText _description, UInteger _writeMask, UInteger _userWriteMask, Boolean _isAbstract, Boolean _symmetric, LocalizedText _inverseName) {
        super(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask);
        this._isAbstract = _isAbstract;
        this._symmetric = _symmetric;
        this._inverseName = _inverseName;
    }

    public Boolean getIsAbstract() { return _isAbstract; }

    public Boolean getSymmetric() { return _symmetric; }

    public LocalizedText getInverseName() { return _inverseName; }

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
            .add("IsAbstract", _isAbstract)
            .add("Symmetric", _symmetric)
            .add("InverseName", _inverseName)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReferenceTypeAttributes> {
        @Override
        public ReferenceTypeAttributes decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _specifiedAttributes = reader.readUInt32();
            LocalizedText _displayName = reader.readLocalizedText();
            LocalizedText _description = reader.readLocalizedText();
            UInteger _writeMask = reader.readUInt32();
            UInteger _userWriteMask = reader.readUInt32();
            Boolean _isAbstract = reader.readBoolean();
            Boolean _symmetric = reader.readBoolean();
            LocalizedText _inverseName = reader.readLocalizedText();

            return new ReferenceTypeAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask, _isAbstract, _symmetric, _inverseName);
        }

        @Override
        public void encode(SerializationContext context, ReferenceTypeAttributes encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._specifiedAttributes);
            writer.writeLocalizedText(encodable._displayName);
            writer.writeLocalizedText(encodable._description);
            writer.writeUInt32(encodable._writeMask);
            writer.writeUInt32(encodable._userWriteMask);
            writer.writeBoolean(encodable._isAbstract);
            writer.writeBoolean(encodable._symmetric);
            writer.writeLocalizedText(encodable._inverseName);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReferenceTypeAttributes> {
        @Override
        public ReferenceTypeAttributes decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _specifiedAttributes = reader.readUInt32("SpecifiedAttributes");
            LocalizedText _displayName = reader.readLocalizedText("DisplayName");
            LocalizedText _description = reader.readLocalizedText("Description");
            UInteger _writeMask = reader.readUInt32("WriteMask");
            UInteger _userWriteMask = reader.readUInt32("UserWriteMask");
            Boolean _isAbstract = reader.readBoolean("IsAbstract");
            Boolean _symmetric = reader.readBoolean("Symmetric");
            LocalizedText _inverseName = reader.readLocalizedText("InverseName");

            return new ReferenceTypeAttributes(_specifiedAttributes, _displayName, _description, _writeMask, _userWriteMask, _isAbstract, _symmetric, _inverseName);
        }

        @Override
        public void encode(SerializationContext context, ReferenceTypeAttributes encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("SpecifiedAttributes", encodable._specifiedAttributes);
            writer.writeLocalizedText("DisplayName", encodable._displayName);
            writer.writeLocalizedText("Description", encodable._description);
            writer.writeUInt32("WriteMask", encodable._writeMask);
            writer.writeUInt32("UserWriteMask", encodable._userWriteMask);
            writer.writeBoolean("IsAbstract", encodable._isAbstract);
            writer.writeBoolean("Symmetric", encodable._symmetric);
            writer.writeLocalizedText("InverseName", encodable._inverseName);
        }
    }

}
