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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

public class EndpointDescription implements UaStructure {

    public static final NodeId TypeId = Identifiers.EndpointDescription;
    public static final NodeId BinaryEncodingId = Identifiers.EndpointDescription_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EndpointDescription_Encoding_DefaultXml;

    protected final String endpointUrl;
    protected final ApplicationDescription server;
    protected final ByteString serverCertificate;
    protected final MessageSecurityMode securityMode;
    protected final String securityPolicyUri;
    protected final UserTokenPolicy[] userIdentityTokens;
    protected final String transportProfileUri;
    protected final UByte securityLevel;

    public EndpointDescription() {
        this.endpointUrl = null;
        this.server = null;
        this.serverCertificate = null;
        this.securityMode = null;
        this.securityPolicyUri = null;
        this.userIdentityTokens = null;
        this.transportProfileUri = null;
        this.securityLevel = null;
    }

    public EndpointDescription(String endpointUrl, ApplicationDescription server, ByteString serverCertificate, MessageSecurityMode securityMode, String securityPolicyUri, UserTokenPolicy[] userIdentityTokens, String transportProfileUri, UByte securityLevel) {
        this.endpointUrl = endpointUrl;
        this.server = server;
        this.serverCertificate = serverCertificate;
        this.securityMode = securityMode;
        this.securityPolicyUri = securityPolicyUri;
        this.userIdentityTokens = userIdentityTokens;
        this.transportProfileUri = transportProfileUri;
        this.securityLevel = securityLevel;
    }

    public String getEndpointUrl() { return endpointUrl; }

    public ApplicationDescription getServer() { return server; }

    public ByteString getServerCertificate() { return serverCertificate; }

    public MessageSecurityMode getSecurityMode() { return securityMode; }

    public String getSecurityPolicyUri() { return securityPolicyUri; }

    @Nullable
    public UserTokenPolicy[] getUserIdentityTokens() { return userIdentityTokens; }

    public String getTransportProfileUri() { return transportProfileUri; }

    public UByte getSecurityLevel() { return securityLevel; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("EndpointUrl", endpointUrl)
            .add("Server", server)
            .add("ServerCertificate", serverCertificate)
            .add("SecurityMode", securityMode)
            .add("SecurityPolicyUri", securityPolicyUri)
            .add("UserIdentityTokens", userIdentityTokens)
            .add("TransportProfileUri", transportProfileUri)
            .add("SecurityLevel", securityLevel)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<EndpointDescription> {

        @Override
        public Class<EndpointDescription> getType() {
            return EndpointDescription.class;
        }

        @Override
        public EndpointDescription decode(UaDecoder decoder) throws UaSerializationException {
            String endpointUrl = decoder.readString("EndpointUrl");
            ApplicationDescription server = (ApplicationDescription) decoder.readBuiltinStruct("Server", ApplicationDescription.class);
            ByteString serverCertificate = decoder.readByteString("ServerCertificate");
            MessageSecurityMode securityMode = MessageSecurityMode.from(decoder.readInt32("SecurityMode"));
            String securityPolicyUri = decoder.readString("SecurityPolicyUri");
            UserTokenPolicy[] userIdentityTokens =
                decoder.readBuiltinStructArray(
                    "UserIdentityTokens",
                    UserTokenPolicy.class
                );
            String transportProfileUri = decoder.readString("TransportProfileUri");
            UByte securityLevel = decoder.readByte("SecurityLevel");

            return new EndpointDescription(endpointUrl, server, serverCertificate, securityMode, securityPolicyUri, userIdentityTokens, transportProfileUri, securityLevel);
        }

        @Override
        public void encode(EndpointDescription value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeString("EndpointUrl", value.endpointUrl);
            encoder.writeBuiltinStruct("Server", value.server, ApplicationDescription.class);
            encoder.writeByteString("ServerCertificate", value.serverCertificate);
            encoder.writeInt32("SecurityMode", value.securityMode != null ? value.securityMode.getValue() : 0);
            encoder.writeString("SecurityPolicyUri", value.securityPolicyUri);
            encoder.writeBuiltinStructArray(
                "UserIdentityTokens",
                value.userIdentityTokens,
                UserTokenPolicy.class
            );
            encoder.writeString("TransportProfileUri", value.transportProfileUri);
            encoder.writeByte("SecurityLevel", value.securityLevel);
        }
    }

}
