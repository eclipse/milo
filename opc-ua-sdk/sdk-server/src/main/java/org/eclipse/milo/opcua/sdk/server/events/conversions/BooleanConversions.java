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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
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
    static String booleanToString(@Nonnull Boolean b) {
        return b ? "1" : "0";
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

    @Nullable
    static Object convert(@Nonnull Object o, BuiltinDataType targetType, boolean implicit) {
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
    static Object explicitConversion(@Nonnull Boolean b, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case String:    return booleanToString(b);
            default:        return implicitConversion(b, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@Nonnull Boolean b, BuiltinDataType targetType) {
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
