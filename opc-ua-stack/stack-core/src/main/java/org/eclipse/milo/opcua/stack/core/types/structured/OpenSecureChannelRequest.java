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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
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


    public static void encode(OpenSecureChannelRequest openSecureChannelRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", openSecureChannelRequest._requestHeader != null ? openSecureChannelRequest._requestHeader : new RequestHeader());
        encoder.encodeUInt32("ClientProtocolVersion", openSecureChannelRequest._clientProtocolVersion);
        encoder.encodeEnumeration("RequestType", openSecureChannelRequest._requestType);
        encoder.encodeEnumeration("SecurityMode", openSecureChannelRequest._securityMode);
        encoder.encodeByteString("ClientNonce", openSecureChannelRequest._clientNonce);
        encoder.encodeUInt32("RequestedLifetime", openSecureChannelRequest._requestedLifetime);
    }

    public static OpenSecureChannelRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger _clientProtocolVersion = decoder.decodeUInt32("ClientProtocolVersion");
        SecurityTokenRequestType _requestType = decoder.decodeEnumeration("RequestType", SecurityTokenRequestType.class);
        MessageSecurityMode _securityMode = decoder.decodeEnumeration("SecurityMode", MessageSecurityMode.class);
        ByteString _clientNonce = decoder.decodeByteString("ClientNonce");
        UInteger _requestedLifetime = decoder.decodeUInt32("RequestedLifetime");

        return new OpenSecureChannelRequest(_requestHeader, _clientProtocolVersion, _requestType, _securityMode, _clientNonce, _requestedLifetime);
    }

    static {
        DelegateRegistry.registerEncoder(OpenSecureChannelRequest::encode, OpenSecureChannelRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(OpenSecureChannelRequest::decode, OpenSecureChannelRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
