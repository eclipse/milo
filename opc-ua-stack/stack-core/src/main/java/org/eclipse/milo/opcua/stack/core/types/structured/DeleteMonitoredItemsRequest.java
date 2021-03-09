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

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class DeleteMonitoredItemsRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=779");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=781");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=780");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final UInteger[] monitoredItemIds;

    public DeleteMonitoredItemsRequest(RequestHeader requestHeader, UInteger subscriptionId,
                                       UInteger[] monitoredItemIds) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.monitoredItemIds = monitoredItemIds;
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

    public UInteger[] getMonitoredItemIds() {
        return monitoredItemIds;
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteMonitoredItemsRequest> {
        @Override
        public Class<DeleteMonitoredItemsRequest> getType() {
            return DeleteMonitoredItemsRequest.class;
        }

        @Override
        public DeleteMonitoredItemsRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger[] monitoredItemIds = decoder.readUInt32Array("MonitoredItemIds");
            return new DeleteMonitoredItemsRequest(requestHeader, subscriptionId, monitoredItemIds);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DeleteMonitoredItemsRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeUInt32Array("MonitoredItemIds", value.getMonitoredItemIds());
        }
    }
}
