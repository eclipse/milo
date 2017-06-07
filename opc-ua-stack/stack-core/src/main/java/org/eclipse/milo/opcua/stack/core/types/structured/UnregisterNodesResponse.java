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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("UnregisterNodesResponse")
public class UnregisterNodesResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.UnregisterNodesResponse;
    public static final NodeId BinaryEncodingId = Identifiers.UnregisterNodesResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UnregisterNodesResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;

    public UnregisterNodesResponse() {
        this._responseHeader = null;
    }

    public UnregisterNodesResponse(ResponseHeader _responseHeader) {
        this._responseHeader = _responseHeader;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

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
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<UnregisterNodesResponse> {
        @Override
        public UnregisterNodesResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);

            return new UnregisterNodesResponse(_responseHeader);
        }

        @Override
        public void encode(SerializationContext context, UnregisterNodesResponse value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, value._responseHeader, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<UnregisterNodesResponse> {
        @Override
        public UnregisterNodesResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);

            return new UnregisterNodesResponse(_responseHeader);
        }

        @Override
        public void encode(SerializationContext context, UnregisterNodesResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
        }
    }

}
