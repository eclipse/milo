/*
 * Copyright (c) 2017 Kevin Herron
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

import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToByte;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToDouble;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToFloat;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToInt16;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToInt32;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToInt64;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToSByte;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToUInt16;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToUInt32;
import static org.eclipse.milo.opcua.sdk.server.events.conversions.BooleanConversions.booleanToUInt64;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.testng.Assert.assertEquals;

public class BooleanConversionsTest {

    @Test
    public void testBooleanToByte() {
        assertEquals(booleanToByte(false), ubyte(0));
        assertEquals(booleanToByte(true), ubyte(1));
    }

    @Test
    public void testBooleanToDouble() {
        assertEquals(booleanToDouble(false), 0.0);
        assertEquals(booleanToDouble(true), 1.0);
    }

    @Test
    public void testBooleanToFloat() {
        assertEquals(booleanToFloat(false), 0.0f);
        assertEquals(booleanToFloat(true), 1.0f);
    }

    @Test
    public void testBooleanToInt16() {
        assertEquals((short) booleanToInt16(false), 0);
        assertEquals((short) booleanToInt16(true), 1);
    }

    @Test
    public void testBooleanToInt32() {
        assertEquals((int) booleanToInt32(false), 0);
        assertEquals((int) booleanToInt32(true), 1);
    }

    @Test
    public void testBooleanToInt64() {
        assertEquals((long) booleanToInt64(false), 0);
        assertEquals((long) booleanToInt64(true), 1);
    }

    @Test
    public void testBooleanToSByte() {
        assertEquals((byte) booleanToSByte(false), 0);
        assertEquals((byte) booleanToSByte(true), 1);
    }

    @Test
    public void testBooleanToUInt16() {
        assertEquals(booleanToUInt16(false), ushort(0));
        assertEquals(booleanToUInt16(true), ushort(1));
    }

    @Test
    public void testBooleanToUInt32() {
        assertEquals(booleanToUInt32(false), uint(0));
        assertEquals(booleanToUInt32(true), uint(1));
    }

    @Test
    public void testBooleanToUInt64() {
        assertEquals(booleanToUInt64(false), ulong(0));
        assertEquals(booleanToUInt64(true), ulong(1));
    }

}