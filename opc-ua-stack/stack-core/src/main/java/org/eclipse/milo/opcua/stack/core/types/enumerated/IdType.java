/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.jetbrains.annotations.Nullable;

public enum IdType implements UaEnumeration {
    Numeric(0),

    String(1),

    Guid(2),

    Opaque(3);

    private final int value;

    IdType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static IdType from(int value) {
        switch (value) {
            case 0:
                return Numeric;
            case 1:
                return String;
            case 2:
                return Guid;
            case 3:
                return Opaque;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=256");
    }

    public static class Codec extends GenericDataTypeCodec<IdType> {
        @Override
        public Class<IdType> getType() {
            return IdType.class;
        }

        @Override
        public IdType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, IdType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, IdType value) {
            encoder.writeEnum(null, value);
        }
    }
}
