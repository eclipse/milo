package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataSetOrderingType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.1/#6.3.1.1.7">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.1/#6.3.1.1.7</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class UadpWriterGroupMessageDataType extends WriterGroupMessageDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15645");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15715");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=16014");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16323");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15715),
            new NodeId(0, 15616),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("GroupVersion", LocalizedText.NULL_VALUE, new NodeId(0, 20998), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetOrdering", LocalizedText.NULL_VALUE, new NodeId(0, 20408), -1, null, UInteger.valueOf(0), false),
                new StructureField("NetworkMessageContentMask", LocalizedText.NULL_VALUE, new NodeId(0, 15642), -1, null, UInteger.valueOf(0), false),
                new StructureField("SamplingOffset", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("PublishingOffset", LocalizedText.NULL_VALUE, new NodeId(0, 290), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<UadpWriterGroupMessageDataType> {
        @Override
        public Class<UadpWriterGroupMessageDataType> getType() {
            return UadpWriterGroupMessageDataType.class;
        }

        @Override
        public UadpWriterGroupMessageDataType decodeType(SerializationContext context,
                                                         UaDecoder decoder) {
            UInteger groupVersion = decoder.readUInt32("GroupVersion");
            DataSetOrderingType dataSetOrdering = DataSetOrderingType.from(decoder.readEnum("DataSetOrdering"));
            UadpNetworkMessageContentMask networkMessageContentMask = new UadpNetworkMessageContentMask(decoder.readUInt32("NetworkMessageContentMask"));
            Double samplingOffset = decoder.readDouble("SamplingOffset");
            Double[] publishingOffset = decoder.readDoubleArray("PublishingOffset");
            return new UadpWriterGroupMessageDataType(groupVersion, dataSetOrdering, networkMessageContentMask, samplingOffset, publishingOffset);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               UadpWriterGroupMessageDataType value) {
            encoder.writeUInt32("GroupVersion", value.getGroupVersion());
            encoder.writeEnum("DataSetOrdering", value.getDataSetOrdering());
            encoder.writeUInt32("NetworkMessageContentMask", value.getNetworkMessageContentMask().getValue());
            encoder.writeDouble("SamplingOffset", value.getSamplingOffset());
            encoder.writeDoubleArray("PublishingOffset", value.getPublishingOffset());
        }
    }
}
