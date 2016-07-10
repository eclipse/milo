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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("MonitoredItemModifyRequest")
public class MonitoredItemModifyRequest implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoredItemModifyRequest;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemModifyRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoredItemModifyRequest_Encoding_DefaultXml;

    protected final UInteger _monitoredItemId;
    protected final MonitoringParameters _requestedParameters;

    public MonitoredItemModifyRequest() {
        this._monitoredItemId = null;
        this._requestedParameters = null;
    }

    public MonitoredItemModifyRequest(UInteger _monitoredItemId, MonitoringParameters _requestedParameters) {
        this._monitoredItemId = _monitoredItemId;
        this._requestedParameters = _requestedParameters;
    }

    public UInteger getMonitoredItemId() { return _monitoredItemId; }

    public MonitoringParameters getRequestedParameters() { return _requestedParameters; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(MonitoredItemModifyRequest monitoredItemModifyRequest, UaEncoder encoder) {
        encoder.encodeUInt32("MonitoredItemId", monitoredItemModifyRequest._monitoredItemId);
        encoder.encodeSerializable("RequestedParameters", monitoredItemModifyRequest._requestedParameters != null ? monitoredItemModifyRequest._requestedParameters : new MonitoringParameters());
    }

    public static MonitoredItemModifyRequest decode(UaDecoder decoder) {
        UInteger _monitoredItemId = decoder.decodeUInt32("MonitoredItemId");
        MonitoringParameters _requestedParameters = decoder.decodeSerializable("RequestedParameters", MonitoringParameters.class);

        return new MonitoredItemModifyRequest(_monitoredItemId, _requestedParameters);
    }

    static {
        DelegateRegistry.registerEncoder(MonitoredItemModifyRequest::encode, MonitoredItemModifyRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(MonitoredItemModifyRequest::decode, MonitoredItemModifyRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
