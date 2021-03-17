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

public enum SecurityTokenRequestType implements UaEnumeration {
    Issue(0),

    Renew(1);

    private final int value;

    SecurityTokenRequestType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static SecurityTokenRequestType from(int value) {
        switch (value) {
            case 0:
                return Issue;
            case 1:
                return Renew;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=315");
    }

    public static class Codec extends GenericDataTypeCodec<SecurityTokenRequestType> {
        @Override
        public Class<SecurityTokenRequestType> getType() {
            return SecurityTokenRequestType.class;
        }

        @Override
        public SecurityTokenRequestType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, SecurityTokenRequestType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           SecurityTokenRequestType value) {
            encoder.writeEnum(null, value);
        }
    }
}
