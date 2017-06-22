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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("RegisterServerRequest")
public class RegisterServerRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RegisterServerRequest;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterServerRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterServerRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final RegisteredServer _server;

    public RegisterServerRequest() {
        this._requestHeader = null;
        this._server = null;
    }

    public RegisterServerRequest(RequestHeader _requestHeader, RegisteredServer _server) {
        this._requestHeader = _requestHeader;
        this._server = _server;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public RegisteredServer getServer() { return _server; }

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
            .add("Server", _server)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RegisterServerRequest> {
        @Override
        public RegisterServerRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            RegisteredServer _server = (RegisteredServer) context.decode(RegisteredServer.BinaryEncodingId, reader);

            return new RegisterServerRequest(_requestHeader, _server);
        }

        @Override
        public void encode(SerializationContext context, RegisterServerRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            context.encode(RegisteredServer.BinaryEncodingId, value._server, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RegisterServerRequest> {
        @Override
        public RegisterServerRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            RegisteredServer _server = (RegisteredServer) context.decode(RegisteredServer.XmlEncodingId, reader);

            return new RegisterServerRequest(_requestHeader, _server);
        }

        @Override
        public void encode(SerializationContext context, RegisterServerRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            context.encode(RegisteredServer.XmlEncodingId, encodable._server, writer);
        }
    }

}
