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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
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

    public DataValue[] getDataValues() { return _dataValues; }

    public ModificationInfo[] getModificationInfos() { return _modificationInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(HistoryModifiedData historyModifiedData, UaEncoder encoder) {
        encoder.encodeArray("DataValues", historyModifiedData._dataValues, encoder::encodeDataValue);
        encoder.encodeArray("ModificationInfos", historyModifiedData._modificationInfos, encoder::encodeSerializable);
    }

    public static HistoryModifiedData decode(UaDecoder decoder) {
        DataValue[] _dataValues = decoder.decodeArray("DataValues", decoder::decodeDataValue, DataValue.class);
        ModificationInfo[] _modificationInfos = decoder.decodeArray("ModificationInfos", decoder::decodeSerializable, ModificationInfo.class);

        return new HistoryModifiedData(_dataValues, _modificationInfos);
    }

    static {
        DelegateRegistry.registerEncoder(HistoryModifiedData::encode, HistoryModifiedData.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(HistoryModifiedData::decode, HistoryModifiedData.class, BinaryEncodingId, XmlEncodingId);
    }

}
