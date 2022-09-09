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
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.13">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.13</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class SignedSoftwareCertificate extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=344");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=346");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=345");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15136");

    private final ByteString certificateData;

    private final ByteString signature;

    public SignedSoftwareCertificate(ByteString certificateData, ByteString signature) {
        this.certificateData = certificateData;
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

    public ByteString getCertificateData() {
        return certificateData;
    }

    public ByteString getSignature() {
        return signature;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 346),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("CertificateData", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false),
                new StructureField("Signature", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SignedSoftwareCertificate> {
        @Override
        public Class<SignedSoftwareCertificate> getType() {
            return SignedSoftwareCertificate.class;
        }

        @Override
        public SignedSoftwareCertificate decodeType(SerializationContext context, UaDecoder decoder) {
            ByteString certificateData = decoder.readByteString("CertificateData");
            ByteString signature = decoder.readByteString("Signature");
            return new SignedSoftwareCertificate(certificateData, signature);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SignedSoftwareCertificate value) {
            encoder.writeByteString("CertificateData", value.getCertificateData());
            encoder.writeByteString("Signature", value.getSignature());
        }
    }
}
