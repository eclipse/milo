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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class CreateSessionResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.CreateSessionResponse;
    public static final NodeId BinaryEncodingId = Identifiers.CreateSessionResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateSessionResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final NodeId sessionId;
    protected final NodeId authenticationToken;
    protected final Double revisedSessionTimeout;
    protected final ByteString serverNonce;
    protected final ByteString serverCertificate;
    protected final EndpointDescription[] serverEndpoints;
    protected final SignedSoftwareCertificate[] serverSoftwareCertificates;
    protected final SignatureData serverSignature;
    protected final UInteger maxRequestMessageSize;

    public CreateSessionResponse() {
        this.responseHeader = null;
        this.sessionId = null;
        this.authenticationToken = null;
        this.revisedSessionTimeout = null;
        this.serverNonce = null;
        this.serverCertificate = null;
        this.serverEndpoints = null;
        this.serverSoftwareCertificates = null;
        this.serverSignature = null;
        this.maxRequestMessageSize = null;
    }

    public CreateSessionResponse(ResponseHeader responseHeader, NodeId sessionId, NodeId authenticationToken, Double revisedSessionTimeout, ByteString serverNonce, ByteString serverCertificate, EndpointDescription[] serverEndpoints, SignedSoftwareCertificate[] serverSoftwareCertificates, SignatureData serverSignature, UInteger maxRequestMessageSize) {
        this.responseHeader = responseHeader;
        this.sessionId = sessionId;
        this.authenticationToken = authenticationToken;
        this.revisedSessionTimeout = revisedSessionTimeout;
        this.serverNonce = serverNonce;
        this.serverCertificate = serverCertificate;
        this.serverEndpoints = serverEndpoints;
        this.serverSoftwareCertificates = serverSoftwareCertificates;
        this.serverSignature = serverSignature;
        this.maxRequestMessageSize = maxRequestMessageSize;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    public NodeId getSessionId() { return sessionId; }

    public NodeId getAuthenticationToken() { return authenticationToken; }

    public Double getRevisedSessionTimeout() { return revisedSessionTimeout; }

    public ByteString getServerNonce() { return serverNonce; }

    public ByteString getServerCertificate() { return serverCertificate; }

    @Nullable
    public EndpointDescription[] getServerEndpoints() { return serverEndpoints; }

    @Nullable
    public SignedSoftwareCertificate[] getServerSoftwareCertificates() { return serverSoftwareCertificates; }

    public SignatureData getServerSignature() { return serverSignature; }

    public UInteger getMaxRequestMessageSize() { return maxRequestMessageSize; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", responseHeader)
            .add("SessionId", sessionId)
            .add("AuthenticationToken", authenticationToken)
            .add("RevisedSessionTimeout", revisedSessionTimeout)
            .add("ServerNonce", serverNonce)
            .add("ServerCertificate", serverCertificate)
            .add("ServerEndpoints", serverEndpoints)
            .add("ServerSoftwareCertificates", serverSoftwareCertificates)
            .add("ServerSignature", serverSignature)
            .add("MaxRequestMessageSize", maxRequestMessageSize)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CreateSessionResponse> {

        @Override
        public Class<CreateSessionResponse> getType() {
            return CreateSessionResponse.class;
        }

        @Override
        public CreateSessionResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            NodeId sessionId = decoder.readNodeId("SessionId");
            NodeId authenticationToken = decoder.readNodeId("AuthenticationToken");
            Double revisedSessionTimeout = decoder.readDouble("RevisedSessionTimeout");
            ByteString serverNonce = decoder.readByteString("ServerNonce");
            ByteString serverCertificate = decoder.readByteString("ServerCertificate");
            EndpointDescription[] serverEndpoints =
                decoder.readBuiltinStructArray(
                    "ServerEndpoints",
                    EndpointDescription.class
                );
            SignedSoftwareCertificate[] serverSoftwareCertificates =
                decoder.readBuiltinStructArray(
                    "ServerSoftwareCertificates",
                    SignedSoftwareCertificate.class
                );
            SignatureData serverSignature = (SignatureData) decoder.readBuiltinStruct("ServerSignature", SignatureData.class);
            UInteger maxRequestMessageSize = decoder.readUInt32("MaxRequestMessageSize");

            return new CreateSessionResponse(responseHeader, sessionId, authenticationToken, revisedSessionTimeout, serverNonce, serverCertificate, serverEndpoints, serverSoftwareCertificates, serverSignature, maxRequestMessageSize);
        }

        @Override
        public void encode(CreateSessionResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeNodeId("SessionId", value.sessionId);
            encoder.writeNodeId("AuthenticationToken", value.authenticationToken);
            encoder.writeDouble("RevisedSessionTimeout", value.revisedSessionTimeout);
            encoder.writeByteString("ServerNonce", value.serverNonce);
            encoder.writeByteString("ServerCertificate", value.serverCertificate);
            encoder.writeBuiltinStructArray(
                "ServerEndpoints",
                value.serverEndpoints,
                EndpointDescription.class
            );
            encoder.writeBuiltinStructArray(
                "ServerSoftwareCertificates",
                value.serverSoftwareCertificates,
                SignedSoftwareCertificate.class
            );
            encoder.writeBuiltinStruct("ServerSignature", value.serverSignature, SignatureData.class);
            encoder.writeUInt32("MaxRequestMessageSize", value.maxRequestMessageSize);
        }
    }

}
