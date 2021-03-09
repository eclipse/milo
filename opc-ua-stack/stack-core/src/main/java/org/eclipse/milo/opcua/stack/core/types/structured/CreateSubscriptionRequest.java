/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class CreateSubscriptionRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=785");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=787");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=786");

    private final RequestHeader requestHeader;

    private final Double requestedPublishingInterval;

    private final UInteger requestedLifetimeCount;

    private final UInteger requestedMaxKeepAliveCount;

    private final UInteger maxNotificationsPerPublish;

    private final Boolean publishingEnabled;

    private final UByte priority;

    public CreateSubscriptionRequest(RequestHeader requestHeader, Double requestedPublishingInterval,
                                     UInteger requestedLifetimeCount, UInteger requestedMaxKeepAliveCount,
                                     UInteger maxNotificationsPerPublish, Boolean publishingEnabled, UByte priority) {
        this.requestHeader = requestHeader;
        this.requestedPublishingInterval = requestedPublishingInterval;
        this.requestedLifetimeCount = requestedLifetimeCount;
        this.requestedMaxKeepAliveCount = requestedMaxKeepAliveCount;
        this.maxNotificationsPerPublish = maxNotificationsPerPublish;
        this.publishingEnabled = publishingEnabled;
        this.priority = priority;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public Double getRequestedPublishingInterval() {
        return requestedPublishingInterval;
    }

    public UInteger getRequestedLifetimeCount() {
        return requestedLifetimeCount;
    }

    public UInteger getRequestedMaxKeepAliveCount() {
        return requestedMaxKeepAliveCount;
    }

    public UInteger getMaxNotificationsPerPublish() {
        return maxNotificationsPerPublish;
    }

    public Boolean getPublishingEnabled() {
        return publishingEnabled;
    }

    public UByte getPriority() {
        return priority;
    }

    public static final class Codec extends GenericDataTypeCodec<CreateSubscriptionRequest> {
        @Override
        public Class<CreateSubscriptionRequest> getType() {
            return CreateSubscriptionRequest.class;
        }

        @Override
        public CreateSubscriptionRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            Double requestedPublishingInterval = decoder.readDouble("RequestedPublishingInterval");
            UInteger requestedLifetimeCount = decoder.readUInt32("RequestedLifetimeCount");
            UInteger requestedMaxKeepAliveCount = decoder.readUInt32("RequestedMaxKeepAliveCount");
            UInteger maxNotificationsPerPublish = decoder.readUInt32("MaxNotificationsPerPublish");
            Boolean publishingEnabled = decoder.readBoolean("PublishingEnabled");
            UByte priority = decoder.readByte("Priority");
            return new CreateSubscriptionRequest(requestHeader, requestedPublishingInterval, requestedLifetimeCount, requestedMaxKeepAliveCount, maxNotificationsPerPublish, publishingEnabled, priority);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           CreateSubscriptionRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeDouble("RequestedPublishingInterval", value.getRequestedPublishingInterval());
            encoder.writeUInt32("RequestedLifetimeCount", value.getRequestedLifetimeCount());
            encoder.writeUInt32("RequestedMaxKeepAliveCount", value.getRequestedMaxKeepAliveCount());
            encoder.writeUInt32("MaxNotificationsPerPublish", value.getMaxNotificationsPerPublish());
            encoder.writeBoolean("PublishingEnabled", value.getPublishingEnabled());
            encoder.writeByte("Priority", value.getPriority());
        }
    }
}
