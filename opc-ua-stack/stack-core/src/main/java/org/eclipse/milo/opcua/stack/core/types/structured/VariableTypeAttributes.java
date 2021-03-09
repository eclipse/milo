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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class VariableTypeAttributes extends NodeAttributes implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=364");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=366");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=365");

    private final Variant value;

    private final NodeId dataType;

    private final Integer valueRank;

    private final UInteger[] arrayDimensions;

    private final Boolean isAbstract;

    public VariableTypeAttributes(UInteger specifiedAttributes, LocalizedText displayName,
                                  LocalizedText description, UInteger writeMask, UInteger userWriteMask, Variant value,
                                  NodeId dataType, Integer valueRank, UInteger[] arrayDimensions, Boolean isAbstract) {
        super(specifiedAttributes, displayName, description, writeMask, userWriteMask);
        this.value = value;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.isAbstract = isAbstract;
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

    public Variant getValue() {
        return value;
    }

    public NodeId getDataType() {
        return dataType;
    }

    public Integer getValueRank() {
        return valueRank;
    }

    public UInteger[] getArrayDimensions() {
        return arrayDimensions;
    }

    public Boolean getIsAbstract() {
        return isAbstract;
    }

    public static final class Codec extends GenericDataTypeCodec<VariableTypeAttributes> {
        @Override
        public Class<VariableTypeAttributes> getType() {
            return VariableTypeAttributes.class;
        }

        @Override
        public VariableTypeAttributes decode(SerializationContext context, UaDecoder decoder) {
            UInteger specifiedAttributes = decoder.readUInt32("SpecifiedAttributes");
            LocalizedText displayName = decoder.readLocalizedText("DisplayName");
            LocalizedText description = decoder.readLocalizedText("Description");
            UInteger writeMask = decoder.readUInt32("WriteMask");
            UInteger userWriteMask = decoder.readUInt32("UserWriteMask");
            Variant value = decoder.readVariant("Value");
            NodeId dataType = decoder.readNodeId("DataType");
            Integer valueRank = decoder.readInt32("ValueRank");
            UInteger[] arrayDimensions = decoder.readUInt32Array("ArrayDimensions");
            Boolean isAbstract = decoder.readBoolean("IsAbstract");
            return new VariableTypeAttributes(specifiedAttributes, displayName, description, writeMask, userWriteMask, value, dataType, valueRank, arrayDimensions, isAbstract);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           VariableTypeAttributes value) {
            encoder.writeUInt32("SpecifiedAttributes", value.getSpecifiedAttributes());
            encoder.writeLocalizedText("DisplayName", value.getDisplayName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeUInt32("WriteMask", value.getWriteMask());
            encoder.writeUInt32("UserWriteMask", value.getUserWriteMask());
            encoder.writeVariant("Value", value.getValue());
            encoder.writeNodeId("DataType", value.getDataType());
            encoder.writeInt32("ValueRank", value.getValueRank());
            encoder.writeUInt32Array("ArrayDimensions", value.getArrayDimensions());
            encoder.writeBoolean("IsAbstract", value.getIsAbstract());
        }
    }
}
