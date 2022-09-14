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

import java.util.UUID;

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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.2.3">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.2.3</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class FieldMetaData extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=14524");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=14839");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=14795");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15051");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 14839),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Description", LocalizedText.NULL_VALUE, new NodeId(0, 21), -1, null, UInteger.valueOf(0), false),
                new StructureField("FieldFlags", LocalizedText.NULL_VALUE, new NodeId(0, 15904), -1, null, UInteger.valueOf(0), false),
                new StructureField("BuiltInType", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataType", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("ValueRank", LocalizedText.NULL_VALUE, new NodeId(0, 6), -1, null, UInteger.valueOf(0), false),
                new StructureField("ArrayDimensions", LocalizedText.NULL_VALUE, new NodeId(0, 7), 1, null, UInteger.valueOf(0), false),
                new StructureField("MaxStringLength", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetFieldId", LocalizedText.NULL_VALUE, new NodeId(0, 14), -1, null, UInteger.valueOf(0), false),
                new StructureField("Properties", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<FieldMetaData> {
        @Override
        public Class<FieldMetaData> getType() {
            return FieldMetaData.class;
        }

        @Override
        public FieldMetaData decodeType(SerializationContext context, UaDecoder decoder) {
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
        public void encodeType(SerializationContext context, UaEncoder encoder, FieldMetaData value) {
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
