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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("RepublishRequest")
public class RepublishRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RepublishRequest;
    public static final NodeId BinaryEncodingId = Identifiers.RepublishRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RepublishRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final UInteger _retransmitSequenceNumber;

    public RepublishRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._retransmitSequenceNumber = null;
    }

    public RepublishRequest(RequestHeader _requestHeader, UInteger _subscriptionId, UInteger _retransmitSequenceNumber) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._retransmitSequenceNumber = _retransmitSequenceNumber;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public UInteger getRetransmitSequenceNumber() { return _retransmitSequenceNumber; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RepublishRequest republishRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", republishRequest._requestHeader != null ? republishRequest._requestHeader : new RequestHeader());
        encoder.encodeUInt32("SubscriptionId", republishRequest._subscriptionId);
        encoder.encodeUInt32("RetransmitSequenceNumber", republishRequest._retransmitSequenceNumber);
    }

    public static RepublishRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger _subscriptionId = decoder.decodeUInt32("SubscriptionId");
        UInteger _retransmitSequenceNumber = decoder.decodeUInt32("RetransmitSequenceNumber");

        return new RepublishRequest(_requestHeader, _subscriptionId, _retransmitSequenceNumber);
    }

    static {
        DelegateRegistry.registerEncoder(RepublishRequest::encode, RepublishRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RepublishRequest::decode, RepublishRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
