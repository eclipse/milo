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

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class Int32Conversions {

    private Int32Conversions() {}

    static Boolean int32ToBoolean(Integer i) {
        return i != 0;
    }

    static UByte int32ToByte(Integer i) throws ConversionUnderflowException, ConversionOverflowException {
        if (i < 0) {
            throw new ConversionUnderflowException(i, 0);
        } else if (i > UByte.MAX_VALUE) {
            throw new ConversionOverflowException(i, UByte.MAX_VALUE);
        } else {
            return ubyte(i);
        }
    }

    static Double int32ToDouble(Integer i) {
        return i.doubleValue();
    }

    static Float int32ToFloat(Integer i) {
        return i.floatValue();
    }

    static Short int32ToInt16(Integer i) throws ConversionUnderflowException, ConversionOverflowException {
        if (i < Short.MIN_VALUE) {
            throw new ConversionUnderflowException(i, 0);
        } else if (i > Short.MAX_VALUE) {
            throw new ConversionOverflowException(i, Short.MAX_VALUE);
        } else {
            return i.shortValue();
        }
    }

    static Long int32ToInt64(Integer i) {
        return i.longValue();
    }

    static Byte int32ToSByte(Integer i) throws ConversionUnderflowException, ConversionOverflowException {
        if (i < Byte.MIN_VALUE) {
            throw new ConversionUnderflowException(i, Byte.MIN_VALUE);
        } else if (i > Byte.MAX_VALUE) {
            throw new ConversionOverflowException(i, Byte.MAX_VALUE);
        } else {
            return i.byteValue();
        }
    }

    static StatusCode int32ToStatusCode(Integer i) {
        return new StatusCode(i);
    }

    static String int32ToString(Integer i) {
        return i.toString();
    }

    static UShort int32ToUInt16(Integer i) throws ConversionUnderflowException, ConversionOverflowException {
        if (i < UShort.MIN_VALUE) {
            throw new ConversionUnderflowException(i, UShort.MIN_VALUE);
        } else if (i > UShort.MAX_VALUE) {
            throw new ConversionOverflowException(i, UShort.MAX_VALUE);
        } else {
            return ushort(i);
        }
    }

    static UInteger int32ToUInt32(Integer i) throws ConversionUnderflowException {
        if (i >= 0) {
            return uint(i);
        } else {
            throw new ConversionUnderflowException(i, 0);
        }
    }

    static ULong int32ToUInt64(Integer i) throws ConversionUnderflowException {
        if (i >= 0) {
            return ulong(i);
        } else {
            throw new ConversionUnderflowException(i, 0);
        }
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionException {

        if (value instanceof Integer) {
            Integer i = (Integer) value;

            return implicit ?
                implicitConversion(i, targetType) :
                explicitConversion(i, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(Integer i, BuiltinDataType targetType) throws ConversionException {
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

    static Object implicitConversion(Integer i, BuiltinDataType targetType) throws ConversionException {
        //@formatter:off
        switch (targetType) {
            case Double:    return int32ToDouble(i);
            case Float:     return int32ToFloat(i);
            case Int64:     return int32ToInt64(i);
            case UInt64:    return int32ToUInt64(i);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.Int32, targetType);
        }
        //@formatter:on
    }

}
