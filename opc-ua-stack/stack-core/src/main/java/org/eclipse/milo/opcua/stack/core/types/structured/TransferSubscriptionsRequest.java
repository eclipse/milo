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
public class TransferSubscriptionsRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=839");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=841");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=840");

    private final RequestHeader requestHeader;

    private final UInteger[] subscriptionIds;

    private final Boolean sendInitialValues;

    public TransferSubscriptionsRequest(RequestHeader requestHeader, UInteger[] subscriptionIds,
                                        Boolean sendInitialValues) {
        this.requestHeader = requestHeader;
        this.subscriptionIds = subscriptionIds;
        this.sendInitialValues = sendInitialValues;
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

    public UInteger[] getSubscriptionIds() {
        return subscriptionIds;
    }

    public Boolean getSendInitialValues() {
        return sendInitialValues;
    }

    public static final class Codec extends GenericDataTypeCodec<TransferSubscriptionsRequest> {
        @Override
        public Class<TransferSubscriptionsRequest> getType() {
            return TransferSubscriptionsRequest.class;
        }

        @Override
        public TransferSubscriptionsRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            UInteger[] subscriptionIds = decoder.readUInt32Array("SubscriptionIds");
            Boolean sendInitialValues = decoder.readBoolean("SendInitialValues");
            return new TransferSubscriptionsRequest(requestHeader, subscriptionIds, sendInitialValues);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           TransferSubscriptionsRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeUInt32Array("SubscriptionIds", value.getSubscriptionIds());
            encoder.writeBoolean("SendInitialValues", value.getSendInitialValues());
        }
    }
}
