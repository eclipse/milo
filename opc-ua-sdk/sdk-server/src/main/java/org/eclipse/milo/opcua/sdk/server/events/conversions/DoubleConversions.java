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

final class DoubleConversions {

    private DoubleConversions() {}

    @Nonnull
    static Boolean doubleToBoolean(@Nonnull Double d) {
        return d != 0.0d;
    }

    @Nullable
    static UByte doubleToByte(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= 0 && rounded <= UByte.MAX_VALUE) {
            return ubyte(rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static Float doubleToFloat(@Nonnull Double d) {
        if (d >= Float.MIN_VALUE && d <= Float.MAX_VALUE) {
            return d.floatValue();
        } else {
            return null;
        }
    }

    @Nullable
    static Short doubleToInt16(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= Short.MIN_VALUE && rounded <= Short.MAX_VALUE) {
            return (short) rounded;
        } else {
            return null;
        }
    }

    @Nullable
    static Integer doubleToInt32(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= Integer.MIN_VALUE && rounded <= Integer.MAX_VALUE) {
            return (int) rounded;
        } else {
            return null;
        }
    }

    @Nonnull
    static Long doubleToInt64(@Nonnull Double d) {
        return Math.round(d);
    }

    @Nullable
    static Byte doubleToSByte(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= Byte.MIN_VALUE && rounded <= Byte.MAX_VALUE) {
            return (byte) rounded;
        } else {
            return null;
        }
    }

    @Nonnull
    static String doubleToString(@Nonnull Double d) {
        return d.toString();
    }

    @Nullable
    static UShort doubleToUInt16(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= UShort.MIN_VALUE && rounded <= UShort.MAX_VALUE) {
            return ushort((int) rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static UInteger doubleToUInt32(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= UInteger.MIN_VALUE && rounded <= UInteger.MAX_VALUE) {
            return uint(rounded);
        } else {
            return null;
        }
    }

    @Nullable
    static ULong doubleToUInt64(@Nonnull Double d) {
        long rounded = Math.round(d);

        if (rounded >= 0) {
            return ulong(rounded);
        } else {
            return null;
        }
    }

}
