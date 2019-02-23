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

final class DoubleConversions {

    private DoubleConversions() {}

    @Nonnull
    static Boolean doubleToBoolean(@Nonnull Double d) {
        return d != 0.0d;
    }

    @Nullable
    static UByte doubleToByte(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= 0 && rounded <= UByte.MAX_VALUE) {
            return ubyte(rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static Float doubleToFloat(@Nonnull Double d) {
        if (d >= -Float.MAX_VALUE && d <= Float.MAX_VALUE) {
            return d.floatValue();
        } else {
            return null;
        }
    }

    @Nullable
    static Short doubleToInt16(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= Short.MIN_VALUE && rounded <= Short.MAX_VALUE) {
            return (short) rounded;
        } else {
            return null;
        }
    }

    @Nullable
    static Integer doubleToInt32(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= Integer.MIN_VALUE && rounded <= Integer.MAX_VALUE) {
            return (int) rounded;
        } else {
            return null;
        }
    }

    @Nullable
    static Long doubleToInt64(@Nonnull Double d) {
        if (d >= Long.MIN_VALUE && d <= Long.MAX_VALUE) {
            return Math.round(d);
        } else {
            return null;
        }
    }

    @Nullable
    static Byte doubleToSByte(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= Byte.MIN_VALUE && rounded <= Byte.MAX_VALUE) {
            return (byte) rounded;
        } else {
            return null;
        }
    }

    @Nonnull
    static String doubleToString(@Nonnull Double d) {
        return d.toString();
    }

    @Nullable
    static UShort doubleToUInt16(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= UShort.MIN_VALUE && rounded <= UShort.MAX_VALUE) {
            return ushort((int) rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger doubleToUInt32(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= UInteger.MIN_VALUE && rounded <= UInteger.MAX_VALUE) {
            return uint(rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong doubleToUInt64(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= 0) {
            return ulong(rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static Object convert(@Nullable Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof Double) {
            Double d = (Double) o;

            return implicit ?
                implicitConversion(d, targetType) :
                explicitConversion(d, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@Nonnull Double d, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return doubleToBoolean(d);
            case Byte:      return doubleToByte(d);
            case Float:     return doubleToFloat(d);
            case Int16:     return doubleToInt16(d);
            case Int32:     return doubleToInt32(d);
            case Int64:     return doubleToInt64(d);
            case SByte:     return doubleToSByte(d);
            case String:    return doubleToString(d);
            case UInt16:    return doubleToUInt16(d);
            case UInt32:    return doubleToUInt32(d);
            case UInt64:    return doubleToUInt64(d);
            default:        return implicitConversion(d, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@Nonnull Double d, BuiltinDataType targetType) {
        // no implicit conversions exist
        return null;
    }

}
