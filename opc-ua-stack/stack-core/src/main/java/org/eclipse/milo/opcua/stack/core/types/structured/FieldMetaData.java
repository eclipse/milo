/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.UUID;

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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class FieldMetaData extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14524");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14839");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14795");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15051");

    private final String name;

    private final LocalizedText description;

    private final DataSetFieldFlags fieldFlags;

    private final UByte builtInType;

    private final NodeId dataType;

    private final Integer valueRank;

    private final UInteger[] arrayDimensions;

    private final UInteger maxStringLength;

    private final UUID dataSetFieldId;

    private final KeyValuePair[] properties;

    public FieldMetaData(String name, LocalizedText description, DataSetFieldFlags fieldFlags,
                         UByte builtInType, NodeId dataType, Integer valueRank, UInteger[] arrayDimensions,
                         UInteger maxStringLength, UUID dataSetFieldId, KeyValuePair[] properties) {
        this.name = name;
        this.description = description;
        this.fieldFlags = fieldFlags;
        this.builtInType = builtInType;
        this.dataType = dataType;
        this.valueRank = valueRank;
        this.arrayDimensions = arrayDimensions;
        this.maxStringLength = maxStringLength;
        this.dataSetFieldId = dataSetFieldId;
        this.properties = properties;
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

    public String getName() {
        return name;
    }

    public LocalizedText getDescription() {
        return description;
    }

    public DataSetFieldFlags getFieldFlags() {
        return fieldFlags;
    }

    public UByte getBuiltInType() {
        return builtInType;
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

    public UUID getDataSetFieldId() {
        return dataSetFieldId;
    }

    public KeyValuePair[] getProperties() {
        return properties;
    }

    public static final class Codec extends GenericDataTypeCodec<FieldMetaData> {
        @Override
        public Class<FieldMetaData> getType() {
            return FieldMetaData.class;
        }

        @Override
        public FieldMetaData decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            LocalizedText description = decoder.readLocalizedText("Description");
            DataSetFieldFlags fieldFlags = new DataSetFieldFlags(decoder.readUInt16("FieldFlags"));
            UByte builtInType = decoder.readByte("BuiltInType");
            NodeId dataType = decoder.readNodeId("DataType");
            Integer valueRank = decoder.readInt32("ValueRank");
            UInteger[] arrayDimensions = decoder.readUInt32Array("ArrayDimensions");
            UInteger maxStringLength = decoder.readUInt32("MaxStringLength");
            UUID dataSetFieldId = decoder.readGuid("DataSetFieldId");
            KeyValuePair[] properties = (KeyValuePair[]) decoder.readStructArray("Properties", KeyValuePair.TYPE_ID);
            return new FieldMetaData(name, description, fieldFlags, builtInType, dataType, valueRank, arrayDimensions, maxStringLength, dataSetFieldId, properties);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, FieldMetaData value) {
            encoder.writeString("Name", value.getName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeUInt16("FieldFlags", value.getFieldFlags().getValue());
            encoder.writeByte("BuiltInType", value.getBuiltInType());
            encoder.writeNodeId("DataType", value.getDataType());
            encoder.writeInt32("ValueRank", value.getValueRank());
            encoder.writeUInt32Array("ArrayDimensions", value.getArrayDimensions());
            encoder.writeUInt32("MaxStringLength", value.getMaxStringLength());
            encoder.writeGuid("DataSetFieldId", value.getDataSetFieldId());
            encoder.writeStructArray("Properties", value.getProperties(), KeyValuePair.TYPE_ID);
        }
    }
}
