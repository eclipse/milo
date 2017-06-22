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

@UaDataType("CloseSecureChannelRequest")
public class CloseSecureChannelRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CloseSecureChannelRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CloseSecureChannelRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CloseSecureChannelRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;

    public CloseSecureChannelRequest() {
        this._requestHeader = null;
    }

    public CloseSecureChannelRequest(RequestHeader _requestHeader) {
        this._requestHeader = _requestHeader;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

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
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CloseSecureChannelRequest> {
        @Override
        public CloseSecureChannelRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);

            return new CloseSecureChannelRequest(_requestHeader);
        }

        @Override
        public void encode(SerializationContext context, CloseSecureChannelRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CloseSecureChannelRequest> {
        @Override
        public CloseSecureChannelRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);

            return new CloseSecureChannelRequest(_requestHeader);
        }

        @Override
        public void encode(SerializationContext context, CloseSecureChannelRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
        }
    }

}
