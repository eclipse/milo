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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class UABinaryFileDataType extends DataTypeSchemaHeader implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15006");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15422");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15531");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15714");

    private final String schemaLocation;

    private final KeyValuePair[] fileHeader;

    private final Variant body;

    public UABinaryFileDataType(String[] namespaces, StructureDescription[] structureDataTypes,
                                EnumDescription[] enumDataTypes, SimpleTypeDescription[] simpleDataTypes,
                                String schemaLocation, KeyValuePair[] fileHeader, Variant body) {
        super(namespaces, structureDataTypes, enumDataTypes, simpleDataTypes);
        this.schemaLocation = schemaLocation;
        this.fileHeader = fileHeader;
        this.body = body;
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

    public String getSchemaLocation() {
        return schemaLocation;
    }

    public KeyValuePair[] getFileHeader() {
        return fileHeader;
    }

    public Variant getBody() {
        return body;
    }

    public static final class Codec extends GenericDataTypeCodec<UABinaryFileDataType> {
        @Override
        public Class<UABinaryFileDataType> getType() {
            return UABinaryFileDataType.class;
        }

        @Override
        public UABinaryFileDataType decode(SerializationContext context, UaDecoder decoder) {
            String[] namespaces = decoder.readStringArray("Namespaces");
            StructureDescription[] structureDataTypes = (StructureDescription[]) decoder.readStructArray("StructureDataTypes", StructureDescription.TYPE_ID);
            EnumDescription[] enumDataTypes = (EnumDescription[]) decoder.readStructArray("EnumDataTypes", EnumDescription.TYPE_ID);
            SimpleTypeDescription[] simpleDataTypes = (SimpleTypeDescription[]) decoder.readStructArray("SimpleDataTypes", SimpleTypeDescription.TYPE_ID);
            String schemaLocation = decoder.readString("SchemaLocation");
            KeyValuePair[] fileHeader = (KeyValuePair[]) decoder.readStructArray("FileHeader", KeyValuePair.TYPE_ID);
            Variant body = decoder.readVariant("Body");
            return new UABinaryFileDataType(namespaces, structureDataTypes, enumDataTypes, simpleDataTypes, schemaLocation, fileHeader, body);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           UABinaryFileDataType value) {
            encoder.writeStringArray("Namespaces", value.getNamespaces());
            encoder.writeStructArray("StructureDataTypes", value.getStructureDataTypes(), StructureDescription.TYPE_ID);
            encoder.writeStructArray("EnumDataTypes", value.getEnumDataTypes(), EnumDescription.TYPE_ID);
            encoder.writeStructArray("SimpleDataTypes", value.getSimpleDataTypes(), SimpleTypeDescription.TYPE_ID);
            encoder.writeString("SchemaLocation", value.getSchemaLocation());
            encoder.writeStructArray("FileHeader", value.getFileHeader(), KeyValuePair.TYPE_ID);
            encoder.writeVariant("Body", value.getBody());
        }
    }
}
