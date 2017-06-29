/*
 * Copyright (c) 2017 Kevin Herron
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

package org.eclipse.milo.opcua.stack.core.types;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.ByteOrder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.eclipse.milo.opcua.stack.core.StatusCodes;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaXmlDataTypeCodec;
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
            OpcUaBinaryDataTypeCodec<Object> codec =
                (OpcUaBinaryDataTypeCodec<Object>) dataTypeManager.getBinaryCodec(encodingTypeId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_EncodingError,
                    "no codec registered for encodingTypeId=" + encodingTypeId);
            }

            ByteBuf buffer = allocator.buffer().order(ByteOrder.LITTLE_ENDIAN);

            OpcUaBinaryStreamEncoder writer = new OpcUaBinaryStreamEncoder(buffer);

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
            OpcUaXmlDataTypeCodec<Object> codec =
                (OpcUaXmlDataTypeCodec<Object>) dataTypeManager.getXmlCodec(encodingTypeId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_EncodingError,
                    "no codec registered for encodingTypeId=" + encodingTypeId);
            }

            OpcUaXmlStreamEncoder writer = new OpcUaXmlStreamEncoder();

            codec.encode(() -> dataTypeManager, object, writer);

            return new XmlElement(writer.getDocumentXml());
        } catch (ClassCastException e) {
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
            OpcUaBinaryDataTypeCodec<Object> codec =
                (OpcUaBinaryDataTypeCodec<Object>) dataTypeManager.getBinaryCodec(encodingTypeId);

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

            OpcUaBinaryStreamDecoder reader = new OpcUaBinaryStreamDecoder(buffer);

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
            OpcUaXmlDataTypeCodec<Object> codec =
                (OpcUaXmlDataTypeCodec<Object>) dataTypeManager.getXmlCodec(encodingTypeId);

            if (codec == null) {
                throw new UaSerializationException(
                    StatusCodes.Bad_DecodingError,
                    "no codec registered for encodingTypeId=" + encodingTypeId);
            }

            OpcUaXmlStreamDecoder reader = new OpcUaXmlStreamDecoder();
            reader.setInput(new ByteArrayInputStream(encoded.getFragment().getBytes()));

            return reader.readStruct(null, encodingTypeId);
        } catch (IOException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

}
