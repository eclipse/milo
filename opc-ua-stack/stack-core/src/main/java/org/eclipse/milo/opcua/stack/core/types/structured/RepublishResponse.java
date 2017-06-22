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

@UaDataType("RepublishResponse")
public class RepublishResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.RepublishResponse;
    public static final NodeId BinaryEncodingId = Identifiers.RepublishResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RepublishResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final NotificationMessage _notificationMessage;

    public RepublishResponse() {
        this._responseHeader = null;
        this._notificationMessage = null;
    }

    public RepublishResponse(ResponseHeader _responseHeader, NotificationMessage _notificationMessage) {
        this._responseHeader = _responseHeader;
        this._notificationMessage = _notificationMessage;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public NotificationMessage getNotificationMessage() { return _notificationMessage; }

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
            .add("NotificationMessage", _notificationMessage)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RepublishResponse> {
        @Override
        public RepublishResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);
            NotificationMessage _notificationMessage = (NotificationMessage) context.decode(NotificationMessage.BinaryEncodingId, reader);

            return new RepublishResponse(_responseHeader, _notificationMessage);
        }

        @Override
        public void encode(SerializationContext context, RepublishResponse value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, value._responseHeader, writer);
            context.encode(NotificationMessage.BinaryEncodingId, value._notificationMessage, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RepublishResponse> {
        @Override
        public RepublishResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);
            NotificationMessage _notificationMessage = (NotificationMessage) context.decode(NotificationMessage.XmlEncodingId, reader);

            return new RepublishResponse(_responseHeader, _notificationMessage);
        }

        @Override
        public void encode(SerializationContext context, RepublishResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
            context.encode(NotificationMessage.XmlEncodingId, encodable._notificationMessage, writer);
        }
    }

}
