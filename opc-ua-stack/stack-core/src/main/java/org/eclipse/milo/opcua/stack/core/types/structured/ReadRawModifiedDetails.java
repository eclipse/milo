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

@UaDataType("ReadRawModifiedDetails")
public class ReadRawModifiedDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadRawModifiedDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadRawModifiedDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadRawModifiedDetails_Encoding_DefaultXml;

    protected final Boolean _isReadModified;
    protected final DateTime _startTime;
    protected final DateTime _endTime;
    protected final UInteger _numValuesPerNode;
    protected final Boolean _returnBounds;

    public ReadRawModifiedDetails() {
        super();
        this._isReadModified = null;
        this._startTime = null;
        this._endTime = null;
        this._numValuesPerNode = null;
        this._returnBounds = null;
    }

    public ReadRawModifiedDetails(Boolean _isReadModified, DateTime _startTime, DateTime _endTime, UInteger _numValuesPerNode, Boolean _returnBounds) {
        super();
        this._isReadModified = _isReadModified;
        this._startTime = _startTime;
        this._endTime = _endTime;
        this._numValuesPerNode = _numValuesPerNode;
        this._returnBounds = _returnBounds;
    }

    public Boolean getIsReadModified() { return _isReadModified; }

    public DateTime getStartTime() { return _startTime; }

    public DateTime getEndTime() { return _endTime; }

    public UInteger getNumValuesPerNode() { return _numValuesPerNode; }

    public Boolean getReturnBounds() { return _returnBounds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("IsReadModified", _isReadModified)
            .add("StartTime", _startTime)
            .add("EndTime", _endTime)
            .add("NumValuesPerNode", _numValuesPerNode)
            .add("ReturnBounds", _returnBounds)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReadRawModifiedDetails> {
        @Override
        public ReadRawModifiedDetails decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            Boolean _isReadModified = reader.readBoolean();
            DateTime _startTime = reader.readDateTime();
            DateTime _endTime = reader.readDateTime();
            UInteger _numValuesPerNode = reader.readUInt32();
            Boolean _returnBounds = reader.readBoolean();

            return new ReadRawModifiedDetails(_isReadModified, _startTime, _endTime, _numValuesPerNode, _returnBounds);
        }

        @Override
        public void encode(SerializationContext context, ReadRawModifiedDetails value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeBoolean(value._isReadModified);
            writer.writeDateTime(value._startTime);
            writer.writeDateTime(value._endTime);
            writer.writeUInt32(value._numValuesPerNode);
            writer.writeBoolean(value._returnBounds);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReadRawModifiedDetails> {
        @Override
        public ReadRawModifiedDetails decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            Boolean _isReadModified = reader.readBoolean("IsReadModified");
            DateTime _startTime = reader.readDateTime("StartTime");
            DateTime _endTime = reader.readDateTime("EndTime");
            UInteger _numValuesPerNode = reader.readUInt32("NumValuesPerNode");
            Boolean _returnBounds = reader.readBoolean("ReturnBounds");

            return new ReadRawModifiedDetails(_isReadModified, _startTime, _endTime, _numValuesPerNode, _returnBounds);
        }

        @Override
        public void encode(SerializationContext context, ReadRawModifiedDetails encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeBoolean("IsReadModified", encodable._isReadModified);
            writer.writeDateTime("StartTime", encodable._startTime);
            writer.writeDateTime("EndTime", encodable._endTime);
            writer.writeUInt32("NumValuesPerNode", encodable._numValuesPerNode);
            writer.writeBoolean("ReturnBounds", encodable._returnBounds);
        }
    }

}
