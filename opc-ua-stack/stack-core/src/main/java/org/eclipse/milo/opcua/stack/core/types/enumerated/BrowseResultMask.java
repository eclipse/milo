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

public enum BrowseResultMask implements UaEnumeration {
    None(0),

    ReferenceTypeId(1),

    IsForward(2),

    NodeClass(4),

    BrowseName(8),

    DisplayName(16),

    TypeDefinition(32),

    All(63),

    ReferenceTypeInfo(3),

    TargetInfo(60);

    private final int value;

    BrowseResultMask(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static BrowseResultMask from(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return ReferenceTypeId;
            case 2:
                return IsForward;
            case 4:
                return NodeClass;
            case 8:
                return BrowseName;
            case 16:
                return DisplayName;
            case 32:
                return TypeDefinition;
            case 63:
                return All;
            case 3:
                return ReferenceTypeInfo;
            case 60:
                return TargetInfo;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=517");
    }

    public static class Codec extends GenericDataTypeCodec<BrowseResultMask> {
        @Override
        public Class<BrowseResultMask> getType() {
            return BrowseResultMask.class;
        }

        @Override
        public BrowseResultMask decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, BrowseResultMask.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowseResultMask value) {
            encoder.writeEnum(null, value);
        }
    }
}
