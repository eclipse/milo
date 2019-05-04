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

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

public enum NodeAttributesMask implements UaEnumeration {
    None(0),

    AccessLevel(1),

    ArrayDimensions(2),

    BrowseName(4),

    ContainsNoLoops(8),

    DataType(16),

    Description(32),

    DisplayName(64),

    EventNotifier(128),

    Executable(256),

    Historizing(512),

    InverseName(1024),

    IsAbstract(2048),

    MinimumSamplingInterval(4096),

    NodeClass(8192),

    NodeId(16384),

    Symmetric(32768),

    UserAccessLevel(65536),

    UserExecutable(131072),

    UserWriteMask(262144),

    ValueRank(524288),

    WriteMask(1048576),

    Value(2097152),

    All(33554431),

    BaseNode(26501220),

    Object(26501348),

    ObjectType(26503268),

    Variable(26571383),

    VariableType(28600438),

    Method(26632548),

    ReferenceType(26537060),

    View(26501356);

    private final int value;

    NodeAttributesMask(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static NodeAttributesMask from(int value) {
        switch (value) {
            case 0:
                return None;
            case 1:
                return AccessLevel;
            case 2:
                return ArrayDimensions;
            case 4:
                return BrowseName;
            case 8:
                return ContainsNoLoops;
            case 16:
                return DataType;
            case 32:
                return Description;
            case 64:
                return DisplayName;
            case 128:
                return EventNotifier;
            case 256:
                return Executable;
            case 512:
                return Historizing;
            case 1024:
                return InverseName;
            case 2048:
                return IsAbstract;
            case 4096:
                return MinimumSamplingInterval;
            case 8192:
                return NodeClass;
            case 16384:
                return NodeId;
            case 32768:
                return Symmetric;
            case 65536:
                return UserAccessLevel;
            case 131072:
                return UserExecutable;
            case 262144:
                return UserWriteMask;
            case 524288:
                return ValueRank;
            case 1048576:
                return WriteMask;
            case 2097152:
                return Value;
            case 33554431:
                return All;
            case 26501220:
                return BaseNode;
            case 26501348:
                return Object;
            case 26503268:
                return ObjectType;
            case 26571383:
                return Variable;
            case 28600438:
                return VariableType;
            case 26632548:
                return Method;
            case 26537060:
                return ReferenceType;
            case 26501356:
                return View;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=348");
    }

    public static class Codec extends GenericDataTypeCodec<NodeAttributesMask> {
        @Override
        public Class<NodeAttributesMask> getType() {
            return NodeAttributesMask.class;
        }

        @Override
        public NodeAttributesMask decode(SerializationContext context, UaDecoder decoder) {
            return NodeAttributesMask.from(decoder.readInt32(null));
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, NodeAttributesMask value) {
            encoder.writeInt32(null, value.getValue());
        }
    }
}
