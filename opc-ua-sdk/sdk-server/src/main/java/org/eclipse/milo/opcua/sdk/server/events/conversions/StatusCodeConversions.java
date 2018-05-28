/*
 * Copyright (c) 2018 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import javax.annotation.Nonnull;

import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

final class StatusCodeConversions {

    private StatusCodeConversions() {}

    @Nonnull
    static Short statusCodeToInt16(@Nonnull StatusCode s) {
        return (short) ((s.getValue() >>> 16) & 0xFFFF);
    }

    @Nonnull
    static Integer statusCodeToInt32(@Nonnull StatusCode s) {
        return (int) s.getValue();
    }

    @Nonnull
    static Long statusCodeToInt64(@Nonnull StatusCode s) {
        return s.getValue();
    }

    @Nonnull
    static UShort statusCodeToUInt16(@Nonnull StatusCode s) {
        return ushort(statusCodeToInt16(s));
    }

    @Nonnull
    static UInteger statusCodeToUInt32(@Nonnull StatusCode s) {
        return uint(statusCodeToInt32(s));
    }

    @Nonnull
    static ULong statusCodeToUInt64(@Nonnull StatusCode s) {
        return ulong(s.getValue());
    }

}
