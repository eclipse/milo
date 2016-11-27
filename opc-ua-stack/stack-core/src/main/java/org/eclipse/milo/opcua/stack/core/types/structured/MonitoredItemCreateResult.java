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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("MonitoredItemCreateResult")
public class MonitoredItemCreateResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoredItemCreateResult;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemCreateResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoredItemCreateResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final UInteger _monitoredItemId;
    protected final Double _revisedSamplingInterval;
    protected final UInteger _revisedQueueSize;
    protected final ExtensionObject _filterResult;

    public MonitoredItemCreateResult() {
        this._statusCode = null;
        this._monitoredItemId = null;
        this._revisedSamplingInterval = null;
        this._revisedQueueSize = null;
        this._filterResult = null;
    }

    public MonitoredItemCreateResult(StatusCode _statusCode, UInteger _monitoredItemId, Double _revisedSamplingInterval, UInteger _revisedQueueSize, ExtensionObject _filterResult) {
        this._statusCode = _statusCode;
        this._monitoredItemId = _monitoredItemId;
        this._revisedSamplingInterval = _revisedSamplingInterval;
        this._revisedQueueSize = _revisedQueueSize;
        this._filterResult = _filterResult;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public UInteger getMonitoredItemId() { return _monitoredItemId; }

    public Double getRevisedSamplingInterval() { return _revisedSamplingInterval; }

    public UInteger getRevisedQueueSize() { return _revisedQueueSize; }

    public ExtensionObject getFilterResult() { return _filterResult; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", _statusCode)
            .add("MonitoredItemId", _monitoredItemId)
            .add("RevisedSamplingInterval", _revisedSamplingInterval)
            .add("RevisedQueueSize", _revisedQueueSize)
            .add("FilterResult", _filterResult)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<MonitoredItemCreateResult> {
        @Override
        public MonitoredItemCreateResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode();
            UInteger _monitoredItemId = reader.readUInt32();
            Double _revisedSamplingInterval = reader.readDouble();
            UInteger _revisedQueueSize = reader.readUInt32();
            ExtensionObject _filterResult = reader.readExtensionObject();

            return new MonitoredItemCreateResult(_statusCode, _monitoredItemId, _revisedSamplingInterval, _revisedQueueSize, _filterResult);
        }

        @Override
        public void encode(SerializationContext context, MonitoredItemCreateResult encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode(encodable._statusCode);
            writer.writeUInt32(encodable._monitoredItemId);
            writer.writeDouble(encodable._revisedSamplingInterval);
            writer.writeUInt32(encodable._revisedQueueSize);
            writer.writeExtensionObject(encodable._filterResult);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<MonitoredItemCreateResult> {
        @Override
        public MonitoredItemCreateResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode _statusCode = reader.readStatusCode("StatusCode");
            UInteger _monitoredItemId = reader.readUInt32("MonitoredItemId");
            Double _revisedSamplingInterval = reader.readDouble("RevisedSamplingInterval");
            UInteger _revisedQueueSize = reader.readUInt32("RevisedQueueSize");
            ExtensionObject _filterResult = reader.readExtensionObject("FilterResult");

            return new MonitoredItemCreateResult(_statusCode, _monitoredItemId, _revisedSamplingInterval, _revisedQueueSize, _filterResult);
        }

        @Override
        public void encode(SerializationContext context, MonitoredItemCreateResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeStatusCode("StatusCode", encodable._statusCode);
            writer.writeUInt32("MonitoredItemId", encodable._monitoredItemId);
            writer.writeDouble("RevisedSamplingInterval", encodable._revisedSamplingInterval);
            writer.writeUInt32("RevisedQueueSize", encodable._revisedQueueSize);
            writer.writeExtensionObject("FilterResult", encodable._filterResult);
        }
    }

}
