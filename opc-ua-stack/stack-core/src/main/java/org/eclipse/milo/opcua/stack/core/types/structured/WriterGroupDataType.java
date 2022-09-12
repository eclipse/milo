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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.6/#6.2.6.7.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.6/#6.2.6.7.1</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class WriterGroupDataType extends PubSubGroupDataType implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15480");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=21150");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=21174");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=21198");

    private final UShort writerGroupId;

    private final Double publishingInterval;

    private final Double keepAliveTime;

    private final UByte priority;

    private final String[] localeIds;

    private final String headerLayoutUri;

    private final WriterGroupTransportDataType transportSettings;

    private final WriterGroupMessageDataType messageSettings;

    private final DataSetWriterDataType[] dataSetWriters;

    public WriterGroupDataType(String name, Boolean enabled, MessageSecurityMode securityMode,
                               String securityGroupId, EndpointDescription[] securityKeyServices,
                               UInteger maxNetworkMessageSize, KeyValuePair[] groupProperties, UShort writerGroupId,
                               Double publishingInterval, Double keepAliveTime, UByte priority, String[] localeIds,
                               String headerLayoutUri, WriterGroupTransportDataType transportSettings,
                               WriterGroupMessageDataType messageSettings, DataSetWriterDataType[] dataSetWriters) {
        super(name, enabled, securityMode, securityGroupId, securityKeyServices, maxNetworkMessageSize, groupProperties);
        this.writerGroupId = writerGroupId;
        this.publishingInterval = publishingInterval;
        this.keepAliveTime = keepAliveTime;
        this.priority = priority;
        this.localeIds = localeIds;
        this.headerLayoutUri = headerLayoutUri;
        this.transportSettings = transportSettings;
        this.messageSettings = messageSettings;
        this.dataSetWriters = dataSetWriters;
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

    public UShort getWriterGroupId() {
        return writerGroupId;
    }

    public Double getPublishingInterval() {
        return publishingInterval;
    }

    public Double getKeepAliveTime() {
        return keepAliveTime;
    }

    public UByte getPriority() {
        return priority;
    }

    public String[] getLocaleIds() {
        return localeIds;
    }

    public String getHeaderLayoutUri() {
        return headerLayoutUri;
    }

    public WriterGroupTransportDataType getTransportSettings() {
        return transportSettings;
    }

    public WriterGroupMessageDataType getMessageSettings() {
        return messageSettings;
    }

    public DataSetWriterDataType[] getDataSetWriters() {
        return dataSetWriters;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 21150),
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
                new StructureField("WriterGroupId", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("PublishingInterval", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("KeepAliveTime", LocalizedText.NULL_VALUE, new NodeId(0, 290), -1, null, UInteger.valueOf(0), false),
                new StructureField("Priority", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false),
                new StructureField("LocaleIds", LocalizedText.NULL_VALUE, new NodeId(0, 295), 1, null, UInteger.valueOf(0), false),
                new StructureField("HeaderLayoutUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("TransportSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15611), -1, null, UInteger.valueOf(0), false),
                new StructureField("MessageSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15616), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetWriters", LocalizedText.NULL_VALUE, new NodeId(0, 15597), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<WriterGroupDataType> {
        @Override
        public Class<WriterGroupDataType> getType() {
            return WriterGroupDataType.class;
        }

        @Override
        public WriterGroupDataType decodeType(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            Boolean enabled = decoder.readBoolean("Enabled");
            MessageSecurityMode securityMode = MessageSecurityMode.from(decoder.readEnum("SecurityMode"));
            String securityGroupId = decoder.readString("SecurityGroupId");
            EndpointDescription[] securityKeyServices = (EndpointDescription[]) decoder.readStructArray("SecurityKeyServices", EndpointDescription.TYPE_ID);
            UInteger maxNetworkMessageSize = decoder.readUInt32("MaxNetworkMessageSize");
            KeyValuePair[] groupProperties = (KeyValuePair[]) decoder.readStructArray("GroupProperties", KeyValuePair.TYPE_ID);
            UShort writerGroupId = decoder.readUInt16("WriterGroupId");
            Double publishingInterval = decoder.readDouble("PublishingInterval");
            Double keepAliveTime = decoder.readDouble("KeepAliveTime");
            UByte priority = decoder.readByte("Priority");
            String[] localeIds = decoder.readStringArray("LocaleIds");
            String headerLayoutUri = decoder.readString("HeaderLayoutUri");
            WriterGroupTransportDataType transportSettings = (WriterGroupTransportDataType) decoder.readStruct("TransportSettings", WriterGroupTransportDataType.TYPE_ID);
            WriterGroupMessageDataType messageSettings = (WriterGroupMessageDataType) decoder.readStruct("MessageSettings", WriterGroupMessageDataType.TYPE_ID);
            DataSetWriterDataType[] dataSetWriters = (DataSetWriterDataType[]) decoder.readStructArray("DataSetWriters", DataSetWriterDataType.TYPE_ID);
            return new WriterGroupDataType(name, enabled, securityMode, securityGroupId, securityKeyServices, maxNetworkMessageSize, groupProperties, writerGroupId, publishingInterval, keepAliveTime, priority, localeIds, headerLayoutUri, transportSettings, messageSettings, dataSetWriters);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               WriterGroupDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeEnum("SecurityMode", value.getSecurityMode());
            encoder.writeString("SecurityGroupId", value.getSecurityGroupId());
            encoder.writeStructArray("SecurityKeyServices", value.getSecurityKeyServices(), EndpointDescription.TYPE_ID);
            encoder.writeUInt32("MaxNetworkMessageSize", value.getMaxNetworkMessageSize());
            encoder.writeStructArray("GroupProperties", value.getGroupProperties(), KeyValuePair.TYPE_ID);
            encoder.writeUInt16("WriterGroupId", value.getWriterGroupId());
            encoder.writeDouble("PublishingInterval", value.getPublishingInterval());
            encoder.writeDouble("KeepAliveTime", value.getKeepAliveTime());
            encoder.writeByte("Priority", value.getPriority());
            encoder.writeStringArray("LocaleIds", value.getLocaleIds());
            encoder.writeString("HeaderLayoutUri", value.getHeaderLayoutUri());
            encoder.writeStruct("TransportSettings", value.getTransportSettings(), WriterGroupTransportDataType.TYPE_ID);
            encoder.writeStruct("MessageSettings", value.getMessageSettings(), WriterGroupMessageDataType.TYPE_ID);
            encoder.writeStructArray("DataSetWriters", value.getDataSetWriters(), DataSetWriterDataType.TYPE_ID);
        }
    }
}
