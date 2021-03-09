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

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class PublishRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=824");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=826");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=825");

    private final RequestHeader requestHeader;

    private final SubscriptionAcknowledgement[] subscriptionAcknowledgements;

    public PublishRequest(RequestHeader requestHeader,
                          SubscriptionAcknowledgement[] subscriptionAcknowledgements) {
        this.requestHeader = requestHeader;
        this.subscriptionAcknowledgements = subscriptionAcknowledgements;
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

    public SubscriptionAcknowledgement[] getSubscriptionAcknowledgements() {
        return subscriptionAcknowledgements;
    }

    public static final class Codec extends GenericDataTypeCodec<PublishRequest> {
        @Override
        public Class<PublishRequest> getType() {
            return PublishRequest.class;
        }

        @Override
        public PublishRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            SubscriptionAcknowledgement[] subscriptionAcknowledgements = (SubscriptionAcknowledgement[]) decoder.readStructArray("SubscriptionAcknowledgements", SubscriptionAcknowledgement.TYPE_ID);
            return new PublishRequest(requestHeader, subscriptionAcknowledgements);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, PublishRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("SubscriptionAcknowledgements", value.getSubscriptionAcknowledgements(), SubscriptionAcknowledgement.TYPE_ID);
        }
    }
}
