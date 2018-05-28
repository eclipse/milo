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
import javax.annotation.Nullable;

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

    @Nonnull
    static Boolean sByteToBoolean(@Nonnull Byte b) {
        return b != 0;
    }

    @Nullable
    static UByte sByteToByte(@Nonnull Byte b) {
        if (b >= 0) {
            return ubyte(b);
        } else {
            return null;
        }
    }

    @Nonnull
    static Double sByteToDouble(@Nonnull Byte b) {
        return b.doubleValue();
    }

    @Nonnull
    static Float sByteToFloat(@Nonnull Byte b) {
        return b.floatValue();
    }

    @Nonnull
    static Short sByteToInt16(@Nonnull Byte b) {
        return b.shortValue();
    }

    @Nonnull
    static Integer sByteToInt32(@Nonnull Byte b) {
        return b.intValue();
    }

    @Nonnull
    static Long sByteToInt64(@Nonnull Byte b) {
        return b.longValue();
    }

    @Nonnull
    static String sByteToString(@Nonnull Byte b) {
        return b.toString();
    }

    @Nullable
    static UShort sByteToUInt16(@Nonnull Byte b) {
        if (b >= 0) {
            return ushort(b);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger sByteToUInt32(@Nonnull Byte b) {
        if (b >= 0) {
            return uint(b);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong sByteToUInt64(@Nonnull Byte b) {
        if (b >= 0) {
            return ulong(b);
        } else {
            return null;
        }
    }

}
