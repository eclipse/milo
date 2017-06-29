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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class MonitoredItemCreateResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoredItemCreateResult;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemCreateResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoredItemCreateResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final UInteger monitoredItemId;
    protected final Double revisedSamplingInterval;
    protected final UInteger revisedQueueSize;
    protected final ExtensionObject filterResult;

    public MonitoredItemCreateResult() {
        this.statusCode = null;
        this.monitoredItemId = null;
        this.revisedSamplingInterval = null;
        this.revisedQueueSize = null;
        this.filterResult = null;
    }

    public MonitoredItemCreateResult(StatusCode statusCode, UInteger monitoredItemId, Double revisedSamplingInterval, UInteger revisedQueueSize, ExtensionObject filterResult) {
        this.statusCode = statusCode;
        this.monitoredItemId = monitoredItemId;
        this.revisedSamplingInterval = revisedSamplingInterval;
        this.revisedQueueSize = revisedQueueSize;
        this.filterResult = filterResult;
    }

    public StatusCode getStatusCode() { return statusCode; }

    public UInteger getMonitoredItemId() { return monitoredItemId; }

    public Double getRevisedSamplingInterval() { return revisedSamplingInterval; }

    public UInteger getRevisedQueueSize() { return revisedQueueSize; }

    public ExtensionObject getFilterResult() { return filterResult; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", statusCode)
            .add("MonitoredItemId", monitoredItemId)
            .add("RevisedSamplingInterval", revisedSamplingInterval)
            .add("RevisedQueueSize", revisedQueueSize)
            .add("FilterResult", filterResult)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<MonitoredItemCreateResult> {

        @Override
        public Class<MonitoredItemCreateResult> getType() {
            return MonitoredItemCreateResult.class;
        }

        @Override
        public MonitoredItemCreateResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            UInteger monitoredItemId = decoder.readUInt32("MonitoredItemId");
            Double revisedSamplingInterval = decoder.readDouble("RevisedSamplingInterval");
            UInteger revisedQueueSize = decoder.readUInt32("RevisedQueueSize");
            ExtensionObject filterResult = decoder.readExtensionObject("FilterResult");

            return new MonitoredItemCreateResult(statusCode, monitoredItemId, revisedSamplingInterval, revisedQueueSize, filterResult);
        }

        @Override
        public void encode(MonitoredItemCreateResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeUInt32("MonitoredItemId", value.monitoredItemId);
            encoder.writeDouble("RevisedSamplingInterval", value.revisedSamplingInterval);
            encoder.writeUInt32("RevisedQueueSize", value.revisedQueueSize);
            encoder.writeExtensionObject("FilterResult", value.filterResult);
        }
    }

}
