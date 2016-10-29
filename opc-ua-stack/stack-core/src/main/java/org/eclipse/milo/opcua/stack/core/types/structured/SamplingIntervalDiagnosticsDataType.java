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

@UaDataType("SamplingIntervalDiagnosticsDataType")
public class SamplingIntervalDiagnosticsDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.SamplingIntervalDiagnosticsDataType;
    public static final NodeId BinaryEncodingId = Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultXml;

    protected final Double _samplingInterval;
    protected final UInteger _monitoredItemCount;
    protected final UInteger _maxMonitoredItemCount;
    protected final UInteger _disabledMonitoredItemCount;

    public SamplingIntervalDiagnosticsDataType() {
        this._samplingInterval = null;
        this._monitoredItemCount = null;
        this._maxMonitoredItemCount = null;
        this._disabledMonitoredItemCount = null;
    }

    public SamplingIntervalDiagnosticsDataType(Double _samplingInterval, UInteger _monitoredItemCount, UInteger _maxMonitoredItemCount, UInteger _disabledMonitoredItemCount) {
        this._samplingInterval = _samplingInterval;
        this._monitoredItemCount = _monitoredItemCount;
        this._maxMonitoredItemCount = _maxMonitoredItemCount;
        this._disabledMonitoredItemCount = _disabledMonitoredItemCount;
    }

    public Double getSamplingInterval() { return _samplingInterval; }

    public UInteger getMonitoredItemCount() { return _monitoredItemCount; }

    public UInteger getMaxMonitoredItemCount() { return _maxMonitoredItemCount; }

    public UInteger getDisabledMonitoredItemCount() { return _disabledMonitoredItemCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(SamplingIntervalDiagnosticsDataType samplingIntervalDiagnosticsDataType, UaEncoder encoder) {
        encoder.encodeDouble("SamplingInterval", samplingIntervalDiagnosticsDataType._samplingInterval);
        encoder.encodeUInt32("MonitoredItemCount", samplingIntervalDiagnosticsDataType._monitoredItemCount);
        encoder.encodeUInt32("MaxMonitoredItemCount", samplingIntervalDiagnosticsDataType._maxMonitoredItemCount);
        encoder.encodeUInt32("DisabledMonitoredItemCount", samplingIntervalDiagnosticsDataType._disabledMonitoredItemCount);
    }

    public static SamplingIntervalDiagnosticsDataType decode(UaDecoder decoder) {
        Double _samplingInterval = decoder.decodeDouble("SamplingInterval");
        UInteger _monitoredItemCount = decoder.decodeUInt32("MonitoredItemCount");
        UInteger _maxMonitoredItemCount = decoder.decodeUInt32("MaxMonitoredItemCount");
        UInteger _disabledMonitoredItemCount = decoder.decodeUInt32("DisabledMonitoredItemCount");

        return new SamplingIntervalDiagnosticsDataType(_samplingInterval, _monitoredItemCount, _maxMonitoredItemCount, _disabledMonitoredItemCount);
    }

    static {
        DelegateRegistry.registerEncoder(SamplingIntervalDiagnosticsDataType::encode, SamplingIntervalDiagnosticsDataType.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SamplingIntervalDiagnosticsDataType::decode, SamplingIntervalDiagnosticsDataType.class, BinaryEncodingId, XmlEncodingId);
    }

}
