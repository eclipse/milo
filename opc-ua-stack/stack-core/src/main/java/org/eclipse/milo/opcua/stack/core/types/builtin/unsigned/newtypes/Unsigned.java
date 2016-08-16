package org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.newtypes;

import java.math.BigInteger;

/**
 * A utility class for creating unsigned number instances.
 */
public final class Unsigned {

    private Unsigned() {}

    /**
     * Create a {@link UInt8} from {@code value}.
     *
     * @param value a value in the range [0, 255].
     * @return a {@link UInt8} containing {@code value}.
     * @throws OutOfRangeException if {@code value} is outside the range [0, 255].
     */
    public static UInt8 uint8(long value) throws OutOfRangeException {
        return UInt8.valueOf(value);
    }

    /**
     * Create a {@link UInt16} from {@code value}.
     *
     * @param value a value in the range [0, 65535].
     * @return a {@link UInt16} containing {@code value}.
     * @throws OutOfRangeException if {@code value} is outside the range [0, 65535].
     */
    public static UInt16 uint16(long value) throws OutOfRangeException {
        return UInt16.valueOf(value);
    }

    /**
     * Create a {@link UInt32} from {@code value}.
     *
     * @param value a value in the range [0, 4294967295].
     * @return a {@link UInt32} containing {@code value}.
     * @throws OutOfRangeException if {@code value} is outside the range [0, 4294967295].
     */
    public static UInt32 uint32(long value) {
        return UInt32.valueOf(value);
    }

    /**
     * Create a {@link UInt64} from {@code value}.
     *
     * @param value a value in the range [0, 18446744073709551615].
     * @return a {@link UInt64} containing {@code value}.
     * @throws OutOfRangeException if {@code value} is outside the range [0, 18446744073709551615].
     */
    public static UInt64 uint64(long value) {
        return UInt64.valueOf(value);
    }

    /**
     * Create a {@link UInt64} from {@code value}.
     *
     * @param value a {@link BigInteger} in the range [0, 18446744073709551615].
     * @return a {@link UInt64} containing {@code value}.
     * @throws OutOfRangeException if {@code value} is outside the range [0, 18446744073709551615].
     */
    public static UInt64 uint64(BigInteger value) {
        return UInt64.valueOf(value);
    }

}
