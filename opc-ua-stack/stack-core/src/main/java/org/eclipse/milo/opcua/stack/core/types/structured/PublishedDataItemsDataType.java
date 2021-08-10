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

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class PublishedDataItemsDataType extends PublishedDataSetSourceDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15581");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15679");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15953");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16154");

    private final PublishedVariableDataType[] publishedData;

    public PublishedDataItemsDataType(PublishedVariableDataType[] publishedData) {
        this.publishedData = publishedData;
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

    public PublishedVariableDataType[] getPublishedData() {
        return publishedData;
    }

    public static final class Codec extends GenericDataTypeCodec<PublishedDataItemsDataType> {
        @Override
        public Class<PublishedDataItemsDataType> getType() {
            return PublishedDataItemsDataType.class;
        }

        @Override
        public PublishedDataItemsDataType decode(SerializationContext context, UaDecoder decoder) {
            PublishedVariableDataType[] publishedData = (PublishedVariableDataType[]) decoder.readStructArray("PublishedData", PublishedVariableDataType.TYPE_ID);
            return new PublishedDataItemsDataType(publishedData);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           PublishedDataItemsDataType value) {
            encoder.writeStructArray("PublishedData", value.getPublishedData(), PublishedVariableDataType.TYPE_ID);
        }
    }
}
