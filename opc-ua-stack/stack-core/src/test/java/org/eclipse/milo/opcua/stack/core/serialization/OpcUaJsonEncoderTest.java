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
import java.io.StringWriter;
import java.util.Base64;
import java.util.Random;
import java.util.UUID;

import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.QualifiedName;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UByte;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.ULong;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UShort;
import org.junit.jupiter.api.Test;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OpcUaJsonEncoderTest {
    
    @Test
    void writeBoolean() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeBoolean(null, true);
        assertEquals("true", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeBoolean(null, false);
        assertEquals("false", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeBoolean("foo", true);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":true}", writer.toString());
    }

    @Test
    public void writeSByte() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeSByte(null, (byte) 0);
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeSByte(null, Byte.MIN_VALUE);
        assertEquals(String.valueOf(Byte.MIN_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeSByte(null, Byte.MAX_VALUE);
        assertEquals(String.valueOf(Byte.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeSByte("foo", (byte) 0);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeInt16() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeInt16(null, (short) 0);
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt16(null, Short.MIN_VALUE);
        assertEquals(String.valueOf(Short.MIN_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt16(null, Short.MAX_VALUE);
        assertEquals(String.valueOf(Short.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeInt16("foo", (short) 0);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeInt32() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeInt32(null, 0);
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt32(null, Integer.MIN_VALUE);
        assertEquals(String.valueOf(Integer.MIN_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt32(null, Integer.MAX_VALUE);
        assertEquals(String.valueOf(Integer.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeInt32("foo", 0);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeInt64() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // Int64 and UInt64 values shall be formatted as a decimal number
        // encoded as a JSON string.

        encoder.writeInt64(null, 0L);
        assertEquals("\"0\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt64(null, Long.MIN_VALUE);
        assertEquals("\"" + Long.MIN_VALUE + "\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeInt64(null, Long.MAX_VALUE);
        assertEquals("\"" + Long.MAX_VALUE + "\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeInt64("foo", Long.MAX_VALUE);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"" + Long.MAX_VALUE + "\"}", writer.toString());
    }

    @Test
    public void writeByte() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeByte(null, ubyte(0));
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeByte(null, UByte.MAX);
        assertEquals(String.valueOf(UByte.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeByte("foo", ubyte(0));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeUInt16() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeUInt16(null, ushort(0));
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeUInt16(null, UShort.MAX);
        assertEquals(String.valueOf(UShort.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeUInt16("foo", ushort(0));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeUInt32() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeUInt32(null, uint(0));
        assertEquals("0", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeUInt32(null, UInteger.MAX);
        assertEquals(String.valueOf(UInteger.MAX_VALUE), writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeUInt32("foo", uint(0));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":0}", writer.toString());
    }

    @Test
    public void writeUInt64() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // Int64 and UInt64 values shall be formatted as a decimal number
        // encoded as a JSON string (See the XML encoding of 64-bit values
        // described in 5.3.1.3).

        {
            encoder.writeUInt64(null, ULong.MIN);
            assertEquals("\"" + ULong.MIN_VALUE + "\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeUInt64(null, ULong.MAX);
            assertEquals("\"" + ULong.MAX_VALUE + "\"", writer.toString());

        }

        {
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeUInt64("foo", ULong.MAX);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":\"" + ULong.MAX_VALUE + "\"}", writer.toString());
        }
    }

    @Test
    public void writeFloat() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        {
            // Normal Float and Double values shall be encoded as a JSON number.

            encoder.writeFloat(null, 0.0f);
            assertEquals("0.0", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.MIN_VALUE);
            assertEquals(String.valueOf(Float.MIN_VALUE), writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.MAX_VALUE);
            assertEquals(String.valueOf(Float.MAX_VALUE), writer.toString());

            // Special floating-point numbers such as positive infinity (INF),
            // negative infinity (-INF) and not-a- number (NaN) shall be
            // represented by the values "Infinity", "-Infinity" and "NaN" encoded
            // as a JSON string.

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.POSITIVE_INFINITY);
            assertEquals("\"Infinity\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.NEGATIVE_INFINITY);
            assertEquals("\"-Infinity\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeFloat(null, Float.NaN);
            assertEquals("\"NaN\"", writer.toString());
        }

        {
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeFloat("foo", 0.0f);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":0.0}", writer.toString());
        }
    }

    @Test
    public void writeDouble() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        {
            // Normal Float and Double values shall be encoded as a JSON number.

            encoder.writeDouble(null, 0.0);
            assertEquals("0.0", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.MIN_VALUE);
            assertEquals(String.valueOf(Double.MIN_VALUE), writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.MAX_VALUE);
            assertEquals(String.valueOf(Double.MAX_VALUE), writer.toString());

            // Special floating-point numbers such as positive infinity (INF),
            // negative infinity (-INF) and not-a- number (NaN) shall be
            // represented by the values "Infinity", "-Infinity" and "NaN" encoded
            // as a JSON string.

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.POSITIVE_INFINITY);
            assertEquals("\"Infinity\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.NEGATIVE_INFINITY);
            assertEquals("\"-Infinity\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDouble(null, Double.NaN);
            assertEquals("\"NaN\"", writer.toString());
        }

        {
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeDouble("foo", 0.0);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":0.0}", writer.toString());
        }
    }

    @Test
    public void writeString() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // String values shall be encoded as JSON strings.
        // Any characters which are not allowed in JSON strings are escaped
        // using the rules defined in RFC 7159.

        encoder.writeString(null, "");
        assertEquals("\"\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeString(null, "foo");
        assertEquals("\"foo\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeString(null, "\"quoted\"");
        assertEquals("\"\\\"quoted\\\"\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeString("foo", "bar");
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"bar\"}", writer.toString());
    }

    @Test
    public void writeDateTime() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        {
            // DateTime values shall be formatted as specified by ISO 8601:2004
            // and encoded as a JSON string.

            encoder.writeDateTime(null, OpcUaJsonEncoder.MIN_DATE_TIME);
            assertEquals("\"" + OpcUaJsonEncoder.MIN_ISO_8601 + "\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDateTime(null, OpcUaJsonEncoder.MAX_DATE_TIME);
            assertEquals("\"9999-12-31T23:59:59Z\"", writer.toString());

            DateTime now = DateTime.now();
            String isoNow = OpcUaJsonEncoder.dateTimeToIso8601UtcString(now);
            encoder.reset(writer = new StringWriter());
            encoder.writeDateTime(null, now);
            assertEquals("\"" + isoNow + "\"", writer.toString());

            // DateTime values which exceed the minimum or maximum values supported
            // on a platform shall be encoded as "0001-01-01T00:00:00Z" or
            // "9999-12-31T23:59:59Z" respectively. During decoding, these values
            // shall be converted to the minimum or maximum values supported on the
            // platform.

            encoder.reset(writer = new StringWriter());
            encoder.writeDateTime(null, new DateTime(OpcUaJsonEncoder.MIN_DATE_TIME.getUtcTime() - 1));
            assertEquals("\"" + OpcUaJsonEncoder.MIN_ISO_8601 + "\"", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeDateTime(null, new DateTime(OpcUaJsonEncoder.MAX_DATE_TIME.getUtcTime() + 1));
            assertEquals("\"" + OpcUaJsonEncoder.MAX_ISO_8601 + "\"", writer.toString());
        }

        {
            DateTime now = DateTime.now();
            String isoNow = OpcUaJsonEncoder.dateTimeToIso8601UtcString(now);

            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeDateTime("foo", now);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":\"" + isoNow + "\"}", writer.toString());
        }
    }

    @Test
    public void writeGuid() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeGuid(null, UUID.fromString("00000000-0000-0000-0000-000000000000"));
        assertEquals("\"00000000-0000-0000-0000-000000000000\"", writer.toString());

        UUID uuid = UUID.randomUUID();
        encoder.reset(writer = new StringWriter());
        encoder.writeGuid(null, uuid);
        assertEquals("\"" + uuid.toString().toUpperCase() + "\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeGuid("foo", uuid);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"" + uuid.toString().toUpperCase() + "\"}", writer.toString());
    }

    @Test
    public void writeByteString() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // ByteString values shall be formatted as a Base64 text and encoded as
        // a JSON string.
        // Any characters which are not allowed in JSON strings are escaped
        // using the rules defined in RFC 7159.

        for (int i = 0; i < 100; i++) {
            ByteString bs = ByteString.of(randomBytes(16 * i));
            encoder.reset(writer = new StringWriter());
            encoder.writeByteString(null, bs);
            assertEquals("\"" + Base64.getEncoder().encodeToString(bs.bytes()) + "\"", writer.toString());
        }

        ByteString bs = ByteString.of(randomBytes(16));
        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeByteString("foo", bs);
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"" + Base64.getEncoder().encodeToString(bs.bytes()) + "\"}", writer.toString());
    }

    @Test
    public void writeXmlElement() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeXmlElement(null, new XmlElement(""));
        assertEquals("\"\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeXmlElement(null, new XmlElement("<foo>bar</foo>"));
        assertEquals("\"<foo>bar</foo>\"", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeXmlElement("foo", new XmlElement("<foo>bar</foo>"));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":\"<foo>bar</foo>\"}", writer.toString());
    }

    @Test
    public void writeNodeId() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // IdType == UInt32, Namespace = 0, reversible
        encoder.writeNodeId(null, new NodeId(0, uint(0)));
        assertEquals("{\"Id\":0}", writer.toString());

        // IdType == UInt32, Namespace != 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, uint(0)));
        assertEquals("{\"Id\":0,\"Namespace\":1}", writer.toString());

        // IdType == String, Namespace = 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(0, "foo"));
        assertEquals("{\"IdType\":1,\"Id\":\"foo\"}", writer.toString());

        // IdType == String, Namespace != 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, "foo"));
        assertEquals("{\"IdType\":1,\"Id\":\"foo\",\"Namespace\":1}", writer.toString());

        // IdType == Guid, Namespace = 0, reversible
        UUID uuid = UUID.randomUUID();
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(0, uuid));
        assertEquals("{\"IdType\":2,\"Id\":\"" + uuid.toString().toUpperCase() + "\"}", writer.toString());

        // IdType == Guid, Namespace != 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, uuid));
        assertEquals("{\"IdType\":2,\"Id\":\"" + uuid.toString().toUpperCase() + "\",\"Namespace\":1}", writer.toString());

        // IdType == ByteString, Namespace = 0, reversible
        ByteString bs = ByteString.of(randomBytes(16));
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(0, bs));
        assertEquals("{\"IdType\":3,\"Id\":\"" + Base64.getEncoder().encodeToString(bs.bytesOrEmpty()) + "\"}", writer.toString());

        // IdType == ByteString, Namespace != 0, reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, bs));
        assertEquals("{\"IdType\":3,\"Id\":\"" + Base64.getEncoder().encodeToString(bs.bytesOrEmpty()) + "\",\"Namespace\":1}", writer.toString());

        encoder.reversible = false;
        encoder.serializationContext = new TestSerializationContext();
        encoder.serializationContext.getNamespaceTable().addUri("urn:eclipse:milo:test1");
        encoder.serializationContext.getNamespaceTable().addUri("urn:eclipse:milo:test2");

        // IdType == UInt32, Namespace = 0, non-reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(0, uint(0)));
        assertEquals("{\"Id\":0}", writer.toString());

        // IdType == UInt32, Namespace = 1, non-reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(1, uint(0)));
        assertEquals("{\"Id\":0,\"Namespace\":1}", writer.toString());

        // IdType == UInt32, Namespace > 1, non-reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(2, uint(0)));
        assertEquals("{\"Id\":0,\"Namespace\":\"urn:eclipse:milo:test2\"}", writer.toString());

        // Namespace > 1 but not in table, non-reversible
        encoder.reset(writer = new StringWriter());
        encoder.writeNodeId(null, new NodeId(99, uint(0)));
        assertEquals("{\"Id\":0,\"Namespace\":99}", writer.toString());

        // key != null
        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeNodeId("foo", new NodeId(1, "foo"));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":{\"IdType\":1,\"Id\":\"foo\",\"Namespace\":1}}", writer.toString());
    }

    @Test
    public void writeExpandedNodeId() {
        // TODO
    }

    @Test
    public void writeStatusCode() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        // reversible form
        {
            encoder.writeStatusCode(null, StatusCode.GOOD);
            assertEquals("0", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, new StatusCode(StatusCodes.Uncertain_InitialValue));
            assertEquals(Long.toString(StatusCodes.Uncertain_InitialValue), writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, new StatusCode(StatusCodes.Bad_UnexpectedError));
            assertEquals(Long.toString(StatusCodes.Bad_UnexpectedError), writer.toString());
        }

        // non-reversible form
        {
            encoder.reversible = false;
            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, StatusCode.GOOD);
            assertEquals("", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, new StatusCode(StatusCodes.Uncertain_InitialValue));
            assertEquals("{\"Code\":1083310080,\"Symbol\":\"Uncertain_InitialValue\"}", writer.toString());

            encoder.reset(writer = new StringWriter());
            encoder.writeStatusCode(null, new StatusCode(StatusCodes.Bad_UnexpectedError));
            assertEquals("{\"Code\":2147549184,\"Symbol\":\"Bad_UnexpectedError\"}", writer.toString());
        }

        // reversible form with field
        {
            encoder.reversible = true;
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeStatusCode("foo", StatusCode.GOOD);
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":0}", writer.toString());
        }

        // non-reversible form with field
        {
            encoder.reversible = false;
            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeStatusCode("foo", StatusCode.GOOD);
            encoder.jsonWriter.endObject();
            assertEquals("{}", writer.toString()); // key/value omitted because code==0

            encoder.reset(writer = new StringWriter());
            encoder.jsonWriter.beginObject();
            encoder.writeStatusCode("foo", new StatusCode(StatusCodes.Uncertain_InitialValue));
            encoder.jsonWriter.endObject();
            assertEquals("{\"foo\":{\"Code\":1083310080,\"Symbol\":\"Uncertain_InitialValue\"}}", writer.toString());
        }
    }

    @Test
    public void writeQualifiedName() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeQualifiedName(null, new QualifiedName(0, "foo"));
        assertEquals("{\"Name\":\"foo\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(1, "foo"));
        assertEquals("{\"Name\":\"foo\",\"Uri\":1}", writer.toString());

        encoder.reversible = false;
        encoder.serializationContext = new TestSerializationContext();
        encoder.serializationContext.getNamespaceTable().addUri("urn:eclipse:milo:test1");
        encoder.serializationContext.getNamespaceTable().addUri("urn:eclipse:milo:test2");

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(0, "foo"));
        assertEquals("{\"Name\":\"foo\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(1, "foo"));
        assertEquals("{\"Name\":\"foo\",\"Uri\":1}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(2, "foo"));
        assertEquals("{\"Name\":\"foo\",\"Uri\":\"urn:eclipse:milo:test2\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeQualifiedName(null, new QualifiedName(99, "foo"));
        assertEquals("{\"Name\":\"foo\",\"Uri\":99}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeQualifiedName("foo", new QualifiedName(0, "foo"));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":{\"Name\":\"foo\"}}", writer.toString());
    }

    @Test
    public void writeLocalizedText() throws IOException {
        var writer = new StringWriter();
        var encoder = new OpcUaJsonEncoder(writer);

        encoder.writeLocalizedText(null, LocalizedText.english("foo"));
        assertEquals("{\"Locale\":\"en\",\"Text\":\"foo\"}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeLocalizedText(null, new LocalizedText("en", null));
        assertEquals("{\"Locale\":\"en\",\"Text\":null}", writer.toString());

        encoder.reset(writer = new StringWriter());
        encoder.writeLocalizedText(null, new LocalizedText(null, "foo"));
        assertEquals("{\"Locale\":null,\"Text\":\"foo\"}", writer.toString());

        encoder.reversible = false;
        encoder.reset(writer = new StringWriter());
        encoder.writeLocalizedText(null, LocalizedText.english("foo"));
        assertEquals("\"foo\"", writer.toString());

        encoder.reversible = true;
        encoder.reset(writer = new StringWriter());
        encoder.jsonWriter.beginObject();
        encoder.writeLocalizedText("foo", LocalizedText.english("foo"));
        encoder.jsonWriter.endObject();
        assertEquals("{\"foo\":{\"Locale\":\"en\",\"Text\":\"foo\"}}", writer.toString());
    }

    @Test
    public void writeExtensionObject() {
        // TODO
    }

    @Test
    public void writeDataValue() {
        // TODO
    }

    @Test
    public void writeVariant() {
        // TODO
    }

    @Test
    public void writeDiagnosticInfo() {
        // TODO
    }

    private static byte[] randomBytes(int length) {
        var random = new Random();
        var bs = new byte[length];
        random.nextBytes(bs);
        return bs;
    }

}
