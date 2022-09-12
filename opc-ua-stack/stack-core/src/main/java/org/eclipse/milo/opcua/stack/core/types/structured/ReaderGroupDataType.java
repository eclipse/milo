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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.8/#6.2.8.2.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.8/#6.2.8.2.1</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class ReaderGroupDataType extends PubSubGroupDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15520");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=21153");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=21177");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=21201");

    private final ReaderGroupTransportDataType transportSettings;

    private final ReaderGroupMessageDataType messageSettings;

    private final DataSetReaderDataType[] dataSetReaders;

    public ReaderGroupDataType(String name, Boolean enabled, MessageSecurityMode securityMode,
                               String securityGroupId, EndpointDescription[] securityKeyServices,
                               UInteger maxNetworkMessageSize, KeyValuePair[] groupProperties,
                               ReaderGroupTransportDataType transportSettings, ReaderGroupMessageDataType messageSettings,
                               DataSetReaderDataType[] dataSetReaders) {
        super(name, enabled, securityMode, securityGroupId, securityKeyServices, maxNetworkMessageSize, groupProperties);
        this.transportSettings = transportSettings;
        this.messageSettings = messageSettings;
        this.dataSetReaders = dataSetReaders;
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

    public ReaderGroupTransportDataType getTransportSettings() {
        return transportSettings;
    }

    public ReaderGroupMessageDataType getMessageSettings() {
        return messageSettings;
    }

    public DataSetReaderDataType[] getDataSetReaders() {
        return dataSetReaders;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 21153),
            new NodeId(0, 15609),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Enabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityMode", LocalizedText.NULL_VALUE, new NodeId(0, 302), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityGroupId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityKeyServices", LocalizedText.NULL_VALUE, new NodeId(0, 312), 1, null, UInteger.valueOf(0), false),
                new StructureField("MaxNetworkMessageSize", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("GroupProperties", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false),
                new StructureField("TransportSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15621), -1, null, UInteger.valueOf(0), false),
                new StructureField("MessageSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15622), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetReaders", LocalizedText.NULL_VALUE, new NodeId(0, 15623), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<ReaderGroupDataType> {
        @Override
        public Class<ReaderGroupDataType> getType() {
            return ReaderGroupDataType.class;
        }

        @Override
        public ReaderGroupDataType decodeType(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            Boolean enabled = decoder.readBoolean("Enabled");
            MessageSecurityMode securityMode = MessageSecurityMode.from(decoder.readEnum("SecurityMode"));
            String securityGroupId = decoder.readString("SecurityGroupId");
            EndpointDescription[] securityKeyServices = (EndpointDescription[]) decoder.readStructArray("SecurityKeyServices", EndpointDescription.TYPE_ID);
            UInteger maxNetworkMessageSize = decoder.readUInt32("MaxNetworkMessageSize");
            KeyValuePair[] groupProperties = (KeyValuePair[]) decoder.readStructArray("GroupProperties", KeyValuePair.TYPE_ID);
            ReaderGroupTransportDataType transportSettings = (ReaderGroupTransportDataType) decoder.readStruct("TransportSettings", ReaderGroupTransportDataType.TYPE_ID);
            ReaderGroupMessageDataType messageSettings = (ReaderGroupMessageDataType) decoder.readStruct("MessageSettings", ReaderGroupMessageDataType.TYPE_ID);
            DataSetReaderDataType[] dataSetReaders = (DataSetReaderDataType[]) decoder.readStructArray("DataSetReaders", DataSetReaderDataType.TYPE_ID);
            return new ReaderGroupDataType(name, enabled, securityMode, securityGroupId, securityKeyServices, maxNetworkMessageSize, groupProperties, transportSettings, messageSettings, dataSetReaders);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               ReaderGroupDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeEnum("SecurityMode", value.getSecurityMode());
            encoder.writeString("SecurityGroupId", value.getSecurityGroupId());
            encoder.writeStructArray("SecurityKeyServices", value.getSecurityKeyServices(), EndpointDescription.TYPE_ID);
            encoder.writeUInt32("MaxNetworkMessageSize", value.getMaxNetworkMessageSize());
            encoder.writeStructArray("GroupProperties", value.getGroupProperties(), KeyValuePair.TYPE_ID);
            encoder.writeStruct("TransportSettings", value.getTransportSettings(), ReaderGroupTransportDataType.TYPE_ID);
            encoder.writeStruct("MessageSettings", value.getMessageSettings(), ReaderGroupMessageDataType.TYPE_ID);
            encoder.writeStructArray("DataSetReaders", value.getDataSetReaders(), DataSetReaderDataType.TYPE_ID);
        }
    }
}
