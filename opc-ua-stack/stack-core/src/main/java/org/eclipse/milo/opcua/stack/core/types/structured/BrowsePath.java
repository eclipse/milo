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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part3/6.2.5">https://reference.opcfoundation.org/v105/Core/docs/Part3/6.2.5</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class BrowsePath extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=543");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=545");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=544");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15190");

    private final NodeId startingNode;

    private final RelativePath relativePath;

    public BrowsePath(NodeId startingNode, RelativePath relativePath) {
        this.startingNode = startingNode;
        this.relativePath = relativePath;
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

    public NodeId getStartingNode() {
        return startingNode;
    }

    public RelativePath getRelativePath() {
        return relativePath;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 545),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("StartingNode", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("RelativePath", LocalizedText.NULL_VALUE, new NodeId(0, 540), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<BrowsePath> {
        @Override
        public Class<BrowsePath> getType() {
            return BrowsePath.class;
        }

        @Override
        public BrowsePath decode(SerializationContext context, UaDecoder decoder) {
            NodeId startingNode = decoder.readNodeId("StartingNode");
            RelativePath relativePath = (RelativePath) decoder.readStruct("RelativePath", RelativePath.TYPE_ID);
            return new BrowsePath(startingNode, relativePath);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowsePath value) {
            encoder.writeNodeId("StartingNode", value.getStartingNode());
            encoder.writeStruct("RelativePath", value.getRelativePath(), RelativePath.TYPE_ID);
        }
    }
}
