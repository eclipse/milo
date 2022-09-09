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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.2.7">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.2.7</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class DatagramConnectionTransport2DataType extends DatagramConnectionTransportDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23612");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23864");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23932");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=24000");

    private final UInteger discoveryAnnounceRate;

    private final UInteger discoveryMaxMessageSize;

    private final String qosCategory;

    private final QosDataType[] datagramQos;

    public DatagramConnectionTransport2DataType(NetworkAddressDataType discoveryAddress,
                                                UInteger discoveryAnnounceRate, UInteger discoveryMaxMessageSize, String qosCategory,
                                                QosDataType[] datagramQos) {
        super(discoveryAddress);
        this.discoveryAnnounceRate = discoveryAnnounceRate;
        this.discoveryMaxMessageSize = discoveryMaxMessageSize;
        this.qosCategory = qosCategory;
        this.datagramQos = datagramQos;
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

    public UInteger getDiscoveryAnnounceRate() {
        return discoveryAnnounceRate;
    }

    public UInteger getDiscoveryMaxMessageSize() {
        return discoveryMaxMessageSize;
    }

    public String getQosCategory() {
        return qosCategory;
    }

    public QosDataType[] getDatagramQos() {
        return datagramQos;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23864),
            new NodeId(0, 17467),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DiscoveryAddress", LocalizedText.NULL_VALUE, new NodeId(0, 15502), -1, null, UInteger.valueOf(0), false),
                new StructureField("DiscoveryAnnounceRate", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("DiscoveryMaxMessageSize", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("QosCategory", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DatagramQos", LocalizedText.NULL_VALUE, new NodeId(0, 23603), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DatagramConnectionTransport2DataType> {
        @Override
        public Class<DatagramConnectionTransport2DataType> getType() {
            return DatagramConnectionTransport2DataType.class;
        }

        @Override
        public DatagramConnectionTransport2DataType decodeType(SerializationContext context,
                                                               UaDecoder decoder) {
            NetworkAddressDataType discoveryAddress = (NetworkAddressDataType) decoder.readStruct("DiscoveryAddress", NetworkAddressDataType.TYPE_ID);
            UInteger discoveryAnnounceRate = decoder.readUInt32("DiscoveryAnnounceRate");
            UInteger discoveryMaxMessageSize = decoder.readUInt32("DiscoveryMaxMessageSize");
            String qosCategory = decoder.readString("QosCategory");
            QosDataType[] datagramQos = (QosDataType[]) decoder.readStructArray("DatagramQos", QosDataType.TYPE_ID);
            return new DatagramConnectionTransport2DataType(discoveryAddress, discoveryAnnounceRate, discoveryMaxMessageSize, qosCategory, datagramQos);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               DatagramConnectionTransport2DataType value) {
            encoder.writeStruct("DiscoveryAddress", value.getDiscoveryAddress(), NetworkAddressDataType.TYPE_ID);
            encoder.writeUInt32("DiscoveryAnnounceRate", value.getDiscoveryAnnounceRate());
            encoder.writeUInt32("DiscoveryMaxMessageSize", value.getDiscoveryMaxMessageSize());
            encoder.writeString("QosCategory", value.getQosCategory());
            encoder.writeStructArray("DatagramQos", value.getDatagramQos(), QosDataType.TYPE_ID);
        }
    }
}
