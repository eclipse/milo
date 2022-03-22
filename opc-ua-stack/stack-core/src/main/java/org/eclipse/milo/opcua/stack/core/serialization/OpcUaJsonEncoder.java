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
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.TimeZone;
import java.util.UUID;
import java.util.function.BiConsumer;

import com.google.gson.stream.JsonWriter;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;

public class OpcUaJsonEncoder implements UaEncoder {

    /**
     * Shared ISO-8601 {@link DateFormat} instance.
     * <p>
     * Do not access directly; use {@link #dateTimeToIso8601UtcString(DateTime)}.
     */
    private static final DateFormat ISO_8601_UTC_DATE_FORMAT;

    static {
        ISO_8601_UTC_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        ISO_8601_UTC_DATE_FORMAT.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /**
     * Minimum ISO-8601 formatted string.
     */
    static final String MIN_ISO_8601 = "0001-01-01T00:00:00Z";

    /**
     * Maximum ISO-8601 formatted string.
     */
    static final String MAX_ISO_8601 = "9999-12-31T23:59:59Z";

    /**
     * Minimum DateTime value that can be formatted in ISO-8601, i.e.
     * "0001-01-01T00:00:00Z".
     */
    static final DateTime MIN_DATE_TIME = new DateTime(-504912960000000000L);

    /**
     * Maximum DateTime value that can be formatted in ISO-8601, i.e.
     * "9999-12-31T23:59:59Z".
     */
    static final DateTime MAX_DATE_TIME = new DateTime(2650467743990000000L);

    boolean reversible = true;
    SerializationContext serializationContext;

    JsonWriter jsonWriter;

    public OpcUaJsonEncoder(Writer writer) {
        jsonWriter = new JsonWriter(writer);
        jsonWriter.setHtmlSafe(false);
    }

    public OpcUaJsonEncoder(OutputStream outputStream) {
        this(new OutputStreamWriter(outputStream));
    }

    /**
     * Reset this encoder with a new {@link Writer} to write to.
     *
     * @param writer the new {@link Writer} to write to.
     */
    void reset(Writer writer) {
        jsonWriter = new JsonWriter(writer);
        jsonWriter.setHtmlSafe(false);
    }

    /**
     * Reset this encoder with a new {@link OutputStream} to write to.
     *
     * @param outputStream the new {@link OutputStream} to write to.
     */
    void reset(OutputStream outputStream) {
        jsonWriter = new JsonWriter(new OutputStreamWriter(outputStream));
        jsonWriter.setHtmlSafe(false);
    }

    /**
     * Format {@param dateTime} as an ISO-8601 String.
     *
     * @param dateTime the {@link DateTime} to format.
     * @return an ISO-8601 formatted date string.
     */
    static String dateTimeToIso8601UtcString(DateTime dateTime) {
        synchronized (ISO_8601_UTC_DATE_FORMAT) {
            return ISO_8601_UTC_DATE_FORMAT.format(dateTime.getJavaDate());
        }
    }

    @Override
    public void writeBoolean(String field, Boolean value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value);
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeSByte(String field, Byte value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value);
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeInt16(String field, Short value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value);
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeInt32(String field, Integer value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value);
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeInt64(String field, Long value) throws UaSerializationException {
        // Int64 and UInt64 values shall be formatted as a decimal number
        // encoded as a JSON string.

        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value.toString());
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeByte(String field, UByte value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value.shortValue());
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeUInt16(String field, UShort value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value.intValue());
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeUInt32(String field, UInteger value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value.longValue());
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeUInt64(String field, ULong value) throws UaSerializationException {
        // Int64 and UInt64 values shall be formatted as a decimal number
        // encoded as a JSON string (See the XML encoding of 64-bit values
        // described in 5.3.1.3).

        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value.toString());
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeFloat(String field, Float value) throws UaSerializationException {
        // Normal Float and Double values shall be encoded as a JSON number.
        // Special floating-point numbers such as positive infinity (INF),
        // negative infinity (-INF) and not-a- number (NaN) shall be
        // represented by the values "Infinity", "-Infinity" and "NaN" encoded
        // as a JSON string.

        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            if (value == Float.POSITIVE_INFINITY) {
                jsonWriter.value("Infinity");
            } else if (value == Float.NEGATIVE_INFINITY) {
                jsonWriter.value("-Infinity");
            } else if (value.isNaN()) {
                jsonWriter.value("NaN");
            } else {
                jsonWriter.value(value);
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeDouble(String field, Double value) throws UaSerializationException {
        // Normal Float and Double values shall be encoded as a JSON number.
        // Special floating-point numbers such as positive infinity (INF),
        // negative infinity (-INF) and not-a- number (NaN) shall be
        // represented by the values "Infinity", "-Infinity" and "NaN" encoded
        // as a JSON string.

        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            if (value == Double.POSITIVE_INFINITY) {
                jsonWriter.value("Infinity");
            } else if (value == Double.NEGATIVE_INFINITY) {
                jsonWriter.value("-Infinity");
            } else if (value.isNaN()) {
                jsonWriter.value("NaN");
            } else {
                jsonWriter.value(value);
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeString(String field, String value) throws UaSerializationException {
        // String values shall be encoded as JSON strings.
        // Any characters which are not allowed in JSON strings are escaped
        // using the rules defined in RFC 7159.

        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value);
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeDateTime(String field, DateTime value) throws UaSerializationException {
        // DateTime values shall be formatted as specified by ISO 8601:2004
        // and encoded as a JSON string.
        // DateTime values which exceed the minimum or maximum values supported
        // on a platform shall be encoded as "0001-01-01T00:00:00Z" or
        // "9999-12-31T23:59:59Z" respectively. During decoding, these values
        // shall be converted to the minimum or maximum values supported on the
        // platform.

        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            if (value.getUtcTime() < MIN_DATE_TIME.getUtcTime()) {
                jsonWriter.value(MIN_ISO_8601);
            } else if (value.getUtcTime() > MAX_DATE_TIME.getUtcTime()) {
                jsonWriter.value(MAX_ISO_8601);
            } else {
                jsonWriter.value(dateTimeToIso8601UtcString(value));
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeGuid(String field, UUID value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value.toString().toUpperCase());
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeByteString(String field, ByteString value) throws UaSerializationException {
        // ByteString values shall be formatted as a Base64 text and encoded as
        // a JSON string.
        // Any characters which are not allowed in JSON strings are escaped
        // using the rules defined in RFC 7159.

        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(Base64.getEncoder().encodeToString(value.bytesOrEmpty()));
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeXmlElement(String field, XmlElement value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.value(value.getFragmentOrEmpty());
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeNodeId(String field, NodeId value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.beginObject();
            writeNodeIdCommonFields(value.getType(), value.getIdentifier(), value.getNamespaceIndex().intValue());
            jsonWriter.endObject();
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeExpandedNodeId(String field, ExpandedNodeId value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }
            jsonWriter.beginObject();
            if (value.isAbsolute()) {
                writeNodeIdCommonFields(value.getType(), value.getIdentifier(), 0);
                jsonWriter.name("Namespace").value(value.getNamespaceUri());
            } else {
                writeNodeIdCommonFields(value.getType(), value.getIdentifier(), value.getNamespaceIndex().intValue());
            }
            if (!value.isLocal()) {
                int serverIndex = value.getServerIndex().intValue();
                if (reversible) {
                    jsonWriter.name("ServerUri").value(serverIndex);
                } else {
                    String serverUri = serializationContext.getServerTable().get(serverIndex);
                    if (serverUri != null) {
                        jsonWriter.name("ServerUri").value(serverUri);
                    } else {
                        jsonWriter.name("ServerUri").value(serverIndex);
                    }
                }
            }
            jsonWriter.endObject();
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    /**
     * Writes the fields that are common to both NodeId and ExpandedNodeId.
     */
    private void writeNodeIdCommonFields(IdType idType, Object identifier, int namespaceIndex) throws IOException {
        if (idType != IdType.Numeric) {
            jsonWriter.name("IdType").value(idType.getValue());
        }
        jsonWriter.name("Id");
        switch (idType) {
            case Numeric:
                jsonWriter.value((Number) identifier);
                break;
            case String:
                jsonWriter.value((String) identifier);
                break;
            case Guid: {
                UUID uuid = (UUID) identifier;
                jsonWriter.value(uuid.toString().toUpperCase());
                break;
            }
            case Opaque: {
                ByteString bs = (ByteString) identifier;
                jsonWriter.value(Base64.getEncoder().encodeToString(bs.bytesOrEmpty()));
                break;
            }
        }
        if (namespaceIndex > 0) {
            if (reversible || namespaceIndex == 1) {
                jsonWriter.name("Namespace").value(namespaceIndex);
            } else {
                String namespaceUri = serializationContext.getNamespaceTable().get(namespaceIndex);
                if (namespaceUri != null) {
                    jsonWriter.name("Namespace").value(namespaceUri);
                } else {
                    jsonWriter.name("Namespace").value(namespaceIndex);
                }
            }
        }
    }

    @Override
    public void writeStatusCode(String field, StatusCode value) throws UaSerializationException {
        // StatusCode values shall be encoded as a JSON number for the
        // reversible encoding.
        //
        // For the non-reversible form, StatusCode values shall be encoded as
        // a JSON object with the fields defined as follows:
        //
        // "Code":
        // The numeric code encoded as a JSON number.
        // The Code is omitted if the numeric code is 0 (Good).
        //
        // "Symbol":
        // The string literal associated with the numeric code encoded as JSON
        // string. e.g. 0x80AB0000 has the associated literal
        // “BadInvalidArgument”.
        // The Symbol is omitted if the numeric code is 0 (Good).
        //
        // A StatusCode of Good (0) is treated like a NULL and not encoded. If
        // it is an element of an JSON array it is encoded as the JSON literal
        // `null`.

        try {
            long code = value.getValue();

            if (reversible) {
                if (field != null) {
                    jsonWriter.name(field);
                }

                jsonWriter.value(code);
            } else {
                if (code != 0L) {
                    if (field != null) {
                        jsonWriter.name(field);
                    }

                    String symbol = StatusCodes.lookup(code).map(ss -> ss[0]).orElse("");

                    jsonWriter.beginObject();
                    jsonWriter.name("Code").value(code);
                    jsonWriter.name("Symbol").value(symbol);
                    jsonWriter.endObject();
                }
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeQualifiedName(String field, QualifiedName value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }

            jsonWriter.beginObject();
            jsonWriter.name("Name").value(value.getName());
            int namespaceIndex = value.getNamespaceIndex().intValue();
            if (namespaceIndex > 0) {
                if (reversible || namespaceIndex == 1) {
                    jsonWriter.name("Uri").value(namespaceIndex);
                } else {
                    String namespaceUri = serializationContext.getNamespaceTable().get(namespaceIndex);
                    if (namespaceUri != null) {
                        jsonWriter.name("Uri").value(namespaceUri);
                    } else {
                        jsonWriter.name("Uri").value(namespaceIndex);
                    }
                }
            }
            jsonWriter.endObject();
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeLocalizedText(String field, LocalizedText value) throws UaSerializationException {
        try {
            if (field != null) {
                jsonWriter.name(field);
            }

            if (reversible) {
                jsonWriter.beginObject();
                jsonWriter.name("Locale").value(value.getLocale());
                jsonWriter.name("Text").value(value.getText());
                jsonWriter.endObject();
            } else {
                jsonWriter.value(value.getText());
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public void writeExtensionObject(String field, ExtensionObject value) throws UaSerializationException {

    }

    @Override
    public void writeDataValue(String field, DataValue value) throws UaSerializationException {

    }

    @Override
    public void writeVariant(String field, Variant value) throws UaSerializationException {

    }

    @Override
    public void writeDiagnosticInfo(String field, DiagnosticInfo value) throws UaSerializationException {

    }

    @Override
    public void writeMessage(String field, UaMessage message) throws UaSerializationException {

    }

    @Override
    public void writeEnum(String field, UaEnumeration value) throws UaSerializationException {

    }

    @Override
    public void writeStruct(String field, Object value, NodeId dataTypeId) throws UaSerializationException {

    }

    @Override
    public void writeStruct(String field, Object value, ExpandedNodeId dataTypeId) throws UaSerializationException {

    }

    @Override
    public void writeStruct(String field, Object value, DataTypeCodec codec) throws UaSerializationException {

    }

    @Override
    public void writeBooleanArray(String field, Boolean[] value) throws UaSerializationException {

    }

    @Override
    public void writeSByteArray(String field, Byte[] value) throws UaSerializationException {

    }

    @Override
    public void writeInt16Array(String field, Short[] value) throws UaSerializationException {

    }

    @Override
    public void writeInt32Array(String field, Integer[] value) throws UaSerializationException {

    }

    @Override
    public void writeInt64Array(String field, Long[] value) throws UaSerializationException {

    }

    @Override
    public void writeByteArray(String field, UByte[] value) throws UaSerializationException {

    }

    @Override
    public void writeUInt16Array(String field, UShort[] value) throws UaSerializationException {

    }

    @Override
    public void writeUInt32Array(String field, UInteger[] value) throws UaSerializationException {

    }

    @Override
    public void writeUInt64Array(String field, ULong[] value) throws UaSerializationException {

    }

    @Override
    public void writeFloatArray(String field, Float[] value) throws UaSerializationException {

    }

    @Override
    public void writeDoubleArray(String field, Double[] value) throws UaSerializationException {

    }

    @Override
    public void writeStringArray(String field, String[] value) throws UaSerializationException {

    }

    @Override
    public void writeDateTimeArray(String field, DateTime[] value) throws UaSerializationException {

    }

    @Override
    public void writeGuidArray(String field, UUID[] value) throws UaSerializationException {

    }

    @Override
    public void writeByteStringArray(String field, ByteString[] value) throws UaSerializationException {

    }

    @Override
    public void writeXmlElementArray(String field, XmlElement[] value) throws UaSerializationException {

    }

    @Override
    public void writeNodeIdArray(String field, NodeId[] value) throws UaSerializationException {

    }

    @Override
    public void writeExpandedNodeIdArray(String field, ExpandedNodeId[] value) throws UaSerializationException {

    }

    @Override
    public void writeStatusCodeArray(String field, StatusCode[] value) throws UaSerializationException {

    }

    @Override
    public void writeQualifiedNameArray(String field, QualifiedName[] value) throws UaSerializationException {

    }

    @Override
    public void writeLocalizedTextArray(String field, LocalizedText[] value) throws UaSerializationException {

    }

    @Override
    public void writeExtensionObjectArray(String field, ExtensionObject[] value) throws UaSerializationException {

    }

    @Override
    public void writeDataValueArray(String field, DataValue[] value) throws UaSerializationException {

    }

    @Override
    public void writeVariantArray(String field, Variant[] value) throws UaSerializationException {

    }

    @Override
    public void writeDiagnosticInfoArray(String field, DiagnosticInfo[] value) throws UaSerializationException {

    }

    @Override
    public void writeEnumArray(String field, UaEnumeration[] value) throws UaSerializationException {

    }

    @Override
    public void writeStructArray(String field, Object[] value, NodeId dataTypeId) throws UaSerializationException {

    }

    @Override
    public void writeStructArray(String field, Object[] value, ExpandedNodeId dataTypeId) throws UaSerializationException {

    }

    @Override
    public <T> void writeArray(String field, T[] values, BiConsumer<String, T> encoder) throws UaSerializationException {

    }

}
