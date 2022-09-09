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
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.42">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.42</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class UserTokenPolicy extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=304");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=306");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=305");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15098");

    private final String policyId;

    private final UserTokenType tokenType;

    private final String issuedTokenType;

    private final String issuerEndpointUrl;

    private final String securityPolicyUri;

    public UserTokenPolicy(String policyId, UserTokenType tokenType, String issuedTokenType,
                           String issuerEndpointUrl, String securityPolicyUri) {
        this.policyId = policyId;
        this.tokenType = tokenType;
        this.issuedTokenType = issuedTokenType;
        this.issuerEndpointUrl = issuerEndpointUrl;
        this.securityPolicyUri = securityPolicyUri;
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

    public String getPolicyId() {
        return policyId;
    }

    public UserTokenType getTokenType() {
        return tokenType;
    }

    public String getIssuedTokenType() {
        return issuedTokenType;
    }

    public String getIssuerEndpointUrl() {
        return issuerEndpointUrl;
    }

    public String getSecurityPolicyUri() {
        return securityPolicyUri;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 306),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("PolicyId", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("TokenType", LocalizedText.NULL_VALUE, new NodeId(0, 303), -1, null, UInteger.valueOf(0), false),
                new StructureField("IssuedTokenType", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("IssuerEndpointUrl", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityPolicyUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<UserTokenPolicy> {
        @Override
        public Class<UserTokenPolicy> getType() {
            return UserTokenPolicy.class;
        }

        @Override
        public UserTokenPolicy decodeType(SerializationContext context, UaDecoder decoder) {
            String policyId = decoder.readString("PolicyId");
            UserTokenType tokenType = (UserTokenType) decoder.readEnum("TokenType", UserTokenType.class);
            String issuedTokenType = decoder.readString("IssuedTokenType");
            String issuerEndpointUrl = decoder.readString("IssuerEndpointUrl");
            String securityPolicyUri = decoder.readString("SecurityPolicyUri");
            return new UserTokenPolicy(policyId, tokenType, issuedTokenType, issuerEndpointUrl, securityPolicyUri);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder, UserTokenPolicy value) {
            encoder.writeString("PolicyId", value.getPolicyId());
            encoder.writeEnum("TokenType", value.getTokenType());
            encoder.writeString("IssuedTokenType", value.getIssuedTokenType());
            encoder.writeString("IssuerEndpointUrl", value.getIssuerEndpointUrl());
            encoder.writeString("SecurityPolicyUri", value.getSecurityPolicyUri());
        }
    }
}
