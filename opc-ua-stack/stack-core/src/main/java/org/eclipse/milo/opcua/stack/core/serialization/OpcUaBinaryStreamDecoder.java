/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.function.Supplier;
import javax.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.util.ByteProcessor;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.BuiltinDataTypeDictionary;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.types.OpcUaDefaultBinaryEncoding;
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
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.eclipse.milo.opcua.stack.core.util.TypeUtil;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ubyte;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ulong;
import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.ushort;

public class OpcUaBinaryStreamDecoder implements UaDecoder {

    private static final Charset CHARSET_UTF8 = Charset.forName("UTF-8");
    private static final Charset CHARSET_UTF16 = Charset.forName("UTF-16");

    private volatile ByteBuf buffer;

    private volatile int currentByte = 0;
    private volatile int bitsRemaining = 0;

    private final AtomicInteger depth = new AtomicInteger(0);

    private final SerializationContext context;

    public OpcUaBinaryStreamDecoder(SerializationContext context) {
        this.context = context;
    }

    public OpcUaBinaryStreamDecoder setBuffer(ByteBuf buffer) {
        this.buffer = buffer;
        return this;
    }

    public <T> T[] readArray(Supplier<T> read, Class<T> clazz) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            @SuppressWarnings("unchecked")
            T[] array = (T[]) Array.newInstance(clazz, length);

            for (int i = 0; i < length; i++) {
                array[i] = read.get();
            }

