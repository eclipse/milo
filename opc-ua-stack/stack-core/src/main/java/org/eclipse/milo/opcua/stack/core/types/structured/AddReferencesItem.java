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
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.2">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class AddReferencesItem extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=379");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=381");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=380");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15169");

    private final NodeId sourceNodeId;

    private final NodeId referenceTypeId;

    private final Boolean isForward;

    private final String targetServerUri;

    private final ExpandedNodeId targetNodeId;

    private final NodeClass targetNodeClass;

    public AddReferencesItem(NodeId sourceNodeId, NodeId referenceTypeId, Boolean isForward,
                             String targetServerUri, ExpandedNodeId targetNodeId, NodeClass targetNodeClass) {
        this.sourceNodeId = sourceNodeId;
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.targetServerUri = targetServerUri;
        this.targetNodeId = targetNodeId;
        this.targetNodeClass = targetNodeClass;
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

    public String getTargetServerUri() {
        return targetServerUri;
    }

    public ExpandedNodeId getTargetNodeId() {
        return targetNodeId;
    }

    public NodeClass getTargetNodeClass() {
        return targetNodeClass;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 381),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("SourceNodeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReferenceTypeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("IsForward", LocalizedText.NULL_VALUE, new NodeId(0, 1), -1, null, UInteger.valueOf(0), false),
                new StructureField("TargetServerUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("TargetNodeId", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false),
                new StructureField("TargetNodeClass", LocalizedText.NULL_VALUE, new NodeId(0, 257), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AddReferencesItem> {
        @Override
        public Class<AddReferencesItem> getType() {
            return AddReferencesItem.class;
        }

        @Override
        public AddReferencesItem decodeType(SerializationContext context, UaDecoder decoder) {
            NodeId sourceNodeId = decoder.readNodeId("SourceNodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            String targetServerUri = decoder.readString("TargetServerUri");
            ExpandedNodeId targetNodeId = decoder.readExpandedNodeId("TargetNodeId");
            NodeClass targetNodeClass = (NodeClass) decoder.readEnum("TargetNodeClass", NodeClass.class);
            return new AddReferencesItem(sourceNodeId, referenceTypeId, isForward, targetServerUri, targetNodeId, targetNodeClass);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               AddReferencesItem value) {
            encoder.writeNodeId("SourceNodeId", value.getSourceNodeId());
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeBoolean("IsForward", value.getIsForward());
            encoder.writeString("TargetServerUri", value.getTargetServerUri());
            encoder.writeExpandedNodeId("TargetNodeId", value.getTargetNodeId());
            encoder.writeEnum("TargetNodeClass", value.getTargetNodeClass());
        }
    }
}
