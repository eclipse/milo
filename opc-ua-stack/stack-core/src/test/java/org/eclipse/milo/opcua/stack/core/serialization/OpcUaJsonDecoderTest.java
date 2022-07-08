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

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OpcUaJsonDecoderTest {

    @Test
    void readBoolean() throws IOException {
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
        var decoder = new OpcUaJsonDecoder(new StringReader(""));

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
    void readStatusCode() throws IOException {}

    @Test
    void readQualifiedName() throws IOException {}

    @Test
    void readLocalizedText() throws IOException {}

    @Test
    void readExtensionObject() throws IOException {}

    @Test
    void readDataValue() throws IOException {}

    @Test
    void readVariant() throws IOException {}

    @Test
    void readDiagnosticInfo() throws IOException {}

    @Test
    void readMessage() throws IOException {}

    @Test
    void readEnum() throws IOException {}

    @Test
    void readStruct() throws IOException {}

    private static byte[] randomBytes(int length) {
        var random = new Random();
        var bs = new byte[length];
        random.nextBytes(bs);
        return bs;
    }

}
