/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class ArrayUtilTest {

    public static Object[][] getArrays() {
        return new Object[][]{
                {new Integer[]{0, 1, 2, 3, 4, 5, 6, 7}},
                {new int[]{0, 1, 2, 3, 4, 5, 6, 7}},
                {new int[][]{{0, 1}, {2, 3}, {4, 5}, {6, 7}}},
                {new int[][][]{{{0, 1}, {2, 3},}, {{4, 5}, {6, 7}}}}
        };
    }

    @ParameterizedTest
    @MethodSource("getArrays")
    public void testRoundTrip(Object array) throws Exception {
        Object flattened = ArrayUtil.flatten(array);
        Object unflattened = ArrayUtil.unflatten(flattened, ArrayUtil.getDimensions(array));
        if (array instanceof Object[]) {
        	assertArrayEquals((Object[]) unflattened, (Object[]) array);
        } else if (array instanceof int[]){
        	assertArrayEquals((int[]) unflattened, (int[]) array);
        }
    }

    @ParameterizedTest
    @MethodSource("getArrays")
    public void testFlatten(Object array) throws Exception {
        Object flattened = ArrayUtil.flatten(array);

        for (int i = 0; i < Array.getLength(flattened); i++) {
            assertEquals(Array.get(flattened, i), i);
        }
    }

    public static Object[][] getDimensions() {
        return new Object[][]{
                {new int[0], new int[]{0}},
                {new int[1], new int[]{1}},
                {new int[0][0], new int[]{0, 0}},
                {new int[1][2], new int[]{1, 2}},
                {new int[0][0][0], new int[]{0, 0, 0}},
                {new int[1][2][3], new int[]{1, 2, 3}}
        };
    }

    @ParameterizedTest
    @MethodSource("getDimensions")
    public void testGetDimensions(Object array, int[] dimensions) throws Exception {
        assertArrayEquals(ArrayUtil.getDimensions(array), dimensions);
    }

    public static Object[][] getTypedArrays() {
        return new Object[][]{
                {new int[1], int.class},
                {new int[2][2], int.class},
                {new int[3][3][3], int.class},
                {new Integer[1], Integer.class},
                {new Integer[2][2], Integer.class},
                {new Integer[3][3][3], Integer.class},
                {new String[1], String.class},
                {new String[2][2], String.class},
                {new String[3][3][3], String.class},
        };
    }

    @ParameterizedTest
    @MethodSource("getTypedArrays")
    public void testGetType(Object array, Class<?> type) throws Exception {
        assertEquals(ArrayUtil.getType(array), type);
    }

}
