/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class Int16Conversions {

    private Int16Conversions() {}

    @NotNull
    static Boolean int16ToBoolean(@NotNull Short s) {
        return s != 0;
    }

    @Nullable
    static UByte int16ToByte(@NotNull Short s) {
        if (s >= 0 && s <= UByte.MAX_VALUE) {
            return ubyte(s);
        } else {
            return null;
        }
    }

    @NotNull
    static Double int16ToDouble(@NotNull Short s) {
        return (double) s;
    }

    @NotNull
    static Float int16ToFloat(@NotNull Short s) {
        return (float) s;
    }

    @NotNull
    static Integer int16ToInt32(@NotNull Short s) {
        return (int) s;
    }

    @NotNull
    static Long int16ToInt64(@NotNull Short s) {
        return (long) s;
    }

    @Nullable
    static Byte int16ToSByte(@NotNull Short s) {
        if (s >= Byte.MIN_VALUE && s <= Byte.MAX_VALUE) {
            return s.byteValue();
        } else {
            return null;
        }
    }

    @NotNull
    static String int16ToString(@NotNull Short s) {
        return s.toString();
    }

    @Nullable
    static UShort int16ToUInt16(@NotNull Short s) {
        if (s >= 0) {
            return ushort(s);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger int16ToUInt32(@NotNull Short s) {
        if (s >= 0) {
            return uint(s);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong int16ToUInt64(@NotNull Short s) {
        if (s >= 0) {
            return ulong(s);
        } else {
            return null;
        }
    }

    @Nullable
    static Object convert(@Nullable Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof Short) {
            Short s = (Short) o;

            return implicit ?
                implicitConversion(s, targetType) :
                explicitConversion(s, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@NotNull Short s, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return int16ToBoolean(s);
            case Byte:      return int16ToByte(s);
            case SByte:     return int16ToSByte(s);
            case String:    return int16ToString(s);
            case UInt16:    return int16ToUInt16(s);
            default:        return implicitConversion(s, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@NotNull Short s, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Double:    return int16ToDouble(s);
            case Float:     return int16ToFloat(s);
            case Int32:     return int16ToInt32(s);
            case Int64:     return int16ToInt64(s);
            case UInt32:    return int16ToUInt32(s);
            case UInt64:    return int16ToUInt64(s);
            default:        return null;
        }
        //@formatter:on
    }

}
