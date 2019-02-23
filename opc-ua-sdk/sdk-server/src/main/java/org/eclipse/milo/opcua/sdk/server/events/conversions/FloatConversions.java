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

final class FloatConversions {

    private FloatConversions() {}

    @Nonnull
    static Boolean floatToBoolean(@Nonnull Float f) {
        return f != 0.0f;
    }

    @Nullable
    static UByte floatToByte(@Nonnull Float f) {
        long rounded = Math.round(f);

        if (rounded >= 0 && rounded <= UByte.MAX_VALUE) {
            return ubyte(rounded);
        } else {
            return null;
        }
    }

    @Nonnull
    static Double floatToDouble(@Nonnull Float f) {
        return f.doubleValue();
    }

    @Nullable
    static Short floatToInt16(@Nonnull Float f) {
        long rounded = Math.round(f);

        if (rounded >= Short.MIN_VALUE && rounded <= Short.MAX_VALUE) {
            return (short) rounded;
        } else {
            return null;
        }
    }

    @Nonnull
    static Integer floatToInt32(@Nonnull Float f) {
        return Math.round(f);
    }

    @Nonnull
    static Long floatToInt64(@Nonnull Float f) {
        return (long) Math.round(f);
    }

    @Nullable
    static Byte floatToSByte(@Nonnull Float f) {
        long rounded = Math.round(f);

        if (rounded >= Byte.MIN_VALUE && rounded <= Byte.MAX_VALUE) {
            return (byte) rounded;
        } else {
            return null;
        }
    }

    @Nonnull
    static String floatToString(@Nonnull Float f) {
        return f.toString();
    }

    @Nullable
    static UShort floatToUInt16(@Nonnull Float f) {
        long rounded = Math.round(f);

        if (rounded >= UShort.MIN_VALUE && rounded <= UShort.MAX_VALUE) {
            return ushort((int) rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger floatToUInt32(@Nonnull Float f) {
        int rounded = Math.round(f);

        if (rounded >= 0) {
            return uint(rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong floatToUInt64(@Nonnull Float f) {
        long rounded = Math.round(f);

        if (rounded >= 0) {
            return ulong(rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static Object convert(@Nullable Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof Float) {
            Float f = (Float) o;

            return implicit ?
                implicitConversion(f, targetType) :
                explicitConversion(f, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@Nonnull Float f, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return floatToBoolean(f);
            case Byte:      return floatToByte(f);
            case Int16:     return floatToInt16(f);
            case Int32:     return floatToInt32(f);
            case Int64:     return floatToInt64(f);
            case SByte:     return floatToSByte(f);
            case String:    return floatToString(f);
            case UInt16:    return floatToUInt16(f);
            case UInt32:    return floatToUInt32(f);
            case UInt64:    return floatToUInt64(f);
            default:        return implicitConversion(f, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@Nonnull Float f, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Double:    return floatToDouble(f);
            default:        return null;
        }
        //@formatter:on
    }

}
