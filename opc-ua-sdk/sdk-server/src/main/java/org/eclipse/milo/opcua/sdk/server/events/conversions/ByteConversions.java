/*
 * Copyright (c) 2018 Kevin Herron
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
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class ByteConversions {

    private ByteConversions() {}

    @Nonnull
    static Boolean byteToBoolean(@Nonnull UByte ub) {
        return ub.intValue() != 0;
    }

    @Nonnull
    static Double byteToDouble(@Nonnull UByte ub) {
        return ub.doubleValue();
    }

    @Nonnull
    static Float byteToFloat(@Nonnull UByte ub) {
        return ub.floatValue();
    }

    @Nonnull
    static Short byteToInt16(@Nonnull UByte ub) {
        return ub.shortValue();
    }

    @Nonnull
    static Integer byteToInt32(@Nonnull UByte ub) {
        return ub.intValue();
    }

    @Nonnull
    static Long byteToInt64(@Nonnull UByte ub) {
        return ub.longValue();
    }

    @Nullable
    static Byte byteToSByte(@Nonnull UByte ub) {
        return ub.intValue() > Byte.MAX_VALUE ? null : ub.byteValue();
    }

    @Nonnull
    static String byteToString(@Nonnull UByte ub) {
        return ub.toString();
    }

    @Nonnull
    static UShort byteToUInt16(@Nonnull UByte ub) {
        return ushort(ub.intValue());
    }

    @Nonnull
    static UInteger byteToUInt32(@Nonnull UByte ub) {
        return uint(ub.intValue());
    }

    @Nonnull
    static ULong byteToUInt64(@Nonnull UByte ub) {
        return ulong(ub.longValue());
    }

}
