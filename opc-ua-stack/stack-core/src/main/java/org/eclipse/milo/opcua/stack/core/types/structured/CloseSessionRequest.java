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

@UaDataType("CloseSessionRequest")
public class CloseSessionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CloseSessionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CloseSessionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CloseSessionRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Boolean _deleteSubscriptions;

    public CloseSessionRequest() {
        this._requestHeader = null;
        this._deleteSubscriptions = null;
    }

    public CloseSessionRequest(RequestHeader _requestHeader, Boolean _deleteSubscriptions) {
        this._requestHeader = _requestHeader;
        this._deleteSubscriptions = _deleteSubscriptions;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Boolean getDeleteSubscriptions() { return _deleteSubscriptions; }

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
            .add("DeleteSubscriptions", _deleteSubscriptions)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CloseSessionRequest> {
        @Override
        public CloseSessionRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            Boolean _deleteSubscriptions = reader.readBoolean();

            return new CloseSessionRequest(_requestHeader, _deleteSubscriptions);
        }

        @Override
        public void encode(SerializationContext context, CloseSessionRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, encodable._requestHeader, writer);
            writer.writeBoolean(encodable._deleteSubscriptions);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CloseSessionRequest> {
        @Override
        public CloseSessionRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            Boolean _deleteSubscriptions = reader.readBoolean("DeleteSubscriptions");

            return new CloseSessionRequest(_requestHeader, _deleteSubscriptions);
        }

        @Override
        public void encode(SerializationContext context, CloseSessionRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeBoolean("DeleteSubscriptions", encodable._deleteSubscriptions);
        }
    }

}
