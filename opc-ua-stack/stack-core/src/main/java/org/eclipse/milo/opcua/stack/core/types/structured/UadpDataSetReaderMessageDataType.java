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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class UadpDataSetReaderMessageDataType extends DataSetReaderMessageDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15653");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15718");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16016");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=16392");

    private final UInteger groupVersion;

    private final UShort networkMessageNumber;

    private final UShort dataSetOffset;

    private final UUID dataSetClassId;

    private final UadpNetworkMessageContentMask networkMessageContentMask;

    private final UadpDataSetMessageContentMask dataSetMessageContentMask;

    private final Double publishingInterval;

    private final Double receiveOffset;

    private final Double processingOffset;

    public UadpDataSetReaderMessageDataType(UInteger groupVersion, UShort networkMessageNumber,
                                            UShort dataSetOffset, UUID dataSetClassId,
                                            UadpNetworkMessageContentMask networkMessageContentMask,
                                            UadpDataSetMessageContentMask dataSetMessageContentMask, Double publishingInterval,
                                            Double receiveOffset, Double processingOffset) {
        this.groupVersion = groupVersion;
        this.networkMessageNumber = networkMessageNumber;
        this.dataSetOffset = dataSetOffset;
        this.dataSetClassId = dataSetClassId;
        this.networkMessageContentMask = networkMessageContentMask;
        this.dataSetMessageContentMask = dataSetMessageContentMask;
        this.publishingInterval = publishingInterval;
        this.receiveOffset = receiveOffset;
        this.processingOffset = processingOffset;
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

    public UShort getNetworkMessageNumber() {
        return networkMessageNumber;
    }

    public UShort getDataSetOffset() {
        return dataSetOffset;
    }

    public UUID getDataSetClassId() {
        return dataSetClassId;
    }

    public UadpNetworkMessageContentMask getNetworkMessageContentMask() {
        return networkMessageContentMask;
    }

    public UadpDataSetMessageContentMask getDataSetMessageContentMask() {
        return dataSetMessageContentMask;
    }

    public Double getPublishingInterval() {
        return publishingInterval;
    }

    public Double getReceiveOffset() {
        return receiveOffset;
    }

    public Double getProcessingOffset() {
        return processingOffset;
    }

    public static final class Codec extends GenericDataTypeCodec<UadpDataSetReaderMessageDataType> {
        @Override
        public Class<UadpDataSetReaderMessageDataType> getType() {
            return UadpDataSetReaderMessageDataType.class;
        }

        @Override
        public UadpDataSetReaderMessageDataType decode(SerializationContext context,
                                                       UaDecoder decoder) {
            UInteger groupVersion = decoder.readUInt32("GroupVersion");
            UShort networkMessageNumber = decoder.readUInt16("NetworkMessageNumber");
            UShort dataSetOffset = decoder.readUInt16("DataSetOffset");
            UUID dataSetClassId = decoder.readGuid("DataSetClassId");
            UadpNetworkMessageContentMask networkMessageContentMask = new UadpNetworkMessageContentMask(decoder.readUInt32("NetworkMessageContentMask"));
            UadpDataSetMessageContentMask dataSetMessageContentMask = new UadpDataSetMessageContentMask(decoder.readUInt32("DataSetMessageContentMask"));
            Double publishingInterval = decoder.readDouble("PublishingInterval");
            Double receiveOffset = decoder.readDouble("ReceiveOffset");
            Double processingOffset = decoder.readDouble("ProcessingOffset");
            return new UadpDataSetReaderMessageDataType(groupVersion, networkMessageNumber, dataSetOffset, dataSetClassId, networkMessageContentMask, dataSetMessageContentMask, publishingInterval, receiveOffset, processingOffset);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           UadpDataSetReaderMessageDataType value) {
            encoder.writeUInt32("GroupVersion", value.getGroupVersion());
            encoder.writeUInt16("NetworkMessageNumber", value.getNetworkMessageNumber());
            encoder.writeUInt16("DataSetOffset", value.getDataSetOffset());
            encoder.writeGuid("DataSetClassId", value.getDataSetClassId());
            encoder.writeUInt32("NetworkMessageContentMask", value.getNetworkMessageContentMask().getValue());
            encoder.writeUInt32("DataSetMessageContentMask", value.getDataSetMessageContentMask().getValue());
            encoder.writeDouble("PublishingInterval", value.getPublishingInterval());
            encoder.writeDouble("ReceiveOffset", value.getReceiveOffset());
            encoder.writeDouble("ProcessingOffset", value.getProcessingOffset());
        }
    }
}
