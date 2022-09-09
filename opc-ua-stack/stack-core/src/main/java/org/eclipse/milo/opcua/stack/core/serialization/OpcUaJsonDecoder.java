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
import java.io.Reader;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.UUID;
import java.util.function.Function;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultJsonEncoding;
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
import org.eclipse.milo.opcua.stack.core.util.TypeUtil;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class OpcUaJsonDecoder implements UaDecoder {

    private String peekedNextName = null;

    JsonReader jsonReader;
    SerializationContext context;

    public OpcUaJsonDecoder(SerializationContext context) {
        this.context = context;
    }

    public OpcUaJsonDecoder(SerializationContext context, String s) {
        this(context, new StringReader(s));
    }

    public OpcUaJsonDecoder(SerializationContext context, Reader reader) {
        this.context = context;

        reset(reader);
    }

    void reset(Reader reader) {
        jsonReader = new JsonReader(reader);
    }

    @Override
    public Boolean readBoolean(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return false;
                }
            }

            if (jsonReader.peek() == JsonToken.BOOLEAN) {
                return jsonReader.nextBoolean();
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readBoolean: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Byte readSByte(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return (byte) 0;
                }
            }

            if (jsonReader.peek() == JsonToken.NUMBER) {
                return (byte) jsonReader.nextInt();
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readSByte: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Short readInt16(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return (short) 0;
                }
            }

            if (jsonReader.peek() == JsonToken.NUMBER) {
                return (short) jsonReader.nextInt();
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readInt16: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Integer readInt32(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return 0;
                }
            }

            if (jsonReader.peek() == JsonToken.NUMBER) {
                return jsonReader.nextInt();
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readInt32: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Long readInt64(String field) throws UaSerializationException {
        // Int64 and UInt64 values shall be formatted as a decimal number
        // encoded as a JSON string (See the XML encoding of 64-bit values
        // described in 5.3.1.3).

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return 0L;
                }
            }

            if (jsonReader.peek() == JsonToken.STRING) {
                try {
                    return Long.valueOf(jsonReader.nextString());
                } catch (NumberFormatException e) {
                    throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
                }
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readInt64: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public UByte readByte(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return UByte.MIN;
                }
            }

            if (jsonReader.peek() == JsonToken.NUMBER) {
                return ubyte(jsonReader.nextInt());
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readByte: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public UShort readUInt16(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return UShort.MIN;
                }
            }

            if (jsonReader.peek() == JsonToken.NUMBER) {
                return ushort(jsonReader.nextInt());
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readUInt16: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public UInteger readUInt32(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return UInteger.MIN;
                }
            }

            if (jsonReader.peek() == JsonToken.NUMBER) {
                return uint(jsonReader.nextLong());
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readUInt32: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public ULong readUInt64(String field) throws UaSerializationException {
        // Int64 and UInt64 values shall be formatted as a decimal number
        // encoded as a JSON string (See the XML encoding of 64-bit values
        // described in 5.3.1.3).

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return ULong.MIN;
                }
            }

            if (jsonReader.peek() == JsonToken.STRING) {
                try {
                    return ULong.valueOf(jsonReader.nextString());
                } catch (NumberFormatException e) {
                    throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
                }
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readUInt64: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Float readFloat(String field) throws UaSerializationException {
        // Normal Float and Double values shall be encoded as a JSON number.
        // Special floating-point numbers such as positive infinity (INF),
        // negative infinity (-INF) and not-a-number (NaN) shall be
        // represented by the values "Infinity", "-Infinity" and "NaN" encoded
        // as a JSON string.

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return 0.0f;
                }
            }

            switch (jsonReader.peek()) {
                case NUMBER:
                    return (float) jsonReader.nextDouble();
                case STRING: {
                    String s = jsonReader.nextString();
                    switch (s) {
                        case "Infinity":
                            return Float.POSITIVE_INFINITY;
                        case "-Infinity":
                            return Float.NEGATIVE_INFINITY;
                        case "NaN":
                            return Float.NaN;
                        default:
                            throw new UaSerializationException(
                                StatusCodes.Bad_DecodingError,
                                "readFloat: unexpected string value: " + s
                            );
                    }
                }
                default:
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        "readFloat: unexpected token: " + jsonReader.peek()
                    );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Double readDouble(String field) throws UaSerializationException {
        // Normal Float and Double values shall be encoded as a JSON number.
        // Special floating-point numbers such as positive infinity (INF),
        // negative infinity (-INF) and not-a-number (NaN) shall be
        // represented by the values "Infinity", "-Infinity" and "NaN" encoded
        // as a JSON string.

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return 0.0;
                }
            }

            switch (jsonReader.peek()) {
                case NUMBER:
                    return jsonReader.nextDouble();
                case STRING: {
                    String s = jsonReader.nextString();
                    switch (s) {
                        case "Infinity":
                            return Double.POSITIVE_INFINITY;
                        case "-Infinity":
                            return Double.NEGATIVE_INFINITY;
                        case "NaN":
                            return Double.NaN;
                        default:
                            throw new UaSerializationException(
                                StatusCodes.Bad_DecodingError,
                                "readDouble: unexpected string value: " + s
                            );
                    }
                }
                default:
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        "readDouble: unexpected token: " + jsonReader.peek()
                    );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public String readString(String field) throws UaSerializationException {
        // String values shall be encoded as JSON strings.
        // Any characters which are not allowed in JSON strings are escaped
        // using the rules defined in RFC 7159.

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return null;
                }
            }

            if (jsonReader.peek() == JsonToken.STRING) {
                return jsonReader.nextString();
            } else if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readString: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public DateTime readDateTime(String field) throws UaSerializationException {
        // DateTime values shall be formatted as specified by ISO 8601:2004
        // and encoded as a JSON string.
        // DateTime values which exceed the minimum or maximum values supported
        // on a platform shall be encoded as "0001-01-01T00:00:00Z" or
        // "9999-12-31T23:59:59Z" respectively. During decoding, these values
        // shall be converted to the minimum or maximum values supported on the
        // platform.

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return DateTime.MIN_VALUE;
                }
            }

            if (jsonReader.peek() == JsonToken.STRING) {
                String s = jsonReader.nextString();
                try {
                    Instant instant = Instant.parse(s);

                    if (instant.isBefore(DateTime.MIN_ISO_8601_INSTANT)) {
                        return DateTime.MIN_DATE_TIME;
                    } else if (instant.isAfter(DateTime.MAX_ISO_8601_INSTANT)) {
                        return DateTime.MAX_DATE_TIME;
                    } else {
                        return new DateTime(instant);
                    }
                } catch (DateTimeParseException e) {
                    throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
                }
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readDateTime: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public UUID readGuid(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return null;
                }
            }

            if (jsonReader.peek() == JsonToken.STRING) {
                String s = jsonReader.nextString();

                return UUID.fromString(s);
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readGuid: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public ByteString readByteString(String field) throws UaSerializationException {
        // ByteString values shall be formatted as a Base64 text and encoded as
        // a JSON string.
        // Any characters which are not allowed in JSON strings are escaped
        // using the rules defined in RFC 7159.

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return null;
                }
            }

            if (jsonReader.peek() == JsonToken.STRING) {
                String s = jsonReader.nextString();
                byte[] bs = Base64.getDecoder().decode(s);

                return ByteString.of(bs);
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readByteString: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public XmlElement readXmlElement(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return null;
                }
            }

            if (jsonReader.peek() == JsonToken.STRING) {
                String s = jsonReader.nextString();

                return new XmlElement(s);
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readXmlElement: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public NodeId readNodeId(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return NodeId.NULL_VALUE;
                }
            }

            IdType idType = IdType.Numeric;
            int namespaceIndex = 0;
            Object id = null;

            try {
                jsonReader.beginObject();

                while (jsonReader.peek() == JsonToken.NAME) {
                    String propertyName = nextName();

                    switch (propertyName) {
                        case "IdType": {
                            int value = jsonReader.nextInt();
                            idType = IdType.from(value);
                            if (idType == null) {
                                throw new UaSerializationException(
                                    StatusCodes.Bad_DecodingError,
                                    "readNodeId: invalid IdType: " + value
                                );
                            }
                            break;
                        }
                        case "Id": {
                            id = readIdObject(jsonReader, idType);
                            break;
                        }
                        case "Namespace": {
                            namespaceIndex = jsonReader.nextInt();
                            break;
                        }
                    }
                }

                jsonReader.endObject();

                if (id == null) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        "readNodeId: id == null"
                    );
                } else {
                    switch (idType) {
                        case Numeric:
                            assert id instanceof UInteger;
                            return new NodeId(namespaceIndex, (UInteger) id);
                        case String:
                            assert id instanceof String;
                            return new NodeId(namespaceIndex, (String) id);
                        case Guid:
                            assert id instanceof UUID;
                            return new NodeId(namespaceIndex, (UUID) id);
                        case Opaque:
                            assert id instanceof ByteString;
                            return new NodeId(namespaceIndex, (ByteString) id);
                        default:
                            throw new UaSerializationException(
                                StatusCodes.Bad_DecodingError,
                                "readNodeId: idType: " + idType
                            );
                    }
                }
            } catch (IOException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public ExpandedNodeId readExpandedNodeId(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return ExpandedNodeId.NULL_VALUE;
                }
            }

            IdType idType = IdType.Numeric;
            Object id = null;
            int namespaceIndex = 0;
            String namespaceUri = null;
            int serverIndex = 0;

            try {
                jsonReader.beginObject();

                while (jsonReader.peek() == JsonToken.NAME) {
                    String propertyName = nextName();

                    switch (propertyName) {
                        case "IdType": {
                            int value = jsonReader.nextInt();
                            idType = IdType.from(value);
                            if (idType == null) {
                                throw new UaSerializationException(
                                    StatusCodes.Bad_DecodingError,
                                    "readExpandedNodeId: invalid IdType: " + value
                                );
                            }
                            break;
                        }
                        case "Id": {
                            id = readIdObject(jsonReader, idType);
                            break;
                        }
                        case "Namespace": {
                            switch (jsonReader.peek()) {
                                case NUMBER:
                                    namespaceIndex = jsonReader.nextInt();
                                    break;
                                case STRING:
                                    namespaceUri = jsonReader.nextString();
                                    break;
                                default:
                                    throw new UaSerializationException(
                                        StatusCodes.Bad_DecodingError,
                                        "readExpandedNodeId: unexpected Namespace token: " + jsonReader.peek()
                                    );
                            }
                            break;
                        }
                        case "ServerUri": {
                            serverIndex = jsonReader.nextInt();
                            break;
                        }
                    }
                }

                jsonReader.endObject();

                if (id == null) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        "readExpandedNodeId: id == null"
                    );
                } else {
                    if (namespaceUri == null) {
                        return new ExpandedNodeId(ushort(namespaceIndex), null, id, uint(serverIndex));
                    } else {
                        return new ExpandedNodeId(ushort(namespaceIndex), namespaceUri, id, uint(serverIndex));
                    }
                }
            } catch (IOException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    /**
     * Read the {@code id} Object for a {@link NodeId} or {@link ExpandedNodeId}.
     *
     * @param jsonReader the {@link JsonReader} to read from.
     * @param idType     the expected {@link IdType}.
     * @return the {@code id} Object read from {@code jsonReader}.
     * @throws IOException if {@code jsonReader} throws while reading the next token.
     */
    private static Object readIdObject(JsonReader jsonReader, IdType idType) throws IOException {
        Object id = null;
        switch (idType) {
            case Numeric:
                id = uint(jsonReader.nextInt());
                break;
            case String:
                id = jsonReader.nextString();
                break;
            case Guid:
                id = UUID.fromString(jsonReader.nextString());
                break;
            case Opaque:
                id = ByteString.of(Base64.getDecoder().decode(jsonReader.nextString()));
                break;
        }
        return id;
    }

    @Override
    public StatusCode readStatusCode(String field) throws UaSerializationException {
        // StatusCode values shall be encoded as a JSON number for the
        // reversible encoding.

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return StatusCode.GOOD;
                }
            }

            if (jsonReader.peek() == JsonToken.NUMBER) {
                long value = jsonReader.nextLong();

                return new StatusCode(value);
            } else {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readStatusCode: unexpected token: " + jsonReader.peek()
                );
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public QualifiedName readQualifiedName(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return QualifiedName.NULL_VALUE;
                }
            }

            jsonReader.beginObject();

            String name = null;
            int namespaceIndex = 0;

            while (jsonReader.peek() == JsonToken.NAME) {
                String nextName = nextName();

                switch (nextName) {
                    case "Name": {
                        if (jsonReader.peek() == JsonToken.NULL) {
                            jsonReader.nextNull();
                        } else {
                            name = jsonReader.nextString();
                        }
                        break;
                    }
                    case "Uri":
                        namespaceIndex = jsonReader.nextInt();
                        break;
                    default:
                        throw new UaSerializationException(
                            StatusCodes.Bad_DecodingError,
                            String.format("readQualifiedName: unexpected field: " + nextName)
                        );
                }
            }

            jsonReader.endObject();

            return new QualifiedName(namespaceIndex, name);
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public LocalizedText readLocalizedText(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return LocalizedText.NULL_VALUE;
                }
            }

            jsonReader.beginObject();

            String locale = null;
            String text = null;

            while (jsonReader.peek() == JsonToken.NAME) {
                String nextName = nextName();

                switch (nextName) {
                    case "Locale":
                        locale = jsonReader.nextString();
                        break;
                    case "Text":
                        text = jsonReader.nextString();
                        break;
                    default:
                        throw new UaSerializationException(
                            StatusCodes.Bad_DecodingError,
                            String.format("readLocalizedText: unexpected field: " + nextName)
                        );
                }
            }

            jsonReader.endObject();

            return new LocalizedText(locale, text);
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public ExtensionObject readExtensionObject(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return null;
                }
            }

            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            }

            jsonReader.beginObject();

            NodeId encodingId = null;
            int encoding = 0;
            Object body = null;

            while (jsonReader.peek() == JsonToken.NAME) {
                String nextName = nextName();

                switch (nextName) {
                    case "TypeId":
                        encodingId = readNodeId(null);
                        break;
                    case "Encoding":
                        encoding = jsonReader.nextInt();
                        break;
                    case "Body":
                        switch (encoding) {
                            case 0:
                                body = JsonParser.parseReader(jsonReader);
                                break;
                            case 1:
                                body = readByteString(null);
                                break;
                            case 2:
                                body = readXmlElement(null);
                                break;
                        }
                        break;
                    default:
                        throw new UaSerializationException(
                            StatusCodes.Bad_DecodingError,
                            String.format("readExtensionObject: unexpected field: " + nextName)
                        );
                }
            }

            jsonReader.endObject();

            if (encodingId == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "readExtensionObject: encodingId == null"
                );
            } else {
                switch (encoding) {
                    case 0:
                        assert body instanceof JsonElement;
                        return new ExtensionObject(body.toString(), encodingId);
                    case 1:
                        assert body instanceof ByteString;
                        return new ExtensionObject((ByteString) body, encodingId);
                    case 2:
                        assert body instanceof XmlElement;
                        return new ExtensionObject((XmlElement) body, encodingId);
                    default:
                        throw new UaSerializationException(
                            StatusCodes.Bad_DecodingError,
                            "readExtensionObject: unexpected encoding: " + encoding
                        );
                }
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public DataValue readDataValue(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return null;
                }
            }

            jsonReader.beginObject();

            DataValue.Builder b = DataValue.newValue();
            b.setStatus(StatusCode.GOOD);

            while (jsonReader.peek() == JsonToken.NAME) {
                String nextName = nextName();

                switch (nextName) {
                    case "Value":
                        b.setValue(readVariant(null));
                        break;

                    case "Status":
                        b.setStatus(readStatusCode(null));
                        break;

                    case "SourceTimestamp":
                        b.setSourceTime(readDateTime(null));
                        break;

                    case "SourcePicoseconds":
                        b.setSourcePicoseconds(readUInt16(null));
                        break;

                    case "ServerTimestamp":
                        b.setServerTime(readDateTime(null));
                        break;

                    case "ServerPicoseconds":
                        b.setServerPicoseconds(readUInt16(null));
                        break;
                }
            }

            jsonReader.endObject();

            return b.build();
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Variant readVariant(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return Variant.NULL_VALUE;
                }
            }

            jsonReader.beginObject();

            int typeId = 0;
            JsonElement bodyElement = null;
            int[] dimensions = null;

            while (jsonReader.peek() == JsonToken.NAME) {
                String nextName = nextName();

                switch (nextName) {
                    case "Type":
                        typeId = jsonReader.nextInt();
                        break;
                    case "Body":
                        bodyElement = JsonParser.parseReader(jsonReader);
                        break;
                    case "Dimensions":
                        var dims = new ArrayList<Integer>();
                        jsonReader.beginArray();
                        while (jsonReader.peek() == JsonToken.NUMBER) {
                            dims.add(jsonReader.nextInt());
                        }
                        jsonReader.endArray();
                        dimensions = new int[dims.size()];
                        for (int i = 0; i < dims.size(); i++) {
                            dimensions[i] = dims.get(i);
                        }
                        break;
                }
            }

            jsonReader.endObject();

            if (bodyElement == null) {
                return Variant.NULL_VALUE;
            } else if (dimensions == null) {
                // scalar or one-dimensional array value
                JsonReader reader = jsonReader;
                try {
                    jsonReader = new JsonReader(new StringReader(bodyElement.toString()));

                    if (bodyElement.isJsonArray()) {
                        var elements = new ArrayList<>();
                        jsonReader.beginArray();
                        while (jsonReader.peek() != JsonToken.END_ARRAY) {
                            elements.add(readBuiltinTypeValue(null, typeId));
                        }
                        jsonReader.endArray();
                        Object value = Array.newInstance(TypeUtil.getPrimitiveBackingClass(typeId), elements.size());
                        for (int i = 0; i < elements.size(); i++) {
                            Array.set(value, i, elements.get(i));
                        }
                        return new Variant(value);
                    } else {
                        Object value = readBuiltinTypeValue(null, typeId);
                        return new Variant(value);
                    }
                } finally {
                    jsonReader = reader;
                }
            } else {
                // multi-dimensional array value
                JsonReader reader = jsonReader;
                try {
                    jsonReader = new JsonReader(new StringReader(bodyElement.toString()));

                    Object value = readFlattenedMultiDimensionalVariantValue(typeId, dimensions);

                    return new Variant(value);
                } finally {
                    jsonReader = reader;
                }
            }
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    private Object readFlattenedMultiDimensionalVariantValue(int typeId, int[] dimensions) throws IOException {
        jsonReader.beginArray();
        try {
            return readFlattenedMultiDimensionalVariantValue(typeId, dimensions, 0);
        } finally {
            jsonReader.endArray();
        }
    }

    private Object readFlattenedMultiDimensionalVariantValue(int typeId, int[] dimensions, int dimensionIndex) {
        Object value;

        if (dimensionIndex == dimensions.length - 1) {
            value = Array.newInstance(TypeUtil.getPrimitiveBackingClass(typeId), dimensions[dimensionIndex]);
            for (int i = 0; i < dimensions[dimensionIndex]; i++) {
                Object e = readBuiltinTypeValue(null, typeId);
                Array.set(value, i, e);
            }
        } else {
            value = Array.newInstance(
                TypeUtil.getPrimitiveBackingClass(typeId),
                Arrays.copyOfRange(dimensions, dimensionIndex, dimensions.length)
            );
            for (int i = 0; i < dimensions[dimensionIndex]; i++) {
                Object e = readFlattenedMultiDimensionalVariantValue(typeId, dimensions, dimensionIndex + 1);
                Array.set(value, i, e);
            }
        }

        return value;
    }

    private Object readBuiltinTypeValue(String field, int typeId) throws UaSerializationException {
        switch (typeId) {
            case 1:
                return readBoolean(field);
            case 2:
                return readSByte(field);
            case 3:
                return readByte(field);
            case 4:
                return readInt16(field);
            case 5:
                return readUInt16(field);
            case 6:
                return readInt32(field);
            case 7:
                return readUInt32(field);
            case 8:
                return readInt64(field);
            case 9:
                return readUInt64(field);
            case 10:
                return readFloat(field);
            case 11:
                return readDouble(field);
            case 12:
                return readString(field);
            case 13:
                return readDateTime(field);
            case 14:
                return readGuid(field);
            case 15:
                return readByteString(field);
            case 16:
                return readXmlElement(field);
            case 17:
                return readNodeId(field);
            case 18:
                return readExpandedNodeId(field);
            case 19:
                return readStatusCode(field);
            case 20:
                return readQualifiedName(field);
            case 21:
                return readLocalizedText(field);
            case 22:
                return readExtensionObject(field);
            case 23:
                return readDataValue(field);
            case 24:
                return readVariant(field);
            case 25:
                return readDiagnosticInfo(field);

            default:
                throw new UaSerializationException(
                    StatusCodes.Bad_EncodingError,
                    "not a built-in type: " + typeId
                );
        }
    }

    @Override
    public DiagnosticInfo readDiagnosticInfo(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return DiagnosticInfo.NULL_VALUE;
                }
            }

            jsonReader.beginObject();

            int symbolicId = 0;
            int namespaceUri = 0;
            int locale = 0;
            int localizedText = 0;
            String additionalInfo = null;
            StatusCode innerStatusCode = null;
            DiagnosticInfo innerDiagnosticInfo = null;

            while (jsonReader.peek() == JsonToken.NAME) {
                String nextName = nextName();

                switch (nextName) {
                    case "SymbolicId":
                        symbolicId = jsonReader.nextInt();
                        break;
                    case "NamespaceUri":
                        namespaceUri = jsonReader.nextInt();
                        break;
                    case "Locale":
                        locale = jsonReader.nextInt();
                        break;
                    case "LocalizedText":
                        localizedText = jsonReader.nextInt();
                        break;
                    case "AdditionalInfo":
                        additionalInfo = jsonReader.nextString();
                        break;
                    case "InnerStatusCode":
                        innerStatusCode = readStatusCode(null);
                        break;
                    case "InnerDiagnosticInfo":
                        innerDiagnosticInfo = readDiagnosticInfo(null);
                        break;
                }
            }

            jsonReader.endObject();

            return new DiagnosticInfo(
                namespaceUri,
                symbolicId,
                locale,
                localizedText,
                additionalInfo,
                innerStatusCode,
                innerDiagnosticInfo
            );
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public UaMessage readMessage(String field) throws UaSerializationException {
        try {
            ExtensionObject xo = readExtensionObject(field);

            return (UaMessage) xo.decode(context);
        } catch (ClassCastException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public <T extends Enum<?> & UaEnumeration> T readEnum(
        String field,
        Class<T> enumType
    ) throws UaSerializationException {

        int value = readInt32(field);

        try {
            Method m = enumType.getDeclaredMethod("from", int.class);
            Object o = m.invoke(null, value);
            return enumType.cast(o);
        } catch (ClassCastException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public UaEnumeration readEnum(String field, NodeId dataTypeId) throws UaSerializationException {
        DataTypeCodec codec = context.getDataTypeManager().getEnumCodec(dataTypeId);

        if (codec != null) {
            try {
                if (field != null) {
                    String nextName = nextName();
                    if (!field.equals(nextName)) {
                        throw new UaSerializationException(
                            StatusCodes.Bad_DecodingError,
                            String.format("readEnum: %s != %s", field, nextName)
                        );
                    }
                }

                return (UaEnumeration) codec.decode(context, this);
            } catch (IOException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        } else {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, "no codec registered: " + dataTypeId);
        }
    }

    @Override
    public Object readStruct(String field, NodeId dataTypeId) throws UaSerializationException {
        DataTypeCodec codec = context.getDataTypeManager()
            .getStructCodec(OpcUaDefaultJsonEncoding.ENCODING_NAME, dataTypeId);

        if (codec != null) {
            try {
                if (field != null) {
                    String nextName = nextName();
                    if (!field.equals(nextName)) {
                        throw new UaSerializationException(
                            StatusCodes.Bad_DecodingError,
                            String.format("readStruct: %s != %s", field, nextName)
                        );
                    }
                }

                Object value;

                jsonReader.beginObject();
                value = codec.decode(context, this);
                jsonReader.endObject();

                return value;
            } catch (IOException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "readStruct: no codec registered: " + dataTypeId
            );
        }
    }

    @Override
    public Object readStruct(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        NodeId localDataTypeId = dataTypeId.toNodeId(context.getNamespaceTable())
            .orElseThrow(() -> new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "readStruct: namespace not registered: " + dataTypeId
            ));

        return readStruct(field, localDataTypeId);
    }

    @Override
    public Object readStruct(String field, DataTypeCodec codec) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readStruct: %s != %s", field, nextName)
                    );
                }
            }

            Object value;

            jsonReader.beginObject();
            value = codec.decode(context, this);
            jsonReader.endObject();

            return value;
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Boolean[] readBooleanArray(String field) throws UaSerializationException {
        return readArray(field, this::readBoolean, Boolean.class);
    }

    @Override
    public Byte[] readSByteArray(String field) throws UaSerializationException {
        return readArray(field, this::readSByte, Byte.class);
    }

    @Override
    public Short[] readInt16Array(String field) throws UaSerializationException {
        return readArray(field, this::readInt16, Short.class);
    }

    @Override
    public Integer[] readInt32Array(String field) throws UaSerializationException {
        return readArray(field, this::readInt32, Integer.class);
    }

    @Override
    public Long[] readInt64Array(String field) throws UaSerializationException {
        return readArray(field, this::readInt64, Long.class);
    }

    @Override
    public UByte[] readByteArray(String field) throws UaSerializationException {
        return readArray(field, this::readByte, UByte.class);
    }

    @Override
    public UShort[] readUInt16Array(String field) throws UaSerializationException {
        return readArray(field, this::readUInt16, UShort.class);
    }

    @Override
    public UInteger[] readUInt32Array(String field) throws UaSerializationException {
        return readArray(field, this::readUInt32, UInteger.class);
    }

    @Override
    public ULong[] readUInt64Array(String field) throws UaSerializationException {
        return readArray(field, this::readUInt64, ULong.class);
    }

    @Override
    public Float[] readFloatArray(String field) throws UaSerializationException {
        return readArray(field, this::readFloat, Float.class);
    }

    @Override
    public Double[] readDoubleArray(String field) throws UaSerializationException {
        return readArray(field, this::readDouble, Double.class);
    }

    @Override
    public String[] readStringArray(String field) throws UaSerializationException {
        return readArray(field, this::readString, String.class);
    }

    @Override
    public DateTime[] readDateTimeArray(String field) throws UaSerializationException {
        return readArray(field, this::readDateTime, DateTime.class);
    }

    @Override
    public UUID[] readGuidArray(String field) throws UaSerializationException {
        return readArray(field, this::readGuid, UUID.class);
    }

    @Override
    public ByteString[] readByteStringArray(String field) throws UaSerializationException {
        return readArray(field, this::readByteString, ByteString.class);
    }

    @Override
    public XmlElement[] readXmlElementArray(String field) throws UaSerializationException {
        return readArray(field, this::readXmlElement, XmlElement.class);
    }

    @Override
    public NodeId[] readNodeIdArray(String field) throws UaSerializationException {
        return readArray(field, this::readNodeId, NodeId.class);
    }

    @Override
    public ExpandedNodeId[] readExpandedNodeIdArray(String field) throws UaSerializationException {
        return readArray(field, this::readExpandedNodeId, ExpandedNodeId.class);
    }

    @Override
    public StatusCode[] readStatusCodeArray(String field) throws UaSerializationException {
        return readArray(field, this::readStatusCode, StatusCode.class);
    }

    @Override
    public QualifiedName[] readQualifiedNameArray(String field) throws UaSerializationException {
        return readArray(field, this::readQualifiedName, QualifiedName.class);
    }

    @Override
    public LocalizedText[] readLocalizedTextArray(String field) throws UaSerializationException {
        return readArray(field, this::readLocalizedText, LocalizedText.class);
    }

    @Override
    public ExtensionObject[] readExtensionObjectArray(String field) throws UaSerializationException {
        return readArray(field, this::readExtensionObject, ExtensionObject.class);
    }

    @Override
    public DataValue[] readDataValueArray(String field) throws UaSerializationException {
        return readArray(field, this::readDataValue, DataValue.class);
    }

    @Override
    public Variant[] readVariantArray(String field) throws UaSerializationException {
        return readArray(field, this::readVariant, Variant.class);
    }

    @Override
    public DiagnosticInfo[] readDiagnosticInfoArray(String field) throws UaSerializationException {
        return readArray(field, this::readDiagnosticInfo, DiagnosticInfo.class);
    }

    @Override
    public <T extends Enum<?> & UaEnumeration> Object[] readEnumArray(String field, Class<T> enumType) throws UaSerializationException {
        return readArray(field, s -> readEnum(s, enumType), enumType);
    }

    @Override
    public UaEnumeration[] readEnumArray(String field, NodeId dataTypeId) throws UaSerializationException {
        DataTypeCodec codec = context.getDataTypeManager().getEnumCodec(dataTypeId);

        if (codec != null) {
            try {
                if (field != null) {
                    String nextName = nextName();
                    if (!field.equals(nextName)) {
                        this.peekedNextName = nextName;
                        return null;
                    }
                }

                var elements = new ArrayList<>();

                jsonReader.beginArray();
                while (jsonReader.peek() != JsonToken.END_ARRAY) {
                    elements.add(codec.decode(context, this));
                }
                jsonReader.endArray();

                Object array = Array.newInstance(codec.getType(), elements.size());
                for (int i = 0; i < elements.size(); i++) {
                    Array.set(array, i, elements.get(i));
                }

                return (UaEnumeration[]) array;
            } catch (IOException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "readStructArray: no codec registered: " + dataTypeId
            );
        }
    }

    @Override
    public Object[] readStructArray(String field, NodeId dataTypeId) throws UaSerializationException {
        DataTypeCodec codec = context.getDataTypeManager()
            .getStructCodec(OpcUaDefaultJsonEncoding.ENCODING_NAME, dataTypeId);

        if (codec == null) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "readStructArray: no codec registered: " + dataTypeId
            );
        }

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return null;
                }
            }

            var elements = new ArrayList<>();

            jsonReader.beginArray();
            while (jsonReader.peek() != JsonToken.END_ARRAY) {
                elements.add(readStruct(null, dataTypeId));
            }
            jsonReader.endArray();

            Object array = Array.newInstance(codec.getType(), elements.size());
            for (int i = 0; i < elements.size(); i++) {
                Array.set(array, i, elements.get(i));
            }

            return (Object[]) array;
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Object[] readStructArray(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        NodeId localDataTypeId = dataTypeId.toNodeId(context.getNamespaceTable())
            .orElseThrow(() -> new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "readStructArray: no codec registered: " + dataTypeId
            ));

        return readStructArray(field, localDataTypeId);
    }

    @Override
    public <T> T[] readArray(
        String field,
        Function<String, T> decoder,
        Class<T> clazz
    ) throws UaSerializationException {

        try {
            if (field != null) {
                String nextName = nextName();
                if (!field.equals(nextName)) {
                    this.peekedNextName = nextName;
                    return null;
                }
            }

            var elements = new ArrayList<T>();

            jsonReader.beginArray();
            while (jsonReader.peek() != JsonToken.END_ARRAY) {
                elements.add(decoder.apply(null));
            }
            jsonReader.endArray();

            Object array = Array.newInstance(clazz, elements.size());
            for (int i = 0; i < elements.size(); i++) {
                Array.set(array, i, elements.get(i));
            }

            //noinspection unchecked
            return (T[]) array;
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    /**
     * This ugly hack is so `readArray` can "push back" the next name it reads if it doesn't match
     * the expected field name, because that probably means it was omitted due to being null.
     */
    private String nextName() throws IOException {
        if (peekedNextName != null) {
            String next = peekedNextName;
            peekedNextName = null;
            return next;
        } else {
            return jsonReader.nextName();
        }
    }

}
