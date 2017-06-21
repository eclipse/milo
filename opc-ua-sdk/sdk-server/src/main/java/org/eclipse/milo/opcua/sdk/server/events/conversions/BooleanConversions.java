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

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import javax.annotation.Nonnull;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class BooleanConversions {

    private BooleanConversions() {}

    @Nonnull
    static UByte booleanToByte(@Nonnull Boolean b) {
        return ubyte(b ? 1 : 0);
    }

    @Nonnull
    static Double booleanToDouble(@Nonnull Boolean b) {
        return b ? 1.0 : 0.0;
    }

    @Nonnull
    static Float booleanToFloat(@Nonnull Boolean b) {
        return b ? 1.0f : 0.0f;
    }

    @Nonnull
    static Short booleanToInt16(@Nonnull Boolean b) {
        return b ? (short) 1 : (short) 0;
    }

    @Nonnull
    static Integer booleanToInt32(@Nonnull Boolean b) {
        return b ? 1 : 0;
    }

    @Nonnull
    static Long booleanToInt64(@Nonnull Boolean b) {
        return b ? 1L : 0L;
    }

    @Nonnull
    static Byte booleanToSByte(@Nonnull Boolean b) {
        return b ? (byte) 1 : (byte) 0;
    }

    @Nonnull
    static UShort booleanToUInt16(@Nonnull Boolean b) {
        return b ? ushort(1) : ushort(0);
    }

    @Nonnull
    static UInteger booleanToUInt32(@Nonnull Boolean b) {
        return b ? uint(1) : uint(0);
    }

    @Nonnull
    static ULong booleanToUInt64(@Nonnull Boolean b) {
        return b ? ulong(1) : ulong(0);
    }

}
