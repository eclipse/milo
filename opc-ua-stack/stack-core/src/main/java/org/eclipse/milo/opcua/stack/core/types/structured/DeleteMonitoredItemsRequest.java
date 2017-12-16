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

public class DeleteMonitoredItemsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteMonitoredItemsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteMonitoredItemsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteMonitoredItemsRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger subscriptionId;
    protected final UInteger[] monitoredItemIds;

    public DeleteMonitoredItemsRequest() {
        this.requestHeader = null;
        this.subscriptionId = null;
        this.monitoredItemIds = null;
    }

    public DeleteMonitoredItemsRequest(RequestHeader requestHeader, UInteger subscriptionId, UInteger[] monitoredItemIds) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.monitoredItemIds = monitoredItemIds;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public UInteger getSubscriptionId() { return subscriptionId; }

    @Nullable
    public UInteger[] getMonitoredItemIds() { return monitoredItemIds; }

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
            .add("MonitoredItemIds", monitoredItemIds)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DeleteMonitoredItemsRequest> {

        @Override
        public Class<DeleteMonitoredItemsRequest> getType() {
            return DeleteMonitoredItemsRequest.class;
        }

        @Override
        public DeleteMonitoredItemsRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger[] monitoredItemIds = decoder.readArray("MonitoredItemIds", decoder::readUInt32, UInteger.class);

            return new DeleteMonitoredItemsRequest(requestHeader, subscriptionId, monitoredItemIds);
        }

        @Override
        public void encode(DeleteMonitoredItemsRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeArray("MonitoredItemIds", value.monitoredItemIds, encoder::writeUInt32);
        }
    }

}
