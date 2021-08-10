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

public enum DiagnosticsLevel implements UaEnumeration {
    Basic(0),

    Advanced(1),

    Info(2),

    Log(3),

    Debug(4);

    private final int value;

    DiagnosticsLevel(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static DiagnosticsLevel from(int value) {
        switch (value) {
            case 0:
                return Basic;
            case 1:
                return Advanced;
            case 2:
                return Info;
            case 3:
                return Log;
            case 4:
                return Debug;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=19723");
    }

    public static class Codec extends GenericDataTypeCodec<DiagnosticsLevel> {
        @Override
        public Class<DiagnosticsLevel> getType() {
            return DiagnosticsLevel.class;
        }

        @Override
        public DiagnosticsLevel decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, DiagnosticsLevel.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DiagnosticsLevel value) {
            encoder.writeEnum(null, value);
        }
    }
}
