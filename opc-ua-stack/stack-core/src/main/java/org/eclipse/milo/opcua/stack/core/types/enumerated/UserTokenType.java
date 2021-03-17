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

public enum UserTokenType implements UaEnumeration {
    Anonymous(0),

    UserName(1),

    Certificate(2),

    IssuedToken(3);

    private final int value;

    UserTokenType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static UserTokenType from(int value) {
        switch (value) {
            case 0:
                return Anonymous;
            case 1:
                return UserName;
            case 2:
                return Certificate;
            case 3:
                return IssuedToken;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=303");
    }

    public static class Codec extends GenericDataTypeCodec<UserTokenType> {
        @Override
        public Class<UserTokenType> getType() {
            return UserTokenType.class;
        }

        @Override
        public UserTokenType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, UserTokenType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, UserTokenType value) {
            encoder.writeEnum(null, value);
        }
    }
}
