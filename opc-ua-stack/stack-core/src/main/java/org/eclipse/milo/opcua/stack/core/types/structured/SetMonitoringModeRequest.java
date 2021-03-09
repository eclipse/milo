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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SetMonitoringModeRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=767");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=769");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=768");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final MonitoringMode monitoringMode;

    private final UInteger[] monitoredItemIds;

    public SetMonitoringModeRequest(RequestHeader requestHeader, UInteger subscriptionId,
                                    MonitoringMode monitoringMode, UInteger[] monitoredItemIds) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.monitoringMode = monitoringMode;
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

    public MonitoringMode getMonitoringMode() {
        return monitoringMode;
    }

    public UInteger[] getMonitoredItemIds() {
        return monitoredItemIds;
    }

    public static final class Codec extends GenericDataTypeCodec<SetMonitoringModeRequest> {
        @Override
        public Class<SetMonitoringModeRequest> getType() {
            return SetMonitoringModeRequest.class;
        }

        @Override
        public SetMonitoringModeRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            MonitoringMode monitoringMode = decoder.readEnum("MonitoringMode", MonitoringMode.class);
            UInteger[] monitoredItemIds = decoder.readUInt32Array("MonitoredItemIds");
            return new SetMonitoringModeRequest(requestHeader, subscriptionId, monitoringMode, monitoredItemIds);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SetMonitoringModeRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeEnum("MonitoringMode", value.getMonitoringMode());
            encoder.writeUInt32Array("MonitoredItemIds", value.getMonitoredItemIds());
        }
    }
}
