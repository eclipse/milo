package org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.newtypes;

import java.math.BigInteger;
import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;
import com.google.common.primitives.UnsignedInts;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class UInt32 extends UnsignedNumber implements Comparable<UInt32> {

    public static final UInt32 ZERO = fromIntBits(0);
    public static final UInt32 ONE = fromIntBits(1);
    public static final UInt32 MIN_VALUE = ZERO;
    public static final UInt32 MAX_VALUE = fromIntBits(-1);

    private final int value;

    private UInt32(int value) {
        this.value = value;
    }

    public UInt32 plus(@Nonnull UInt32 other) {
        Preconditions.checkNotNull(other);

        return fromIntBits(value + other.value);
    }

    public UInt32 minus(@Nonnull UInt32 other) {
        Preconditions.checkNotNull(other);

        return fromIntBits(value - other.value);
    }

    @Override
    public int intValue() {
        return value;
    }

    @Override
    public long longValue() {
        return UnsignedInts.toLong(value);
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
    public int compareTo(@Nonnull UInt32 o) {
        Preconditions.checkNotNull(o);

        return UnsignedInts.compare(value, o.value);
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UInt32
            && value == ((UInt32) obj).value;
    }

    @Override
    public int hashCode() {
        return value;
    }

    /**
     * @return a string representation of this {@code UInt32}.
     */
    @Override
    public String toString() {
        return UnsignedInts.toString(value);
    }

    public static UInt32 fromIntBits(int bits) {
        return new UInt32(bits);
    }

    public static UInt32 valueOf(long value) {
        checkArgument(
            (value & 0xFFFFFFFFL) == value,
            "value (%s) must be greater than 0 and less than %s", value, MAX_VALUE);

        return fromIntBits((int) value);
    }

    public static UInt32 valueOf(@Nonnull BigInteger value) {
        checkNotNull(value);
        checkArgument(
            value.signum() >= 0 && value.bitLength() <= Integer.SIZE,
            "value (%s) must be greater than 0 and less than %s", value, MAX_VALUE);

        return fromIntBits(value.intValue());
    }

    public static UInt32 valueOf(@Nonnull String string) {
        return valueOf(string, 10);
    }

    public static UInt32 valueOf(@Nonnull String string, int radix) {
        int value = UnsignedInts.parseUnsignedInt(string, radix);

        return fromIntBits(value);
    }

}
