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

@UaDataType("DeleteMonitoredItemsRequest")
public class DeleteMonitoredItemsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.DeleteMonitoredItemsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteMonitoredItemsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteMonitoredItemsRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _subscriptionId;
    protected final UInteger[] _monitoredItemIds;

    public DeleteMonitoredItemsRequest() {
        this._requestHeader = null;
        this._subscriptionId = null;
        this._monitoredItemIds = null;
    }

    public DeleteMonitoredItemsRequest(RequestHeader _requestHeader, UInteger _subscriptionId, UInteger[] _monitoredItemIds) {
        this._requestHeader = _requestHeader;
        this._subscriptionId = _subscriptionId;
        this._monitoredItemIds = _monitoredItemIds;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getSubscriptionId() { return _subscriptionId; }

    public UInteger[] getMonitoredItemIds() { return _monitoredItemIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(DeleteMonitoredItemsRequest deleteMonitoredItemsRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", deleteMonitoredItemsRequest._requestHeader != null ? deleteMonitoredItemsRequest._requestHeader : new RequestHeader());
        encoder.encodeUInt32("SubscriptionId", deleteMonitoredItemsRequest._subscriptionId);
        encoder.encodeArray("MonitoredItemIds", deleteMonitoredItemsRequest._monitoredItemIds, encoder::encodeUInt32);
    }

    public static DeleteMonitoredItemsRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger _subscriptionId = decoder.decodeUInt32("SubscriptionId");
        UInteger[] _monitoredItemIds = decoder.decodeArray("MonitoredItemIds", decoder::decodeUInt32, UInteger.class);

        return new DeleteMonitoredItemsRequest(_requestHeader, _subscriptionId, _monitoredItemIds);
    }

    static {
        DelegateRegistry.registerEncoder(DeleteMonitoredItemsRequest::encode, DeleteMonitoredItemsRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(DeleteMonitoredItemsRequest::decode, DeleteMonitoredItemsRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
