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

public enum IdentityCriteriaType implements UaEnumeration {
    UserName(1),

    Thumbprint(2),

    Role(3),

    GroupId(4),

    Anonymous(5),

    AuthenticatedUser(6);

    private final int value;

    IdentityCriteriaType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static IdentityCriteriaType from(int value) {
        switch (value) {
            case 1:
                return UserName;
            case 2:
                return Thumbprint;
            case 3:
                return Role;
            case 4:
                return GroupId;
            case 5:
                return Anonymous;
            case 6:
                return AuthenticatedUser;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15632");
    }

    public static class Codec extends GenericDataTypeCodec<IdentityCriteriaType> {
        @Override
        public Class<IdentityCriteriaType> getType() {
            return IdentityCriteriaType.class;
        }

        @Override
        public IdentityCriteriaType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, IdentityCriteriaType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           IdentityCriteriaType value) {
            encoder.writeEnum(null, value);
        }
    }
}
