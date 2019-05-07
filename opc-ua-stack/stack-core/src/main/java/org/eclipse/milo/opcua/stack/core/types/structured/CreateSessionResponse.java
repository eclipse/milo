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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class CreateSessionResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=462");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=464");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=463");

    private final ResponseHeader responseHeader;

    private final NodeId sessionId;

    private final NodeId authenticationToken;

    private final Double revisedSessionTimeout;

    private final ByteString serverNonce;

    private final ByteString serverCertificate;

    private final EndpointDescription[] serverEndpoints;

    private final SignedSoftwareCertificate[] serverSoftwareCertificates;

    private final SignatureData serverSignature;

    private final UInteger maxRequestMessageSize;

    public CreateSessionResponse(ResponseHeader responseHeader, NodeId sessionId,
                                 NodeId authenticationToken, Double revisedSessionTimeout, ByteString serverNonce,
                                 ByteString serverCertificate, EndpointDescription[] serverEndpoints,
                                 SignedSoftwareCertificate[] serverSoftwareCertificates, SignatureData serverSignature,
                                 UInteger maxRequestMessageSize) {
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public NodeId getSessionId() {
        return sessionId;
    }

    public NodeId getAuthenticationToken() {
        return authenticationToken;
    }

    public Double getRevisedSessionTimeout() {
        return revisedSessionTimeout;
    }

    public ByteString getServerNonce() {
        return serverNonce;
    }

    public ByteString getServerCertificate() {
        return serverCertificate;
    }

    public EndpointDescription[] getServerEndpoints() {
        return serverEndpoints;
    }

    public SignedSoftwareCertificate[] getServerSoftwareCertificates() {
        return serverSoftwareCertificates;
    }

    public SignatureData getServerSignature() {
        return serverSignature;
    }

    public UInteger getMaxRequestMessageSize() {
        return maxRequestMessageSize;
    }

    public static final class Codec extends GenericDataTypeCodec<CreateSessionResponse> {
        @Override
        public Class<CreateSessionResponse> getType() {
            return CreateSessionResponse.class;
        }

        @Override
        public CreateSessionResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            NodeId sessionId = decoder.readNodeId("SessionId");
            NodeId authenticationToken = decoder.readNodeId("AuthenticationToken");
            Double revisedSessionTimeout = decoder.readDouble("RevisedSessionTimeout");
            ByteString serverNonce = decoder.readByteString("ServerNonce");
            ByteString serverCertificate = decoder.readByteString("ServerCertificate");
            EndpointDescription[] serverEndpoints = (EndpointDescription[]) decoder.readStructArray("ServerEndpoints", EndpointDescription.TYPE_ID);
            SignedSoftwareCertificate[] serverSoftwareCertificates = (SignedSoftwareCertificate[]) decoder.readStructArray("ServerSoftwareCertificates", SignedSoftwareCertificate.TYPE_ID);
            SignatureData serverSignature = (SignatureData) decoder.readStruct("ServerSignature", SignatureData.TYPE_ID);
            UInteger maxRequestMessageSize = decoder.readUInt32("MaxRequestMessageSize");
            return new CreateSessionResponse(responseHeader, sessionId, authenticationToken, revisedSessionTimeout, serverNonce, serverCertificate, serverEndpoints, serverSoftwareCertificates, serverSignature, maxRequestMessageSize);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           CreateSessionResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeNodeId("SessionId", value.getSessionId());
            encoder.writeNodeId("AuthenticationToken", value.getAuthenticationToken());
            encoder.writeDouble("RevisedSessionTimeout", value.getRevisedSessionTimeout());
            encoder.writeByteString("ServerNonce", value.getServerNonce());
            encoder.writeByteString("ServerCertificate", value.getServerCertificate());
            encoder.writeStructArray("ServerEndpoints", value.getServerEndpoints(), EndpointDescription.TYPE_ID);
            encoder.writeStructArray("ServerSoftwareCertificates", value.getServerSoftwareCertificates(), SignedSoftwareCertificate.TYPE_ID);
            encoder.writeStruct("ServerSignature", value.getServerSignature(), SignatureData.TYPE_ID);
            encoder.writeUInt32("MaxRequestMessageSize", value.getMaxRequestMessageSize());
        }
    }
}
