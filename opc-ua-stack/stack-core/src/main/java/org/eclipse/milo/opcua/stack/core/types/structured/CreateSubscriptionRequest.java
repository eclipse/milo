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

public class CreateSubscriptionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CreateSubscriptionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CreateSubscriptionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateSubscriptionRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final Double requestedPublishingInterval;
    protected final UInteger requestedLifetimeCount;
    protected final UInteger requestedMaxKeepAliveCount;
    protected final UInteger maxNotificationsPerPublish;
    protected final Boolean publishingEnabled;
    protected final UByte priority;

    public CreateSubscriptionRequest() {
        this.requestHeader = null;
        this.requestedPublishingInterval = null;
        this.requestedLifetimeCount = null;
        this.requestedMaxKeepAliveCount = null;
        this.maxNotificationsPerPublish = null;
        this.publishingEnabled = null;
        this.priority = null;
    }

    public CreateSubscriptionRequest(RequestHeader requestHeader, Double requestedPublishingInterval, UInteger requestedLifetimeCount, UInteger requestedMaxKeepAliveCount, UInteger maxNotificationsPerPublish, Boolean publishingEnabled, UByte priority) {
        this.requestHeader = requestHeader;
        this.requestedPublishingInterval = requestedPublishingInterval;
        this.requestedLifetimeCount = requestedLifetimeCount;
        this.requestedMaxKeepAliveCount = requestedMaxKeepAliveCount;
        this.maxNotificationsPerPublish = maxNotificationsPerPublish;
        this.publishingEnabled = publishingEnabled;
        this.priority = priority;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public Double getRequestedPublishingInterval() { return requestedPublishingInterval; }

    public UInteger getRequestedLifetimeCount() { return requestedLifetimeCount; }

    public UInteger getRequestedMaxKeepAliveCount() { return requestedMaxKeepAliveCount; }

    public UInteger getMaxNotificationsPerPublish() { return maxNotificationsPerPublish; }

    public Boolean getPublishingEnabled() { return publishingEnabled; }

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
            .add("RequestedPublishingInterval", requestedPublishingInterval)
            .add("RequestedLifetimeCount", requestedLifetimeCount)
            .add("RequestedMaxKeepAliveCount", requestedMaxKeepAliveCount)
            .add("MaxNotificationsPerPublish", maxNotificationsPerPublish)
            .add("PublishingEnabled", publishingEnabled)
            .add("Priority", priority)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CreateSubscriptionRequest> {

        @Override
        public Class<CreateSubscriptionRequest> getType() {
            return CreateSubscriptionRequest.class;
        }

        @Override
        public CreateSubscriptionRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            Double requestedPublishingInterval = decoder.readDouble("RequestedPublishingInterval");
            UInteger requestedLifetimeCount = decoder.readUInt32("RequestedLifetimeCount");
            UInteger requestedMaxKeepAliveCount = decoder.readUInt32("RequestedMaxKeepAliveCount");
            UInteger maxNotificationsPerPublish = decoder.readUInt32("MaxNotificationsPerPublish");
            Boolean publishingEnabled = decoder.readBoolean("PublishingEnabled");
            UByte priority = decoder.readByte("Priority");

            return new CreateSubscriptionRequest(requestHeader, requestedPublishingInterval, requestedLifetimeCount, requestedMaxKeepAliveCount, maxNotificationsPerPublish, publishingEnabled, priority);
        }

        @Override
        public void encode(CreateSubscriptionRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeDouble("RequestedPublishingInterval", value.requestedPublishingInterval);
            encoder.writeUInt32("RequestedLifetimeCount", value.requestedLifetimeCount);
            encoder.writeUInt32("RequestedMaxKeepAliveCount", value.requestedMaxKeepAliveCount);
            encoder.writeUInt32("MaxNotificationsPerPublish", value.maxNotificationsPerPublish);
            encoder.writeBoolean("PublishingEnabled", value.publishingEnabled);
            encoder.writeByte("Priority", value.priority);
        }
    }

}
