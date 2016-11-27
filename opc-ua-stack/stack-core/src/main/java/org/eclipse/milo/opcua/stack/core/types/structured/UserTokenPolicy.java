/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.UserTokenType;

@UaDataType("UserTokenPolicy")
public class UserTokenPolicy implements UaStructure {

    public static final NodeId TypeId = Identifiers.UserTokenPolicy;
    public static final NodeId BinaryEncodingId = Identifiers.UserTokenPolicy_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UserTokenPolicy_Encoding_DefaultXml;

    protected final String _policyId;
    protected final UserTokenType _tokenType;
    protected final String _issuedTokenType;
    protected final String _issuerEndpointUrl;
    protected final String _securityPolicyUri;

    public UserTokenPolicy() {
        this._policyId = null;
        this._tokenType = null;
        this._issuedTokenType = null;
        this._issuerEndpointUrl = null;
        this._securityPolicyUri = null;
    }

    public UserTokenPolicy(String _policyId, UserTokenType _tokenType, String _issuedTokenType, String _issuerEndpointUrl, String _securityPolicyUri) {
        this._policyId = _policyId;
        this._tokenType = _tokenType;
        this._issuedTokenType = _issuedTokenType;
        this._issuerEndpointUrl = _issuerEndpointUrl;
        this._securityPolicyUri = _securityPolicyUri;
    }

    public String getPolicyId() { return _policyId; }

    public UserTokenType getTokenType() { return _tokenType; }

    public String getIssuedTokenType() { return _issuedTokenType; }

    public String getIssuerEndpointUrl() { return _issuerEndpointUrl; }

    public String getSecurityPolicyUri() { return _securityPolicyUri; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("PolicyId", _policyId)
            .add("TokenType", _tokenType)
            .add("IssuedTokenType", _issuedTokenType)
            .add("IssuerEndpointUrl", _issuerEndpointUrl)
            .add("SecurityPolicyUri", _securityPolicyUri)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<UserTokenPolicy> {
        @Override
        public UserTokenPolicy decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString();
            UserTokenType _tokenType = UserTokenType.from(reader.readInt32());
            String _issuedTokenType = reader.readString();
            String _issuerEndpointUrl = reader.readString();
            String _securityPolicyUri = reader.readString();

            return new UserTokenPolicy(_policyId, _tokenType, _issuedTokenType, _issuerEndpointUrl, _securityPolicyUri);
        }

        @Override
        public void encode(SerializationContext context, UserTokenPolicy encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeString(encodable._policyId);
            writer.writeInt32(encodable._tokenType != null ? encodable._tokenType.getValue() : 0);
            writer.writeString(encodable._issuedTokenType);
            writer.writeString(encodable._issuerEndpointUrl);
            writer.writeString(encodable._securityPolicyUri);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<UserTokenPolicy> {
        @Override
        public UserTokenPolicy decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            String _policyId = reader.readString("PolicyId");
            UserTokenType _tokenType = UserTokenType.from(reader.readInt32("TokenType"));
            String _issuedTokenType = reader.readString("IssuedTokenType");
            String _issuerEndpointUrl = reader.readString("IssuerEndpointUrl");
            String _securityPolicyUri = reader.readString("SecurityPolicyUri");

            return new UserTokenPolicy(_policyId, _tokenType, _issuedTokenType, _issuerEndpointUrl, _securityPolicyUri);
        }

        @Override
        public void encode(SerializationContext context, UserTokenPolicy encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeString("PolicyId", encodable._policyId);
            writer.writeInt32("TokenType", encodable._tokenType != null ? encodable._tokenType.getValue() : 0);
            writer.writeString("IssuedTokenType", encodable._issuedTokenType);
            writer.writeString("IssuerEndpointUrl", encodable._issuerEndpointUrl);
            writer.writeString("SecurityPolicyUri", encodable._securityPolicyUri);
        }
    }

}
