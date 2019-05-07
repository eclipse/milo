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
public class MonitoredItemModifyResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=758");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=760");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=759");

    private final StatusCode statusCode;

    private final Double revisedSamplingInterval;

    private final UInteger revisedQueueSize;

    private final ExtensionObject filterResult;

    public MonitoredItemModifyResult(StatusCode statusCode, Double revisedSamplingInterval,
                                     UInteger revisedQueueSize, ExtensionObject filterResult) {
        this.statusCode = statusCode;
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

    public Double getRevisedSamplingInterval() {
        return revisedSamplingInterval;
    }

    public UInteger getRevisedQueueSize() {
        return revisedQueueSize;
    }

    public ExtensionObject getFilterResult() {
        return filterResult;
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoredItemModifyResult> {
        @Override
        public Class<MonitoredItemModifyResult> getType() {
            return MonitoredItemModifyResult.class;
        }

        @Override
        public MonitoredItemModifyResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            Double revisedSamplingInterval = decoder.readDouble("RevisedSamplingInterval");
            UInteger revisedQueueSize = decoder.readUInt32("RevisedQueueSize");
            ExtensionObject filterResult = decoder.readExtensionObject("FilterResult");
            return new MonitoredItemModifyResult(statusCode, revisedSamplingInterval, revisedQueueSize, filterResult);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoredItemModifyResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeDouble("RevisedSamplingInterval", value.getRevisedSamplingInterval());
            encoder.writeUInt32("RevisedQueueSize", value.getRevisedQueueSize());
            encoder.writeExtensionObject("FilterResult", value.getFilterResult());
        }
    }
}
