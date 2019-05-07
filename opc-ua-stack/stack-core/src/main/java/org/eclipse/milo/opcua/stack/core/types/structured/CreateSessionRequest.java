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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class CreateSessionRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=459");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=461");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=460");

    private final RequestHeader requestHeader;

    private final ApplicationDescription clientDescription;

    private final String serverUri;

    private final String endpointUrl;

    private final String sessionName;

    private final ByteString clientNonce;

    private final ByteString clientCertificate;

    private final Double requestedSessionTimeout;

    private final UInteger maxResponseMessageSize;

    public CreateSessionRequest(RequestHeader requestHeader, ApplicationDescription clientDescription,
                                String serverUri, String endpointUrl, String sessionName, ByteString clientNonce,
                                ByteString clientCertificate, Double requestedSessionTimeout,
                                UInteger maxResponseMessageSize) {
        this.requestHeader = requestHeader;
        this.clientDescription = clientDescription;
        this.serverUri = serverUri;
        this.endpointUrl = endpointUrl;
        this.sessionName = sessionName;
        this.clientNonce = clientNonce;
        this.clientCertificate = clientCertificate;
        this.requestedSessionTimeout = requestedSessionTimeout;
        this.maxResponseMessageSize = maxResponseMessageSize;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public ApplicationDescription getClientDescription() {
        return clientDescription;
    }

    public String getServerUri() {
        return serverUri;
    }

    public String getEndpointUrl() {
        return endpointUrl;
    }

    public String getSessionName() {
        return sessionName;
    }

    public ByteString getClientNonce() {
        return clientNonce;
    }

    public ByteString getClientCertificate() {
        return clientCertificate;
    }

    public Double getRequestedSessionTimeout() {
        return requestedSessionTimeout;
    }

    public UInteger getMaxResponseMessageSize() {
        return maxResponseMessageSize;
    }

    public static final class Codec extends GenericDataTypeCodec<CreateSessionRequest> {
        @Override
        public Class<CreateSessionRequest> getType() {
            return CreateSessionRequest.class;
        }

        @Override
        public CreateSessionRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            ApplicationDescription clientDescription = (ApplicationDescription) decoder.readStruct("ClientDescription", ApplicationDescription.TYPE_ID);
            String serverUri = decoder.readString("ServerUri");
            String endpointUrl = decoder.readString("EndpointUrl");
            String sessionName = decoder.readString("SessionName");
            ByteString clientNonce = decoder.readByteString("ClientNonce");
            ByteString clientCertificate = decoder.readByteString("ClientCertificate");
            Double requestedSessionTimeout = decoder.readDouble("RequestedSessionTimeout");
            UInteger maxResponseMessageSize = decoder.readUInt32("MaxResponseMessageSize");
            return new CreateSessionRequest(requestHeader, clientDescription, serverUri, endpointUrl, sessionName, clientNonce, clientCertificate, requestedSessionTimeout, maxResponseMessageSize);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           CreateSessionRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStruct("ClientDescription", value.getClientDescription(), ApplicationDescription.TYPE_ID);
            encoder.writeString("ServerUri", value.getServerUri());
            encoder.writeString("EndpointUrl", value.getEndpointUrl());
            encoder.writeString("SessionName", value.getSessionName());
            encoder.writeByteString("ClientNonce", value.getClientNonce());
            encoder.writeByteString("ClientCertificate", value.getClientCertificate());
            encoder.writeDouble("RequestedSessionTimeout", value.getRequestedSessionTimeout());
            encoder.writeUInt32("MaxResponseMessageSize", value.getMaxResponseMessageSize());
        }
    }
}
