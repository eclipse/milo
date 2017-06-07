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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("CancelRequest")
public class CancelRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CancelRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CancelRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CancelRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _requestHandle;

    public CancelRequest() {
        this._requestHeader = null;
        this._requestHandle = null;
    }

    public CancelRequest(RequestHeader _requestHeader, UInteger _requestHandle) {
        this._requestHeader = _requestHeader;
        this._requestHandle = _requestHandle;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getRequestHandle() { return _requestHandle; }

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
            .add("RequestHandle", _requestHandle)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CancelRequest> {
        @Override
        public CancelRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            UInteger _requestHandle = reader.readUInt32();

            return new CancelRequest(_requestHeader, _requestHandle);
        }

        @Override
        public void encode(SerializationContext context, CancelRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeUInt32(value._requestHandle);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CancelRequest> {
        @Override
        public CancelRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            UInteger _requestHandle = reader.readUInt32("RequestHandle");

            return new CancelRequest(_requestHeader, _requestHandle);
        }

        @Override
        public void encode(SerializationContext context, CancelRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32("RequestHandle", encodable._requestHandle);
        }
    }

}
