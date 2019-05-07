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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ReferenceTypeAttributes extends NodeAttributes implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=367");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=369");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=368");

    private final Boolean isAbstract;

    private final Boolean symmetric;

    private final LocalizedText inverseName;

    public ReferenceTypeAttributes(UInteger specifiedAttributes, LocalizedText displayName,
                                   LocalizedText description, UInteger writeMask, UInteger userWriteMask, Boolean isAbstract,
                                   Boolean symmetric, LocalizedText inverseName) {
        super(specifiedAttributes, displayName, description, writeMask, userWriteMask);
        this.isAbstract = isAbstract;
        this.symmetric = symmetric;
        this.inverseName = inverseName;
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

    public Boolean getIsAbstract() {
        return isAbstract;
    }

    public Boolean getSymmetric() {
        return symmetric;
    }

    public LocalizedText getInverseName() {
        return inverseName;
    }

    public static final class Codec extends GenericDataTypeCodec<ReferenceTypeAttributes> {
        @Override
        public Class<ReferenceTypeAttributes> getType() {
            return ReferenceTypeAttributes.class;
        }

        @Override
        public ReferenceTypeAttributes decode(SerializationContext context, UaDecoder decoder) {
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
        public void encode(SerializationContext context, UaEncoder encoder,
                           ReferenceTypeAttributes value) {
            encoder.writeUInt32("SpecifiedAttributes", value.getSpecifiedAttributes());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeUInt32("WriteMask", value.getWriteMask());
            encoder.writeUInt32("UserWriteMask", value.getUserWriteMask());
            encoder.writeBoolean("IsAbstract", value.getIsAbstract());
            encoder.writeBoolean("Symmetric", value.getSymmetric());
            encoder.writeLocalizedText("InverseName", value.getInverseName());
        }
    }
}
