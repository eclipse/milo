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

@UaDataType("SetPublishingModeRequest")
public class SetPublishingModeRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.SetPublishingModeRequest;
    public static final NodeId BinaryEncodingId = Identifiers.SetPublishingModeRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SetPublishingModeRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Boolean _publishingEnabled;
    protected final UInteger[] _subscriptionIds;

    public SetPublishingModeRequest() {
        this._requestHeader = null;
        this._publishingEnabled = null;
        this._subscriptionIds = null;
    }

    public SetPublishingModeRequest(RequestHeader _requestHeader, Boolean _publishingEnabled, UInteger[] _subscriptionIds) {
        this._requestHeader = _requestHeader;
        this._publishingEnabled = _publishingEnabled;
        this._subscriptionIds = _subscriptionIds;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Boolean getPublishingEnabled() { return _publishingEnabled; }

    public UInteger[] getSubscriptionIds() { return _subscriptionIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(SetPublishingModeRequest setPublishingModeRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", setPublishingModeRequest._requestHeader != null ? setPublishingModeRequest._requestHeader : new RequestHeader());
        encoder.encodeBoolean("PublishingEnabled", setPublishingModeRequest._publishingEnabled);
        encoder.encodeArray("SubscriptionIds", setPublishingModeRequest._subscriptionIds, encoder::encodeUInt32);
    }

    public static SetPublishingModeRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        Boolean _publishingEnabled = decoder.decodeBoolean("PublishingEnabled");
        UInteger[] _subscriptionIds = decoder.decodeArray("SubscriptionIds", decoder::decodeUInt32, UInteger.class);

        return new SetPublishingModeRequest(_requestHeader, _publishingEnabled, _subscriptionIds);
    }

    static {
        DelegateRegistry.registerEncoder(SetPublishingModeRequest::encode, SetPublishingModeRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SetPublishingModeRequest::decode, SetPublishingModeRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
