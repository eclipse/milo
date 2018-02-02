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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class SamplingIntervalDiagnosticsDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.SamplingIntervalDiagnosticsDataType;
    public static final NodeId BinaryEncodingId = Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SamplingIntervalDiagnosticsDataType_Encoding_DefaultXml;

    protected final Double samplingInterval;
    protected final UInteger monitoredItemCount;
    protected final UInteger maxMonitoredItemCount;
    protected final UInteger disabledMonitoredItemCount;

    public SamplingIntervalDiagnosticsDataType() {
        this.samplingInterval = null;
        this.monitoredItemCount = null;
        this.maxMonitoredItemCount = null;
        this.disabledMonitoredItemCount = null;
    }

    public SamplingIntervalDiagnosticsDataType(Double samplingInterval, UInteger monitoredItemCount, UInteger maxMonitoredItemCount, UInteger disabledMonitoredItemCount) {
        this.samplingInterval = samplingInterval;
        this.monitoredItemCount = monitoredItemCount;
        this.maxMonitoredItemCount = maxMonitoredItemCount;
        this.disabledMonitoredItemCount = disabledMonitoredItemCount;
    }

    public Double getSamplingInterval() { return samplingInterval; }

    public UInteger getMonitoredItemCount() { return monitoredItemCount; }

    public UInteger getMaxMonitoredItemCount() { return maxMonitoredItemCount; }

    public UInteger getDisabledMonitoredItemCount() { return disabledMonitoredItemCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SamplingInterval", samplingInterval)
            .add("MonitoredItemCount", monitoredItemCount)
            .add("MaxMonitoredItemCount", maxMonitoredItemCount)
            .add("DisabledMonitoredItemCount", disabledMonitoredItemCount)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<SamplingIntervalDiagnosticsDataType> {

        @Override
        public Class<SamplingIntervalDiagnosticsDataType> getType() {
            return SamplingIntervalDiagnosticsDataType.class;
        }

        @Override
        public SamplingIntervalDiagnosticsDataType decode(UaDecoder decoder) throws UaSerializationException {
            Double samplingInterval = decoder.readDouble("SamplingInterval");
            UInteger monitoredItemCount = decoder.readUInt32("MonitoredItemCount");
            UInteger maxMonitoredItemCount = decoder.readUInt32("MaxMonitoredItemCount");
            UInteger disabledMonitoredItemCount = decoder.readUInt32("DisabledMonitoredItemCount");

            return new SamplingIntervalDiagnosticsDataType(samplingInterval, monitoredItemCount, maxMonitoredItemCount, disabledMonitoredItemCount);
        }

        @Override
        public void encode(SamplingIntervalDiagnosticsDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeDouble("SamplingInterval", value.samplingInterval);
            encoder.writeUInt32("MonitoredItemCount", value.monitoredItemCount);
            encoder.writeUInt32("MaxMonitoredItemCount", value.maxMonitoredItemCount);
            encoder.writeUInt32("DisabledMonitoredItemCount", value.disabledMonitoredItemCount);
        }
    }

}
