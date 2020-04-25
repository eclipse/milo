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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class StatusCodeConversions {

    private StatusCodeConversions() {}

    static Short statusCodeToInt16(StatusCode s) {
        return (short) ((s.getValue() >>> 16) & 0xFFFF);
    }

    static Integer statusCodeToInt32(StatusCode s) {
        return (int) s.getValue();
    }

    static Long statusCodeToInt64(StatusCode s) {
        return s.getValue();
    }

    static UShort statusCodeToUInt16(StatusCode s) {
        return ushort(statusCodeToInt16(s));
    }

    static UInteger statusCodeToUInt32(StatusCode s) {
        return uint(statusCodeToInt32(s));
    }

    static ULong statusCodeToUInt64(StatusCode s) {
        return ulong(s.getValue());
    }

    static Object convert(
        Object value,
        BuiltinDataType targetType,
        boolean implicit
    ) throws ConversionNotDefinedException {

        if (value instanceof StatusCode) {
            StatusCode s = (StatusCode) value;

            return implicit ?
                implicitConversion(s, targetType) :
                explicitConversion(s, targetType);
        } else {
            throw new IllegalArgumentException("value: " + value);
        }
    }

    static Object explicitConversion(StatusCode s, BuiltinDataType targetType) throws ConversionNotDefinedException {
        //@formatter:off
        switch (targetType) {
            case Int16:     return statusCodeToInt16(s);
            case UInt16:    return statusCodeToUInt16(s);
            default:        return implicitConversion(s, targetType);
        }
        //@formatter:on
    }

    static Object implicitConversion(StatusCode s, BuiltinDataType targetType) throws ConversionNotDefinedException {
        //@formatter:off
        switch (targetType) {
            case Int32:     return statusCodeToInt32(s);
            case Int64:     return statusCodeToInt64(s);
            case UInt32:    return statusCodeToUInt32(s);
            case UInt64:    return statusCodeToUInt64(s);
            default:        throw new ConversionNotDefinedException(BuiltinDataType.StatusCode, targetType);
        }
        //@formatter:on
    }

}
