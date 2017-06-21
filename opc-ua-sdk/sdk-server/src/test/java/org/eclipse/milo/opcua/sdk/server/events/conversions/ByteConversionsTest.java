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

import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToBoolean;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToDouble;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToFloat;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToInt16;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToInt32;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToInt64;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToSByte;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToString;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToUInt16;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToUInt32;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.ByteConversions.byteToUInt64;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

public class ByteConversionsTest {

    @Test
    public void testByteToBoolean() {
        assertEquals(byteToBoolean(ubyte(0)), Boolean.FALSE);
        assertEquals(byteToBoolean(ubyte(1)), Boolean.TRUE);
    }

    @Test
    public void testByteToDouble() {
        assertEquals(byteToDouble(ubyte(0)), 0.0d);
        assertEquals(byteToDouble(ubyte(1)), 1.0d);
    }

    @Test
    public void testByteToFloat() {
        assertEquals(byteToFloat(ubyte(0)), 0.0f);
        assertEquals(byteToFloat(ubyte(1)), 1.0f);
    }

    @Test
    public void testByteToInt16() {
        assertEquals(byteToInt16(ubyte(0)), Short.valueOf((short) 0));
        assertEquals(byteToInt16(ubyte(1)), Short.valueOf((short) 1));
    }

    @Test
    public void testByteToInt32() {
        assertEquals(byteToInt32(ubyte(0)), Integer.valueOf(0));
        assertEquals(byteToInt32(ubyte(1)), Integer.valueOf(1));
    }

    @Test
    public void testByteToInt64() {
        assertEquals(byteToInt64(ubyte(0)), Long.valueOf(0));
        assertEquals(byteToInt64(ubyte(1)), Long.valueOf(1));
    }

    @Test
    public void testByteToSByte() {
        assertEquals(byteToSByte(ubyte(0)), Byte.valueOf((byte) 0));
        assertEquals(byteToSByte(ubyte(Byte.MAX_VALUE)), Byte.valueOf(Byte.MAX_VALUE));
        assertNull(byteToSByte(ubyte(Byte.MAX_VALUE + 1)));
    }

    @Test
    public void testByteToString() {
        assertEquals(byteToString(ubyte(0)), "0");
        assertEquals(byteToString(ubyte(1)), "1");
    }

    @Test
    public void testByteToUInt16() {
        assertEquals(byteToUInt16(UByte.MIN), ushort(UByte.MIN_VALUE));
        assertEquals(byteToUInt16(UByte.MAX), ushort(UByte.MAX_VALUE));
    }

    @Test
    public void testByteToUInt32() {
        assertEquals(byteToUInt32(UByte.MIN), uint(UByte.MIN_VALUE));
        assertEquals(byteToUInt32(UByte.MAX), uint(UByte.MAX_VALUE));
    }

    @Test
    public void testByteToUInt64() {
        assertEquals(byteToUInt64(UByte.MIN), ulong(UByte.MIN_VALUE));
        assertEquals(byteToUInt64(UByte.MAX), ulong(UByte.MAX_VALUE));
    }

}
