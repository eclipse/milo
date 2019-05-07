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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class MonitoringParameters extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=740");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=742");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=741");

    private final UInteger clientHandle;

    private final Double samplingInterval;

    private final ExtensionObject filter;

    private final UInteger queueSize;

    private final Boolean discardOldest;

    public MonitoringParameters(UInteger clientHandle, Double samplingInterval,
                                ExtensionObject filter, UInteger queueSize, Boolean discardOldest) {
        this.clientHandle = clientHandle;
        this.samplingInterval = samplingInterval;
        this.filter = filter;
        this.queueSize = queueSize;
        this.discardOldest = discardOldest;
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

    public UInteger getClientHandle() {
        return clientHandle;
    }

    public Double getSamplingInterval() {
        return samplingInterval;
    }

    public ExtensionObject getFilter() {
        return filter;
    }

    public UInteger getQueueSize() {
        return queueSize;
    }

    public Boolean getDiscardOldest() {
        return discardOldest;
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoringParameters> {
        @Override
        public Class<MonitoringParameters> getType() {
            return MonitoringParameters.class;
        }

        @Override
        public MonitoringParameters decode(SerializationContext context, UaDecoder decoder) {
            UInteger clientHandle = decoder.readUInt32("ClientHandle");
            Double samplingInterval = decoder.readDouble("SamplingInterval");
            ExtensionObject filter = decoder.readExtensionObject("Filter");
            UInteger queueSize = decoder.readUInt32("QueueSize");
            Boolean discardOldest = decoder.readBoolean("DiscardOldest");
            return new MonitoringParameters(clientHandle, samplingInterval, filter, queueSize, discardOldest);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoringParameters value) {
            encoder.writeUInt32("ClientHandle", value.getClientHandle());
            encoder.writeDouble("SamplingInterval", value.getSamplingInterval());
            encoder.writeExtensionObject("Filter", value.getFilter());
            encoder.writeUInt32("QueueSize", value.getQueueSize());
            encoder.writeBoolean("DiscardOldest", value.getDiscardOldest());
        }
    }
}
