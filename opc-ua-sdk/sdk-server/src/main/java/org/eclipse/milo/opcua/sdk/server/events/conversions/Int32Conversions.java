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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class Int32Conversions {

    private Int32Conversions() {}

    @Nonnull
    static Boolean int32ToBoolean(@Nonnull Integer i) {
        return i != 0;
    }

    @Nullable
    static UByte int32ToByte(@Nonnull Integer i) {
        if (i >= 0 && i <= UByte.MAX_VALUE) {
            return ubyte(i);
        } else {
            return null;
        }
    }

    @Nonnull
    static Double int32ToDouble(@Nonnull Integer i) {
        return i.doubleValue();
    }

    @Nonnull
    static Float int32ToFloat(@Nonnull Integer i) {
        return i.floatValue();
    }

    @Nullable
    static Short int32ToInt16(@Nonnull Integer i) {
        if (i >= Short.MIN_VALUE && i <= Short.MAX_VALUE) {
            return i.shortValue();
        } else {
            return null;
        }
    }

    @Nonnull
    static Long int32ToInt64(@Nonnull Integer i) {
        return i.longValue();
    }

    @Nullable
    static Byte int32ToSByte(@Nonnull Integer i) {
        if (i >= Byte.MIN_VALUE && i <= Byte.MAX_VALUE) {
            return i.byteValue();
        } else {
            return null;
        }
    }

    @Nonnull
    static StatusCode int32ToStatusCode(@Nonnull Integer i) {
        return new StatusCode(i);
    }

    @Nonnull
    static String int32ToString(@Nonnull Integer i) {
        return i.toString();
    }

    @Nullable
    static UShort int32ToUInt16(@Nonnull Integer i) {
        if (i >= UShort.MIN_VALUE && i <= UShort.MAX_VALUE) {
            return ushort(i);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger int32ToUInt32(@Nonnull Integer i) {
        if (i >= 0) {
            return uint(i);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong int32ToUInt64(@Nonnull Integer i) {
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
    static Object explicitConversion(@Nonnull Integer i, BuiltinDataType targetType) {
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
    static Object implicitConversion(@Nonnull Integer i, BuiltinDataType targetType) {
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
