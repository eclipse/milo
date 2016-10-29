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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("CreateSessionResponse")
public class CreateSessionResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.CreateSessionResponse;
    public static final NodeId BinaryEncodingId = Identifiers.CreateSessionResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateSessionResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final NodeId _sessionId;
    protected final NodeId _authenticationToken;
    protected final Double _revisedSessionTimeout;
    protected final ByteString _serverNonce;
    protected final ByteString _serverCertificate;
    protected final EndpointDescription[] _serverEndpoints;
    protected final SignedSoftwareCertificate[] _serverSoftwareCertificates;
    protected final SignatureData _serverSignature;
    protected final UInteger _maxRequestMessageSize;

    public CreateSessionResponse() {
        this._responseHeader = null;
        this._sessionId = null;
        this._authenticationToken = null;
        this._revisedSessionTimeout = null;
        this._serverNonce = null;
        this._serverCertificate = null;
        this._serverEndpoints = null;
        this._serverSoftwareCertificates = null;
        this._serverSignature = null;
        this._maxRequestMessageSize = null;
    }

    public CreateSessionResponse(ResponseHeader _responseHeader, NodeId _sessionId, NodeId _authenticationToken, Double _revisedSessionTimeout, ByteString _serverNonce, ByteString _serverCertificate, EndpointDescription[] _serverEndpoints, SignedSoftwareCertificate[] _serverSoftwareCertificates, SignatureData _serverSignature, UInteger _maxRequestMessageSize) {
        this._responseHeader = _responseHeader;
        this._sessionId = _sessionId;
        this._authenticationToken = _authenticationToken;
        this._revisedSessionTimeout = _revisedSessionTimeout;
        this._serverNonce = _serverNonce;
        this._serverCertificate = _serverCertificate;
        this._serverEndpoints = _serverEndpoints;
        this._serverSoftwareCertificates = _serverSoftwareCertificates;
        this._serverSignature = _serverSignature;
        this._maxRequestMessageSize = _maxRequestMessageSize;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public NodeId getSessionId() { return _sessionId; }

    public NodeId getAuthenticationToken() { return _authenticationToken; }

    public Double getRevisedSessionTimeout() { return _revisedSessionTimeout; }

    public ByteString getServerNonce() { return _serverNonce; }

    public ByteString getServerCertificate() { return _serverCertificate; }

    public EndpointDescription[] getServerEndpoints() { return _serverEndpoints; }

    public SignedSoftwareCertificate[] getServerSoftwareCertificates() { return _serverSoftwareCertificates; }

    public SignatureData getServerSignature() { return _serverSignature; }

    public UInteger getMaxRequestMessageSize() { return _maxRequestMessageSize; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(CreateSessionResponse createSessionResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", createSessionResponse._responseHeader != null ? createSessionResponse._responseHeader : new ResponseHeader());
        encoder.encodeNodeId("SessionId", createSessionResponse._sessionId);
        encoder.encodeNodeId("AuthenticationToken", createSessionResponse._authenticationToken);
        encoder.encodeDouble("RevisedSessionTimeout", createSessionResponse._revisedSessionTimeout);
        encoder.encodeByteString("ServerNonce", createSessionResponse._serverNonce);
        encoder.encodeByteString("ServerCertificate", createSessionResponse._serverCertificate);
        encoder.encodeArray("ServerEndpoints", createSessionResponse._serverEndpoints, encoder::encodeSerializable);
        encoder.encodeArray("ServerSoftwareCertificates", createSessionResponse._serverSoftwareCertificates, encoder::encodeSerializable);
        encoder.encodeSerializable("ServerSignature", createSessionResponse._serverSignature != null ? createSessionResponse._serverSignature : new SignatureData());
        encoder.encodeUInt32("MaxRequestMessageSize", createSessionResponse._maxRequestMessageSize);
    }

    public static CreateSessionResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        NodeId _sessionId = decoder.decodeNodeId("SessionId");
        NodeId _authenticationToken = decoder.decodeNodeId("AuthenticationToken");
        Double _revisedSessionTimeout = decoder.decodeDouble("RevisedSessionTimeout");
        ByteString _serverNonce = decoder.decodeByteString("ServerNonce");
        ByteString _serverCertificate = decoder.decodeByteString("ServerCertificate");
        EndpointDescription[] _serverEndpoints = decoder.decodeArray("ServerEndpoints", decoder::decodeSerializable, EndpointDescription.class);
        SignedSoftwareCertificate[] _serverSoftwareCertificates = decoder.decodeArray("ServerSoftwareCertificates", decoder::decodeSerializable, SignedSoftwareCertificate.class);
        SignatureData _serverSignature = decoder.decodeSerializable("ServerSignature", SignatureData.class);
        UInteger _maxRequestMessageSize = decoder.decodeUInt32("MaxRequestMessageSize");

        return new CreateSessionResponse(_responseHeader, _sessionId, _authenticationToken, _revisedSessionTimeout, _serverNonce, _serverCertificate, _serverEndpoints, _serverSoftwareCertificates, _serverSignature, _maxRequestMessageSize);
    }

    static {
        DelegateRegistry.registerEncoder(CreateSessionResponse::encode, CreateSessionResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CreateSessionResponse::decode, CreateSessionResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
