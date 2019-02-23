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

public enum AxisScaleEnumeration implements UaEnumeration {

    Linear(0),
    Log(1),
    Ln(2);

    private final int value;

    AxisScaleEnumeration(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    private static final ImmutableMap<Integer, AxisScaleEnumeration> VALUES;

    static {
        Builder<Integer, AxisScaleEnumeration> builder = ImmutableMap.builder();
        for (AxisScaleEnumeration e : values()) {
            builder.put(e.getValue(), e);
        }
        VALUES = builder.build();
    }

    public static AxisScaleEnumeration from(Integer value) {
        if (value == null) return null;
        return VALUES.getOrDefault(value, null);
    }

    public static void encode(AxisScaleEnumeration axisScaleEnumeration, UaEncoder encoder) {
        encoder.writeInt32(null, axisScaleEnumeration.getValue());
    }

    public static AxisScaleEnumeration decode(UaDecoder decoder) {
        int value = decoder.readInt32(null);

        return VALUES.getOrDefault(value, null);
    }

}
