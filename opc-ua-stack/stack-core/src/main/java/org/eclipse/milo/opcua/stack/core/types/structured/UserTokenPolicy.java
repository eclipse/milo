/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;

public class UserTokenPolicy implements UaStructure {

    public static final NodeId TypeId = Identifiers.UserTokenPolicy;
    public static final NodeId BinaryEncodingId = Identifiers.UserTokenPolicy_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UserTokenPolicy_Encoding_DefaultXml;

    protected final String policyId;
    protected final UserTokenType tokenType;
    protected final String issuedTokenType;
    protected final String issuerEndpointUrl;
    protected final String securityPolicyUri;

    public UserTokenPolicy() {
        this.policyId = null;
        this.tokenType = null;
        this.issuedTokenType = null;
        this.issuerEndpointUrl = null;
        this.securityPolicyUri = null;
    }

    public UserTokenPolicy(String policyId, UserTokenType tokenType, String issuedTokenType, String issuerEndpointUrl, String securityPolicyUri) {
        this.policyId = policyId;
        this.tokenType = tokenType;
        this.issuedTokenType = issuedTokenType;
        this.issuerEndpointUrl = issuerEndpointUrl;
        this.securityPolicyUri = securityPolicyUri;
    }

    public String getPolicyId() { return policyId; }

    public UserTokenType getTokenType() { return tokenType; }

    public String getIssuedTokenType() { return issuedTokenType; }

    public String getIssuerEndpointUrl() { return issuerEndpointUrl; }

    public String getSecurityPolicyUri() { return securityPolicyUri; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("PolicyId", policyId)
            .add("TokenType", tokenType)
            .add("IssuedTokenType", issuedTokenType)
            .add("IssuerEndpointUrl", issuerEndpointUrl)
            .add("SecurityPolicyUri", securityPolicyUri)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<UserTokenPolicy> {

        @Override
        public Class<UserTokenPolicy> getType() {
            return UserTokenPolicy.class;
        }

        @Override
        public UserTokenPolicy decode(UaDecoder decoder) throws UaSerializationException {
            String policyId = decoder.readString("PolicyId");
            UserTokenType tokenType = UserTokenType.from(decoder.readInt32("TokenType"));
            String issuedTokenType = decoder.readString("IssuedTokenType");
            String issuerEndpointUrl = decoder.readString("IssuerEndpointUrl");
            String securityPolicyUri = decoder.readString("SecurityPolicyUri");

            return new UserTokenPolicy(policyId, tokenType, issuedTokenType, issuerEndpointUrl, securityPolicyUri);
        }

        @Override
        public void encode(UserTokenPolicy value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("PolicyId", value.policyId);
            encoder.writeInt32("TokenType", value.tokenType != null ? value.tokenType.getValue() : 0);
            encoder.writeString("IssuedTokenType", value.issuedTokenType);
            encoder.writeString("IssuerEndpointUrl", value.issuerEndpointUrl);
            encoder.writeString("SecurityPolicyUri", value.securityPolicyUri);
        }
    }

}
