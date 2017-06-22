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

package org.eclipse.milo.opcua.stack.core.serialization;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.ByteOrder;
import javax.xml.stream.XMLStreamException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codec.DataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;

public class OpcUaDataTypeEncoding implements DataTypeEncoding {

    private final ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;

    @Override
    public ByteString encodeToByteString(
        Object object,
        NodeId encodingTypeId,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        try {
            @SuppressWarnings("unchecked")
            OpcBinaryDataTypeCodec<Object> codec =
                (OpcBinaryDataTypeCodec<Object>) dataTypeManager.getBinaryCodec(encodingTypeId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_EncodingError,
                    "no codec registered for encodingTypeId=" + encodingTypeId);
            }

            ByteBuf buffer = allocator.buffer().order(ByteOrder.LITTLE_ENDIAN);

            OpcBinaryStreamWriter writer = new OpcBinaryStreamWriter(buffer);

            codec.encode(() -> dataTypeManager, object, writer);

            byte[] bs = new byte[buffer.readableBytes()];
            buffer.readBytes(bs);
            buffer.release();

            return ByteString.of(bs);
        } catch (ClassCastException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public XmlElement encodeToXmlElement(
        Object object,
        NodeId encodingTypeId,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        try {
            @SuppressWarnings("unchecked")
            OpcXmlDataTypeCodec<Object> codec =
                (OpcXmlDataTypeCodec<Object>) dataTypeManager.getXmlCodec(encodingTypeId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_EncodingError,
                    "no codec registered for encodingTypeId=" + encodingTypeId);
            }

            StringWriter stringWriter = new StringWriter();

            OpcXmlStreamWriter writer = new OpcXmlStreamWriter(stringWriter);

            codec.encode(() -> dataTypeManager, object, writer);

            return new XmlElement(stringWriter.toString());
        } catch (ClassCastException | XMLStreamException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public Object decodeFromByteString(
        ByteString encoded,
        NodeId encodingTypeId,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        try {
            @SuppressWarnings("unchecked")
            OpcBinaryDataTypeCodec<Object> codec =
                (OpcBinaryDataTypeCodec<Object>) dataTypeManager.getBinaryCodec(encodingTypeId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered for encodingTypeId=" + encodingTypeId);
            }

            byte[] bs = encoded.bytes();
            if (bs == null) bs = new byte[0];

            ByteBuf buffer = Unpooled
                .wrappedBuffer(bs)
                .order(ByteOrder.LITTLE_ENDIAN);

            OpcBinaryStreamReader reader = new OpcBinaryStreamReader(buffer);

            return codec.decode(() -> dataTypeManager, reader);
        } catch (ClassCastException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

    @Override
    public Object decodeFromXmlElement(
        XmlElement encoded,
        NodeId encodingTypeId,
        DataTypeManager dataTypeManager) throws UaSerializationException {

        try {
            @SuppressWarnings("unchecked")
            OpcXmlDataTypeCodec<Object> codec =
                (OpcXmlDataTypeCodec<Object>) dataTypeManager.getXmlCodec(encodingTypeId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered for encodingTypeId=" + encodingTypeId);
            }

            OpcXmlStreamReader reader = new OpcXmlStreamReader(new StringReader(encoded.getFragment()));

            return codec.decode(() -> dataTypeManager, reader);
        } catch (ClassCastException | XMLStreamException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

}
