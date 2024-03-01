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

    /**
     * Convert an OPC UA value of some {@link BuiltinDataType} to a {@link JsonElement}.
     * <p>
     * Converting from values of type {@link BuiltinDataType#DiagnosticInfo} is not supported.
     *
     * @param value the OPC UA value to convert.
     * @param dataType the OPC UA {@link BuiltinDataType} of the value.
     * @return the converted {@link JsonElement}.
     * @throws IllegalArgumentException if the {@link BuiltinDataType} is not supported.
     */
    public static JsonElement from(Object value, BuiltinDataType dataType) {
        switch (dataType) {
            case Boolean:
                return fromBoolean((Boolean) value);
            case SByte:
                return fromSByte((Byte) value);
            case Byte:
                return fromByte((UByte) value);
            case Int16:
                return fromInt16((Short) value);
            case UInt16:
                return fromUInt16((UShort) value);
            case Int32:
                return fromInt32((Integer) value);
            case UInt32:
                return fromUInt32((UInteger) value);
            case Int64:
                return fromInt64((Long) value);
            case UInt64:
                return fromUInt64((ULong) value);
            case Float:
                return fromFloat((Float) value);
            case Double:
                return fromDouble((Double) value);
            case String:
                return fromString((String) value);
            case DateTime:
                return fromDateTime((DateTime) value);
            case Guid:
                return fromGuid((UUID) value);
            case ByteString:
                return fromByteString((ByteString) value);
            case XmlElement:
                return fromXmlElement((XmlElement) value);
            case NodeId:
                return fromNodeId((NodeId) value);
            case ExpandedNodeId:
                return fromExpandedNodeId((ExpandedNodeId) value);
            case StatusCode:
                return fromStatusCode((StatusCode) value);
            case QualifiedName:
                return fromQualifiedName((QualifiedName) value);
            case LocalizedText:
                return fromLocalizedText((LocalizedText) value);
            case ExtensionObject:
                return fromExtensionObject((ExtensionObject) value);
            case DataValue:
                return fromDataValue((DataValue) value);
            case Variant:
                return fromVariant((Variant) value);
            case DiagnosticInfo:
            default:
                throw new IllegalArgumentException("dataType: " + dataType);
        }
    }

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
        // TODO the v2 OPC UA JSON encoding uses a namespace URI here

        String name = value.getName() == null ? "" : value.getName();
        int ns = value.getNamespaceIndex().intValue();

        if (ns != 0) {
            return new JsonPrimitive(value.getNamespaceIndex() + ":" + name);
        } else {
            return new JsonPrimitive(name);
        }
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

    /**
     * Convert a {@link JsonElement} to a value of some OPC UA {@link BuiltinDataType}.
     * <p>
     * Converting to values of type {@link BuiltinDataType#DiagnosticInfo} is not supported.
     *
     * @param element the {@link JsonElement} to convert.
     * @param dataType the OPC UA {@link BuiltinDataType} to convert to.
     * @return the converted value.
     * @throws IllegalArgumentException if the {@link BuiltinDataType} is not supported.
     */
    public static Object to(JsonElement element, BuiltinDataType dataType) {
        switch (dataType) {
            case Boolean:
                return toBoolean(element);
            case SByte:
                return toSByte(element);
            case Byte:
                return toByte(element);
            case Int16:
                return toInt16(element);
            case UInt16:
                return toUInt16(element);
            case Int32:
                return toInt32(element);
            case UInt32:
                return toUInt32(element);
            case Int64:
                return toInt64(element);
            case UInt64:
                return toUInt64(element);
            case Float:
                return toFloat(element);
            case Double:
                return toDouble(element);
            case String:
                return toString(element);
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
                throw new IllegalArgumentException("dataType: " + dataType);
        }
    }

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
        // TODO the v2 OPC UA JSON encoding uses a namespace URI here

        int ns = 0;
        String name = element.getAsString();

        if (name.contains(":")) {
            String[] ss = name.split(":", 2);
            ns = Integer.parseInt(ss[0]);
            name = ss[1];
        }

        return new QualifiedName(ns, name);
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
                Object value = to(jsonElement, dataType);
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
            return Variant.of(to(bodyElement, dataType));
        }
    }

    //endregion

}
