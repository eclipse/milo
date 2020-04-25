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
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class UInt32Conversions {

    private UInt32Conversions() {}

    static Boolean uInt32ToBoolean(UInteger ui) {
        return ui.intValue() != 0;
    }

    static UByte uInt32ToByte(UInteger ui) throws ConversionOverflowException {
        long l = ui.longValue();

        if (l <= UByte.MAX_VALUE) {
            return ubyte(l);
        } else {
            throw new ConversionOverflowException(l, UByte.MAX_VALUE);
        }
    }

    static Double uInt32ToDouble(UInteger ui) {
        return ui.doubleValue();
    }

    static Float uInt32ToFloat(UInteger ui) {
        return ui.floatValue();
    }

    static Short uInt32ToInt16(UInteger ui) throws ConversionOverflowException {
        int i = ui.intValue();

        if (i <= Short.MAX_VALUE) {
            return (short) i;
        } else {
            throw new ConversionOverflowException(i, Short.MAX_VALUE);
        }
    }

    static Integer uInt32ToInt32(UInteger ui) throws ConversionOverflowException {
        long l = ui.longValue();

        if (l <= Integer.MAX_VALUE) {
            return (int) l;
        } else {
            throw new ConversionOverflowException(ui, Integer.MAX_VALUE);
        }
    }

    static Long uInt32ToInt64(UInteger ui) {
        return ui.longValue();
    }

    static Byte uInt32ToSByte(UInteger ui) throws ConversionOverflowException {
        int i = ui.intValue();

        if (i <= Byte.MAX_VALUE) {
            return (byte) i;
        } else {
            throw new ConversionOverflowException(i, Byte.MAX_VALUE);
        }
    }

    static StatusCode uInt32ToStatusCode(UInteger ui) {
        return new StatusCode(ui);
    }

    static String uInt32ToString(UInteger ui) {
        return ui.toString();
    }

    static UShort uInt32ToUInt16(UInteger ui) throws ConversionOverflowException {
        int i = ui.intValue();

        if (i <= UShort.MAX_VALUE) {
            return ushort(i);
        } else {
            throw new ConversionOverflowException(i, UShort.MAX_VALUE);
        }
    }

    static ULong uInt32ToUInt64(UInteger ui) {
        return ulong(ui.longValue());
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException, ConversionOverflowException {

        if (value instanceof UInteger) {
            UInteger ui = (UInteger) value;

            return implicit ?
                implicitConversion(ui, targetType) :
                explicitConversion(ui, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(
        UInteger ui,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException, ConversionOverflowException {

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

    static Object implicitConversion(
        UInteger ui,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException, ConversionOverflowException {

        //@formatter:off
        switch (targetType) {
            case Double:        return uInt32ToDouble(ui);
            case Float:         return uInt32ToFloat(ui);
            case Int32:         return uInt32ToInt32(ui);
            case Int64:         return uInt32ToInt64(ui);
            case UInt64:        return uInt32ToUInt64(ui);
            default:            throw new ConversionNotDefinedException(BuiltinDataType.UInt32, targetType);
        }
        //@formatter:on
    }

}
