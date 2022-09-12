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
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.7">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.7</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class DeleteReferencesItem extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=385");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=387");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=386");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15175");

    private final NodeId sourceNodeId;

    private final NodeId referenceTypeId;

    private final Boolean isForward;

    private final ExpandedNodeId targetNodeId;

    private final Boolean deleteBidirectional;

    public DeleteReferencesItem(NodeId sourceNodeId, NodeId referenceTypeId, Boolean isForward,
                                ExpandedNodeId targetNodeId, Boolean deleteBidirectional) {
        this.sourceNodeId = sourceNodeId;
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.targetNodeId = targetNodeId;
        this.deleteBidirectional = deleteBidirectional;
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

    public NodeId getSourceNodeId() {
        return sourceNodeId;
    }

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public Boolean getIsForward() {
        return isForward;
    }

    public ExpandedNodeId getTargetNodeId() {
        return targetNodeId;
    }

    public Boolean getDeleteBidirectional() {
        return deleteBidirectional;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 387),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("SourceNodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReferenceTypeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("IsForward", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("TargetNodeId", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false),
                new StructureField("DeleteBidirectional", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteReferencesItem> {
        @Override
        public Class<DeleteReferencesItem> getType() {
            return DeleteReferencesItem.class;
        }

        @Override
        public DeleteReferencesItem decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId sourceNodeId = decoder.readNodeId("SourceNodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            ExpandedNodeId targetNodeId = decoder.readExpandedNodeId("TargetNodeId");
            Boolean deleteBidirectional = decoder.readBoolean("DeleteBidirectional");
            return new DeleteReferencesItem(sourceNodeId, referenceTypeId, isForward, targetNodeId, deleteBidirectional);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               DeleteReferencesItem value) {
            encoder.writeNodeId("SourceNodeId", value.getSourceNodeId());
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeBoolean("IsForward", value.getIsForward());
            encoder.writeExpandedNodeId("TargetNodeId", value.getTargetNodeId());
            encoder.writeBoolean("DeleteBidirectional", value.getDeleteBidirectional());
        }
    }
}
