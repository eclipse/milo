/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ObjectAttributes extends NodeAttributes implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=352");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=354");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=353");

    private final UByte eventNotifier;

    public ObjectAttributes(UInteger specifiedAttributes, LocalizedText displayName,
                            LocalizedText description, UInteger writeMask, UInteger userWriteMask, UByte eventNotifier) {
        super(specifiedAttributes, displayName, description, writeMask, userWriteMask);
        this.eventNotifier = eventNotifier;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public UByte getEventNotifier() {
        return eventNotifier;
    }

    public static final class Codec extends GenericDataTypeCodec<ObjectAttributes> {
        @Override
        public Class<ObjectAttributes> getType() {
            return ObjectAttributes.class;
        }

        @Override
        public ObjectAttributes decode(SerializationContext context, UaDecoder decoder) {
            UInteger specifiedAttributes = decoder.readUInt32("SpecifiedAttributes");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            UByte eventNotifier = decoder.readByte("EventNotifier");
            return new ObjectAttributes(specifiedAttributes, displayName, description, writeMask, userWriteMask, eventNotifier);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ObjectAttributes value) {
            encoder.writeUInt32("SpecifiedAttributes", value.getSpecifiedAttributes());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeUInt32("WriteMask", value.getWriteMask());
            encoder.writeUInt32("UserWriteMask", value.getUserWriteMask());
            encoder.writeByte("EventNotifier", value.getEventNotifier());
        }
    }
}
