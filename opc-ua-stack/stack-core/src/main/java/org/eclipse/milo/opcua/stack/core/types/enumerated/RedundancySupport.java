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

public enum RedundancySupport implements UaEnumeration {

    None(0),
    Cold(1),
    Warm(2),
    Hot(3),
    Transparent(4),
    HotAndMirrored(5);

    private final int value;

    RedundancySupport(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    private static final ImmutableMap<Integer, RedundancySupport> VALUES;

    static {
        Builder<Integer, RedundancySupport> builder = ImmutableMap.builder();
        for (RedundancySupport e : values()) {
            builder.put(e.getValue(), e);
        }
        VALUES = builder.build();
    }

    public static RedundancySupport from(Integer value) {
        if (value == null) return null;
        return VALUES.getOrDefault(value, null);
    }

    public static void encode(RedundancySupport redundancySupport, UaEncoder encoder) {
        encoder.writeInt32(null, redundancySupport.getValue());
    }

    public static RedundancySupport decode(UaDecoder decoder) {
        int value = decoder.readInt32(null);

        return VALUES.getOrDefault(value, null);
    }

}
