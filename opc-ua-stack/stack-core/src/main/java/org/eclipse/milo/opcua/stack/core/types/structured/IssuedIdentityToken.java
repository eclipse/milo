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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15/#12.3.15.2">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15/#12.3.15.2</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class IssuedIdentityToken extends UserIdentityToken implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=938");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=940");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=939");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15144");

    private final ByteString tokenData;

    private final String encryptionAlgorithm;

    public IssuedIdentityToken(String policyId, ByteString tokenData, String encryptionAlgorithm) {
        super(policyId);
        this.tokenData = tokenData;
        this.encryptionAlgorithm = encryptionAlgorithm;
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

    public ByteString getTokenData() {
        return tokenData;
    }

    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 940),
            new NodeId(0, 316),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PolicyId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("TokenData", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false),
                new StructureField("EncryptionAlgorithm", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<IssuedIdentityToken> {
        @Override
        public Class<IssuedIdentityToken> getType() {
            return IssuedIdentityToken.class;
        }

        @Override
        public IssuedIdentityToken decodeType(SerializationContext context, UaDecoder decoder) {
            String policyId = decoder.readString("PolicyId");
            ByteString tokenData = decoder.readByteString("TokenData");
            String encryptionAlgorithm = decoder.readString("EncryptionAlgorithm");
            return new IssuedIdentityToken(policyId, tokenData, encryptionAlgorithm);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               IssuedIdentityToken value) {
            encoder.writeString("PolicyId", value.getPolicyId());
            encoder.writeByteString("TokenData", value.getTokenData());
            encoder.writeString("EncryptionAlgorithm", value.getEncryptionAlgorithm());
        }
    }
}
