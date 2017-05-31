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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ModifySubscriptionRequest")
public class ModifySubscriptionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ModifySubscriptionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ModifySubscriptionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModifySubscriptionRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final Double _requestedPublishingInterval;
    protected final UInteger _requestedLifetimeCount;
    protected final UInteger _requestedMaxKeepAliveCount;
    protected final UInteger _maxNotificationsPerPublish;
    protected final UByte _priority;

    public ModifySubscriptionRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._requestedPublishingInterval = null;
        this._requestedLifetimeCount = null;
        this._requestedMaxKeepAliveCount = null;
        this._maxNotificationsPerPublish = null;
        this._priority = null;
    }

    public ModifySubscriptionRequest(RequestHeader _requestHeader, UInteger _subscriptionId, Double _requestedPublishingInterval, UInteger _requestedLifetimeCount, UInteger _requestedMaxKeepAliveCount, UInteger _maxNotificationsPerPublish, UByte _priority) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._requestedPublishingInterval = _requestedPublishingInterval;
        this._requestedLifetimeCount = _requestedLifetimeCount;
        this._requestedMaxKeepAliveCount = _requestedMaxKeepAliveCount;
        this._maxNotificationsPerPublish = _maxNotificationsPerPublish;
        this._priority = _priority;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public Double getRequestedPublishingInterval() { return _requestedPublishingInterval; }

    public UInteger getRequestedLifetimeCount() { return _requestedLifetimeCount; }

    public UInteger getRequestedMaxKeepAliveCount() { return _requestedMaxKeepAliveCount; }

    public UInteger getMaxNotificationsPerPublish() { return _maxNotificationsPerPublish; }

    public UByte getPriority() { return _priority; }

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
            .add("RequestedPublishingInterval", _requestedPublishingInterval)
            .add("RequestedLifetimeCount", _requestedLifetimeCount)
            .add("RequestedMaxKeepAliveCount", _requestedMaxKeepAliveCount)
            .add("MaxNotificationsPerPublish", _maxNotificationsPerPublish)
            .add("Priority", _priority)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ModifySubscriptionRequest> {
        @Override
        public ModifySubscriptionRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32();
            Double _requestedPublishingInterval = reader.readDouble();
            UInteger _requestedLifetimeCount = reader.readUInt32();
            UInteger _requestedMaxKeepAliveCount = reader.readUInt32();
            UInteger _maxNotificationsPerPublish = reader.readUInt32();
            UByte _priority = reader.readByte();

            return new ModifySubscriptionRequest(_requestHeader, _subscriptionId, _requestedPublishingInterval, _requestedLifetimeCount, _requestedMaxKeepAliveCount, _maxNotificationsPerPublish, _priority);
        }

        @Override
        public void encode(SerializationContext context, ModifySubscriptionRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32(encodable._subscriptionId);
            writer.writeDouble(encodable._requestedPublishingInterval);
            writer.writeUInt32(encodable._requestedLifetimeCount);
            writer.writeUInt32(encodable._requestedMaxKeepAliveCount);
            writer.writeUInt32(encodable._maxNotificationsPerPublish);
            writer.writeByte(encodable._priority);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ModifySubscriptionRequest> {
        @Override
        public ModifySubscriptionRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            UInteger _subscriptionId = reader.readUInt32("SubscriptionId");
            Double _requestedPublishingInterval = reader.readDouble("RequestedPublishingInterval");
            UInteger _requestedLifetimeCount = reader.readUInt32("RequestedLifetimeCount");
            UInteger _requestedMaxKeepAliveCount = reader.readUInt32("RequestedMaxKeepAliveCount");
            UInteger _maxNotificationsPerPublish = reader.readUInt32("MaxNotificationsPerPublish");
            UByte _priority = reader.readByte("Priority");

            return new ModifySubscriptionRequest(_requestHeader, _subscriptionId, _requestedPublishingInterval, _requestedLifetimeCount, _requestedMaxKeepAliveCount, _maxNotificationsPerPublish, _priority);
        }

        @Override
        public void encode(SerializationContext context, ModifySubscriptionRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeUInt32("SubscriptionId", encodable._subscriptionId);
            writer.writeDouble("RequestedPublishingInterval", encodable._requestedPublishingInterval);
            writer.writeUInt32("RequestedLifetimeCount", encodable._requestedLifetimeCount);
            writer.writeUInt32("RequestedMaxKeepAliveCount", encodable._requestedMaxKeepAliveCount);
            writer.writeUInt32("MaxNotificationsPerPublish", encodable._maxNotificationsPerPublish);
            writer.writeByte("Priority", encodable._priority);
        }
    }

}
