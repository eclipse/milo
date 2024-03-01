/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.sdk.core.types.json;

import java.util.Arrays;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.Matrix;
import org.junit.jupiter.api.Test;

class JsonStructCodecTest {

    @Test
    void multiDimensionJsonArray() {
        // 2D JSON array
        String json2d = "[[1, 2], [3, 4]]";
        // 3D JSON array
        String json3d = "[[[1, 2], [3, 4]], [[5, 6], [7, 8]]]";
        // 4D JSON array
        String json4d = "[[[[1, 2], [3, 4]], [[5, 6], [7, 8]]], [[[9, 10], [11, 12]], [[13, 14], [15, 16]]]]";

        List<String> jsonArrays = List.of(json2d, json3d, json4d);

        List<BuiltinDataType> numericDataTypes = List.of(
            BuiltinDataType.Int16,
            BuiltinDataType.Int32,
            BuiltinDataType.Int64,
            BuiltinDataType.UInt16,
            BuiltinDataType.UInt32,
            BuiltinDataType.UInt64,
            BuiltinDataType.Float,
            BuiltinDataType.Double
        );

        // for each json array, for each datatype...
        for (String json : jsonArrays) {
            for (BuiltinDataType dataType : numericDataTypes) {
                JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
                Object[] flatArray = JsonStructCodec.encodeBuiltinDataTypeMatrixFlat(dataType, jsonArray);
                System.out.println("JSON: " + jsonArray);

                var matrix = new Matrix(flatArray, JsonStructCodec.getDimensions(jsonArray), dataType);
                System.out.println("Matrix: " + matrix);
                System.out.println("NestedArrayValue: " + Arrays.deepToString((Object[]) matrix.nestedArrayValue()));
            }
        }
    }

}
