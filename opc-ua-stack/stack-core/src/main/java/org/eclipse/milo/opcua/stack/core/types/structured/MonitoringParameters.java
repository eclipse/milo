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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class MonitoringParameters implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoringParameters;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoringParameters_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoringParameters_Encoding_DefaultXml;

    protected final UInteger clientHandle;
    protected final Double samplingInterval;
    protected final ExtensionObject filter;
    protected final UInteger queueSize;
    protected final Boolean discardOldest;

    public MonitoringParameters() {
        this.clientHandle = null;
        this.samplingInterval = null;
        this.filter = null;
        this.queueSize = null;
        this.discardOldest = null;
    }

    public MonitoringParameters(UInteger clientHandle, Double samplingInterval, ExtensionObject filter, UInteger queueSize, Boolean discardOldest) {
        this.clientHandle = clientHandle;
        this.samplingInterval = samplingInterval;
        this.filter = filter;
        this.queueSize = queueSize;
        this.discardOldest = discardOldest;
    }

    public UInteger getClientHandle() { return clientHandle; }

    public Double getSamplingInterval() { return samplingInterval; }

    public ExtensionObject getFilter() { return filter; }

    public UInteger getQueueSize() { return queueSize; }

    public Boolean getDiscardOldest() { return discardOldest; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ClientHandle", clientHandle)
            .add("SamplingInterval", samplingInterval)
            .add("Filter", filter)
            .add("QueueSize", queueSize)
            .add("DiscardOldest", discardOldest)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<MonitoringParameters> {

        @Override
        public Class<MonitoringParameters> getType() {
            return MonitoringParameters.class;
        }

        @Override
        public MonitoringParameters decode(UaDecoder decoder) throws UaSerializationException {
            UInteger clientHandle = decoder.readUInt32("ClientHandle");
            Double samplingInterval = decoder.readDouble("SamplingInterval");
            ExtensionObject filter = decoder.readExtensionObject("Filter");
            UInteger queueSize = decoder.readUInt32("QueueSize");
            Boolean discardOldest = decoder.readBoolean("DiscardOldest");

            return new MonitoringParameters(clientHandle, samplingInterval, filter, queueSize, discardOldest);
        }

        @Override
        public void encode(MonitoringParameters value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("ClientHandle", value.clientHandle);
            encoder.writeDouble("SamplingInterval", value.samplingInterval);
            encoder.writeExtensionObject("Filter", value.filter);
            encoder.writeUInt32("QueueSize", value.queueSize);
            encoder.writeBoolean("DiscardOldest", value.discardOldest);
        }
    }

}
