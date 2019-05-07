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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class MonitoredItemCreateResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=746");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=748");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=747");

    private final StatusCode statusCode;

    private final UInteger monitoredItemId;

    private final Double revisedSamplingInterval;

    private final UInteger revisedQueueSize;

    private final ExtensionObject filterResult;

    public MonitoredItemCreateResult(StatusCode statusCode, UInteger monitoredItemId,
                                     Double revisedSamplingInterval, UInteger revisedQueueSize, ExtensionObject filterResult) {
        this.statusCode = statusCode;
        this.monitoredItemId = monitoredItemId;
        this.revisedSamplingInterval = revisedSamplingInterval;
        this.revisedQueueSize = revisedQueueSize;
        this.filterResult = filterResult;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public UInteger getMonitoredItemId() {
        return monitoredItemId;
    }

    public Double getRevisedSamplingInterval() {
        return revisedSamplingInterval;
    }

    public UInteger getRevisedQueueSize() {
        return revisedQueueSize;
    }

    public ExtensionObject getFilterResult() {
        return filterResult;
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoredItemCreateResult> {
        @Override
        public Class<MonitoredItemCreateResult> getType() {
            return MonitoredItemCreateResult.class;
        }

        @Override
        public MonitoredItemCreateResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            UInteger monitoredItemId = decoder.readUInt32("MonitoredItemId");
            Double revisedSamplingInterval = decoder.readDouble("RevisedSamplingInterval");
            UInteger revisedQueueSize = decoder.readUInt32("RevisedQueueSize");
            ExtensionObject filterResult = decoder.readExtensionObject("FilterResult");
            return new MonitoredItemCreateResult(statusCode, monitoredItemId, revisedSamplingInterval, revisedQueueSize, filterResult);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoredItemCreateResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeUInt32("MonitoredItemId", value.getMonitoredItemId());
            encoder.writeDouble("RevisedSamplingInterval", value.getRevisedSamplingInterval());
            encoder.writeUInt32("RevisedQueueSize", value.getRevisedQueueSize());
            encoder.writeExtensionObject("FilterResult", value.getFilterResult());
        }
    }
}
