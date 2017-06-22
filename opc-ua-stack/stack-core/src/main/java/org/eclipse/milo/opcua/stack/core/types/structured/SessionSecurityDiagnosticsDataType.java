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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;

@UaDataType("SessionSecurityDiagnosticsDataType")
public class SessionSecurityDiagnosticsDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.SessionSecurityDiagnosticsDataType;
    public static final NodeId BinaryEncodingId = Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SessionSecurityDiagnosticsDataType_Encoding_DefaultXml;

    protected final NodeId _sessionId;
    protected final String _clientUserIdOfSession;
    protected final String[] _clientUserIdHistory;
    protected final String _authenticationMechanism;
    protected final String _encoding;
    protected final String _transportProtocol;
    protected final MessageSecurityMode _securityMode;
    protected final String _securityPolicyUri;
    protected final ByteString _clientCertificate;

    public SessionSecurityDiagnosticsDataType() {
        this._sessionId = null;
        this._clientUserIdOfSession = null;
        this._clientUserIdHistory = null;
        this._authenticationMechanism = null;
        this._encoding = null;
        this._transportProtocol = null;
        this._securityMode = null;
        this._securityPolicyUri = null;
        this._clientCertificate = null;
    }

    public SessionSecurityDiagnosticsDataType(NodeId _sessionId, String _clientUserIdOfSession, String[] _clientUserIdHistory, String _authenticationMechanism, String _encoding, String _transportProtocol, MessageSecurityMode _securityMode, String _securityPolicyUri, ByteString _clientCertificate) {
        this._sessionId = _sessionId;
        this._clientUserIdOfSession = _clientUserIdOfSession;
        this._clientUserIdHistory = _clientUserIdHistory;
        this._authenticationMechanism = _authenticationMechanism;
        this._encoding = _encoding;
        this._transportProtocol = _transportProtocol;
        this._securityMode = _securityMode;
        this._securityPolicyUri = _securityPolicyUri;
        this._clientCertificate = _clientCertificate;
    }

    public NodeId getSessionId() { return _sessionId; }

    public String getClientUserIdOfSession() { return _clientUserIdOfSession; }

    @Nullable
    public String[] getClientUserIdHistory() { return _clientUserIdHistory; }

    public String getAuthenticationMechanism() { return _authenticationMechanism; }

    public String getEncoding() { return _encoding; }

    public String getTransportProtocol() { return _transportProtocol; }

    public MessageSecurityMode getSecurityMode() { return _securityMode; }

    public String getSecurityPolicyUri() { return _securityPolicyUri; }

    public ByteString getClientCertificate() { return _clientCertificate; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SessionId", _sessionId)
            .add("ClientUserIdOfSession", _clientUserIdOfSession)
            .add("ClientUserIdHistory", _clientUserIdHistory)
            .add("AuthenticationMechanism", _authenticationMechanism)
            .add("Encoding", _encoding)
            .add("TransportProtocol", _transportProtocol)
            .add("SecurityMode", _securityMode)
            .add("SecurityPolicyUri", _securityPolicyUri)
            .add("ClientCertificate", _clientCertificate)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SessionSecurityDiagnosticsDataType> {
        @Override
        public SessionSecurityDiagnosticsDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _sessionId = reader.readNodeId();
            String _clientUserIdOfSession = reader.readString();
            String[] _clientUserIdHistory = reader.readArray(reader::readString, String.class);
            String _authenticationMechanism = reader.readString();
            String _encoding = reader.readString();
            String _transportProtocol = reader.readString();
            MessageSecurityMode _securityMode = MessageSecurityMode.from(reader.readInt32());
            String _securityPolicyUri = reader.readString();
            ByteString _clientCertificate = reader.readByteString();

            return new SessionSecurityDiagnosticsDataType(_sessionId, _clientUserIdOfSession, _clientUserIdHistory, _authenticationMechanism, _encoding, _transportProtocol, _securityMode, _securityPolicyUri, _clientCertificate);
        }

        @Override
        public void encode(SerializationContext context, SessionSecurityDiagnosticsDataType value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._sessionId);
            writer.writeString(value._clientUserIdOfSession);
            writer.writeArray(value._clientUserIdHistory, writer::writeString);
            writer.writeString(value._authenticationMechanism);
            writer.writeString(value._encoding);
            writer.writeString(value._transportProtocol);
            writer.writeInt32(value._securityMode != null ? value._securityMode.getValue() : 0);
            writer.writeString(value._securityPolicyUri);
            writer.writeByteString(value._clientCertificate);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SessionSecurityDiagnosticsDataType> {
        @Override
        public SessionSecurityDiagnosticsDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _sessionId = reader.readNodeId("SessionId");
            String _clientUserIdOfSession = reader.readString("ClientUserIdOfSession");
            String[] _clientUserIdHistory = reader.readArray("ClientUserIdHistory", reader::readString, String.class);
            String _authenticationMechanism = reader.readString("AuthenticationMechanism");
            String _encoding = reader.readString("Encoding");
            String _transportProtocol = reader.readString("TransportProtocol");
            MessageSecurityMode _securityMode = MessageSecurityMode.from(reader.readInt32("SecurityMode"));
            String _securityPolicyUri = reader.readString("SecurityPolicyUri");
            ByteString _clientCertificate = reader.readByteString("ClientCertificate");

            return new SessionSecurityDiagnosticsDataType(_sessionId, _clientUserIdOfSession, _clientUserIdHistory, _authenticationMechanism, _encoding, _transportProtocol, _securityMode, _securityPolicyUri, _clientCertificate);
        }

        @Override
        public void encode(SerializationContext context, SessionSecurityDiagnosticsDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("SessionId", encodable._sessionId);
            writer.writeString("ClientUserIdOfSession", encodable._clientUserIdOfSession);
            writer.writeArray("ClientUserIdHistory", encodable._clientUserIdHistory, writer::writeString);
            writer.writeString("AuthenticationMechanism", encodable._authenticationMechanism);
            writer.writeString("Encoding", encodable._encoding);
            writer.writeString("TransportProtocol", encodable._transportProtocol);
            writer.writeInt32("SecurityMode", encodable._securityMode != null ? encodable._securityMode.getValue() : 0);
            writer.writeString("SecurityPolicyUri", encodable._securityPolicyUri);
            writer.writeByteString("ClientCertificate", encodable._clientCertificate);
        }
    }

}
