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

    static Boolean floatToBoolean(Float f) {
        return f != 0.0f;
    }

    static UByte floatToByte(Float f) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(f);

        if (rounded < 0) {
            throw new ConversionUnderflowException(rounded, 0);
        } else if (rounded > UByte.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, UByte.MAX_VALUE);
        } else {
            return ubyte(rounded);
        }
    }

    static Double floatToDouble(@Nonnull Float f) {
        return f.doubleValue();
    }

    static Short floatToInt16(@Nonnull Float f) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(f);

        if (rounded < Short.MIN_VALUE) {
            throw new ConversionUnderflowException(rounded, 0);
        } else if (rounded > Short.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, Short.MAX_VALUE);
        } else {
            return (short) rounded;
        }
    }

    static Integer floatToInt32(@Nonnull Float f) {
        return Math.round(f);
    }

    static Long floatToInt64(@Nonnull Float f) {
        return (long) Math.round(f);
    }

    static Byte floatToSByte(@Nonnull Float f) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(f);

        if (rounded < Byte.MIN_VALUE) {
            throw new ConversionUnderflowException(rounded, Byte.MIN_VALUE);
        } else if (rounded > Byte.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, Byte.MAX_VALUE);
        } else {
            return (byte) rounded;
        }
    }

    static String floatToString(Float f) {
        return f.toString();
    }

    static UShort floatToUInt16(Float f) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(f);

        if (rounded < UShort.MIN_VALUE) {
            throw new ConversionUnderflowException(rounded, UShort.MIN_VALUE);
        } else if (rounded > UShort.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, UShort.MAX_VALUE);
        } else {
            return ushort((int) rounded);
        }
    }

    static UInteger floatToUInt32(Float f) throws ConversionUnderflowException {
        int rounded = Math.round(f);

        if (rounded >= 0) {
            return uint(rounded);
        } else {
            throw new ConversionUnderflowException(rounded, 0);
        }
    }

    static ULong floatToUInt64(Float f) throws ConversionUnderflowException {
        long rounded = Math.round(f);

        if (rounded >= 0) {
            return ulong(rounded);
        } else {
            throw new ConversionUnderflowException(rounded, 0);
        }
    }

    static Object convert(Object value, BuiltinDataType targetType, boolean implicit) throws ConversionException {
        if (value instanceof Float) {
            Float f = (Float) value;

            return implicit ?
                implicitConversion(f, targetType) :
                explicitConversion(f, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(@Nonnull Float f, BuiltinDataType targetType) throws ConversionException {
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

    static Object implicitConversion(Float f, BuiltinDataType targetType) throws ConversionNotDefinedException {
        //@formatter:off
        switch (targetType) {
            case Double:    return floatToDouble(f);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.Float, targetType);
        }
        //@formatter:on
    }

}
