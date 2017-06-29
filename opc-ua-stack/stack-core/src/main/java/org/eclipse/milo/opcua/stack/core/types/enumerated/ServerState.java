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

public enum ServerState implements UaEnumeration {

    Running(0),
    Failed(1),
    NoConfiguration(2),
    Suspended(3),
    Shutdown(4),
    Test(5),
    CommunicationFault(6),
    Unknown(7);

    private final int value;

    ServerState(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    private static final ImmutableMap<Integer, ServerState> VALUES;

    static {
        Builder<Integer, ServerState> builder = ImmutableMap.builder();
        for (ServerState e : values()) {
            builder.put(e.getValue(), e);
        }
        VALUES = builder.build();
    }

    public static ServerState from(Integer value) {
        if (value == null) return null;
        return VALUES.getOrDefault(value, null);
    }

    public static void encode(ServerState serverState, UaEncoder encoder) {
        encoder.writeInt32(null, serverState.getValue());
    }

    public static ServerState decode(UaDecoder decoder) {
        int value = decoder.readInt32(null);

        return VALUES.getOrDefault(value, null);
    }

}
