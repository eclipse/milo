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

@UaDataType("ModifyMonitoredItemsRequest")
public class ModifyMonitoredItemsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ModifyMonitoredItemsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ModifyMonitoredItemsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModifyMonitoredItemsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final TimestampsToReturn _timestampsToReturn;
    protected final MonitoredItemModifyRequest[] _itemsToModify;

    public ModifyMonitoredItemsRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._timestampsToReturn = null;
        this._itemsToModify = null;
    }

    public ModifyMonitoredItemsRequest(RequestHeader _requestHeader, UInteger _subscriptionId, TimestampsToReturn _timestampsToReturn, MonitoredItemModifyRequest[] _itemsToModify) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._timestampsToReturn = _timestampsToReturn;
        this._itemsToModify = _itemsToModify;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public TimestampsToReturn getTimestampsToReturn() { return _timestampsToReturn; }

    public MonitoredItemModifyRequest[] getItemsToModify() { return _itemsToModify; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ModifyMonitoredItemsRequest modifyMonitoredItemsRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", modifyMonitoredItemsRequest._requestHeader != null ? modifyMonitoredItemsRequest._requestHeader : new RequestHeader());
        encoder.encodeUInt32("SubscriptionId", modifyMonitoredItemsRequest._subscriptionId);
        encoder.encodeEnumeration("TimestampsToReturn", modifyMonitoredItemsRequest._timestampsToReturn);
        encoder.encodeArray("ItemsToModify", modifyMonitoredItemsRequest._itemsToModify, encoder::encodeSerializable);
    }

    public static ModifyMonitoredItemsRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger _subscriptionId = decoder.decodeUInt32("SubscriptionId");
        TimestampsToReturn _timestampsToReturn = decoder.decodeEnumeration("TimestampsToReturn", TimestampsToReturn.class);
        MonitoredItemModifyRequest[] _itemsToModify = decoder.decodeArray("ItemsToModify", decoder::decodeSerializable, MonitoredItemModifyRequest.class);

        return new ModifyMonitoredItemsRequest(_requestHeader, _subscriptionId, _timestampsToReturn, _itemsToModify);
    }

    static {
        DelegateRegistry.registerEncoder(ModifyMonitoredItemsRequest::encode, ModifyMonitoredItemsRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ModifyMonitoredItemsRequest::decode, ModifyMonitoredItemsRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
