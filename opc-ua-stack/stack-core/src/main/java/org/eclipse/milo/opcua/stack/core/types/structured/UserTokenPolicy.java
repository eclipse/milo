/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.NamespaceTable;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
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
public class UserTokenPolicy extends Structure implements UaStructuredType {
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
        public UserTokenPolicy decodeType(EncodingContext context, UaDecoder decoder) {
            String policyId = decoder.decodeString("PolicyId");
            UserTokenType tokenType = UserTokenType.from(decoder.decodeEnum("TokenType"));
            String issuedTokenType = decoder.decodeString("IssuedTokenType");
            String issuerEndpointUrl = decoder.decodeString("IssuerEndpointUrl");
            String securityPolicyUri = decoder.decodeString("SecurityPolicyUri");
            return new UserTokenPolicy(policyId, tokenType, issuedTokenType, issuerEndpointUrl, securityPolicyUri);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder, UserTokenPolicy value) {
            encoder.encodeString("PolicyId", value.getPolicyId());
            encoder.encodeEnum("TokenType", value.getTokenType());
            encoder.encodeString("IssuedTokenType", value.getIssuedTokenType());
            encoder.encodeString("IssuerEndpointUrl", value.getIssuerEndpointUrl());
            encoder.encodeString("SecurityPolicyUri", value.getSecurityPolicyUri());
        }
    }
}
