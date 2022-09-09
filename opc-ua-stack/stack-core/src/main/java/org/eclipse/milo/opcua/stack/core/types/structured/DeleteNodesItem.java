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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.6">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.6</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DeleteNodesItem extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=382");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=384");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=383");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15172");

    private final NodeId nodeId;

    private final Boolean deleteTargetReferences;

    public DeleteNodesItem(NodeId nodeId, Boolean deleteTargetReferences) {
        this.nodeId = nodeId;
        this.deleteTargetReferences = deleteTargetReferences;
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

    public NodeId getNodeId() {
        return nodeId;
    }

    public Boolean getDeleteTargetReferences() {
        return deleteTargetReferences;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 384),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("DeleteTargetReferences", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteNodesItem> {
        @Override
        public Class<DeleteNodesItem> getType() {
            return DeleteNodesItem.class;
        }

        @Override
        public DeleteNodesItem decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            Boolean deleteTargetReferences = decoder.readBoolean("DeleteTargetReferences");
            return new DeleteNodesItem(nodeId, deleteTargetReferences);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, DeleteNodesItem value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeBoolean("DeleteTargetReferences", value.getDeleteTargetReferences());
        }
    }
}
