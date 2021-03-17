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

final class BooleanConversions {

    private BooleanConversions() {}

    @NotNull
    static UByte booleanToByte(@NotNull Boolean b) {
        return ubyte(b ? 1 : 0);
    }

    @NotNull
    static Double booleanToDouble(@NotNull Boolean b) {
        return b ? 1.0 : 0.0;
    }

    @NotNull
    static Float booleanToFloat(@NotNull Boolean b) {
        return b ? 1.0f : 0.0f;
    }

    @NotNull
    static Short booleanToInt16(@NotNull Boolean b) {
        return b ? (short) 1 : (short) 0;
    }

    @NotNull
    static Integer booleanToInt32(@NotNull Boolean b) {
        return b ? 1 : 0;
    }

    @NotNull
    static Long booleanToInt64(@NotNull Boolean b) {
        return b ? 1L : 0L;
    }

    @NotNull
    static Byte booleanToSByte(@NotNull Boolean b) {
        return b ? (byte) 1 : (byte) 0;
    }

    @NotNull
    static String booleanToString(@NotNull Boolean b) {
        return b ? "1" : "0";
    }

    @NotNull
    static UShort booleanToUInt16(@NotNull Boolean b) {
        return b ? ushort(1) : ushort(0);
    }

    @NotNull
    static UInteger booleanToUInt32(@NotNull Boolean b) {
        return b ? uint(1) : uint(0);
    }

    @NotNull
    static ULong booleanToUInt64(@NotNull Boolean b) {
        return b ? ulong(1) : ulong(0);
    }

    @Nullable
    static Object convert(@NotNull Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof Boolean) {
            Boolean b = (Boolean) o;

            return implicit ?
                implicitConversion(b, targetType) :
                explicitConversion(b, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@NotNull Boolean b, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case String:    return booleanToString(b);
            default:        return implicitConversion(b, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@NotNull Boolean b, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Byte:      return booleanToByte(b);
            case Double:    return booleanToDouble(b);
            case Float:     return booleanToFloat(b);
            case Int16:     return booleanToInt16(b);
            case Int32:     return booleanToInt32(b);
            case Int64:     return booleanToInt64(b);
            case SByte:     return booleanToSByte(b);
            case UInt16:    return booleanToUInt16(b);
            case UInt32:    return booleanToUInt32(b);
            case UInt64:    return booleanToUInt64(b);
            default:        return null;
        }
        //@formatter:on
    }

}
