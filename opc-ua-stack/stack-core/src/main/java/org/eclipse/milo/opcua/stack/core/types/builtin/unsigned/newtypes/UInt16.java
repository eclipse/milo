package org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.newtypes;

import java.math.BigInteger;
import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

import static com.google.common.base.Preconditions.checkArgument;

public class UInt16 extends UnsignedNumber implements Comparable<UInt16>, UnsignedArithmetic<UInt16> {

    private static final long serialVersionUID = 1L;

    private static final int MIN_VALUE = 0x00;
    private static final int MAX_VALUE = 0xFFFF;

    public static final UInt16 MIN = valueOf(MIN_VALUE);
    public static final UInt16 MAX = valueOf(MAX_VALUE);

    private final int value;

    private UInt16(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(@Nonnull UInt16 o) {
        Preconditions.checkNotNull(o);

        return Integer.compare(value, o.value);
    }

    @Override
    public UInt16 plus(@Nonnull UInt16 other) {
        return valueOf(this.value + other.value);
    }

    @Override
    public UInt16 minus(@Nonnull UInt16 other) {
        return valueOf(this.value - other.value);
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return value;
    }

    @Override
    public float floatValue() {
        return value;
    }

    @Override
    public double doubleValue() {
        return value;
    }

    @Override
    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(value);
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof UInt16 && this.value == ((UInt16) other).value;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(value);
    }

    @Override
    public String toString() {
        return Integer.toUnsignedString(value);
    }

    public static UInt16 valueOf(final long value) {
        checkArgument(
            value >= MIN_VALUE && value <= MAX_VALUE,
            "value (%s) must be greater than %s and less than %s",
            value, MIN_VALUE, MAX_VALUE
        );

        return new UInt16((int) (value & 0xFFFF));
    }

    public static UInt16 valueOf(@Nonnull final String string) {
        return valueOf(string, 10);
    }

    public static UInt16 valueOf(@Nonnull final String string, final int radix) {
        final int value = Integer.parseUnsignedInt(string, radix);

        return valueOf(value);
    }

}
