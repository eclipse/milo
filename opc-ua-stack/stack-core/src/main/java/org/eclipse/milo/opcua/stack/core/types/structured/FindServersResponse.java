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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("FindServersResponse")
public class FindServersResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.FindServersResponse;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final ApplicationDescription[] _servers;

    public FindServersResponse() {
        this._responseHeader = null;
        this._servers = null;
    }

    public FindServersResponse(ResponseHeader _responseHeader, ApplicationDescription[] _servers) {
        this._responseHeader = _responseHeader;
        this._servers = _servers;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    @Nullable
    public ApplicationDescription[] getServers() { return _servers; }

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
            .add("Servers", _servers)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<FindServersResponse> {
        @Override
        public FindServersResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);
            ApplicationDescription[] _servers =
                reader.readArray(
                    () -> (ApplicationDescription) context.decode(
                        ApplicationDescription.BinaryEncodingId, reader),
                    ApplicationDescription.class
                );

            return new FindServersResponse(_responseHeader, _servers);
        }

        @Override
        public void encode(SerializationContext context, FindServersResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, encodable._responseHeader, writer);
            writer.writeArray(
                encodable._servers,
                e -> context.encode(ApplicationDescription.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<FindServersResponse> {
        @Override
        public FindServersResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);
            ApplicationDescription[] _servers =
                reader.readArray(
                    "Servers",
                    f -> (ApplicationDescription) context.decode(
                        ApplicationDescription.XmlEncodingId, reader),
                    ApplicationDescription.class
                );

            return new FindServersResponse(_responseHeader, _servers);
        }

        @Override
        public void encode(SerializationContext context, FindServersResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
            writer.writeArray(
                "Servers",
                encodable._servers,
                (f, e) -> context.encode(ApplicationDescription.XmlEncodingId, e, writer)
            );
        }
    }

}
