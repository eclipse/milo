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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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
public class SubscriptionAcknowledgement extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=821");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=823");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=822");

    private final UInteger subscriptionId;

    private final UInteger sequenceNumber;

    public SubscriptionAcknowledgement(UInteger subscriptionId, UInteger sequenceNumber) {
        this.subscriptionId = subscriptionId;
        this.sequenceNumber = sequenceNumber;
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

    public UInteger getSubscriptionId() {
        return subscriptionId;
    }

    public UInteger getSequenceNumber() {
        return sequenceNumber;
    }

    public static final class Codec extends GenericDataTypeCodec<SubscriptionAcknowledgement> {
        @Override
        public Class<SubscriptionAcknowledgement> getType() {
            return SubscriptionAcknowledgement.class;
        }

        @Override
        public SubscriptionAcknowledgement decode(SerializationContext context, UaDecoder decoder) {
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger sequenceNumber = decoder.readUInt32("SequenceNumber");
            return new SubscriptionAcknowledgement(subscriptionId, sequenceNumber);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SubscriptionAcknowledgement value) {
            encoder.writeUInt32("SubscriptionId", value.getSubscriptionId());
            encoder.writeUInt32("SequenceNumber", value.getSequenceNumber());
        }
    }
}
