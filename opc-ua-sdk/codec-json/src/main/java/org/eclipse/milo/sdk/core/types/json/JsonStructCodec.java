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

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.eclipse.milo.opcua.sdk.core.typetree.DataType;
import org.eclipse.milo.opcua.sdk.core.typetree.DataTypeTree;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;
import org.eclipse.milo.opcua.stack.core.types.UaEnumeratedType;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.StructureType;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureField;
import org.eclipse.milo.opcua.stack.core.util.Lazy;
import org.jetbrains.annotations.Nullable;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElse;

public class JsonStructCodec extends GenericDataTypeCodec<JsonStruct> {

    private final Lazy<Map<StructureField, Object>> hints = new Lazy<>();

    private final StructureDefinition definition;

    private final DataType dataType;
    private final DataTypeTree dataTypeTree;

    public JsonStructCodec(DataType dataType, DataTypeTree dataTypeTree) {
        this.dataType = dataType;
        this.dataTypeTree = dataTypeTree;

        definition = (StructureDefinition) dataType.getDataTypeDefinition();
    }

    @Override
    public Class<JsonStruct> getType() {
        return JsonStruct.class;
    }

    //region Decoding

    @Override
    public JsonStruct decodeType(EncodingContext context, UaDecoder decoder) throws UaSerializationException {
        switch (definition.getStructureType()) {
            case Structure:
            case StructureWithOptionalFields:
            case StructureWithSubtypedValues:
                return decodeStruct(context, decoder);

            case Union:
            case UnionWithSubtypedValues:
                return decodeUnion(context, decoder);

            default:
                throw new IllegalArgumentException(
                    "unsupported structure type: " + definition.getStructureType());
        }
    }

    private JsonStruct decodeStruct(EncodingContext context, UaDecoder decoder) throws UaSerializationException {
        var jsonObject = new JsonObject();

        var switchField = 0xFFFFFFFFL;
        if (definition.getStructureType() == StructureType.StructureWithOptionalFields) {
            switchField = decoder.decodeUInt32("SwitchField").longValue();
        }

        StructureField[] fields = requireNonNullElse(definition.getFields(), new StructureField[0]);

        for (int i = 0; i < fields.length; i++) {
            StructureField field = fields[i];
            if (!field.getIsOptional() || (switchField >>> i & 1L) == 1L) {
                JsonElement value = decodeFieldValue(decoder, field);

                jsonObject.add(requireNonNull(field.getName()), value);
            }
        }

        return new JsonStruct(dataType, new JsonObject());
    }

