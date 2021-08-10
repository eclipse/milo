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

public enum DataSetOrderingType implements UaEnumeration {
    Undefined(0),

    AscendingWriterId(1),

    AscendingWriterIdSingle(2);

    private final int value;

    DataSetOrderingType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static DataSetOrderingType from(int value) {
        switch (value) {
            case 0:
                return Undefined;
            case 1:
                return AscendingWriterId;
            case 2:
                return AscendingWriterIdSingle;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=20408");
    }

    public static class Codec extends GenericDataTypeCodec<DataSetOrderingType> {
        @Override
        public Class<DataSetOrderingType> getType() {
            return DataSetOrderingType.class;
        }

        @Override
        public DataSetOrderingType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, DataSetOrderingType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DataSetOrderingType value) {
            encoder.writeEnum(null, value);
        }
    }
}
