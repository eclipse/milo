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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class NodeAttributes implements UaStructure {

    public static final NodeId TypeId = Identifiers.NodeAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.NodeAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NodeAttributes_Encoding_DefaultXml;

    protected final UInteger specifiedAttributes;
    protected final LocalizedText displayName;
    protected final LocalizedText description;
    protected final UInteger writeMask;
    protected final UInteger userWriteMask;

    public NodeAttributes() {
        this.specifiedAttributes = null;
        this.displayName = null;
        this.description = null;
        this.writeMask = null;
        this.userWriteMask = null;
    }

    public NodeAttributes(UInteger specifiedAttributes, LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask) {
        this.specifiedAttributes = specifiedAttributes;
        this.displayName = displayName;
        this.description = description;
        this.writeMask = writeMask;
        this.userWriteMask = userWriteMask;
    }

    public UInteger getSpecifiedAttributes() { return specifiedAttributes; }

    public LocalizedText getDisplayName() { return displayName; }

    public LocalizedText getDescription() { return description; }

    public UInteger getWriteMask() { return writeMask; }

    public UInteger getUserWriteMask() { return userWriteMask; }

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
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<NodeAttributes> {

        @Override
        public Class<NodeAttributes> getType() {
            return NodeAttributes.class;
        }

        @Override
        public NodeAttributes decode(UaDecoder decoder) throws UaSerializationException {
            UInteger specifiedAttributes = decoder.readUInt32("SpecifiedAttributes");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");

            return new NodeAttributes(specifiedAttributes, displayName, description, writeMask, userWriteMask);
        }

        @Override
        public void encode(NodeAttributes value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("SpecifiedAttributes", value.specifiedAttributes);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeLocalizedText("Description", value.description);
            encoder.writeUInt32("WriteMask", value.writeMask);
            encoder.writeUInt32("UserWriteMask", value.userWriteMask);
        }
    }

}
