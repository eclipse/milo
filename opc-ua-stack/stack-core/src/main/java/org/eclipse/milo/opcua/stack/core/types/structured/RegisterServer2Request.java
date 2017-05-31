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

import javax.annotation.Nullable;

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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("RegisterServer2Request")
public class RegisterServer2Request implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RegisterServer2Request;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterServer2Request_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterServer2Request_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final RegisteredServer _server;
    protected final ExtensionObject[] _discoveryConfiguration;

    public RegisterServer2Request() {
        this._requestHeader = null;
        this._server = null;
        this._discoveryConfiguration = null;
    }

    public RegisterServer2Request(RequestHeader _requestHeader, RegisteredServer _server, ExtensionObject[] _discoveryConfiguration) {
        this._requestHeader = _requestHeader;
        this._server = _server;
        this._discoveryConfiguration = _discoveryConfiguration;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public RegisteredServer getServer() { return _server; }

    @Nullable
    public ExtensionObject[] getDiscoveryConfiguration() { return _discoveryConfiguration; }

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
            .add("DiscoveryConfiguration", _discoveryConfiguration)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RegisterServer2Request> {
        @Override
        public RegisterServer2Request decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            RegisteredServer _server = (RegisteredServer) context.decode(RegisteredServer.BinaryEncodingId, reader);
            ExtensionObject[] _discoveryConfiguration = reader.readArray(reader::readExtensionObject, ExtensionObject.class);

            return new RegisterServer2Request(_requestHeader, _server, _discoveryConfiguration);
        }

        @Override
        public void encode(SerializationContext context, RegisterServer2Request encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, encodable._requestHeader, writer);
            context.encode(RegisteredServer.BinaryEncodingId, encodable._server, writer);
            writer.writeArray(encodable._discoveryConfiguration, writer::writeExtensionObject);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RegisterServer2Request> {
        @Override
        public RegisterServer2Request decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            RegisteredServer _server = (RegisteredServer) context.decode(RegisteredServer.XmlEncodingId, reader);
            ExtensionObject[] _discoveryConfiguration = reader.readArray("DiscoveryConfiguration", reader::readExtensionObject, ExtensionObject.class);

            return new RegisterServer2Request(_requestHeader, _server, _discoveryConfiguration);
        }

        @Override
        public void encode(SerializationContext context, RegisterServer2Request encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            context.encode(RegisteredServer.XmlEncodingId, encodable._server, writer);
            writer.writeArray("DiscoveryConfiguration", encodable._discoveryConfiguration, writer::writeExtensionObject);
        }
    }

}
