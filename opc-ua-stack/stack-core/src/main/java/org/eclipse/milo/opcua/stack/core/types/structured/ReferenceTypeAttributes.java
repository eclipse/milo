/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.24.7">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.24.7</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class ReferenceTypeAttributes extends NodeAttributes implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=367");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=369");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=368");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15160");

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

    @Override
    public ExpandedNodeId getJsonEncodingId() {
        return JSON_ENCODING_ID;
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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 369),
            new NodeId(0, 349),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("SpecifiedAttributes", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("DisplayName", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false),
                new StructureField("Description", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false),
                new StructureField("WriteMask", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("UserWriteMask", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("IsAbstract", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("Symmetric", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("InverseName", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ReferenceTypeAttributes> {
        @Override
        public Class<ReferenceTypeAttributes> getType() {
            return ReferenceTypeAttributes.class;
        }

        @Override
        public ReferenceTypeAttributes decodeType(SerializationContext context, UaDecoder decoder) {
            UInteger specifiedAttributes = decoder.decodeUInt32("SpecifiedAttributes");
            LocalizedText displayName = decoder.decodeLocalizedText("DisplayName");
            LocalizedText description = decoder.decodeLocalizedText("Description");
            UInteger writeMask = decoder.decodeUInt32("WriteMask");
            UInteger userWriteMask = decoder.decodeUInt32("UserWriteMask");
            Boolean isAbstract = decoder.decodeBoolean("IsAbstract");
            Boolean symmetric = decoder.decodeBoolean("Symmetric");
            LocalizedText inverseName = decoder.decodeLocalizedText("InverseName");
            return new ReferenceTypeAttributes(specifiedAttributes, displayName, description, writeMask, userWriteMask, isAbstract, symmetric, inverseName);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ReferenceTypeAttributes value) {
            encoder.encodeUInt32("SpecifiedAttributes", value.getSpecifiedAttributes());
            encoder.encodeLocalizedText("DisplayName", value.getDisplayName());
            encoder.encodeLocalizedText("Description", value.getDescription());
            encoder.encodeUInt32("WriteMask", value.getWriteMask());
            encoder.encodeUInt32("UserWriteMask", value.getUserWriteMask());
            encoder.encodeBoolean("IsAbstract", value.getIsAbstract());
            encoder.encodeBoolean("Symmetric", value.getSymmetric());
            encoder.encodeLocalizedText("InverseName", value.getInverseName());
        }
    }
}
