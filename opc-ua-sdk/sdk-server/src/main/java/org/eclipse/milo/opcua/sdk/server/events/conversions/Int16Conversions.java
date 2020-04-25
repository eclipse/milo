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

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class Int16Conversions {

    private Int16Conversions() {}

    static Boolean int16ToBoolean(Short s) {
        return s != 0;
    }

    static UByte int16ToByte(Short s) throws ConversionUnderflowException, ConversionOverflowException {
        if (s < 0) {
            throw new ConversionUnderflowException(s, 0);
        } else if (s > UByte.MAX_VALUE) {
            throw new ConversionOverflowException(s, UByte.MAX_VALUE);
        } else {
            return ubyte(s);
        }
    }

    static Double int16ToDouble(Short s) {
        return (double) s;
    }

    static Float int16ToFloat(Short s) {
        return (float) s;
    }

    static Integer int16ToInt32(Short s) {
        return (int) s;
    }

    static Long int16ToInt64(Short s) {
        return (long) s;
    }

    static Byte int16ToSByte(Short s) throws ConversionUnderflowException, ConversionOverflowException {
        if (s < Byte.MIN_VALUE) {
            throw new ConversionUnderflowException(s, Byte.MIN_VALUE);
        } else if (s > Byte.MAX_VALUE) {
            throw new ConversionOverflowException(s, Byte.MAX_VALUE);
        } else {
            return s.byteValue();
        }
    }

    static String int16ToString(Short s) {
        return s.toString();
    }

    static UShort int16ToUInt16(Short s) throws ConversionUnderflowException {
        if (s >= 0) {
            return ushort(s);
        } else {
            throw new ConversionUnderflowException(s, 0);
        }
    }

    static UInteger int16ToUInt32(Short s) throws ConversionUnderflowException {
        if (s >= 0) {
            return uint(s);
        } else {
            throw new ConversionUnderflowException(s, 0);
        }
    }

    static ULong int16ToUInt64(Short s) throws ConversionUnderflowException {
        if (s >= 0) {
            return ulong(s);
        } else {
            throw new ConversionUnderflowException(s, 0);
        }
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionUnderflowException, ConversionOverflowException, ConversionNotDefinedException {

        if (value instanceof Short) {
            Short s = (Short) value;

            return implicit ?
                implicitConversion(s, targetType) :
                explicitConversion(s, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(
        Short s,
        BuiltinDataType targetType
    ) throws ConversionUnderflowException, ConversionOverflowException, ConversionNotDefinedException {

        //@formatter:off
        switch (targetType) {
            case Boolean:   return int16ToBoolean(s);
            case Byte:      return int16ToByte(s);
            case SByte:     return int16ToSByte(s);
            case String:    return int16ToString(s);
            case UInt16:    return int16ToUInt16(s);
            default:        return implicitConversion(s, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(
        Short s,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException, ConversionUnderflowException {

        //@formatter:off
        switch (targetType) {
            case Double:    return int16ToDouble(s);
            case Float:     return int16ToFloat(s);
            case Int32:     return int16ToInt32(s);
            case Int64:     return int16ToInt64(s);
            case UInt32:    return int16ToUInt32(s);
            case UInt64:    return int16ToUInt64(s);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.Int16, targetType);
        }
        //@formatter:on
    }

}
