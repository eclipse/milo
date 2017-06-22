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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("WriteRequest")
public class WriteRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.WriteRequest;
    public static final NodeId BinaryEncodingId = Identifiers.WriteRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.WriteRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final WriteValue[] _nodesToWrite;

    public WriteRequest() {
        this._requestHeader = null;
        this._nodesToWrite = null;
    }

    public WriteRequest(RequestHeader _requestHeader, WriteValue[] _nodesToWrite) {
        this._requestHeader = _requestHeader;
        this._nodesToWrite = _nodesToWrite;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public WriteValue[] getNodesToWrite() { return _nodesToWrite; }

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
            .add("NodesToWrite", _nodesToWrite)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<WriteRequest> {
        @Override
        public WriteRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            WriteValue[] _nodesToWrite =
                reader.readArray(
                    () -> (WriteValue) context.decode(
                        WriteValue.BinaryEncodingId, reader),
                    WriteValue.class
                );

            return new WriteRequest(_requestHeader, _nodesToWrite);
        }

        @Override
        public void encode(SerializationContext context, WriteRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeArray(
                value._nodesToWrite,
                e -> context.encode(WriteValue.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<WriteRequest> {
        @Override
        public WriteRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            WriteValue[] _nodesToWrite =
                reader.readArray(
                    "NodesToWrite",
                    f -> (WriteValue) context.decode(
                        WriteValue.XmlEncodingId, reader),
                    WriteValue.class
                );

            return new WriteRequest(_requestHeader, _nodesToWrite);
        }

        @Override
        public void encode(SerializationContext context, WriteRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeArray(
                "NodesToWrite",
                encodable._nodesToWrite,
                (f, e) -> context.encode(WriteValue.XmlEncodingId, e, writer)
            );
        }
    }

}
