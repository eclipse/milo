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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
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

@UaDataType("GetEndpointsResponse")
public class GetEndpointsResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.GetEndpointsResponse;
    public static final NodeId BinaryEncodingId = Identifiers.GetEndpointsResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.GetEndpointsResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final EndpointDescription[] _endpoints;

    public GetEndpointsResponse() {
        this._responseHeader = null;
        this._endpoints = null;
    }

    public GetEndpointsResponse(ResponseHeader _responseHeader, EndpointDescription[] _endpoints) {
        this._responseHeader = _responseHeader;
        this._endpoints = _endpoints;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    @Nullable
    public EndpointDescription[] getEndpoints() { return _endpoints; }

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
            .add("Endpoints", _endpoints)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<GetEndpointsResponse> {
        @Override
        public GetEndpointsResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            EndpointDescription[] _endpoints =
                reader.readArray(
                    () -> (EndpointDescription) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EndpointDescription", reader),
                    EndpointDescription.class
                );

            return new GetEndpointsResponse(_responseHeader, _endpoints);
        }

        @Override
        public void encode(SerializationContext context, GetEndpointsResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeArray(
                encodable._endpoints,
                e -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EndpointDescription", e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<GetEndpointsResponse> {
        @Override
        public GetEndpointsResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            EndpointDescription[] _endpoints =
                reader.readArray(
                    "Endpoints",
                    f -> (EndpointDescription) context.decode(
                        OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EndpointDescription", reader),
                    EndpointDescription.class
                );

            return new GetEndpointsResponse(_responseHeader, _endpoints);
        }

        @Override
        public void encode(SerializationContext context, GetEndpointsResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeArray(
                "Endpoints",
                encodable._endpoints,
                (f, e) -> context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EndpointDescription", e, writer)
            );
        }
    }

}
