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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.4/#6.2.4.5.1">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.4/#6.2.4.5.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DataSetWriterDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=15597");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=15682");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=15955");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=16156");

    private final String name;

    private final Boolean enabled;

    private final UShort dataSetWriterId;

    private final DataSetFieldContentMask dataSetFieldContentMask;

    private final UInteger keyFrameCount;

    private final String dataSetName;

    private final KeyValuePair[] dataSetWriterProperties;

    private final DataSetWriterTransportDataType transportSettings;

    private final DataSetWriterMessageDataType messageSettings;

    public DataSetWriterDataType(String name, Boolean enabled, UShort dataSetWriterId,
                                 DataSetFieldContentMask dataSetFieldContentMask, UInteger keyFrameCount, String dataSetName,
                                 KeyValuePair[] dataSetWriterProperties, DataSetWriterTransportDataType transportSettings,
                                 DataSetWriterMessageDataType messageSettings) {
        this.name = name;
        this.enabled = enabled;
        this.dataSetWriterId = dataSetWriterId;
        this.dataSetFieldContentMask = dataSetFieldContentMask;
        this.keyFrameCount = keyFrameCount;
        this.dataSetName = dataSetName;
        this.dataSetWriterProperties = dataSetWriterProperties;
        this.transportSettings = transportSettings;
        this.messageSettings = messageSettings;
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

    public UShort getDataSetWriterId() {
        return dataSetWriterId;
    }

    public DataSetFieldContentMask getDataSetFieldContentMask() {
        return dataSetFieldContentMask;
    }

    public UInteger getKeyFrameCount() {
        return keyFrameCount;
    }

    public String getDataSetName() {
        return dataSetName;
    }

    public KeyValuePair[] getDataSetWriterProperties() {
        return dataSetWriterProperties;
    }

    public DataSetWriterTransportDataType getTransportSettings() {
        return transportSettings;
    }

    public DataSetWriterMessageDataType getMessageSettings() {
        return messageSettings;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 15682),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Enabled", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetWriterId", LocalizedText.NULL_VALUE, new NodeId(0, 5), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetFieldContentMask", LocalizedText.NULL_VALUE, new NodeId(0, 15583), -1, null, UInteger.valueOf(0), false),
                new StructureField("KeyFrameCount", LocalizedText.NULL_VALUE, new NodeId(0, 7), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetWriterProperties", LocalizedText.NULL_VALUE, new NodeId(0, 14533), 1, null, UInteger.valueOf(0), false),
                new StructureField("TransportSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15598), -1, null, UInteger.valueOf(0), false),
                new StructureField("MessageSettings", LocalizedText.NULL_VALUE, new NodeId(0, 15605), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DataSetWriterDataType> {
        @Override
        public Class<DataSetWriterDataType> getType() {
            return DataSetWriterDataType.class;
        }

        @Override
        public DataSetWriterDataType decodeType(SerializationContext context, UaDecoder decoder) {
            String name = decoder.readString("Name");
            Boolean enabled = decoder.readBoolean("Enabled");
            UShort dataSetWriterId = decoder.readUInt16("DataSetWriterId");
            DataSetFieldContentMask dataSetFieldContentMask = new DataSetFieldContentMask(decoder.readUInt32("DataSetFieldContentMask"));
            UInteger keyFrameCount = decoder.readUInt32("KeyFrameCount");
            String dataSetName = decoder.readString("DataSetName");
            KeyValuePair[] dataSetWriterProperties = (KeyValuePair[]) decoder.readStructArray("DataSetWriterProperties", KeyValuePair.TYPE_ID);
            DataSetWriterTransportDataType transportSettings = (DataSetWriterTransportDataType) decoder.readStruct("TransportSettings", DataSetWriterTransportDataType.TYPE_ID);
            DataSetWriterMessageDataType messageSettings = (DataSetWriterMessageDataType) decoder.readStruct("MessageSettings", DataSetWriterMessageDataType.TYPE_ID);
            return new DataSetWriterDataType(name, enabled, dataSetWriterId, dataSetFieldContentMask, keyFrameCount, dataSetName, dataSetWriterProperties, transportSettings, messageSettings);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               DataSetWriterDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeBoolean("Enabled", value.getEnabled());
            encoder.writeUInt16("DataSetWriterId", value.getDataSetWriterId());
            encoder.writeUInt32("DataSetFieldContentMask", value.getDataSetFieldContentMask().getValue());
            encoder.writeUInt32("KeyFrameCount", value.getKeyFrameCount());
            encoder.writeString("DataSetName", value.getDataSetName());
            encoder.writeStructArray("DataSetWriterProperties", value.getDataSetWriterProperties(), KeyValuePair.TYPE_ID);
            encoder.writeStruct("TransportSettings", value.getTransportSettings(), DataSetWriterTransportDataType.TYPE_ID);
            encoder.writeStruct("MessageSettings", value.getMessageSettings(), DataSetWriterMessageDataType.TYPE_ID);
        }
    }
}
