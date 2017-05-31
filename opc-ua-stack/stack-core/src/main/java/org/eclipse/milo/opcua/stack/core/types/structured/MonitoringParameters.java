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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("MonitoringParameters")
public class MonitoringParameters implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoringParameters;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoringParameters_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoringParameters_Encoding_DefaultXml;

    protected final UInteger _clientHandle;
    protected final Double _samplingInterval;
    protected final ExtensionObject _filter;
    protected final UInteger _queueSize;
    protected final Boolean _discardOldest;

    public MonitoringParameters() {
        this._clientHandle = null;
        this._samplingInterval = null;
        this._filter = null;
        this._queueSize = null;
        this._discardOldest = null;
    }

    public MonitoringParameters(UInteger _clientHandle, Double _samplingInterval, ExtensionObject _filter, UInteger _queueSize, Boolean _discardOldest) {
        this._clientHandle = _clientHandle;
        this._samplingInterval = _samplingInterval;
        this._filter = _filter;
        this._queueSize = _queueSize;
        this._discardOldest = _discardOldest;
    }

    public UInteger getClientHandle() { return _clientHandle; }

    public Double getSamplingInterval() { return _samplingInterval; }

    public ExtensionObject getFilter() { return _filter; }

    public UInteger getQueueSize() { return _queueSize; }

    public Boolean getDiscardOldest() { return _discardOldest; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ClientHandle", _clientHandle)
            .add("SamplingInterval", _samplingInterval)
            .add("Filter", _filter)
            .add("QueueSize", _queueSize)
            .add("DiscardOldest", _discardOldest)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<MonitoringParameters> {
        @Override
        public MonitoringParameters decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _clientHandle = reader.readUInt32();
            Double _samplingInterval = reader.readDouble();
            ExtensionObject _filter = reader.readExtensionObject();
            UInteger _queueSize = reader.readUInt32();
            Boolean _discardOldest = reader.readBoolean();

            return new MonitoringParameters(_clientHandle, _samplingInterval, _filter, _queueSize, _discardOldest);
        }

        @Override
        public void encode(SerializationContext context, MonitoringParameters encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._clientHandle);
            writer.writeDouble(encodable._samplingInterval);
            writer.writeExtensionObject(encodable._filter);
            writer.writeUInt32(encodable._queueSize);
            writer.writeBoolean(encodable._discardOldest);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<MonitoringParameters> {
        @Override
        public MonitoringParameters decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _clientHandle = reader.readUInt32("ClientHandle");
            Double _samplingInterval = reader.readDouble("SamplingInterval");
            ExtensionObject _filter = reader.readExtensionObject("Filter");
            UInteger _queueSize = reader.readUInt32("QueueSize");
            Boolean _discardOldest = reader.readBoolean("DiscardOldest");

            return new MonitoringParameters(_clientHandle, _samplingInterval, _filter, _queueSize, _discardOldest);
        }

        @Override
        public void encode(SerializationContext context, MonitoringParameters encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("ClientHandle", encodable._clientHandle);
            writer.writeDouble("SamplingInterval", encodable._samplingInterval);
            writer.writeExtensionObject("Filter", encodable._filter);
            writer.writeUInt32("QueueSize", encodable._queueSize);
            writer.writeBoolean("DiscardOldest", encodable._discardOldest);
        }
    }

}
