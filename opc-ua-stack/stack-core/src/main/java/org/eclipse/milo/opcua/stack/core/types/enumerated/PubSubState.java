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

public enum PubSubState implements UaEnumeration {
    Disabled(0),

    Paused(1),

    Operational(2),

    Error(3);

    private final int value;

    PubSubState(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static PubSubState from(int value) {
        switch (value) {
            case 0:
                return Disabled;
            case 1:
                return Paused;
            case 2:
                return Operational;
            case 3:
                return Error;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=14647");
    }

    public static class Codec extends GenericDataTypeCodec<PubSubState> {
        @Override
        public Class<PubSubState> getType() {
            return PubSubState.class;
        }

        @Override
        public PubSubState decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, PubSubState.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, PubSubState value) {
            encoder.writeEnum(null, value);
        }
    }
}
