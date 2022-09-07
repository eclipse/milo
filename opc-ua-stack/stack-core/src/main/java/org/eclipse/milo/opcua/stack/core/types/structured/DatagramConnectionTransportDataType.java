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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.2.2">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.4.1/#6.4.1.2.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class DatagramConnectionTransportDataType extends ConnectionTransportDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=17467");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=17468");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=17472");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=17476");

    private final NetworkAddressDataType discoveryAddress;

    public DatagramConnectionTransportDataType(NetworkAddressDataType discoveryAddress) {
        this.discoveryAddress = discoveryAddress;
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

    public NetworkAddressDataType getDiscoveryAddress() {
        return discoveryAddress;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 17468),
            new NodeId(0, 15618),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DiscoveryAddress", LocalizedText.NULL_VALUE, new NodeId(0, 15502), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DatagramConnectionTransportDataType> {
        @Override
        public Class<DatagramConnectionTransportDataType> getType() {
            return DatagramConnectionTransportDataType.class;
        }

        @Override
        public DatagramConnectionTransportDataType decode(SerializationContext context,
                                                          UaDecoder decoder) {
            NetworkAddressDataType discoveryAddress = (NetworkAddressDataType) decoder.readStruct("DiscoveryAddress", NetworkAddressDataType.TYPE_ID);
            return new DatagramConnectionTransportDataType(discoveryAddress);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DatagramConnectionTransportDataType value) {
            encoder.writeStruct("DiscoveryAddress", value.getDiscoveryAddress(), NetworkAddressDataType.TYPE_ID);
        }
    }
}
