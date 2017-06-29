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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class CreateSessionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CreateSessionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CreateSessionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateSessionRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final ApplicationDescription clientDescription;
    protected final String serverUri;
    protected final String endpointUrl;
    protected final String sessionName;
    protected final ByteString clientNonce;
    protected final ByteString clientCertificate;
    protected final Double requestedSessionTimeout;
    protected final UInteger maxResponseMessageSize;

    public CreateSessionRequest() {
        this.requestHeader = null;
        this.clientDescription = null;
        this.serverUri = null;
        this.endpointUrl = null;
        this.sessionName = null;
        this.clientNonce = null;
        this.clientCertificate = null;
        this.requestedSessionTimeout = null;
        this.maxResponseMessageSize = null;
    }

    public CreateSessionRequest(RequestHeader requestHeader, ApplicationDescription clientDescription, String serverUri, String endpointUrl, String sessionName, ByteString clientNonce, ByteString clientCertificate, Double requestedSessionTimeout, UInteger maxResponseMessageSize) {
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

    public RequestHeader getRequestHeader() { return requestHeader; }

    public ApplicationDescription getClientDescription() { return clientDescription; }

    public String getServerUri() { return serverUri; }

    public String getEndpointUrl() { return endpointUrl; }

    public String getSessionName() { return sessionName; }

    public ByteString getClientNonce() { return clientNonce; }

    public ByteString getClientCertificate() { return clientCertificate; }

    public Double getRequestedSessionTimeout() { return requestedSessionTimeout; }

    public UInteger getMaxResponseMessageSize() { return maxResponseMessageSize; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", requestHeader)
            .add("ClientDescription", clientDescription)
            .add("ServerUri", serverUri)
            .add("EndpointUrl", endpointUrl)
            .add("SessionName", sessionName)
            .add("ClientNonce", clientNonce)
            .add("ClientCertificate", clientCertificate)
            .add("RequestedSessionTimeout", requestedSessionTimeout)
            .add("MaxResponseMessageSize", maxResponseMessageSize)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CreateSessionRequest> {

        @Override
        public Class<CreateSessionRequest> getType() {
            return CreateSessionRequest.class;
        }

        @Override
        public CreateSessionRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            ApplicationDescription clientDescription = (ApplicationDescription) decoder.readBuiltinStruct("ClientDescription", ApplicationDescription.class);
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
        public void encode(CreateSessionRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStruct("ClientDescription", value.clientDescription, ApplicationDescription.class);
            encoder.writeString("ServerUri", value.serverUri);
            encoder.writeString("EndpointUrl", value.endpointUrl);
            encoder.writeString("SessionName", value.sessionName);
            encoder.writeByteString("ClientNonce", value.clientNonce);
            encoder.writeByteString("ClientCertificate", value.clientCertificate);
            encoder.writeDouble("RequestedSessionTimeout", value.requestedSessionTimeout);
            encoder.writeUInt32("MaxResponseMessageSize", value.maxResponseMessageSize);
        }
    }

}
