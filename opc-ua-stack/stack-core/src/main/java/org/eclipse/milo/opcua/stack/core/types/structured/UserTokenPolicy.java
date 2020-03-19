/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class UserTokenPolicy extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=304");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=305");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=306");

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
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
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

    public static final class Codec extends GenericDataTypeCodec<UserTokenPolicy> {
        @Override
        public Class<UserTokenPolicy> getType() {
            return UserTokenPolicy.class;
        }

        @Override
        public UserTokenPolicy decode(SerializationContext context, UaDecoder decoder) {
            String policyId = decoder.readString("PolicyId");
            UserTokenType tokenType = decoder.readEnum("TokenType", UserTokenType.class);
            String issuedTokenType = decoder.readString("IssuedTokenType");
            String issuerEndpointUrl = decoder.readString("IssuerEndpointUrl");
            String securityPolicyUri = decoder.readString("SecurityPolicyUri");
            return new UserTokenPolicy(policyId, tokenType, issuedTokenType, issuerEndpointUrl, securityPolicyUri);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, UserTokenPolicy value) {
            encoder.writeString("PolicyId", value.getPolicyId());
            encoder.writeEnum("TokenType", value.getTokenType());
            encoder.writeString("IssuedTokenType", value.getIssuedTokenType());
            encoder.writeString("IssuerEndpointUrl", value.getIssuerEndpointUrl());
            encoder.writeString("SecurityPolicyUri", value.getSecurityPolicyUri());
        }
    }
}
