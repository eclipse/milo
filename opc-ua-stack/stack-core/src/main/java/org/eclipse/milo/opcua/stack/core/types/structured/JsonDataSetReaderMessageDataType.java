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
public class JsonDataSetReaderMessageDataType extends DataSetReaderMessageDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15665");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15725");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16019");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16404");

    private final JsonNetworkMessageContentMask networkMessageContentMask;

    private final JsonDataSetMessageContentMask dataSetMessageContentMask;

    public JsonDataSetReaderMessageDataType(JsonNetworkMessageContentMask networkMessageContentMask,
                                            JsonDataSetMessageContentMask dataSetMessageContentMask) {
        this.networkMessageContentMask = networkMessageContentMask;
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

    public JsonNetworkMessageContentMask getNetworkMessageContentMask() {
        return networkMessageContentMask;
    }

    public JsonDataSetMessageContentMask getDataSetMessageContentMask() {
        return dataSetMessageContentMask;
    }

    public static final class Codec extends GenericDataTypeCodec<JsonDataSetReaderMessageDataType> {
        @Override
        public Class<JsonDataSetReaderMessageDataType> getType() {
            return JsonDataSetReaderMessageDataType.class;
        }

        @Override
        public JsonDataSetReaderMessageDataType decode(SerializationContext context,
                                                       UaDecoder decoder) {
            JsonNetworkMessageContentMask networkMessageContentMask = new JsonNetworkMessageContentMask(decoder.readUInt32("NetworkMessageContentMask"));
            JsonDataSetMessageContentMask dataSetMessageContentMask = new JsonDataSetMessageContentMask(decoder.readUInt32("DataSetMessageContentMask"));
            return new JsonDataSetReaderMessageDataType(networkMessageContentMask, dataSetMessageContentMask);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           JsonDataSetReaderMessageDataType value) {
            encoder.writeUInt32("NetworkMessageContentMask", value.getNetworkMessageContentMask().getValue());
            encoder.writeUInt32("DataSetMessageContentMask", value.getDataSetMessageContentMask().getValue());
        }
    }
}
