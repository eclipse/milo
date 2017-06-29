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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ViewAttributes extends NodeAttributes {

    public static final NodeId TypeId = Identifiers.ViewAttributes;
    public static final NodeId BinaryEncodingId = Identifiers.ViewAttributes_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ViewAttributes_Encoding_DefaultXml;

    protected final Boolean containsNoLoops;
    protected final UByte eventNotifier;

    public ViewAttributes() {
        super(null, null, null, null, null);
        this.containsNoLoops = null;
        this.eventNotifier = null;
    }

    public ViewAttributes(UInteger specifiedAttributes, LocalizedText displayName, LocalizedText description, UInteger writeMask, UInteger userWriteMask, Boolean containsNoLoops, UByte eventNotifier) {
        super(specifiedAttributes, displayName, description, writeMask, userWriteMask);
        this.containsNoLoops = containsNoLoops;
        this.eventNotifier = eventNotifier;
    }

    public Boolean getContainsNoLoops() { return containsNoLoops; }

    public UByte getEventNotifier() { return eventNotifier; }

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
            .add("ContainsNoLoops", containsNoLoops)
            .add("EventNotifier", eventNotifier)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ViewAttributes> {

        @Override
        public Class<ViewAttributes> getType() {
            return ViewAttributes.class;
        }

        @Override
        public ViewAttributes decode(UaDecoder decoder) throws UaSerializationException {
            UInteger specifiedAttributes = decoder.readUInt32("SpecifiedAttributes");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            Boolean containsNoLoops = decoder.readBoolean("ContainsNoLoops");
            UByte eventNotifier = decoder.readByte("EventNotifier");

            return new ViewAttributes(specifiedAttributes, displayName, description, writeMask, userWriteMask, containsNoLoops, eventNotifier);
        }

        @Override
        public void encode(ViewAttributes value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("SpecifiedAttributes", value.specifiedAttributes);
            encoder.writeLocalizedText("DisplayName", value.displayName);
            encoder.writeLocalizedText("Description", value.description);
            encoder.writeUInt32("WriteMask", value.writeMask);
            encoder.writeUInt32("UserWriteMask", value.userWriteMask);
            encoder.writeBoolean("ContainsNoLoops", value.containsNoLoops);
            encoder.writeByte("EventNotifier", value.eventNotifier);
        }
    }

}
