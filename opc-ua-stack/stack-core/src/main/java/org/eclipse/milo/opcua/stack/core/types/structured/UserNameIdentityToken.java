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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15/#12.3.15.3">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.3.15/#12.3.15.3</a>
 */
@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder
@ToString
public class UserNameIdentityToken extends UserIdentityToken implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=322");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=324");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=323");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15142");

    private final String userName;

    private final ByteString password;

    private final String encryptionAlgorithm;

    public UserNameIdentityToken(String policyId, String userName, ByteString password,
                                 String encryptionAlgorithm) {
        super(policyId);
        this.userName = userName;
        this.password = password;
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

    public String getUserName() {
        return userName;
    }

    public ByteString getPassword() {
        return password;
    }

    public String getEncryptionAlgorithm() {
        return encryptionAlgorithm;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 324),
            new NodeId(0, 316),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PolicyId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("UserName", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Password", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false),
                new StructureField("EncryptionAlgorithm", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<UserNameIdentityToken> {
        @Override
        public Class<UserNameIdentityToken> getType() {
            return UserNameIdentityToken.class;
        }

        @Override
        public UserNameIdentityToken decodeType(SerializationContext context, UaDecoder decoder) {
            String policyId = decoder.readString("PolicyId");
            String userName = decoder.readString("UserName");
            ByteString password = decoder.readByteString("Password");
            String encryptionAlgorithm = decoder.readString("EncryptionAlgorithm");
            return new UserNameIdentityToken(policyId, userName, password, encryptionAlgorithm);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               UserNameIdentityToken value) {
            encoder.writeString("PolicyId", value.getPolicyId());
            encoder.writeString("UserName", value.getUserName());
            encoder.writeByteString("Password", value.getPassword());
            encoder.writeString("EncryptionAlgorithm", value.getEncryptionAlgorithm());
        }
    }
}
