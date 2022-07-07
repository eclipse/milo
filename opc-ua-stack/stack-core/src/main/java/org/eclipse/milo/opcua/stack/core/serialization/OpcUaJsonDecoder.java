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
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Base64;
import java.util.UUID;
import java.util.function.Function;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
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

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class OpcUaJsonDecoder implements UaDecoder {

    JsonReader jsonReader;

    public OpcUaJsonDecoder(Reader reader) {
        this.jsonReader = new JsonReader(reader);
    }

    void reset(Reader reader) {
        jsonReader = new JsonReader(reader);
    }

    @Override
    public Boolean readBoolean(String field) throws UaSerializationException {
        try {
            if (field != null) {
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readFloat: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readSByte: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readInt16: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readInt32: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readInt64: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readByte: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readUInt16: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readUInt32: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readUInt64: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readFloat: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readDouble: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readString: %s != %s", field, nextName)
                    );
                }
            }

            if (jsonReader.peek() == JsonToken.STRING) {
                return jsonReader.nextString();
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readDateTime: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readGuid: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readByteString: %s != %s", field, nextName)
                    );
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
                String nextName = jsonReader.nextName();
                if (!field.equals(nextName)) {
                    throw new UaSerializationException(
                        StatusCodes.Bad_DecodingError,
                        String.format("readXmlElement: %s != %s", field, nextName)
                    );
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
        return null;
    }

    @Override
    public ExpandedNodeId readExpandedNodeId(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public StatusCode readStatusCode(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public QualifiedName readQualifiedName(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public LocalizedText readLocalizedText(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public ExtensionObject readExtensionObject(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public DataValue readDataValue(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public Variant readVariant(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public DiagnosticInfo readDiagnosticInfo(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public UaMessage readMessage(String field) throws UaSerializationException {
        return null;
    }

    @Override
    public <T extends Enum<?> & UaEnumeration> T readEnum(String field, Class<T> enumType) throws UaSerializationException {
        return null;
    }

    @Override
    public Object readStruct(String field, NodeId dataTypeId) throws UaSerializationException {
        return null;
    }

    @Override
    public Object readStruct(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        return null;
    }

    @Override
    public Object readStruct(String field, DataTypeCodec codec) throws UaSerializationException {
        return null;
    }

    @Override
    public Boolean[] readBooleanArray(String field) throws UaSerializationException {
        return new Boolean[0];
    }

    @Override
    public Byte[] readSByteArray(String field) throws UaSerializationException {
        return new Byte[0];
    }

    @Override
    public Short[] readInt16Array(String field) throws UaSerializationException {
        return new Short[0];
    }

    @Override
    public Integer[] readInt32Array(String field) throws UaSerializationException {
        return new Integer[0];
    }

    @Override
    public Long[] readInt64Array(String field) throws UaSerializationException {
        return new Long[0];
    }

    @Override
    public UByte[] readByteArray(String field) throws UaSerializationException {
        return new UByte[0];
    }

    @Override
    public UShort[] readUInt16Array(String field) throws UaSerializationException {
        return new UShort[0];
    }

    @Override
    public UInteger[] readUInt32Array(String field) throws UaSerializationException {
        return new UInteger[0];
    }

    @Override
    public ULong[] readUInt64Array(String field) throws UaSerializationException {
        return new ULong[0];
    }

    @Override
    public Float[] readFloatArray(String field) throws UaSerializationException {
        return new Float[0];
    }

    @Override
    public Double[] readDoubleArray(String field) throws UaSerializationException {
        return new Double[0];
    }

    @Override
    public String[] readStringArray(String field) throws UaSerializationException {
        return new String[0];
    }

    @Override
    public DateTime[] readDateTimeArray(String field) throws UaSerializationException {
        return new DateTime[0];
    }

    @Override
    public UUID[] readGuidArray(String field) throws UaSerializationException {
        return new UUID[0];
    }

    @Override
    public ByteString[] readByteStringArray(String field) throws UaSerializationException {
        return new ByteString[0];
    }

    @Override
    public XmlElement[] readXmlElementArray(String field) throws UaSerializationException {
        return new XmlElement[0];
    }

    @Override
    public NodeId[] readNodeIdArray(String field) throws UaSerializationException {
        return new NodeId[0];
    }

    @Override
    public ExpandedNodeId[] readExpandedNodeIdArray(String field) throws UaSerializationException {
        return new ExpandedNodeId[0];
    }

    @Override
    public StatusCode[] readStatusCodeArray(String field) throws UaSerializationException {
        return new StatusCode[0];
    }

    @Override
    public QualifiedName[] readQualifiedNameArray(String field) throws UaSerializationException {
        return new QualifiedName[0];
    }

    @Override
    public LocalizedText[] readLocalizedTextArray(String field) throws UaSerializationException {
        return new LocalizedText[0];
    }

    @Override
    public ExtensionObject[] readExtensionObjectArray(String field) throws UaSerializationException {
        return new ExtensionObject[0];
    }

    @Override
    public DataValue[] readDataValueArray(String field) throws UaSerializationException {
        return new DataValue[0];
    }

    @Override
    public Variant[] readVariantArray(String field) throws UaSerializationException {
        return new Variant[0];
    }

    @Override
    public DiagnosticInfo[] readDiagnosticInfoArray(String field) throws UaSerializationException {
        return new DiagnosticInfo[0];
    }

    @Override
    public <T extends Enum<?> & UaEnumeration> Object[] readEnumArray(String field, Class<T> enumType) throws UaSerializationException {
        return new Object[0];
    }

    @Override
    public Object[] readStructArray(String field, NodeId dataTypeId) throws UaSerializationException {
        return new Object[0];
    }

    @Override
    public Object[] readStructArray(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        return new Object[0];
    }

    @Override
    public <T> T[] readArray(String field, Function<String, T> decoder, Class<T> clazz) throws UaSerializationException {
        return null;
    }

}
