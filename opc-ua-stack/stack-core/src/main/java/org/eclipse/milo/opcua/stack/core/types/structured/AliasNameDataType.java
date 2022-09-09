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
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part17/7.2">https://reference.opcfoundation.org/v105/Core/docs/Part17/7.2</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class AliasNameDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=23468");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=23499");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=23505");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=23511");

    private final QualifiedName aliasName;

    private final ExpandedNodeId[] referencedNodes;

    public AliasNameDataType(QualifiedName aliasName, ExpandedNodeId[] referencedNodes) {
        this.aliasName = aliasName;
        this.referencedNodes = referencedNodes;
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

    public QualifiedName getAliasName() {
        return aliasName;
    }

    public ExpandedNodeId[] getReferencedNodes() {
        return referencedNodes;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 23499),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("AliasName", LocalizedText.NULL_VALUE, new NodeId(0, 20), -1, null, UInteger.valueOf(0), false),
                new StructureField("ReferencedNodes", LocalizedText.NULL_VALUE, new NodeId(0, 18), 1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<AliasNameDataType> {
        @Override
        public Class<AliasNameDataType> getType() {
            return AliasNameDataType.class;
        }

        @Override
        public AliasNameDataType decodeType(SerializationContext context, UaDecoder decoder) {
            QualifiedName aliasName = decoder.readQualifiedName("AliasName");
            ExpandedNodeId[] referencedNodes = decoder.readExpandedNodeIdArray("ReferencedNodes");
            return new AliasNameDataType(aliasName, referencedNodes);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               AliasNameDataType value) {
            encoder.writeQualifiedName("AliasName", value.getAliasName());
            encoder.writeExpandedNodeIdArray("ReferencedNodes", value.getReferencedNodes());
        }
    }
}
