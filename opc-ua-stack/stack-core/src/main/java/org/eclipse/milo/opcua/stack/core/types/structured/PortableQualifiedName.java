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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.37">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.37</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class PortableQualifiedName extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=24105");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=24108");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=24120");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=24132");

    private final String namespaceUri;

    private final String name;

    public PortableQualifiedName(String namespaceUri, String name) {
        this.namespaceUri = namespaceUri;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 24108),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("NamespaceUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Name", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<PortableQualifiedName> {
        @Override
        public Class<PortableQualifiedName> getType() {
            return PortableQualifiedName.class;
        }

        @Override
        public PortableQualifiedName decodeType(SerializationContext context, UaDecoder decoder) {
            String namespaceUri = decoder.readString("NamespaceUri");
            String name = decoder.readString("Name");
            return new PortableQualifiedName(namespaceUri, name);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               PortableQualifiedName value) {
            encoder.writeString("NamespaceUri", value.getNamespaceUri());
            encoder.writeString("Name", value.getName());
        }
    }
}
