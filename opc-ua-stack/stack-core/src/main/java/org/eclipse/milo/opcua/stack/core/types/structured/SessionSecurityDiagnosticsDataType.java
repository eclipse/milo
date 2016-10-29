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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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


    public static void encode(SessionSecurityDiagnosticsDataType sessionSecurityDiagnosticsDataType, UaEncoder encoder) {
        encoder.encodeNodeId("SessionId", sessionSecurityDiagnosticsDataType._sessionId);
        encoder.encodeString("ClientUserIdOfSession", sessionSecurityDiagnosticsDataType._clientUserIdOfSession);
        encoder.encodeArray("ClientUserIdHistory", sessionSecurityDiagnosticsDataType._clientUserIdHistory, encoder::encodeString);
        encoder.encodeString("AuthenticationMechanism", sessionSecurityDiagnosticsDataType._authenticationMechanism);
        encoder.encodeString("Encoding", sessionSecurityDiagnosticsDataType._encoding);
        encoder.encodeString("TransportProtocol", sessionSecurityDiagnosticsDataType._transportProtocol);
        encoder.encodeEnumeration("SecurityMode", sessionSecurityDiagnosticsDataType._securityMode);
        encoder.encodeString("SecurityPolicyUri", sessionSecurityDiagnosticsDataType._securityPolicyUri);
        encoder.encodeByteString("ClientCertificate", sessionSecurityDiagnosticsDataType._clientCertificate);
    }

    public static SessionSecurityDiagnosticsDataType decode(UaDecoder decoder) {
        NodeId _sessionId = decoder.decodeNodeId("SessionId");
        String _clientUserIdOfSession = decoder.decodeString("ClientUserIdOfSession");
        String[] _clientUserIdHistory = decoder.decodeArray("ClientUserIdHistory", decoder::decodeString, String.class);
        String _authenticationMechanism = decoder.decodeString("AuthenticationMechanism");
        String _encoding = decoder.decodeString("Encoding");
        String _transportProtocol = decoder.decodeString("TransportProtocol");
        MessageSecurityMode _securityMode = decoder.decodeEnumeration("SecurityMode", MessageSecurityMode.class);
        String _securityPolicyUri = decoder.decodeString("SecurityPolicyUri");
        ByteString _clientCertificate = decoder.decodeByteString("ClientCertificate");

        return new SessionSecurityDiagnosticsDataType(_sessionId, _clientUserIdOfSession, _clientUserIdHistory, _authenticationMechanism, _encoding, _transportProtocol, _securityMode, _securityPolicyUri, _clientCertificate);
    }

    static {
        DelegateRegistry.registerEncoder(SessionSecurityDiagnosticsDataType::encode, SessionSecurityDiagnosticsDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SessionSecurityDiagnosticsDataType::decode, SessionSecurityDiagnosticsDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}
