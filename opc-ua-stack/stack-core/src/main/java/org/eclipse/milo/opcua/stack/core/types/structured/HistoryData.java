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
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.5.2">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.5.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class HistoryData extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=656");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=658");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=657");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15270");

    private final DataValue[] dataValues;

    public HistoryData(DataValue[] dataValues) {
        this.dataValues = dataValues;
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

    public DataValue[] getDataValues() {
        return dataValues;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 658),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("DataValues", LocalizedText.NULL_VALUE, new NodeId(0, 23), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryData> {
        @Override
        public Class<HistoryData> getType() {
            return HistoryData.class;
        }

        @Override
        public HistoryData decode(SerializationContext context, UaDecoder decoder) {
            DataValue[] dataValues = decoder.readDataValueArray("DataValues");
            return new HistoryData(dataValues);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, HistoryData value) {
            encoder.writeDataValueArray("DataValues", value.getDataValues());
        }
    }
}
