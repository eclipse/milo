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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class HistoryModifiedData extends HistoryData {

    public static final NodeId TypeId = Identifiers.HistoryModifiedData;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryModifiedData_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryModifiedData_Encoding_DefaultXml;

    protected final DataValue[] dataValues;
    protected final ModificationInfo[] modificationInfos;

    public HistoryModifiedData() {
        super();
        this.dataValues = null;
        this.modificationInfos = null;
    }

    public HistoryModifiedData(DataValue[] dataValues, ModificationInfo[] modificationInfos) {
        super();
        this.dataValues = dataValues;
        this.modificationInfos = modificationInfos;
    }

    @Nullable
    public DataValue[] getDataValues() { return dataValues; }

    @Nullable
    public ModificationInfo[] getModificationInfos() { return modificationInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("DataValues", dataValues)
            .add("ModificationInfos", modificationInfos)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<HistoryModifiedData> {

        @Override
        public Class<HistoryModifiedData> getType() {
            return HistoryModifiedData.class;
        }

        @Override
        public HistoryModifiedData decode(UaDecoder decoder) throws UaSerializationException {
            DataValue[] dataValues = decoder.readArray("DataValues", decoder::readDataValue, DataValue.class);
            ModificationInfo[] modificationInfos =
                decoder.readBuiltinStructArray(
                    "ModificationInfos",
                    ModificationInfo.class
                );

            return new HistoryModifiedData(dataValues, modificationInfos);
        }

        @Override
        public void encode(HistoryModifiedData value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeArray("DataValues", value.dataValues, encoder::writeDataValue);
            encoder.writeBuiltinStructArray(
                "ModificationInfos",
                value.modificationInfos,
                ModificationInfo.class
            );
        }
    }

}
