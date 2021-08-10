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

public enum BrowseDirection implements UaEnumeration {
    Forward(0),

    Inverse(1),

    Both(2),

    Invalid(3);

    private final int value;

    BrowseDirection(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static BrowseDirection from(int value) {
        switch (value) {
            case 0:
                return Forward;
            case 1:
                return Inverse;
            case 2:
                return Both;
            case 3:
                return Invalid;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=510");
    }

    public static class Codec extends GenericDataTypeCodec<BrowseDirection> {
        @Override
        public Class<BrowseDirection> getType() {
            return BrowseDirection.class;
        }

        @Override
        public BrowseDirection decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, BrowseDirection.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowseDirection value) {
            encoder.writeEnum(null, value);
        }
    }
}
