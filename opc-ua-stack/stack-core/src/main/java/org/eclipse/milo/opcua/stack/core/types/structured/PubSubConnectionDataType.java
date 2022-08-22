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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.7/#6.2.7.5.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.7/#6.2.7.5.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PubSubConnectionDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15617");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15694");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15992");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16281");

    private final String name;

    private final Boolean enabled;

    private final Variant publisherId;

    private final String transportProfileUri;

    private final NetworkAddressDataType address;

    private final KeyValuePair[] connectionProperties;

    private final ConnectionTransportDataType transportSettings;

    private final WriterGroupDataType[] writerGroups;

    private final ReaderGroupDataType[] readerGroups;

    public PubSubConnectionDataType(String name, Boolean enabled, Variant publisherId,
                                    String transportProfileUri, NetworkAddressDataType address,
                                    KeyValuePair[] connectionProperties, ConnectionTransportDataType transportSettings,
                                    WriterGroupDataType[] writerGroups, ReaderGroupDataType[] readerGroups) {
        this.name = name;
        this.enabled = enabled;
        this.publisherId = publisherId;
        this.transportProfileUri = transportProfileUri;
        this.address = address;
        this.connectionProperties = connectionProperties;
        this.transportSettings = transportSettings;
        this.writerGroups = writerGroups;
        this.readerGroups = readerGroups;
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

    public String getName() {
        return name;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Variant getPublisherId() {
        return publisherId;
    }

    public String getTransportProfileUri() {
        return transportProfileUri;
    }

    public NetworkAddressDataType getAddress() {
        return address;
    }

    public KeyValuePair[] getConnectionProperties() {
        return connectionProperties;
    }

    public ConnectionTransportDataType getTransportSettings() {
        return transportSettings;
    }

    public WriterGroupDataType[] getWriterGroups() {
        return writerGroups;
    }

    public ReaderGroupDataType[] getReaderGroups() {
        return readerGroups;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15694),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Enabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("PublisherId", LocalizedText.NULL_VALUE, new NodeId(0, 24), -1, null, UInteger.valueOf(0), false),
                new StructureField("TransportProfileUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Address", LocalizedText.NULL_VALUE, new NodeId(0, 15502), -1, null, UInteger.valueOf(0), false),
                new StructureField("ConnectionProperties", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false),
                new StructureField("TransportSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15618), -1, null, UInteger.valueOf(0), false),
                new StructureField("WriterGroups", LocalizedText.NULL_VALUE, new NodeId(0, 15480), 1, null, UInteger.valueOf(0), false),
                new StructureField("ReaderGroups", LocalizedText.NULL_VALUE, new NodeId(0, 15520), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubConnectionDataType> {
        @Override
        public Class<PubSubConnectionDataType> getType() {
            return PubSubConnectionDataType.class;
        }

        @Override
        public PubSubConnectionDataType decode(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            Boolean enabled = decoder.readBoolean("Enabled");
            Variant publisherId = decoder.readVariant("PublisherId");
            String transportProfileUri = decoder.readString("TransportProfileUri");
            NetworkAddressDataType address = (NetworkAddressDataType) decoder.readStruct("Address", NetworkAddressDataType.TYPE_ID);
            KeyValuePair[] connectionProperties = (KeyValuePair[]) decoder.readStructArray("ConnectionProperties", KeyValuePair.TYPE_ID);
            ConnectionTransportDataType transportSettings = (ConnectionTransportDataType) decoder.readStruct("TransportSettings", ConnectionTransportDataType.TYPE_ID);
            WriterGroupDataType[] writerGroups = (WriterGroupDataType[]) decoder.readStructArray("WriterGroups", WriterGroupDataType.TYPE_ID);
            ReaderGroupDataType[] readerGroups = (ReaderGroupDataType[]) decoder.readStructArray("ReaderGroups", ReaderGroupDataType.TYPE_ID);
            return new PubSubConnectionDataType(name, enabled, publisherId, transportProfileUri, address, connectionProperties, transportSettings, writerGroups, readerGroups);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           PubSubConnectionDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeVariant("PublisherId", value.getPublisherId());
            encoder.writeString("TransportProfileUri", value.getTransportProfileUri());
            encoder.writeStruct("Address", value.getAddress(), NetworkAddressDataType.TYPE_ID);
            encoder.writeStructArray("ConnectionProperties", value.getConnectionProperties(), KeyValuePair.TYPE_ID);
            encoder.writeStruct("TransportSettings", value.getTransportSettings(), ConnectionTransportDataType.TYPE_ID);
            encoder.writeStructArray("WriterGroups", value.getWriterGroups(), WriterGroupDataType.TYPE_ID);
            encoder.writeStructArray("ReaderGroups", value.getReaderGroups(), ReaderGroupDataType.TYPE_ID);
        }
    }
}
