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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
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

final class Int32Conversions {

    private Int32Conversions() {}

    @NotNull
    static Boolean int32ToBoolean(@NotNull Integer i) {
        return i != 0;
    }

    @Nullable
    static UByte int32ToByte(@NotNull Integer i) {
        if (i >= 0 && i <= UByte.MAX_VALUE) {
            return ubyte(i);
        } else {
            return null;
        }
    }

    @NotNull
    static Double int32ToDouble(@NotNull Integer i) {
        return i.doubleValue();
    }

    @NotNull
    static Float int32ToFloat(@NotNull Integer i) {
        return i.floatValue();
    }

    @Nullable
    static Short int32ToInt16(@NotNull Integer i) {
        if (i >= Short.MIN_VALUE && i <= Short.MAX_VALUE) {
            return i.shortValue();
        } else {
            return null;
        }
    }

    @NotNull
    static Long int32ToInt64(@NotNull Integer i) {
        return i.longValue();
    }

    @Nullable
    static Byte int32ToSByte(@NotNull Integer i) {
        if (i >= Byte.MIN_VALUE && i <= Byte.MAX_VALUE) {
            return i.byteValue();
        } else {
            return null;
        }
    }

    @NotNull
    static StatusCode int32ToStatusCode(@NotNull Integer i) {
        return new StatusCode(i);
    }

    @NotNull
    static String int32ToString(@NotNull Integer i) {
        return i.toString();
    }

    @Nullable
    static UShort int32ToUInt16(@NotNull Integer i) {
        if (i >= UShort.MIN_VALUE && i <= UShort.MAX_VALUE) {
            return ushort(i);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger int32ToUInt32(@NotNull Integer i) {
        if (i >= 0) {
            return uint(i);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong int32ToUInt64(@NotNull Integer i) {
        if (i >= 0) {
            return ulong(i);
        } else {
            return null;
        }
    }

    @Nullable
    static Object convert(@Nullable Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof Integer) {
            Integer i = (Integer) o;

            return implicit ?
                implicitConversion(i, targetType) :
                explicitConversion(i, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@NotNull Integer i, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:       return int32ToBoolean(i);
            case Byte:          return int32ToByte(i);
            case Int16:         return int32ToInt16(i);
            case SByte:         return int32ToSByte(i);
            case StatusCode:    return int32ToStatusCode(i);
            case String:        return int32ToString(i);
            case UInt16:        return int32ToUInt16(i);
            case UInt32:        return int32ToUInt32(i);
            default:            return implicitConversion(i, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@NotNull Integer i, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Double:    return int32ToDouble(i);
            case Float:     return int32ToFloat(i);
            case Int64:     return int32ToInt64(i);
            case UInt64:    return int32ToUInt64(i);
            default:        return null;
        }
        //@formatter:on
    }

}
