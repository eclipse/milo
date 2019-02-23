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

final class SByteConversions {

    private SByteConversions() {}

    @Nonnull
    static Boolean sByteToBoolean(@Nonnull Byte b) {
        return b != 0;
    }

    @Nullable
    static UByte sByteToByte(@Nonnull Byte b) {
        if (b >= 0) {
            return ubyte(b);
        } else {
            return null;
        }
    }

    @Nonnull
    static Double sByteToDouble(@Nonnull Byte b) {
        return b.doubleValue();
    }

    @Nonnull
    static Float sByteToFloat(@Nonnull Byte b) {
        return b.floatValue();
    }

    @Nonnull
    static Short sByteToInt16(@Nonnull Byte b) {
        return b.shortValue();
    }

    @Nonnull
    static Integer sByteToInt32(@Nonnull Byte b) {
        return b.intValue();
    }

    @Nonnull
    static Long sByteToInt64(@Nonnull Byte b) {
        return b.longValue();
    }

    @Nonnull
    static String sByteToString(@Nonnull Byte b) {
        return b.toString();
    }

    @Nullable
    static UShort sByteToUInt16(@Nonnull Byte b) {
        if (b >= 0) {
            return ushort(b);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger sByteToUInt32(@Nonnull Byte b) {
        if (b >= 0) {
            return uint(b);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong sByteToUInt64(@Nonnull Byte b) {
        if (b >= 0) {
            return ulong(b);
        } else {
            return null;
        }
    }

    @Nullable
    static Object convert(@Nonnull Object o, BuiltinDataType targetType, boolean implicit) {
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
    static Object explicitConversion(@Nonnull Byte b, BuiltinDataType targetType) {
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
    static Object implicitConversion(@Nonnull Byte b, BuiltinDataType targetType) {
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
