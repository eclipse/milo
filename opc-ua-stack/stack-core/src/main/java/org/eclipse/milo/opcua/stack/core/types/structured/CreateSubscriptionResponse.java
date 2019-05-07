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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class CreateSubscriptionResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=788");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=790");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=789");

    private final ResponseHeader responseHeader;

    private final UInteger subscriptionId;

    private final Double revisedPublishingInterval;

    private final UInteger revisedLifetimeCount;

    private final UInteger revisedMaxKeepAliveCount;

    public CreateSubscriptionResponse(ResponseHeader responseHeader, UInteger subscriptionId,
                                      Double revisedPublishingInterval, UInteger revisedLifetimeCount,
                                      UInteger revisedMaxKeepAliveCount) {
        this.responseHeader = responseHeader;
        this.subscriptionId = subscriptionId;
        this.revisedPublishingInterval = revisedPublishingInterval;
        this.revisedLifetimeCount = revisedLifetimeCount;
        this.revisedMaxKeepAliveCount = revisedMaxKeepAliveCount;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public UInteger getSubscriptionId() {
        return subscriptionId;
    }

    public Double getRevisedPublishingInterval() {
        return revisedPublishingInterval;
    }

    public UInteger getRevisedLifetimeCount() {
        return revisedLifetimeCount;
    }

    public UInteger getRevisedMaxKeepAliveCount() {
        return revisedMaxKeepAliveCount;
    }

    public static final class Codec extends GenericDataTypeCodec<CreateSubscriptionResponse> {
        @Override
        public Class<CreateSubscriptionResponse> getType() {
            return CreateSubscriptionResponse.class;
        }

        @Override
        public CreateSubscriptionResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            Double revisedPublishingInterval = decoder.readDouble("RevisedPublishingInterval");
            UInteger revisedLifetimeCount = decoder.readUInt32("RevisedLifetimeCount");
            UInteger revisedMaxKeepAliveCount = decoder.readUInt32("RevisedMaxKeepAliveCount");
            return new CreateSubscriptionResponse(responseHeader, subscriptionId, revisedPublishingInterval, revisedLifetimeCount, revisedMaxKeepAliveCount);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           CreateSubscriptionResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeDouble("RevisedPublishingInterval", value.getRevisedPublishingInterval());
            encoder.writeUInt32("RevisedLifetimeCount", value.getRevisedLifetimeCount());
            encoder.writeUInt32("RevisedMaxKeepAliveCount", value.getRevisedMaxKeepAliveCount());
        }
    }
}
