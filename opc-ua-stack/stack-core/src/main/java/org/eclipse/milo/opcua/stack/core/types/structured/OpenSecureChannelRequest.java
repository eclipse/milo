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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MessageSecurityMode;
import org.eclipse.milo.opcua.stack.core.types.enumerated.SecurityTokenRequestType;

public class OpenSecureChannelRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.OpenSecureChannelRequest;
    public static final NodeId BinaryEncodingId = Identifiers.OpenSecureChannelRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.OpenSecureChannelRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger clientProtocolVersion;
    protected final SecurityTokenRequestType requestType;
    protected final MessageSecurityMode securityMode;
    protected final ByteString clientNonce;
    protected final UInteger requestedLifetime;

    public OpenSecureChannelRequest() {
        this.requestHeader = null;
        this.clientProtocolVersion = null;
        this.requestType = null;
        this.securityMode = null;
        this.clientNonce = null;
        this.requestedLifetime = null;
    }

    public OpenSecureChannelRequest(RequestHeader requestHeader, UInteger clientProtocolVersion, SecurityTokenRequestType requestType, MessageSecurityMode securityMode, ByteString clientNonce, UInteger requestedLifetime) {
        this.requestHeader = requestHeader;
        this.clientProtocolVersion = clientProtocolVersion;
        this.requestType = requestType;
        this.securityMode = securityMode;
        this.clientNonce = clientNonce;
        this.requestedLifetime = requestedLifetime;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public UInteger getClientProtocolVersion() { return clientProtocolVersion; }

    public SecurityTokenRequestType getRequestType() { return requestType; }

    public MessageSecurityMode getSecurityMode() { return securityMode; }

    public ByteString getClientNonce() { return clientNonce; }

    public UInteger getRequestedLifetime() { return requestedLifetime; }

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
            .add("ClientProtocolVersion", clientProtocolVersion)
            .add("RequestType", requestType)
            .add("SecurityMode", securityMode)
            .add("ClientNonce", clientNonce)
            .add("RequestedLifetime", requestedLifetime)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<OpenSecureChannelRequest> {

        @Override
        public Class<OpenSecureChannelRequest> getType() {
            return OpenSecureChannelRequest.class;
        }

        @Override
        public OpenSecureChannelRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger clientProtocolVersion = decoder.readUInt32("ClientProtocolVersion");
            SecurityTokenRequestType requestType = SecurityTokenRequestType.from(decoder.readInt32("RequestType"));
            MessageSecurityMode securityMode = MessageSecurityMode.from(decoder.readInt32("SecurityMode"));
            ByteString clientNonce = decoder.readByteString("ClientNonce");
            UInteger requestedLifetime = decoder.readUInt32("RequestedLifetime");

            return new OpenSecureChannelRequest(requestHeader, clientProtocolVersion, requestType, securityMode, clientNonce, requestedLifetime);
        }

        @Override
        public void encode(OpenSecureChannelRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeUInt32("ClientProtocolVersion", value.clientProtocolVersion);
            encoder.writeInt32("RequestType", value.requestType != null ? value.requestType.getValue() : 0);
            encoder.writeInt32("SecurityMode", value.securityMode != null ? value.securityMode.getValue() : 0);
            encoder.writeByteString("ClientNonce", value.clientNonce);
            encoder.writeUInt32("RequestedLifetime", value.requestedLifetime);
        }
    }

}
