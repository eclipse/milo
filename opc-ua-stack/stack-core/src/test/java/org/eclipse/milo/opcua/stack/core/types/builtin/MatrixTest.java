/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.builtin;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MatrixTest {
    private final int[][] primitiveInt2d = {{1, 2}, {3, 4}};
    private final Integer[][] boxedInt2d = {{1, 2}, {3, 4}};

    private final Matrix primitiveMatrix2d = new Matrix(primitiveInt2d);
    private final Matrix boxedMatrix2d = new Matrix(boxedInt2d);

    @Test
    void matrixTransform() {
        Matrix m = Matrix.ofInt32(new int[][]{
            {0, 1},
            {2, 3}
        });

        Matrix transformed = m.transform(Object::toString);

        Matrix expected = Matrix.ofString(new String[][]{
            new String[]{"0", "1"},
            new String[]{"2", "3"}
        });

        assertEquals(expected, transformed);
    }

    @Test
    void matrixUnflatten() {
        String[][] value = {
            new String[]{"0", "1"},
            new String[]{"2", "3"}
        };

        Matrix m = Matrix.ofString(value);

        assertTrue(Arrays.deepEquals(new String[]{"0", "1", "2", "3"}, (Object[]) m.getElements()));
        assertTrue(Arrays.deepEquals(value, (String[][]) m.unflatten()));
    }

    @Test
    void matrixToString() {
        assertEquals(
            "Matrix{builtinDataType=Int32, dimensions=[2, 2], flatArray=[1, 2, 3, 4]}", primitiveMatrix2d.toString());
        assertEquals(
            "Matrix{builtinDataType=Int32, dimensions=[2, 2], flatArray=[1, 2, 3, 4]}", boxedMatrix2d.toString());
    }

    @Test
    void matrixEquals() {
        assertEquals(primitiveMatrix2d, new Matrix(primitiveInt2d));
        assertEquals(boxedMatrix2d, new Matrix(boxedInt2d));
    }

}