            return array;
        }
    }

    public int readBit() throws UaSerializationException {
        if (bitsRemaining == 0) {
            currentByte = buffer.readUnsignedByte();
            bitsRemaining = 8;
        }

        int bit = currentByte & 1;
        currentByte >>= 1;
        bitsRemaining--;
        return bit;
    }

    public Character readCharacter() throws UaSerializationException {
        return (char) (buffer.readUnsignedByte());
    }

    public Character readWideChar() throws UaSerializationException {
        return buffer.readChar();
    }

    public String readUtf8NullTerminatedString() throws UaSerializationException {
        return readNullTerminatedString(CHARSET_UTF8);
    }

    public String readUtf8CharArray() throws UaSerializationException {
        return readLengthPrefixedString(CHARSET_UTF8);
    }

    public String readUtf16NullTerminatedString() throws UaSerializationException {
        return readNullTerminatedString(CHARSET_UTF16);
    }

    public String readUtf16CharArray() throws UaSerializationException {
        return readLengthPrefixedString(CHARSET_UTF16);
    }

    public Boolean readBoolean() throws UaSerializationException {
        return buffer.readBoolean();
    }

    public Byte readSByte() throws UaSerializationException {
        return buffer.readByte();
    }

    public UByte readByte() throws UaSerializationException {
        return ubyte(buffer.readUnsignedByte());
    }

    public Short readInt16() throws UaSerializationException {
        return buffer.readShortLE();
    }

    public UShort readUInt16() throws UaSerializationException {
        return ushort(buffer.readUnsignedShortLE());
    }

    public Integer readInt32() throws UaSerializationException {
        return buffer.readIntLE();
    }

    public UInteger readUInt32() throws UaSerializationException {
        return uint(buffer.readUnsignedIntLE());
    }

    public Long readInt64() throws UaSerializationException {
        return buffer.readLongLE();
    }

    public ULong readUInt64() throws UaSerializationException {
        return ulong(buffer.readLongLE());
    }

    public Float readFloat() throws UaSerializationException {
        return buffer.readFloatLE();
    }

    public Double readDouble() throws UaSerializationException {
        return buffer.readDoubleLE();
    }

    public DateTime readDateTime() throws UaSerializationException {
        return new DateTime(buffer.readLongLE());
    }

    public ByteString readByteString() throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return ByteString.NULL_VALUE;
        } else {
            checkArrayLength(length);

            byte[] bs = new byte[length];
            buffer.readBytes(bs);
            return new ByteString(bs);
        }
    }

    public UUID readGuid() throws UaSerializationException {
        long part1 = buffer.readUnsignedIntLE();
        long part2 = buffer.readUnsignedShortLE();
        long part3 = buffer.readUnsignedShortLE();
        long part4 = buffer.readLong(); // intentionally Big Endian

        long msb = (part1 << 32) | (part2 << 16) | part3;

        return new UUID(msb, part4);
    }

    public XmlElement readXmlElement() throws UaSerializationException {
        ByteString byteString = readByteString();
        byte[] bs = byteString.bytes();

        if (bs == null) {
            return new XmlElement(null);
        } else {
            String fragment = new String(bs, StandardCharsets.UTF_8);
            return new XmlElement(fragment);
        }
    }

    public DataValue readDataValue() throws UaSerializationException {
        int mask = buffer.readByte() & 0xFF;

        Variant value = ((mask & 0x01) != 0) ? readVariant() : Variant.NULL_VALUE;
        StatusCode status = ((mask & 0x02) != 0) ? readStatusCode() : StatusCode.GOOD;
        DateTime sourceTime = ((mask & 0x04) != 0) ? readDateTime() : DateTime.MIN_VALUE;
        UShort sourcePicoseconds = ((mask & 0x10) != 0) ? readUInt16() : null;
        DateTime serverTime = ((mask & 0x08) != 0) ? readDateTime() : DateTime.MIN_VALUE;
        UShort serverPicoseconds = ((mask & 0x20) != 0) ? readUInt16() : null;

        return new DataValue(value, status, sourceTime, sourcePicoseconds, serverTime, serverPicoseconds);
    }

    public DiagnosticInfo readDiagnosticInfo() throws UaSerializationException {
        if (depth.get() >= context.getEncodingLimits().getMaxRecursionDepth()) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingLimitsExceeded,
                "max recursion depth exceeded: " +
                    context.getEncodingLimits().getMaxRecursionDepth()
            );
        }

        depth.incrementAndGet();
        try {
            int mask = buffer.readByte();

            if (mask == 0) {
                return null;
            } else {
                int symbolicId = ((mask & 0x01) == 0x01) ? readInt32() : -1;
                int namespaceUri = ((mask & 0x02) == 0x02) ? readInt32() : -1;
                int localizedText = ((mask & 0x04) == 0x04) ? readInt32() : -1;
                int locale = ((mask & 0x08) == 0x08) ? readInt32() : -1;
                String additionalInfo = ((mask & 0x10) == 0x10) ? readString() : null;
                StatusCode innerStatusCode = ((mask & 0x20) == 0x20) ? readStatusCode() : null;
                DiagnosticInfo innerDiagnosticInfo = ((mask & 0x40) == 0x40) ? readDiagnosticInfo() : null;

                return new DiagnosticInfo(
                    namespaceUri,
                    symbolicId,
                    locale,
                    localizedText,
                    additionalInfo,
                    innerStatusCode,
                    innerDiagnosticInfo
                );
            }
        } finally {
            depth.decrementAndGet();
        }
    }

    public ExpandedNodeId readExpandedNodeId() throws UaSerializationException {
        int flags = buffer.getByte(buffer.readerIndex());

        NodeId nodeId = readNodeId();

        String namespaceUri = null;
        long serverIndex = 0;

        if ((flags & 0x80) == 0x80) {
            namespaceUri = readString();
        }

        if ((flags & 0x40) == 0x40) {
            serverIndex = readUInt32().longValue();
        }

        return new ExpandedNodeId(nodeId, namespaceUri, serverIndex);
    }

    public ExtensionObject readExtensionObject() throws UaSerializationException {
        NodeId encodingTypeId = readNodeId();
        int encoding = buffer.readByte();

        if (encoding == 0) {
            return new ExtensionObject(ByteString.NULL_VALUE, encodingTypeId);
        } else if (encoding == 1) {
            ByteString byteString = readByteString();

            return new ExtensionObject(byteString, encodingTypeId);
        } else if (encoding == 2) {
            XmlElement xmlElement = readXmlElement();

            return new ExtensionObject(xmlElement, encodingTypeId);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "unknown ExtensionObject encoding: " + encoding);
        }
    }

    public LocalizedText readLocalizedText() throws UaSerializationException {
        int mask = buffer.readByte();

        String locale = null;
        String text = null;

        if ((mask & 1) == 1) {
            locale = readString();
        }

        if ((mask & 2) == 2) {
            text = readString();
        }

        return new LocalizedText(locale, text);
    }

    public NodeId readNodeId() throws UaSerializationException {
        int format = buffer.readByte() & 0x0F;

        if (format == 0x00) {
            /* Two-byte format */
            return new NodeId(UShort.MIN, uint(buffer.readUnsignedByte()));
        } else if (format == 0x01) {
            /* Four-byte format */
            return new NodeId(ushort(buffer.readUnsignedByte()), uint(buffer.readUnsignedShortLE()));
        } else if (format == 0x02) {
            /* Numeric format */
            return new NodeId(readUInt16(), readUInt32());
        } else if (format == 0x03) {
            /* String format */
            return new NodeId(readUInt16(), readString());
        } else if (format == 0x04) {
            /* Guid format */
            return new NodeId(readUInt16(), readGuid());
        } else if (format == 0x05) {
            /* Opaque format */
            return new NodeId(readUInt16(), readByteString());
        } else {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, "invalid NodeId format: " + format);
        }
    }

    public QualifiedName readQualifiedName() throws UaSerializationException {
        UShort namespaceIndex = readUInt16();
        String name = readString();

        return new QualifiedName(namespaceIndex, name);
    }

    public StatusCode readStatusCode() throws UaSerializationException {
        return new StatusCode(readUInt32());
    }

    public String readString() throws UaSerializationException {
        return readLengthPrefixedString(CHARSET_UTF8);
    }

    public Variant readVariant() throws UaSerializationException {
        if (depth.get() >= context.getEncodingLimits().getMaxRecursionDepth()) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingLimitsExceeded,
                "max recursion depth exceeded: " +
                    context.getEncodingLimits().getMaxRecursionDepth()
            );
        }

        depth.incrementAndGet();
        try {
            int encodingMask = buffer.readByte();

            if (encodingMask == 0) {
                return new Variant(null);
            } else {
                int typeId = encodingMask & 0x3F;
                boolean dimensionsEncoded = (encodingMask & 0x40) == 0x40;
                boolean arrayEncoded = (encodingMask & 0x80) == 0x80;

                if (arrayEncoded) {
                    Class<?> backingClass = TypeUtil.getBackingClass(typeId);
                    int length = readInt32();

                    if (length == -1) {
                        return new Variant(null);
                    } else {
                        checkArrayLength(length);

                        Object flatArray = Array.newInstance(backingClass, length);

                        for (int i = 0; i < length; i++) {
                            Object element = decodeBuiltinType(typeId);

                            Array.set(flatArray, i, element);
                        }

                        int[] dimensions = dimensionsEncoded ? decodeDimensions() : new int[]{length};
                        Object array = dimensions.length > 1 ? ArrayUtil.unflatten(flatArray, dimensions) : flatArray;

                        return new Variant(array);
                    }
                } else {
                    Object value = decodeBuiltinType(typeId);

                    return new Variant(value);
                }
            }
        } finally {
            depth.decrementAndGet();
        }
    }

    @Nullable
    private String readLengthPrefixedString(Charset charset) {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            if (length > context.getEncodingLimits().getMaxStringLength()) {
                throw new UaSerializationException(
                    StatusCodes.Bad_EncodingLimitsExceeded,
                    String.format(
                        "max string length exceeded (length=%s, max=%s)",
                        length, context.getEncodingLimits().getMaxStringLength())
                );
            }

            String str = buffer.toString(buffer.readerIndex(), length, charset);
            buffer.skipBytes(length);
            return str;
        }
    }

    private String readNullTerminatedString(Charset charset) {
        int indexOfNull = buffer.forEachByte(ByteProcessor.FIND_NUL);

        if (indexOfNull == -1) {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError, "null terminator not found");
        }

        int index = buffer.readerIndex();
        int length = indexOfNull - index;
        String str = buffer.toString(index, length, charset);
        buffer.skipBytes(length + 1);

        return str;
    }

    private int[] decodeDimensions() {
        int length = readInt32();

        if (length == -1) {
            return new int[0];
        } else {
            int[] is = new int[length];
            for (int i = 0; i < length; i++) {
                is[i] = readInt32();
            }
            return is;
        }
    }

    private Object decodeBuiltinType(int typeId) throws UaSerializationException {
        switch (typeId) {
            case 1:
                return readBoolean();
            case 2:
                return readSByte();
            case 3:
                return readByte();
            case 4:
                return readInt16();
            case 5:
                return readUInt16();
            case 6:
                return readInt32();
            case 7:
                return readUInt32();
            case 8:
                return readInt64();
            case 9:
                return readUInt64();
            case 10:
                return readFloat();
            case 11:
                return readDouble();
            case 12:
                return readString();
            case 13:
                return readDateTime();
            case 14:
                return readGuid();
            case 15:
                return readByteString();
            case 16:
                return readXmlElement();
            case 17:
                return readNodeId();
            case 18:
                return readExpandedNodeId();
            case 19:
                return readStatusCode();
            case 20:
                return readQualifiedName();
            case 21:
                return readLocalizedText();
            case 22:
                return readExtensionObject();
            case 23:
                return readDataValue();
            case 24:
                return readVariant();
            case 25:
                return readDiagnosticInfo();
            default:
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, "unknown builtin type: " + typeId);
        }
    }

    @Override
    public Boolean readBoolean(String field) throws UaSerializationException {
        return readBoolean();
    }

    @Override
    public Byte readSByte(String field) throws UaSerializationException {
        return readSByte();
    }

    @Override
    public Short readInt16(String field) throws UaSerializationException {
        return readInt16();
    }

    @Override
    public Integer readInt32(String field) throws UaSerializationException {
        return readInt32();
    }

    @Override
    public Long readInt64(String field) throws UaSerializationException {
        return readInt64();
    }

    @Override
    public UByte readByte(String field) throws UaSerializationException {
        return readByte();
    }

    @Override
    public UShort readUInt16(String field) throws UaSerializationException {
        return readUInt16();
    }

    @Override
    public UInteger readUInt32(String field) throws UaSerializationException {
        return readUInt32();
    }

    @Override
    public ULong readUInt64(String field) throws UaSerializationException {
        return readUInt64();
    }

    @Override
    public Float readFloat(String field) throws UaSerializationException {
        return readFloat();
    }

    @Override
    public Double readDouble(String field) throws UaSerializationException {
        return readDouble();
    }

    @Override
    public String readString(String field) throws UaSerializationException {
        return readString();
    }

    @Override
    public DateTime readDateTime(String field) throws UaSerializationException {
        return readDateTime();
    }

    @Override
    public UUID readGuid(String field) throws UaSerializationException {
        return readGuid();
    }

    @Override
    public ByteString readByteString(String field) throws UaSerializationException {
        return readByteString();
    }

    @Override
    public XmlElement readXmlElement(String field) throws UaSerializationException {
        return readXmlElement();
    }

    @Override
    public NodeId readNodeId(String field) throws UaSerializationException {
        return readNodeId();
    }

    @Override
    public ExpandedNodeId readExpandedNodeId(String field) throws UaSerializationException {
        return readExpandedNodeId();
    }

    @Override
    public StatusCode readStatusCode(String field) throws UaSerializationException {
        return readStatusCode();
    }

    @Override
    public QualifiedName readQualifiedName(String field) throws UaSerializationException {
        return readQualifiedName();
    }

    @Override
    public LocalizedText readLocalizedText(String field) throws UaSerializationException {
        return readLocalizedText();
    }

    @Override
    public ExtensionObject readExtensionObject(String field) throws UaSerializationException {
        return readExtensionObject();
    }

    @Override
    public DataValue readDataValue(String field) throws UaSerializationException {
        return readDataValue();
    }

    @Override
    public Variant readVariant(String field) throws UaSerializationException {
        return readVariant();
    }

    @Override
    public DiagnosticInfo readDiagnosticInfo(String field) throws UaSerializationException {
        return readDiagnosticInfo();
    }

    @Override
    public UaMessage readMessage(String field) throws UaSerializationException {
        NodeId encodingId = readNodeId();

        OpcUaBinaryDataTypeCodec<?> binaryCodec = (OpcUaBinaryDataTypeCodec<?>)
            OpcUaDataTypeManager
                .getInstance()
                .getCodec(encodingId);

        if (binaryCodec != null) {
            return (UaMessage) binaryCodec.decode(context, this);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "no codec registered: " + encodingId
            );
        }
    }

    @Override
    public Object readStruct(String field, NodeId dataTypeId) throws UaSerializationException {
        OpcUaBinaryDataTypeCodec<?> binaryCodec = (OpcUaBinaryDataTypeCodec<?>)
            OpcUaDataTypeManager
                .getInstance()
                .getCodec(OpcUaDefaultBinaryEncoding.ENCODING_NAME, dataTypeId);

        if (binaryCodec != null) {
            return binaryCodec.decode(context, this);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "no codec registered: " + dataTypeId
            );
        }
    }

    @Override
    public Object readStruct(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        return dataTypeId
            .local(context.getNamespaceTable())
            .map(id -> readStruct(field, id))
            .orElseThrow(() -> new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "no codec registered: " + dataTypeId
            ));
    }

    @Override
    public Object readStruct(String field, DataTypeCodec codec) throws UaSerializationException {
        if (codec instanceof OpcUaBinaryDataTypeCodec) {
            OpcUaBinaryDataTypeCodec binaryCodec = (OpcUaBinaryDataTypeCodec) codec;

            return binaryCodec.decode(context, this);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                new IllegalArgumentException("codec: " + codec)
            );
        }
    }

    @Override
    public Boolean[] readBooleanArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            Boolean[] values = new Boolean[length];
            for (int i = 0; i < length; i++) {
                values[i] = readBoolean(field);
            }
            return values;
        }
    }

    @Override
    public Byte[] readSByteArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            Byte[] values = new Byte[length];
            for (int i = 0; i < length; i++) {
                values[i] = readSByte(field);
            }
            return values;
        }
    }

    @Override
    public Short[] readInt16Array(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            Short[] values = new Short[length];
            for (int i = 0; i < length; i++) {
                values[i] = readInt16(field);
            }
            return values;
        }
    }

    @Override
    public Integer[] readInt32Array(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            Integer[] values = new Integer[length];
            for (int i = 0; i < length; i++) {
                values[i] = readInt32(field);
            }
            return values;
        }
    }

    @Override
    public Long[] readInt64Array(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            Long[] values = new Long[length];
            for (int i = 0; i < length; i++) {
                values[i] = readInt64(field);
            }
            return values;
        }
    }

    @Override
    public UByte[] readByteArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            UByte[] values = new UByte[length];
            for (int i = 0; i < length; i++) {
                values[i] = readByte(field);
            }
            return values;
        }
    }

    @Override
    public UShort[] readUInt16Array(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            UShort[] values = new UShort[length];
            for (int i = 0; i < length; i++) {
                values[i] = readUInt16(field);
            }
            return values;
        }
    }

    @Override
    public UInteger[] readUInt32Array(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            UInteger[] values = new UInteger[length];
            for (int i = 0; i < length; i++) {
                values[i] = readUInt32(field);
            }
            return values;
        }
    }

    @Override
    public ULong[] readUInt64Array(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            ULong[] values = new ULong[length];
            for (int i = 0; i < length; i++) {
                values[i] = readUInt64(field);
            }
            return values;
        }
    }

    @Override
    public Float[] readFloatArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            Float[] values = new Float[length];
            for (int i = 0; i < length; i++) {
                values[i] = readFloat(field);
            }
            return values;
        }
    }

    @Override
    public Double[] readDoubleArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            Double[] values = new Double[length];
            for (int i = 0; i < length; i++) {
                values[i] = readDouble(field);
            }
            return values;
        }
    }

    @Override
    public String[] readStringArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            String[] values = new String[length];
            for (int i = 0; i < length; i++) {
                values[i] = readString(field);
            }
            return values;
        }
    }

    @Override
    public DateTime[] readDateTimeArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            DateTime[] values = new DateTime[length];
            for (int i = 0; i < length; i++) {
                values[i] = readDateTime(field);
            }
            return values;
        }
    }

    @Override
    public UUID[] readGuidArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            UUID[] values = new UUID[length];
            for (int i = 0; i < length; i++) {
                values[i] = readGuid(field);
            }
            return values;
        }
    }

    @Override
    public ByteString[] readByteStringArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            ByteString[] values = new ByteString[length];
            for (int i = 0; i < length; i++) {
                values[i] = readByteString(field);
            }
            return values;
        }
    }

    @Override
    public XmlElement[] readXmlElementArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            XmlElement[] values = new XmlElement[length];
            for (int i = 0; i < length; i++) {
                values[i] = readXmlElement(field);
            }
            return values;
        }
    }

    @Override
    public NodeId[] readNodeIdArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            NodeId[] values = new NodeId[length];
            for (int i = 0; i < length; i++) {
                values[i] = readNodeId(field);
            }
            return values;
        }
    }

    @Override
    public ExpandedNodeId[] readExpandedNodeIdArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            ExpandedNodeId[] values = new ExpandedNodeId[length];
            for (int i = 0; i < length; i++) {
                values[i] = readExpandedNodeId(field);
            }
            return values;
        }
    }

    @Override
    public StatusCode[] readStatusCodeArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            StatusCode[] values = new StatusCode[length];
            for (int i = 0; i < length; i++) {
                values[i] = readStatusCode(field);
            }
            return values;
        }
    }

    @Override
    public QualifiedName[] readQualifiedNameArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            QualifiedName[] values = new QualifiedName[length];
            for (int i = 0; i < length; i++) {
                values[i] = readQualifiedName(field);
            }
            return values;
        }
    }

    @Override
    public LocalizedText[] readLocalizedTextArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            LocalizedText[] values = new LocalizedText[length];
            for (int i = 0; i < length; i++) {
                values[i] = readLocalizedText(field);
            }
            return values;
        }
    }

    @Override
    public ExtensionObject[] readExtensionObjectArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            ExtensionObject[] values = new ExtensionObject[length];
            for (int i = 0; i < length; i++) {
                values[i] = readExtensionObject(field);
            }
            return values;
        }
    }

    @Override
    public DataValue[] readDataValueArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            DataValue[] values = new DataValue[length];
            for (int i = 0; i < length; i++) {
                values[i] = readDataValue(field);
            }
            return values;
        }
    }

    @Override
    public Variant[] readVariantArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            Variant[] values = new Variant[length];
            for (int i = 0; i < length; i++) {
                values[i] = readVariant(field);
            }
            return values;
        }
    }

    @Override
    public DiagnosticInfo[] readDiagnosticInfoArray(String field) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            DiagnosticInfo[] values = new DiagnosticInfo[length];
            for (int i = 0; i < length; i++) {
                values[i] = readDiagnosticInfo(field);
            }
            return values;
        }
    }

    @Override
    public Object[] readStructArray(String field, NodeId dataTypeId) throws UaSerializationException {
        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            OpcUaBinaryDataTypeCodec<?> binaryCodec = (OpcUaBinaryDataTypeCodec<?>)
                OpcUaDataTypeManager
                    .getInstance()
                    .getCodec(OpcUaDefaultBinaryEncoding.ENCODING_NAME, dataTypeId);

            if (binaryCodec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered: " + dataTypeId
                );
            }

            Class<?> clazz = binaryCodec.getType();
            Object array = Array.newInstance(clazz, length);

            for (int i = 0; i < length; i++) {
                Object value = binaryCodec.decode(context, this);

                Array.set(array, i, value);
            }

            return (Object[]) array;
        }
    }

    @Override
    public Object[] readStructArray(String field, ExpandedNodeId dataTypeId) throws UaSerializationException {
        return dataTypeId
            .local(context.getNamespaceTable())
            .map(id -> readStructArray(field, id))
            .orElseThrow(() -> new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "no codec registered: " + dataTypeId
            ));
    }

    private void checkArrayLength(int length) throws UaSerializationException {
        if (length > context.getEncodingLimits().getMaxArrayLength()) {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingLimitsExceeded,
                String.format(
                    "max array length exceeded (length=%s, max=%s)",
                    length, context.getEncodingLimits().getMaxArrayLength())
            );
        }
    }

    @Override
    public <T> T[] readArray(
        String field, Function<String, T> decoder, Class<T> clazz) throws UaSerializationException {

        int length = readInt32();

        if (length == -1) {
            return null;
        } else {
            checkArrayLength(length);

            @SuppressWarnings("unchecked")
            T[] array = (T[]) Array.newInstance(clazz, length);

            for (int i = 0; i < length; i++) {
                array[i] = decoder.apply(field);
            }

            return array;
        }
    }

    @Override
    public <T extends UaStructure> T readBuiltinStruct(String field, Class<T> clazz) throws UaSerializationException {
        try {
            @SuppressWarnings("unchecked")
            BuiltinDataTypeCodec<UaStructure> codec =
                (BuiltinDataTypeCodec<UaStructure>) BuiltinDataTypeDictionary.getBuiltinCodec(clazz);

            if (codec == null) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, "No codec registered:" + clazz);
            } else {
                @SuppressWarnings("unchecked")
                T value = (T) codec.decode(context, this);

                return value;
            }
        } catch (ClassCastException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public <T extends UaStructure> T[] readBuiltinStructArray(
        String field, Class<T> clazz) throws UaSerializationException {

        return readArray(field, s -> readBuiltinStruct(s, clazz), clazz);
    }

}
