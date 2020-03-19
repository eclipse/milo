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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EndpointDescription extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=312");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=313");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=314");

    private final String endpointUrl;

    private final ApplicationDescription server;

    private final ByteString serverCertificate;

    private final MessageSecurityMode securityMode;

    private final String securityPolicyUri;

    private final UserTokenPolicy[] userIdentityTokens;

    private final String transportProfileUri;

    private final UByte securityLevel;

    public EndpointDescription(String endpointUrl, ApplicationDescription server,
                               ByteString serverCertificate, MessageSecurityMode securityMode, String securityPolicyUri,
                               UserTokenPolicy[] userIdentityTokens, String transportProfileUri, UByte securityLevel) {
        this.endpointUrl = endpointUrl;
        this.server = server;
        this.serverCertificate = serverCertificate;
        this.securityMode = securityMode;
        this.securityPolicyUri = securityPolicyUri;
        this.userIdentityTokens = userIdentityTokens;
        this.transportProfileUri = transportProfileUri;
        this.securityLevel = securityLevel;
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

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public ApplicationDescription getServer() {
        return server;
    }

    public ByteString getServerCertificate() {
        return serverCertificate;
    }

    public MessageSecurityMode getSecurityMode() {
        return securityMode;
    }

    public String getSecurityPolicyUri() {
        return securityPolicyUri;
    }

    public UserTokenPolicy[] getUserIdentityTokens() {
        return userIdentityTokens;
    }

    public String getTransportProfileUri() {
        return transportProfileUri;
    }

    public UByte getSecurityLevel() {
        return securityLevel;
    }

    public static final class Codec extends GenericDataTypeCodec<EndpointDescription> {
        @Override
        public Class<EndpointDescription> getType() {
            return EndpointDescription.class;
        }

        @Override
        public EndpointDescription decode(SerializationContext context, UaDecoder decoder) {
            String endpointUrl = decoder.readString("EndpointUrl");
            ApplicationDescription server = (ApplicationDescription) decoder.readStruct("Server", ApplicationDescription.TYPE_ID);
            ByteString serverCertificate = decoder.readByteString("ServerCertificate");
            MessageSecurityMode securityMode = decoder.readEnum("SecurityMode", MessageSecurityMode.class);
            String securityPolicyUri = decoder.readString("SecurityPolicyUri");
            UserTokenPolicy[] userIdentityTokens = (UserTokenPolicy[]) decoder.readStructArray("UserIdentityTokens", UserTokenPolicy.TYPE_ID);
            String transportProfileUri = decoder.readString("TransportProfileUri");
            UByte securityLevel = decoder.readByte("SecurityLevel");
            return new EndpointDescription(endpointUrl, server, serverCertificate, securityMode, securityPolicyUri, userIdentityTokens, transportProfileUri, securityLevel);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EndpointDescription value) {
            encoder.writeString("EndpointUrl", value.getEndpointUrl());
            encoder.writeStruct("Server", value.getServer(), ApplicationDescription.TYPE_ID);
            encoder.writeByteString("ServerCertificate", value.getServerCertificate());
            encoder.writeEnum("SecurityMode", value.getSecurityMode());
            encoder.writeString("SecurityPolicyUri", value.getSecurityPolicyUri());
            encoder.writeStructArray("UserIdentityTokens", value.getUserIdentityTokens(), UserTokenPolicy.TYPE_ID);
            encoder.writeString("TransportProfileUri", value.getTransportProfileUri());
            encoder.writeByte("SecurityLevel", value.getSecurityLevel());
        }
    }
}
