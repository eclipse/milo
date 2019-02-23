/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class TimeZoneDataType implements UaStructure {

    public static final NodeId TypeId = Identifiers.TimeZoneDataType;
    public static final NodeId BinaryEncodingId = Identifiers.TimeZoneDataType_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TimeZoneDataType_Encoding_DefaultXml;

    protected final Short offset;
    protected final Boolean daylightSavingInOffset;

    public TimeZoneDataType() {
        this.offset = null;
        this.daylightSavingInOffset = null;
    }

    public TimeZoneDataType(Short offset, Boolean daylightSavingInOffset) {
        this.offset = offset;
        this.daylightSavingInOffset = daylightSavingInOffset;
    }

    public Short getOffset() { return offset; }

    public Boolean getDaylightSavingInOffset() { return daylightSavingInOffset; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Offset", offset)
            .add("DaylightSavingInOffset", daylightSavingInOffset)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<TimeZoneDataType> {

        @Override
        public Class<TimeZoneDataType> getType() {
            return TimeZoneDataType.class;
        }

        @Override
        public TimeZoneDataType decode(UaDecoder decoder) throws UaSerializationException {
            Short offset = decoder.readInt16("Offset");
            Boolean daylightSavingInOffset = decoder.readBoolean("DaylightSavingInOffset");

            return new TimeZoneDataType(offset, daylightSavingInOffset);
        }

        @Override
        public void encode(TimeZoneDataType value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeInt16("Offset", value.offset);
            encoder.writeBoolean("DaylightSavingInOffset", value.daylightSavingInOffset);
        }
    }

}
