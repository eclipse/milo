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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotEquals;

import org.testng.annotations.Test;

public class UInt8Test extends AbstractUnsingedTest {

    @Test
    public void testValueOfByteSuccess() {
        UInt8.valueOf((byte) 0);
        UInt8.valueOf((byte) 127);
        UInt8.valueOf((byte) 255);
    }

    @Test
    public void testValueOfShortSuccess() {
        UInt8.valueOf((short) 0);
        UInt8.valueOf((short) 127);
        UInt8.valueOf((short) 255);
    }

    @Test
    public void testValueOfIntSuccess() {
        UInt8.valueOf(0);
        UInt8.valueOf(127);
        UInt8.valueOf(255);
    }

    @Test
    public void testValueOfLongSuccess() {
        UInt8.valueOf(0L);
        UInt8.valueOf(127L);
        UInt8.valueOf(255L);
    }

    @Test(expectedExceptions = OutOfRangeException.class)
    public void testRangeError1() {
        UInt8.valueOf(-1);
    }

    @Test(expectedExceptions = OutOfRangeException.class)
    public void testRangeError2() {
        UInt8.valueOf(256);
    }

    @Test
    public void testCompareMin() {
        assertEquals(UInt8.valueOf((byte) 0), UInt8.valueOf((byte) 0));
        assertEquals(UInt8.valueOf((byte) 0), UInt8.valueOf((short) 0));
        assertEquals(UInt8.valueOf((byte) 0), UInt8.valueOf(0));
        assertEquals(UInt8.valueOf((byte) 0), UInt8.valueOf(0L));
    }

    @Test
    public void testCompareMax() {
        assertEquals(UInt8.valueOf((byte) 255), UInt8.valueOf((byte) 255));
        assertEquals(UInt8.valueOf((byte) 255), UInt8.valueOf((short) 255));
        assertEquals(UInt8.valueOf((byte) 255), UInt8.valueOf(255));
        assertEquals(UInt8.valueOf((byte) 255), UInt8.valueOf(255L));
    }

    @Test
    public void testCorners() {
        assertEquals(UInt8.valueOf((byte) -1), UInt8.valueOf((byte) 255));
        assertEquals(UInt8.valueOf((byte) -128), UInt8.valueOf((byte) 128));
    }

    @Test
    public void testCompareNoMatch() {
        assertNotEquals(UInt8.valueOf((byte) 0), UInt8.valueOf((byte) 255));
    }

    @Test
    public void testCompareOrder() {
        assertCompare(UInt8.valueOf(0), UInt8.valueOf(0), 0);
        assertCompare(UInt8.valueOf(1), UInt8.valueOf(0), 1);
        assertCompare(UInt8.valueOf(0), UInt8.valueOf(1), -1);

        assertCompare(UInt8.valueOf(255), UInt8.valueOf(255), 0);
        assertCompare(UInt8.valueOf(255), UInt8.valueOf(0), 1);
        assertCompare(UInt8.valueOf(0), UInt8.valueOf(255), -1);

        assertCompare(UInt8.valueOf((byte) 255), UInt8.valueOf((byte) 255), 0);
        assertCompare(UInt8.valueOf((byte) 255), UInt8.valueOf((byte) 0), 1);
        assertCompare(UInt8.valueOf((byte) 0), UInt8.valueOf((byte) 255), -1);

        assertCompare(UInt8.valueOf((byte) 255), UInt8.valueOf(255), 0);
        assertCompare(UInt8.valueOf((byte) 255), UInt8.valueOf(0), 1);
        assertCompare(UInt8.valueOf((byte) 0), UInt8.valueOf(255), -1);
    }

    @Test
    public void testToShort() {
        assertEquals(UInt8.valueOf(0).shortValue(), (short) 0);

        assertEquals(UInt8.valueOf((byte) 255).shortValue(), (short) 255);
        assertEquals(UInt8.valueOf((short) 255).shortValue(), (short) 255);
        assertEquals(UInt8.valueOf(255).shortValue(), (short) 255);
        assertEquals(UInt8.valueOf(255L).shortValue(), (short) 255);
    }

    @Test
    public void testToInt() {
        assertEquals(UInt8.valueOf(0).intValue(), 0);

        assertEquals(UInt8.valueOf((byte) 255).intValue(), 255);
        assertEquals(UInt8.valueOf((short) 255).intValue(), 255);
        assertEquals(UInt8.valueOf(255).intValue(), 255);
        assertEquals(UInt8.valueOf(255L).intValue(), 255);
    }

    @Test
    public void testToLong() {
        assertEquals(UInt8.valueOf(0).longValue(), 0L);

        assertEquals(UInt8.valueOf((byte) 255).longValue(), 255L);
        assertEquals(UInt8.valueOf((short) 255).longValue(), 255L);
        assertEquals(UInt8.valueOf(255).longValue(), 255L);
        assertEquals(UInt8.valueOf(255L).longValue(), 255L);
    }

    @Test
    public void testPlusSuccess() {
        assertEquals(UInt8.valueOf(0).plus(UInt8.valueOf(0)), UInt8.valueOf(0));
        assertEquals(UInt8.valueOf(127).plus(UInt8.valueOf(128)), UInt8.valueOf(255));
    }

    @Test
    public void testMinusSuccess() {
        assertEquals(UInt8.valueOf(0).minus(UInt8.valueOf(0)), UInt8.valueOf(0));
        assertEquals(UInt8.valueOf(255).minus(UInt8.valueOf(255)), UInt8.valueOf(0));
    }

    @Test(expectedExceptions = OutOfRangeException.class)
    public void testPlusFail1() {
        UInt8.valueOf(128).plus(UInt8.valueOf(128));
    }

    @Test(expectedExceptions = OutOfRangeException.class)
    public void testMinusFail1() {
        UInt8.valueOf(0).minus(UInt8.valueOf(1));
    }

}
