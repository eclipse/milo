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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("SubscriptionAcknowledgement")
public class SubscriptionAcknowledgement implements UaStructure {

    public static final NodeId TypeId = Identifiers.SubscriptionAcknowledgement;
    public static final NodeId BinaryEncodingId = Identifiers.SubscriptionAcknowledgement_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SubscriptionAcknowledgement_Encoding_DefaultXml;

    protected final UInteger _subscriptionId;
    protected final UInteger _sequenceNumber;

    public SubscriptionAcknowledgement() {
        this._subscriptionId = null;
        this._sequenceNumber = null;
    }

    public SubscriptionAcknowledgement(UInteger _subscriptionId, UInteger _sequenceNumber) {
        this._subscriptionId = _subscriptionId;
        this._sequenceNumber = _sequenceNumber;
    }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public UInteger getSequenceNumber() { return _sequenceNumber; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SubscriptionId", _subscriptionId)
            .add("SequenceNumber", _sequenceNumber)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SubscriptionAcknowledgement> {
        @Override
        public SubscriptionAcknowledgement decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _subscriptionId = reader.readUInt32();
            UInteger _sequenceNumber = reader.readUInt32();

            return new SubscriptionAcknowledgement(_subscriptionId, _sequenceNumber);
        }

        @Override
        public void encode(SerializationContext context, SubscriptionAcknowledgement value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(value._subscriptionId);
            writer.writeUInt32(value._sequenceNumber);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SubscriptionAcknowledgement> {
        @Override
        public SubscriptionAcknowledgement decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _subscriptionId = reader.readUInt32("SubscriptionId");
            UInteger _sequenceNumber = reader.readUInt32("SequenceNumber");

            return new SubscriptionAcknowledgement(_subscriptionId, _sequenceNumber);
        }

        @Override
        public void encode(SerializationContext context, SubscriptionAcknowledgement encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("SubscriptionId", encodable._subscriptionId);
            writer.writeUInt32("SequenceNumber", encodable._sequenceNumber);
        }
    }

}
