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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part4/7.14">https://reference.opcfoundation.org/v105/Core/docs/Part4/7.14</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class EndpointDescription extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=312");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=314");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=313");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15099");

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

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 314),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("EndpointUrl", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Server", LocalizedText.NULL_VALUE, new NodeId(0, 308), -1, null, UInteger.valueOf(0), false),
                new StructureField("ServerCertificate", LocalizedText.NULL_VALUE, new NodeId(0, 311), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityMode", LocalizedText.NULL_VALUE, new NodeId(0, 302), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityPolicyUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("UserIdentityTokens", LocalizedText.NULL_VALUE, new NodeId(0, 304), 1, null, UInteger.valueOf(0), false),
                new StructureField("TransportProfileUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityLevel", LocalizedText.NULL_VALUE, new NodeId(0, 3), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<EndpointDescription> {
        @Override
        public Class<EndpointDescription> getType() {
            return EndpointDescription.class;
        }

        @Override
        public EndpointDescription decodeType(EncodingContext context, UaDecoder decoder) {
            String endpointUrl = decoder.decodeString("EndpointUrl");
            ApplicationDescription server = (ApplicationDescription) decoder.decodeStruct("Server", ApplicationDescription.TYPE_ID);
            ByteString serverCertificate = decoder.decodeByteString("ServerCertificate");
            MessageSecurityMode securityMode = MessageSecurityMode.from(decoder.decodeEnum("SecurityMode"));
            String securityPolicyUri = decoder.decodeString("SecurityPolicyUri");
            UserTokenPolicy[] userIdentityTokens = (UserTokenPolicy[]) decoder.decodeStructArray("UserIdentityTokens", UserTokenPolicy.TYPE_ID);
            String transportProfileUri = decoder.decodeString("TransportProfileUri");
            UByte securityLevel = decoder.decodeByte("SecurityLevel");
            return new EndpointDescription(endpointUrl, server, serverCertificate, securityMode, securityPolicyUri, userIdentityTokens, transportProfileUri, securityLevel);
        }

        @Override
        public void encodeType(EncodingContext context, UaEncoder encoder,
                               EndpointDescription value) {
            encoder.encodeString("EndpointUrl", value.getEndpointUrl());
            encoder.encodeStruct("Server", value.getServer(), ApplicationDescription.TYPE_ID);
            encoder.encodeByteString("ServerCertificate", value.getServerCertificate());
            encoder.encodeEnum("SecurityMode", value.getSecurityMode());
            encoder.encodeString("SecurityPolicyUri", value.getSecurityPolicyUri());
            encoder.encodeStructArray("UserIdentityTokens", value.getUserIdentityTokens(), UserTokenPolicy.TYPE_ID);
            encoder.encodeString("TransportProfileUri", value.getTransportProfileUri());
            encoder.encodeByte("SecurityLevel", value.getSecurityLevel());
        }
    }
}
