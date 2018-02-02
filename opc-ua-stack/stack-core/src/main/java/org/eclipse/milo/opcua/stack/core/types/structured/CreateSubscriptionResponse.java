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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class CreateSubscriptionResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.CreateSubscriptionResponse;
    public static final NodeId BinaryEncodingId = Identifiers.CreateSubscriptionResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateSubscriptionResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final UInteger subscriptionId;
    protected final Double revisedPublishingInterval;
    protected final UInteger revisedLifetimeCount;
    protected final UInteger revisedMaxKeepAliveCount;

    public CreateSubscriptionResponse() {
        this.responseHeader = null;
        this.subscriptionId = null;
        this.revisedPublishingInterval = null;
        this.revisedLifetimeCount = null;
        this.revisedMaxKeepAliveCount = null;
    }

    public CreateSubscriptionResponse(ResponseHeader responseHeader, UInteger subscriptionId, Double revisedPublishingInterval, UInteger revisedLifetimeCount, UInteger revisedMaxKeepAliveCount) {
        this.responseHeader = responseHeader;
        this.subscriptionId = subscriptionId;
        this.revisedPublishingInterval = revisedPublishingInterval;
        this.revisedLifetimeCount = revisedLifetimeCount;
        this.revisedMaxKeepAliveCount = revisedMaxKeepAliveCount;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    public UInteger getSubscriptionId() { return subscriptionId; }

    public Double getRevisedPublishingInterval() { return revisedPublishingInterval; }

    public UInteger getRevisedLifetimeCount() { return revisedLifetimeCount; }

    public UInteger getRevisedMaxKeepAliveCount() { return revisedMaxKeepAliveCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", responseHeader)
            .add("SubscriptionId", subscriptionId)
            .add("RevisedPublishingInterval", revisedPublishingInterval)
            .add("RevisedLifetimeCount", revisedLifetimeCount)
            .add("RevisedMaxKeepAliveCount", revisedMaxKeepAliveCount)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CreateSubscriptionResponse> {

        @Override
        public Class<CreateSubscriptionResponse> getType() {
            return CreateSubscriptionResponse.class;
        }

        @Override
        public CreateSubscriptionResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            Double revisedPublishingInterval = decoder.readDouble("RevisedPublishingInterval");
            UInteger revisedLifetimeCount = decoder.readUInt32("RevisedLifetimeCount");
            UInteger revisedMaxKeepAliveCount = decoder.readUInt32("RevisedMaxKeepAliveCount");

            return new CreateSubscriptionResponse(responseHeader, subscriptionId, revisedPublishingInterval, revisedLifetimeCount, revisedMaxKeepAliveCount);
        }

        @Override
        public void encode(CreateSubscriptionResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeDouble("RevisedPublishingInterval", value.revisedPublishingInterval);
            encoder.writeUInt32("RevisedLifetimeCount", value.revisedLifetimeCount);
            encoder.writeUInt32("RevisedMaxKeepAliveCount", value.revisedMaxKeepAliveCount);
        }
    }

}
