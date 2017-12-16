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

public class CreateMonitoredItemsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CreateMonitoredItemsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CreateMonitoredItemsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateMonitoredItemsRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger subscriptionId;
    protected final TimestampsToReturn timestampsToReturn;
    protected final MonitoredItemCreateRequest[] itemsToCreate;

    public CreateMonitoredItemsRequest() {
        this.requestHeader = null;
        this.subscriptionId = null;
        this.timestampsToReturn = null;
        this.itemsToCreate = null;
    }

    public CreateMonitoredItemsRequest(RequestHeader requestHeader, UInteger subscriptionId, TimestampsToReturn timestampsToReturn, MonitoredItemCreateRequest[] itemsToCreate) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.timestampsToReturn = timestampsToReturn;
        this.itemsToCreate = itemsToCreate;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public UInteger getSubscriptionId() { return subscriptionId; }

    public TimestampsToReturn getTimestampsToReturn() { return timestampsToReturn; }

    @Nullable
    public MonitoredItemCreateRequest[] getItemsToCreate() { return itemsToCreate; }

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
            .add("ItemsToCreate", itemsToCreate)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CreateMonitoredItemsRequest> {

        @Override
        public Class<CreateMonitoredItemsRequest> getType() {
            return CreateMonitoredItemsRequest.class;
        }

        @Override
        public CreateMonitoredItemsRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            TimestampsToReturn timestampsToReturn = TimestampsToReturn.from(decoder.readInt32("TimestampsToReturn"));
            MonitoredItemCreateRequest[] itemsToCreate =
                decoder.readBuiltinStructArray(
                    "ItemsToCreate",
                    MonitoredItemCreateRequest.class
                );

            return new CreateMonitoredItemsRequest(requestHeader, subscriptionId, timestampsToReturn, itemsToCreate);
        }

        @Override
        public void encode(CreateMonitoredItemsRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeInt32("TimestampsToReturn", value.timestampsToReturn != null ? value.timestampsToReturn.getValue() : 0);
            encoder.writeBuiltinStructArray(
                "ItemsToCreate",
                value.itemsToCreate,
                MonitoredItemCreateRequest.class
            );
        }
    }

}
