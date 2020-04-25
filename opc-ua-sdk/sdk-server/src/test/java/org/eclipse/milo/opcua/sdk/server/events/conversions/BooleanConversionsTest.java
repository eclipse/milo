/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.server.events.conversions;

import org.testng.annotations.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.testng.Assert.assertEquals;

public class BooleanConversionsTest {

    private final BooleanConversions instance = BooleanConversions.INSTANCE;

    @Test
    public void testBooleanToByte() {
        assertEquals(instance.booleanToByte(false), ubyte(0));
        assertEquals(instance.booleanToByte(true), ubyte(1));
    }

    @Test
    public void testBooleanToDouble() {
        assertEquals(instance.booleanToDouble(false), 0.0);
        assertEquals(instance.booleanToDouble(true), 1.0);
    }

    @Test
    public void testBooleanToFloat() {
        assertEquals(instance.booleanToFloat(false), 0.0f);
        assertEquals(instance.booleanToFloat(true), 1.0f);
    }

    @Test
    public void testBooleanToInt16() {
        assertEquals((short) instance.booleanToInt16(false), 0);
        assertEquals((short) instance.booleanToInt16(true), 1);
    }

    @Test
    public void testBooleanToInt32() {
        assertEquals((int) instance.booleanToInt32(false), 0);
        assertEquals((int) instance.booleanToInt32(true), 1);
    }

    @Test
    public void testBooleanToInt64() {
        assertEquals((long) instance.booleanToInt64(false), 0);
        assertEquals((long) instance.booleanToInt64(true), 1);
    }

    @Test
    public void testBooleanToSByte() {
        assertEquals((byte) instance.booleanToSByte(false), 0);
        assertEquals((byte) instance.booleanToSByte(true), 1);
    }

    @Test
    public void testBooleanToString() {
        assertEquals(instance.booleanToString(true), "1");
        assertEquals(instance.booleanToString(false), "0");
    }

    @Test
    public void testBooleanToUInt16() {
        assertEquals(instance.booleanToUInt16(false), ushort(0));
        assertEquals(instance.booleanToUInt16(true), ushort(1));
    }

    @Test
    public void testBooleanToUInt32() {
        assertEquals(instance.booleanToUInt32(false), uint(0));
        assertEquals(instance.booleanToUInt32(true), uint(1));
    }

    @Test
    public void testBooleanToUInt64() {
        assertEquals(instance.booleanToUInt64(false), ulong(0));
        assertEquals(instance.booleanToUInt64(true), ulong(1));
    }

}
