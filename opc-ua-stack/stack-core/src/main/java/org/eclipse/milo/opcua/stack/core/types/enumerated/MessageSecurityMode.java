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

public enum MessageSecurityMode implements UaEnumeration {

    Invalid(0),
    None(1),
    Sign(2),
    SignAndEncrypt(3);

    private final int value;

    MessageSecurityMode(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    private static final ImmutableMap<Integer, MessageSecurityMode> VALUES;

    static {
        Builder<Integer, MessageSecurityMode> builder = ImmutableMap.builder();
        for (MessageSecurityMode e : values()) {
            builder.put(e.getValue(), e);
        }
        VALUES = builder.build();
    }

    public static MessageSecurityMode from(Integer value) {
        if (value == null) return null;
        return VALUES.getOrDefault(value, null);
    }

    public static void encode(MessageSecurityMode messageSecurityMode, UaEncoder encoder) {
        encoder.writeInt32(null, messageSecurityMode.getValue());
    }

    public static MessageSecurityMode decode(UaDecoder decoder) {
        int value = decoder.readInt32(null);

        return VALUES.getOrDefault(value, null);
    }

}
