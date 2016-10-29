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

@UaDataType("CreateSubscriptionRequest")
public class CreateSubscriptionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CreateSubscriptionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CreateSubscriptionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateSubscriptionRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final Double _requestedPublishingInterval;
    protected final UInteger _requestedLifetimeCount;
    protected final UInteger _requestedMaxKeepAliveCount;
    protected final UInteger _maxNotificationsPerPublish;
    protected final Boolean _publishingEnabled;
    protected final UByte _priority;

    public CreateSubscriptionRequest() {
        this._requestHeader = null;
        this._requestedPublishingInterval = null;
        this._requestedLifetimeCount = null;
        this._requestedMaxKeepAliveCount = null;
        this._maxNotificationsPerPublish = null;
        this._publishingEnabled = null;
        this._priority = null;
    }

    public CreateSubscriptionRequest(RequestHeader _requestHeader, Double _requestedPublishingInterval, UInteger _requestedLifetimeCount, UInteger _requestedMaxKeepAliveCount, UInteger _maxNotificationsPerPublish, Boolean _publishingEnabled, UByte _priority) {
        this._requestHeader = _requestHeader;
        this._requestedPublishingInterval = _requestedPublishingInterval;
        this._requestedLifetimeCount = _requestedLifetimeCount;
        this._requestedMaxKeepAliveCount = _requestedMaxKeepAliveCount;
        this._maxNotificationsPerPublish = _maxNotificationsPerPublish;
        this._publishingEnabled = _publishingEnabled;
        this._priority = _priority;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public Double getRequestedPublishingInterval() { return _requestedPublishingInterval; }

    public UInteger getRequestedLifetimeCount() { return _requestedLifetimeCount; }

    public UInteger getRequestedMaxKeepAliveCount() { return _requestedMaxKeepAliveCount; }

    public UInteger getMaxNotificationsPerPublish() { return _maxNotificationsPerPublish; }

    public Boolean getPublishingEnabled() { return _publishingEnabled; }

    public UByte getPriority() { return _priority; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(CreateSubscriptionRequest createSubscriptionRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", createSubscriptionRequest._requestHeader != null ? createSubscriptionRequest._requestHeader : new RequestHeader());
        encoder.encodeDouble("RequestedPublishingInterval", createSubscriptionRequest._requestedPublishingInterval);
        encoder.encodeUInt32("RequestedLifetimeCount", createSubscriptionRequest._requestedLifetimeCount);
        encoder.encodeUInt32("RequestedMaxKeepAliveCount", createSubscriptionRequest._requestedMaxKeepAliveCount);
        encoder.encodeUInt32("MaxNotificationsPerPublish", createSubscriptionRequest._maxNotificationsPerPublish);
        encoder.encodeBoolean("PublishingEnabled", createSubscriptionRequest._publishingEnabled);
        encoder.encodeByte("Priority", createSubscriptionRequest._priority);
    }

    public static CreateSubscriptionRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        Double _requestedPublishingInterval = decoder.decodeDouble("RequestedPublishingInterval");
        UInteger _requestedLifetimeCount = decoder.decodeUInt32("RequestedLifetimeCount");
        UInteger _requestedMaxKeepAliveCount = decoder.decodeUInt32("RequestedMaxKeepAliveCount");
        UInteger _maxNotificationsPerPublish = decoder.decodeUInt32("MaxNotificationsPerPublish");
        Boolean _publishingEnabled = decoder.decodeBoolean("PublishingEnabled");
        UByte _priority = decoder.decodeByte("Priority");

        return new CreateSubscriptionRequest(_requestHeader, _requestedPublishingInterval, _requestedLifetimeCount, _requestedMaxKeepAliveCount, _maxNotificationsPerPublish, _publishingEnabled, _priority);
    }

    static {
        DelegateRegistry.registerEncoder(CreateSubscriptionRequest::encode, CreateSubscriptionRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CreateSubscriptionRequest::decode, CreateSubscriptionRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
