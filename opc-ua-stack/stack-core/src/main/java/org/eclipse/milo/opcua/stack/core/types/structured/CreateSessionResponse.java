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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
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

    @Nullable
    public EndpointDescription[] getServerEndpoints() { return _serverEndpoints; }

    @Nullable
    public SignedSoftwareCertificate[] getServerSoftwareCertificates() { return _serverSoftwareCertificates; }

    public SignatureData getServerSignature() { return _serverSignature; }

    public UInteger getMaxRequestMessageSize() { return _maxRequestMessageSize; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", _responseHeader)
            .add("SessionId", _sessionId)
            .add("AuthenticationToken", _authenticationToken)
            .add("RevisedSessionTimeout", _revisedSessionTimeout)
            .add("ServerNonce", _serverNonce)
            .add("ServerCertificate", _serverCertificate)
            .add("ServerEndpoints", _serverEndpoints)
            .add("ServerSoftwareCertificates", _serverSoftwareCertificates)
            .add("ServerSignature", _serverSignature)
            .add("MaxRequestMessageSize", _maxRequestMessageSize)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CreateSessionResponse> {
        @Override
        public CreateSessionResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);
            NodeId _sessionId = reader.readNodeId();
            NodeId _authenticationToken = reader.readNodeId();
            Double _revisedSessionTimeout = reader.readDouble();
            ByteString _serverNonce = reader.readByteString();
            ByteString _serverCertificate = reader.readByteString();
            EndpointDescription[] _serverEndpoints =
                reader.readArray(
                    () -> (EndpointDescription) context.decode(
                        EndpointDescription.BinaryEncodingId, reader),
                    EndpointDescription.class
                );
            SignedSoftwareCertificate[] _serverSoftwareCertificates =
                reader.readArray(
                    () -> (SignedSoftwareCertificate) context.decode(
                        SignedSoftwareCertificate.BinaryEncodingId, reader),
                    SignedSoftwareCertificate.class
                );
            SignatureData _serverSignature = (SignatureData) context.decode(SignatureData.BinaryEncodingId, reader);
            UInteger _maxRequestMessageSize = reader.readUInt32();

            return new CreateSessionResponse(_responseHeader, _sessionId, _authenticationToken, _revisedSessionTimeout, _serverNonce, _serverCertificate, _serverEndpoints, _serverSoftwareCertificates, _serverSignature, _maxRequestMessageSize);
        }

        @Override
        public void encode(SerializationContext context, CreateSessionResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, encodable._responseHeader, writer);
            writer.writeNodeId(encodable._sessionId);
            writer.writeNodeId(encodable._authenticationToken);
            writer.writeDouble(encodable._revisedSessionTimeout);
            writer.writeByteString(encodable._serverNonce);
            writer.writeByteString(encodable._serverCertificate);
            writer.writeArray(
                encodable._serverEndpoints,
                e -> context.encode(EndpointDescription.BinaryEncodingId, e, writer)
            );
            writer.writeArray(
                encodable._serverSoftwareCertificates,
                e -> context.encode(SignedSoftwareCertificate.BinaryEncodingId, e, writer)
            );
            context.encode(SignatureData.BinaryEncodingId, encodable._serverSignature, writer);
            writer.writeUInt32(encodable._maxRequestMessageSize);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CreateSessionResponse> {
        @Override
        public CreateSessionResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);
            NodeId _sessionId = reader.readNodeId("SessionId");
            NodeId _authenticationToken = reader.readNodeId("AuthenticationToken");
            Double _revisedSessionTimeout = reader.readDouble("RevisedSessionTimeout");
            ByteString _serverNonce = reader.readByteString("ServerNonce");
            ByteString _serverCertificate = reader.readByteString("ServerCertificate");
            EndpointDescription[] _serverEndpoints =
                reader.readArray(
                    "ServerEndpoints",
                    f -> (EndpointDescription) context.decode(
                        EndpointDescription.XmlEncodingId, reader),
                    EndpointDescription.class
                );
            SignedSoftwareCertificate[] _serverSoftwareCertificates =
                reader.readArray(
                    "ServerSoftwareCertificates",
                    f -> (SignedSoftwareCertificate) context.decode(
                        SignedSoftwareCertificate.XmlEncodingId, reader),
                    SignedSoftwareCertificate.class
                );
            SignatureData _serverSignature = (SignatureData) context.decode(SignatureData.XmlEncodingId, reader);
            UInteger _maxRequestMessageSize = reader.readUInt32("MaxRequestMessageSize");

            return new CreateSessionResponse(_responseHeader, _sessionId, _authenticationToken, _revisedSessionTimeout, _serverNonce, _serverCertificate, _serverEndpoints, _serverSoftwareCertificates, _serverSignature, _maxRequestMessageSize);
        }

        @Override
        public void encode(SerializationContext context, CreateSessionResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
            writer.writeNodeId("SessionId", encodable._sessionId);
            writer.writeNodeId("AuthenticationToken", encodable._authenticationToken);
            writer.writeDouble("RevisedSessionTimeout", encodable._revisedSessionTimeout);
            writer.writeByteString("ServerNonce", encodable._serverNonce);
            writer.writeByteString("ServerCertificate", encodable._serverCertificate);
            writer.writeArray(
                "ServerEndpoints",
                encodable._serverEndpoints,
                (f, e) -> context.encode(EndpointDescription.XmlEncodingId, e, writer)
            );
            writer.writeArray(
                "ServerSoftwareCertificates",
                encodable._serverSoftwareCertificates,
                (f, e) -> context.encode(SignedSoftwareCertificate.XmlEncodingId, e, writer)
            );
            context.encode(SignatureData.XmlEncodingId, encodable._serverSignature, writer);
            writer.writeUInt32("MaxRequestMessageSize", encodable._maxRequestMessageSize);
        }
    }

}
