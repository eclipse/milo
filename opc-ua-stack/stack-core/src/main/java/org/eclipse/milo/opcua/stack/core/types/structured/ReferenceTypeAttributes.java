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

public class ReferenceTypeAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.ReferenceTypeAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.ReferenceTypeAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReferenceTypeAttributes_Encoding_DefaultXml;

    protected final Boolean isAbstract;
    protected final Boolean symmetric;
    protected final LocalizedText inverseName;

    public ReferenceTypeAttributes() {
        super(null, null, null, null, null);
        this.isAbstract = null;
        this.symmetric = null;
        this.inverseName = null;
    }

    public ReferenceTypeAttributes(UInteger specifiedAttributes, LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask, Boolean isAbstract, Boolean symmetric, LocalizedText inverseName) {
        super(specifiedAttributes, displayName, description, writeMask, userWriteMask);
        this.isAbstract = isAbstract;
        this.symmetric = symmetric;
        this.inverseName = inverseName;
    }

    public Boolean getIsAbstract() { return isAbstract; }

    public Boolean getSymmetric() { return symmetric; }

    public LocalizedText getInverseName() { return inverseName; }

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
            .add("Symmetric", symmetric)
            .add("InverseName", inverseName)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReferenceTypeAttributes> {

        @Override
        public Class<ReferenceTypeAttributes> getType() {
            return ReferenceTypeAttributes.class;
        }

        @Override
        public ReferenceTypeAttributes decode(UaDecoder decoder) throws UaSerializationException {
            UInteger specifiedAttributes = decoder.readUInt32("SpecifiedAttributes");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            Boolean isAbstract = decoder.readBoolean("IsAbstract");
            Boolean symmetric = decoder.readBoolean("Symmetric");
            LocalizedText inverseName = decoder.readLocalizedText("InverseName");

            return new ReferenceTypeAttributes(specifiedAttributes, displayName, description, writeMask, userWriteMask, isAbstract, symmetric, inverseName);
        }

        @Override
        public void encode(ReferenceTypeAttributes value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("SpecifiedAttributes", value.specifiedAttributes);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeLocalizedText("Description", value.description);
            encoder.writeUInt32("WriteMask", value.writeMask);
            encoder.writeUInt32("UserWriteMask", value.userWriteMask);
            encoder.writeBoolean("IsAbstract", value.isAbstract);
            encoder.writeBoolean("Symmetric", value.symmetric);
            encoder.writeLocalizedText("InverseName", value.inverseName);
        }
    }

}
