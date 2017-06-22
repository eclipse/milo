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

@UaDataType("HistoryModifiedData")
public class HistoryModifiedData extends HistoryData {

    public static final NodeId TypeId = Identifiers.HistoryModifiedData;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryModifiedData_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryModifiedData_Encoding_DefaultXml;

    protected final DataValue[] _dataValues;
    protected final ModificationInfo[] _modificationInfos;

    public HistoryModifiedData() {
        super();
        this._dataValues = null;
        this._modificationInfos = null;
    }

    public HistoryModifiedData(DataValue[] _dataValues, ModificationInfo[] _modificationInfos) {
        super();
        this._dataValues = _dataValues;
        this._modificationInfos = _modificationInfos;
    }

    @Nullable
    public DataValue[] getDataValues() { return _dataValues; }

    @Nullable
    public ModificationInfo[] getModificationInfos() { return _modificationInfos; }

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
            .add("ModificationInfos", _modificationInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<HistoryModifiedData> {
        @Override
        public HistoryModifiedData decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            DataValue[] _dataValues = reader.readArray(reader::readDataValue, DataValue.class);
            ModificationInfo[] _modificationInfos =
                reader.readArray(
                    () -> (ModificationInfo) context.decode(
                        ModificationInfo.BinaryEncodingId, reader),
                    ModificationInfo.class
                );

            return new HistoryModifiedData(_dataValues, _modificationInfos);
        }

        @Override
        public void encode(SerializationContext context, HistoryModifiedData value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(value._dataValues, writer::writeDataValue);
            writer.writeArray(
                value._modificationInfos,
                e -> context.encode(ModificationInfo.BinaryEncodingId, e, writer)
            );
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<HistoryModifiedData> {
        @Override
        public HistoryModifiedData decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            DataValue[] _dataValues = reader.readArray("DataValues", reader::readDataValue, DataValue.class);
            ModificationInfo[] _modificationInfos =
                reader.readArray(
                    "ModificationInfos",
                    f -> (ModificationInfo) context.decode(
                        ModificationInfo.XmlEncodingId, reader),
                    ModificationInfo.class
                );

            return new HistoryModifiedData(_dataValues, _modificationInfos);
        }

        @Override
        public void encode(SerializationContext context, HistoryModifiedData encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray("DataValues", encodable._dataValues, writer::writeDataValue);
            writer.writeArray(
                "ModificationInfos",
                encodable._modificationInfos,
                (f, e) -> context.encode(ModificationInfo.XmlEncodingId, e, writer)
            );
        }
    }

}
