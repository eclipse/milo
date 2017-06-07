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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;

@UaDataType("OpenSecureChannelRequest")
public class OpenSecureChannelRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.OpenSecureChannelRequest;
    public static final NodeId BinaryEncodingId = Identifiers.OpenSecureChannelRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.OpenSecureChannelRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _clientProtocolVersion;
    protected final SecurityTokenRequestType _requestType;
    protected final MessageSecurityMode _securityMode;
    protected final ByteString _clientNonce;
    protected final UInteger _requestedLifetime;

    public OpenSecureChannelRequest() {
        this._requestHeader = null;
        this._clientProtocolVersion = null;
        this._requestType = null;
        this._securityMode = null;
        this._clientNonce = null;
        this._requestedLifetime = null;
    }

    public OpenSecureChannelRequest(RequestHeader _requestHeader, UInteger _clientProtocolVersion, SecurityTokenRequestType _requestType, MessageSecurityMode _securityMode, ByteString _clientNonce, UInteger _requestedLifetime) {
        this._requestHeader = _requestHeader;
        this._clientProtocolVersion = _clientProtocolVersion;
        this._requestType = _requestType;
        this._securityMode = _securityMode;
        this._clientNonce = _clientNonce;
        this._requestedLifetime = _requestedLifetime;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getClientProtocolVersion() { return _clientProtocolVersion; }

    public SecurityTokenRequestType getRequestType() { return _requestType; }

    public MessageSecurityMode getSecurityMode() { return _securityMode; }

    public ByteString getClientNonce() { return _clientNonce; }

    public UInteger getRequestedLifetime() { return _requestedLifetime; }

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
            .add("ClientProtocolVersion", _clientProtocolVersion)
            .add("RequestType", _requestType)
            .add("SecurityMode", _securityMode)
            .add("ClientNonce", _clientNonce)
            .add("RequestedLifetime", _requestedLifetime)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<OpenSecureChannelRequest> {
        @Override
        public OpenSecureChannelRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            UInteger _clientProtocolVersion = reader.readUInt32();
            SecurityTokenRequestType _requestType = SecurityTokenRequestType.from(reader.readInt32());
            MessageSecurityMode _securityMode = MessageSecurityMode.from(reader.readInt32());
            ByteString _clientNonce = reader.readByteString();
            UInteger _requestedLifetime = reader.readUInt32();

            return new OpenSecureChannelRequest(_requestHeader, _clientProtocolVersion, _requestType, _securityMode, _clientNonce, _requestedLifetime);
        }

        @Override
        public void encode(SerializationContext context, OpenSecureChannelRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeUInt32(value._clientProtocolVersion);
            writer.writeInt32(value._requestType != null ? value._requestType.getValue() : 0);
            writer.writeInt32(value._securityMode != null ? value._securityMode.getValue() : 0);
            writer.writeByteString(value._clientNonce);
            writer.writeUInt32(value._requestedLifetime);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<OpenSecureChannelRequest> {
        @Override
        public OpenSecureChannelRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            UInteger _clientProtocolVersion = reader.readUInt32("ClientProtocolVersion");
            SecurityTokenRequestType _requestType = SecurityTokenRequestType.from(reader.readInt32("RequestType"));
            MessageSecurityMode _securityMode = MessageSecurityMode.from(reader.readInt32("SecurityMode"));
            ByteString _clientNonce = reader.readByteString("ClientNonce");
            UInteger _requestedLifetime = reader.readUInt32("RequestedLifetime");

            return new OpenSecureChannelRequest(_requestHeader, _clientProtocolVersion, _requestType, _securityMode, _clientNonce, _requestedLifetime);
        }

        @Override
        public void encode(SerializationContext context, OpenSecureChannelRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32("ClientProtocolVersion", encodable._clientProtocolVersion);
            writer.writeInt32("RequestType", encodable._requestType != null ? encodable._requestType.getValue() : 0);
            writer.writeInt32("SecurityMode", encodable._securityMode != null ? encodable._securityMode.getValue() : 0);
            writer.writeByteString("ClientNonce", encodable._clientNonce);
            writer.writeUInt32("RequestedLifetime", encodable._requestedLifetime);
        }
    }

}
