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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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

@UaDataType("HistoryData")
public class HistoryData implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryData;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryData_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryData_Encoding_DefaultXml;

    protected final DataValue[] _dataValues;

    public HistoryData() {
        this._dataValues = null;
    }

    public HistoryData(DataValue[] _dataValues) {
        this._dataValues = _dataValues;
    }

    @Nullable
    public DataValue[] getDataValues() { return _dataValues; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("DataValues", _dataValues)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryData> {
        @Override
        public HistoryData decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            DataValue[] _dataValues = reader.readArray(reader::readDataValue, DataValue.class);

            return new HistoryData(_dataValues);
        }

        @Override
        public void encode(SerializationContext context, HistoryData value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(value._dataValues, writer::writeDataValue);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryData> {
        @Override
        public HistoryData decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            DataValue[] _dataValues = reader.readArray("DataValues", reader::readDataValue, DataValue.class);

            return new HistoryData(_dataValues);
        }

        @Override
        public void encode(SerializationContext context, HistoryData encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray("DataValues", encodable._dataValues, writer::writeDataValue);
        }
    }

}
