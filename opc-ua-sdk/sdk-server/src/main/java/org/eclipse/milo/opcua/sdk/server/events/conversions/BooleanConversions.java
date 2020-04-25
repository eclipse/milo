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

final class BooleanConversions {

    private BooleanConversions() {}

    static UByte booleanToByte(@Nonnull Boolean b) {
        return ubyte(b ? 1 : 0);
    }

    static Double booleanToDouble(@Nonnull Boolean b) {
        return b ? 1.0 : 0.0;
    }

    static Float booleanToFloat(@Nonnull Boolean b) {
        return b ? 1.0f : 0.0f;
    }

    static Short booleanToInt16(@Nonnull Boolean b) {
        return b ? (short) 1 : (short) 0;
    }

    static Integer booleanToInt32(@Nonnull Boolean b) {
        return b ? 1 : 0;
    }

    static Long booleanToInt64(@Nonnull Boolean b) {
        return b ? 1L : 0L;
    }

    static Byte booleanToSByte(@Nonnull Boolean b) {
        return b ? (byte) 1 : (byte) 0;
    }

    static String booleanToString(@Nonnull Boolean b) {
        return b ? "1" : "0";
    }

    static UShort booleanToUInt16(@Nonnull Boolean b) {
        return b ? ushort(1) : ushort(0);
    }

    static UInteger booleanToUInt32(@Nonnull Boolean b) {
        return b ? uint(1) : uint(0);
    }

    static ULong booleanToUInt64(@Nonnull Boolean b) {
        return b ? ulong(1) : ulong(0);
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException {

        if (value instanceof Boolean) {
            Boolean b = (Boolean) value;

            return implicit ?
                implicitConversion(b, targetType) :
                explicitConversion(b, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(
        Boolean b,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException {

        //@formatter:off
        switch (targetType) {
            case String:    return booleanToString(b);
            default:        return implicitConversion(b, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(
        Boolean b,
        BuiltinDataType targetType
    ) throws ConversionNotDefinedException {

        //@formatter:off
        switch (targetType) {
            case Byte:      return booleanToByte(b);
            case Double:    return booleanToDouble(b);
            case Float:     return booleanToFloat(b);
            case Int16:     return booleanToInt16(b);
            case Int32:     return booleanToInt32(b);
            case Int64:     return booleanToInt64(b);
            case SByte:     return booleanToSByte(b);
            case UInt16:    return booleanToUInt16(b);
            case UInt32:    return booleanToUInt32(b);
            case UInt64:    return booleanToUInt64(b);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.Boolean, targetType);
        }
        //@formatter:on
    }

}
