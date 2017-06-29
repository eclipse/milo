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

public class DeleteSubscriptionsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteSubscriptionsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteSubscriptionsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteSubscriptionsRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger[] subscriptionIds;

    public DeleteSubscriptionsRequest() {
        this.requestHeader = null;
        this.subscriptionIds = null;
    }

    public DeleteSubscriptionsRequest(RequestHeader requestHeader, UInteger[] subscriptionIds) {
        this.requestHeader = requestHeader;
        this.subscriptionIds = subscriptionIds;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public UInteger[] getSubscriptionIds() { return subscriptionIds; }

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
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DeleteSubscriptionsRequest> {

        @Override
        public Class<DeleteSubscriptionsRequest> getType() {
            return DeleteSubscriptionsRequest.class;
        }

        @Override
        public DeleteSubscriptionsRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger[] subscriptionIds = decoder.readArray("SubscriptionIds", decoder::readUInt32, UInteger.class);

            return new DeleteSubscriptionsRequest(requestHeader, subscriptionIds);
        }

        @Override
        public void encode(DeleteSubscriptionsRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeArray("SubscriptionIds", value.subscriptionIds, encoder::writeUInt32);
        }
    }

}
