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

    static Boolean doubleToBoolean(Double d) {
        return d != 0.0d;
    }

    static UByte doubleToByte(Double d) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(d);

        if (rounded < 0) {
            throw new ConversionUnderflowException(rounded, 0);
        } else if (rounded > UByte.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, UByte.MAX_VALUE);
        } else {
            return ubyte(rounded);
        }
    }

    static Float doubleToFloat(Double d) throws ConversionUnderflowException, ConversionOverflowException {
        if (d < -Float.MAX_VALUE) {
            throw new ConversionUnderflowException(d, -Float.MAX_VALUE);
        } else if (d > Float.MAX_VALUE) {
            throw new ConversionOverflowException(d, Float.MAX_VALUE);
        } else {
            return d.floatValue();
        }
    }

    static Short doubleToInt16(Double d) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(d);

        if (rounded < Short.MIN_VALUE) {
            throw new ConversionUnderflowException(rounded, Short.MIN_VALUE);
        } else if (rounded > Short.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, Short.MAX_VALUE);
        } else {
            return (short) rounded;
        }
    }

    static Integer doubleToInt32(Double d) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(d);

        if (rounded < Integer.MIN_VALUE) {
            throw new ConversionUnderflowException(rounded, Integer.MIN_VALUE);
        } else if (rounded > Integer.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, Integer.MAX_VALUE);
        } else {
            return (int) rounded;
        }
    }

    static Long doubleToInt64(Double d) throws ConversionUnderflowException, ConversionOverflowException {
        if (d < Long.MIN_VALUE) {
            throw new ConversionUnderflowException(d, Long.MIN_VALUE);
        } else if (d > Long.MAX_VALUE) {
            throw new ConversionOverflowException(d, Long.MAX_VALUE);
        } else {
            return Math.round(d);
        }
    }

    static Byte doubleToSByte(Double d) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(d);

        if (rounded < Byte.MIN_VALUE) {
            throw new ConversionUnderflowException(rounded, Byte.MIN_VALUE);
        } else if (rounded > Byte.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, Byte.MAX_VALUE);
        } else {
            return (byte) rounded;
        }
    }

    static String doubleToString(Double d) {
        return d.toString();
    }

    static UShort doubleToUInt16(Double d) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(d);

        if (rounded < UShort.MIN_VALUE) {
            throw new ConversionUnderflowException(rounded, UShort.MIN_VALUE);
        } else if (rounded > UShort.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, UShort.MAX_VALUE);
        } else {
            return ushort((int) rounded);
        }
    }

    static UInteger doubleToUInt32(Double d) throws ConversionUnderflowException, ConversionOverflowException {
        long rounded = Math.round(d);

        if (rounded < UInteger.MIN_VALUE) {
            throw new ConversionUnderflowException(rounded, UInteger.MIN_VALUE);
        } else if (rounded > UInteger.MAX_VALUE) {
            throw new ConversionOverflowException(rounded, UInteger.MAX_VALUE);
        } else {
            return uint(rounded);
        }
    }

    static ULong doubleToUInt64(Double d) throws ConversionUnderflowException {
        long rounded = Math.round(d);

        if (rounded >= 0) {
            return ulong(rounded);
        } else {
            throw new ConversionUnderflowException(rounded, 0);
        }
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionUnderflowException, ConversionNotDefinedException, ConversionOverflowException {

        if (value instanceof Double) {
            Double d = (Double) value;

            return implicit ?
                implicitConversion(d, targetType) :
                explicitConversion(d, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(
        Double d,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException, ConversionUnderflowException, ConversionOverflowException {
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
    static Object implicitConversion(Double d, BuiltinDataType targetType) throws ConversionNotDefinedException {
        // no implicit conversions exist
        throw new ConversionNotDefinedException(BuiltinDataType.Double, targetType);
    }

}
