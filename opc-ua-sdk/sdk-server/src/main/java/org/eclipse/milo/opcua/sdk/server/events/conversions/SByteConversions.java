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

final class SByteConversions {

    private SByteConversions() {}

    static Boolean sByteToBoolean(Byte b) {
        return b != 0;
    }

    static UByte sByteToByte(Byte b) throws ConversionUnderflowException {
        if (b >= 0) {
            return ubyte(b);
        } else {
            throw new ConversionUnderflowException(b, 0);
        }
    }

    static Double sByteToDouble(Byte b) {
        return b.doubleValue();
    }

    static Float sByteToFloat(Byte b) {
        return b.floatValue();
    }

    static Short sByteToInt16(Byte b) {
        return b.shortValue();
    }

    static Integer sByteToInt32(Byte b) {
        return b.intValue();
    }

    static Long sByteToInt64(Byte b) {
        return b.longValue();
    }

    static String sByteToString(Byte b) {
        return b.toString();
    }

    static UShort sByteToUInt16(Byte b) throws ConversionUnderflowException {
        if (b >= 0) {
            return ushort(b);
        } else {
            throw new ConversionUnderflowException(b, 0);
        }
    }

    static UInteger sByteToUInt32(Byte b) throws ConversionUnderflowException {
        if (b >= 0) {
            return uint(b);
        } else {
            throw new ConversionUnderflowException(b, 0);
        }
    }

    static ULong sByteToUInt64(Byte b) throws ConversionUnderflowException {
        if (b >= 0) {
            return ulong(b);
        } else {
            throw new ConversionUnderflowException(b, 0);
        }
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionException {

        if (value instanceof Byte) {
            Byte b = (Byte) value;

            return implicit ?
                implicitConversion(b, targetType) :
                explicitConversion(b, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(@Nonnull Byte b, BuiltinDataType targetType) throws ConversionException {
        //@formatter:off
        switch (targetType) {
            case Boolean:   return sByteToBoolean(b);
            case Byte:      return sByteToByte(b);
            case String:    return sByteToString(b);
            default:        return implicitConversion(b, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(@Nonnull Byte b, BuiltinDataType targetType) throws ConversionException {
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
            default:        throw new ConversionNotDefinedException(BuiltinDataType.SByte, targetType);
        }
        //@formatter:on
    }

}
