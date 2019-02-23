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

final class UInt16Conversions {

    private UInt16Conversions() {}

    @Nonnull
    static Boolean uInt16ToBoolean(@Nonnull UShort us) {
        return us.intValue() != 0;
    }

    @Nullable
    static UByte uInt16ToByte(@Nonnull UShort us) {
        int i = us.intValue();

        if (i <= UByte.MAX_VALUE) {
            return ubyte(i);
        } else {
            return null;
        }
    }

    @Nonnull
    static Double uInt16ToDouble(@Nonnull UShort us) {
        return us.doubleValue();
    }

    @Nonnull
    static Float uInt16ToFloat(@Nonnull UShort us) {
        return us.floatValue();
    }

    @Nullable
    static Short uInt16ToInt16(@Nonnull UShort us) {
        int i = us.intValue();

        if (i <= Short.MAX_VALUE) {
            return (short) i;
        } else {
            return null;
        }
    }

    @Nonnull
    static Integer uInt16ToInt32(@Nonnull UShort us) {
        return us.intValue();
    }

    @Nonnull
    static Long uInt16ToInt64(@Nonnull UShort us) {
        return us.longValue();
    }

    @Nullable
    static Byte uInt16ToSByte(@Nonnull UShort us) {
        int i = us.intValue();

        if (i <= Byte.MAX_VALUE) {
            return (byte) i;
        } else {
            return null;
        }
    }

    @Nonnull
    static StatusCode uInt16ToStatusCode(@Nonnull UShort us) {
        return new StatusCode(us.longValue() << 16);
    }

    @Nonnull
    static String uInt16ToString(@Nonnull UShort us) {
        return us.toString();
    }

    @Nonnull
    static UInteger uInt16ToUInt32(@Nonnull UShort us) {
        return uint(us.intValue());
    }

    @Nonnull
    static ULong uInt16ToUInt64(@Nonnull UShort us) {
        return ulong(us.longValue());
    }

    @Nullable
    static Object convert(@Nullable Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof UShort) {
            UShort us = (UShort) o;

            return implicit ?
                implicitConversion(us, targetType) :
                explicitConversion(us, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@Nonnull UShort us, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return uInt16ToBoolean(us);
            case Byte:      return uInt16ToByte(us);
            case SByte:     return uInt16ToSByte(us);
            case String:    return uInt16ToString(us);
            default:        return implicitConversion(us, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@Nonnull UShort us, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Double:        return uInt16ToDouble(us);
            case Float:         return uInt16ToFloat(us);
            case Int16:         return uInt16ToInt16(us);
            case Int32:         return uInt16ToInt32(us);
            case Int64:         return uInt16ToInt64(us);
            case StatusCode:    return uInt16ToStatusCode(us);
            case UInt32:        return uInt16ToUInt32(us);
            case UInt64:        return uInt16ToUInt64(us);
            default:            return null;
        }
        //@formatter:on
    }

}
