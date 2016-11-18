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
import java.util.UUID;
import java.util.function.BiConsumer;
import javax.annotation.Nonnull;

import io.netty.buffer.ByteBuf;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.channel.ChannelConfig;
import org.eclipse.milo.opcua.stack.core.serialization.DataTypeEncoding;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.EncoderDelegate;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
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
import org.eclipse.milo.opcua.stack.core.types.enumerated.IdType;
import org.eclipse.milo.opcua.stack.core.util.ArrayUtil;
import org.eclipse.milo.opcua.stack.core.util.TypeUtil;
import org.slf4j.LoggerFactory;

public class BinaryEncoder implements UaEncoder {

    private static final DelegateRegistry.Instance DELEGATE_REGISTRY = DelegateRegistry.getInstance();

    private volatile ByteBuf buffer;

    private final int maxArrayLength;
    private final int maxStringLength;

    public BinaryEncoder() {
        this(ChannelConfig.DEFAULT_MAX_ARRAY_LENGTH, ChannelConfig.DEFAULT_MAX_STRING_LENGTH);
    }

    public BinaryEncoder(int maxArrayLength, int maxStringLength) {
        this.maxArrayLength = maxArrayLength;
        this.maxStringLength = maxStringLength;
    }

    public BinaryEncoder setBuffer(ByteBuf buffer) {
        this.buffer = buffer;
        return this;
    }

    public ByteBuf getBuffer() {
        return buffer;
    }

    @Override
    public void encodeBoolean(String field, Boolean value) {
        if (value == null) {
            buffer.writeBoolean(false);
        } else {
            buffer.writeBoolean(value);
        }
    }

    @Override
    public void encodeSByte(String field, Byte value) {
        if (value == null) {
            buffer.writeByte(0);
        } else {
            buffer.writeByte(value);
        }
    }

    @Override
    public void encodeInt16(String field, Short value) {
        if (value == null) {
            buffer.writeShort(0);
        } else {
            buffer.writeShort(value);
        }
    }

    @Override
    public void encodeInt32(String field, Integer value) {
        if (value == null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(value);
        }
    }

    @Override
    public void encodeInt64(String field, Long value) {
        if (value == null) {
            buffer.writeLong(0);
        } else {
            buffer.writeLong(value);
        }
    }

    @Override
    public void encodeByte(String field, UByte value) throws UaSerializationException {
        if (value == null) {
            buffer.writeByte(0);
        } else {
            buffer.writeByte(value.intValue());
        }
    }

    @Override
    public void encodeUInt16(String field, UShort value) throws UaSerializationException {
        if (value == null) {
            buffer.writeShort(0);
        } else {
            buffer.writeShort(value.intValue());
        }
    }

    @Override
    public void encodeUInt32(String field, UInteger value) throws UaSerializationException {
        if (value == null) {
            buffer.writeInt(0);
        } else {
            buffer.writeInt(value.intValue());
        }
    }

    @Override
    public void encodeUInt64(String field, ULong value) throws UaSerializationException {
        if (value == null) {
            buffer.writeLong(0);
        } else {
            buffer.writeLong(value.longValue());
        }
    }

    @Override
    public void encodeFloat(String field, Float value) {
        if (value == null) {
            buffer.writeFloat(0);
        } else {
            buffer.writeFloat(value);
        }
    }

    @Override
    public void encodeDouble(String field, Double value) {
        if (value == null) {
            buffer.writeDouble(0);
        } else {
            buffer.writeDouble(value);
        }
    }

    @Override
    public void encodeString(String field, String value) throws UaSerializationException {
        if (value == null) {
            buffer.writeInt(-1);
        } else {
            if (value.length() > maxStringLength) {
                throw new UaSerializationException(StatusCodes.Bad_EncodingLimitsExceeded,
                    "max string length exceeded");
            }

            try {
                // Record the current index and write a placeholder for the length.
                int lengthIndex = buffer.writerIndex();
                buffer.writeInt(0x42424242);

                // Write the string bytes.
                int indexBefore = buffer.writerIndex();
                buffer.writeBytes(value.getBytes("UTF-8"));
                int indexAfter = buffer.writerIndex();
                int bytesWritten = indexAfter - indexBefore;

                // Go back and update the length.
                buffer.writerIndex(lengthIndex);
                buffer.writeInt(bytesWritten);

                // Return to where we were after writing the string.
                buffer.writerIndex(indexAfter);
            } catch (UnsupportedEncodingException e) {
                throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
            }
        }
    }

