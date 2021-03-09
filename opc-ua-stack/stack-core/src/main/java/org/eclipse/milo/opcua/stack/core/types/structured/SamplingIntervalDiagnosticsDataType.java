/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class SamplingIntervalDiagnosticsDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=856");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=857");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=858");

    private final Double samplingInterval;

    private final UInteger monitoredItemCount;

    private final UInteger maxMonitoredItemCount;

    private final UInteger disabledMonitoredItemCount;

    public SamplingIntervalDiagnosticsDataType(Double samplingInterval, UInteger monitoredItemCount,
                                               UInteger maxMonitoredItemCount, UInteger disabledMonitoredItemCount) {
        this.samplingInterval = samplingInterval;
        this.monitoredItemCount = monitoredItemCount;
        this.maxMonitoredItemCount = maxMonitoredItemCount;
        this.disabledMonitoredItemCount = disabledMonitoredItemCount;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public Double getSamplingInterval() {
        return samplingInterval;
    }

    public UInteger getMonitoredItemCount() {
        return monitoredItemCount;
    }

    public UInteger getMaxMonitoredItemCount() {
        return maxMonitoredItemCount;
    }

    public UInteger getDisabledMonitoredItemCount() {
        return disabledMonitoredItemCount;
    }

    public static final class Codec extends GenericDataTypeCodec<SamplingIntervalDiagnosticsDataType> {
        @Override
        public Class<SamplingIntervalDiagnosticsDataType> getType() {
            return SamplingIntervalDiagnosticsDataType.class;
        }

        @Override
        public SamplingIntervalDiagnosticsDataType decode(SerializationContext context,
                                                          UaDecoder decoder) {
            Double samplingInterval = decoder.readDouble("SamplingInterval");
            UInteger monitoredItemCount = decoder.readUInt32("MonitoredItemCount");
            UInteger maxMonitoredItemCount = decoder.readUInt32("MaxMonitoredItemCount");
            UInteger disabledMonitoredItemCount = decoder.readUInt32("DisabledMonitoredItemCount");
            return new SamplingIntervalDiagnosticsDataType(samplingInterval, monitoredItemCount, maxMonitoredItemCount, disabledMonitoredItemCount);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SamplingIntervalDiagnosticsDataType value) {
            encoder.writeDouble("SamplingInterval", value.getSamplingInterval());
            encoder.writeUInt32("MonitoredItemCount", value.getMonitoredItemCount());
            encoder.writeUInt32("MaxMonitoredItemCount", value.getMaxMonitoredItemCount());
            encoder.writeUInt32("DisabledMonitoredItemCount", value.getDisabledMonitoredItemCount());
        }
    }
}
