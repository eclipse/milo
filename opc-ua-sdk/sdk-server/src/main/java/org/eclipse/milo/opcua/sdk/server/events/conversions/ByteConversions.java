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

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class ByteConversions {

    private ByteConversions() {}

    static Boolean byteToBoolean(@Nonnull UByte ub) {
        return ub.intValue() != 0;
    }

    static Double byteToDouble(@Nonnull UByte ub) {
        return ub.doubleValue();
    }

    static Float byteToFloat(@Nonnull UByte ub) {
        return ub.floatValue();
    }

    static Short byteToInt16(@Nonnull UByte ub) {
        return ub.shortValue();
    }

    static Integer byteToInt32(@Nonnull UByte ub) {
        return ub.intValue();
    }

    static Long byteToInt64(@Nonnull UByte ub) {
        return ub.longValue();
    }

    static Byte byteToSByte(@Nonnull UByte ub) {
        return ub.intValue() > Byte.MAX_VALUE ? null : ub.byteValue();
    }

    static String byteToString(@Nonnull UByte ub) {
        return ub.toString();
    }

    static UShort byteToUInt16(@Nonnull UByte ub) {
        return ushort(ub.intValue());
    }

    static UInteger byteToUInt32(@Nonnull UByte ub) {
        return uint(ub.intValue());
    }

    static ULong byteToUInt64(@Nonnull UByte ub) {
        return ulong(ub.longValue());
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException {

        if (value instanceof UByte) {
            UByte b = (UByte) value;

            return implicit ?
                implicitConversion(b, targetType) :
                explicitConversion(b, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(UByte b, BuiltinDataType targetType) throws ConversionNotDefinedException {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return byteToBoolean(b);
            case String:    return byteToString(b);
            default:        return implicitConversion(b, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(UByte b, BuiltinDataType targetType) throws ConversionNotDefinedException {
        //@formatter:off
        switch (targetType) {
            case Double:    return byteToDouble(b);
            case Float:     return byteToFloat(b);
            case Int16:     return byteToInt16(b);
            case Int32:     return byteToInt32(b);
            case Int64:     return byteToInt64(b);
            case SByte:     return byteToSByte(b);
            case UInt16:    return byteToUInt16(b);
            case UInt32:    return byteToUInt32(b);
            case UInt64:    return byteToUInt64(b);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.Byte, targetType);
        }
        //@formatter:on
    }

}
