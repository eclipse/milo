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
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.encoding.DefaultEncodingContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.Matrix;
import org.eclipse.milo.opcua.stack.core.types.structured.XVType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonStructCodecTest {

    @Test
    void encodeDecodeStaticType() {
        var xv = new XVType(1.0d, 2.0f);
        var encoded1 = ExtensionObject.encode(DefaultEncodingContext.INSTANCE, xv);

        JsonStruct decoded = (JsonStruct) encoded1.decode(new TestEncodingContext());

        assertEquals(
            "{\"X\":1.0,\"Value\":2.0,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}",
            decoded.getJsonObject().toString()
        );

        var encoded2 = ExtensionObject.encode(new TestEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @Test
    void encodeType() {
        var jsonStruct = new JsonStruct(
            TestEncodingContext.XV_DATA_TYPE,
            JsonParser.parseString("{\"X\": 1.0, \"Value\": 2.0}").getAsJsonObject()
        );

        var encoded = ExtensionObject.encode(new TestEncodingContext(), jsonStruct);
        var expected = ExtensionObject.encode(DefaultEncodingContext.INSTANCE, new XVType(1.0d, 2.0f));

        assertEquals(expected, encoded);
    }

    @Test
    void decodeType() {
        var encoded = ExtensionObject.encode(DefaultEncodingContext.INSTANCE, new XVType(1.0d, 2.0f));

        var decoded = encoded.decode(new TestEncodingContext());
        var expected = new JsonStruct(
            TestEncodingContext.XV_DATA_TYPE,
            JsonParser.parseString("{\"X\": 1.0, \"Value\": 2.0,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}").getAsJsonObject()
        );

        assertEquals(expected, decoded);
    }

    @Test
    void encodeBuiltinDataTypeMatrix() {
        String json2d = "[[1, 2], [3, 4]]";
        String json3d = "[[[1, 2], [3, 4]], [[5, 6], [7, 8]]]";
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
                Object[] flatArray = JsonStructCodec.encodeBuiltinDataTypeMatrix(dataType, jsonArray);
                System.out.println("JSON: " + jsonArray);

                var matrix = new Matrix(flatArray, JsonStructCodec.getDimensions(jsonArray), dataType);
                System.out.println("Matrix: " + matrix);
                System.out.println("NestedArrayValue: " + Arrays.deepToString((Object[]) matrix.nestedArrayValue()));
            }
        }
    }

    @Test
    void encodeEnumMatrix() {
        String json2d = "[[1, 2], [3, 4]]";
        String json3d = "[[[1, 2], [3, 4]], [[5, 6], [7, 8]]]";
        String json4d = "[[[[1, 2], [3, 4]], [[5, 6], [7, 8]]], [[[9, 10], [11, 12]], [[13, 14], [15, 16]]]]";

        for (String json : List.of(json2d, json3d, json4d)) {
            JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
            Object[] flatArray = JsonStructCodec.encodeEnumMatrix(NodeIds.ApplicationType.expanded(), jsonArray);
            System.out.println("JSON: " + jsonArray);

            var matrix = new Matrix(flatArray, JsonStructCodec.getDimensions(jsonArray), BuiltinDataType.Int32);
            System.out.println("Matrix: " + matrix);
            System.out.println("NestedArrayValue: " + Arrays.deepToString((Object[]) matrix.nestedArrayValue()));
        }
    }

    @Test
    void encodeStructMatrix() {
        String json2d = "[[{\"X\": 1, \"Value\": 2,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}, {\"X\": 3, \"Value\": 4,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}], [{\"X\": 5, \"Value\": 6,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}, {\"X\": 7, \"Value\": 8,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}]]";
        String json3d = "[[[{\"X\": 1, \"Value\": 2,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}, {\"X\": 3, \"Value\": 4,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}], [{\"X\": 5, \"Value\": 6,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}, {\"X\": 7, \"Value\": 8,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}]], [[{\"X\": 9, \"Value\": 10,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}, {\"X\": 11, \"Value\": 12,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}, {\"X\": 13, \"Value\": 14,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}, {\"X\": 15, \"Value\": 16,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}]]]";

        var context = new TestEncodingContext();

        for (String json : List.of(json2d, json3d)) {
            JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
            Object[] flatArray = JsonStructCodec.encodeStructMatrix(context, context.dataTypeTree, jsonArray, false);
            System.out.println("JSON: " + jsonArray);

            var matrix = new Matrix(flatArray, JsonStructCodec.getDimensions(jsonArray), BuiltinDataType.ExtensionObject);
            System.out.println("Matrix: " + matrix);
            System.out.println("NestedArrayValue: " + Arrays.deepToString((Object[]) matrix.nestedArrayValue()));
        }
    }

    @Test
    void decodeBuiltinDataTypeMatrix_Int32() {
        var matrix2d = new Matrix(new Object[]{1, 2, 3, 4}, new int[]{2, 2}, BuiltinDataType.Int32);
        var matrix3d = new Matrix(new Object[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{2, 2, 2}, BuiltinDataType.Int32);
        var matrix4d = new Matrix(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, new int[]{2, 2, 2, 2}, BuiltinDataType.Int32);

        for (Matrix matrix : List.of(matrix2d, matrix3d, matrix4d)) {
            JsonElement jsonArray = JsonStructCodec.decodeBuiltinDataTypeMatrix(matrix);

            System.out.println("Matrix: " + matrix);
            System.out.println("JSON: " + jsonArray);
        }
    }

    @Test
    void decodeBuiltinDataTypeMatrix_Float() {
        var matrix2d = new Matrix(new Object[]{1.0f, 2.0f, 3.0f, 4.0f}, new int[]{2, 2}, BuiltinDataType.Float);
        var matrix3d = new Matrix(new Object[]{1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f}, new int[]{2, 2, 2}, BuiltinDataType.Float);
        var matrix4d = new Matrix(new Object[]{1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f, 9.0f, 10.0f, 11.0f, 12.0f, 13.0f, 14.0f, 15.0f, 16.0f}, new int[]{2, 2, 2, 2}, BuiltinDataType.Float);

        for (Matrix matrix : List.of(matrix2d, matrix3d, matrix4d)) {
            JsonElement jsonArray = JsonStructCodec.decodeBuiltinDataTypeMatrix(matrix);

            System.out.println("Matrix: " + matrix);
            System.out.println("JSON: " + jsonArray);
        }
    }

    @Test
    void decodeBuiltinDatatypeMatrix_String() {
        var matrix2d = new Matrix(new Object[]{"a", "b", "c", "d"}, new int[]{2, 2}, BuiltinDataType.String);
        var matrix3d = new Matrix(new Object[]{"a", "b", "c", "d", "e", "f", "g", "h"}, new int[]{2, 2, 2}, BuiltinDataType.String);
        var matrix4d = new Matrix(new Object[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p"}, new int[]{2, 2, 2, 2}, BuiltinDataType.String);

        for (Matrix matrix : List.of(matrix2d, matrix3d, matrix4d)) {
            JsonElement jsonArray = JsonStructCodec.decodeBuiltinDataTypeMatrix(matrix);

            System.out.println("Matrix: " + matrix);
            System.out.println("JSON: " + jsonArray);
        }
    }

    @Test
    void decodeEnumMatrix() {
        var matrix2d = new Matrix(new Object[]{1, 2, 3, 4}, new int[]{2, 2}, BuiltinDataType.Int32);
        var matrix3d = new Matrix(new Object[]{1, 2, 3, 4, 5, 6, 7, 8}, new int[]{2, 2, 2}, BuiltinDataType.Int32);
        var matrix4d = new Matrix(new Object[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, new int[]{2, 2, 2, 2}, BuiltinDataType.Int32);

        for (Matrix matrix : List.of(matrix2d, matrix3d, matrix4d)) {
            JsonElement jsonArray = JsonStructCodec.decodeEnumMatrix(matrix);

            System.out.println("Matrix: " + matrix);
            System.out.println("JSON: " + jsonArray);
        }
    }

    @Test
    void decodeStructMatrix() {
        var context = new TestEncodingContext();

        var matrix2d = new Matrix(
            new Object[]{
                ExtensionObject.encode(
                    context,
                    new JsonStruct(TestEncodingContext.XV_DATA_TYPE, JsonParser.parseString("{\"X\": 1.0, \"Value\": 2.0}").getAsJsonObject())),
                ExtensionObject.encode(
                    context,
                    new JsonStruct(TestEncodingContext.XV_DATA_TYPE, JsonParser.parseString("{\"X\": 3.0, \"Value\": 4.0}").getAsJsonObject())),
                ExtensionObject.encode(
                    context,
                    new JsonStruct(TestEncodingContext.XV_DATA_TYPE, JsonParser.parseString("{\"X\": 5.0, \"Value\": 6.0}").getAsJsonObject())),
                ExtensionObject.encode(
                    context,
                    new JsonStruct(TestEncodingContext.XV_DATA_TYPE, JsonParser.parseString("{\"X\": 7.0, \"Value\": 8.0}").getAsJsonObject())
                )
            },
            new int[]{2, 2},
            BuiltinDataType.ExtensionObject
        );

        for (Matrix matrix : List.of(matrix2d)) {
            JsonElement jsonArray = JsonStructCodec.decodeStructMatrix(new TestEncodingContext(), matrix, true);

            System.out.println("Matrix: " + matrix);
            System.out.println("JSON: " + jsonArray);
        }
    }

}
