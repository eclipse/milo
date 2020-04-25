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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class Int64Conversions {

    private Int64Conversions() {}

    static Boolean int64ToBoolean(@Nonnull Long l) {
        return l != 0;
    }

    static UByte int64ToByte(@Nonnull Long l) throws ConversionUnderflowException, ConversionOverflowException {
        if (l < 0) {
            throw new ConversionUnderflowException(l, 0);
        } else if (l > UByte.MAX_VALUE) {
            throw new ConversionOverflowException(l, UByte.MAX_VALUE);
        } else {
            return ubyte(l);
        }
    }

    static Double int64ToDouble(@Nonnull Long l) {
        return l.doubleValue();
    }

    static Float int64ToFloat(@Nonnull Long l) {
        return l.floatValue();
    }

    static Short int64ToInt16(@Nonnull Long l) throws ConversionUnderflowException, ConversionOverflowException {
        if (l < Short.MIN_VALUE) {
            throw new ConversionUnderflowException(l, Short.MIN_VALUE);
        } else if (l > Short.MAX_VALUE) {
            throw new ConversionOverflowException(l, Short.MAX_VALUE);
        } else {
            return l.shortValue();
        }
    }

    static Integer int64ToInt32(@Nonnull Long l) throws ConversionUnderflowException, ConversionOverflowException {
        if (l < Integer.MIN_VALUE) {
            throw new ConversionUnderflowException(l, Integer.MIN_VALUE);
        } else if (l > Integer.MAX_VALUE) {
            throw new ConversionOverflowException(l, Integer.MAX_VALUE);
        } else {
            return l.intValue();
        }
    }

    static Byte int64ToSByte(@Nonnull Long l) throws ConversionUnderflowException, ConversionOverflowException {
        if (l < Byte.MIN_VALUE) {
            throw new ConversionUnderflowException(l, Byte.MIN_VALUE);
        } else if (l > Byte.MAX_VALUE) {
            throw new ConversionOverflowException(l, Byte.MAX_VALUE);
        } else {
            return l.byteValue();
        }
    }

    static StatusCode int64ToStatusCode(@Nonnull Long l) {
        return new StatusCode(l);
    }

    static String int64ToString(@Nonnull Long l) {
        return l.toString();
    }

    static UShort int64ToUInt16(@Nonnull Long l) throws ConversionUnderflowException, ConversionOverflowException {
        if (l < 0) {
            throw new ConversionUnderflowException(l, 0);
        } else if (l > UShort.MAX_VALUE) {
            throw new ConversionOverflowException(l, UShort.MAX_VALUE);
        } else {
            return ushort(l.intValue());
        }
    }

    static UInteger int64ToUInt32(@Nonnull Long l) throws ConversionUnderflowException, ConversionOverflowException {
        if (l < 0) {
            throw new ConversionUnderflowException(l, 0);
        } else if (l > UInteger.MAX_VALUE) {
            throw new ConversionOverflowException(l, UInteger.MAX_VALUE);
        } else {
            return uint(l);
        }
    }

    static ULong int64ToUInt64(@Nonnull Long l) throws ConversionUnderflowException {
        if (l >= 0) {
            return ulong(l);
        } else {
            throw new ConversionUnderflowException(l, 0);
        }
    }

    static Object convert(Object value, BuiltinDataType targetType, boolean implicit) throws ConversionException {
        if (value instanceof Long) {
            Long l = (Long) value;

            return implicit ?
                implicitConversion(l, targetType) :
                explicitConversion(l, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(@Nonnull Long l, BuiltinDataType targetType) throws ConversionException {
        //@formatter:off
        switch (targetType) {
            case Boolean:       return int64ToBoolean(l);
            case Byte:          return int64ToByte(l);
            case Int16:         return int64ToInt16(l);
            case Int32:         return int64ToInt32(l);
            case SByte:         return int64ToSByte(l);
            case StatusCode:    return int64ToStatusCode(l);
            case String:        return int64ToString(l);
            case UInt16:        return int64ToUInt16(l);
            case UInt32:        return int64ToUInt32(l);
            case UInt64:        return int64ToUInt64(l);
            default:            return implicitConversion(l, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(@Nonnull Long l, BuiltinDataType targetType) throws ConversionNotDefinedException {
        //@formatter:off
        switch (targetType) {
            case Double:    return int64ToDouble(l);
            case Float:     return int64ToFloat(l);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.Int64, targetType);
        }
        //@formatter:on
    }

}
