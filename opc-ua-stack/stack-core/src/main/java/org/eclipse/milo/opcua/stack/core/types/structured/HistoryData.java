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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class HistoryData implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryData;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryData_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryData_Encoding_DefaultXml;

    protected final DataValue[] dataValues;

    public HistoryData() {
        this.dataValues = null;
    }

    public HistoryData(DataValue[] dataValues) {
        this.dataValues = dataValues;
    }

    @Nullable
    public DataValue[] getDataValues() { return dataValues; }

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
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<HistoryData> {

        @Override
        public Class<HistoryData> getType() {
            return HistoryData.class;
        }

        @Override
        public HistoryData decode(UaDecoder decoder) throws UaSerializationException {
            DataValue[] dataValues = decoder.readArray("DataValues", decoder::readDataValue, DataValue.class);

            return new HistoryData(dataValues);
        }

        @Override
        public void encode(HistoryData value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeArray("DataValues", value.dataValues, encoder::writeDataValue);
        }
    }

}
