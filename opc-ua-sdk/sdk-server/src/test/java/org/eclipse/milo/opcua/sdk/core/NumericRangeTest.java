/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.eclipse.milo.opcua.stack.core.UaException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class NumericRangeTest {

    @ParameterizedTest
    @MethodSource("getArray1dRanges")
    public void testArray1d(String range, int[] expected) throws UaException {
        NumericRange nr = NumericRange.parse(range);
        Variant value = new Variant(array1d);

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof int[]);
        assertTrue(Arrays.equals(expected, (int[]) result));
    }

    @ParameterizedTest
    @MethodSource("getArray2dRanges")
    public void testArray2d(String range, int[][] expected) throws UaException {
        NumericRange nr = NumericRange.parse(range);
        Variant value = new Variant(array2d);

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof int[][]);
        assertTrue(Arrays.deepEquals(expected, (int[][]) result));
    }

    @ParameterizedTest
    @MethodSource("getArray3dRanges")
    public void testArray3d(String range, int[][][] expected) throws UaException {
        NumericRange nr = NumericRange.parse(range);
        Variant value = new Variant(array3d);

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof int[][][]);
        assertTrue(Arrays.deepEquals(expected, (int[][][]) result));
    }

    @Test
    public void testArrayBeyondEnd() throws UaException {
        int[] array = new int[]{0, 1, 2, 3};
        NumericRange nr = NumericRange.parse("0:10");
        Variant value = new Variant(array);

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof int[]);
        assertArrayEquals((int[]) result, array);
    }

    @Test
    public void testString1d() throws UaException {
        NumericRange nr = NumericRange.parse("1:2");
        Variant value = new Variant("abcdef");

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof String);
        assertTrue("bc".equals(result));
    }

    @Test
    public void testString2d_1() throws UaException {
        NumericRange nr = NumericRange.parse("0:1,3:4");
        Variant value = new Variant(new String[]{
            "abcdef",
            "ghijkl"
        });

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof String[]);
        assertTrue(Arrays.deepEquals(new String[]{"de", "jk"}, (String[]) result));
    }

    @Test
    public void testString2d_2() throws UaException {
        NumericRange nr = NumericRange.parse("0:2,0:2");
        Variant value = new Variant(new String[]{
            "abcdef",
            "ghijkl",
            "mnopqr"
        });

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof String[]);
        assertTrue(Arrays.deepEquals(new String[]{"abc", "ghi", "mno"}, (String[]) result));
    }

    @Test
    public void testStringBeyondEnd() throws UaException {
        String string = "abcdef";
        NumericRange nr = NumericRange.parse("0:10");
        Variant value = new Variant(string);

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof String);
        assertEquals(string, result);
    }

    @Test
    public void testByteString1d() throws UaException {
        NumericRange nr = NumericRange.parse("1:2");
        Variant value = new Variant(new ByteString(new byte[]{1, 2, 3, 4}));

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof ByteString);
        assertEquals(new ByteString(new byte[]{2, 3}), result);
    }

    @Test
    public void testByteString2d() throws UaException {
        NumericRange nr = NumericRange.parse("0:1,1:2");
        Variant value = new Variant(new ByteString[]{
            new ByteString(new byte[]{1, 2, 3, 4}),
            new ByteString(new byte[]{5, 6, 7, 8})
        });

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof ByteString[]);

        ByteString[] expected = {
            new ByteString(new byte[]{2, 3}),
            new ByteString(new byte[]{6, 7})
        };

        assertTrue(Arrays.deepEquals((ByteString[]) result, expected));
    }

    @Test
    public void testByteStringBeyondEnd() throws UaException {
        ByteString byteString = new ByteString(new byte[]{1, 2, 3, 4});
        NumericRange nr = NumericRange.parse("0:10");
        Variant value = new Variant(byteString);

        Object result = NumericRange.readFromValueAtRange(value, nr);

        assertTrue(result instanceof ByteString);
        assertEquals(byteString, result);
    }

    @ParameterizedTest
    @MethodSource("getInvalidRanges")
    public void testInvalidRange(String indexRange) throws UaException {
        assertThrows(UaException.class, () ->  NumericRange.parse(indexRange));
    }


    @Test
    public void testWrite1d() throws UaException {
        Variant current = new Variant(new int[]{0, 1, 2, 3});
        Variant update = new Variant(new int[]{2, 4});
        NumericRange range = NumericRange.parse("1:2");

        Object updated = NumericRange.writeToValueAtRange(current, update, range);

        assertTrue(updated instanceof int[]);
        assertTrue(Arrays.equals(
            (int[]) updated,
            new int[]{0, 2, 4, 3}
        ));
    }

    @Test
    public void testWriteString() throws UaException {
        Variant current = new Variant("abcdefg");
        Variant update = new Variant("ZzZzZ");
        NumericRange range = NumericRange.parse("1:5");

        Object updated = NumericRange.writeToValueAtRange(current, update, range);

        assertTrue(updated instanceof String);
        assertEquals("aZzZzZg", updated);
    }

    @Test
    public void testWriteByteString() throws UaException {
        Variant current = new Variant(new ByteString(new byte[]{0, 1, 2, 3}));
        Variant update = new Variant(new ByteString(new byte[]{2, 4}));
        NumericRange range = NumericRange.parse("1:2");

        Object updated = NumericRange.writeToValueAtRange(current, update, range);

        assertTrue(updated instanceof ByteString);
        assertEquals(new ByteString(new byte[]{0, 2, 4, 3}), updated);
    }

    public static Object[][] getInvalidRanges() {
        return new Object[][]{
            {"0:0"},
            {"1:1"},
            {"-4:0"},
            {"0:-4"},
            {"3:1"},
            {"abc,def"},
            {"1:2,3:1"},
        };
    }

    private static final int[] array1d = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static Object[][] getArray1dRanges() {
        return new Object[][]{
            {"0:3", new int[]{0, 1, 2, 3}},
            {"4:9", new int[]{4, 5, 6, 7, 8, 9}},
            {"1:4", new int[]{1, 2, 3, 4}},
            {"5:8", new int[]{5, 6, 7, 8}},
            {"0:10", new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9}},
            {"5:15", new int[]{5, 6, 7, 8, 9}},
            {"0", new int[]{0}},
            {"5", new int[]{5}},
            {"9", new int[]{9}}
        };
    }


    private static final int[][] array2d = new int[][]{
        {0, 1, 2, 3},
        {4, 5, 6, 7},
        {8, 9, 10, 11},
        {12, 13, 14, 15}
    };

    public static Object[][] getArray2dRanges() {
        return new Object[][]{
            {"0:1,0:1", new int[][]{{0, 1}, {4, 5}}},
            {"1:2,1:3", new int[][]{{5, 6, 7}, {9, 10, 11}}},
            {"2:5,2:5", new int[][]{{10, 11}, {14, 15}}},
            {"3,3", new int[][]{{15}}}
        };
    }

    private static final int[][][] array3d = new int[][][]{
        {{0, 1}, {2, 3}, {4, 5}},
        {{6, 7}, {8, 9}, {10, 11}},
        {{12, 13}, {14, 15}, {16, 17}, {18, 19}},
        {{20, 21}, {22, 23}, {24, 25}, {26, 27}}
    };

    public static Object[][] getArray3dRanges() {
        return new Object[][]{
            {"0:1,0:1,0:1", new int[][][]{
                {{0, 1}, {2, 3}},
                {{6, 7}, {8, 9}}}
            },
            {"1:2,1:2,0:1", new int[][][]{
                {{8, 9}, {10, 11}},
                {{14, 15}, {16, 17}}}
            },
            {"3,2,0", new int[][][]{
                {{24}}}
            }
        };
    }

}
