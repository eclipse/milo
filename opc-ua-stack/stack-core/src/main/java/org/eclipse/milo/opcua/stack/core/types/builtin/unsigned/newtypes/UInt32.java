package org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.newtypes;

import java.math.BigInteger;
import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedInts;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class UInt32 extends UnsignedNumber implements Comparable<UInt32>, UnsignedArithmetic<UInt32> {

    private static final long serialVersionUID = 1L;

    public static final UInt32 ZERO = new UInt32(0);
    public static final UInt32 ONE = new UInt32(1);
    public static final UInt32 MIN = ZERO;
    public static final UInt32 MAX = new UInt32(-1);

    private final int value;

    private UInt32(final int value) {
        this.value = value;
    }

    @Override
    public UInt32 plus(@Nonnull final UInt32 other) {
        return valueOf(this.value + other.value);
    }

    @Override
    public UInt32 minus(@Nonnull final UInt32 other) {
        return valueOf(this.value - other.value);
    }

    @Override
    public int intValue() {
        return this.value;
    }

    @Override
    public long longValue() {
        return UnsignedInts.toLong(this.value);
    }

    @Override
    public float floatValue() {
        return longValue();
    }

    @Override
    public double doubleValue() {
        return longValue();
    }

    @Override
    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(longValue());
    }

    @Override
    public int compareTo(@Nonnull final UInt32 o) {
        Preconditions.checkNotNull(o);

        return UnsignedInts.compare(this.value, o.value);
    }

    @Override
    public boolean equals(final Object obj) {
        return obj instanceof UInt32 && this.value == ((UInt32) obj).value;
    }

    @Override
    public int hashCode() {
        return this.value;
    }

    /**
     * @return a string representation of this {@code UInt32}.
     */
    @Override
    public String toString() {
        return UnsignedInts.toString(this.value);
    }

    public static UInt32 valueOf(final long value) {
        checkArgument(
            (value & 0xFFFFFFFFL) == value,
            "value (%s) must be greater than %s and less than %s",
            value, MIN, MAX
        );

        return new UInt32((int) value);
    }

    public static UInt32 valueOf(@Nonnull final BigInteger value) {
        checkNotNull(value);
        checkArgument(
            value.signum() >= 0 && value.bitLength() <= Integer.SIZE,
            "value (%s) must be greater than %s and less than %s",
            value, MIN, MAX
        );

        return new UInt32(value.intValue());
    }

    public static UInt32 valueOf(@Nonnull final String string) {
        return valueOf(string, 10);
    }

    public static UInt32 valueOf(@Nonnull final String string, final int radix) {
        final int value = UnsignedInts.parseUnsignedInt(string, radix);

        return valueOf(value);
    }

}
