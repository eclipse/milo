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

@UaDataType("ModifyMonitoredItemsRequest")
public class ModifyMonitoredItemsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ModifyMonitoredItemsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ModifyMonitoredItemsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModifyMonitoredItemsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final TimestampsToReturn _timestampsToReturn;
    protected final MonitoredItemModifyRequest[] _itemsToModify;

    public ModifyMonitoredItemsRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._timestampsToReturn = null;
        this._itemsToModify = null;
    }

    public ModifyMonitoredItemsRequest(RequestHeader _requestHeader, UInteger _subscriptionId, TimestampsToReturn _timestampsToReturn, MonitoredItemModifyRequest[] _itemsToModify) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._timestampsToReturn = _timestampsToReturn;
        this._itemsToModify = _itemsToModify;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public TimestampsToReturn getTimestampsToReturn() { return _timestampsToReturn; }

    @Nullable
    public MonitoredItemModifyRequest[] getItemsToModify() { return _itemsToModify; }

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
            .add("ItemsToModify", _itemsToModify)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ModifyMonitoredItemsRequest> {
        @Override
        public ModifyMonitoredItemsRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32();
            TimestampsToReturn _timestampsToReturn = TimestampsToReturn.from(reader.readInt32());
            MonitoredItemModifyRequest[] _itemsToModify =
                reader.readArray(
                    () -> (MonitoredItemModifyRequest) context.decode(
                        MonitoredItemModifyRequest.BinaryEncodingId, reader),
                    MonitoredItemModifyRequest.class
                );

            return new ModifyMonitoredItemsRequest(_requestHeader, _subscriptionId, _timestampsToReturn, _itemsToModify);
        }

        @Override
        public void encode(SerializationContext context, ModifyMonitoredItemsRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeUInt32(value._subscriptionId);
            writer.writeInt32(value._timestampsToReturn != null ? value._timestampsToReturn.getValue() : 0);
            writer.writeArray(
                value._itemsToModify,
                e -> context.encode(MonitoredItemModifyRequest.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ModifyMonitoredItemsRequest> {
        @Override
        public ModifyMonitoredItemsRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32("SubscriptionId");
            TimestampsToReturn _timestampsToReturn = TimestampsToReturn.from(reader.readInt32("TimestampsToReturn"));
            MonitoredItemModifyRequest[] _itemsToModify =
                reader.readArray(
                    "ItemsToModify",
                    f -> (MonitoredItemModifyRequest) context.decode(
                        MonitoredItemModifyRequest.XmlEncodingId, reader),
                    MonitoredItemModifyRequest.class
                );

            return new ModifyMonitoredItemsRequest(_requestHeader, _subscriptionId, _timestampsToReturn, _itemsToModify);
        }

        @Override
        public void encode(SerializationContext context, ModifyMonitoredItemsRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32("SubscriptionId", encodable._subscriptionId);
            writer.writeInt32("TimestampsToReturn", encodable._timestampsToReturn != null ? encodable._timestampsToReturn.getValue() : 0);
            writer.writeArray(
                "ItemsToModify",
                encodable._itemsToModify,
                (f, e) -> context.encode(MonitoredItemModifyRequest.XmlEncodingId, e, writer)
            );
        }
    }

}
