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

@UaDataType("EUInformation")
public class EUInformation implements UaStructure {

    public static final NodeId TypeId = Identifiers.EUInformation;
    public static final NodeId BinaryEncodingId = Identifiers.EUInformation_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EUInformation_Encoding_DefaultXml;

    protected final String _namespaceUri;
    protected final Integer _unitId;
    protected final LocalizedText _displayName;
    protected final LocalizedText _description;

    public EUInformation() {
        this._namespaceUri = null;
        this._unitId = null;
        this._displayName = null;
        this._description = null;
    }

    public EUInformation(String _namespaceUri, Integer _unitId, LocalizedText _displayName, LocalizedText _description) {
        this._namespaceUri = _namespaceUri;
        this._unitId = _unitId;
        this._displayName = _displayName;
        this._description = _description;
    }

    public String getNamespaceUri() { return _namespaceUri; }

    public Integer getUnitId() { return _unitId; }

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
            .add("NamespaceUri", _namespaceUri)
            .add("UnitId", _unitId)
            .add("DisplayName", _displayName)
            .add("Description", _description)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<EUInformation> {
        @Override
        public EUInformation decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _namespaceUri = reader.readString();
            Integer _unitId = reader.readInt32();
            LocalizedText _displayName = reader.readLocalizedText();
            LocalizedText _description = reader.readLocalizedText();

            return new EUInformation(_namespaceUri, _unitId, _displayName, _description);
        }

        @Override
        public void encode(SerializationContext context, EUInformation encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._namespaceUri);
            writer.writeInt32(encodable._unitId);
            writer.writeLocalizedText(encodable._displayName);
            writer.writeLocalizedText(encodable._description);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<EUInformation> {
        @Override
        public EUInformation decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _namespaceUri = reader.readString("NamespaceUri");
            Integer _unitId = reader.readInt32("UnitId");
            LocalizedText _displayName = reader.readLocalizedText("DisplayName");
            LocalizedText _description = reader.readLocalizedText("Description");

            return new EUInformation(_namespaceUri, _unitId, _displayName, _description);
        }

        @Override
        public void encode(SerializationContext context, EUInformation encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("NamespaceUri", encodable._namespaceUri);
            writer.writeInt32("UnitId", encodable._unitId);
            writer.writeLocalizedText("DisplayName", encodable._displayName);
            writer.writeLocalizedText("Description", encodable._description);
        }
    }

}
