package org.eclipse.milo.opcua.stack.core.types.builtin;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.jetbrains.annotations.Nullable;

/**
 * Matrix is a container for multidimensional array values of one of the built-in types.
 */
public class Matrix {

    private final Object flatArray;
    private final int[] dimensions;
    private final BuiltinDataType builtinDataType;

    public Matrix(@Nullable Object array) {
        if (array == null) {
            this.flatArray = null;
            this.dimensions = new int[0];
            this.builtinDataType = null;
        } else {
            this.flatArray = ArrayUtil.flatten(array);
            this.dimensions = ArrayUtil.getDimensions(array);
            this.builtinDataType = BuiltinDataType.fromBackingClass(ArrayUtil.getType(flatArray));
        }

        assert flatArray == null || (dimensions.length > 1 && builtinDataType != null);
    }

    public Matrix(Object flatArray, int[] dimensions) {
        this.flatArray = flatArray;
        this.dimensions = dimensions;
        this.builtinDataType = BuiltinDataType.fromBackingClass(ArrayUtil.getType(flatArray));

        assert dimensions.length > 1 && builtinDataType != null;
    }

    public Matrix(Object flatArray, int[] dimensions, BuiltinDataType builtinDataType) {
        this.flatArray = flatArray;
        this.dimensions = dimensions;
        this.builtinDataType = builtinDataType;

        assert flatArray != null && dimensions.length > 1 && builtinDataType != null;
    }

    /**
     * Get the elements of this Matrix in a flattened array.
     *
     * @return the elements of this Matrix in a flattened array.
     */
    public @Nullable Object getElements() {
        return flatArray;
    }

    /**
     * Get the dimensions of this Matrix.
     * <p>
     * The returned array is zero length if this Matrix contains a null value.
     *
     * @return the dimensions of this Matrix.
     */
    public int[] getDimensions() {
        return dimensions;
    }

    /**
     * Get the {@link BuiltinDataType} of the elements of this Matrix.
     * <p>
     * Empty only if this Matrix contains a {@code null} value.
     *
     * @return the {@link BuiltinDataType} of the elements of this Matrix.
     */
    public Optional<BuiltinDataType> getBuiltinDataType() {
        return Optional.ofNullable(builtinDataType);
    }

    /**
     * @return {@code true} if this Matrix contains a null value.
     */
    public boolean isNull() {
        return flatArray == null;
    }

    /**
     * @return {@code true} if this Matrix contains a non-null value.
     */
    public boolean isNotNull() {
        return !isNull();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Matrix matrix = (Matrix) o;
        return Objects.deepEquals(flatArray, matrix.flatArray) &&
            Arrays.equals(dimensions, matrix.dimensions) &&
            builtinDataType == matrix.builtinDataType;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(flatArray, builtinDataType);
        result = 31 * result + Arrays.hashCode(dimensions);
        return result;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner(", ", Matrix.class.getSimpleName() + "{", "}")
            .add("builtinDataType=" + builtinDataType)
            .add("dimensions=" + Arrays.toString(dimensions));

        Class<?> clazz = ArrayUtil.getType(flatArray);

        if (clazz.isPrimitive()) {
            if (clazz == boolean.class) {
                joiner.add("flatArray=" + Arrays.toString((boolean[]) flatArray));
            } else if (clazz == byte.class) {
                joiner.add("flatArray=" + Arrays.toString((byte[]) flatArray));
            } else if (clazz == short.class) {
                joiner.add("flatArray=" + Arrays.toString((short[]) flatArray));
            } else if (clazz == int.class) {
                joiner.add("flatArray=" + Arrays.toString((int[]) flatArray));
            } else if (clazz == long.class) {
                joiner.add("flatArray=" + Arrays.toString((long[]) flatArray));
            } else if (clazz == float.class) {
                joiner.add("flatArray=" + Arrays.toString((float[]) flatArray));
            } else if (clazz == double.class) {
                joiner.add("flatArray=" + Arrays.toString((double[]) flatArray));
            } else {
                joiner.add("flatArray=" + flatArray);
            }
        } else {
            joiner.add("flatArray=" + Arrays.toString((Object[]) flatArray));
        }

        return joiner.toString();
    }

    /**
     * Create a Matrix containing a null value.
     *
     * @return a Matrix containing a null value.
     */
    public static Matrix ofNull() {
        return new Matrix(null);
    }

}
