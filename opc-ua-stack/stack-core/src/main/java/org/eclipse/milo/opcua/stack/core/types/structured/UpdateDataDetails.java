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
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.PerformUpdateType;

@UaDataType("UpdateDataDetails")
public class UpdateDataDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.UpdateDataDetails;
    public static final NodeId BinaryEncodingId = Identifiers.UpdateDataDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UpdateDataDetails_Encoding_DefaultXml;

    protected final PerformUpdateType _performInsertReplace;
    protected final DataValue[] _updateValues;

    public UpdateDataDetails() {
        super(null);
        this._performInsertReplace = null;
        this._updateValues = null;
    }

    public UpdateDataDetails(NodeId _nodeId, PerformUpdateType _performInsertReplace, DataValue[] _updateValues) {
        super(_nodeId);
        this._performInsertReplace = _performInsertReplace;
        this._updateValues = _updateValues;
    }

    public PerformUpdateType getPerformInsertReplace() { return _performInsertReplace; }

    @Nullable
    public DataValue[] getUpdateValues() { return _updateValues; }

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
            .add("PerformInsertReplace", _performInsertReplace)
            .add("UpdateValues", _updateValues)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<UpdateDataDetails> {
        @Override
        public UpdateDataDetails decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            PerformUpdateType _performInsertReplace = PerformUpdateType.from(reader.readInt32());
            DataValue[] _updateValues = reader.readArray(reader::readDataValue, DataValue.class);

            return new UpdateDataDetails(_nodeId, _performInsertReplace, _updateValues);
        }

        @Override
        public void encode(SerializationContext context, UpdateDataDetails encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._nodeId);
            writer.writeInt32(encodable._performInsertReplace != null ? encodable._performInsertReplace.getValue() : 0);
            writer.writeArray(encodable._updateValues, writer::writeDataValue);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<UpdateDataDetails> {
        @Override
        public UpdateDataDetails decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            PerformUpdateType _performInsertReplace = PerformUpdateType.from(reader.readInt32("PerformInsertReplace"));
            DataValue[] _updateValues = reader.readArray("UpdateValues", reader::readDataValue, DataValue.class);

            return new UpdateDataDetails(_nodeId, _performInsertReplace, _updateValues);
        }

        @Override
        public void encode(SerializationContext context, UpdateDataDetails encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeInt32("PerformInsertReplace", encodable._performInsertReplace != null ? encodable._performInsertReplace.getValue() : 0);
            writer.writeArray("UpdateValues", encodable._updateValues, writer::writeDataValue);
        }
    }

}
