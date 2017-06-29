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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class EUInformation implements UaStructure {

    public static final NodeId TypeId = Identifiers.EUInformation;
    public static final NodeId BinaryEncodingId = Identifiers.EUInformation_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EUInformation_Encoding_DefaultXml;

    protected final String namespaceUri;
    protected final Integer unitId;
    protected final LocalizedText displayName;
    protected final LocalizedText description;

    public EUInformation() {
        this.namespaceUri = null;
        this.unitId = null;
        this.displayName = null;
        this.description = null;
    }

    public EUInformation(String namespaceUri, Integer unitId, LocalizedText displayName, LocalizedText description) {
        this.namespaceUri = namespaceUri;
        this.unitId = unitId;
        this.displayName = displayName;
        this.description = description;
    }

    public String getNamespaceUri() { return namespaceUri; }

    public Integer getUnitId() { return unitId; }

    public LocalizedText getDisplayName() { return displayName; }

    public LocalizedText getDescription() { return description; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NamespaceUri", namespaceUri)
            .add("UnitId", unitId)
            .add("DisplayName", displayName)
            .add("Description", description)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<EUInformation> {

        @Override
        public Class<EUInformation> getType() {
            return EUInformation.class;
        }

        @Override
        public EUInformation decode(UaDecoder decoder) throws UaSerializationException {
            String namespaceUri = decoder.readString("NamespaceUri");
            Integer unitId = decoder.readInt32("UnitId");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");

            return new EUInformation(namespaceUri, unitId, displayName, description);
        }

        @Override
        public void encode(EUInformation value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("NamespaceUri", value.namespaceUri);
            encoder.writeInt32("UnitId", value.unitId);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeLocalizedText("Description", value.description);
        }
    }

}
