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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class RegisterServerRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RegisterServerRequest;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterServerRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterServerRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final RegisteredServer server;

    public RegisterServerRequest() {
        this.requestHeader = null;
        this.server = null;
    }

    public RegisterServerRequest(RequestHeader requestHeader, RegisteredServer server) {
        this.requestHeader = requestHeader;
        this.server = server;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public RegisteredServer getServer() { return server; }

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
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RegisterServerRequest> {

        @Override
        public Class<RegisterServerRequest> getType() {
            return RegisterServerRequest.class;
        }

        @Override
        public RegisterServerRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            RegisteredServer server = (RegisteredServer) decoder.readBuiltinStruct("Server", RegisteredServer.class);

            return new RegisterServerRequest(requestHeader, server);
        }

        @Override
        public void encode(RegisterServerRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStruct("Server", value.server, RegisteredServer.class);
        }
    }

}
