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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.21">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.21</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class KeyValuePair extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=14533");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=14846");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=14802");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15041");

    private final QualifiedName key;

    private final Variant value;

    public KeyValuePair(QualifiedName key, Variant value) {
        this.key = key;
        this.value = value;
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

    public QualifiedName getKey() {
        return key;
    }

    public Variant getValue() {
        return value;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 14846),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Key", LocalizedText.NULL_VALUE, new NodeId(0, 20), -1, null, UInteger.valueOf(0), false),
                new StructureField("Value", LocalizedText.NULL_VALUE, new NodeId(0, 24), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<KeyValuePair> {
        @Override
        public Class<KeyValuePair> getType() {
            return KeyValuePair.class;
        }

        @Override
        public KeyValuePair decodeType(SerializationContext context, UaDecoder decoder) {
            QualifiedName key = decoder.readQualifiedName("Key");
            Variant value = decoder.readVariant("Value");
            return new KeyValuePair(key, value);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, KeyValuePair value) {
            encoder.writeQualifiedName("Key", value.getKey());
            encoder.writeVariant("Value", value.getValue());
        }
    }
}
