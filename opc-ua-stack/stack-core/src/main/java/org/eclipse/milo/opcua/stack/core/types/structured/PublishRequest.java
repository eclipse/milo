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

public class PublishRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.PublishRequest;
    public static final NodeId BinaryEncodingId = Identifiers.PublishRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.PublishRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final SubscriptionAcknowledgement[] subscriptionAcknowledgements;

    public PublishRequest() {
        this.requestHeader = null;
        this.subscriptionAcknowledgements = null;
    }

    public PublishRequest(RequestHeader requestHeader, SubscriptionAcknowledgement[] subscriptionAcknowledgements) {
        this.requestHeader = requestHeader;
        this.subscriptionAcknowledgements = subscriptionAcknowledgements;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public SubscriptionAcknowledgement[] getSubscriptionAcknowledgements() { return subscriptionAcknowledgements; }

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
            .add("SubscriptionAcknowledgements", subscriptionAcknowledgements)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<PublishRequest> {

        @Override
        public Class<PublishRequest> getType() {
            return PublishRequest.class;
        }

        @Override
        public PublishRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            SubscriptionAcknowledgement[] subscriptionAcknowledgements =
                decoder.readBuiltinStructArray(
                    "SubscriptionAcknowledgements",
                    SubscriptionAcknowledgement.class
                );

            return new PublishRequest(requestHeader, subscriptionAcknowledgements);
        }

        @Override
        public void encode(PublishRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStructArray(
                "SubscriptionAcknowledgements",
                value.subscriptionAcknowledgements,
                SubscriptionAcknowledgement.class
            );
        }
    }

}