    @Override
    public void encodeDateTime(String field, DateTime value) {
        if (value == null) {
            buffer.writeLong(0L);
        } else {
            buffer.writeLong(value.getUtcTime());
        }
    }

    @Override
    public void encodeGuid(String field, UUID value) {
        if (value == null) {
            buffer.writeZero(16);
        } else {
            long msb = value.getMostSignificantBits();
            long lsb = value.getLeastSignificantBits();

            buffer.writeInt((int) (msb >>> 32));
            buffer.writeShort((int) (msb >>> 16) & 0xFFFF);
            buffer.writeShort((int) (msb) & 0xFFFF);

            buffer.order(ByteOrder.BIG_ENDIAN).writeLong(lsb);
        }
    }

    @Override
    public void encodeByteString(String field, ByteString value) {
        if (value == null || value.isNull()) {
            buffer.writeInt(-1);
        } else {
            byte[] bytes = value.bytes();

            assert (bytes != null);

            buffer.writeInt(bytes.length);
            buffer.writeBytes(bytes);
        }
    }

    @Override
    public void encodeXmlElement(String field, XmlElement value) throws UaSerializationException {
        if (value == null || value.isNull()) {
            buffer.writeInt(-1);
        } else {
            try {
                encodeByteString(null, new ByteString(value.getFragment().getBytes("UTF-8")));
            } catch (UnsupportedEncodingException e) {
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
            }
        }
    }

