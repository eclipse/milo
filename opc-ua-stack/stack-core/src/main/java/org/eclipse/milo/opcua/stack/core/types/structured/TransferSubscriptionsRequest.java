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

@UaDataType("TransferSubscriptionsRequest")
public class TransferSubscriptionsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.TransferSubscriptionsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.TransferSubscriptionsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TransferSubscriptionsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger[] _subscriptionIds;
    protected final Boolean _sendInitialValues;

    public TransferSubscriptionsRequest() {
        this._requestHeader = null;
        this._subscriptionIds = null;
        this._sendInitialValues = null;
    }

    public TransferSubscriptionsRequest(RequestHeader _requestHeader, UInteger[] _subscriptionIds, Boolean _sendInitialValues) {
        this._requestHeader = _requestHeader;
        this._subscriptionIds = _subscriptionIds;
        this._sendInitialValues = _sendInitialValues;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger[] getSubscriptionIds() { return _subscriptionIds; }

    public Boolean getSendInitialValues() { return _sendInitialValues; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(TransferSubscriptionsRequest transferSubscriptionsRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", transferSubscriptionsRequest._requestHeader != null ? transferSubscriptionsRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("SubscriptionIds", transferSubscriptionsRequest._subscriptionIds, encoder::encodeUInt32);
        encoder.encodeBoolean("SendInitialValues", transferSubscriptionsRequest._sendInitialValues);
    }

    public static TransferSubscriptionsRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger[] _subscriptionIds = decoder.decodeArray("SubscriptionIds", decoder::decodeUInt32, UInteger.class);
        Boolean _sendInitialValues = decoder.decodeBoolean("SendInitialValues");

        return new TransferSubscriptionsRequest(_requestHeader, _subscriptionIds, _sendInitialValues);
    }

    static {
        DelegateRegistry.registerEncoder(TransferSubscriptionsRequest::encode, TransferSubscriptionsRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(TransferSubscriptionsRequest::decode, TransferSubscriptionsRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
