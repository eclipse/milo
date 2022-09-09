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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.12/#6.2.12.4">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.12/#6.2.12.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class PubSubConfiguration2DataType extends PubSubConfigurationDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23602");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23854");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23922");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=23990");

    private final StandaloneSubscribedDataSetDataType[] subscribedDataSets;

    private final DataSetMetaDataType[] dataSetClasses;

    private final EndpointDescription[] defaultSecurityKeyServices;

    private final SecurityGroupDataType[] securityGroups;

    private final PubSubKeyPushTargetDataType[] pubSubKeyPushTargets;

    private final UInteger configurationVersion;

    private final KeyValuePair[] configurationProperties;

    public PubSubConfiguration2DataType(PublishedDataSetDataType[] publishedDataSets,
                                        PubSubConnectionDataType[] connections, Boolean enabled,
                                        StandaloneSubscribedDataSetDataType[] subscribedDataSets,
                                        DataSetMetaDataType[] dataSetClasses, EndpointDescription[] defaultSecurityKeyServices,
                                        SecurityGroupDataType[] securityGroups, PubSubKeyPushTargetDataType[] pubSubKeyPushTargets,
                                        UInteger configurationVersion, KeyValuePair[] configurationProperties) {
        super(publishedDataSets, connections, enabled);
        this.subscribedDataSets = subscribedDataSets;
        this.dataSetClasses = dataSetClasses;
        this.defaultSecurityKeyServices = defaultSecurityKeyServices;
        this.securityGroups = securityGroups;
        this.pubSubKeyPushTargets = pubSubKeyPushTargets;
        this.configurationVersion = configurationVersion;
        this.configurationProperties = configurationProperties;
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

    public StandaloneSubscribedDataSetDataType[] getSubscribedDataSets() {
        return subscribedDataSets;
    }

    public DataSetMetaDataType[] getDataSetClasses() {
        return dataSetClasses;
    }

    public EndpointDescription[] getDefaultSecurityKeyServices() {
        return defaultSecurityKeyServices;
    }

    public SecurityGroupDataType[] getSecurityGroups() {
        return securityGroups;
    }

    public PubSubKeyPushTargetDataType[] getPubSubKeyPushTargets() {
        return pubSubKeyPushTargets;
    }

    public UInteger getConfigurationVersion() {
        return configurationVersion;
    }

    public KeyValuePair[] getConfigurationProperties() {
        return configurationProperties;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23854),
            new NodeId(0, 15530),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PublishedDataSets", LocalizedText.NULL_VALUE, new NodeId(0, 15578), 1, null, UInteger.valueOf(0), false),
                new StructureField("Connections", LocalizedText.NULL_VALUE, new NodeId(0, 15617), 1, null, UInteger.valueOf(0), false),
                new StructureField("Enabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscribedDataSets", LocalizedText.NULL_VALUE, new NodeId(0, 23600), 1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetClasses", LocalizedText.NULL_VALUE, new NodeId(0, 14523), 1, null, UInteger.valueOf(0), false),
                new StructureField("DefaultSecurityKeyServices", LocalizedText.NULL_VALUE, new NodeId(0, 312), 1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityGroups", LocalizedText.NULL_VALUE, new NodeId(0, 23601), 1, null, UInteger.valueOf(0), false),
                new StructureField("PubSubKeyPushTargets", LocalizedText.NULL_VALUE, new NodeId(0, 25270), 1, null, UInteger.valueOf(0), false),
                new StructureField("ConfigurationVersion", LocalizedText.NULL_VALUE, new NodeId(0, 20998), -1, null, UInteger.valueOf(0), false),
                new StructureField("ConfigurationProperties", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubConfiguration2DataType> {
        @Override
        public Class<PubSubConfiguration2DataType> getType() {
            return PubSubConfiguration2DataType.class;
        }

        @Override
        public PubSubConfiguration2DataType decodeType(SerializationContext context,
                                                       UaDecoder decoder) {
            PublishedDataSetDataType[] publishedDataSets = (PublishedDataSetDataType[]) decoder.readStructArray("PublishedDataSets", PublishedDataSetDataType.TYPE_ID);
            PubSubConnectionDataType[] connections = (PubSubConnectionDataType[]) decoder.readStructArray("Connections", PubSubConnectionDataType.TYPE_ID);
            Boolean enabled = decoder.readBoolean("Enabled");
            StandaloneSubscribedDataSetDataType[] subscribedDataSets = (StandaloneSubscribedDataSetDataType[]) decoder.readStructArray("SubscribedDataSets", StandaloneSubscribedDataSetDataType.TYPE_ID);
            DataSetMetaDataType[] dataSetClasses = (DataSetMetaDataType[]) decoder.readStructArray("DataSetClasses", DataSetMetaDataType.TYPE_ID);
            EndpointDescription[] defaultSecurityKeyServices = (EndpointDescription[]) decoder.readStructArray("DefaultSecurityKeyServices", EndpointDescription.TYPE_ID);
            SecurityGroupDataType[] securityGroups = (SecurityGroupDataType[]) decoder.readStructArray("SecurityGroups", SecurityGroupDataType.TYPE_ID);
            PubSubKeyPushTargetDataType[] pubSubKeyPushTargets = (PubSubKeyPushTargetDataType[]) decoder.readStructArray("PubSubKeyPushTargets", PubSubKeyPushTargetDataType.TYPE_ID);
            UInteger configurationVersion = decoder.readUInt32("ConfigurationVersion");
            KeyValuePair[] configurationProperties = (KeyValuePair[]) decoder.readStructArray("ConfigurationProperties", KeyValuePair.TYPE_ID);
            return new PubSubConfiguration2DataType(publishedDataSets, connections, enabled, subscribedDataSets, dataSetClasses, defaultSecurityKeyServices, securityGroups, pubSubKeyPushTargets, configurationVersion, configurationProperties);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PubSubConfiguration2DataType value) {
            encoder.writeStructArray("PublishedDataSets", value.getPublishedDataSets(), PublishedDataSetDataType.TYPE_ID);
            encoder.writeStructArray("Connections", value.getConnections(), PubSubConnectionDataType.TYPE_ID);
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeStructArray("SubscribedDataSets", value.getSubscribedDataSets(), StandaloneSubscribedDataSetDataType.TYPE_ID);
            encoder.writeStructArray("DataSetClasses", value.getDataSetClasses(), DataSetMetaDataType.TYPE_ID);
            encoder.writeStructArray("DefaultSecurityKeyServices", value.getDefaultSecurityKeyServices(), EndpointDescription.TYPE_ID);
            encoder.writeStructArray("SecurityGroups", value.getSecurityGroups(), SecurityGroupDataType.TYPE_ID);
            encoder.writeStructArray("PubSubKeyPushTargets", value.getPubSubKeyPushTargets(), PubSubKeyPushTargetDataType.TYPE_ID);
            encoder.writeUInt32("ConfigurationVersion", value.getConfigurationVersion());
            encoder.writeStructArray("ConfigurationProperties", value.getConfigurationProperties(), KeyValuePair.TYPE_ID);
        }
    }
}
