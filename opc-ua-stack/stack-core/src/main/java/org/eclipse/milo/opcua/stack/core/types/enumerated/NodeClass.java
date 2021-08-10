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

public enum NodeClass implements UaEnumeration {
    Unspecified(0),

    Object(1),

    Variable(2),

    Method(4),

    ObjectType(8),

    VariableType(16),

    ReferenceType(32),

    DataType(64),

    View(128);

    private final int value;

    NodeClass(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static NodeClass from(int value) {
        switch (value) {
            case 0:
                return Unspecified;
            case 1:
                return Object;
            case 2:
                return Variable;
            case 4:
                return Method;
            case 8:
                return ObjectType;
            case 16:
                return VariableType;
            case 32:
                return ReferenceType;
            case 64:
                return DataType;
            case 128:
                return View;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=257");
    }

    public static class Codec extends GenericDataTypeCodec<NodeClass> {
        @Override
        public Class<NodeClass> getType() {
            return NodeClass.class;
        }

        @Override
        public NodeClass decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, NodeClass.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, NodeClass value) {
            encoder.writeEnum(null, value);
        }
    }
}
