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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("ReadProcessedDetails")
public class ReadProcessedDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadProcessedDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadProcessedDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadProcessedDetails_Encoding_DefaultXml;

    protected final DateTime _startTime;
    protected final DateTime _endTime;
    protected final Double _processingInterval;
    protected final NodeId[] _aggregateType;
    protected final AggregateConfiguration _aggregateConfiguration;

    public ReadProcessedDetails() {
        super();
        this._startTime = null;
        this._endTime = null;
        this._processingInterval = null;
        this._aggregateType = null;
        this._aggregateConfiguration = null;
    }

    public ReadProcessedDetails(DateTime _startTime, DateTime _endTime, Double _processingInterval, NodeId[] _aggregateType, AggregateConfiguration _aggregateConfiguration) {
        super();
        this._startTime = _startTime;
        this._endTime = _endTime;
        this._processingInterval = _processingInterval;
        this._aggregateType = _aggregateType;
        this._aggregateConfiguration = _aggregateConfiguration;
    }

    public DateTime getStartTime() { return _startTime; }

    public DateTime getEndTime() { return _endTime; }

    public Double getProcessingInterval() { return _processingInterval; }

    @Nullable
    public NodeId[] getAggregateType() { return _aggregateType; }

    public AggregateConfiguration getAggregateConfiguration() { return _aggregateConfiguration; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StartTime", _startTime)
            .add("EndTime", _endTime)
            .add("ProcessingInterval", _processingInterval)
            .add("AggregateType", _aggregateType)
            .add("AggregateConfiguration", _aggregateConfiguration)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReadProcessedDetails> {
        @Override
        public ReadProcessedDetails decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            DateTime _startTime = reader.readDateTime();
            DateTime _endTime = reader.readDateTime();
            Double _processingInterval = reader.readDouble();
            NodeId[] _aggregateType = reader.readArray(reader::readNodeId, NodeId.class);
            AggregateConfiguration _aggregateConfiguration = (AggregateConfiguration) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "AggregateConfiguration", reader);

            return new ReadProcessedDetails(_startTime, _endTime, _processingInterval, _aggregateType, _aggregateConfiguration);
        }

        @Override
        public void encode(SerializationContext context, ReadProcessedDetails encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime(encodable._startTime);
            writer.writeDateTime(encodable._endTime);
            writer.writeDouble(encodable._processingInterval);
            writer.writeArray(encodable._aggregateType, writer::writeNodeId);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "AggregateConfiguration", encodable._aggregateConfiguration, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReadProcessedDetails> {
        @Override
        public ReadProcessedDetails decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            DateTime _startTime = reader.readDateTime("StartTime");
            DateTime _endTime = reader.readDateTime("EndTime");
            Double _processingInterval = reader.readDouble("ProcessingInterval");
            NodeId[] _aggregateType = reader.readArray("AggregateType", reader::readNodeId, NodeId.class);
            AggregateConfiguration _aggregateConfiguration = (AggregateConfiguration) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "AggregateConfiguration", reader);

            return new ReadProcessedDetails(_startTime, _endTime, _processingInterval, _aggregateType, _aggregateConfiguration);
        }

        @Override
        public void encode(SerializationContext context, ReadProcessedDetails encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime("StartTime", encodable._startTime);
            writer.writeDateTime("EndTime", encodable._endTime);
            writer.writeDouble("ProcessingInterval", encodable._processingInterval);
            writer.writeArray("AggregateType", encodable._aggregateType, writer::writeNodeId);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "AggregateConfiguration", encodable._aggregateConfiguration, writer);
        }
    }

}
