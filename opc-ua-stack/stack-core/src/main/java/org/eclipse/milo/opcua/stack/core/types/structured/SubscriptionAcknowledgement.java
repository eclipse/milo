/*
 * Copyright (c) 2016 Kevin Herron
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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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


    public static void encode(SubscriptionAcknowledgement subscriptionAcknowledgement, UaEncoder encoder) {
        encoder.encodeUInt32("SubscriptionId", subscriptionAcknowledgement._subscriptionId);
        encoder.encodeUInt32("SequenceNumber", subscriptionAcknowledgement._sequenceNumber);
    }

    public static SubscriptionAcknowledgement decode(UaDecoder decoder) {
        UInteger _subscriptionId = decoder.decodeUInt32("SubscriptionId");
        UInteger _sequenceNumber = decoder.decodeUInt32("SequenceNumber");

        return new SubscriptionAcknowledgement(_subscriptionId, _sequenceNumber);
    }

    static {
        DelegateRegistry.registerEncoder(SubscriptionAcknowledgement::encode, SubscriptionAcknowledgement.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SubscriptionAcknowledgement::decode, SubscriptionAcknowledgement.class, BinaryEncodingId, XmlEncodingId);
    }

}
