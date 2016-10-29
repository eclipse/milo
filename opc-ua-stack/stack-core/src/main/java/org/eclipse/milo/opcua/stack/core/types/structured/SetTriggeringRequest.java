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

@UaDataType("SetTriggeringRequest")
public class SetTriggeringRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.SetTriggeringRequest;
    public static final NodeId BinaryEncodingId = Identifiers.SetTriggeringRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SetTriggeringRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final UInteger _triggeringItemId;
    protected final UInteger[] _linksToAdd;
    protected final UInteger[] _linksToRemove;

    public SetTriggeringRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._triggeringItemId = null;
        this._linksToAdd = null;
        this._linksToRemove = null;
    }

    public SetTriggeringRequest(RequestHeader _requestHeader, UInteger _subscriptionId, UInteger _triggeringItemId, UInteger[] _linksToAdd, UInteger[] _linksToRemove) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._triggeringItemId = _triggeringItemId;
        this._linksToAdd = _linksToAdd;
        this._linksToRemove = _linksToRemove;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public UInteger getTriggeringItemId() { return _triggeringItemId; }

    public UInteger[] getLinksToAdd() { return _linksToAdd; }

    public UInteger[] getLinksToRemove() { return _linksToRemove; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(SetTriggeringRequest setTriggeringRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", setTriggeringRequest._requestHeader != null ? setTriggeringRequest._requestHeader : new RequestHeader());
        encoder.encodeUInt32("SubscriptionId", setTriggeringRequest._subscriptionId);
        encoder.encodeUInt32("TriggeringItemId", setTriggeringRequest._triggeringItemId);
        encoder.encodeArray("LinksToAdd", setTriggeringRequest._linksToAdd, encoder::encodeUInt32);
        encoder.encodeArray("LinksToRemove", setTriggeringRequest._linksToRemove, encoder::encodeUInt32);
    }

    public static SetTriggeringRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger _subscriptionId = decoder.decodeUInt32("SubscriptionId");
        UInteger _triggeringItemId = decoder.decodeUInt32("TriggeringItemId");
        UInteger[] _linksToAdd = decoder.decodeArray("LinksToAdd", decoder::decodeUInt32, UInteger.class);
        UInteger[] _linksToRemove = decoder.decodeArray("LinksToRemove", decoder::decodeUInt32, UInteger.class);

        return new SetTriggeringRequest(_requestHeader, _subscriptionId, _triggeringItemId, _linksToAdd, _linksToRemove);
    }

    static {
        DelegateRegistry.registerEncoder(SetTriggeringRequest::encode, SetTriggeringRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SetTriggeringRequest::decode, SetTriggeringRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
