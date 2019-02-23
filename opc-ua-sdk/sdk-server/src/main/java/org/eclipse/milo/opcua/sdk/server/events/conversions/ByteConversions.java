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

    @Nullable
    static Object convert(@Nonnull Object o, BuiltinDataType targetType, boolean implicit) {
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
    static Object explicitConversion(@Nonnull UByte b, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return byteToBoolean(b);
            case String:    return byteToString(b);
            default:        return implicitConversion(b, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@Nonnull UByte b, BuiltinDataType targetType) {
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
