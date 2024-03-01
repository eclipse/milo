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

import java.math.BigInteger;
import java.time.Instant;
import java.util.Base64;
import java.util.UUID;
import java.util.stream.Stream;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonConversionsTest {

    @ParameterizedTest
    @ValueSource(ints = {0, 1, Byte.MAX_VALUE, UByte.MAX_VALUE})
    void testByte(int input) {
        var original = UByte.valueOf(input);

        JsonElement jsonValue = JsonConversions.fromByte(original);
        assertEquals(new JsonPrimitive(input), jsonValue);

        UByte opcValue = JsonConversions.toByte(jsonValue);
        assertEquals(original, opcValue);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, Short.MAX_VALUE, UShort.MAX_VALUE})
    void testUInt16(int input) {
        var original = UShort.valueOf(input);

        JsonElement jsonValue = JsonConversions.fromUInt16(original);
        assertEquals(new JsonPrimitive(input), jsonValue);

        UShort opcValue = JsonConversions.toUInt16(jsonValue);
        assertEquals(original, opcValue);
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, 1L, Integer.MAX_VALUE, UInteger.MAX_VALUE})
    void testUInt32(long input) {
        var original = UInteger.valueOf(input);

        JsonElement jsonValue = JsonConversions.fromUInt32(original);
        assertEquals(new JsonPrimitive(input), jsonValue);

        UInteger opcValue = JsonConversions.toUInt32(jsonValue);
        assertEquals(original, opcValue);
    }

    @ParameterizedTest
    @MethodSource("testUInt64Provider")
    void testUInt64(BigInteger input) {
        var original = ULong.valueOf(input);

        JsonElement jsonValue = JsonConversions.fromUInt64(original);
        assertEquals(new JsonPrimitive(input), jsonValue);

        ULong opcValue = JsonConversions.toUInt64(jsonValue);
        assertEquals(original, opcValue);
    }

    private static Stream<Arguments> testUInt64Provider() {
        return Stream.of(
            () -> new Object[]{BigInteger.ZERO},
            () -> new Object[]{BigInteger.ONE},
            () -> new Object[]{ULong.MAX_VALUE_LONG},
            () -> new Object[]{ULong.MAX_VALUE}
        );
    }

    @Test
    void toDateTime() {
        var now = Instant.now();
        String iso8601 = now.toString();

        DateTime result = JsonConversions.toDateTime(new JsonPrimitive(iso8601));
        assertEquals(new DateTime(now), result);
    }

    @Test
    void toUUID() {
        String uuid = "123e4567-e89b-12d3-a456-426614174000";
        UUID result = JsonConversions.toGuid(new JsonPrimitive(uuid));
        assertEquals(UUID.fromString(uuid), result);
    }

    @Test
    void toByteString() {
        byte[] bs = new byte[]{1, 2, 3, 4, 5};
        String b64 = Base64.getEncoder().encodeToString(bs);

        ByteString result = JsonConversions.toByteString(new JsonPrimitive(b64));

        assertEquals(ByteString.of(bs), result);
    }

    @Test
    public void toXmlElement() {
        String knownXml = "<root><element>value</element></root>";
        var json = new JsonPrimitive(knownXml);

        XmlElement result = JsonConversions.toXmlElement(json);
        XmlElement expected = new XmlElement(knownXml);

        assertEquals(expected, result);

        assertEquals(new XmlElement(null), JsonConversions.toXmlElement(JsonNull.INSTANCE));
    }

    @Test
    void toNodeId() {
        // TODO
    }

    @Test
    void toExpandedNodeId() {
        // TODO
    }

    @ParameterizedTest
    @MethodSource("toStatusCodeProvider")
    void testStatusCode(StatusCode input) {
        var jsonValue = JsonConversions.fromStatusCode(input);
        assertEquals(new JsonPrimitive(input.getValue()), jsonValue);

        StatusCode opcValue = JsonConversions.toStatusCode(jsonValue);
        assertEquals(input, opcValue);
    }

    private static Stream<Arguments> toStatusCodeProvider() {
        return Stream.of(
            Arguments.of(StatusCode.GOOD),
            Arguments.of(StatusCode.BAD),
            Arguments.of(StatusCode.UNCERTAIN)
        );
    }

    @Test
    void toQualifiedName() {
        {
            var qn0 = new QualifiedName(0, "foo");

            var asJson = JsonConversions.fromQualifiedName(qn0);
            assertEquals(new JsonPrimitive("foo"), asJson);
            var asOpcUa = JsonConversions.toQualifiedName(asJson);
            assertEquals(qn0, asOpcUa);
        }
        {
            var qn1 = new QualifiedName(1, "bar");

            var asJson = JsonConversions.fromQualifiedName(qn1);
            assertEquals(new JsonPrimitive("1:bar"), asJson);
            var asOpcUa = JsonConversions.toQualifiedName(asJson);
            assertEquals(qn1, asOpcUa);
        }
        {
            var qn2 = new QualifiedName(2, "baz:qux");

            var asJson = JsonConversions.fromQualifiedName(qn2);
            assertEquals(new JsonPrimitive("2:baz:qux"), asJson);
            var asOpcUa = JsonConversions.toQualifiedName(asJson);
            assertEquals(qn2, asOpcUa);
        }
    }

    @Test
    void toLocalizedText() {
        var jsonObject = new JsonObject();
        jsonObject.addProperty("Locale", "en");
        jsonObject.addProperty("Text", "Hello, World!");

        LocalizedText result = JsonConversions.toLocalizedText(jsonObject);
        LocalizedText expected = new LocalizedText("en", "Hello, World!");

        assertEquals(expected, result);
    }

    @Test
    void toVariant() {

    }

}
