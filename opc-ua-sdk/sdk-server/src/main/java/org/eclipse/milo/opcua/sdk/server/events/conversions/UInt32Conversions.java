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
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class UInt32Conversions {

    private UInt32Conversions() {}

    @NotNull
    static Boolean uInt32ToBoolean(@NotNull UInteger ui) {
        return ui.intValue() != 0;
    }

    @Nullable
    static UByte uInt32ToByte(@NotNull UInteger ui) {
        long l = ui.longValue();

        if (l <= UByte.MAX_VALUE) {
            return ubyte(l);
        } else {
            return null;
        }
    }

    @NotNull
    static Double uInt32ToDouble(@NotNull UInteger ui) {
        return ui.doubleValue();
    }

    @NotNull
    static Float uInt32ToFloat(@NotNull UInteger ui) {
        return ui.floatValue();
    }

    @Nullable
    static Short uInt32ToInt16(@NotNull UInteger ui) {
        int i = ui.intValue();

        if (i <= Short.MAX_VALUE) {
            return (short) i;
        } else {
            return null;
        }
    }

    @Nullable
    static Integer uInt32ToInt32(@NotNull UInteger ui) {
        long l = ui.longValue();

        if (l <= Integer.MAX_VALUE) {
            return (int) l;
        } else {
            return null;
        }
    }

    @NotNull
    static Long uInt32ToInt64(@NotNull UInteger ui) {
        return ui.longValue();
    }

    @Nullable
    static Byte uInt32ToSByte(@NotNull UInteger ui) {
        int i = ui.intValue();

        if (i <= Byte.MAX_VALUE) {
            return (byte) i;
        } else {
            return null;
        }
    }

    @NotNull
    static StatusCode uInt32ToStatusCode(@NotNull UInteger ui) {
        return new StatusCode(ui);
    }

    @NotNull
    static String uInt32ToString(@NotNull UInteger ui) {
        return ui.toString();
    }

    @Nullable
    static UShort uInt32ToUInt16(@NotNull UInteger ui) {
        int i = ui.intValue();

        if (i <= UShort.MAX_VALUE) {
            return ushort(i);
        } else {
            return null;
        }
    }

    @NotNull
    static ULong uInt32ToUInt64(@NotNull UInteger ui) {
        return ulong(ui.longValue());
    }

    @Nullable
    static Object convert(@Nullable Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof UInteger) {
            UInteger ui = (UInteger) o;

            return implicit ?
                implicitConversion(ui, targetType) :
                explicitConversion(ui, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@NotNull UInteger ui, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:       return uInt32ToBoolean(ui);
            case Byte:          return uInt32ToByte(ui);
            case Int16:         return uInt32ToInt16(ui);
            case SByte:         return uInt32ToSByte(ui);
            case StatusCode:    return uInt32ToStatusCode(ui);
            case String:        return uInt32ToString(ui);
            case UInt16:        return uInt32ToUInt16(ui);
            default:            return implicitConversion(ui, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@NotNull UInteger ui, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Double:        return uInt32ToDouble(ui);
            case Float:         return uInt32ToFloat(ui);
            case Int32:         return uInt32ToInt32(ui);
            case Int64:         return uInt32ToInt64(ui);
            case UInt64:        return uInt32ToUInt64(ui);
            default:            return null;
        }
        //@formatter:on
    }

}
