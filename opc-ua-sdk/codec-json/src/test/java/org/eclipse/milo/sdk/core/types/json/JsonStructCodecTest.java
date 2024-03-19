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
import java.util.UUID;
import java.util.stream.Stream;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.NodeIds;
import org.eclipse.milo.opcua.stack.core.encoding.DefaultEncodingContext;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.Matrix;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.eclipse.milo.opcua.stack.core.types.enumerated.ApplicationType;
import org.eclipse.milo.opcua.stack.core.types.structured.XVType;
import org.eclipse.milo.opcua.test.types.ConcreteTestType;
import org.eclipse.milo.opcua.test.types.ConcreteTestTypeEx;
import org.eclipse.milo.opcua.test.types.StructWithAbstractArrayFields;
import org.eclipse.milo.opcua.test.types.StructWithAbstractScalarFields;
import org.eclipse.milo.opcua.test.types.StructWithArrayFields;
import org.eclipse.milo.opcua.test.types.StructWithArrayFieldsEx;
import org.eclipse.milo.opcua.test.types.StructWithOptionalArrayFields;
import org.eclipse.milo.opcua.test.types.StructWithOptionalScalarFields;
import org.eclipse.milo.opcua.test.types.StructWithScalarFields;
import org.eclipse.milo.opcua.test.types.StructWithScalarFieldsEx;
import org.eclipse.milo.opcua.test.types.TestEnumType;
import org.eclipse.milo.opcua.test.types.UnionOfArray;
import org.eclipse.milo.opcua.test.types.UnionOfScalar;
import org.eclipse.milo.sdk.core.types.json.util.DynamicEncodingContext;
import org.eclipse.milo.sdk.core.types.json.util.StaticEncodingContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonStructCodecTest {

    @Test
    void structWithScalarFields() {
        var struct = new StructWithScalarFields(
            false,
            (byte) 0,
            ubyte(0),
            (short) 0,
            ushort(0),
            0,
            uint(0),
            0L,
            ulong(0L),
            0.0f,
            0.0d,
            "",
            DateTime.MIN_DATE_TIME,
            UUID.fromString("00000000-0000-0000-0000-000000000000"),
            ByteString.of(new byte[]{0}),
            XmlElement.of(""),
            new NodeId(0, 0),
            new ExpandedNodeId(ushort(0), null, uint(0)),
            StatusCode.GOOD,
            new QualifiedName(0, ""),
            LocalizedText.NULL_VALUE,
            new DataValue(new Variant(0)),
            Variant.ofInt32(0)
        );

        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), struct);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @Test
    void structWithScalarFieldsEx() {
        var struct = new StructWithScalarFieldsEx(
            false,
            (byte) 0,
            ubyte(0),
            (short) 0,
            ushort(0),
            0,
            uint(0),
            0L,
            ulong(0L),
            0.0f,
            0.0d,
            "",
            DateTime.MIN_DATE_TIME,
            UUID.fromString("00000000-0000-0000-0000-000000000000"),
            ByteString.of(new byte[]{0}),
            XmlElement.of(""),
            new NodeId(0, 0),
            new ExpandedNodeId(ushort(0), null, uint(0)),
            StatusCode.GOOD,
            new QualifiedName(0, ""),
            LocalizedText.NULL_VALUE,
            new DataValue(new Variant(0)),
            Variant.ofInt32(0),
            0.0,
            ApplicationType.Server,
            TestEnumType.A,
            new XVType(0.0d, 0.0f),
            new ConcreteTestType((short) 0, 0.0, "", false),
            UnionOfScalar.ofBoolean(false),
            UnionOfArray.ofBoolean(new Boolean[]{false, false})
        );

        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), struct);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());

        System.out.println(decoded.getJsonObject());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @Test
    void structWithArrayFields() {
        var struct = new StructWithArrayFields(
            new Boolean[]{false, false},
            new Byte[]{0, 0},
            new UByte[]{ubyte(0), ubyte(0)},
            new Short[]{0, 0},
            new UShort[]{ushort(0), ushort(0)},
            new Integer[]{0, 0},
            new UInteger[]{uint(0), uint(0)},
            new Long[]{0L, 0L},
            new ULong[]{ulong(0L), ulong(0L)},
            new Float[]{0.0f, 0.0f},
            new Double[]{0.0d, 0.0d},
            new String[]{"", ""},
            new DateTime[]{DateTime.MIN_DATE_TIME, DateTime.MIN_DATE_TIME},
            new UUID[]{
                UUID.fromString("00000000-0000-0000-0000-000000000000"),
                UUID.fromString("00000000-0000-0000-0000-000000000000")},
            new ByteString[]{ByteString.of(new byte[]{0}), ByteString.of(new byte[]{0})},
            new XmlElement[]{XmlElement.of(""), XmlElement.of("")},
            new NodeId[]{new NodeId(0, 0), new NodeId(0, 0)},
            new ExpandedNodeId[]{
                new ExpandedNodeId(ushort(0), null, uint(0)),
                new ExpandedNodeId(ushort(0), null, uint(0))},
            new StatusCode[]{StatusCode.GOOD, StatusCode.GOOD},
            new QualifiedName[]{
                new QualifiedName(0, ""),
                new QualifiedName(0, "")},
            new LocalizedText[]{LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE},
            new DataValue[]{new DataValue(new Variant(0)), new DataValue(new Variant(0))},
            new Variant[]{Variant.ofInt32(0), Variant.ofInt32(0)}
        );

        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), struct);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @Test
    void structWithArrayFieldsEx() {
        var struct = new StructWithArrayFieldsEx(
            new Boolean[]{false, false},
            new Byte[]{0, 0},
            new UByte[]{ubyte(0), ubyte(0)},
            new Short[]{0, 0},
            new UShort[]{ushort(0), ushort(0)},
            new Integer[]{0, 0},
            new UInteger[]{uint(0), uint(0)},
            new Long[]{0L, 0L},
            new ULong[]{ulong(0L), ulong(0L)},
            new Float[]{0.0f, 0.0f},
            new Double[]{0.0d, 0.0d},
            new String[]{"", ""},
            new DateTime[]{DateTime.MIN_DATE_TIME, DateTime.MIN_DATE_TIME},
            new UUID[]{
                UUID.fromString("00000000-0000-0000-0000-000000000000"),
                UUID.fromString("00000000-0000-0000-0000-000000000000")},
            new ByteString[]{ByteString.of(new byte[]{0}), ByteString.of(new byte[]{0})},
            new XmlElement[]{XmlElement.of(""), XmlElement.of("")},
            new NodeId[]{new NodeId(0, 0), new NodeId(0, 0)},
            new ExpandedNodeId[]{
                new ExpandedNodeId(ushort(0), null, uint(0)),
                new ExpandedNodeId(ushort(0), null, uint(0))},
            new StatusCode[]{StatusCode.GOOD, StatusCode.GOOD},
            new QualifiedName[]{
                new QualifiedName(0, ""),
                new QualifiedName(0, "")},
            new LocalizedText[]{LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE},
            new DataValue[]{new DataValue(new Variant(0)), new DataValue(new Variant(0))},
            new Variant[]{Variant.ofInt32(0), Variant.ofInt32(0)},
            new Double[]{0.0, 0.0}
        );

        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), struct);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @Test
    void structWithAbstractScalarFields() {
        var struct = new StructWithAbstractScalarFields(
            0,
            new ConcreteTestType((short) 0, 0.0, "", false),
            new ConcreteTestTypeEx((short) 0, 0.0, "", false, uint(0))
        );

        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), struct);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @Test
    void structWithAbstractArrayFields() {
        var struct = new StructWithAbstractArrayFields(
            new Integer[]{0, 0},
            new ConcreteTestType[]{
                new ConcreteTestType((short) 0, 0.0, "", false),
                new ConcreteTestType((short) 0, 0.0, "", false)},
            new ConcreteTestTypeEx[]{
                new ConcreteTestTypeEx((short) 0, 0.0, "", false, uint(0)),
                new ConcreteTestTypeEx((short) 0, 0.0, "", false, uint(0))}
        );

        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), struct);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @ParameterizedTest
    @MethodSource("structWithOptionalScalarFieldsProvider")
    void structWithOptionalScalarFields(StructWithOptionalScalarFields struct) {
        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), struct);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @ParameterizedTest
    @MethodSource("structWithOptionalArrayFieldsProvider")
    void structWithOptionalArrayFields(StructWithOptionalArrayFields struct) {
        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), struct);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    private static Stream<Arguments> structWithOptionalScalarFieldsProvider() {
        return Stream.of(
            Arguments.of(
                new StructWithOptionalScalarFields(
                    "",
                    "",
                    0,
                    0,
                    0.0,
                    0.0,
                    new ConcreteTestType((short) 0, 0.0, "", false),
                    new ConcreteTestType((short) 0, 0.0, "", false)
                )
            ),
            Arguments.of(
                new StructWithOptionalScalarFields(
                    "",
                    null,
                    0,
                    null,
                    0.0,
                    null,
                    new ConcreteTestType((short) 0, 0.0, "", false),
                    null
                )
            )
        );
    }

    private static Stream<Arguments> structWithOptionalArrayFieldsProvider() {
        return Stream.of(
            Arguments.of(
                new StructWithOptionalArrayFields(
                    new Integer[]{0, 0},
                    new Integer[]{0, 0},
                    new String[]{"", ""},
                    new String[]{"", ""},
                    new Double[]{0.0, 0.0},
                    new Double[]{0.0, 0.0},
                    new ConcreteTestType[]{
                        new ConcreteTestType((short) 0, 0.0, "", false),
                        new ConcreteTestType((short) 0, 0.0, "", false)
                    },
                    new ConcreteTestType[]{
                        new ConcreteTestType((short) 0, 0.0, "", false),
                        new ConcreteTestType((short) 0, 0.0, "", false)
                    }
                )
            ),
            Arguments.of(
                new StructWithOptionalArrayFields(
                    new Integer[]{0, 0},
                    null,
                    new String[]{"", ""},
                    null,
                    new Double[]{0.0, 0.0},
                    null,
                    new ConcreteTestType[]{
                        new ConcreteTestType((short) 0, 0.0, "", false),
                        new ConcreteTestType((short) 0, 0.0, "", false)
                    },
                    null
                )
            )
        );
    }

    @Test
    void unionOfScalar() {
        var union = UnionOfScalar.ofByte(ubyte(0));

        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), union);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @Test
    void unionOfArray() {
        var union = UnionOfArray.ofBoolean(new Boolean[]{false, false});

        var encoded1 = ExtensionObject.encode(new StaticEncodingContext(), union);
        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());
        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @Test
    void encodeDecodeStaticType() {
        var xv = new XVType(1.0d, 2.0f);
        var encoded1 = ExtensionObject.encode(DefaultEncodingContext.INSTANCE, xv);

        JsonStruct decoded = (JsonStruct) encoded1.decode(new DynamicEncodingContext());

        assertEquals(
            "{\"X\":1.0,\"Value\":2.0,\"__metadata\":{\"dataTypeId\":\"ns=0;i=12080\"}}",
            decoded.getJsonObject().toString()
        );

        var encoded2 = ExtensionObject.encode(new DynamicEncodingContext(), decoded);

        assertEquals(encoded1, encoded2);
    }

    @Test
    void encodeType() {
        var jsonStruct = new JsonStruct(
            DynamicEncodingContext.XV_DATA_TYPE,
            JsonParser.parseString("{\"X\": 1.0, \"Value\": 2.0}").getAsJsonObject()
        );

        var encoded = ExtensionObject.encode(new DynamicEncodingContext(), jsonStruct);
        var expected = ExtensionObject.encode(DefaultEncodingContext.INSTANCE, new XVType(1.0d, 2.0f));

        assertEquals(expected, encoded);
    }

    @Test
    void decodeType() {
        var encoded = ExtensionObject.encode(DefaultEncodingContext.INSTANCE, new XVType(1.0d, 2.0f));

        var decoded = encoded.decode(new DynamicEncodingContext());
        var expected = new JsonStruct(
            DynamicEncodingContext.XV_DATA_TYPE,
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

        var context = new DynamicEncodingContext();

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
        var context = new DynamicEncodingContext();

        var matrix2d = new Matrix(
            new Object[]{
                ExtensionObject.encode(
                    context,
                    new JsonStruct(DynamicEncodingContext.XV_DATA_TYPE, JsonParser.parseString("{\"X\": 1.0, \"Value\": 2.0}").getAsJsonObject())),
                ExtensionObject.encode(
                    context,
                    new JsonStruct(DynamicEncodingContext.XV_DATA_TYPE, JsonParser.parseString("{\"X\": 3.0, \"Value\": 4.0}").getAsJsonObject())),
                ExtensionObject.encode(
                    context,
                    new JsonStruct(DynamicEncodingContext.XV_DATA_TYPE, JsonParser.parseString("{\"X\": 5.0, \"Value\": 6.0}").getAsJsonObject())),
                ExtensionObject.encode(
                    context,
                    new JsonStruct(DynamicEncodingContext.XV_DATA_TYPE, JsonParser.parseString("{\"X\": 7.0, \"Value\": 8.0}").getAsJsonObject())
                )
            },
            new int[]{2, 2},
            BuiltinDataType.ExtensionObject
        );

        for (Matrix matrix : List.of(matrix2d)) {
            JsonElement jsonArray = JsonStructCodec.decodeStructMatrix(new DynamicEncodingContext(), matrix, true);

            System.out.println("Matrix: " + matrix);
            System.out.println("JSON: " + jsonArray);
        }
    }

}
