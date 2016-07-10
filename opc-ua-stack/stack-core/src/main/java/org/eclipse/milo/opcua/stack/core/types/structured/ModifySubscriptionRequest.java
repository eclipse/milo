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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ModifySubscriptionRequest")
public class ModifySubscriptionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ModifySubscriptionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ModifySubscriptionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModifySubscriptionRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final Double _requestedPublishingInterval;
    protected final UInteger _requestedLifetimeCount;
    protected final UInteger _requestedMaxKeepAliveCount;
    protected final UInteger _maxNotificationsPerPublish;
    protected final UByte _priority;

    public ModifySubscriptionRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._requestedPublishingInterval = null;
        this._requestedLifetimeCount = null;
        this._requestedMaxKeepAliveCount = null;
        this._maxNotificationsPerPublish = null;
        this._priority = null;
    }

    public ModifySubscriptionRequest(RequestHeader _requestHeader, UInteger _subscriptionId, Double _requestedPublishingInterval, UInteger _requestedLifetimeCount, UInteger _requestedMaxKeepAliveCount, UInteger _maxNotificationsPerPublish, UByte _priority) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._requestedPublishingInterval = _requestedPublishingInterval;
        this._requestedLifetimeCount = _requestedLifetimeCount;
        this._requestedMaxKeepAliveCount = _requestedMaxKeepAliveCount;
        this._maxNotificationsPerPublish = _maxNotificationsPerPublish;
        this._priority = _priority;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public Double getRequestedPublishingInterval() { return _requestedPublishingInterval; }

    public UInteger getRequestedLifetimeCount() { return _requestedLifetimeCount; }

    public UInteger getRequestedMaxKeepAliveCount() { return _requestedMaxKeepAliveCount; }

    public UInteger getMaxNotificationsPerPublish() { return _maxNotificationsPerPublish; }

    public UByte getPriority() { return _priority; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ModifySubscriptionRequest modifySubscriptionRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", modifySubscriptionRequest._requestHeader != null ? modifySubscriptionRequest._requestHeader : new RequestHeader());
        encoder.encodeUInt32("SubscriptionId", modifySubscriptionRequest._subscriptionId);
        encoder.encodeDouble("RequestedPublishingInterval", modifySubscriptionRequest._requestedPublishingInterval);
        encoder.encodeUInt32("RequestedLifetimeCount", modifySubscriptionRequest._requestedLifetimeCount);
        encoder.encodeUInt32("RequestedMaxKeepAliveCount", modifySubscriptionRequest._requestedMaxKeepAliveCount);
        encoder.encodeUInt32("MaxNotificationsPerPublish", modifySubscriptionRequest._maxNotificationsPerPublish);
        encoder.encodeByte("Priority", modifySubscriptionRequest._priority);
    }

    public static ModifySubscriptionRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger _subscriptionId = decoder.decodeUInt32("SubscriptionId");
        Double _requestedPublishingInterval = decoder.decodeDouble("RequestedPublishingInterval");
        UInteger _requestedLifetimeCount = decoder.decodeUInt32("RequestedLifetimeCount");
        UInteger _requestedMaxKeepAliveCount = decoder.decodeUInt32("RequestedMaxKeepAliveCount");
        UInteger _maxNotificationsPerPublish = decoder.decodeUInt32("MaxNotificationsPerPublish");
        UByte _priority = decoder.decodeByte("Priority");

        return new ModifySubscriptionRequest(_requestHeader, _subscriptionId, _requestedPublishingInterval, _requestedLifetimeCount, _requestedMaxKeepAliveCount, _maxNotificationsPerPublish, _priority);
    }

    static {
        DelegateRegistry.registerEncoder(ModifySubscriptionRequest::encode, ModifySubscriptionRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ModifySubscriptionRequest::decode, ModifySubscriptionRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
