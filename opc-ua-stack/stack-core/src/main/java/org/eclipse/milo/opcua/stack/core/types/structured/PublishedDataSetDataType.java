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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.5">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.3/#6.2.3.5</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PublishedDataSetDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15578");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15677");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15951");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16152");

    private final String name;

    private final String[] dataSetFolder;

    private final DataSetMetaDataType dataSetMetaData;

    private final KeyValuePair[] extensionFields;

    private final PublishedDataSetSourceDataType dataSetSource;

    public PublishedDataSetDataType(String name, String[] dataSetFolder,
                                    DataSetMetaDataType dataSetMetaData, KeyValuePair[] extensionFields,
                                    PublishedDataSetSourceDataType dataSetSource) {
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

    public PublishedDataSetSourceDataType getDataSetSource() {
        return dataSetSource;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15677),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetFolder", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetMetaData", LocalizedText.NULL_VALUE, new NodeId(0, 14523), -1, null, UInteger.valueOf(0), false),
                new StructureField("ExtensionFields", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetSource", LocalizedText.NULL_VALUE, new NodeId(0, 15580), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PublishedDataSetDataType> {
        @Override
        public Class<PublishedDataSetDataType> getType() {
            return PublishedDataSetDataType.class;
        }

        @Override
        public PublishedDataSetDataType decodeType(SerializationContext context, UaDecoder decoder) {
            String name = decoder.decodeString("Name");
            String[] dataSetFolder = decoder.decodeStringArray("DataSetFolder");
            DataSetMetaDataType dataSetMetaData = (DataSetMetaDataType) decoder.decodeStruct("DataSetMetaData", DataSetMetaDataType.TYPE_ID);
            KeyValuePair[] extensionFields = (KeyValuePair[]) decoder.decodeStructArray("ExtensionFields", KeyValuePair.TYPE_ID);
            PublishedDataSetSourceDataType dataSetSource = (PublishedDataSetSourceDataType) decoder.decodeStruct("DataSetSource", PublishedDataSetSourceDataType.TYPE_ID);
            return new PublishedDataSetDataType(name, dataSetFolder, dataSetMetaData, extensionFields, dataSetSource);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PublishedDataSetDataType value) {
            encoder.encodeString("Name", value.getName());
            encoder.encodeStringArray("DataSetFolder", value.getDataSetFolder());
            encoder.encodeStruct("DataSetMetaData", value.getDataSetMetaData(), DataSetMetaDataType.TYPE_ID);
            encoder.encodeStructArray("ExtensionFields", value.getExtensionFields(), KeyValuePair.TYPE_ID);
            encoder.encodeStruct("DataSetSource", value.getDataSetSource(), PublishedDataSetSourceDataType.TYPE_ID);
        }
    }
}
