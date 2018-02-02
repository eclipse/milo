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

public class EnumValueType implements UaStructure {

    public static final NodeId TypeId = Identifiers.EnumValueType;
    public static final NodeId BinaryEncodingId = Identifiers.EnumValueType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EnumValueType_Encoding_DefaultXml;

    protected final Long value;
    protected final LocalizedText displayName;
    protected final LocalizedText description;

    public EnumValueType() {
        this.value = null;
        this.displayName = null;
        this.description = null;
    }

    public EnumValueType(Long value, LocalizedText displayName, LocalizedText description) {
        this.value = value;
        this.displayName = displayName;
        this.description = description;
    }

    public Long getValue() { return value; }

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
            .add("Value", value)
            .add("DisplayName", displayName)
            .add("Description", description)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<EnumValueType> {

        @Override
        public Class<EnumValueType> getType() {
            return EnumValueType.class;
        }

        @Override
        public EnumValueType decode(UaDecoder decoder) throws UaSerializationException {
            Long value = decoder.readInt64("Value");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");

            return new EnumValueType(value, displayName, description);
        }

        @Override
        public void encode(EnumValueType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeInt64("Value", value.value);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeLocalizedText("Description", value.description);
        }
    }

}
