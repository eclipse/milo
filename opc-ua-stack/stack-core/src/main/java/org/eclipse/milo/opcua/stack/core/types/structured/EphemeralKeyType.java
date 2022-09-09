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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.15">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.15</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class EphemeralKeyType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=17548");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=17549");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=17553");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=17557");

    private final ByteString publicKey;

    private final ByteString signature;

    public EphemeralKeyType(ByteString publicKey, ByteString signature) {
        this.publicKey = publicKey;
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

    public ByteString getPublicKey() {
        return publicKey;
    }

    public ByteString getSignature() {
        return signature;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 17549),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PublicKey", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false),
                new StructureField("Signature", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EphemeralKeyType> {
        @Override
        public Class<EphemeralKeyType> getType() {
            return EphemeralKeyType.class;
        }

        @Override
        public EphemeralKeyType decodeType(SerializationContext context, UaDecoder decoder) {
            ByteString publicKey = decoder.readByteString("PublicKey");
            ByteString signature = decoder.readByteString("Signature");
            return new EphemeralKeyType(publicKey, signature);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               EphemeralKeyType value) {
            encoder.writeByteString("PublicKey", value.getPublicKey());
            encoder.writeByteString("Signature", value.getSignature());
        }
    }
}
