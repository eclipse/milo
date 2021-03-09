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

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class TimeZoneDataType extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=8912");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=8913");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=8917");

    private final Short offset;

    private final Boolean daylightSavingInOffset;

    public TimeZoneDataType(Short offset, Boolean daylightSavingInOffset) {
        this.offset = offset;
        this.daylightSavingInOffset = daylightSavingInOffset;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public Short getOffset() {
        return offset;
    }

    public Boolean getDaylightSavingInOffset() {
        return daylightSavingInOffset;
    }

    public static final class Codec extends GenericDataTypeCodec<TimeZoneDataType> {
        @Override
        public Class<TimeZoneDataType> getType() {
            return TimeZoneDataType.class;
        }

        @Override
        public TimeZoneDataType decode(SerializationContext context, UaDecoder decoder) {
            Short offset = decoder.readInt16("Offset");
            Boolean daylightSavingInOffset = decoder.readBoolean("DaylightSavingInOffset");
            return new TimeZoneDataType(offset, daylightSavingInOffset);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, TimeZoneDataType value) {
            encoder.writeInt16("Offset", value.getOffset());
            encoder.writeBoolean("DaylightSavingInOffset", value.getDaylightSavingInOffset());
        }
    }
}
