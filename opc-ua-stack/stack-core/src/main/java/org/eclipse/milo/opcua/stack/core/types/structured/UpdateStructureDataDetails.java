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
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v104/Core/docs/Part11/6.8.3/#6.8.3.1">https://reference.opcfoundation.org/v104/Core/docs/Part11/6.8.3/#6.8.3.1</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class UpdateStructureDataDetails extends HistoryUpdateDetails implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=11295");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=11300");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=11296");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15281");

    private final PerformUpdateType performInsertReplace;

    private final DataValue[] updateValues;

    public UpdateStructureDataDetails(NodeId nodeId, PerformUpdateType performInsertReplace,
                                      DataValue[] updateValues) {
        super(nodeId);
        this.performInsertReplace = performInsertReplace;
        this.updateValues = updateValues;
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

    public PerformUpdateType getPerformInsertReplace() {
        return performInsertReplace;
    }

    public DataValue[] getUpdateValues() {
        return updateValues;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 11300),
            new NodeId(0, 677),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("PerformInsertReplace", LocalizedText.NULL_VALUE, new NodeId(0, 11293), -1, null, UInteger.valueOf(0), false),
                new StructureField("UpdateValues", LocalizedText.NULL_VALUE, new NodeId(0, 23), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<UpdateStructureDataDetails> {
        @Override
        public Class<UpdateStructureDataDetails> getType() {
            return UpdateStructureDataDetails.class;
        }

        @Override
        public UpdateStructureDataDetails decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            PerformUpdateType performInsertReplace = (PerformUpdateType) decoder.readEnum("PerformInsertReplace", PerformUpdateType.class);
            DataValue[] updateValues = decoder.readDataValueArray("UpdateValues");
            return new UpdateStructureDataDetails(nodeId, performInsertReplace, updateValues);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               UpdateStructureDataDetails value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeEnum("PerformInsertReplace", value.getPerformInsertReplace());
            encoder.writeDataValueArray("UpdateValues", value.getUpdateValues());
        }
    }
}
