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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

@UaDataType("CreateMonitoredItemsRequest")
public class CreateMonitoredItemsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CreateMonitoredItemsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CreateMonitoredItemsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateMonitoredItemsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final TimestampsToReturn _timestampsToReturn;
    protected final MonitoredItemCreateRequest[] _itemsToCreate;

    public CreateMonitoredItemsRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._timestampsToReturn = null;
        this._itemsToCreate = null;
    }

    public CreateMonitoredItemsRequest(RequestHeader _requestHeader, UInteger _subscriptionId, TimestampsToReturn _timestampsToReturn, MonitoredItemCreateRequest[] _itemsToCreate) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._timestampsToReturn = _timestampsToReturn;
        this._itemsToCreate = _itemsToCreate;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public TimestampsToReturn getTimestampsToReturn() { return _timestampsToReturn; }

    @Nullable
    public MonitoredItemCreateRequest[] getItemsToCreate() { return _itemsToCreate; }

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
            .add("TimestampsToReturn", _timestampsToReturn)
            .add("ItemsToCreate", _itemsToCreate)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CreateMonitoredItemsRequest> {
        @Override
        public CreateMonitoredItemsRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32();
            TimestampsToReturn _timestampsToReturn = TimestampsToReturn.from(reader.readInt32());
            MonitoredItemCreateRequest[] _itemsToCreate =
                reader.readArray(
                    () -> (MonitoredItemCreateRequest) context.decode(
                        MonitoredItemCreateRequest.BinaryEncodingId, reader),
                    MonitoredItemCreateRequest.class
                );

            return new CreateMonitoredItemsRequest(_requestHeader, _subscriptionId, _timestampsToReturn, _itemsToCreate);
        }

        @Override
        public void encode(SerializationContext context, CreateMonitoredItemsRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeUInt32(value._subscriptionId);
            writer.writeInt32(value._timestampsToReturn != null ? value._timestampsToReturn.getValue() : 0);
            writer.writeArray(
                value._itemsToCreate,
                e -> context.encode(MonitoredItemCreateRequest.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CreateMonitoredItemsRequest> {
        @Override
        public CreateMonitoredItemsRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32("SubscriptionId");
            TimestampsToReturn _timestampsToReturn = TimestampsToReturn.from(reader.readInt32("TimestampsToReturn"));
            MonitoredItemCreateRequest[] _itemsToCreate =
                reader.readArray(
                    "ItemsToCreate",
                    f -> (MonitoredItemCreateRequest) context.decode(
                        MonitoredItemCreateRequest.XmlEncodingId, reader),
                    MonitoredItemCreateRequest.class
                );

            return new CreateMonitoredItemsRequest(_requestHeader, _subscriptionId, _timestampsToReturn, _itemsToCreate);
        }

        @Override
        public void encode(SerializationContext context, CreateMonitoredItemsRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32("SubscriptionId", encodable._subscriptionId);
            writer.writeInt32("TimestampsToReturn", encodable._timestampsToReturn != null ? encodable._timestampsToReturn.getValue() : 0);
            writer.writeArray(
                "ItemsToCreate",
                encodable._itemsToCreate,
                (f, e) -> context.encode(MonitoredItemCreateRequest.XmlEncodingId, e, writer)
            );
        }
    }

}
