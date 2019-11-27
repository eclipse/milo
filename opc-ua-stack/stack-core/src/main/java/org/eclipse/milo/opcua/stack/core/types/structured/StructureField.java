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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class StructureField extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=101");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14844");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14800");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15065");

    private final String name;

    private final LocalizedText description;

    private final NodeId dataType;

    private final Integer valueRank;

    private final UInteger[] arrayDimensions;

    private final UInteger maxStringLength;

    private final Boolean isOptional;

    public StructureField(String name, LocalizedText description, NodeId dataType, Integer valueRank,
                          UInteger[] arrayDimensions, UInteger maxStringLength, Boolean isOptional) {
        this.name = name;
        this.description = description;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.maxStringLength = maxStringLength;
        this.isOptional = isOptional;
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

    public LocalizedText getDescription() {
        return description;
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

    public UInteger getMaxStringLength() {
        return maxStringLength;
    }

    public Boolean getIsOptional() {
        return isOptional;
    }

    public static final class Codec extends GenericDataTypeCodec<StructureField> {
        @Override
        public Class<StructureField> getType() {
            return StructureField.class;
        }

        @Override
        public StructureField decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            LocalizedText description = decoder.readLocalizedText("Description");
            NodeId dataType = decoder.readNodeId("DataType");
            Integer valueRank = decoder.readInt32("ValueRank");
            UInteger[] arrayDimensions = decoder.readUInt32Array("ArrayDimensions");
            UInteger maxStringLength = decoder.readUInt32("MaxStringLength");
            Boolean isOptional = decoder.readBoolean("IsOptional");
            return new StructureField(name, description, dataType, valueRank, arrayDimensions, maxStringLength, isOptional);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, StructureField value) {
            encoder.writeString("Name", value.getName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeNodeId("DataType", value.getDataType());
            encoder.writeInt32("ValueRank", value.getValueRank());
            encoder.writeUInt32Array("ArrayDimensions", value.getArrayDimensions());
            encoder.writeUInt32("MaxStringLength", value.getMaxStringLength());
            encoder.writeBoolean("IsOptional", value.getIsOptional());
        }
    }
}
