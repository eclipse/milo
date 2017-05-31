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

@UaDataType("RepublishRequest")
public class RepublishRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RepublishRequest;
    public static final NodeId BinaryEncodingId = Identifiers.RepublishRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RepublishRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final UInteger _retransmitSequenceNumber;

    public RepublishRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._retransmitSequenceNumber = null;
    }

    public RepublishRequest(RequestHeader _requestHeader, UInteger _subscriptionId, UInteger _retransmitSequenceNumber) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._retransmitSequenceNumber = _retransmitSequenceNumber;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public UInteger getRetransmitSequenceNumber() { return _retransmitSequenceNumber; }

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
            .add("SubscriptionId", _subscriptionId)
            .add("RetransmitSequenceNumber", _retransmitSequenceNumber)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RepublishRequest> {
        @Override
        public RepublishRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32();
            UInteger _retransmitSequenceNumber = reader.readUInt32();

            return new RepublishRequest(_requestHeader, _subscriptionId, _retransmitSequenceNumber);
        }

        @Override
        public void encode(SerializationContext context, RepublishRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32(encodable._subscriptionId);
            writer.writeUInt32(encodable._retransmitSequenceNumber);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RepublishRequest> {
        @Override
        public RepublishRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32("SubscriptionId");
            UInteger _retransmitSequenceNumber = reader.readUInt32("RetransmitSequenceNumber");

            return new RepublishRequest(_requestHeader, _subscriptionId, _retransmitSequenceNumber);
        }

        @Override
        public void encode(SerializationContext context, RepublishRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32("SubscriptionId", encodable._subscriptionId);
            writer.writeUInt32("RetransmitSequenceNumber", encodable._retransmitSequenceNumber);
        }
    }

}
