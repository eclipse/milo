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

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class ByteConversions {

    private ByteConversions() {}

    @NotNull
    static Boolean byteToBoolean(@NotNull UByte ub) {
        return ub.intValue() != 0;
    }

    @NotNull
    static Double byteToDouble(@NotNull UByte ub) {
        return ub.doubleValue();
    }

    @NotNull
    static Float byteToFloat(@NotNull UByte ub) {
        return ub.floatValue();
    }

    @NotNull
    static Short byteToInt16(@NotNull UByte ub) {
        return ub.shortValue();
    }

    @NotNull
    static Integer byteToInt32(@NotNull UByte ub) {
        return ub.intValue();
    }

    @NotNull
    static Long byteToInt64(@NotNull UByte ub) {
        return ub.longValue();
    }

    @Nullable
    static Byte byteToSByte(@NotNull UByte ub) {
        return ub.intValue() > Byte.MAX_VALUE ? null : ub.byteValue();
    }

    @NotNull
    static String byteToString(@NotNull UByte ub) {
        return ub.toString();
    }

    @NotNull
    static UShort byteToUInt16(@NotNull UByte ub) {
        return ushort(ub.intValue());
    }

    @NotNull
    static UInteger byteToUInt32(@NotNull UByte ub) {
        return uint(ub.intValue());
    }

    @NotNull
    static ULong byteToUInt64(@NotNull UByte ub) {
        return ulong(ub.longValue());
    }

    @Nullable
    static Object convert(@NotNull Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof UByte) {
            UByte b = (UByte) o;

            return implicit ?
                implicitConversion(b, targetType) :
                explicitConversion(b, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@NotNull UByte b, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return byteToBoolean(b);
            case String:    return byteToString(b);
            default:        return implicitConversion(b, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@NotNull UByte b, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Double:    return byteToDouble(b);
            case Float:     return byteToFloat(b);
            case Int16:     return byteToInt16(b);
            case Int32:     return byteToInt32(b);
            case Int64:     return byteToInt64(b);
            case SByte:     return byteToSByte(b);
            case UInt16:    return byteToUInt16(b);
            case UInt32:    return byteToUInt32(b);
            case UInt64:    return byteToUInt64(b);
            default:        return null;
        }
        //@formatter:on
    }
    
}
