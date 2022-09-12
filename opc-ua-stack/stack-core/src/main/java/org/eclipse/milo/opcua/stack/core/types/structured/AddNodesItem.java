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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.1">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.1</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class AddNodesItem extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=376");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=378");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=377");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15165");

    private final ExpandedNodeId parentNodeId;

    private final NodeId referenceTypeId;

    private final ExpandedNodeId requestedNewNodeId;

    private final QualifiedName browseName;

    private final NodeClass nodeClass;

    private final ExtensionObject nodeAttributes;

    private final ExpandedNodeId typeDefinition;

    public AddNodesItem(ExpandedNodeId parentNodeId, NodeId referenceTypeId,
                        ExpandedNodeId requestedNewNodeId, QualifiedName browseName, NodeClass nodeClass,
                        ExtensionObject nodeAttributes, ExpandedNodeId typeDefinition) {
        this.parentNodeId = parentNodeId;
        this.referenceTypeId = referenceTypeId;
        this.requestedNewNodeId = requestedNewNodeId;
        this.browseName = browseName;
        this.nodeClass = nodeClass;
        this.nodeAttributes = nodeAttributes;
        this.typeDefinition = typeDefinition;
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

    public ExpandedNodeId getParentNodeId() {
        return parentNodeId;
    }

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public ExpandedNodeId getRequestedNewNodeId() {
        return requestedNewNodeId;
    }

    public QualifiedName getBrowseName() {
        return browseName;
    }

    public NodeClass getNodeClass() {
        return nodeClass;
    }

    public ExtensionObject getNodeAttributes() {
        return nodeAttributes;
    }

    public ExpandedNodeId getTypeDefinition() {
        return typeDefinition;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 378),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("ParentNodeId", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReferenceTypeId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("RequestedNewNodeId", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false),
                new StructureField("BrowseName", LocalizedText.NULL_VALUE, new NodeId(0, 20), -1, null, UInteger.valueOf(0), false),
                new StructureField("NodeClass", LocalizedText.NULL_VALUE, new NodeId(0, 257), -1, null, UInteger.valueOf(0), false),
                new StructureField("NodeAttributes", LocalizedText.NULL_VALUE, new NodeId(0, 22), -1, null, UInteger.valueOf(0), false),
                new StructureField("TypeDefinition", LocalizedText.NULL_VALUE, new NodeId(0, 18), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AddNodesItem> {
        @Override
        public Class<AddNodesItem> getType() {
            return AddNodesItem.class;
        }

        @Override
        public AddNodesItem decodeType(SerializationContext context, UaDecoder decoder) {
            ExpandedNodeId parentNodeId = decoder.readExpandedNodeId("ParentNodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            ExpandedNodeId requestedNewNodeId = decoder.readExpandedNodeId("RequestedNewNodeId");
            QualifiedName browseName = decoder.readQualifiedName("BrowseName");
            NodeClass nodeClass = NodeClass.from(decoder.readEnum("NodeClass"));
            ExtensionObject nodeAttributes = decoder.readExtensionObject("NodeAttributes");
            ExpandedNodeId typeDefinition = decoder.readExpandedNodeId("TypeDefinition");
            return new AddNodesItem(parentNodeId, referenceTypeId, requestedNewNodeId, browseName, nodeClass, nodeAttributes, typeDefinition);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, AddNodesItem value) {
            encoder.writeExpandedNodeId("ParentNodeId", value.getParentNodeId());
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeExpandedNodeId("RequestedNewNodeId", value.getRequestedNewNodeId());
            encoder.writeQualifiedName("BrowseName", value.getBrowseName());
            encoder.writeEnum("NodeClass", value.getNodeClass());
            encoder.writeExtensionObject("NodeAttributes", value.getNodeAttributes());
            encoder.writeExpandedNodeId("TypeDefinition", value.getTypeDefinition());
        }
    }
}
