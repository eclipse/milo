/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.enumerated;

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

public enum TimestampsToReturn implements UaEnumeration {
    Source(0),

    Server(1),

    Both(2),

    Neither(3),

    Invalid(4);

    private final int value;

    TimestampsToReturn(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static TimestampsToReturn from(int value) {
        switch (value) {
            case 0:
                return Source;
            case 1:
                return Server;
            case 2:
                return Both;
            case 3:
                return Neither;
            case 4:
                return Invalid;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=625");
    }

    public static class Codec extends GenericDataTypeCodec<TimestampsToReturn> {
        @Override
        public Class<TimestampsToReturn> getType() {
            return TimestampsToReturn.class;
        }

        @Override
        public TimestampsToReturn decode(SerializationContext context, UaDecoder decoder) {
            return TimestampsToReturn.from(decoder.readInt32(null));
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, TimestampsToReturn value) {
            encoder.writeInt32(null, value.getValue());
        }
    }
}
