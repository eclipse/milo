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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("MonitoredItemModifyResult")
public class MonitoredItemModifyResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoredItemModifyResult;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemModifyResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoredItemModifyResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final Double _revisedSamplingInterval;
    protected final UInteger _revisedQueueSize;
    protected final ExtensionObject _filterResult;

    public MonitoredItemModifyResult() {
        this._statusCode = null;
        this._revisedSamplingInterval = null;
        this._revisedQueueSize = null;
        this._filterResult = null;
    }

    public MonitoredItemModifyResult(StatusCode _statusCode, Double _revisedSamplingInterval, UInteger _revisedQueueSize, ExtensionObject _filterResult) {
        this._statusCode = _statusCode;
        this._revisedSamplingInterval = _revisedSamplingInterval;
        this._revisedQueueSize = _revisedQueueSize;
        this._filterResult = _filterResult;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public Double getRevisedSamplingInterval() { return _revisedSamplingInterval; }

    public UInteger getRevisedQueueSize() { return _revisedQueueSize; }

    public ExtensionObject getFilterResult() { return _filterResult; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(MonitoredItemModifyResult monitoredItemModifyResult, UaEncoder encoder) {
        encoder.encodeStatusCode("StatusCode", monitoredItemModifyResult._statusCode);
        encoder.encodeDouble("RevisedSamplingInterval", monitoredItemModifyResult._revisedSamplingInterval);
        encoder.encodeUInt32("RevisedQueueSize", monitoredItemModifyResult._revisedQueueSize);
        encoder.encodeExtensionObject("FilterResult", monitoredItemModifyResult._filterResult);
    }

    public static MonitoredItemModifyResult decode(UaDecoder decoder) {
        StatusCode _statusCode = decoder.decodeStatusCode("StatusCode");
        Double _revisedSamplingInterval = decoder.decodeDouble("RevisedSamplingInterval");
        UInteger _revisedQueueSize = decoder.decodeUInt32("RevisedQueueSize");
        ExtensionObject _filterResult = decoder.decodeExtensionObject("FilterResult");

        return new MonitoredItemModifyResult(_statusCode, _revisedSamplingInterval, _revisedQueueSize, _filterResult);
    }

    static {
        DelegateRegistry.registerEncoder(MonitoredItemModifyResult::encode, MonitoredItemModifyResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(MonitoredItemModifyResult::decode, MonitoredItemModifyResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
