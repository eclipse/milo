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
public class SetPublishingModeRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=797");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=799");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=798");

    private final RequestHeader requestHeader;

    private final Boolean publishingEnabled;

    private final UInteger[] subscriptionIds;

    public SetPublishingModeRequest(RequestHeader requestHeader, Boolean publishingEnabled,
                                    UInteger[] subscriptionIds) {
        this.requestHeader = requestHeader;
        this.publishingEnabled = publishingEnabled;
        this.subscriptionIds = subscriptionIds;
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

    public Boolean getPublishingEnabled() {
        return publishingEnabled;
    }

    public UInteger[] getSubscriptionIds() {
        return subscriptionIds;
    }

    public static final class Codec extends GenericDataTypeCodec<SetPublishingModeRequest> {
        @Override
        public Class<SetPublishingModeRequest> getType() {
            return SetPublishingModeRequest.class;
        }

        @Override
        public SetPublishingModeRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            Boolean publishingEnabled = decoder.readBoolean("PublishingEnabled");
            UInteger[] subscriptionIds = decoder.readUInt32Array("SubscriptionIds");
            return new SetPublishingModeRequest(requestHeader, publishingEnabled, subscriptionIds);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SetPublishingModeRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeBoolean("PublishingEnabled", value.getPublishingEnabled());
            encoder.writeUInt32Array("SubscriptionIds", value.getSubscriptionIds());
        }
    }
}
