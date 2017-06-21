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

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class Int16Conversions {

    private Int16Conversions() {}

    @Nonnull
    static Boolean int16ToBoolean(@Nonnull Short s) {
        return s != 0;
    }

    @Nullable
    static UByte int16ToByte(@Nonnull Short s) {
        if (s >= 0 && s <= UByte.MAX_VALUE) {
            return ubyte(s);
        } else {
            return null;
        }
    }

    @Nonnull
    static Double int16ToDouble(@Nonnull Short s) {
        return (double) s;
    }

    @Nonnull
    static Float int16ToFloat(@Nonnull Short s) {
        return (float) s;
    }

    @Nonnull
    static Integer int16ToInt32(@Nonnull Short s) {
        return (int) s;
    }

    @Nonnull
    static Long int16ToInt64(@Nonnull Short s) {
        return (long) s;
    }

    @Nullable
    static Byte int16ToSByte(@Nonnull Short s) {
        if (s >= Byte.MIN_VALUE && s <= Byte.MAX_VALUE) {
            return s.byteValue();
        } else {
            return null;
        }
    }

    @Nonnull
    static String int16ToString(@Nonnull Short s) {
        return s.toString();
    }

    @Nullable
    static UShort int16ToUInt16(@Nonnull Short s) {
        if (s >= 0) {
            return ushort(s);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger int16ToUInt32(@Nonnull Short s) {
        if (s >= 0) {
            return uint(s);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong int16ToUInt64(@Nonnull Short s) {
        if (s >= 0) {
            return ulong(s);
        } else {
            return null;
        }
    }

}
