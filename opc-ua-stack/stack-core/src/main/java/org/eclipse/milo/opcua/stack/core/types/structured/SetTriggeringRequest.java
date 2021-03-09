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
public class SetTriggeringRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=773");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=775");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=774");

    private final RequestHeader requestHeader;

    private final UInteger subscriptionId;

    private final UInteger triggeringItemId;

    private final UInteger[] linksToAdd;

    private final UInteger[] linksToRemove;

    public SetTriggeringRequest(RequestHeader requestHeader, UInteger subscriptionId,
                                UInteger triggeringItemId, UInteger[] linksToAdd, UInteger[] linksToRemove) {
        this.requestHeader = requestHeader;
        this.subscriptionId = subscriptionId;
        this.triggeringItemId = triggeringItemId;
        this.linksToAdd = linksToAdd;
        this.linksToRemove = linksToRemove;
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

    public UInteger getTriggeringItemId() {
        return triggeringItemId;
    }

    public UInteger[] getLinksToAdd() {
        return linksToAdd;
    }

    public UInteger[] getLinksToRemove() {
        return linksToRemove;
    }

    public static final class Codec extends GenericDataTypeCodec<SetTriggeringRequest> {
        @Override
        public Class<SetTriggeringRequest> getType() {
            return SetTriggeringRequest.class;
        }

        @Override
        public SetTriggeringRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger triggeringItemId = decoder.readUInt32("TriggeringItemId");
            UInteger[] linksToAdd = decoder.readUInt32Array("LinksToAdd");
            UInteger[] linksToRemove = decoder.readUInt32Array("LinksToRemove");
            return new SetTriggeringRequest(requestHeader, subscriptionId, triggeringItemId, linksToAdd, linksToRemove);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SetTriggeringRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeUInt32("TriggeringItemId", value.getTriggeringItemId());
            encoder.writeUInt32Array("LinksToAdd", value.getLinksToAdd());
            encoder.writeUInt32Array("LinksToRemove", value.getLinksToRemove());
        }
    }
}
