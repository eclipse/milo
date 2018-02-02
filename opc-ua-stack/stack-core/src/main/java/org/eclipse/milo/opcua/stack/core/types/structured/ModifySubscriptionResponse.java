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

public class ModifySubscriptionResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.ModifySubscriptionResponse;
    public static final NodeId BinaryEncodingId = Identifiers.ModifySubscriptionResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModifySubscriptionResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final Double revisedPublishingInterval;
    protected final UInteger revisedLifetimeCount;
    protected final UInteger revisedMaxKeepAliveCount;

    public ModifySubscriptionResponse() {
        this.responseHeader = null;
        this.revisedPublishingInterval = null;
        this.revisedLifetimeCount = null;
        this.revisedMaxKeepAliveCount = null;
    }

    public ModifySubscriptionResponse(ResponseHeader responseHeader, Double revisedPublishingInterval, UInteger revisedLifetimeCount, UInteger revisedMaxKeepAliveCount) {
        this.responseHeader = responseHeader;
        this.revisedPublishingInterval = revisedPublishingInterval;
        this.revisedLifetimeCount = revisedLifetimeCount;
        this.revisedMaxKeepAliveCount = revisedMaxKeepAliveCount;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

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
            .add("RevisedPublishingInterval", revisedPublishingInterval)
            .add("RevisedLifetimeCount", revisedLifetimeCount)
            .add("RevisedMaxKeepAliveCount", revisedMaxKeepAliveCount)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ModifySubscriptionResponse> {

        @Override
        public Class<ModifySubscriptionResponse> getType() {
            return ModifySubscriptionResponse.class;
        }

        @Override
        public ModifySubscriptionResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            Double revisedPublishingInterval = decoder.readDouble("RevisedPublishingInterval");
            UInteger revisedLifetimeCount = decoder.readUInt32("RevisedLifetimeCount");
            UInteger revisedMaxKeepAliveCount = decoder.readUInt32("RevisedMaxKeepAliveCount");

            return new ModifySubscriptionResponse(responseHeader, revisedPublishingInterval, revisedLifetimeCount, revisedMaxKeepAliveCount);
        }

        @Override
        public void encode(ModifySubscriptionResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeDouble("RevisedPublishingInterval", value.revisedPublishingInterval);
            encoder.writeUInt32("RevisedLifetimeCount", value.revisedLifetimeCount);
            encoder.writeUInt32("RevisedMaxKeepAliveCount", value.revisedMaxKeepAliveCount);
        }
    }

}
