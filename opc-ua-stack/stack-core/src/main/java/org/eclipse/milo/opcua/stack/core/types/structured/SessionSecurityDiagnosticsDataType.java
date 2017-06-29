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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

public class SessionSecurityDiagnosticsDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.SessionSecurityDiagnosticsDataType;
    public static final NodeId BinaryEncodingId = Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultXml;

    protected final NodeId sessionId;
    protected final String clientUserIdOfSession;
    protected final String[] clientUserIdHistory;
    protected final String authenticationMechanism;
    protected final String encoding;
    protected final String transportProtocol;
    protected final MessageSecurityMode securityMode;
    protected final String securityPolicyUri;
    protected final ByteString clientCertificate;

    public SessionSecurityDiagnosticsDataType() {
        this.sessionId = null;
        this.clientUserIdOfSession = null;
        this.clientUserIdHistory = null;
        this.authenticationMechanism = null;
        this.encoding = null;
        this.transportProtocol = null;
        this.securityMode = null;
        this.securityPolicyUri = null;
        this.clientCertificate = null;
    }

    public SessionSecurityDiagnosticsDataType(NodeId sessionId, String clientUserIdOfSession, String[] clientUserIdHistory, String authenticationMechanism, String encoding, String transportProtocol, MessageSecurityMode securityMode, String securityPolicyUri, ByteString clientCertificate) {
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

    public NodeId getSessionId() { return sessionId; }

    public String getClientUserIdOfSession() { return clientUserIdOfSession; }

    @Nullable
    public String[] getClientUserIdHistory() { return clientUserIdHistory; }

    public String getAuthenticationMechanism() { return authenticationMechanism; }

    public String getEncoding() { return encoding; }

    public String getTransportProtocol() { return transportProtocol; }

    public MessageSecurityMode getSecurityMode() { return securityMode; }

    public String getSecurityPolicyUri() { return securityPolicyUri; }

    public ByteString getClientCertificate() { return clientCertificate; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SessionId", sessionId)
            .add("ClientUserIdOfSession", clientUserIdOfSession)
            .add("ClientUserIdHistory", clientUserIdHistory)
            .add("AuthenticationMechanism", authenticationMechanism)
            .add("Encoding", encoding)
            .add("TransportProtocol", transportProtocol)
            .add("SecurityMode", securityMode)
            .add("SecurityPolicyUri", securityPolicyUri)
            .add("ClientCertificate", clientCertificate)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SessionSecurityDiagnosticsDataType> {

        @Override
        public Class<SessionSecurityDiagnosticsDataType> getType() {
            return SessionSecurityDiagnosticsDataType.class;
        }

        @Override
        public SessionSecurityDiagnosticsDataType decode(UaDecoder decoder) throws UaSerializationException {
            NodeId sessionId = decoder.readNodeId("SessionId");
            String clientUserIdOfSession = decoder.readString("ClientUserIdOfSession");
            String[] clientUserIdHistory = decoder.readArray("ClientUserIdHistory", decoder::readString, String.class);
            String authenticationMechanism = decoder.readString("AuthenticationMechanism");
            String encoding = decoder.readString("Encoding");
            String transportProtocol = decoder.readString("TransportProtocol");
            MessageSecurityMode securityMode = MessageSecurityMode.from(decoder.readInt32("SecurityMode"));
            String securityPolicyUri = decoder.readString("SecurityPolicyUri");
            ByteString clientCertificate = decoder.readByteString("ClientCertificate");

            return new SessionSecurityDiagnosticsDataType(sessionId, clientUserIdOfSession, clientUserIdHistory, authenticationMechanism, encoding, transportProtocol, securityMode, securityPolicyUri, clientCertificate);
        }

        @Override
        public void encode(SessionSecurityDiagnosticsDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("SessionId", value.sessionId);
            encoder.writeString("ClientUserIdOfSession", value.clientUserIdOfSession);
            encoder.writeArray("ClientUserIdHistory", value.clientUserIdHistory, encoder::writeString);
            encoder.writeString("AuthenticationMechanism", value.authenticationMechanism);
            encoder.writeString("Encoding", value.encoding);
            encoder.writeString("TransportProtocol", value.transportProtocol);
            encoder.writeInt32("SecurityMode", value.securityMode != null ? value.securityMode.getValue() : 0);
            encoder.writeString("SecurityPolicyUri", value.securityPolicyUri);
            encoder.writeByteString("ClientCertificate", value.clientCertificate);
        }
    }

}
