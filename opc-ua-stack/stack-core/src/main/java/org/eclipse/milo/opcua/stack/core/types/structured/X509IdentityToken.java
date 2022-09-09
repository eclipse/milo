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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15/#12.3.15.4">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15/#12.3.15.4</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class X509IdentityToken extends UserIdentityToken implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=325");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=327");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=326");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15143");

    private final ByteString certificateData;

    public X509IdentityToken(String policyId, ByteString certificateData) {
        super(policyId);
        this.certificateData = certificateData;
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

    public ByteString getCertificateData() {
        return certificateData;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 327),
            new NodeId(0, 316),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PolicyId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("CertificateData", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<X509IdentityToken> {
        @Override
        public Class<X509IdentityToken> getType() {
            return X509IdentityToken.class;
        }

        @Override
        public X509IdentityToken decodeType(SerializationContext context, UaDecoder decoder) {
            String policyId = decoder.readString("PolicyId");
            ByteString certificateData = decoder.readByteString("CertificateData");
            return new X509IdentityToken(policyId, certificateData);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               X509IdentityToken value) {
            encoder.writeString("PolicyId", value.getPolicyId());
            encoder.writeByteString("CertificateData", value.getCertificateData());
        }
    }
}
