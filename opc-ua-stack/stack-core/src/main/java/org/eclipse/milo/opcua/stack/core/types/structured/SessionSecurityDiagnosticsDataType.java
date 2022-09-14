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
import org.eclipse.milo.opcua.stack.core.serialization.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaStructuredType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;

/**
 * @see <a href="https://reference.opcfoundation.org/v105/Core/docs/Part5/12.12">https://reference.opcfoundation.org/v105/Core/docs/Part5/12.12</a>
 */
@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder
@ToString
public class SessionSecurityDiagnosticsDataType extends Structure implements UaStructuredType {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("ns=0;i=868");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("i=870");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("i=869");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("i=15369");

    private final NodeId sessionId;

    private final String clientUserIdOfSession;

    private final String[] clientUserIdHistory;

    private final String authenticationMechanism;

    private final String encoding;

    private final String transportProtocol;

    private final MessageSecurityMode securityMode;

    private final String securityPolicyUri;

    private final ByteString clientCertificate;

    public SessionSecurityDiagnosticsDataType(NodeId sessionId, String clientUserIdOfSession,
                                              String[] clientUserIdHistory, String authenticationMechanism, String encoding,
                                              String transportProtocol, MessageSecurityMode securityMode, String securityPolicyUri,
                                              ByteString clientCertificate) {
        this.sessionId = sessionId;
        this.clientUserIdOfSession = clientUserIdOfSession;
        this.clientUserIdHistory = clientUserIdHistory;
        this.authenticationMechanism = authenticationMechanism;
        this.encoding = encoding;
        this.transportProtocol = transportProtocol;
        this.securityMode = securityMode;
        this.securityPolicyUri = securityPolicyUri;
        this.clientCertificate = clientCertificate;
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

    public NodeId getSessionId() {
        return sessionId;
    }

    public String getClientUserIdOfSession() {
        return clientUserIdOfSession;
    }

    public String[] getClientUserIdHistory() {
        return clientUserIdHistory;
    }

    public String getAuthenticationMechanism() {
        return authenticationMechanism;
    }

    public String getEncoding() {
        return encoding;
    }

    public String getTransportProtocol() {
        return transportProtocol;
    }

    public MessageSecurityMode getSecurityMode() {
        return securityMode;
    }

    public String getSecurityPolicyUri() {
        return securityPolicyUri;
    }

    public ByteString getClientCertificate() {
        return clientCertificate;
    }

    public static StructureDefinition definition(NamespaceTable namespaceTable) {
        return new StructureDefinition(
            new NodeId(0, 870),
            new NodeId(0, 22),
            StructureType.Structure,
            new StructureField[]{
                new StructureField("SessionId", LocalizedText.NULL_VALUE, new NodeId(0, 17), -1, null, UInteger.valueOf(0), false),
                new StructureField("ClientUserIdOfSession", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ClientUserIdHistory", LocalizedText.NULL_VALUE, new NodeId(0, 12), 1, null, UInteger.valueOf(0), false),
                new StructureField("AuthenticationMechanism", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("Encoding", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("TransportProtocol", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityMode", LocalizedText.NULL_VALUE, new NodeId(0, 302), -1, null, UInteger.valueOf(0), false),
                new StructureField("SecurityPolicyUri", LocalizedText.NULL_VALUE, new NodeId(0, 12), -1, null, UInteger.valueOf(0), false),
                new StructureField("ClientCertificate", LocalizedText.NULL_VALUE, new NodeId(0, 15), -1, null, UInteger.valueOf(0), false)
            }
        );
    }

    public static final class Codec extends GenericDataTypeCodec<SessionSecurityDiagnosticsDataType> {
        @Override
        public Class<SessionSecurityDiagnosticsDataType> getType() {
            return SessionSecurityDiagnosticsDataType.class;
        }

        @Override
        public SessionSecurityDiagnosticsDataType decodeType(SerializationContext context,
                                                             UaDecoder decoder) {
            NodeId sessionId = decoder.readNodeId("SessionId");
            String clientUserIdOfSession = decoder.readString("ClientUserIdOfSession");
            String[] clientUserIdHistory = decoder.readStringArray("ClientUserIdHistory");
            String authenticationMechanism = decoder.readString("AuthenticationMechanism");
            String encoding = decoder.readString("Encoding");
            String transportProtocol = decoder.readString("TransportProtocol");
            MessageSecurityMode securityMode = MessageSecurityMode.from(decoder.readEnum("SecurityMode"));
            String securityPolicyUri = decoder.readString("SecurityPolicyUri");
            ByteString clientCertificate = decoder.readByteString("ClientCertificate");
            return new SessionSecurityDiagnosticsDataType(sessionId, clientUserIdOfSession, clientUserIdHistory, authenticationMechanism, encoding, transportProtocol, securityMode, securityPolicyUri, clientCertificate);
        }

        @Override
        public void encodeType(SerializationContext context, UaEncoder encoder,
                               SessionSecurityDiagnosticsDataType value) {
            encoder.writeNodeId("SessionId", value.getSessionId());
            encoder.writeString("ClientUserIdOfSession", value.getClientUserIdOfSession());
            encoder.writeStringArray("ClientUserIdHistory", value.getClientUserIdHistory());
            encoder.writeString("AuthenticationMechanism", value.getAuthenticationMechanism());
            encoder.writeString("Encoding", value.getEncoding());
            encoder.writeString("TransportProtocol", value.getTransportProtocol());
            encoder.writeEnum("SecurityMode", value.getSecurityMode());
            encoder.writeString("SecurityPolicyUri", value.getSecurityPolicyUri());
            encoder.writeByteString("ClientCertificate", value.getClientCertificate());
        }
    }
}
