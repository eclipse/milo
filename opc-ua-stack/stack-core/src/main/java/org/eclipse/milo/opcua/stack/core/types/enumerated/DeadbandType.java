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

public enum DeadbandType implements UaEnumeration {
    None(0),

    Absolute(1),

    Percent(2);

    private final int value;

    DeadbandType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static DeadbandType from(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return Absolute;
            case 2:
                return Percent;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=718");
    }

    public static class Codec extends GenericDataTypeCodec<DeadbandType> {
        @Override
        public Class<DeadbandType> getType() {
            return DeadbandType.class;
        }

        @Override
        public DeadbandType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, DeadbandType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DeadbandType value) {
            encoder.writeEnum(null, value);
        }
    }
}
