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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
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

@UaDataType("CreateSessionRequest")
public class CreateSessionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CreateSessionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CreateSessionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateSessionRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final ApplicationDescription _clientDescription;
    protected final String _serverUri;
    protected final String _endpointUrl;
    protected final String _sessionName;
    protected final ByteString _clientNonce;
    protected final ByteString _clientCertificate;
    protected final Double _requestedSessionTimeout;
    protected final UInteger _maxResponseMessageSize;

    public CreateSessionRequest() {
        this._requestHeader = null;
        this._clientDescription = null;
        this._serverUri = null;
        this._endpointUrl = null;
        this._sessionName = null;
        this._clientNonce = null;
        this._clientCertificate = null;
        this._requestedSessionTimeout = null;
        this._maxResponseMessageSize = null;
    }

    public CreateSessionRequest(RequestHeader _requestHeader, ApplicationDescription _clientDescription, String _serverUri, String _endpointUrl, String _sessionName, ByteString _clientNonce, ByteString _clientCertificate, Double _requestedSessionTimeout, UInteger _maxResponseMessageSize) {
        this._requestHeader = _requestHeader;
        this._clientDescription = _clientDescription;
        this._serverUri = _serverUri;
        this._endpointUrl = _endpointUrl;
        this._sessionName = _sessionName;
        this._clientNonce = _clientNonce;
        this._clientCertificate = _clientCertificate;
        this._requestedSessionTimeout = _requestedSessionTimeout;
        this._maxResponseMessageSize = _maxResponseMessageSize;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public ApplicationDescription getClientDescription() { return _clientDescription; }

    public String getServerUri() { return _serverUri; }

    public String getEndpointUrl() { return _endpointUrl; }

    public String getSessionName() { return _sessionName; }

    public ByteString getClientNonce() { return _clientNonce; }

    public ByteString getClientCertificate() { return _clientCertificate; }

    public Double getRequestedSessionTimeout() { return _requestedSessionTimeout; }

    public UInteger getMaxResponseMessageSize() { return _maxResponseMessageSize; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", _requestHeader)
            .add("ClientDescription", _clientDescription)
            .add("ServerUri", _serverUri)
            .add("EndpointUrl", _endpointUrl)
            .add("SessionName", _sessionName)
            .add("ClientNonce", _clientNonce)
            .add("ClientCertificate", _clientCertificate)
            .add("RequestedSessionTimeout", _requestedSessionTimeout)
            .add("MaxResponseMessageSize", _maxResponseMessageSize)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CreateSessionRequest> {
        @Override
        public CreateSessionRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            ApplicationDescription _clientDescription = (ApplicationDescription) context.decode(ApplicationDescription.BinaryEncodingId, reader);
            String _serverUri = reader.readString();
            String _endpointUrl = reader.readString();
            String _sessionName = reader.readString();
            ByteString _clientNonce = reader.readByteString();
            ByteString _clientCertificate = reader.readByteString();
            Double _requestedSessionTimeout = reader.readDouble();
            UInteger _maxResponseMessageSize = reader.readUInt32();

            return new CreateSessionRequest(_requestHeader, _clientDescription, _serverUri, _endpointUrl, _sessionName, _clientNonce, _clientCertificate, _requestedSessionTimeout, _maxResponseMessageSize);
        }

        @Override
        public void encode(SerializationContext context, CreateSessionRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            context.encode(ApplicationDescription.BinaryEncodingId, value._clientDescription, writer);
            writer.writeString(value._serverUri);
            writer.writeString(value._endpointUrl);
            writer.writeString(value._sessionName);
            writer.writeByteString(value._clientNonce);
            writer.writeByteString(value._clientCertificate);
            writer.writeDouble(value._requestedSessionTimeout);
            writer.writeUInt32(value._maxResponseMessageSize);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CreateSessionRequest> {
        @Override
        public CreateSessionRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            ApplicationDescription _clientDescription = (ApplicationDescription) context.decode(ApplicationDescription.XmlEncodingId, reader);
            String _serverUri = reader.readString("ServerUri");
            String _endpointUrl = reader.readString("EndpointUrl");
            String _sessionName = reader.readString("SessionName");
            ByteString _clientNonce = reader.readByteString("ClientNonce");
            ByteString _clientCertificate = reader.readByteString("ClientCertificate");
            Double _requestedSessionTimeout = reader.readDouble("RequestedSessionTimeout");
            UInteger _maxResponseMessageSize = reader.readUInt32("MaxResponseMessageSize");

            return new CreateSessionRequest(_requestHeader, _clientDescription, _serverUri, _endpointUrl, _sessionName, _clientNonce, _clientCertificate, _requestedSessionTimeout, _maxResponseMessageSize);
        }

        @Override
        public void encode(SerializationContext context, CreateSessionRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            context.encode(ApplicationDescription.XmlEncodingId, encodable._clientDescription, writer);
            writer.writeString("ServerUri", encodable._serverUri);
            writer.writeString("EndpointUrl", encodable._endpointUrl);
            writer.writeString("SessionName", encodable._sessionName);
            writer.writeByteString("ClientNonce", encodable._clientNonce);
            writer.writeByteString("ClientCertificate", encodable._clientCertificate);
            writer.writeDouble("RequestedSessionTimeout", encodable._requestedSessionTimeout);
            writer.writeUInt32("MaxResponseMessageSize", encodable._maxResponseMessageSize);
        }
    }

}
