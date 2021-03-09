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
public class ModifyMonitoredItemsRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=761");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=763");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=762");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final TimestampsToReturn timestampsToReturn;

    private final MonitoredItemModifyRequest[] itemsToModify;

    public ModifyMonitoredItemsRequest(RequestHeader requestHeader, UInteger subscriptionId,
                                       TimestampsToReturn timestampsToReturn, MonitoredItemModifyRequest[] itemsToModify) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.timestampsToReturn = timestampsToReturn;
        this.itemsToModify = itemsToModify;
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

    public MonitoredItemModifyRequest[] getItemsToModify() {
        return itemsToModify;
    }

    public static final class Codec extends GenericDataTypeCodec<ModifyMonitoredItemsRequest> {
        @Override
        public Class<ModifyMonitoredItemsRequest> getType() {
            return ModifyMonitoredItemsRequest.class;
        }

        @Override
        public ModifyMonitoredItemsRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            TimestampsToReturn timestampsToReturn = decoder.readEnum("TimestampsToReturn", TimestampsToReturn.class);
            MonitoredItemModifyRequest[] itemsToModify = (MonitoredItemModifyRequest[]) decoder.readStructArray("ItemsToModify", MonitoredItemModifyRequest.TYPE_ID);
            return new ModifyMonitoredItemsRequest(requestHeader, subscriptionId, timestampsToReturn, itemsToModify);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ModifyMonitoredItemsRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeEnum("TimestampsToReturn", value.getTimestampsToReturn());
            encoder.writeStructArray("ItemsToModify", value.getItemsToModify(), MonitoredItemModifyRequest.TYPE_ID);
        }
    }
}