    @Override
    public void encodeNodeId(String field, NodeId value) throws UaSerializationException {
        if (value == null) value = NodeId.NULL_VALUE;

        int namespaceIndex = value.getNamespaceIndex().intValue();

        if (value.getType() == IdType.Numeric) {
            UInteger identifier = (UInteger) value.getIdentifier();
            long idv = identifier.longValue();

            if (namespaceIndex == 0 && idv >= 0 && idv <= 255) {
                /* Two-byte format */
                buffer.writeByte(0x00);
                buffer.writeByte((int) idv);
            } else if (namespaceIndex >= 0 && namespaceIndex <= 255 && idv <= 65535) {
                /* Four-byte format */
                buffer.writeByte(0x01);
                buffer.writeByte(namespaceIndex);
                buffer.writeShort((int) idv);
            } else {
                /* Numeric format */
                buffer.writeByte(0x02);
                buffer.writeShort(namespaceIndex);
                buffer.writeInt((int) idv);
            }
        } else if (value.getType() == IdType.String) {
            String identifier = (String) value.getIdentifier();

            buffer.writeByte(0x03);
            buffer.writeShort(namespaceIndex);
            encodeString(null, identifier);
        } else if (value.getType() == IdType.Guid) {
            UUID identifier = (UUID) value.getIdentifier();

            buffer.writeByte(0x04);
            buffer.writeShort(namespaceIndex);
            encodeGuid(null, identifier);
        } else if (value.getType() == IdType.Opaque) {
            ByteString identifier = (ByteString) value.getIdentifier();

            buffer.writeByte(0x05);
            buffer.writeShort(namespaceIndex);
            encodeByteString(null, identifier);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                "invalid identifier: " + value.getIdentifier());
        }
    }

    @Override
    public void encodeExpandedNodeId(String field, ExpandedNodeId value) throws UaSerializationException {
        if (value == null) value = ExpandedNodeId.NULL_VALUE;

        int flags = 0;
        String namespaceUri = value.getNamespaceUri();
        long serverIndex = value.getServerIndex();

        if (namespaceUri != null && namespaceUri.length() > 0) {
            flags |= 0x80;
        }

        if (serverIndex > 0) {
            flags |= 0x40;
        }

        int namespaceIndex = value.getNamespaceIndex().intValue();

        if (value.getType() == IdType.Numeric) {
            UInteger identifier = (UInteger) value.getIdentifier();
            long idv = identifier.longValue();

            if (namespaceIndex == 0 && idv >= 0 && idv <= 255) {
                /* Two-byte format */
                buffer.writeByte(flags);
                buffer.writeByte((int) idv);
            } else if (namespaceIndex >= 0 && namespaceIndex <= 255 && idv <= 65535) {
                /* Four-byte format */
                buffer.writeByte(0x01 | flags);
                buffer.writeByte(namespaceIndex);
                buffer.writeShort((int) idv);
            } else {
                /* Numeric format */
                buffer.writeByte(0x02 | flags);
                buffer.writeShort(namespaceIndex);
                buffer.writeInt((int) idv);
            }
        } else if (value.getType() == IdType.String) {
            String identifier = (String) value.getIdentifier();

            buffer.writeByte(0x03 | flags);
            buffer.writeShort(namespaceIndex);
            encodeString(null, identifier);
        } else if (value.getType() == IdType.Guid) {
            UUID identifier = (UUID) value.getIdentifier();

            buffer.writeByte(0x04 | flags);
            buffer.writeShort(namespaceIndex);
            encodeGuid(null, identifier);
        } else if (value.getType() == IdType.Opaque) {
            ByteString identifier = (ByteString) value.getIdentifier();

            buffer.writeByte(0x05 | flags);
            buffer.writeShort(namespaceIndex);
            encodeByteString(null, identifier);
        } else {
            throw new UaSerializationException(
                StatusCodes.Bad_EncodingError,
                "invalid identifier: " + value.getIdentifier());
        }

        if (namespaceUri != null && namespaceUri.length() > 0) {
            encodeString(null, namespaceUri);
        }

        if (serverIndex > 0) {
            encodeUInt32(null, Unsigned.uint(serverIndex));
        }
    }

    @Override
    public void encodeStatusCode(String field, StatusCode value) {
        if (value == null) {
            buffer.writeInt(0);
        } else {
            encodeUInt32(null, Unsigned.uint(value.getValue()));
        }
    }

    @Override
    public void encodeQualifiedName(String field, QualifiedName value) throws UaSerializationException {
        if (value == null) value = QualifiedName.NULL_VALUE;

        encodeUInt16(null, value.getNamespaceIndex());
        encodeString(null, value.getName());
    }

    @Override
    public void encodeLocalizedText(String field, LocalizedText value) throws UaSerializationException {
        if (value == null) value = LocalizedText.NULL_VALUE;

        String locale = value.getLocale();
        String text = value.getText();

        int mask = 1 | 2;
        if (locale == null || locale.isEmpty()) {
            mask ^= 1;
        }
        if (text == null || text.isEmpty()) {
            mask ^= 2;
        }

        buffer.writeByte(mask);

        if (locale != null && !locale.isEmpty()) {
            encodeString(null, locale);
        }
        if (text != null && !text.isEmpty()) {
            encodeString(null, text);
        }
    }

    @Override
    public void encodeExtensionObject(String field, ExtensionObject value) throws UaSerializationException {
        if (value == null || value.isNull()) {
            encodeNodeId(null, NodeId.NULL_VALUE);
            buffer.writeByte(0); // No body is encoded
        } else {
            Object object = value.getEncodedBody(DataTypeEncoding.OPC_UA);

            switch (value.getBodyType()) {
                case ByteString: {
                    ByteString byteString = (ByteString) object;

                    encodeNodeId(null, value.getEncodingTypeId());
                    buffer.writeByte(1); // Body is binary encoded

                    encodeByteString(null, byteString);

                    break;
                }
                case XmlElement: {
                    XmlElement xmlElement = (XmlElement) object;

                    encodeNodeId(null, value.getEncodingTypeId());
                    buffer.writeByte(2);

                    encodeXmlElement(null, xmlElement);

                    break;
                }

                default:
                    throw new IllegalStateException("unknown body type: " + value.getBodyType());
            }
        }
    }

    @Override
    public void encodeDataValue(String field, DataValue value) throws UaSerializationException {
        if (value == null) {
            buffer.writeByte(0);
        } else {
            int mask = 0x00;

            if (value.getValue() != null && value.getValue().isNotNull()) mask |= 0x01;
            if (!StatusCode.GOOD.equals(value.getStatusCode())) mask |= 0x02;
            if (!DateTime.MIN_VALUE.equals(value.getSourceTime())) mask |= 0x04;
            if (!DateTime.MIN_VALUE.equals(value.getServerTime())) mask |= 0x08;

            buffer.writeByte(mask);

            if ((mask & 0x01) == 0x01) encodeVariant(null, value.getValue());
            if ((mask & 0x02) == 0x02) encodeStatusCode(null, value.getStatusCode());
            if ((mask & 0x04) == 0x04) encodeDateTime(null, value.getSourceTime());
            if ((mask & 0x08) == 0x08) encodeDateTime(null, value.getServerTime());
        }
    }

    @Override
    public void encodeVariant(String field, Variant variant) throws UaSerializationException {
        Object value = variant.getValue();

        if (value == null) {
            buffer.writeByte(0);
        } else {
            boolean structure = false;
            boolean enumeration = false;
            Class<?> valueClass = getClass(value);

            if (UaStructure.class.isAssignableFrom(valueClass)) {
                valueClass = ExtensionObject.class;
                structure = true;
            } else if (UaEnumeration.class.isAssignableFrom(valueClass)) {
                valueClass = Integer.class;
                enumeration = true;
            }

            int typeId = TypeUtil.getBuiltinTypeId(valueClass);

            if (typeId == -1) {
                LoggerFactory.getLogger(getClass())
                    .warn("Not a built-in type: {}", valueClass);
            }

            if (value.getClass().isArray()) {
                int[] dimensions = ArrayUtil.getDimensions(value);

                if (dimensions.length == 1) {
                    buffer.writeByte(typeId | 0x80);

                    int length = Array.getLength(value);
                    buffer.writeInt(length);

                    for (int i = 0; i < length; i++) {
                        Object o = Array.get(value, i);

                        encodeValue(o, typeId, structure, enumeration);
                    }
                } else {
                    buffer.writeByte(typeId | 0xC0);

                    Object flattened = ArrayUtil.flatten(value);
                    int length = Array.getLength(flattened);
                    buffer.writeInt(length);

                    for (int i = 0; i < length; i++) {
                        Object o = Array.get(flattened, i);

                        encodeValue(o, typeId, structure, enumeration);
                    }

                    encodeInt32(null, dimensions.length);
                    for (int dimension : dimensions) {
                        encodeInt32(null, dimension);
                    }
                }
            } else {
                buffer.writeByte(typeId);

                encodeValue(value, typeId, structure, enumeration);
            }
        }
    }

    private void encodeValue(Object value, int typeId, boolean structured, boolean enumeration) {
        if (structured) {
            UaStructure structure = (UaStructure) value;

            ExtensionObject extensionObject = ExtensionObject.fromStructure(structure);

            encodeBuiltinType(typeId, extensionObject);
        } else if (enumeration) {
            encodeBuiltinType(typeId, ((UaEnumeration) value).getValue());
        } else {
            encodeBuiltinType(typeId, value);
        }
    }

    private Class<?> getClass(@Nonnull Object o) {
        if (o.getClass().isArray()) {
            return ArrayUtil.getType(o);
        } else {
            return o.getClass();
        }
    }


    @Override
    public void encodeDiagnosticInfo(String field, DiagnosticInfo value) throws UaSerializationException {
        if (value == null) {
            buffer.writeByte(0);
        } else {
            int mask = 0x7F;

            if (value.getSymbolicId() == -1) mask ^= 0x01;
            if (value.getNamespaceUri() == -1) mask ^= 0x02;
            if (value.getLocalizedText() == -1) mask ^= 0x04;
            if (value.getLocale() == -1) mask ^= 0x08;
            if (value.getAdditionalInfo() == null || value.getAdditionalInfo().isEmpty()) mask ^= 0x10;
            if (value.getInnerStatusCode() == null) mask ^= 0x20;
            if (value.getInnerDiagnosticInfo() == null) mask ^= 0x40;

            buffer.writeByte(mask);

            if ((mask & 0x01) == 0x01) encodeInt32(null, value.getSymbolicId());
            if ((mask & 0x02) == 0x02) encodeInt32(null, value.getNamespaceUri());
            if ((mask & 0x04) == 0x04) encodeInt32(null, value.getLocalizedText());
            if ((mask & 0x08) == 0x08) encodeInt32(null, value.getLocale());
            if ((mask & 0x10) == 0x10) encodeString(null, value.getAdditionalInfo());
            if ((mask & 0x20) == 0x20) encodeStatusCode(null, value.getInnerStatusCode());
            if ((mask & 0x40) == 0x40) encodeDiagnosticInfo(null, value.getInnerDiagnosticInfo());
        }
    }

    @Override
    public <T extends UaStructure> void encodeMessage(String field, T message) throws UaSerializationException {
        EncoderDelegate<T> delegate = DELEGATE_REGISTRY.getEncoder(message.getBinaryEncodingId());

        encodeNodeId(null, message.getBinaryEncodingId());

        delegate.encode(message, this);
    }

    @Override
    public <T extends UaEnumeration> void encodeEnumeration(String field, T value) throws UaSerializationException {
        if (value == null) {
            encodeInt32(null, -1);
        } else {
            EncoderDelegate<T> delegate = DELEGATE_REGISTRY.getEncoder(value);

            delegate.encode(value, this);
        }
    }

    @Override
    public <T extends UaSerializable> void encodeSerializable(String field, T value) throws UaSerializationException {
        EncoderDelegate<T> delegate = DELEGATE_REGISTRY.getEncoder(value);

        delegate.encode(value, this);
    }

    @Override
    public <T> void encodeArray(
        String field, T[] values,
        BiConsumer<String, T> consumer) throws UaSerializationException {

        if (values == null) {
            buffer.writeInt(-1);
        } else {
            if (values.length > maxArrayLength) {
                throw new UaSerializationException(StatusCodes.Bad_EncodingLimitsExceeded,
                    "max array length exceeded");
            }

            encodeInt32(null, values.length);
            for (T t : values) {
                consumer.accept(null, t);
            }
        }
    }

    private void encodeBuiltinType(int typeId, Object value) throws UaSerializationException {
        switch (typeId) {
            case 1:
                encodeBoolean(null, (Boolean) value);
                break;
            case 2:
                encodeSByte(null, (Byte) value);
                break;
            case 3:
                encodeByte(null, (UByte) value);
                break;
            case 4:
                encodeInt16(null, (Short) value);
                break;
            case 5:
                encodeUInt16(null, (UShort) value);
                break;
            case 6:
                encodeInt32(null, (Integer) value);
                break;
            case 7:
                encodeUInt32(null, (UInteger) value);
                break;
            case 8:
                encodeInt64(null, (Long) value);
                break;
            case 9:
                encodeUInt64(null, (ULong) value);
                break;
            case 10:
                encodeFloat(null, (Float) value);
                break;
            case 11:
                encodeDouble(null, (Double) value);
                break;
            case 12:
                encodeString(null, (String) value);
                break;
            case 13:
                encodeDateTime(null, (DateTime) value);
                break;
            case 14:
                encodeGuid(null, (UUID) value);
                break;
            case 15:
                encodeByteString(null, (ByteString) value);
                break;
            case 16:
                encodeXmlElement(null, (XmlElement) value);
                break;
            case 17:
                encodeNodeId(null, (NodeId) value);
                break;
            case 18:
                encodeExpandedNodeId(null, (ExpandedNodeId) value);
                break;
            case 19:
                encodeStatusCode(null, (StatusCode) value);
                break;
            case 20:
                encodeQualifiedName(null, (QualifiedName) value);
                break;
            case 21:
                encodeLocalizedText(null, (LocalizedText) value);
                break;
            case 22:
                encodeExtensionObject(null, (ExtensionObject) value);
                break;
            case 23:
                encodeDataValue(null, (DataValue) value);
                break;
            case 24:
                encodeVariant(null, (Variant) value);
                break;
            case 25:
                encodeDiagnosticInfo(null, (DiagnosticInfo) value);
                break;
            default:
                throw new UaSerializationException(StatusCodes.Bad_DecodingError, "unknown builtin type: " + typeId);
        }
    }

}
