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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class PublishedDataSetDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15578");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15677");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15951");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16152");

    private final String name;

    private final String[] dataSetFolder;

    private final DataSetMetaDataType dataSetMetaData;

    private final KeyValuePair[] extensionFields;

    private final ExtensionObject dataSetSource;

    public PublishedDataSetDataType(String name, String[] dataSetFolder,
                                    DataSetMetaDataType dataSetMetaData, KeyValuePair[] extensionFields,
                                    ExtensionObject dataSetSource) {
        this.name = name;
        this.dataSetFolder = dataSetFolder;
        this.dataSetMetaData = dataSetMetaData;
        this.extensionFields = extensionFields;
        this.dataSetSource = dataSetSource;
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

    public String[] getDataSetFolder() {
        return dataSetFolder;
    }

    public DataSetMetaDataType getDataSetMetaData() {
        return dataSetMetaData;
    }

    public KeyValuePair[] getExtensionFields() {
        return extensionFields;
    }

    public ExtensionObject getDataSetSource() {
        return dataSetSource;
    }

    public static final class Codec extends GenericDataTypeCodec<PublishedDataSetDataType> {
        @Override
        public Class<PublishedDataSetDataType> getType() {
            return PublishedDataSetDataType.class;
        }

        @Override
        public PublishedDataSetDataType decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            String[] dataSetFolder = decoder.readStringArray("DataSetFolder");
            DataSetMetaDataType dataSetMetaData = (DataSetMetaDataType) decoder.readStruct("DataSetMetaData", DataSetMetaDataType.TYPE_ID);
            KeyValuePair[] extensionFields = (KeyValuePair[]) decoder.readStructArray("ExtensionFields", KeyValuePair.TYPE_ID);
            ExtensionObject dataSetSource = decoder.readExtensionObject("DataSetSource");
            return new PublishedDataSetDataType(name, dataSetFolder, dataSetMetaData, extensionFields, dataSetSource);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           PublishedDataSetDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeStringArray("DataSetFolder", value.getDataSetFolder());
            encoder.writeStruct("DataSetMetaData", value.getDataSetMetaData(), DataSetMetaDataType.TYPE_ID);
            encoder.writeStructArray("ExtensionFields", value.getExtensionFields(), KeyValuePair.TYPE_ID);
            encoder.writeExtensionObject("DataSetSource", value.getDataSetSource());
        }
    }
}
