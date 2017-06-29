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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class TransferSubscriptionsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.TransferSubscriptionsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.TransferSubscriptionsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TransferSubscriptionsRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger[] subscriptionIds;
    protected final Boolean sendInitialValues;

    public TransferSubscriptionsRequest() {
        this.requestHeader = null;
        this.subscriptionIds = null;
        this.sendInitialValues = null;
    }

    public TransferSubscriptionsRequest(RequestHeader requestHeader, UInteger[] subscriptionIds, Boolean sendInitialValues) {
        this.requestHeader = requestHeader;
        this.subscriptionIds = subscriptionIds;
        this.sendInitialValues = sendInitialValues;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public UInteger[] getSubscriptionIds() { return subscriptionIds; }

    public Boolean getSendInitialValues() { return sendInitialValues; }

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
            .add("SubscriptionIds", subscriptionIds)
            .add("SendInitialValues", sendInitialValues)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<TransferSubscriptionsRequest> {

        @Override
        public Class<TransferSubscriptionsRequest> getType() {
            return TransferSubscriptionsRequest.class;
        }

        @Override
        public TransferSubscriptionsRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger[] subscriptionIds = decoder.readArray("SubscriptionIds", decoder::readUInt32, UInteger.class);
            Boolean sendInitialValues = decoder.readBoolean("SendInitialValues");

            return new TransferSubscriptionsRequest(requestHeader, subscriptionIds, sendInitialValues);
        }

        @Override
        public void encode(TransferSubscriptionsRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeArray("SubscriptionIds", value.subscriptionIds, encoder::writeUInt32);
            encoder.writeBoolean("SendInitialValues", value.sendInitialValues);
        }
    }

}
