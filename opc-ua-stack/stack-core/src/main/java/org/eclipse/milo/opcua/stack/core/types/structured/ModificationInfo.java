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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.HistoryUpdateType;

public class ModificationInfo implements UaStructure {

    public static final NodeId TypeId = Identifiers.ModificationInfo;
    public static final NodeId BinaryEncodingId = Identifiers.ModificationInfo_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ModificationInfo_Encoding_DefaultXml;

    protected final DateTime modificationTime;
    protected final HistoryUpdateType updateType;
    protected final String userName;

    public ModificationInfo() {
        this.modificationTime = null;
        this.updateType = null;
        this.userName = null;
    }

    public ModificationInfo(DateTime modificationTime, HistoryUpdateType updateType, String userName) {
        this.modificationTime = modificationTime;
        this.updateType = updateType;
        this.userName = userName;
    }

    public DateTime getModificationTime() { return modificationTime; }

    public HistoryUpdateType getUpdateType() { return updateType; }

    public String getUserName() { return userName; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ModificationTime", modificationTime)
            .add("UpdateType", updateType)
            .add("UserName", userName)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ModificationInfo> {

        @Override
        public Class<ModificationInfo> getType() {
            return ModificationInfo.class;
        }

        @Override
        public ModificationInfo decode(UaDecoder decoder) throws UaSerializationException {
            DateTime modificationTime = decoder.readDateTime("ModificationTime");
            HistoryUpdateType updateType = HistoryUpdateType.from(decoder.readInt32("UpdateType"));
            String userName = decoder.readString("UserName");

            return new ModificationInfo(modificationTime, updateType, userName);
        }

        @Override
        public void encode(ModificationInfo value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeDateTime("ModificationTime", value.modificationTime);
            encoder.writeInt32("UpdateType", value.updateType != null ? value.updateType.getValue() : 0);
            encoder.writeString("UserName", value.userName);
        }
    }

}
