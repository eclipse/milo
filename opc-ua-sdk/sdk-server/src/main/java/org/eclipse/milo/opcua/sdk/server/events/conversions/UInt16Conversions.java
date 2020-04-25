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

final class UInt16Conversions {

    private UInt16Conversions() {}

    static Boolean uInt16ToBoolean(UShort us) {
        return us.intValue() != 0;
    }

    static UByte uInt16ToByte(UShort us) throws ConversionOverflowException {
        int i = us.intValue();

        if (i <= UByte.MAX_VALUE) {
            return ubyte(i);
        } else {
            throw new ConversionOverflowException(i, UByte.MAX_VALUE);
        }
    }

    static Double uInt16ToDouble(UShort us) {
        return us.doubleValue();
    }

    static Float uInt16ToFloat(UShort us) {
        return us.floatValue();
    }

    static Short uInt16ToInt16(UShort us) throws ConversionOverflowException {
        int i = us.intValue();

        if (i <= Short.MAX_VALUE) {
            return (short) i;
        } else {
            throw new ConversionOverflowException(i, Short.MAX_VALUE);
        }
    }

    static Integer uInt16ToInt32(UShort us) {
        return us.intValue();
    }

    static Long uInt16ToInt64(UShort us) {
        return us.longValue();
    }

    static Byte uInt16ToSByte(UShort us) throws ConversionOverflowException {
        int i = us.intValue();

        if (i <= Byte.MAX_VALUE) {
            return (byte) i;
        } else {
            throw new ConversionOverflowException(i, Byte.MAX_VALUE);
        }
    }

    static StatusCode uInt16ToStatusCode(UShort us) {
        return new StatusCode(us.longValue() << 16);
    }

    static String uInt16ToString(UShort us) {
        return us.toString();
    }

    static UInteger uInt16ToUInt32(UShort us) {
        return uint(us.intValue());
    }

    static ULong uInt16ToUInt64(UShort us) {
        return ulong(us.longValue());
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException, ConversionOverflowException {

        if (value instanceof UShort) {
            UShort us = (UShort) value;

            return implicit ?
                implicitConversion(us, targetType) :
                explicitConversion(us, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(
        UShort us,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException, ConversionOverflowException {

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

    static Object implicitConversion(
        UShort us,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException, ConversionOverflowException {

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
            default:            throw new ConversionNotDefinedException(BuiltinDataType.UInt16, targetType);
        }
        //@formatter:on
    }

}
