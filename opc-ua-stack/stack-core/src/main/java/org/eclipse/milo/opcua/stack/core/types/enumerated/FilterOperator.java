/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.enumerated;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;

public enum FilterOperator implements UaEnumeration {

    Equals(0),
    IsNull(1),
    GreaterThan(2),
    LessThan(3),
    GreaterThanOrEqual(4),
    LessThanOrEqual(5),
    Like(6),
    Not(7),
    Between(8),
    InList(9),
    And(10),
    Or(11),
    Cast(12),
    InView(13),
    OfType(14),
    RelatedTo(15),
    BitwiseAnd(16),
    BitwiseOr(17);

    private final int value;

    FilterOperator(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    private static final ImmutableMap<Integer, FilterOperator> VALUES;

    static {
        Builder<Integer, FilterOperator> builder = ImmutableMap.builder();
        for (FilterOperator e : values()) {
            builder.put(e.getValue(), e);
        }
        VALUES = builder.build();
    }

    public static FilterOperator from(Integer value) {
        if (value == null) return null;
        return VALUES.getOrDefault(value, null);
    }

    public static void encode(FilterOperator filterOperator, UaEncoder encoder) {
        encoder.writeInt32(null, filterOperator.getValue());
    }

    public static FilterOperator decode(UaDecoder decoder) {
        int value = decoder.readInt32(null);

        return VALUES.getOrDefault(value, null);
    }

}
