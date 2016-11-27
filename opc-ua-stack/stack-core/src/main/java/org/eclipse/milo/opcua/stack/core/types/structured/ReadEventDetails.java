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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ReadEventDetails")
public class ReadEventDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadEventDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadEventDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadEventDetails_Encoding_DefaultXml;

    protected final UInteger _numValuesPerNode;
    protected final DateTime _startTime;
    protected final DateTime _endTime;
    protected final EventFilter _filter;

    public ReadEventDetails() {
        super();
        this._numValuesPerNode = null;
        this._startTime = null;
        this._endTime = null;
        this._filter = null;
    }

    public ReadEventDetails(UInteger _numValuesPerNode, DateTime _startTime, DateTime _endTime, EventFilter _filter) {
        super();
        this._numValuesPerNode = _numValuesPerNode;
        this._startTime = _startTime;
        this._endTime = _endTime;
        this._filter = _filter;
    }

    public UInteger getNumValuesPerNode() { return _numValuesPerNode; }

    public DateTime getStartTime() { return _startTime; }

    public DateTime getEndTime() { return _endTime; }

    public EventFilter getFilter() { return _filter; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NumValuesPerNode", _numValuesPerNode)
            .add("StartTime", _startTime)
            .add("EndTime", _endTime)
            .add("Filter", _filter)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReadEventDetails> {
        @Override
        public ReadEventDetails decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _numValuesPerNode = reader.readUInt32();
            DateTime _startTime = reader.readDateTime();
            DateTime _endTime = reader.readDateTime();
            EventFilter _filter = (EventFilter) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EventFilter", reader);

            return new ReadEventDetails(_numValuesPerNode, _startTime, _endTime, _filter);
        }

        @Override
        public void encode(SerializationContext context, ReadEventDetails encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._numValuesPerNode);
            writer.writeDateTime(encodable._startTime);
            writer.writeDateTime(encodable._endTime);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EventFilter", encodable._filter, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReadEventDetails> {
        @Override
        public ReadEventDetails decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _numValuesPerNode = reader.readUInt32("NumValuesPerNode");
            DateTime _startTime = reader.readDateTime("StartTime");
            DateTime _endTime = reader.readDateTime("EndTime");
            EventFilter _filter = (EventFilter) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EventFilter", reader);

            return new ReadEventDetails(_numValuesPerNode, _startTime, _endTime, _filter);
        }

        @Override
        public void encode(SerializationContext context, ReadEventDetails encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("NumValuesPerNode", encodable._numValuesPerNode);
            writer.writeDateTime("StartTime", encodable._startTime);
            writer.writeDateTime("EndTime", encodable._endTime);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "EventFilter", encodable._filter, writer);
        }
    }

}
