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

import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;

public class OpcUaToJson {

    public static JsonElement toBoolean(boolean value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement toSByte(byte value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement toByte(UByte value) {
        return new JsonPrimitive(value.shortValue());
    }

    public static JsonElement toInt16(short value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement toUInt16(UShort value) {
        return new JsonPrimitive(value.intValue());
    }

    public static JsonElement toInt32(int value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement toUInt32(UInteger value) {
        return new JsonPrimitive(value.longValue());
    }

    public static JsonElement toInt64(long value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement toUInt64(ULong value) {
        return new JsonPrimitive(value.toBigInteger());
    }

    public static JsonElement toFloat(float value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement toDouble(double value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement toString(String value) {
        return new JsonPrimitive(value);
    }

    public static JsonElement toDateTime(DateTime value) {
        return new JsonPrimitive(value.toIso8601String());
    }

    public static JsonElement toGuid(UUID value) {
        return new JsonPrimitive(value.toString());
    }

    public static JsonElement toByteString(ByteString value) {
        var jsonArray = new JsonArray();
        for (byte b : value.bytesOrEmpty()) {
            jsonArray.add(b);
        }
        return jsonArray;
    }

    public static JsonElement toXmlElement(XmlElement value) {
        if (value.isNull()) {
            return JsonNull.INSTANCE;
        } else {
            return new JsonPrimitive(value.getFragment());
        }
    }


}
