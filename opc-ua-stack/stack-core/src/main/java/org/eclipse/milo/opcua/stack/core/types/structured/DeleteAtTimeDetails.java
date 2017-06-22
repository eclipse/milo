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

import javax.annotation.Nullable;

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

@UaDataType("DeleteAtTimeDetails")
public class DeleteAtTimeDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.DeleteAtTimeDetails;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteAtTimeDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteAtTimeDetails_Encoding_DefaultXml;

    protected final DateTime[] _reqTimes;

    public DeleteAtTimeDetails() {
        super(null);
        this._reqTimes = null;
    }

    public DeleteAtTimeDetails(NodeId _nodeId, DateTime[] _reqTimes) {
        super(_nodeId);
        this._reqTimes = _reqTimes;
    }

    @Nullable
    public DateTime[] getReqTimes() { return _reqTimes; }

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
            .add("ReqTimes", _reqTimes)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DeleteAtTimeDetails> {
        @Override
        public DeleteAtTimeDetails decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            DateTime[] _reqTimes = reader.readArray(reader::readDateTime, DateTime.class);

            return new DeleteAtTimeDetails(_nodeId, _reqTimes);
        }

        @Override
        public void encode(SerializationContext context, DeleteAtTimeDetails value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._nodeId);
            writer.writeArray(value._reqTimes, writer::writeDateTime);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DeleteAtTimeDetails> {
        @Override
        public DeleteAtTimeDetails decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            DateTime[] _reqTimes = reader.readArray("ReqTimes", reader::readDateTime, DateTime.class);

            return new DeleteAtTimeDetails(_nodeId, _reqTimes);
        }

        @Override
        public void encode(SerializationContext context, DeleteAtTimeDetails encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeArray("ReqTimes", encodable._reqTimes, writer::writeDateTime);
        }
    }

}
