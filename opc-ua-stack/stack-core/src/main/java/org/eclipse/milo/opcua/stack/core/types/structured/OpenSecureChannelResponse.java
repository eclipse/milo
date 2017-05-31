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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", _responseHeader)
            .add("ServerProtocolVersion", _serverProtocolVersion)
            .add("SecurityToken", _securityToken)
            .add("ServerNonce", _serverNonce)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<OpenSecureChannelResponse> {
        @Override
        public OpenSecureChannelResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);
            UInteger _serverProtocolVersion = reader.readUInt32();
            ChannelSecurityToken _securityToken = (ChannelSecurityToken) context.decode(ChannelSecurityToken.BinaryEncodingId, reader);
            ByteString _serverNonce = reader.readByteString();

            return new OpenSecureChannelResponse(_responseHeader, _serverProtocolVersion, _securityToken, _serverNonce);
        }

        @Override
        public void encode(SerializationContext context, OpenSecureChannelResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, encodable._responseHeader, writer);
            writer.writeUInt32(encodable._serverProtocolVersion);
            context.encode(ChannelSecurityToken.BinaryEncodingId, encodable._securityToken, writer);
            writer.writeByteString(encodable._serverNonce);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<OpenSecureChannelResponse> {
        @Override
        public OpenSecureChannelResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);
            UInteger _serverProtocolVersion = reader.readUInt32("ServerProtocolVersion");
            ChannelSecurityToken _securityToken = (ChannelSecurityToken) context.decode(ChannelSecurityToken.XmlEncodingId, reader);
            ByteString _serverNonce = reader.readByteString("ServerNonce");

            return new OpenSecureChannelResponse(_responseHeader, _serverProtocolVersion, _securityToken, _serverNonce);
        }

        @Override
        public void encode(SerializationContext context, OpenSecureChannelResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
            writer.writeUInt32("ServerProtocolVersion", encodable._serverProtocolVersion);
            context.encode(ChannelSecurityToken.XmlEncodingId, encodable._securityToken, writer);
            writer.writeByteString("ServerNonce", encodable._serverNonce);
        }
    }

}
