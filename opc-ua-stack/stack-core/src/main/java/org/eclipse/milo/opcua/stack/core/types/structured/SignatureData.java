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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.37">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.37</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class SignatureData extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=456");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=458");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=457");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15137");

    private final String algorithm;

    private final ByteString signature;

    public SignatureData(String algorithm, ByteString signature) {
        this.algorithm = algorithm;
        this.signature = signature;
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

    public String getAlgorithm() {
        return algorithm;
    }

    public ByteString getSignature() {
        return signature;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 458),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("Algorithm", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Signature", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SignatureData> {
        @Override
        public Class<SignatureData> getType() {
            return SignatureData.class;
        }

        @Override
        public SignatureData decodeType(SerializationContext context, UaDecoder decoder) {
            String algorithm = decoder.readString("Algorithm");
            ByteString signature = decoder.readByteString("Signature");
            return new SignatureData(algorithm, signature);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, SignatureData value) {
            encoder.writeString("Algorithm", value.getAlgorithm());
            encoder.writeByteString("Signature", value.getSignature());
        }
    }
}
