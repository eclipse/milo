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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class SubscriptionAcknowledgement implements UaStructure {

    public static final NodeId TypeId = Identifiers.SubscriptionAcknowledgement;
    public static final NodeId BinaryEncodingId = Identifiers.SubscriptionAcknowledgement_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SubscriptionAcknowledgement_Encoding_DefaultXml;

    protected final UInteger subscriptionId;
    protected final UInteger sequenceNumber;

    public SubscriptionAcknowledgement() {
        this.subscriptionId = null;
        this.sequenceNumber = null;
    }

    public SubscriptionAcknowledgement(UInteger subscriptionId, UInteger sequenceNumber) {
        this.subscriptionId = subscriptionId;
        this.sequenceNumber = sequenceNumber;
    }

    public UInteger getSubscriptionId() { return subscriptionId; }

    public UInteger getSequenceNumber() { return sequenceNumber; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SubscriptionId", subscriptionId)
            .add("SequenceNumber", sequenceNumber)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SubscriptionAcknowledgement> {

        @Override
        public Class<SubscriptionAcknowledgement> getType() {
            return SubscriptionAcknowledgement.class;
        }

        @Override
        public SubscriptionAcknowledgement decode(UaDecoder decoder) throws UaSerializationException {
            UInteger subscriptionId = decoder.readUInt32("SubscriptionId");
            UInteger sequenceNumber = decoder.readUInt32("SequenceNumber");

            return new SubscriptionAcknowledgement(subscriptionId, sequenceNumber);
        }

        @Override
        public void encode(SubscriptionAcknowledgement value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("SubscriptionId", value.subscriptionId);
            encoder.writeUInt32("SequenceNumber", value.sequenceNumber);
        }
    }

}
