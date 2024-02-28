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

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.eclipse.milo.opcua.stack.core.BuiltinDataType;
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

public class JsonConversions {

    //region OPC UA to JSON Conversions

    public static JsonElement fromBoolean(boolean value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement fromSByte(byte value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement fromByte(UByte value) {
        return new JsonPrimitive(value.shortValue());
    }

    public static JsonElement fromInt16(short value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement fromUInt16(UShort value) {
        return new JsonPrimitive(value.intValue());
    }

    public static JsonElement fromInt32(int value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement fromUInt32(UInteger value) {
        return new JsonPrimitive(value.longValue());
    }

    public static JsonElement fromInt64(long value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement fromUInt64(ULong value) {
        return new JsonPrimitive(value.toBigInteger());
    }

    public static JsonElement fromFloat(float value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement fromDouble(double value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement fromString(String value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement fromDateTime(DateTime value) {
        return new JsonPrimitive(value.toIso8601String());
    }

    public static JsonElement fromGuid(UUID value) {
        return new JsonPrimitive(value.toString());
    }

    public static JsonElement fromByteString(ByteString value) {
        String b64 = Base64.getEncoder().encodeToString(value.bytesOrEmpty());

        return new JsonPrimitive(b64);
    }

    public static JsonElement fromXmlElement(XmlElement value) {
        if (value.isNull()) {
            return JsonNull.INSTANCE;
        } else {
            return new JsonPrimitive(value.getFragment());
        }
    }

    public static JsonElement fromNodeId(NodeId value) {
        return new JsonPrimitive(value.toParseableString());
    }

    public static JsonElement fromExpandedNodeId(ExpandedNodeId value) {
        return new JsonPrimitive(value.toParseableString());
    }

    public static JsonElement fromStatusCode(StatusCode value) {
        return new JsonPrimitive(value.getValue());
    }

    public static JsonElement fromQualifiedName(QualifiedName value) {
        return null; // TODO
    }

    public static JsonElement fromLocalizedText(LocalizedText value) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("Locale", value.getLocale());
        jsonObject.addProperty("Text", value.getText());

        return jsonObject;
    }

    public static JsonElement fromExtensionObject(ExtensionObject value) {
        JsonObject jsonObject = new JsonObject();

        jsonObject.add("TypeId", fromNodeId(value.getEncodingId()));

        if (value.getBody() instanceof ByteString) {
            jsonObject.addProperty("Encoding", 1);
            jsonObject.add("Body", fromByteString((ByteString) value.getBody()));
        } else if (value.getBody() instanceof XmlElement) {
            jsonObject.addProperty("Encoding", 2);
            jsonObject.add("Body", fromXmlElement((XmlElement) value.getBody()));
        } else {
            // Omit "Encoding" field when Body is JSON
            jsonObject.addProperty("Body", value.getBody().toString());
        }

        return jsonObject;
    }

    public static JsonElement fromDataValue(DataValue value) {
        return null; // TODO
    }

    public static JsonElement fromVariant(Variant value) {
        return null; // TODO
    }

    //endregion

    //region JSON to OPC UA Conversions

    public static boolean toBoolean(JsonElement element) {
        return element.getAsBoolean();
    }

    public static byte toSByte(JsonElement element) {
        return element.getAsByte();
    }

    public static UByte toByte(JsonElement element) {
        return UByte.valueOf(element.getAsShort());
    }

    public static short toInt16(JsonElement element) {
        return element.getAsShort();
    }

    public static UShort toUInt16(JsonElement element) {
        return UShort.valueOf(element.getAsInt());
    }

    public static int toInt32(JsonElement element) {
        return element.getAsInt();
    }

    public static UInteger toUInt32(JsonElement element) {
        return UInteger.valueOf(element.getAsLong());
    }

    public static long toInt64(JsonElement element) {
        return element.getAsLong();
    }

    public static ULong toUInt64(JsonElement element) {
        return ULong.valueOf(element.getAsBigInteger());
    }

    public static float toFloat(JsonElement element) {
        return element.getAsFloat();
    }

    public static double toDouble(JsonElement element) {
        return element.getAsDouble();
    }

    public static String toString(JsonElement element) {
        return element.getAsString();
    }

    public static DateTime toDateTime(JsonElement element) {
        return new DateTime(Instant.parse(element.getAsString()));
    }

    public static UUID toGuid(JsonElement element) {
        return UUID.fromString(element.getAsString());
    }

    public static ByteString toByteString(JsonElement element) {
        byte[] bs = Base64.getDecoder().decode(element.getAsString());

        return new ByteString(bs);
    }

    public static XmlElement toXmlElement(JsonElement element) {
        if (element.isJsonNull()) {
            return new XmlElement(null);
        } else {
            return new XmlElement(element.getAsString());
        }
    }

    public static NodeId toNodeId(JsonElement element) {
        return NodeId.parse(element.getAsString());
    }

    public static ExpandedNodeId toExpandedNodeId(JsonElement element) {
        return ExpandedNodeId.parse(element.getAsString());
    }

    public static StatusCode toStatusCode(JsonElement element) {
        return new StatusCode(element.getAsLong());
    }

    public static QualifiedName toQualifiedName(JsonElement element) {
        return null; // TODO
    }

    public static LocalizedText toLocalizedText(JsonElement element) {
        JsonObject jsonObject = element.getAsJsonObject();
        String locale = jsonObject.get("Locale").getAsString();
        String text = jsonObject.get("Text").getAsString();

        return new LocalizedText(locale, text);
    }

    public static ExtensionObject toExtensionObject(JsonElement element) {
        JsonObject jsonObject = element.getAsJsonObject();

        NodeId typeId = toNodeId(jsonObject.get("TypeId"));

        int encoding = 0;
        if (jsonObject.has("Encoding")) {
            encoding = jsonObject.get("Encoding").getAsInt();
        }

        switch (encoding) {
            case 0: {
                String body = jsonObject.get("Body").toString();
                return new ExtensionObject(body, typeId);
            }
            case 1: {
                ByteString body = toByteString(jsonObject.get("Body"));
                return new ExtensionObject(body, typeId);
            }
            case 2: {
                XmlElement body = toXmlElement(jsonObject.get("Body"));
                return new ExtensionObject(body, typeId);
            }
            default:
                throw new IllegalArgumentException("unknown encoding: " + encoding);
        }
    }

    public static DataValue toDataValue(JsonElement element) {
        return null; // TODO
    }

    public static Variant toVariant(JsonElement element) {
        if (element.isJsonNull() || !element.isJsonObject()) {
            return Variant.NULL_VALUE;
        }

        JsonObject jsonObject = element.getAsJsonObject();

        int typeId = jsonObject.get("Type").getAsInt();
        JsonElement bodyElement = jsonObject.get("Body");

        BuiltinDataType dataType = BuiltinDataType.fromTypeId(typeId);
        if (dataType == null) {
            throw new IllegalArgumentException("unknown type: " + typeId);
        }

        if (bodyElement.isJsonArray()) {
            JsonArray jsonArray = jsonObject.getAsJsonArray();
            Class<?> clazz = dataType.getBackingClass();
            Object array = Array.newInstance(clazz, jsonArray.size());

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonElement jsonElement = jsonArray.get(i);
                Object value = getScalarValue(jsonElement, dataType);
                Array.set(array, i, value);
            }

            if (jsonObject.has("Dimensions")) {
                JsonArray dimensionsArray = jsonObject.get("Dimensions").getAsJsonArray();
                int[] dimensions = new int[dimensionsArray.size()];
                for (int i = 0; i < dimensionsArray.size(); i++) {
                    dimensions[i] = dimensionsArray.get(i).getAsInt();
                }

                return Variant.ofMatrix(new Matrix(array, dimensions, dataType));
            } else {
                return Variant.of(array);
            }
        } else {
            return Variant.of(getScalarValue(bodyElement, dataType));
        }
    }

    private static Object getScalarValue(JsonElement element, BuiltinDataType dataType) {
        switch (dataType) {
            case Boolean:
                return toBoolean(element);
            case SByte:
                return toSByte(element);
            case Byte:
                return toByte(element).intValue();
            case Int16:
                return toInt16(element);
            case UInt16:
                return toUInt16(element).intValue();
            case Int32:
                return element.getAsInt();
            case UInt32:
                return toUInt32(element).longValue();
            case Int64:
                return element.getAsLong();
            case UInt64:
                return toUInt64(element).longValue();
            case Float:
                return element.getAsFloat();
            case Double:
                return element.getAsDouble();
            case String:
                return element.getAsString();
            case DateTime:
                return toDateTime(element);
            case Guid:
                return toGuid(element);
            case ByteString:
                return toByteString(element);
            case XmlElement:
                return toXmlElement(element);
            case NodeId:
                return toNodeId(element);
            case ExpandedNodeId:
                return toExpandedNodeId(element);
            case StatusCode:
                return toStatusCode(element);
            case QualifiedName:
                return toQualifiedName(element);
            case LocalizedText:
                return toLocalizedText(element);
            case ExtensionObject:
                return toExtensionObject(element);
            case DataValue:
                return toDataValue(element);
            case Variant:
                return toVariant(element);
            case DiagnosticInfo:
            default:
                // note: shouldn't be possible to get here.
                // DiagnosticInfo is not allowed in Variant.
                // All other types should be handled above.
                return null;
        }
    }

    //endregion

}
