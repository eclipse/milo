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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.38">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.38</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PortableNodeId extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=24106");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=24109");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=24121");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=24133");

    private final String namespaceUri;

    private final NodeId identifier;

    public PortableNodeId(String namespaceUri, NodeId identifier) {
        this.namespaceUri = namespaceUri;
        this.identifier = identifier;
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

    public String getNamespaceUri() {
        return namespaceUri;
    }

    public NodeId getIdentifier() {
        return identifier;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 24109),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NamespaceUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Identifier", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PortableNodeId> {
        @Override
        public Class<PortableNodeId> getType() {
            return PortableNodeId.class;
        }

        @Override
        public PortableNodeId decode(SerializationContext context, UaDecoder decoder) {
            String namespaceUri = decoder.readString("NamespaceUri");
            NodeId identifier = decoder.readNodeId("Identifier");
            return new PortableNodeId(namespaceUri, identifier);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, PortableNodeId value) {
            encoder.writeString("NamespaceUri", value.getNamespaceUri());
            encoder.writeNodeId("Identifier", value.getIdentifier());
        }
    }
}
