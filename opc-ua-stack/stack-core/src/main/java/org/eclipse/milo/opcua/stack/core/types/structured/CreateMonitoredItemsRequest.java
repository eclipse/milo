/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class CreateMonitoredItemsRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=749");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=751");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=750");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final TimestampsToReturn timestampsToReturn;

    private final MonitoredItemCreateRequest[] itemsToCreate;

    public CreateMonitoredItemsRequest(RequestHeader requestHeader, UInteger subscriptionId,
                                       TimestampsToReturn timestampsToReturn, MonitoredItemCreateRequest[] itemsToCreate) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.timestampsToReturn = timestampsToReturn;
        this.itemsToCreate = itemsToCreate;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public UInteger getSubscriptionId() {
        return subscriptionId;
    }

    public TimestampsToReturn getTimestampsToReturn() {
        return timestampsToReturn;
    }

    public MonitoredItemCreateRequest[] getItemsToCreate() {
        return itemsToCreate;
    }

    public static final class Codec extends GenericDataTypeCodec<CreateMonitoredItemsRequest> {
        @Override
        public Class<CreateMonitoredItemsRequest> getType() {
            return CreateMonitoredItemsRequest.class;
        }

        @Override
        public CreateMonitoredItemsRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            TimestampsToReturn timestampsToReturn = decoder.readEnum("TimestampsToReturn", TimestampsToReturn.class);
            MonitoredItemCreateRequest[] itemsToCreate = (MonitoredItemCreateRequest[]) decoder.readStructArray("ItemsToCreate", MonitoredItemCreateRequest.TYPE_ID);
            return new CreateMonitoredItemsRequest(requestHeader, subscriptionId, timestampsToReturn, itemsToCreate);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           CreateMonitoredItemsRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeEnum("TimestampsToReturn", value.getTimestampsToReturn());
            encoder.writeStructArray("ItemsToCreate", value.getItemsToCreate(), MonitoredItemCreateRequest.TYPE_ID);
        }
    }
}
