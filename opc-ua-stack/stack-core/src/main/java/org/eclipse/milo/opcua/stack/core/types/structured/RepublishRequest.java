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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class RepublishRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RepublishRequest;
    public static final NodeId BinaryEncodingId = Identifiers.RepublishRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RepublishRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger subscriptionId;
    protected final UInteger retransmitSequenceNumber;

    public RepublishRequest() {
        this.requestHeader = null;
        this.subscriptionId = null;
        this.retransmitSequenceNumber = null;
    }

    public RepublishRequest(RequestHeader requestHeader, UInteger subscriptionId, UInteger retransmitSequenceNumber) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.retransmitSequenceNumber = retransmitSequenceNumber;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public UInteger getSubscriptionId() { return subscriptionId; }

    public UInteger getRetransmitSequenceNumber() { return retransmitSequenceNumber; }

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
            .add("RetransmitSequenceNumber", retransmitSequenceNumber)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RepublishRequest> {

        @Override
        public Class<RepublishRequest> getType() {
            return RepublishRequest.class;
        }

        @Override
        public RepublishRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger retransmitSequenceNumber = decoder.readUInt32("RetransmitSequenceNumber");

            return new RepublishRequest(requestHeader, subscriptionId, retransmitSequenceNumber);
        }

        @Override
        public void encode(RepublishRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeUInt32("RetransmitSequenceNumber", value.retransmitSequenceNumber);
        }
    }

}
