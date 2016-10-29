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

@UaDataType("OpenSecureChannelResponse")
public class OpenSecureChannelResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.OpenSecureChannelResponse;
    public static final NodeId BinaryEncodingId = Identifiers.OpenSecureChannelResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.OpenSecureChannelResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final UInteger _serverProtocolVersion;
    protected final ChannelSecurityToken _securityToken;
    protected final ByteString _serverNonce;

    public OpenSecureChannelResponse() {
        this._responseHeader = null;
        this._serverProtocolVersion = null;
        this._securityToken = null;
        this._serverNonce = null;
    }

    public OpenSecureChannelResponse(ResponseHeader _responseHeader, UInteger _serverProtocolVersion, ChannelSecurityToken _securityToken, ByteString _serverNonce) {
        this._responseHeader = _responseHeader;
        this._serverProtocolVersion = _serverProtocolVersion;
        this._securityToken = _securityToken;
        this._serverNonce = _serverNonce;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public UInteger getServerProtocolVersion() { return _serverProtocolVersion; }

    public ChannelSecurityToken getSecurityToken() { return _securityToken; }

    public ByteString getServerNonce() { return _serverNonce; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(OpenSecureChannelResponse openSecureChannelResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", openSecureChannelResponse._responseHeader != null ? openSecureChannelResponse._responseHeader : new ResponseHeader());
        encoder.encodeUInt32("ServerProtocolVersion", openSecureChannelResponse._serverProtocolVersion);
        encoder.encodeSerializable("SecurityToken", openSecureChannelResponse._securityToken != null ? openSecureChannelResponse._securityToken : new ChannelSecurityToken());
        encoder.encodeByteString("ServerNonce", openSecureChannelResponse._serverNonce);
    }

    public static OpenSecureChannelResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        UInteger _serverProtocolVersion = decoder.decodeUInt32("ServerProtocolVersion");
        ChannelSecurityToken _securityToken = decoder.decodeSerializable("SecurityToken", ChannelSecurityToken.class);
        ByteString _serverNonce = decoder.decodeByteString("ServerNonce");

        return new OpenSecureChannelResponse(_responseHeader, _serverProtocolVersion, _securityToken, _serverNonce);
    }

    static {
        DelegateRegistry.registerEncoder(OpenSecureChannelResponse::encode, OpenSecureChannelResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(OpenSecureChannelResponse::decode, OpenSecureChannelResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
