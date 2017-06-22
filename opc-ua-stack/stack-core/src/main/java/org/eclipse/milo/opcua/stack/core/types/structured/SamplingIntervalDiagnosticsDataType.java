/*
 * Copyright (c) 2017 Kevin Herron
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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SamplingInterval", _samplingInterval)
            .add("MonitoredItemCount", _monitoredItemCount)
            .add("MaxMonitoredItemCount", _maxMonitoredItemCount)
            .add("DisabledMonitoredItemCount", _disabledMonitoredItemCount)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<SamplingIntervalDiagnosticsDataType> {
        @Override
        public SamplingIntervalDiagnosticsDataType decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Double _samplingInterval = reader.readDouble();
            UInteger _monitoredItemCount = reader.readUInt32();
            UInteger _maxMonitoredItemCount = reader.readUInt32();
            UInteger _disabledMonitoredItemCount = reader.readUInt32();

            return new SamplingIntervalDiagnosticsDataType(_samplingInterval, _monitoredItemCount, _maxMonitoredItemCount, _disabledMonitoredItemCount);
        }

        @Override
        public void encode(SerializationContext context, SamplingIntervalDiagnosticsDataType value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeDouble(value._samplingInterval);
            writer.writeUInt32(value._monitoredItemCount);
            writer.writeUInt32(value._maxMonitoredItemCount);
            writer.writeUInt32(value._disabledMonitoredItemCount);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<SamplingIntervalDiagnosticsDataType> {
        @Override
        public SamplingIntervalDiagnosticsDataType decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Double _samplingInterval = reader.readDouble("SamplingInterval");
            UInteger _monitoredItemCount = reader.readUInt32("MonitoredItemCount");
            UInteger _maxMonitoredItemCount = reader.readUInt32("MaxMonitoredItemCount");
            UInteger _disabledMonitoredItemCount = reader.readUInt32("DisabledMonitoredItemCount");

            return new SamplingIntervalDiagnosticsDataType(_samplingInterval, _monitoredItemCount, _maxMonitoredItemCount, _disabledMonitoredItemCount);
        }

        @Override
        public void encode(SerializationContext context, SamplingIntervalDiagnosticsDataType encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeDouble("SamplingInterval", encodable._samplingInterval);
            writer.writeUInt32("MonitoredItemCount", encodable._monitoredItemCount);
            writer.writeUInt32("MaxMonitoredItemCount", encodable._maxMonitoredItemCount);
            writer.writeUInt32("DisabledMonitoredItemCount", encodable._disabledMonitoredItemCount);
        }
    }

}
