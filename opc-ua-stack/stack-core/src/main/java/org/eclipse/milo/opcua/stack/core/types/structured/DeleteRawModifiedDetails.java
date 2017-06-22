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

@UaDataType("DeleteRawModifiedDetails")
public class DeleteRawModifiedDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.DeleteRawModifiedDetails;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteRawModifiedDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteRawModifiedDetails_Encoding_DefaultXml;

    protected final Boolean _isDeleteModified;
    protected final DateTime _startTime;
    protected final DateTime _endTime;

    public DeleteRawModifiedDetails() {
        super(null);
        this._isDeleteModified = null;
        this._startTime = null;
        this._endTime = null;
    }

    public DeleteRawModifiedDetails(NodeId _nodeId, Boolean _isDeleteModified, DateTime _startTime, DateTime _endTime) {
        super(_nodeId);
        this._isDeleteModified = _isDeleteModified;
        this._startTime = _startTime;
        this._endTime = _endTime;
    }

    public Boolean getIsDeleteModified() { return _isDeleteModified; }

    public DateTime getStartTime() { return _startTime; }

    public DateTime getEndTime() { return _endTime; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", _nodeId)
            .add("IsDeleteModified", _isDeleteModified)
            .add("StartTime", _startTime)
            .add("EndTime", _endTime)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DeleteRawModifiedDetails> {
        @Override
        public DeleteRawModifiedDetails decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            Boolean _isDeleteModified = reader.readBoolean();
            DateTime _startTime = reader.readDateTime();
            DateTime _endTime = reader.readDateTime();

            return new DeleteRawModifiedDetails(_nodeId, _isDeleteModified, _startTime, _endTime);
        }

        @Override
        public void encode(SerializationContext context, DeleteRawModifiedDetails value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._nodeId);
            writer.writeBoolean(value._isDeleteModified);
            writer.writeDateTime(value._startTime);
            writer.writeDateTime(value._endTime);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DeleteRawModifiedDetails> {
        @Override
        public DeleteRawModifiedDetails decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            Boolean _isDeleteModified = reader.readBoolean("IsDeleteModified");
            DateTime _startTime = reader.readDateTime("StartTime");
            DateTime _endTime = reader.readDateTime("EndTime");

            return new DeleteRawModifiedDetails(_nodeId, _isDeleteModified, _startTime, _endTime);
        }

        @Override
        public void encode(SerializationContext context, DeleteRawModifiedDetails encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeBoolean("IsDeleteModified", encodable._isDeleteModified);
            writer.writeDateTime("StartTime", encodable._startTime);
            writer.writeDateTime("EndTime", encodable._endTime);
        }
    }

}