    private JsonStruct decodeUnion(EncodingContext context, UaDecoder decoder) throws UaSerializationException {
        int switchField = decoder.decodeUInt32("SwitchField").intValue();
        StructureField[] fields = requireNonNullElse(definition.getFields(), new StructureField[0]);

        if (switchField == 0) {
            return new JsonStruct(dataType, new JsonObject());
        } else if (switchField <= fields.length) {
            StructureField field = fields[switchField - 1];
            JsonElement value = decodeFieldValue(decoder, field);

            var jsonObject = new JsonObject();
            jsonObject.add(requireNonNull(field.getName()), value);

            return new JsonStruct(dataType, jsonObject);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "invalid Union SwitchField value: " + switchField
            );
        }
    }

    private JsonElement decodeFieldValue(UaDecoder decoder, StructureField field) {
        String fieldName = requireNonNull(field.getName());
        NodeId dataTypeId = field.getDataType();

        if (field.getValueRank() == -1) {
            Object hint = getHint(field);
            if (hint instanceof BuiltinDataType) {
                return decodeBuiltinDataType(decoder, fieldName, (BuiltinDataType) hint);
            } else if (hint instanceof EnumHint) {
                int enumValue = decoder.decodeEnum(fieldName);

                return new JsonPrimitive(enumValue);
            } else if (hint instanceof StructHint) {
                JsonStruct structValue = (JsonStruct) decoder.decodeStruct(fieldName, dataTypeId);

                return structValue.getJsonObject();
            } else {
                throw new IllegalArgumentException("hint: " + hint);
            }
        } else if (field.getValueRank() == 1) {
            Object hint = getHint(field);
            if (hint instanceof BuiltinDataType) {
                return decodeBuiltinDataTypeArray(decoder, fieldName, (BuiltinDataType) hint);
            } else if (hint instanceof EnumHint) {
                var array = new JsonArray();
                for (int value : decoder.decodeEnumArray(fieldName)) {
                    array.add(value);
                }
                return array;
            } else if (hint instanceof StructHint) {
                var array = new JsonArray();
                for (Object value : decoder.decodeStructArray(fieldName, dataTypeId)) {
                    array.add(((JsonStruct) value).getJsonObject());
                }
                return array;
            } else {
                throw new IllegalArgumentException("hint: " + hint);
            }
        } else if (field.getValueRank() > 1) {
            Object hint = getHint(field);
            if (hint instanceof BuiltinDataType) {
                Matrix matrix = decoder.decodeMatrix(fieldName, (BuiltinDataType) hint);
                return JsonNull.INSTANCE; // TODO
            } else if (hint instanceof EnumHint) {
                Matrix matrix = decoder.decodeEnumMatrix(fieldName);
                return JsonNull.INSTANCE; // TODO
            } else if (hint instanceof StructHint) {
                Matrix matrix = decoder.decodeStructMatrix(fieldName, dataTypeId);
                return JsonNull.INSTANCE; // TODO
            } else {
                throw new IllegalArgumentException("hint: " + hint);
            }
        } else {
            throw new IllegalArgumentException("unsupported value rank: " + field.getValueRank());
        }
    }

    private JsonElement decodeBuiltinDataType(UaDecoder decoder, String fieldName, BuiltinDataType dataType) {
        switch (dataType) {
            case Boolean:
                return JsonConversions.fromBoolean(decoder.decodeBoolean(fieldName));
            case SByte:
                return JsonConversions.fromSByte(decoder.decodeSByte(fieldName));
            case Byte:
                return JsonConversions.fromByte(decoder.decodeByte(fieldName));
            case Int16:
                return JsonConversions.fromInt16(decoder.decodeInt16(fieldName));
            case UInt16:
                return JsonConversions.fromUInt16(decoder.decodeUInt16(fieldName));
            case Int32:
                return JsonConversions.fromInt32(decoder.decodeInt32(fieldName));
            case UInt32:
                return JsonConversions.fromUInt32(decoder.decodeUInt32(fieldName));
            case Int64:
                return JsonConversions.fromInt64(decoder.decodeInt64(fieldName));
            case UInt64:
                return JsonConversions.fromUInt64(decoder.decodeUInt64(fieldName));
            case Float:
                return JsonConversions.fromFloat(decoder.decodeFloat(fieldName));
            case Double:
                return JsonConversions.fromDouble(decoder.decodeDouble(fieldName));
            case String:
                return JsonConversions.fromString(decoder.decodeString(fieldName));
            case DateTime:
                return JsonConversions.fromDateTime(decoder.decodeDateTime(fieldName));
            case Guid:
                return JsonConversions.fromGuid(decoder.decodeGuid(fieldName));
            case ByteString:
                return JsonConversions.fromByteString(decoder.decodeByteString(fieldName));
            case XmlElement:
                return JsonConversions.fromXmlElement(decoder.decodeXmlElement(fieldName));
            case NodeId:
                return JsonConversions.fromNodeId(decoder.decodeNodeId(fieldName));
            case ExpandedNodeId:
                return JsonConversions.fromExpandedNodeId(decoder.decodeExpandedNodeId(fieldName));
            case StatusCode:
                return JsonConversions.fromStatusCode(decoder.decodeStatusCode(fieldName));
            case QualifiedName:
                return JsonConversions.fromQualifiedName(decoder.decodeQualifiedName(fieldName));
            case LocalizedText:
                return JsonConversions.fromLocalizedText(decoder.decodeLocalizedText(fieldName));
            case ExtensionObject:
                return JsonConversions.fromExtensionObject(decoder.decodeExtensionObject(fieldName));
            case DataValue:
                return JsonConversions.fromDataValue(decoder.decodeDataValue(fieldName));
            case Variant:
                return JsonConversions.fromVariant(decoder.decodeVariant(fieldName));

            case DiagnosticInfo:
            default:
                return JsonNull.INSTANCE;
        }
    }

    private JsonElement decodeBuiltinDataTypeArray(UaDecoder decoder, String fieldName, BuiltinDataType dataType) {
        switch (dataType) {
            case Boolean: {
                var array = new JsonArray();
                for (boolean value : decoder.decodeBooleanArray(fieldName)) {
                    array.add(JsonConversions.fromBoolean(value));
                }
                return array;
            }
            case SByte: {
                var array = new JsonArray();
                for (byte value : decoder.decodeSByteArray(fieldName)) {
                    array.add(JsonConversions.fromSByte(value));
                }
                return array;
            }
            case Byte: {
                var array = new JsonArray();
                for (UByte value : decoder.decodeByteArray(fieldName)) {
                    array.add(JsonConversions.fromByte(value));
                }
                return array;
            }
            case Int16: {
                var array = new JsonArray();
                for (short value : decoder.decodeInt16Array(fieldName)) {
                    array.add(JsonConversions.fromInt16(value));
                }
                return array;
            }
            case UInt16: {
                var array = new JsonArray();
                for (UShort value : decoder.decodeUInt16Array(fieldName)) {
                    array.add(JsonConversions.fromUInt16(value));
                }
                return array;
            }
            case Int32: {
                var array = new JsonArray();
                for (int value : decoder.decodeInt32Array(fieldName)) {
                    array.add(JsonConversions.fromInt32(value));
                }
                return array;
            }
            case UInt32: {
                var array = new JsonArray();
                for (UInteger value : decoder.decodeUInt32Array(fieldName)) {
                    array.add(JsonConversions.fromUInt32(value));
                }
                return array;
            }
            case Int64: {
                var array = new JsonArray();
                for (long value : decoder.decodeInt64Array(fieldName)) {
                    array.add(JsonConversions.fromInt64(value));
                }
                return array;
            }
            case UInt64: {
                var array = new JsonArray();
                for (ULong value : decoder.decodeUInt64Array(fieldName)) {
                    array.add(JsonConversions.fromUInt64(value));
                }
                return array;
            }
            case Float: {
                var array = new JsonArray();
                for (float value : decoder.decodeFloatArray(fieldName)) {
                    array.add(JsonConversions.fromFloat(value));
                }
                return array;
            }
            case Double: {
                var array = new JsonArray();
                for (double value : decoder.decodeDoubleArray(fieldName)) {
                    array.add(JsonConversions.fromDouble(value));
                }
                return array;
            }
            case String: {
                var array = new JsonArray();
                for (String value : decoder.decodeStringArray(fieldName)) {
                    array.add(JsonConversions.fromString(value));
                }
                return array;
            }
            case DateTime: {
                var array = new JsonArray();
                for (DateTime value : decoder.decodeDateTimeArray(fieldName)) {
                    array.add(JsonConversions.fromDateTime(value));
                }
                return array;
            }
            case Guid: {
                var array = new JsonArray();
                for (UUID value : decoder.decodeGuidArray(fieldName)) {
                    array.add(JsonConversions.fromGuid(value));
                }
                return array;
            }
            case ByteString: {
                var array = new JsonArray();
                for (ByteString value : decoder.decodeByteStringArray(fieldName)) {
                    array.add(JsonConversions.fromByteString(value));
                }
                return array;
            }
            case XmlElement: {
                var array = new JsonArray();
                for (XmlElement value : decoder.decodeXmlElementArray(fieldName)) {
                    array.add(JsonConversions.fromXmlElement(value));
                }
                return array;
            }
            case NodeId: {
                var array = new JsonArray();
                for (NodeId value : decoder.decodeNodeIdArray(fieldName)) {
                    array.add(JsonConversions.fromNodeId(value));
                }
                return array;
            }
            case ExpandedNodeId: {
                var array = new JsonArray();
                for (ExpandedNodeId value : decoder.decodeExpandedNodeIdArray(fieldName)) {
                    array.add(JsonConversions.fromExpandedNodeId(value));
                }
                return array;
            }
            case StatusCode: {
                var array = new JsonArray();
                for (StatusCode value : decoder.decodeStatusCodeArray(fieldName)) {
                    array.add(JsonConversions.fromStatusCode(value));
                }
                return array;
            }
            case QualifiedName: {
                var array = new JsonArray();
                for (QualifiedName value : decoder.decodeQualifiedNameArray(fieldName)) {
                    array.add(JsonConversions.fromQualifiedName(value));
                }
                return array;
            }
            case LocalizedText: {
                var array = new JsonArray();
                for (LocalizedText value : decoder.decodeLocalizedTextArray(fieldName)) {
                    array.add(JsonConversions.fromLocalizedText(value));
                }
                return array;
            }
            case ExtensionObject: {
                var array = new JsonArray();
                for (ExtensionObject value : decoder.decodeExtensionObjectArray(fieldName)) {
                    array.add(JsonConversions.fromExtensionObject(value));
                }
                return array;
            }
            case DataValue: {
                var array = new JsonArray();
                for (DataValue value : decoder.decodeDataValueArray(fieldName)) {
                    array.add(JsonConversions.fromDataValue(value));
                }
                return array;
            }
            case Variant:
                var array = new JsonArray();
                for (Variant value : decoder.decodeVariantArray(fieldName)) {
                    array.add(JsonConversions.fromVariant(value));
                }
                return array;

            case DiagnosticInfo:
            default:
                return JsonNull.INSTANCE;
        }
    }

    //endregion

    //region Encoding

    @Override
    public void encodeType(EncodingContext context, UaEncoder encoder, JsonStruct value) throws UaSerializationException {
        switch (definition.getStructureType()) {
            case Structure:
            case StructureWithOptionalFields:
            case StructureWithSubtypedValues:
                encodeStruct(context, encoder, value);
                break;

            case Union:
            case UnionWithSubtypedValues:
                encodeUnion(context, encoder, value);
                break;

            default:
                throw new IllegalArgumentException(
                    "unsupported structure type: " + definition.getStructureType());
        }
    }

    private void encodeStruct(EncodingContext context, UaEncoder encoder, JsonStruct value) {
        // TODO
    }

    private void encodeUnion(EncodingContext context, UaEncoder encoder, JsonStruct value) {
        if (value.getJsonObject().size() == 0) {
            encoder.encodeUInt32("SwitchValue", UInteger.valueOf(0));
        } else {
            StructureField[] fields = requireNonNullElse(definition.getFields(), new StructureField[0]);

            for (int i = 0; i < fields.length; i++) {
                StructureField field = fields[i];

                if (value.getJsonObject().has(requireNonNull(field.getName()))) {
                    encoder.encodeUInt32("SwitchValue", UInteger.valueOf(i + 1));
                    JsonElement fieldValue = value.getJsonObject().get(requireNonNull(field.getName()));
                    encodeFieldValue(encoder, field, fieldValue);

                    // Return as soon as a field has been encoded.
                    // Unions are only one field, indicated by SwitchValue.
                    return;
                }
            }

            throw new UaSerializationException(StatusCodes.Bad_EncodingError, "no Union value found");
        }
    }

    private void encodeFieldValue(UaEncoder encoder, StructureField field, JsonElement value) {
        String fieldName = requireNonNull(field.getName());
        NodeId dataTypeId = field.getDataType();

        if (field.getValueRank() == -1) {
            Object hint = getHint(field);
            if (hint instanceof BuiltinDataType) {
                encodeBuiltinDataType(encoder, fieldName, (BuiltinDataType) hint, value);
            } else if (hint instanceof EnumHint) {
                // TODO JsonEnum should be a public class like DynamicEnum is, and then we just do a cast here.
                //  That means the decoder needs to produce JsonEnum as well, when appropriate.
                encoder.encodeEnum(fieldName, new JsonEnum(value.getAsInt(), dataTypeId.expanded()));
            } else if (hint instanceof StructHint) {
                encoder.encodeStruct(fieldName, value, dataTypeId);
            } else {
                throw new IllegalArgumentException("hint: " + hint);
            }
        } else if (field.getValueRank() == 1) {
            JsonArray jsonArray = value.getAsJsonArray();

            Object hint = getHint(field);
            if (hint instanceof BuiltinDataType) {
                encodeBuiltinDataTypeArray(encoder, fieldName, (BuiltinDataType) hint, jsonArray);
            } else if (hint instanceof EnumHint) {
                JsonEnum[] enumValues = new JsonEnum[jsonArray.size()];
                for (int i = 0; i < jsonArray.size(); i++) {
                    enumValues[i] = new JsonEnum(jsonArray.get(i).getAsInt(), dataTypeId.expanded());
                }
                encoder.encodeEnumArray(fieldName, enumValues);
            } else if (hint instanceof StructHint) {
                JsonStruct[] structValues = new JsonStruct[jsonArray.size()];
                for (int i = 0; i < jsonArray.size(); i++) {
                    structValues[i] = new JsonStruct(dataTypeTree.getDataType(dataTypeId), jsonArray.get(i).getAsJsonObject());
                }
                encoder.encodeStructArray(fieldName, structValues, dataTypeId);
            } else {
                throw new IllegalArgumentException("hint: " + hint);
            }
        } else if (field.getValueRank() > 1) {
            JsonArray jsonArray = value.getAsJsonArray();

            Object hint = getHint(field);
            if (hint instanceof BuiltinDataType) {
                // TODO
            } else if (hint instanceof EnumHint) {
                // TODO
            } else if (hint instanceof StructHint) {
                // TODO
            } else {
                throw new IllegalArgumentException("hint: " + hint);
            }
        } else {
            throw new IllegalArgumentException("unsupported value rank: " + field.getValueRank());
        }
    }

    private void encodeBuiltinDataType(UaEncoder encoder, String fieldName, BuiltinDataType dataType, JsonElement value) {
        switch (dataType) {
            case Boolean:
                encoder.encodeBoolean(fieldName, JsonConversions.toBoolean(value));
                break;
            case SByte:
                encoder.encodeSByte(fieldName, JsonConversions.toSByte(value));
                break;
            case Byte:
                encoder.encodeByte(fieldName, JsonConversions.toByte(value));
                break;
            case Int16:
                encoder.encodeInt16(fieldName, JsonConversions.toInt16(value));
                break;
            case UInt16:
                encoder.encodeUInt16(fieldName, JsonConversions.toUInt16(value));
                break;
            case Int32:
                encoder.encodeInt32(fieldName, JsonConversions.toInt32(value));
                break;
            case UInt32:
                encoder.encodeUInt32(fieldName, JsonConversions.toUInt32(value));
                break;
            case Int64:
                encoder.encodeInt64(fieldName, JsonConversions.toInt64(value));
                break;
            case UInt64:
                encoder.encodeUInt64(fieldName, JsonConversions.toUInt64(value));
                break;
            case Float:
                encoder.encodeFloat(fieldName, JsonConversions.toFloat(value));
                break;
            case Double:
                encoder.encodeDouble(fieldName, JsonConversions.toDouble(value));
                break;
            case String:
                encoder.encodeString(fieldName, JsonConversions.toString(value));
                break;
            case DateTime:
                encoder.encodeDateTime(fieldName, JsonConversions.toDateTime(value));
                break;
            case Guid:
                encoder.encodeGuid(fieldName, JsonConversions.toGuid(value));
                break;
            case ByteString:
                encoder.encodeByteString(fieldName, JsonConversions.toByteString(value));
                break;
            case XmlElement:
                encoder.encodeXmlElement(fieldName, JsonConversions.toXmlElement(value));
                break;
            case NodeId:
                encoder.encodeNodeId(fieldName, JsonConversions.toNodeId(value));
                break;
            case ExpandedNodeId:
                encoder.encodeExpandedNodeId(fieldName, JsonConversions.toExpandedNodeId(value));
                break;
            case StatusCode:
                encoder.encodeStatusCode(fieldName, JsonConversions.toStatusCode(value));
                break;
            case QualifiedName:
                encoder.encodeQualifiedName(fieldName, JsonConversions.toQualifiedName(value));
                break;
            case LocalizedText:
                encoder.encodeLocalizedText(fieldName, JsonConversions.toLocalizedText(value));
                break;
            case ExtensionObject:
                encoder.encodeExtensionObject(fieldName, JsonConversions.toExtensionObject(value));
                break;
            case DataValue:
                encoder.encodeDataValue(fieldName, JsonConversions.toDataValue(value));
                break;
            case Variant:
                encoder.encodeVariant(fieldName, JsonConversions.toVariant(value));
                break;

            case DiagnosticInfo:
            default:
                throw new IllegalArgumentException("Unsupported BuiltinDataType: " + dataType);
        }
    }

    private void encodeBuiltinDataTypeArray(UaEncoder encoder, String fieldName, BuiltinDataType dataType, JsonArray value) {
        switch (dataType) {
            case Boolean: {
                Boolean[] array = new Boolean[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toBoolean(value.get(i));
                }
                encoder.encodeBooleanArray(fieldName, array);
                break;
            }
            case SByte: {
                Byte[] array = new Byte[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toSByte(value.get(i));
                }
                encoder.encodeSByteArray(fieldName, array);
                break;
            }
            case Byte: {
                UByte[] array = new UByte[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toByte(value.get(i));
                }
                encoder.encodeByteArray(fieldName, array);
                break;
            }
            case Int16: {
                Short[] array = new Short[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toInt16(value.get(i));
                }
                encoder.encodeInt16Array(fieldName, array);
                break;
            }
            case UInt16: {
                UShort[] array = new UShort[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toUInt16(value.get(i));
                }
                encoder.encodeUInt16Array(fieldName, array);
                break;
            }
            case Int32: {
                Integer[] array = new Integer[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toInt32(value.get(i));
                }
                encoder.encodeInt32Array(fieldName, array);
                break;
            }
            case UInt32: {
                UInteger[] array = new UInteger[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toUInt32(value.get(i));
                }
                encoder.encodeUInt32Array(fieldName, array);
                break;
            }
            case Int64: {
                Long[] array = new Long[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toInt64(value.get(i));
                }
                encoder.encodeInt64Array(fieldName, array);
                break;
            }
            case UInt64: {
                ULong[] array = new ULong[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toUInt64(value.get(i));
                }
                encoder.encodeUInt64Array(fieldName, array);
                break;
            }
            case Float: {
                Float[] array = new Float[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toFloat(value.get(i));
                }
                encoder.encodeFloatArray(fieldName, array);
                break;
            }
            case Double: {
                Double[] array = new Double[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toDouble(value.get(i));
                }
                encoder.encodeDoubleArray(fieldName, array);
                break;
            }
            case String: {
                String[] array = new String[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toString(value.get(i));
                }
                encoder.encodeStringArray(fieldName, array);
                break;
            }
            case DateTime: {
                DateTime[] array = new DateTime[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toDateTime(value.get(i));
                }
                encoder.encodeDateTimeArray(fieldName, array);
                break;
            }
            case Guid: {
                UUID[] array = new UUID[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toGuid(value.get(i));
                }
                encoder.encodeGuidArray(fieldName, array);
                break;
            }
            case ByteString: {
                ByteString[] array = new ByteString[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toByteString(value.get(i));
                }
                encoder.encodeByteStringArray(fieldName, array);
                break;
            }
            case XmlElement: {
                XmlElement[] array = new XmlElement[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toXmlElement(value.get(i));
                }
                encoder.encodeXmlElementArray(fieldName, array);
                break;
            }
            case NodeId: {
                NodeId[] array = new NodeId[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toNodeId(value.get(i));
                }
                encoder.encodeNodeIdArray(fieldName, array);
                break;
            }
            case ExpandedNodeId: {
                ExpandedNodeId[] array = new ExpandedNodeId[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toExpandedNodeId(value.get(i));
                }
                encoder.encodeExpandedNodeIdArray(fieldName, array);
                break;
            }
            case StatusCode: {
                StatusCode[] array = new StatusCode[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toStatusCode(value.get(i));
                }
                encoder.encodeStatusCodeArray(fieldName, array);
                break;
            }
            case QualifiedName: {
                QualifiedName[] array = new QualifiedName[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toQualifiedName(value.get(i));
                }
                encoder.encodeQualifiedNameArray(fieldName, array);
                break;
            }
            case LocalizedText: {
                LocalizedText[] array = new LocalizedText[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toLocalizedText(value.get(i));
                }
                encoder.encodeLocalizedTextArray(fieldName, array);
                break;
            }
            case ExtensionObject: {
                ExtensionObject[] array = new ExtensionObject[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toExtensionObject(value.get(i));
                }
                encoder.encodeExtensionObjectArray(fieldName, array);
                break;
            }
            case DataValue: {
                DataValue[] array = new DataValue[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toDataValue(value.get(i));
                }
                encoder.encodeDataValueArray(fieldName, array);
                break;
            }
            case Variant: {
                Variant[] array = new Variant[value.size()];
                for (int i = 0; i < value.size(); i++) {
                    array[i] = JsonConversions.toVariant(value.get(i));
                }
                encoder.encodeVariantArray(fieldName, array);
                break;
            }

            case DiagnosticInfo:
            default:
                throw new IllegalArgumentException("Unsupported BuiltinDataType: " + dataType);
        }
    }

    //endregion

    private @Nullable Object getHint(StructureField field) {
        Map<StructureField, Object> hints = this.hints.get(() -> {
            Map<StructureField, Object> map = new HashMap<>();

            StructureField[] fields = requireNonNullElse(definition.getFields(), new StructureField[0]);

            for (StructureField f : fields) {
                NodeId dataTypeId = f.getDataType();
                if (BuiltinDataType.isBuiltin(dataTypeId)) {
                    map.put(f, BuiltinDataType.fromNodeId(dataTypeId));
                } else if (dataTypeTree.isEnumType(dataTypeId)) {
                    map.put(f, new EnumHint());
                } else if (dataTypeTree.isStructType(dataTypeId)) {
                    map.put(f, new StructHint());
                } else {
                    // alias/simple type, find the builtin parent
                    map.put(f, dataTypeTree.getBuiltinType(dataTypeId));

                }
            }

            return map;
        });

        return hints.get(field);
    }

    private static class EnumHint {}

    private static class StructHint {}

    private static class JsonEnum implements UaEnumeratedType {
        private final int value;
        private final ExpandedNodeId typeId;

        JsonEnum(int value, ExpandedNodeId typeId) {
            this.value = value;
            this.typeId = typeId;
        }

        @Override
        public ExpandedNodeId getTypeId() {
            return typeId;
        }

        @Override
        public int getValue() {
            return value;
        }
    }

}
