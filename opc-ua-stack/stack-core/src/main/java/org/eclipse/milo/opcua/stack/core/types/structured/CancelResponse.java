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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("CancelResponse")
public class CancelResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.CancelResponse;
    public static final NodeId BinaryEncodingId = Identifiers.CancelResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CancelResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final UInteger _cancelCount;

    public CancelResponse() {
        this._responseHeader = null;
        this._cancelCount = null;
    }

    public CancelResponse(ResponseHeader _responseHeader, UInteger _cancelCount) {
        this._responseHeader = _responseHeader;
        this._cancelCount = _cancelCount;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public UInteger getCancelCount() { return _cancelCount; }

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
            .add("CancelCount", _cancelCount)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CancelResponse> {
        @Override
        public CancelResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);
            UInteger _cancelCount = reader.readUInt32();

            return new CancelResponse(_responseHeader, _cancelCount);
        }

        @Override
        public void encode(SerializationContext context, CancelResponse value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, value._responseHeader, writer);
            writer.writeUInt32(value._cancelCount);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CancelResponse> {
        @Override
        public CancelResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);
            UInteger _cancelCount = reader.readUInt32("CancelCount");

            return new CancelResponse(_responseHeader, _cancelCount);
        }

        @Override
        public void encode(SerializationContext context, CancelResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
            writer.writeUInt32("CancelCount", encodable._cancelCount);
        }
    }

}
