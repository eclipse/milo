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

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.jetbrains.annotations.Nullable;

public enum HistoryUpdateType implements UaEnumeration {
    Insert(1),

    Replace(2),

    Update(3),

    Delete(4);

    private final int value;

    HistoryUpdateType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static HistoryUpdateType from(int value) {
        switch (value) {
            case 1:
                return Insert;
            case 2:
                return Replace;
            case 3:
                return Update;
            case 4:
                return Delete;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11234");
    }

    public static class Codec extends GenericDataTypeCodec<HistoryUpdateType> {
        @Override
        public Class<HistoryUpdateType> getType() {
            return HistoryUpdateType.class;
        }

        @Override
        public HistoryUpdateType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, HistoryUpdateType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, HistoryUpdateType value) {
            encoder.writeEnum(null, value);
        }
    }
}
