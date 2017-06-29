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
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ObjectTypeAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.ObjectTypeAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.ObjectTypeAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ObjectTypeAttributes_Encoding_DefaultXml;

    protected final Boolean isAbstract;

    public ObjectTypeAttributes() {
        super(null, null, null, null, null);
        this.isAbstract = null;
    }

    public ObjectTypeAttributes(UInteger specifiedAttributes, LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask, Boolean isAbstract) {
        super(specifiedAttributes, displayName, description, writeMask, userWriteMask);
        this.isAbstract = isAbstract;
    }

    public Boolean getIsAbstract() { return isAbstract; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SpecifiedAttributes", specifiedAttributes)
            .add("DisplayName", displayName)
            .add("Description", description)
            .add("WriteMask", writeMask)
            .add("UserWriteMask", userWriteMask)
            .add("IsAbstract", isAbstract)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ObjectTypeAttributes> {

        @Override
        public Class<ObjectTypeAttributes> getType() {
            return ObjectTypeAttributes.class;
        }

        @Override
        public ObjectTypeAttributes decode(UaDecoder decoder) throws UaSerializationException {
            UInteger specifiedAttributes = decoder.readUInt32("SpecifiedAttributes");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            Boolean isAbstract = decoder.readBoolean("IsAbstract");

            return new ObjectTypeAttributes(specifiedAttributes, displayName, description, writeMask, userWriteMask, isAbstract);
        }

        @Override
        public void encode(ObjectTypeAttributes value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("SpecifiedAttributes", value.specifiedAttributes);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeLocalizedText("Description", value.description);
            encoder.writeUInt32("WriteMask", value.writeMask);
            encoder.writeUInt32("UserWriteMask", value.userWriteMask);
            encoder.writeBoolean("IsAbstract", value.isAbstract);
        }
    }

}
