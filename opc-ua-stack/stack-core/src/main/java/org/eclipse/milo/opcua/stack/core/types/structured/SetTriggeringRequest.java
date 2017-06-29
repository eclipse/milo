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

public class SetTriggeringRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.SetTriggeringRequest;
    public static final NodeId BinaryEncodingId = Identifiers.SetTriggeringRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SetTriggeringRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger subscriptionId;
    protected final UInteger triggeringItemId;
    protected final UInteger[] linksToAdd;
    protected final UInteger[] linksToRemove;

    public SetTriggeringRequest() {
        this.requestHeader = null;
        this.subscriptionId = null;
        this.triggeringItemId = null;
        this.linksToAdd = null;
        this.linksToRemove = null;
    }

    public SetTriggeringRequest(RequestHeader requestHeader, UInteger subscriptionId, UInteger triggeringItemId, UInteger[] linksToAdd, UInteger[] linksToRemove) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.triggeringItemId = triggeringItemId;
        this.linksToAdd = linksToAdd;
        this.linksToRemove = linksToRemove;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public UInteger getSubscriptionId() { return subscriptionId; }

    public UInteger getTriggeringItemId() { return triggeringItemId; }

    @Nullable
    public UInteger[] getLinksToAdd() { return linksToAdd; }

    @Nullable
    public UInteger[] getLinksToRemove() { return linksToRemove; }

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
            .add("TriggeringItemId", triggeringItemId)
            .add("LinksToAdd", linksToAdd)
            .add("LinksToRemove", linksToRemove)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SetTriggeringRequest> {

        @Override
        public Class<SetTriggeringRequest> getType() {
            return SetTriggeringRequest.class;
        }

        @Override
        public SetTriggeringRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger triggeringItemId = decoder.readUInt32("TriggeringItemId");
            UInteger[] linksToAdd = decoder.readArray("LinksToAdd", decoder::readUInt32, UInteger.class);
            UInteger[] linksToRemove = decoder.readArray("LinksToRemove", decoder::readUInt32, UInteger.class);

            return new SetTriggeringRequest(requestHeader, subscriptionId, triggeringItemId, linksToAdd, linksToRemove);
        }

        @Override
        public void encode(SetTriggeringRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeUInt32("TriggeringItemId", value.triggeringItemId);
            encoder.writeArray("LinksToAdd", value.linksToAdd, encoder::writeUInt32);
            encoder.writeArray("LinksToRemove", value.linksToRemove, encoder::writeUInt32);
        }
    }

}
