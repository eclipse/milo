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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ModificationTime", _modificationTime)
            .add("UpdateType", _updateType)
            .add("UserName", _userName)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ModificationInfo> {
        @Override
        public ModificationInfo decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            DateTime _modificationTime = reader.readDateTime();
            HistoryUpdateType _updateType = HistoryUpdateType.from(reader.readInt32());
            String _userName = reader.readString();

            return new ModificationInfo(_modificationTime, _updateType, _userName);
        }

        @Override
        public void encode(SerializationContext context, ModificationInfo value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime(value._modificationTime);
            writer.writeInt32(value._updateType != null ? value._updateType.getValue() : 0);
            writer.writeString(value._userName);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ModificationInfo> {
        @Override
        public ModificationInfo decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            DateTime _modificationTime = reader.readDateTime("ModificationTime");
            HistoryUpdateType _updateType = HistoryUpdateType.from(reader.readInt32("UpdateType"));
            String _userName = reader.readString("UserName");

            return new ModificationInfo(_modificationTime, _updateType, _userName);
        }

        @Override
        public void encode(SerializationContext context, ModificationInfo encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeDateTime("ModificationTime", encodable._modificationTime);
            writer.writeInt32("UpdateType", encodable._updateType != null ? encodable._updateType.getValue() : 0);
            writer.writeString("UserName", encodable._userName);
        }
    }

}
