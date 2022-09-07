/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import java.io.IOException;
import java.io.StringReader;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OpcUaJsonDecoderTest {

    private final SerializationContext context = new TestSerializationContext();

    @Test
    void readBoolean() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("true"));
        assertTrue(decoder.readBoolean(null));

        decoder.reset(new StringReader("false"));
        assertFalse(decoder.readBoolean(null));

        decoder.reset(new StringReader("{\"foo\":true}"));
        decoder.jsonReader.beginObject();
        assertTrue(decoder.readBoolean("foo"));
        decoder.jsonReader.endObject();

        decoder.reset(new StringReader("{\"foo\":false}"));
        decoder.jsonReader.beginObject();
        assertFalse(decoder.readBoolean("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":true}"));
            decoder.jsonReader.beginObject();
            decoder.readBoolean("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readSByte() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("0"));
        assertEquals((byte) 0, decoder.readSByte(null));

        decoder.reset(new StringReader(String.valueOf(Byte.MIN_VALUE)));
        assertEquals(Byte.MIN_VALUE, decoder.readSByte(null));

        decoder.reset(new StringReader(String.valueOf(Byte.MAX_VALUE)));
        assertEquals(Byte.MAX_VALUE, decoder.readSByte(null));

        decoder.reset(new StringReader("{\"foo\":0}"));
        decoder.jsonReader.beginObject();
        assertEquals((byte) 0, decoder.readSByte("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":0}"));
            decoder.jsonReader.beginObject();
            decoder.readSByte("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readInt16() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("0"));
        assertEquals((short) 0, decoder.readInt16(null));

        decoder.reset(new StringReader(String.valueOf(Short.MIN_VALUE)));
        assertEquals(Short.MIN_VALUE, decoder.readInt16(null));

        decoder.reset(new StringReader(String.valueOf(Short.MAX_VALUE)));
        assertEquals(Short.MAX_VALUE, decoder.readInt16(null));

        decoder.reset(new StringReader("{\"foo\":0}"));
        decoder.jsonReader.beginObject();
        assertEquals((short) 0, decoder.readInt16("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":0}"));
            decoder.jsonReader.beginObject();
            decoder.readInt16("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readInt32() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("0"));
        assertEquals(0, decoder.readInt32(null));

        decoder.reset(new StringReader(String.valueOf(Integer.MIN_VALUE)));
        assertEquals(Integer.MIN_VALUE, decoder.readInt32(null));

        decoder.reset(new StringReader(String.valueOf(Integer.MAX_VALUE)));
        assertEquals(Integer.MAX_VALUE, decoder.readInt32(null));

        decoder.reset(new StringReader("{\"foo\":0}"));
        decoder.jsonReader.beginObject();
        assertEquals(0, decoder.readInt32("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":0}"));
            decoder.jsonReader.beginObject();
            decoder.readInt32("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readInt64() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("\"0\""));
        assertEquals(0L, decoder.readInt64(null));

        decoder.reset(new StringReader(String.format("\"%s\"", Long.MIN_VALUE)));
        assertEquals(Long.MIN_VALUE, decoder.readInt64(null));

        decoder.reset(new StringReader(String.format("\"%s\"", Long.MAX_VALUE)));
        assertEquals(Long.MAX_VALUE, decoder.readInt64(null));

        decoder.reset(new StringReader("{\"foo\":\"0\"}"));
        decoder.jsonReader.beginObject();
        assertEquals(0L, decoder.readInt64("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":\"0\"}"));
            decoder.jsonReader.beginObject();
            decoder.readInt64("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readByte() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader(String.valueOf(UByte.MIN)));
        assertEquals(UByte.MIN, decoder.readByte(null));

        decoder.reset(new StringReader(String.valueOf(UByte.MAX)));
        assertEquals(UByte.MAX, decoder.readByte(null));

        decoder.reset(new StringReader("{\"foo\":0}"));
        decoder.jsonReader.beginObject();
        assertEquals(UByte.MIN, decoder.readByte("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":0}"));
            decoder.jsonReader.beginObject();
            decoder.readByte("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readUInt16() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader(String.valueOf(UShort.MIN)));
        assertEquals(UShort.MIN, decoder.readUInt16(null));

        decoder.reset(new StringReader(String.valueOf(UShort.MAX)));
        assertEquals(UShort.MAX, decoder.readUInt16(null));

        decoder.reset(new StringReader("{\"foo\":0}"));
        decoder.jsonReader.beginObject();
        assertEquals(UShort.MIN, decoder.readUInt16("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":0}"));
            decoder.jsonReader.beginObject();
            decoder.readUInt16("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readUInt32() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader(String.valueOf(UInteger.MIN)));
        assertEquals(UInteger.MIN, decoder.readUInt32(null));

        decoder.reset(new StringReader(String.valueOf(UInteger.MAX)));
        assertEquals(UInteger.MAX, decoder.readUInt32(null));

        decoder.reset(new StringReader("{\"foo\":0}"));
        decoder.jsonReader.beginObject();
        assertEquals(UInteger.MIN, decoder.readUInt32("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":0}"));
            decoder.jsonReader.beginObject();
            decoder.readUInt32("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readUInt64() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader(String.format("\"%s\"", ULong.MIN)));
        assertEquals(ULong.MIN, decoder.readUInt64(null));

        decoder.reset(new StringReader(String.format("\"%s\"", ULong.MAX)));
        assertEquals(ULong.MAX, decoder.readUInt64(null));

        decoder.reset(new StringReader("{\"foo\":\"0\"}"));
        decoder.jsonReader.beginObject();
        assertEquals(ULong.MIN, decoder.readUInt64("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":\"0\"}"));
            decoder.jsonReader.beginObject();
            decoder.readUInt64("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readFloat() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("0.0"));
        assertEquals(0.0f, decoder.readFloat(null));

        decoder.reset(new StringReader(String.valueOf(Float.MIN_VALUE)));
        assertEquals(Float.MIN_VALUE, decoder.readFloat(null));

        decoder.reset(new StringReader(String.valueOf(Float.MAX_VALUE)));
        assertEquals(Float.MAX_VALUE, decoder.readFloat(null));

        decoder.reset(new StringReader("\"Infinity\""));
        assertEquals(Float.POSITIVE_INFINITY, decoder.readFloat(null));

        decoder.reset(new StringReader("\"-Infinity\""));
        assertEquals(Float.NEGATIVE_INFINITY, decoder.readFloat(null));

        decoder.reset(new StringReader("\"NaN\""));
        assertEquals(Float.NaN, decoder.readFloat(null));

        decoder.reset(new StringReader("{\"foo\":0.0}"));
        decoder.jsonReader.beginObject();
        assertEquals(0.0f, decoder.readFloat("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":0.0}"));
            decoder.jsonReader.beginObject();
            decoder.readFloat("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readDouble() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("0.0"));
        assertEquals(0.0, decoder.readDouble(null));

        decoder.reset(new StringReader(String.valueOf(Double.MIN_VALUE)));
        assertEquals(Double.MIN_VALUE, decoder.readDouble(null));

        decoder.reset(new StringReader(String.valueOf(Double.MAX_VALUE)));
        assertEquals(Double.MAX_VALUE, decoder.readDouble(null));

        decoder.reset(new StringReader("\"Infinity\""));
        assertEquals(Double.POSITIVE_INFINITY, decoder.readDouble(null));

        decoder.reset(new StringReader("\"-Infinity\""));
        assertEquals(Double.NEGATIVE_INFINITY, decoder.readDouble(null));

        decoder.reset(new StringReader("\"NaN\""));
        assertEquals(Double.NaN, decoder.readDouble(null));

        decoder.reset(new StringReader("{\"foo\":0.0}"));
        decoder.jsonReader.beginObject();
        assertEquals(0.0, decoder.readDouble("foo"));
        decoder.jsonReader.endObject();

        assertThrows(UaSerializationException.class, () -> {
            decoder.reset(new StringReader("{\"foo\":0.0}"));
            decoder.jsonReader.beginObject();
            decoder.readDouble("bar");
            decoder.jsonReader.endObject();
        });
    }

    @Test
    void readString() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("\"\""));
        assertEquals("", decoder.readString(null));

        decoder.reset(new StringReader("\"foo\""));
        assertEquals("foo", decoder.readString(null));

        decoder.reset(new StringReader("{\"foo\":\"bar\"}"));
        decoder.jsonReader.beginObject();
        assertEquals("bar", decoder.readString("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readDateTime() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader(String.format("\"%s\"", DateTime.MIN_ISO_8601_STRING)));
        assertEquals(DateTime.MIN_DATE_TIME, decoder.readDateTime(null));

        decoder.reset(new StringReader(String.format("\"%s\"", DateTime.MAX_ISO_8601_STRING)));
        assertEquals(DateTime.MAX_DATE_TIME, decoder.readDateTime(null));

        Instant minus1 = DateTime.MIN_ISO_8601_INSTANT.minus(1, ChronoUnit.SECONDS);
        decoder.reset(new StringReader(String.format("\"%s\"", minus1)));
        assertEquals(DateTime.MIN_DATE_TIME, decoder.readDateTime(null));

        Instant plus1 = DateTime.MAX_ISO_8601_INSTANT.plus(1, ChronoUnit.SECONDS);
        decoder.reset(new StringReader(String.format("\"%s\"", plus1)));
        assertEquals(DateTime.MAX_DATE_TIME, decoder.readDateTime(null));

        DateTime now = DateTime.nowNanos();
        String isoNow = now.toIso8601String();
        decoder.reset(new StringReader(String.format("\"%s\"", isoNow)));
        assertEquals(now, decoder.readDateTime(null));

        decoder.reset(new StringReader(String.format("{\"foo\":\"%s\"}", isoNow)));
        decoder.jsonReader.beginObject();
        assertEquals(now, decoder.readDateTime("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readGuid() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        UUID uuid = UUID.randomUUID();

        decoder.reset(new StringReader(String.format("\"%s\"", uuid.toString().toLowerCase())));
        assertEquals(uuid, decoder.readGuid(null));

        decoder.reset(new StringReader(String.format("\"%s\"", uuid.toString().toUpperCase())));
        assertEquals(uuid, decoder.readGuid(null));

        decoder.reset(new StringReader(String.format("{\"foo\":\"%s\"}", uuid)));
        decoder.jsonReader.beginObject();
        assertEquals(uuid, decoder.readGuid("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readByteString() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        byte[] emptyBytes = new byte[0];
        decoder.reset(new StringReader(String.format("\"%s\"", Base64.getEncoder().encodeToString(emptyBytes))));
        assertEquals(ByteString.of(emptyBytes), decoder.readByteString(null));

        byte[] randomBytes = randomBytes(16);
        decoder.reset(new StringReader(String.format("\"%s\"", Base64.getEncoder().encodeToString(randomBytes))));
        assertEquals(ByteString.of(randomBytes), decoder.readByteString(null));

        decoder.reset(new StringReader(String.format("{\"foo\":\"%s\"}", Base64.getEncoder().encodeToString(randomBytes))));
        decoder.jsonReader.beginObject();
        assertEquals(ByteString.of(randomBytes), decoder.readByteString("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readXmlElement() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        var emptyElement = new XmlElement("");
        decoder.reset(new StringReader("\"\""));
        assertEquals(emptyElement, decoder.readXmlElement(null));

        var element = new XmlElement("<foo>bar</foo>");
        decoder.reset(new StringReader(String.format("\"%s\"", element.getFragment())));
        assertEquals(element, decoder.readXmlElement(null));

        decoder.reset(new StringReader(String.format("{\"foo\":\"%s\"}", element.getFragment())));
        decoder.jsonReader.beginObject();
        assertEquals(element, decoder.readXmlElement("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readNodeId() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        // IdType == UInt32, Namespace = 0
        var nodeId = new NodeId(0, 0);
        decoder.reset(new StringReader("{\"Id\":0}"));
        assertEquals(nodeId, decoder.readNodeId(null));

        // IdType == UInt32, Namespace != 0
        nodeId = new NodeId(1, 0);
        decoder.reset(new StringReader("{\"Id\":0,\"Namespace\":1}"));
        assertEquals(nodeId, decoder.readNodeId(null));

        // IdType == String, Namespace = 0
        nodeId = new NodeId(0, "foo");
        decoder.reset(new StringReader("{\"IdType\":1,\"Id\":\"foo\"}"));
        assertEquals(nodeId, decoder.readNodeId(null));

        // IdType == String, Namespace != 0
        nodeId = new NodeId(1, "foo");
        decoder.reset(new StringReader("{\"IdType\":1,\"Id\":\"foo\",\"Namespace\":1}"));
        assertEquals(nodeId, decoder.readNodeId(null));

        // IdType == Guid, Namespace = 0
        UUID uuid = UUID.randomUUID();
        nodeId = new NodeId(0, uuid);
        decoder.reset(new StringReader("{\"IdType\":2,\"Id\":\"" + uuid.toString().toUpperCase() + "\"}"));
        assertEquals(nodeId, decoder.readNodeId(null));

        // IdType == Guid, Namespace != 0
        nodeId = new NodeId(1, uuid);
        decoder.reset(new StringReader("{\"IdType\":2,\"Id\":\"" + uuid.toString().toUpperCase() + "\",\"Namespace\":1}"));
        assertEquals(nodeId, decoder.readNodeId(null));

        // IdType == ByteString, Namespace = 0
        ByteString bs = ByteString.of(randomBytes(16));
        nodeId = new NodeId(0, bs);
        decoder.reset(new StringReader("{\"IdType\":3,\"Id\":\"" + Base64.getEncoder().encodeToString(bs.bytesOrEmpty()) + "\"}"));
        assertEquals(nodeId, decoder.readNodeId(null));

        // IdType == ByteString, Namespace != 0
        nodeId = new NodeId(1, bs);
        decoder.reset(new StringReader("{\"IdType\":3,\"Id\":\"" + Base64.getEncoder().encodeToString(bs.bytesOrEmpty()) + "\",\"Namespace\":1}"));
        assertEquals(nodeId, decoder.readNodeId(null));

        nodeId = new NodeId(0, 0);
        decoder.reset(new StringReader("{\"foo\":{\"Id\":0}}"));
        decoder.jsonReader.beginObject();
        assertEquals(nodeId, decoder.readNodeId("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readExpandedNodeId() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        // namespace URI specified
        var xni = new ExpandedNodeId(ushort(0), "http://opcfoundation.org/UA/", "foo");
        decoder.reset(new StringReader("{\"IdType\":1,\"Id\":\"foo\",\"Namespace\":\"http://opcfoundation.org/UA/\"}"));
        assertEquals(xni, decoder.readExpandedNodeId(null));

        // remote server index
        xni = new ExpandedNodeId(ushort(0), null, "foo", uint(1));
        decoder.reset(new StringReader("{\"IdType\":1,\"Id\":\"foo\",\"ServerUri\":1}"));
        assertEquals(xni, decoder.readExpandedNodeId(null));

        decoder.reset(new StringReader("{\"foo\":{\"IdType\":1,\"Id\":\"foo\",\"ServerUri\":1}}"));
        decoder.jsonReader.beginObject();
        assertEquals(xni, decoder.readExpandedNodeId("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readStatusCode() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("0"));
        assertEquals(StatusCode.GOOD, decoder.readStatusCode(null));

        decoder.reset(new StringReader(String.valueOf(StatusCode.UNCERTAIN.getValue())));
        assertEquals(StatusCode.UNCERTAIN, decoder.readStatusCode(null));

        decoder.reset(new StringReader(String.valueOf(StatusCode.BAD.getValue())));
        assertEquals(StatusCode.BAD, decoder.readStatusCode(null));

        StatusCode[] statusCodes = new StatusCode[]{
            new StatusCode(StatusCodes.Good_Overload),
            new StatusCode(StatusCodes.Uncertain_InitialValue),
            new StatusCode(StatusCodes.Bad_DecodingError)
        };

        for (StatusCode statusCode : statusCodes) {
            decoder.reset(new StringReader(String.valueOf(statusCode.getValue())));
            assertEquals(statusCode, decoder.readStatusCode(null));
        }

        decoder.reset(new StringReader("{\"foo\":0}"));
        decoder.jsonReader.beginObject();
        assertEquals(StatusCode.GOOD, decoder.readStatusCode("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readQualifiedName() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("{}"));
        assertEquals(new QualifiedName(0, null), decoder.readQualifiedName(null));

        decoder.reset(new StringReader("{\"Uri\":1}"));
        assertEquals(new QualifiedName(1, null), decoder.readQualifiedName(null));

        decoder.reset(new StringReader("{\"Name\":\"foo\"}"));
        assertEquals(new QualifiedName(0, "foo"), decoder.readQualifiedName(null));

        decoder.reset(new StringReader("{\"Name\":\"foo\",\"Uri\":1}"));
        assertEquals(new QualifiedName(1, "foo"), decoder.readQualifiedName(null));

        decoder.reset(new StringReader("{\"foo\":{\"Name\":\"foo\"}}"));
        decoder.jsonReader.beginObject();
        assertEquals(new QualifiedName(0, "foo"), decoder.readQualifiedName("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readLocalizedText() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("{\"Locale\":\"en\",\"Text\":\"foo\"}"));
        assertEquals(LocalizedText.english("foo"), decoder.readLocalizedText(null));

        decoder.reset(new StringReader("{\"Locale\":\"en\"}"));
        assertEquals(new LocalizedText("en", null), decoder.readLocalizedText(null));

        decoder.reset(new StringReader("{\"Text\":\"foo\"}"));
        assertEquals(new LocalizedText(null, "foo"), decoder.readLocalizedText(null));

        decoder.reset(new StringReader("{\"foo\":{\"Locale\":\"en\",\"Text\":\"foo\"}}"));
        decoder.jsonReader.beginObject();
        assertEquals(LocalizedText.english("foo"), decoder.readLocalizedText("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readExtensionObject() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        var jsonStringXo = new ExtensionObject(
            "{\"foo\":\"bar\",\"baz\":42}",
            new NodeId(2, 42)
        );

        var byteStringXo = new ExtensionObject(
            ByteString.of(new byte[]{0x00, 0x01, 0x02, 0x03}),
            new NodeId(2, 42)
        );

        var xmlElementXo = new ExtensionObject(
            new XmlElement("<foo>bar</foo>"),
            new NodeId(2, 42)
        );

        decoder.reset(new StringReader("{\"TypeId\":{\"Id\":42,\"Namespace\":2},\"Body\":{\"foo\":\"bar\",\"baz\":42}}"));
        assertEquals(jsonStringXo, decoder.readExtensionObject(null));

        decoder.reset(new StringReader("{\"TypeId\":{\"Id\":42,\"Namespace\":2},\"Encoding\":1,\"Body\":\"AAECAw==\"}"));
        assertEquals(byteStringXo, decoder.readExtensionObject(null));

        decoder.reset(new StringReader("{\"TypeId\":{\"Id\":42,\"Namespace\":2},\"Encoding\":2,\"Body\":\"<foo>bar</foo>\"}"));
        assertEquals(xmlElementXo, decoder.readExtensionObject(null));

        decoder.reset(new StringReader("{\"foo\":{\"TypeId\":{\"Id\":42,\"Namespace\":2},\"Body\":{\"foo\":\"bar\",\"baz\":42}}}"));
        decoder.jsonReader.beginObject();
        assertEquals(jsonStringXo, decoder.readExtensionObject("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readDataValue() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        DateTime now = DateTime.now();
        String isoNow = now.toIso8601String();

        var allFieldsValue = new DataValue(
            new Variant("foo"),
            new StatusCode(StatusCodes.Good_Overload),
            now,
            ushort(100),
            now,
            ushort(200)
        );

        decoder.reset(new StringReader(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow, isoNow)));
        assertEquals(allFieldsValue, decoder.readDataValue(null));

        // omit "Value"
        decoder.reset(new StringReader(String.format("{\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow, isoNow)));
        assertEquals(allFieldsValue.copy(b -> b.setValue(Variant.NULL_VALUE)), decoder.readDataValue(null));

        // omit "Status"
        decoder.reset(new StringReader(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow, isoNow)));
        assertEquals(allFieldsValue.copy(b -> b.setStatus(StatusCode.GOOD)), decoder.readDataValue(null));

        // omit "SourceTimestamp"
        decoder.reset(new StringReader(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow)));
        assertEquals(allFieldsValue.copy(b -> b.setSourceTime(null)), decoder.readDataValue(null));

        // omit "SourcePicoseconds"
        decoder.reset(new StringReader(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}", isoNow, isoNow)));
        assertEquals(allFieldsValue.copy(b -> b.setSourcePicoseconds(null)), decoder.readDataValue(null));

        // omit "ServerTimestamp"
        decoder.reset(new StringReader(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerPicoseconds\":200}", isoNow)));
        assertEquals(allFieldsValue.copy(b -> b.setServerTime(null)), decoder.readDataValue(null));

        // omit "ServerPicoseconds"
        decoder.reset(new StringReader(String.format("{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\"}", isoNow, isoNow)));
        assertEquals(allFieldsValue.copy(b -> b.setServerPicoseconds(null)), decoder.readDataValue(null));

        // omit all fields
        decoder.reset(new StringReader("{}"));
        assertEquals(new DataValue(Variant.NULL_VALUE, StatusCode.GOOD, null), decoder.readDataValue(null));

        decoder.reset(new StringReader(String.format("{\"foo\":{\"Value\":{\"Type\":12,\"Body\":\"foo\"},\"Status\":3080192,\"SourceTimestamp\":\"%s\",\"SourcePicoseconds\":100,\"ServerTimestamp\":\"%s\",\"ServerPicoseconds\":200}}", isoNow, isoNow)));
        decoder.jsonReader.beginObject();
        assertEquals(allFieldsValue, decoder.readDataValue("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readVariant() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        decoder.reset(new StringReader("{\"Type\":1,\"Body\":true}"));
        assertEquals(new Variant(true), decoder.readVariant(null));

        decoder.reset(new StringReader("{\"Type\":20,\"Body\":{\"Name\":\"foo\",\"Uri\":1}}"));
        assertEquals(new Variant(new QualifiedName(1, "foo")), decoder.readVariant(null));

        decoder.reset(new StringReader("{\"Type\":24,\"Body\":[{\"Type\":12,\"Body\":\"foo\"},{\"Type\":12,\"Body\":\"bar\"}]}"));
        assertEquals(new Variant(new Variant[]{new Variant("foo"), new Variant("bar")}), decoder.readVariant(null));

        int[] value1d = {0, 1, 2, 3};
        int[][] value2d = {
            {0, 2, 3},
            {1, 3, 4}
        };
        int[][][] value3d = {
            {
                {0, 1},
                {2, 3}
            },
            {
                {4, 5},
                {6, 7},
            }
        };

        decoder.reset(new StringReader("{\"Type\":6,\"Body\":[0,1,2,3]}"));
        assertEquals(new Variant(value1d), decoder.readVariant(null));

        decoder.reset(new StringReader("{\"Type\":6,\"Body\":[0,2,3,1,3,4],\"Dimensions\":[2,3]}"));
        assertEquals(new Variant(value2d), decoder.readVariant(null));

        decoder.reset(new StringReader("{\"Type\":6,\"Body\":[0,1,2,3,4,5,6,7],\"Dimensions\":[2,2,2]}"));
        assertEquals(new Variant(value3d), decoder.readVariant(null));

        decoder.reset(new StringReader("{\"foo\":{\"Type\":1,\"Body\":true}}"));
        decoder.jsonReader.beginObject();
        assertEquals(new Variant(true), decoder.readVariant("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    void readDiagnosticInfo() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        var diagnosticInfo = new DiagnosticInfo(
            0,
            1,
            2,
            3,
            "foo",
            null,
            null
        );

        var nestedDiagnosticInfo = new DiagnosticInfo(
            4,
            5,
            6,
            7,
            "bar",
            StatusCode.GOOD,
            diagnosticInfo
        );

        decoder.reset(new StringReader("{\"SymbolicId\":1,\"NamespaceUri\":0,\"Locale\":2,\"LocalizedText\":3,\"AdditionalInfo\":\"foo\"}"));
        assertEquals(diagnosticInfo, decoder.readDiagnosticInfo(null));

        decoder.reset(new StringReader("{\"SymbolicId\":5,\"NamespaceUri\":4,\"Locale\":6,\"LocalizedText\":7,\"AdditionalInfo\":\"bar\",\"InnerStatusCode\":0,\"InnerDiagnosticInfo\":{\"SymbolicId\":1,\"NamespaceUri\":0,\"Locale\":2,\"LocalizedText\":3,\"AdditionalInfo\":\"foo\"}}"));
        assertEquals(nestedDiagnosticInfo, decoder.readDiagnosticInfo(null));

        decoder.reset(new StringReader("{\"foo\":{\"SymbolicId\":1,\"NamespaceUri\":0,\"Locale\":2,\"LocalizedText\":3,\"AdditionalInfo\":\"foo\"}}"));
        decoder.jsonReader.beginObject();
        assertEquals(diagnosticInfo, decoder.readDiagnosticInfo("foo"));
        decoder.jsonReader.endObject();
    }

    @Test
    @Disabled
    void readMessage() throws IOException {
        // TODO
    }

    @Test
    void readEnum() throws IOException {
        var decoder = new OpcUaJsonDecoder(context, new StringReader(""));

        for (ApplicationType applicationType : ApplicationType.values()) {
            decoder.reset(new StringReader(String.valueOf(applicationType.getValue())));
            assertEquals(applicationType, decoder.readEnum(null, ApplicationType.class));
        }

        decoder.reset(new StringReader("{\"foo\":0}"));
        decoder.jsonReader.beginObject();
        assertEquals(ApplicationType.Server, decoder.readEnum("foo", ApplicationType.class));
        decoder.jsonReader.endObject();
    }

    @Test
    @Disabled
    void readStruct() throws IOException {
        // TODO
    }

    private static byte[] randomBytes(int length) {
        var random = new Random();
        var bs = new byte[length];
        random.nextBytes(bs);
        return bs;
    }

}
