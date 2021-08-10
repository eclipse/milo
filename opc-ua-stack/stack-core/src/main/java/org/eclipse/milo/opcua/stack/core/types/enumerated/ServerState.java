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

public enum ServerState implements UaEnumeration {
    Running(0),

    Failed(1),

    NoConfiguration(2),

    Suspended(3),

    Shutdown(4),

    Test(5),

    CommunicationFault(6),

    Unknown(7);

    private final int value;

    ServerState(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static ServerState from(int value) {
        switch (value) {
            case 0:
                return Running;
            case 1:
                return Failed;
            case 2:
                return NoConfiguration;
            case 3:
                return Suspended;
            case 4:
                return Shutdown;
            case 5:
                return Test;
            case 6:
                return CommunicationFault;
            case 7:
                return Unknown;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=852");
    }

    public static class Codec extends GenericDataTypeCodec<ServerState> {
        @Override
        public Class<ServerState> getType() {
            return ServerState.class;
        }

        @Override
        public ServerState decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, ServerState.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ServerState value) {
            encoder.writeEnum(null, value);
        }
    }
}
