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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("DeleteMonitoredItemsRequest")
public class DeleteMonitoredItemsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteMonitoredItemsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteMonitoredItemsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteMonitoredItemsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final UInteger[] _monitoredItemIds;

    public DeleteMonitoredItemsRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._monitoredItemIds = null;
    }

    public DeleteMonitoredItemsRequest(RequestHeader _requestHeader, UInteger _subscriptionId, UInteger[] _monitoredItemIds) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._monitoredItemIds = _monitoredItemIds;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    @Nullable
    public UInteger[] getMonitoredItemIds() { return _monitoredItemIds; }

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
            .add("MonitoredItemIds", _monitoredItemIds)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DeleteMonitoredItemsRequest> {
        @Override
        public DeleteMonitoredItemsRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32();
            UInteger[] _monitoredItemIds = reader.readArray(reader::readUInt32, UInteger.class);

            return new DeleteMonitoredItemsRequest(_requestHeader, _subscriptionId, _monitoredItemIds);
        }

        @Override
        public void encode(SerializationContext context, DeleteMonitoredItemsRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeUInt32(value._subscriptionId);
            writer.writeArray(value._monitoredItemIds, writer::writeUInt32);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DeleteMonitoredItemsRequest> {
        @Override
        public DeleteMonitoredItemsRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32("SubscriptionId");
            UInteger[] _monitoredItemIds = reader.readArray("MonitoredItemIds", reader::readUInt32, UInteger.class);

            return new DeleteMonitoredItemsRequest(_requestHeader, _subscriptionId, _monitoredItemIds);
        }

        @Override
        public void encode(SerializationContext context, DeleteMonitoredItemsRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32("SubscriptionId", encodable._subscriptionId);
            writer.writeArray("MonitoredItemIds", encodable._monitoredItemIds, writer::writeUInt32);
        }
    }

}
