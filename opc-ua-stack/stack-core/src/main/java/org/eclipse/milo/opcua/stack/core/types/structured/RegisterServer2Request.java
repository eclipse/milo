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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class RegisterServer2Request implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RegisterServer2Request;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterServer2Request_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterServer2Request_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final RegisteredServer server;
    protected final ExtensionObject[] discoveryConfiguration;

    public RegisterServer2Request() {
        this.requestHeader = null;
        this.server = null;
        this.discoveryConfiguration = null;
    }

    public RegisterServer2Request(RequestHeader requestHeader, RegisteredServer server, ExtensionObject[] discoveryConfiguration) {
        this.requestHeader = requestHeader;
        this.server = server;
        this.discoveryConfiguration = discoveryConfiguration;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public RegisteredServer getServer() { return server; }

    @Nullable
    public ExtensionObject[] getDiscoveryConfiguration() { return discoveryConfiguration; }

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
            .add("Server", server)
            .add("DiscoveryConfiguration", discoveryConfiguration)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RegisterServer2Request> {

        @Override
        public Class<RegisterServer2Request> getType() {
            return RegisterServer2Request.class;
        }

        @Override
        public RegisterServer2Request decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            RegisteredServer server = (RegisteredServer) decoder.readBuiltinStruct("Server", RegisteredServer.class);
            ExtensionObject[] discoveryConfiguration = decoder.readArray("DiscoveryConfiguration", decoder::readExtensionObject, ExtensionObject.class);

            return new RegisterServer2Request(requestHeader, server, discoveryConfiguration);
        }

        @Override
        public void encode(RegisterServer2Request value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStruct("Server", value.server, RegisteredServer.class);
            encoder.writeArray("DiscoveryConfiguration", value.discoveryConfiguration, encoder::writeExtensionObject);
        }
    }

}
