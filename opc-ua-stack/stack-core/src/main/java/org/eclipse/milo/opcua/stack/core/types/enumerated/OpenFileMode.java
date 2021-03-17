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

public enum OpenFileMode implements UaEnumeration {
    Read(1),

    Write(2),

    EraseExisting(4),

    Append(8);

    private final int value;

    OpenFileMode(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static OpenFileMode from(int value) {
        switch (value) {
            case 1:
                return Read;
            case 2:
                return Write;
            case 4:
                return EraseExisting;
            case 8:
                return Append;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=11939");
    }

    public static class Codec extends GenericDataTypeCodec<OpenFileMode> {
        @Override
        public Class<OpenFileMode> getType() {
            return OpenFileMode.class;
        }

        @Override
        public OpenFileMode decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, OpenFileMode.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, OpenFileMode value) {
            encoder.writeEnum(null, value);
        }
    }
}
