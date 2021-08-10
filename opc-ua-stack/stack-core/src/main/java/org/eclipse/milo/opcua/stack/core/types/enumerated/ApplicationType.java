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

public enum ApplicationType implements UaEnumeration {
    Server(0),

    Client(1),

    ClientAndServer(2),

    DiscoveryServer(3);

    private final int value;

    ApplicationType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static ApplicationType from(int value) {
        switch (value) {
            case 0:
                return Server;
            case 1:
                return Client;
            case 2:
                return ClientAndServer;
            case 3:
                return DiscoveryServer;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=307");
    }

    public static class Codec extends GenericDataTypeCodec<ApplicationType> {
        @Override
        public Class<ApplicationType> getType() {
            return ApplicationType.class;
        }

        @Override
        public ApplicationType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, ApplicationType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ApplicationType value) {
            encoder.writeEnum(null, value);
        }
    }
}
