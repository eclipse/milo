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
public class JsonDataSetWriterMessageDataType extends DataSetWriterMessageDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15664");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15724");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16018");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16394");

    private final JsonDataSetMessageContentMask dataSetMessageContentMask;

    public JsonDataSetWriterMessageDataType(JsonDataSetMessageContentMask dataSetMessageContentMask) {
        this.dataSetMessageContentMask = dataSetMessageContentMask;
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

    public JsonDataSetMessageContentMask getDataSetMessageContentMask() {
        return dataSetMessageContentMask;
    }

    public static final class Codec extends GenericDataTypeCodec<JsonDataSetWriterMessageDataType> {
        @Override
        public Class<JsonDataSetWriterMessageDataType> getType() {
            return JsonDataSetWriterMessageDataType.class;
        }

        @Override
        public JsonDataSetWriterMessageDataType decode(SerializationContext context,
                                                       UaDecoder decoder) {
            JsonDataSetMessageContentMask dataSetMessageContentMask = new JsonDataSetMessageContentMask(decoder.readUInt32("DataSetMessageContentMask"));
            return new JsonDataSetWriterMessageDataType(dataSetMessageContentMask);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           JsonDataSetWriterMessageDataType value) {
            encoder.writeUInt32("DataSetMessageContentMask", value.getDataSetMessageContentMask().getValue());
        }
    }
}
