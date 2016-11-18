/*
 * Copyright (c) 2016 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.serialization.binary;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.nio.ByteOrder;
import java.nio.charset.Charset;
import java.util.UUID;
import java.util.function.BiFunction;
import java.util.function.Function;

import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.serialization.DecoderDelegate;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.UaSerializable;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned;
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.eclipse.milo.opcua.stack.core.util.TypeUtil;

public class BinaryDecoder implements UaDecoder {

    private static final DelegateRegistry.Instance DELEGATE_REGISTRY = DelegateRegistry.getInstance();

    private volatile ByteBuf buffer;

    private final int maxArrayLength;
    private final int maxStringLength;

    public BinaryDecoder() {
        this(ChannelConfig.DEFAULT_MAX_ARRAY_LENGTH, ChannelConfig.DEFAULT_MAX_STRING_LENGTH);
    }

    public BinaryDecoder(int maxArrayLength, int maxStringLength) {
        this.maxArrayLength = maxArrayLength;
        this.maxStringLength = maxStringLength;
    }

    public BinaryDecoder setBuffer(ByteBuf buffer) {
        this.buffer = buffer;
        return this;
    }

    @Override
    public Boolean decodeBoolean(String field) {
        return buffer.readBoolean();
    }

    @Override
    public Byte decodeSByte(String field) {
        return buffer.readByte();
    }

    @Override
    public Short decodeInt16(String field) {
        return buffer.readShort();
    }

    @Override
    public Integer decodeInt32(String field) {
        return buffer.readInt();
    }

    @Override
    public Long decodeInt64(String field) {
        return buffer.readLong();
    }

    @Override
    public UByte decodeByte(String field) {
        return Unsigned.ubyte(buffer.readUnsignedByte());
    }

    @Override
    public UShort decodeUInt16(String field) {
        return Unsigned.ushort(buffer.readUnsignedShort());
    }

    @Override
    public UInteger decodeUInt32(String field) {
        return Unsigned.uint(buffer.readUnsignedInt());
    }

    @Override
    public ULong decodeUInt64(String field) {
        return Unsigned.ulong(buffer.readLong());
    }

    @Override
    public Float decodeFloat(String field) {
        return buffer.readFloat();
    }

    @Override
    public Double decodeDouble(String field) {
        return buffer.readDouble();
    }

    @Override
    public String decodeString(String field) throws UaSerializationException {
        int length = decodeInt32(null);

        if (length == -1) {
            return null;
        } else {
            if (length > maxStringLength) {
                throw new UaSerializationException(StatusCodes.Bad_EncodingLimitsExceeded,
                    String.format("max string length exceeded (length=%s, max=%s)", length, maxStringLength));
            }

            String s = buffer.toString(buffer.readerIndex(), length, Charset.forName("UTF-8"));
            buffer.skipBytes(length);
            return s;
        }
    }

    @Override
    public DateTime decodeDateTime(String field) {
        return new DateTime(buffer.readLong());
    }

    @Override
    public UUID decodeGuid(String field) {
        long part1 = buffer.readUnsignedInt();
        long part2 = buffer.readUnsignedShort();
        long part3 = buffer.readUnsignedShort();
        long part4 = buffer.order(ByteOrder.BIG_ENDIAN).readLong();

        long msb = (part1 << 32) | (part2 << 16) | part3;

        return new UUID(msb, part4);
    }

    @Override
    public ByteString decodeByteString(String field) {
        int length = decodeInt32(null);

        if (length == -1) {
            return ByteString.NULL_VALUE;
        } else {
            byte[] bs = new byte[length];
            buffer.readBytes(bs);
            return new ByteString(bs);
        }
    }

    @Override
    public XmlElement decodeXmlElement(String field) throws UaSerializationException {
        ByteString byteString = decodeByteString(null);
        byte[] bs = byteString.bytes();

        if (bs == null) {
            return new XmlElement(null);
        } else {
            try {
                String fragment = new String(bs, "UTF-8");
                return new XmlElement(fragment);
            } catch (UnsupportedEncodingException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        }
    }

    @Override
    public NodeId decodeNodeId(String field) throws UaSerializationException {
        int format = buffer.readByte() & 0x0F;


        if (format == 0x00) {
            /* Two-byte format */
            return new NodeId(Unsigned.ushort(0), Unsigned.uint(buffer.readUnsignedByte()));
        } else if (format == 0x01) {
            /* Four-byte format */
            return new NodeId(Unsigned.ushort(buffer.readUnsignedByte()), Unsigned.uint(buffer.readUnsignedShort()));
        } else if (format == 0x02) {
            /* Numeric format */
            return new NodeId(Unsigned.ushort(buffer.readUnsignedShort()), Unsigned.uint(buffer.readUnsignedInt()));
        } else if (format == 0x03) {
            /* String format */
            return new NodeId(Unsigned.ushort(buffer.readUnsignedShort()), decodeString(null));
        } else if (format == 0x04) {
            /* Guid format */
            return new NodeId(Unsigned.ushort(buffer.readUnsignedShort()), decodeGuid(null));
        } else if (format == 0x05) {
            /* Opaque format */
            return new NodeId(Unsigned.ushort(buffer.readUnsignedShort()), decodeByteString(null));
        } else {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, "invalid NodeId format: " + format);
        }
    }

    @Override
    public ExpandedNodeId decodeExpandedNodeId(String field) throws UaSerializationException {
        int flags = buffer.getByte(buffer.readerIndex());

        NodeId nodeId = decodeNodeId(null);

        String namespaceUri = null;
        long serverIndex = 0;

        if ((flags & 0x80) == 0x80) {
            namespaceUri = decodeString(null);
        }

        if ((flags & 0x40) == 0x40) {
            serverIndex = decodeUInt32(null).longValue();
        }

        return new ExpandedNodeId(nodeId, namespaceUri, serverIndex);
    }

    @Override
    public StatusCode decodeStatusCode(String field) {
        return new StatusCode(decodeUInt32(null));
    }

    @Override
    public QualifiedName decodeQualifiedName(String field) throws UaSerializationException {
        int namespaceIndex = decodeUInt16(null).intValue();
        String name = decodeString(null);

        return new QualifiedName(Unsigned.ushort(namespaceIndex), name);
    }

    @Override
    public LocalizedText decodeLocalizedText(String field) throws UaSerializationException {
        int mask = buffer.readByte();

        String locale = null;
        String text = null;

        if ((mask & 1) == 1) {
            locale = decodeString(null);
        }

        if ((mask & 2) == 2) {
            text = decodeString(null);
        }

        return new LocalizedText(locale, text);
    }

    @Override
    public ExtensionObject decodeExtensionObject(String field) throws UaSerializationException {
        NodeId encodingTypeId = decodeNodeId(null);
        int encoding = buffer.readByte();

        if (encoding == 0) {
            return ExtensionObject.fromByteString(null, encodingTypeId);
        } else if (encoding == 1) {
            ByteString byteString = decodeByteString(null);

            if (byteString.isNotNull()) {
                return ExtensionObject.fromByteString(byteString, encodingTypeId);
            } else {
                // Workaround for a bug in other stack(s) where the length is encoded as -1 even though a body
                // is encoded.
                // The encoding byte above has indicated a body is encoded, so if we know the encodingTypeId then
                // we can decode the body anyway.
                Object o = DelegateRegistry.getInstance().getDecoder(encodingTypeId).decode(this);
                return ExtensionObject.fromObject(o, encodingTypeId, ExtensionObject.BodyType.ByteString);
            }
        } else if (encoding == 2) {
            XmlElement xmlElement = decodeXmlElement(null);

            if (xmlElement.isNotNull()) {
                return ExtensionObject.fromXmlElement(xmlElement, encodingTypeId);
            } else {
                // See workaround explained above.
                Object o = DelegateRegistry.getInstance().getDecoder(encodingTypeId).decode(this);
                return ExtensionObject.fromObject(o, encodingTypeId, ExtensionObject.BodyType.XmlElement);
            }
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_DecodingError,
                "unknown ExtensionObject encoding: " + encoding);
        }
    }

    @Override
    public DataValue decodeDataValue(String field) throws UaSerializationException {
        int mask = buffer.readByte() & 0x0F;

        Variant value = ((mask & 0x01) == 0x01) ? decodeVariant(null) : Variant.NULL_VALUE;
        StatusCode status = ((mask & 0x02) == 0x02) ? decodeStatusCode(null) : StatusCode.GOOD;
        DateTime sourceTime = ((mask & 0x04) == 0x04) ? decodeDateTime(null) : DateTime.MIN_VALUE;
        DateTime serverTime = ((mask & 0x08) == 0x08) ? decodeDateTime(null) : DateTime.MIN_VALUE;

        return new DataValue(value, status, sourceTime, serverTime);
    }

    @Override
    public Variant decodeVariant(String field) throws UaSerializationException {
        int encodingMask = buffer.readByte();

        if (encodingMask == 0) {
            return new Variant(null);
        } else {
            int typeId = encodingMask & 0x3F;
            boolean dimensionsEncoded = (encodingMask & 0x40) == 0x40;
            boolean arrayEncoded = (encodingMask & 0x80) == 0x80;

            if (arrayEncoded) {
                Class<?> backingClass = TypeUtil.getBackingClass(typeId);
                int length = decodeInt32(null);

                if (length == -1) {
                    return new Variant(null);
                } else {
                    if (length > maxArrayLength) {
                        throw new UaSerializationException(StatusCodes.Bad_EncodingLimitsExceeded,
                            String.format("max array length exceeded (length=%s, max=%s)", length, maxArrayLength));
                    }

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
    }

    @Override
    public DiagnosticInfo decodeDiagnosticInfo(String field) throws UaSerializationException {
        int mask = buffer.readByte();

        if (mask == 0) {
            return null;
        } else {
            int symbolicId = ((mask & 0x01) == 0x01) ? decodeInt32(null) : -1;
            int namespaceUri = ((mask & 0x02) == 0x02) ? decodeInt32(null) : -1;
            int localizedText = ((mask & 0x04) == 0x04) ? decodeInt32(null) : -1;
            int locale = ((mask & 0x08) == 0x08) ? decodeInt32(null) : -1;
            String additionalInfo = ((mask & 0x10) == 0x10) ? decodeString(null) : null;
            StatusCode innerStatusCode = ((mask & 0x20) == 0x20) ? decodeStatusCode(null) : null;
            DiagnosticInfo innerDiagnosticInfo = ((mask & 0x40) == 0x40) ? decodeDiagnosticInfo(null) : null;

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
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends UaStructure> T decodeMessage(String field) throws UaSerializationException {
        NodeId encodingId = decodeNodeId(null);

        DecoderDelegate<?> delegate = DELEGATE_REGISTRY.getDecoder(encodingId);

        return (T) delegate.decode(this);
    }

    @Override
    public <T extends UaEnumeration> T decodeEnumeration(String field,
                                                         Class<T> clazz) throws UaSerializationException {

        DecoderDelegate<T> delegate = DELEGATE_REGISTRY.getDecoder(clazz);

        return delegate.decode(this);
    }

    @Override
    public <T extends UaSerializable> T decodeSerializable(String field,
                                                           Class<T> clazz) throws UaSerializationException {

        DecoderDelegate<T> delegate = DELEGATE_REGISTRY.getDecoder(clazz);

        return delegate.decode(this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] decodeArray(String field,
                               Function<String, T> decoder,
                               Class<T> clazz) throws UaSerializationException {

        int length = decodeInt32(null);

        if (length == -1) {
            return null;
        } else {
            if (length > maxArrayLength) {
                throw new UaSerializationException(StatusCodes.Bad_EncodingLimitsExceeded,
                    String.format("max array length exceeded (length=%s, max=%s)", length, maxArrayLength));
            }

            Object array = Array.newInstance(clazz, length);

            for (int i = 0; i < length; i++) {
                Array.set(array, i, decoder.apply(null));
            }

            return (T[]) array;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] decodeArray(String field,
                               BiFunction<String, Class<T>, T> decoder,
                               Class<T> clazz) throws UaSerializationException {

        int length = decodeInt32(null);

        if (length == -1) {
            return null;
        } else {
            if (length > maxArrayLength) {
                throw new UaSerializationException(StatusCodes.Bad_EncodingLimitsExceeded,
                    String.format("max array length exceeded (length=%s, max=%s)", length, maxArrayLength));
            }

            T[] array = (T[]) Array.newInstance(clazz, length);
            for (int i = 0; i < length; i++) {
                array[i] = decoder.apply(null, clazz);
            }
            return array;
        }
    }

    private int[] decodeDimensions() {
        int length = decodeInt32(null);

        if (length == -1) {
            return new int[0];
        } else {
            int[] is = new int[length];
            for (int i = 0; i < length; i++) {
                is[i] = decodeInt32(null);
            }
            return is;
        }
    }

    private Object decodeBuiltinType(int typeId) throws UaSerializationException {
        switch (typeId) {
            case 1:
                return decodeBoolean(null);
            case 2:
                return decodeSByte(null);
            case 3:
                return decodeByte(null);
            case 4:
                return decodeInt16(null);
            case 5:
                return decodeUInt16(null);
            case 6:
                return decodeInt32(null);
            case 7:
                return decodeUInt32(null);
            case 8:
                return decodeInt64(null);
            case 9:
                return decodeUInt64(null);
            case 10:
                return decodeFloat(null);
            case 11:
                return decodeDouble(null);
            case 12:
                return decodeString(null);
            case 13:
                return decodeDateTime(null);
            case 14:
                return decodeGuid(null);
            case 15:
                return decodeByteString(null);
            case 16:
                return decodeXmlElement(null);
            case 17:
                return decodeNodeId(null);
            case 18:
                return decodeExpandedNodeId(null);
            case 19:
                return decodeStatusCode(null);
            case 20:
                return decodeQualifiedName(null);
            case 21:
                return decodeLocalizedText(null);
            case 22:
                return decodeExtensionObject(null);
            case 23:
                return decodeDataValue(null);
            case 24:
                return decodeVariant(null);
            case 25:
                return decodeDiagnosticInfo(null);
            default:
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, "unknown builtin type: " + typeId);
        }
    }

}
