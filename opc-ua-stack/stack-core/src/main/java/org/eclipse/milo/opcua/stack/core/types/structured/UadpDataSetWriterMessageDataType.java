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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class UadpDataSetWriterMessageDataType extends DataSetWriterMessageDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15652");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15717");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16015");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16391");

    private final UadpDataSetMessageContentMask dataSetMessageContentMask;

    private final UShort configuredSize;

    private final UShort networkMessageNumber;

    private final UShort dataSetOffset;

    public UadpDataSetWriterMessageDataType(UadpDataSetMessageContentMask dataSetMessageContentMask,
                                            UShort configuredSize, UShort networkMessageNumber, UShort dataSetOffset) {
        this.dataSetMessageContentMask = dataSetMessageContentMask;
        this.configuredSize = configuredSize;
        this.networkMessageNumber = networkMessageNumber;
        this.dataSetOffset = dataSetOffset;
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

    public UadpDataSetMessageContentMask getDataSetMessageContentMask() {
        return dataSetMessageContentMask;
    }

    public UShort getConfiguredSize() {
        return configuredSize;
    }

    public UShort getNetworkMessageNumber() {
        return networkMessageNumber;
    }

    public UShort getDataSetOffset() {
        return dataSetOffset;
    }

    public static final class Codec extends GenericDataTypeCodec<UadpDataSetWriterMessageDataType> {
        @Override
        public Class<UadpDataSetWriterMessageDataType> getType() {
            return UadpDataSetWriterMessageDataType.class;
        }

        @Override
        public UadpDataSetWriterMessageDataType decode(SerializationContext context,
                                                       UaDecoder decoder) {
            UadpDataSetMessageContentMask dataSetMessageContentMask = new UadpDataSetMessageContentMask(decoder.readUInt32("DataSetMessageContentMask"));
            UShort configuredSize = decoder.readUInt16("ConfiguredSize");
            UShort networkMessageNumber = decoder.readUInt16("NetworkMessageNumber");
            UShort dataSetOffset = decoder.readUInt16("DataSetOffset");
            return new UadpDataSetWriterMessageDataType(dataSetMessageContentMask, configuredSize, networkMessageNumber, dataSetOffset);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           UadpDataSetWriterMessageDataType value) {
            encoder.writeUInt32("DataSetMessageContentMask", value.getDataSetMessageContentMask().getValue());
            encoder.writeUInt16("ConfiguredSize", value.getConfiguredSize());
            encoder.writeUInt16("NetworkMessageNumber", value.getNetworkMessageNumber());
            encoder.writeUInt16("DataSetOffset", value.getDataSetOffset());
        }
    }
}
