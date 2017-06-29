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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ModifySubscriptionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ModifySubscriptionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ModifySubscriptionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModifySubscriptionRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger subscriptionId;
    protected final Double requestedPublishingInterval;
    protected final UInteger requestedLifetimeCount;
    protected final UInteger requestedMaxKeepAliveCount;
    protected final UInteger maxNotificationsPerPublish;
    protected final UByte priority;

    public ModifySubscriptionRequest() {
        this.requestHeader = null;
        this.subscriptionId = null;
        this.requestedPublishingInterval = null;
        this.requestedLifetimeCount = null;
        this.requestedMaxKeepAliveCount = null;
        this.maxNotificationsPerPublish = null;
        this.priority = null;
    }

    public ModifySubscriptionRequest(RequestHeader requestHeader, UInteger subscriptionId, Double requestedPublishingInterval, UInteger requestedLifetimeCount, UInteger requestedMaxKeepAliveCount, UInteger maxNotificationsPerPublish, UByte priority) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.requestedPublishingInterval = requestedPublishingInterval;
        this.requestedLifetimeCount = requestedLifetimeCount;
        this.requestedMaxKeepAliveCount = requestedMaxKeepAliveCount;
        this.maxNotificationsPerPublish = maxNotificationsPerPublish;
        this.priority = priority;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public UInteger getSubscriptionId() { return subscriptionId; }

    public Double getRequestedPublishingInterval() { return requestedPublishingInterval; }

    public UInteger getRequestedLifetimeCount() { return requestedLifetimeCount; }

    public UInteger getRequestedMaxKeepAliveCount() { return requestedMaxKeepAliveCount; }

    public UInteger getMaxNotificationsPerPublish() { return maxNotificationsPerPublish; }

    public UByte getPriority() { return priority; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", requestHeader)
            .add("SubscriptionId", subscriptionId)
            .add("RequestedPublishingInterval", requestedPublishingInterval)
            .add("RequestedLifetimeCount", requestedLifetimeCount)
            .add("RequestedMaxKeepAliveCount", requestedMaxKeepAliveCount)
            .add("MaxNotificationsPerPublish", maxNotificationsPerPublish)
            .add("Priority", priority)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ModifySubscriptionRequest> {

        @Override
        public Class<ModifySubscriptionRequest> getType() {
            return ModifySubscriptionRequest.class;
        }

        @Override
        public ModifySubscriptionRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            Double requestedPublishingInterval = decoder.readDouble("RequestedPublishingInterval");
            UInteger requestedLifetimeCount = decoder.readUInt32("RequestedLifetimeCount");
            UInteger requestedMaxKeepAliveCount = decoder.readUInt32("RequestedMaxKeepAliveCount");
            UInteger maxNotificationsPerPublish = decoder.readUInt32("MaxNotificationsPerPublish");
            UByte priority = decoder.readByte("Priority");

            return new ModifySubscriptionRequest(requestHeader, subscriptionId, requestedPublishingInterval, requestedLifetimeCount, requestedMaxKeepAliveCount, maxNotificationsPerPublish, priority);
        }

        @Override
        public void encode(ModifySubscriptionRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeDouble("RequestedPublishingInterval", value.requestedPublishingInterval);
            encoder.writeUInt32("RequestedLifetimeCount", value.requestedLifetimeCount);
            encoder.writeUInt32("RequestedMaxKeepAliveCount", value.requestedMaxKeepAliveCount);
            encoder.writeUInt32("MaxNotificationsPerPublish", value.maxNotificationsPerPublish);
            encoder.writeByte("Priority", value.priority);
        }
    }

}
