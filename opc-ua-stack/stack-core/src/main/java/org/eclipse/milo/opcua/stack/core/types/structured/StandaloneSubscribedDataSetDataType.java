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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.5">https://reference.opcfoundation.org/v105/Core/docs/Part14/6.2.10/#6.2.10.5</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class StandaloneSubscribedDataSetDataType extends SubscribedDataSetDataType implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23600");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23852");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23920");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=23988");

    private final String name;

    private final String[] dataSetFolder;

    private final DataSetMetaDataType dataSetMetaData;

    private final SubscribedDataSetDataType subscribedDataSet;

    public StandaloneSubscribedDataSetDataType(String name, String[] dataSetFolder,
                                               DataSetMetaDataType dataSetMetaData, SubscribedDataSetDataType subscribedDataSet) {
        this.name = name;
        this.dataSetFolder = dataSetFolder;
        this.dataSetMetaData = dataSetMetaData;
        this.subscribedDataSet = subscribedDataSet;
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

    public String[] getDataSetFolder() {
        return dataSetFolder;
    }

    public DataSetMetaDataType getDataSetMetaData() {
        return dataSetMetaData;
    }

    public SubscribedDataSetDataType getSubscribedDataSet() {
        return subscribedDataSet;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23852),
            new NodeId(0, 15630),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetFolder", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false),
                new StructureField("DataSetMetaData", LocalizedText.NULL_VALUE, new NodeId(0, 14523), -1, null, UInteger.valueOf(0), false),
                new StructureField("SubscribedDataSet", LocalizedText.NULL_VALUE, new NodeId(0, 15630), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<StandaloneSubscribedDataSetDataType> {
        @Override
        public Class<StandaloneSubscribedDataSetDataType> getType() {
            return StandaloneSubscribedDataSetDataType.class;
        }

        @Override
        public StandaloneSubscribedDataSetDataType decode(SerializationContext context,
                                                          UaDecoder decoder) {
            String name = decoder.readString("Name");
            String[] dataSetFolder = decoder.readStringArray("DataSetFolder");
            DataSetMetaDataType dataSetMetaData = (DataSetMetaDataType) decoder.readStruct("DataSetMetaData", DataSetMetaDataType.TYPE_ID);
            SubscribedDataSetDataType subscribedDataSet = (SubscribedDataSetDataType) decoder.readStruct("SubscribedDataSet", SubscribedDataSetDataType.TYPE_ID);
            return new StandaloneSubscribedDataSetDataType(name, dataSetFolder, dataSetMetaData, subscribedDataSet);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           StandaloneSubscribedDataSetDataType value) {
            encoder.writeString("Name", value.getName());
            encoder.writeStringArray("DataSetFolder", value.getDataSetFolder());
            encoder.writeStruct("DataSetMetaData", value.getDataSetMetaData(), DataSetMetaDataType.TYPE_ID);
            encoder.writeStruct("SubscribedDataSet", value.getSubscribedDataSet(), SubscribedDataSetDataType.TYPE_ID);
        }
    }
}
