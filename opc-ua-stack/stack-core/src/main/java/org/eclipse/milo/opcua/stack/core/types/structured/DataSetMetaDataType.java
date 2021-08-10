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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class DataSetMetaDataType extends DataTypeSchemaHeader implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14523");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=124");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14794");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15050");

    private final String name;

    private final LocalizedText description;

    private final FieldMetaData[] fields;

    private final UUID dataSetClassId;

    private final ConfigurationVersionDataType configurationVersion;

    public DataSetMetaDataType(String[] namespaces, StructureDescription[] structureDataTypes,
                               EnumDescription[] enumDataTypes, SimpleTypeDescription[] simpleDataTypes, String name,
                               LocalizedText description, FieldMetaData[] fields, UUID dataSetClassId,
                               ConfigurationVersionDataType configurationVersion) {
        super(namespaces, structureDataTypes, enumDataTypes, simpleDataTypes);
        this.name = name;
        this.description = description;
        this.fields = fields;
        this.dataSetClassId = dataSetClassId;
        this.configurationVersion = configurationVersion;
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

    public FieldMetaData[] getFields() {
        return fields;
    }

    public UUID getDataSetClassId() {
        return dataSetClassId;
    }

    public ConfigurationVersionDataType getConfigurationVersion() {
        return configurationVersion;
    }

    public static final class Codec extends GenericDataTypeCodec<DataSetMetaDataType> {
        @Override
        public Class<DataSetMetaDataType> getType() {
            return DataSetMetaDataType.class;
        }

        @Override
        public DataSetMetaDataType decode(SerializationContext context, UaDecoder decoder) {
            String[] namespaces = decoder.readStringArray("Namespaces");
            StructureDescription[] structureDataTypes = (StructureDescription[]) decoder.readStructArray("StructureDataTypes", StructureDescription.TYPE_ID);
            EnumDescription[] enumDataTypes = (EnumDescription[]) decoder.readStructArray("EnumDataTypes", EnumDescription.TYPE_ID);
            SimpleTypeDescription[] simpleDataTypes = (SimpleTypeDescription[]) decoder.readStructArray("SimpleDataTypes", SimpleTypeDescription.TYPE_ID);
            String name = decoder.readString("Name");
            LocalizedText description = decoder.readLocalizedText("Description");
            FieldMetaData[] fields = (FieldMetaData[]) decoder.readStructArray("Fields", FieldMetaData.TYPE_ID);
            UUID dataSetClassId = decoder.readGuid("DataSetClassId");
            ConfigurationVersionDataType configurationVersion = (ConfigurationVersionDataType) decoder.readStruct("ConfigurationVersion", ConfigurationVersionDataType.TYPE_ID);
            return new DataSetMetaDataType(namespaces, structureDataTypes, enumDataTypes, simpleDataTypes, name, description, fields, dataSetClassId, configurationVersion);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DataSetMetaDataType value) {
            encoder.writeStringArray("Namespaces", value.getNamespaces());
            encoder.writeStructArray("StructureDataTypes", value.getStructureDataTypes(), StructureDescription.TYPE_ID);
            encoder.writeStructArray("EnumDataTypes", value.getEnumDataTypes(), EnumDescription.TYPE_ID);
            encoder.writeStructArray("SimpleDataTypes", value.getSimpleDataTypes(), SimpleTypeDescription.TYPE_ID);
            encoder.writeString("Name", value.getName());
            encoder.writeLocalizedText("Description", value.getDescription());
            encoder.writeStructArray("Fields", value.getFields(), FieldMetaData.TYPE_ID);
            encoder.writeGuid("DataSetClassId", value.getDataSetClassId());
            encoder.writeStruct("ConfigurationVersion", value.getConfigurationVersion(), ConfigurationVersionDataType.TYPE_ID);
        }
    }
}
