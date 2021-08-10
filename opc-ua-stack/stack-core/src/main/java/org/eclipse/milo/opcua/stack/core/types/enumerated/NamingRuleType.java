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

public enum NamingRuleType implements UaEnumeration {
    Mandatory(1),

    Optional(2),

    Constraint(3);

    private final int value;

    NamingRuleType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static NamingRuleType from(int value) {
        switch (value) {
            case 1:
                return Mandatory;
            case 2:
                return Optional;
            case 3:
                return Constraint;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=120");
    }

    public static class Codec extends GenericDataTypeCodec<NamingRuleType> {
        @Override
        public Class<NamingRuleType> getType() {
            return NamingRuleType.class;
        }

        @Override
        public NamingRuleType decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, NamingRuleType.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, NamingRuleType value) {
            encoder.writeEnum(null, value);
        }
    }
}
