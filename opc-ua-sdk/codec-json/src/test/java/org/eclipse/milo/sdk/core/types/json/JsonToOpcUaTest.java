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

import java.time.Instant;
import java.util.UUID;

import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonToOpcUaTest {

    @Test
    void toUByte() {
        UByte result = JsonToOpcUa.toByte(new JsonPrimitive("1"));
        assertEquals(UByte.valueOf(1), result);
    }

    @Test
    void toUShort() {
        UShort result = JsonToOpcUa.toUInt16(new JsonPrimitive("1"));
        assertEquals(UShort.valueOf(1), result);
    }

    @Test
    void toUInteger() {
        UInteger result = JsonToOpcUa.toUInt32(new JsonPrimitive("1"));
        assertEquals(UInteger.valueOf(1), result);
    }

    @Test
    void toULong() {
        ULong result = JsonToOpcUa.toUInt64(new JsonPrimitive("1"));
        assertEquals(ULong.valueOf(1), result);
    }

    @Test
    void toDateTime() {
        var now = Instant.now();
        String iso8601 = now.toString();

        DateTime result = JsonToOpcUa.toDateTime(new JsonPrimitive(iso8601));
        assertEquals(new DateTime(now), result);
    }

    @Test
    void toUUID() {
        String uuid = "123e4567-e89b-12d3-a456-426614174000";
        UUID result = JsonToOpcUa.toGuid(new JsonPrimitive(uuid));
        assertEquals(UUID.fromString(uuid), result);
    }

    @Test
    void toByteString() {
        var jsonArray = new JsonArray();
        jsonArray.add((byte) 1);
        jsonArray.add((byte) 2);
        jsonArray.add((byte) 3);

        ByteString result = JsonToOpcUa.toByteString(jsonArray);
        ByteString expected = new ByteString(new byte[]{(byte) 1, (byte) 2, (byte) 3});

        assertEquals(expected, result);
    }

    @Test
    public void toXmlElement() {
        String knownXml = "<root><element>value</element></root>";
        var json = new JsonPrimitive(knownXml);

        XmlElement result = JsonToOpcUa.toXmlElement(json);
        XmlElement expected = new XmlElement(knownXml);

        assertEquals(expected, result);

        assertEquals(new XmlElement(null), JsonToOpcUa.toXmlElement(JsonNull.INSTANCE));
    }

    @Test
    void toNodeId() {
        // TODO
    }

    @Test
    void toExpandedNodeId() {
        // TODO
    }

    @Test
    void toStatusCode() {
        StatusCode result = JsonToOpcUa.toStatusCode(new JsonPrimitive(0L));
        assertEquals(StatusCode.GOOD, result);
    }

    @Test
    void toQualifiedName() {
        // TODO
    }

    @Test
    void toLocalizedText() {
        var jsonObject = new JsonObject();
        jsonObject.addProperty("Locale", "en");
        jsonObject.addProperty("Text", "Hello, World!");

        LocalizedText result = JsonToOpcUa.toLocalizedText(jsonObject);
        LocalizedText expected = new LocalizedText("en", "Hello, World!");

        assertEquals(expected, result);
    }

    @Test
    void toVariant() {

    }

}
