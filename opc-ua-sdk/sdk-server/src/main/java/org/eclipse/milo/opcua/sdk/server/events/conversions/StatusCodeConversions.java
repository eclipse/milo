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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class StatusCodeConversions {

    private StatusCodeConversions() {}

    @NotNull
    static Short statusCodeToInt16(@NotNull StatusCode s) {
        return (short) ((s.getValue() >>> 16) & 0xFFFF);
    }

    @NotNull
    static Integer statusCodeToInt32(@NotNull StatusCode s) {
        return (int) s.getValue();
    }

    @NotNull
    static Long statusCodeToInt64(@NotNull StatusCode s) {
        return s.getValue();
    }

    @NotNull
    static UShort statusCodeToUInt16(@NotNull StatusCode s) {
        return ushort(statusCodeToInt16(s));
    }

    @NotNull
    static UInteger statusCodeToUInt32(@NotNull StatusCode s) {
        return uint(statusCodeToInt32(s));
    }

    @NotNull
    static ULong statusCodeToUInt64(@NotNull StatusCode s) {
        return ulong(s.getValue());
    }

    @Nullable
    static Object convert(@NotNull Object o, BuiltinDataType targetType, boolean implicit) {
        if (o instanceof StatusCode) {
            StatusCode s = (StatusCode) o;

            return implicit ?
                implicitConversion(s, targetType) :
                explicitConversion(s, targetType);
        } else {
            return null;
        }
    }

    @Nullable
    static Object explicitConversion(@NotNull StatusCode s, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Int16:     return statusCodeToInt16(s);
            case UInt16:    return statusCodeToUInt16(s);
            default:        return implicitConversion(s, targetType);
        }
        //@formatter:on
    }

    @Nullable
    static Object implicitConversion(@NotNull StatusCode s, BuiltinDataType targetType) {
        //@formatter:off
        switch (targetType) {
            case Int32:     return statusCodeToInt32(s);
            case Int64:     return statusCodeToInt64(s);
            case UInt32:    return statusCodeToUInt32(s);
            case UInt64:    return statusCodeToUInt64(s);
            default:        return null;
        }
        //@formatter:on
    }

}
