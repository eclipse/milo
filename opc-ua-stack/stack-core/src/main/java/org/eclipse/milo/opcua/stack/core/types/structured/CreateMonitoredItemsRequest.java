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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

@UaDataType("CreateMonitoredItemsRequest")
public class CreateMonitoredItemsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CreateMonitoredItemsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CreateMonitoredItemsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CreateMonitoredItemsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final TimestampsToReturn _timestampsToReturn;
    protected final MonitoredItemCreateRequest[] _itemsToCreate;

    public CreateMonitoredItemsRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._timestampsToReturn = null;
        this._itemsToCreate = null;
    }

    public CreateMonitoredItemsRequest(RequestHeader _requestHeader, UInteger _subscriptionId, TimestampsToReturn _timestampsToReturn, MonitoredItemCreateRequest[] _itemsToCreate) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._timestampsToReturn = _timestampsToReturn;
        this._itemsToCreate = _itemsToCreate;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public TimestampsToReturn getTimestampsToReturn() { return _timestampsToReturn; }

    public MonitoredItemCreateRequest[] getItemsToCreate() { return _itemsToCreate; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(CreateMonitoredItemsRequest createMonitoredItemsRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", createMonitoredItemsRequest._requestHeader != null ? createMonitoredItemsRequest._requestHeader : new RequestHeader());
        encoder.encodeUInt32("SubscriptionId", createMonitoredItemsRequest._subscriptionId);
        encoder.encodeEnumeration("TimestampsToReturn", createMonitoredItemsRequest._timestampsToReturn);
        encoder.encodeArray("ItemsToCreate", createMonitoredItemsRequest._itemsToCreate, encoder::encodeSerializable);
    }

    public static CreateMonitoredItemsRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger _subscriptionId = decoder.decodeUInt32("SubscriptionId");
        TimestampsToReturn _timestampsToReturn = decoder.decodeEnumeration("TimestampsToReturn", TimestampsToReturn.class);
        MonitoredItemCreateRequest[] _itemsToCreate = decoder.decodeArray("ItemsToCreate", decoder::decodeSerializable, MonitoredItemCreateRequest.class);

        return new CreateMonitoredItemsRequest(_requestHeader, _subscriptionId, _timestampsToReturn, _itemsToCreate);
    }

    static {
        DelegateRegistry.registerEncoder(CreateMonitoredItemsRequest::encode, CreateMonitoredItemsRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CreateMonitoredItemsRequest::decode, CreateMonitoredItemsRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
