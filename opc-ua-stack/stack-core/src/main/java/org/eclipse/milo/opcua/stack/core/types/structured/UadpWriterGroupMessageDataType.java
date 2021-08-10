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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataSetOrderingType;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class UadpWriterGroupMessageDataType extends WriterGroupMessageDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15645");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15715");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16014");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16323");

    private final UInteger groupVersion;

    private final DataSetOrderingType dataSetOrdering;

    private final UadpNetworkMessageContentMask networkMessageContentMask;

    private final Double samplingOffset;

    private final Double[] publishingOffset;

    public UadpWriterGroupMessageDataType(UInteger groupVersion, DataSetOrderingType dataSetOrdering,
                                          UadpNetworkMessageContentMask networkMessageContentMask, Double samplingOffset,
                                          Double[] publishingOffset) {
        this.groupVersion = groupVersion;
        this.dataSetOrdering = dataSetOrdering;
        this.networkMessageContentMask = networkMessageContentMask;
        this.samplingOffset = samplingOffset;
        this.publishingOffset = publishingOffset;
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

    public UInteger getGroupVersion() {
        return groupVersion;
    }

    public DataSetOrderingType getDataSetOrdering() {
        return dataSetOrdering;
    }

    public UadpNetworkMessageContentMask getNetworkMessageContentMask() {
        return networkMessageContentMask;
    }

    public Double getSamplingOffset() {
        return samplingOffset;
    }

    public Double[] getPublishingOffset() {
        return publishingOffset;
    }

    public static final class Codec extends GenericDataTypeCodec<UadpWriterGroupMessageDataType> {
        @Override
        public Class<UadpWriterGroupMessageDataType> getType() {
            return UadpWriterGroupMessageDataType.class;
        }

        @Override
        public UadpWriterGroupMessageDataType decode(SerializationContext context, UaDecoder decoder) {
            UInteger groupVersion = decoder.readUInt32("GroupVersion");
            DataSetOrderingType dataSetOrdering = decoder.readEnum("DataSetOrdering", DataSetOrderingType.class);
            UadpNetworkMessageContentMask networkMessageContentMask = new UadpNetworkMessageContentMask(decoder.readUInt32("NetworkMessageContentMask"));
            Double samplingOffset = decoder.readDouble("SamplingOffset");
            Double[] publishingOffset = decoder.readDoubleArray("PublishingOffset");
            return new UadpWriterGroupMessageDataType(groupVersion, dataSetOrdering, networkMessageContentMask, samplingOffset, publishingOffset);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           UadpWriterGroupMessageDataType value) {
            encoder.writeUInt32("GroupVersion", value.getGroupVersion());
            encoder.writeEnum("DataSetOrdering", value.getDataSetOrdering());
            encoder.writeUInt32("NetworkMessageContentMask", value.getNetworkMessageContentMask().getValue());
            encoder.writeDouble("SamplingOffset", value.getSamplingOffset());
            encoder.writeDoubleArray("PublishingOffset", value.getPublishingOffset());
        }
    }
}
