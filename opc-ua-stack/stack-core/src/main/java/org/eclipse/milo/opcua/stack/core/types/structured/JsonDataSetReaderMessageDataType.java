package org.eclipse.milo.opcua.stack.core.types.structured;

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
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.2/#6.3.2.4.3">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.3.2/#6.3.2.4.3</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class JsonDataSetReaderMessageDataType extends DataSetReaderMessageDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15665");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15725");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=16019");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16404");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15725),
            new NodeId(0, 15629),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NetworkMessageContentMask", LocalizedText.NULL_VALUE, new NodeId(0, 15654), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetMessageContentMask", LocalizedText.NULL_VALUE, new NodeId(0, 15658), -1, null, UInteger.valueOf(0), false)
            }
        );
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
