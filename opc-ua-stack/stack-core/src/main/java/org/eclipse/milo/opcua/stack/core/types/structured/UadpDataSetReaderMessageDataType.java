package org.eclipse.milo.opcua.stack.core.types.structured;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.1/#6.3.1.4.10">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.1/#6.3.1.4.10</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class UadpDataSetReaderMessageDataType extends DataSetReaderMessageDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15653");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15718");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=16016");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16392");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15718),
            new NodeId(0, 15629),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("GroupVersion", LocalizedText.NULL_VALUE, new NodeId(0, 20998), -1, null, UInteger.valueOf(0), false),
                new StructureField("NetworkMessageNumber", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetOffset", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetClassId", LocalizedText.NULL_VALUE, new NodeId(0, 14), -1, null, UInteger.valueOf(0), false),
                new StructureField("NetworkMessageContentMask", LocalizedText.NULL_VALUE, new NodeId(0, 15642), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetMessageContentMask", LocalizedText.NULL_VALUE, new NodeId(0, 15646), -1, null, UInteger.valueOf(0), false),
                new StructureField("PublishingInterval", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReceiveOffset", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("ProcessingOffset", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<UadpDataSetReaderMessageDataType> {
        @Override
        public Class<UadpDataSetReaderMessageDataType> getType() {
            return UadpDataSetReaderMessageDataType.class;
        }

        @Override
        public UadpDataSetReaderMessageDataType decodeType(SerializationContext context,
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
        public void encodeType(SerializationContext context, UaEncoder encoder,
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
