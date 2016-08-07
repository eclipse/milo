/*
 * Copyright (c) 2016 Jens Reimann
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *  http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.newtypes;

import java.math.BigInteger;
import javax.annotation.Nonnull;

import static com.google.common.base.Preconditions.checkArgument;

public final class UInt8 extends UnsignedNumber implements Comparable<UInt8>, UnsignedArithmetic<UInt8> {

    private static final long serialVersionUID = 1L;

    private static final short MIN_VALUE = 0x00;
    private static final short MAX_VALUE = 0xFF;

    public static final UInt8 MIN = valueOf(MIN_VALUE);
    public static final UInt8 MAX = valueOf(MAX_VALUE);

    private final short value;

    private UInt8(short value) {
        this.value = value;
    }

    @Override
    public int compareTo(final UInt8 other) {
        return Short.compare(this.value, other.value);
    }

    @Override
    public UInt8 plus(@Nonnull final UInt8 other) {
        return valueOf(this.value + other.value);
    }

    @Override
    public UInt8 minus(@Nonnull final UInt8 other) {
        return valueOf(this.value - other.value);
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return this.value;
    }

    @Override
    public float floatValue() {
        return this.value;
    }

    @Override
    public double doubleValue() {
        return this.value;
    }

    @Override
    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(this.value);
    }

    @Override
    public boolean equals(final Object other) {
        return other instanceof UInt8 && this.value == ((UInt8) other).value;
    }

    @Override
    public int hashCode() {
        return Short.hashCode(this.value);
    }

    @Override
    public String toString() {
        return Integer.toUnsignedString(this.value);
    }

    public static UInt8 valueOf(final long value) {
        checkArgument(
            value >= MIN_VALUE && value <= MAX_VALUE,
            "value (%s) must be greater than %s and less than %s",
            value, MIN_VALUE, MAX_VALUE
        );

        return new UInt8((short) (value & 0xFF));
    }

    public static UInt8 valueOf(@Nonnull final String string) {
        return valueOf(string, 10);
    }

    public static UInt8 valueOf(@Nonnull final String string, final int radix) {
        int value = Integer.parseUnsignedInt(string, radix);

        return valueOf(value);
    }

}
