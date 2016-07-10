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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;

@UaDataType("MonitoredItemCreateRequest")
public class MonitoredItemCreateRequest implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoredItemCreateRequest;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemCreateRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoredItemCreateRequest_Encoding_DefaultXml;

    protected final ReadValueId _itemToMonitor;
    protected final MonitoringMode _monitoringMode;
    protected final MonitoringParameters _requestedParameters;

    public MonitoredItemCreateRequest() {
        this._itemToMonitor = null;
        this._monitoringMode = null;
        this._requestedParameters = null;
    }

    public MonitoredItemCreateRequest(ReadValueId _itemToMonitor, MonitoringMode _monitoringMode, MonitoringParameters _requestedParameters) {
        this._itemToMonitor = _itemToMonitor;
        this._monitoringMode = _monitoringMode;
        this._requestedParameters = _requestedParameters;
    }

    public ReadValueId getItemToMonitor() { return _itemToMonitor; }

    public MonitoringMode getMonitoringMode() { return _monitoringMode; }

    public MonitoringParameters getRequestedParameters() { return _requestedParameters; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(MonitoredItemCreateRequest monitoredItemCreateRequest, UaEncoder encoder) {
        encoder.encodeSerializable("ItemToMonitor", monitoredItemCreateRequest._itemToMonitor != null ? monitoredItemCreateRequest._itemToMonitor : new ReadValueId());
        encoder.encodeEnumeration("MonitoringMode", monitoredItemCreateRequest._monitoringMode);
        encoder.encodeSerializable("RequestedParameters", monitoredItemCreateRequest._requestedParameters != null ? monitoredItemCreateRequest._requestedParameters : new MonitoringParameters());
    }

    public static MonitoredItemCreateRequest decode(UaDecoder decoder) {
        ReadValueId _itemToMonitor = decoder.decodeSerializable("ItemToMonitor", ReadValueId.class);
        MonitoringMode _monitoringMode = decoder.decodeEnumeration("MonitoringMode", MonitoringMode.class);
        MonitoringParameters _requestedParameters = decoder.decodeSerializable("RequestedParameters", MonitoringParameters.class);

        return new MonitoredItemCreateRequest(_itemToMonitor, _monitoringMode, _requestedParameters);
    }

    static {
        DelegateRegistry.registerEncoder(MonitoredItemCreateRequest::encode, MonitoredItemCreateRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(MonitoredItemCreateRequest::decode, MonitoredItemCreateRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
