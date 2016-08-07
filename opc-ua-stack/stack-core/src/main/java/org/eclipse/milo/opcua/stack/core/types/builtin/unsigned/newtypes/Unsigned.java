package org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.newtypes;

import java.math.BigInteger;

public class Unsigned {

    public static UInt8 uint8(byte value) {
        return UInt8.valueOf(value);
    }

    public static UInt8 uint8(short value) {
        return UInt8.valueOf(value);
    }

    public static UInt8 uint8(int value) {
        return UInt8.valueOf(value);
    }

    public static UInt8 uint8(long value) {
        return UInt8.valueOf(value);
    }

    public static UInt16 uint16(short value) {
        return UInt16.valueOf(value);
    }

    public static UInt16 uint16(int value) {
        return UInt16.valueOf(value);
    }

    public static UInt16 uint16(long value) {
        return UInt16.valueOf(value);
    }

    public static UInt32 uint32(int value) {
        return UInt32.valueOf(value);
    }

    public static UInt32 uint32(long value) {
        return UInt32.valueOf(value);
    }

    public static UInt64 uint64(long value) {
        return UInt64.valueOf(value);
    }

    public static UInt64 uint64(BigInteger value) {
        return UInt64.valueOf(value);
    }

}
