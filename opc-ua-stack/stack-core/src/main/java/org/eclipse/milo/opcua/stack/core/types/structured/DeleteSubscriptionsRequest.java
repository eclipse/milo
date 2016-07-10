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

@UaDataType("DeleteSubscriptionsRequest")
public class DeleteSubscriptionsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteSubscriptionsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteSubscriptionsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteSubscriptionsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger[] _subscriptionIds;

    public DeleteSubscriptionsRequest() {
        this._requestHeader = null;
        this._subscriptionIds = null;
    }

    public DeleteSubscriptionsRequest(RequestHeader _requestHeader, UInteger[] _subscriptionIds) {
        this._requestHeader = _requestHeader;
        this._subscriptionIds = _subscriptionIds;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger[] getSubscriptionIds() { return _subscriptionIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(DeleteSubscriptionsRequest deleteSubscriptionsRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", deleteSubscriptionsRequest._requestHeader != null ? deleteSubscriptionsRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("SubscriptionIds", deleteSubscriptionsRequest._subscriptionIds, encoder::encodeUInt32);
    }

    public static DeleteSubscriptionsRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger[] _subscriptionIds = decoder.decodeArray("SubscriptionIds", decoder::decodeUInt32, UInteger.class);

        return new DeleteSubscriptionsRequest(_requestHeader, _subscriptionIds);
    }

    static {
        DelegateRegistry.registerEncoder(DeleteSubscriptionsRequest::encode, DeleteSubscriptionsRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(DeleteSubscriptionsRequest::decode, DeleteSubscriptionsRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
