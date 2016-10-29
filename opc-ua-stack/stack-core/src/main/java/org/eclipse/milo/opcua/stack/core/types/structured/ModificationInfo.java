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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.HistoryUpdateType;

@UaDataType("ModificationInfo")
public class ModificationInfo implements UaStructure {

    public static final NodeId TypeId = Identifiers.ModificationInfo;
    public static final NodeId BinaryEncodingId = Identifiers.ModificationInfo_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModificationInfo_Encoding_DefaultXml;

    protected final DateTime _modificationTime;
    protected final HistoryUpdateType _updateType;
    protected final String _userName;

    public ModificationInfo() {
        this._modificationTime = null;
        this._updateType = null;
        this._userName = null;
    }

    public ModificationInfo(DateTime _modificationTime, HistoryUpdateType _updateType, String _userName) {
        this._modificationTime = _modificationTime;
        this._updateType = _updateType;
        this._userName = _userName;
    }

    public DateTime getModificationTime() { return _modificationTime; }

    public HistoryUpdateType getUpdateType() { return _updateType; }

    public String getUserName() { return _userName; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ModificationInfo modificationInfo, UaEncoder encoder) {
        encoder.encodeDateTime("ModificationTime", modificationInfo._modificationTime);
        encoder.encodeEnumeration("UpdateType", modificationInfo._updateType);
        encoder.encodeString("UserName", modificationInfo._userName);
    }

    public static ModificationInfo decode(UaDecoder decoder) {
        DateTime _modificationTime = decoder.decodeDateTime("ModificationTime");
        HistoryUpdateType _updateType = decoder.decodeEnumeration("UpdateType", HistoryUpdateType.class);
        String _userName = decoder.decodeString("UserName");

        return new ModificationInfo(_modificationTime, _updateType, _userName);
    }

    static {
        DelegateRegistry.registerEncoder(ModificationInfo::encode, ModificationInfo.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ModificationInfo::decode, ModificationInfo.class, BinaryEncodingId, XmlEncodingId);
    }

}
