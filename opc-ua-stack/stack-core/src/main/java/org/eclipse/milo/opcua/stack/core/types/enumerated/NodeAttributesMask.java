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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;

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
    All(4194303),
    BaseNode(1335396),
    Object(1335524),
    ObjectTypeOrDataType(1337444),
    Variable(4026999),
    VariableType(3958902),
    Method(1466724),
    ReferenceType(1371236),
    View(1335532);

    private final int value;

    NodeAttributesMask(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    private static final ImmutableMap<Integer, NodeAttributesMask> VALUES;

    static {
        Builder<Integer, NodeAttributesMask> builder = ImmutableMap.builder();
        for (NodeAttributesMask e : values()) {
            builder.put(e.getValue(), e);
        }
        VALUES = builder.build();
    }

    public static NodeAttributesMask from(Integer value) {
        if (value == null) return null;
        return VALUES.getOrDefault(value, null);
    }

    public static void encode(NodeAttributesMask nodeAttributesMask, UaEncoder encoder) {
        encoder.writeInt32(null, nodeAttributesMask.getValue());
    }

    public static NodeAttributesMask decode(UaDecoder decoder) {
        int value = decoder.readInt32(null);

        return VALUES.getOrDefault(value, null);
    }

}
