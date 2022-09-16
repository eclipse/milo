package org.eclipse.milo.opcua.stack.core.types.builtin;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MatrixTest {
    private final int[][] primitiveInt2d = {{1, 2}, {3, 4}};
    private final Integer[][] boxedInt2d = {{1, 2}, {3, 4}};

    private final Matrix primitiveMatrix2d = new Matrix(primitiveInt2d);
    private final Matrix boxedMatrix2d = new Matrix(boxedInt2d);

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