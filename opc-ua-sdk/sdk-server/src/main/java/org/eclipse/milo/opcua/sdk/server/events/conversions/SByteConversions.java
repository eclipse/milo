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

final class SByteConversions {

    private SByteConversions() {}

    @NotNull
    static Boolean sByteToBoolean(@NotNull Byte b) {
        return b != 0;
    }

    @Nullable
    static UByte sByteToByte(@NotNull Byte b) {
        if (b >= 0) {
            return ubyte(b);
        } else {
            return null;
        }
    }

    @NotNull
    static Double sByteToDouble(@NotNull Byte b) {
        return b.doubleValue();
    }

    @NotNull
    static Float sByteToFloat(@NotNull Byte b) {
        return b.floatValue();
    }

    @NotNull
    static Short sByteToInt16(@NotNull Byte b) {
        return b.shortValue();
    }

    @NotNull
    static Integer sByteToInt32(@NotNull Byte b) {
        return b.intValue();
    }

    @NotNull
    static Long sByteToInt64(@NotNull Byte b) {
        return b.longValue();
    }

    @NotNull
    static String sByteToString(@NotNull Byte b) {
        return b.toString();
    }

    @Nullable
    static UShort sByteToUInt16(@NotNull Byte b) {
        if (b >= 0) {
            return ushort(b);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger sByteToUInt32(@NotNull Byte b) {
        if (b >= 0) {
            return uint(b);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong sByteToUInt64(@NotNull Byte b) {
        if (b >= 0) {
            return ulong(b);
        } else {
            return null;
        }
    }

    @Nullable
    static Object convert(@NotNull Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof Byte) {
            Byte b = (Byte) o;

            return implicit ?
                implicitConversion(b, targetType) :
                explicitConversion(b, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@NotNull Byte b, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return sByteToBoolean(b);
            case Byte:      return sByteToByte(b);
            case String:    return sByteToString(b);
            default:        return implicitConversion(b, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@NotNull Byte b, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Double:    return sByteToDouble(b);
            case Float:     return sByteToFloat(b);
            case Int16:     return sByteToInt16(b);
            case Int32:     return sByteToInt32(b);
            case Int64:     return sByteToInt64(b);
            case UInt16:    return sByteToUInt16(b);
            case UInt32:    return sByteToUInt32(b);
            case UInt64:    return sByteToUInt64(b);
            default:        return null;
        }
        //@formatter:on
    }

}
