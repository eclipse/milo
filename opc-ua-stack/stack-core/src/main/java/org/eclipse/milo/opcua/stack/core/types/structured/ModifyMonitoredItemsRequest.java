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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

public class ModifyMonitoredItemsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ModifyMonitoredItemsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ModifyMonitoredItemsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModifyMonitoredItemsRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger subscriptionId;
    protected final TimestampsToReturn timestampsToReturn;
    protected final MonitoredItemModifyRequest[] itemsToModify;

    public ModifyMonitoredItemsRequest() {
        this.requestHeader = null;
        this.subscriptionId = null;
        this.timestampsToReturn = null;
        this.itemsToModify = null;
    }

    public ModifyMonitoredItemsRequest(RequestHeader requestHeader, UInteger subscriptionId, TimestampsToReturn timestampsToReturn, MonitoredItemModifyRequest[] itemsToModify) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.timestampsToReturn = timestampsToReturn;
        this.itemsToModify = itemsToModify;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public UInteger getSubscriptionId() { return subscriptionId; }

    public TimestampsToReturn getTimestampsToReturn() { return timestampsToReturn; }

    @Nullable
    public MonitoredItemModifyRequest[] getItemsToModify() { return itemsToModify; }

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
            .add("TimestampsToReturn", timestampsToReturn)
            .add("ItemsToModify", itemsToModify)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ModifyMonitoredItemsRequest> {

        @Override
        public Class<ModifyMonitoredItemsRequest> getType() {
            return ModifyMonitoredItemsRequest.class;
        }

        @Override
        public ModifyMonitoredItemsRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            TimestampsToReturn timestampsToReturn = TimestampsToReturn.from(decoder.readInt32("TimestampsToReturn"));
            MonitoredItemModifyRequest[] itemsToModify =
                decoder.readBuiltinStructArray(
                    "ItemsToModify",
                    MonitoredItemModifyRequest.class
                );

            return new ModifyMonitoredItemsRequest(requestHeader, subscriptionId, timestampsToReturn, itemsToModify);
        }

        @Override
        public void encode(ModifyMonitoredItemsRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeInt32("TimestampsToReturn", value.timestampsToReturn != null ? value.timestampsToReturn.getValue() : 0);
            encoder.writeBuiltinStructArray(
                "ItemsToModify",
                value.itemsToModify,
                MonitoredItemModifyRequest.class
            );
        }
    }

}
