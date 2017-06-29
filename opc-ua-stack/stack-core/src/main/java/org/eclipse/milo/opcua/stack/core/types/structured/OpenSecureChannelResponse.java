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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class OpenSecureChannelResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.OpenSecureChannelResponse;
    public static final NodeId BinaryEncodingId = Identifiers.OpenSecureChannelResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.OpenSecureChannelResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final UInteger serverProtocolVersion;
    protected final ChannelSecurityToken securityToken;
    protected final ByteString serverNonce;

    public OpenSecureChannelResponse() {
        this.responseHeader = null;
        this.serverProtocolVersion = null;
        this.securityToken = null;
        this.serverNonce = null;
    }

    public OpenSecureChannelResponse(ResponseHeader responseHeader, UInteger serverProtocolVersion, ChannelSecurityToken securityToken, ByteString serverNonce) {
        this.responseHeader = responseHeader;
        this.serverProtocolVersion = serverProtocolVersion;
        this.securityToken = securityToken;
        this.serverNonce = serverNonce;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    public UInteger getServerProtocolVersion() { return serverProtocolVersion; }

    public ChannelSecurityToken getSecurityToken() { return securityToken; }

    public ByteString getServerNonce() { return serverNonce; }

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
            .add("ServerProtocolVersion", serverProtocolVersion)
            .add("SecurityToken", securityToken)
            .add("ServerNonce", serverNonce)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<OpenSecureChannelResponse> {

        @Override
        public Class<OpenSecureChannelResponse> getType() {
            return OpenSecureChannelResponse.class;
        }

        @Override
        public OpenSecureChannelResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            UInteger serverProtocolVersion = decoder.readUInt32("ServerProtocolVersion");
            ChannelSecurityToken securityToken = (ChannelSecurityToken) decoder.readBuiltinStruct("SecurityToken", ChannelSecurityToken.class);
            ByteString serverNonce = decoder.readByteString("ServerNonce");

            return new OpenSecureChannelResponse(responseHeader, serverProtocolVersion, securityToken, serverNonce);
        }

        @Override
        public void encode(OpenSecureChannelResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeUInt32("ServerProtocolVersion", value.serverProtocolVersion);
            encoder.writeBuiltinStruct("SecurityToken", value.securityToken, ChannelSecurityToken.class);
            encoder.writeByteString("ServerNonce", value.serverNonce);
        }
    }

}
