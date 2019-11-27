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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EnumField extends EnumValueType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=102");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14845");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14801");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15083");

    private final String name;

    public EnumField(Long value, LocalizedText displayName, LocalizedText description, String name) {
        super(value, displayName, description);
        this.name = name;
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

    public String getName() {
        return name;
    }

    public static final class Codec extends GenericDataTypeCodec<EnumField> {
        @Override
        public Class<EnumField> getType() {
            return EnumField.class;
        }

        @Override
        public EnumField decode(SerializationContext context, UaDecoder decoder) {
            Long value = decoder.readInt64("Value");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            String name = decoder.readString("Name");
            return new EnumField(value, displayName, description, name);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EnumField value) {
            encoder.writeInt64("Value", value.getValue());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeString("Name", value.getName());
        }
    }
}
