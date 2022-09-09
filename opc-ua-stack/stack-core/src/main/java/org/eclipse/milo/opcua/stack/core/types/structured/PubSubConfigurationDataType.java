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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.12/#6.2.12.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.12/#6.2.12.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PubSubConfigurationDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15530");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=21154");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=21178");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=21202");

    private final PublishedDataSetDataType[] publishedDataSets;

    private final PubSubConnectionDataType[] connections;

    private final Boolean enabled;

    public PubSubConfigurationDataType(PublishedDataSetDataType[] publishedDataSets,
                                       PubSubConnectionDataType[] connections, Boolean enabled) {
        this.publishedDataSets = publishedDataSets;
        this.connections = connections;
        this.enabled = enabled;
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

    public PublishedDataSetDataType[] getPublishedDataSets() {
        return publishedDataSets;
    }

    public PubSubConnectionDataType[] getConnections() {
        return connections;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 21154),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PublishedDataSets", LocalizedText.NULL_VALUE, new NodeId(0, 15578), 1, null, UInteger.valueOf(0), false),
                new StructureField("Connections", LocalizedText.NULL_VALUE, new NodeId(0, 15617), 1, null, UInteger.valueOf(0), false),
                new StructureField("Enabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PubSubConfigurationDataType> {
        @Override
        public Class<PubSubConfigurationDataType> getType() {
            return PubSubConfigurationDataType.class;
        }

        @Override
        public PubSubConfigurationDataType decodeType(SerializationContext context, UaDecoder decoder) {
            PublishedDataSetDataType[] publishedDataSets = (PublishedDataSetDataType[]) decoder.readStructArray("PublishedDataSets", PublishedDataSetDataType.TYPE_ID);
            PubSubConnectionDataType[] connections = (PubSubConnectionDataType[]) decoder.readStructArray("Connections", PubSubConnectionDataType.TYPE_ID);
            Boolean enabled = decoder.readBoolean("Enabled");
            return new PubSubConfigurationDataType(publishedDataSets, connections, enabled);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PubSubConfigurationDataType value) {
            encoder.writeStructArray("PublishedDataSets", value.getPublishedDataSets(), PublishedDataSetDataType.TYPE_ID);
            encoder.writeStructArray("Connections", value.getConnections(), PubSubConnectionDataType.TYPE_ID);
            encoder.writeBoolean("Enabled", value.getEnabled());
        }
    }
}
