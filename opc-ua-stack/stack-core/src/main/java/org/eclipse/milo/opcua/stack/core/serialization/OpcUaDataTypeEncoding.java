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
import org.eclipse.milo.opcua.stack.core.serialization.binary.BinaryDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.binary.BinaryEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.xml.XmlDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.xml.XmlEncoder;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.XmlElement;

public class OpcUaDataTypeEncoding implements DataTypeEncoding {

    private static final DelegateRegistry.Instance DELEGATE_REGISTRY = DelegateRegistry.getInstance();

    private final ByteBufAllocator allocator = ByteBufAllocator.DEFAULT;

    @Override
    public ByteString encodeToByteString(Object object, NodeId encodingTypeId) {
        EncoderDelegate<Object> delegate = DELEGATE_REGISTRY.getEncoder(encodingTypeId);

        ByteBuf buffer = allocator.buffer().order(ByteOrder.LITTLE_ENDIAN);

        BinaryEncoder encoder = new BinaryEncoder();
        encoder.setBuffer(buffer);

        delegate.encode(object, encoder);

        byte[] bs = new byte[buffer.readableBytes()];
        buffer.readBytes(bs);
        buffer.release();

        return ByteString.of(bs);
    }

    @Override
    public Object decodeFromByteString(ByteString encoded, NodeId encodingTypeId) {
        DecoderDelegate<Object> delegate = DELEGATE_REGISTRY.getDecoder(encodingTypeId);

        byte[] bs = encoded.bytes();
        if (bs == null) bs = new byte[0];

        ByteBuf buffer = Unpooled
            .wrappedBuffer(bs)
            .order(ByteOrder.LITTLE_ENDIAN);

        BinaryDecoder decoder = new BinaryDecoder();
        decoder.setBuffer(buffer);

        return delegate.decode(decoder);
    }

    @Override
    public XmlElement encodeToXmlElement(Object object, NodeId encodingTypeId) {
        try {
            EncoderDelegate<Object> delegate = DELEGATE_REGISTRY.getEncoder(encodingTypeId);

            StringWriter stringWriter = new StringWriter();

            XmlEncoder encoder = new XmlEncoder();
            encoder.setOutput(stringWriter);

            delegate.encode(object, encoder);

            return new XmlElement(stringWriter.toString());
        } catch (XMLStreamException e) {
            throw new UaSerializationException(StatusCodes.Bad_EncodingError, e);
        }
    }

    @Override
    public Object decodeFromXmlElement(XmlElement encoded, NodeId encodingTypeId) {
        try {
            DecoderDelegate<Object> delegate = DELEGATE_REGISTRY.getDecoder(encodingTypeId);

            XmlDecoder decoder = new XmlDecoder();
            decoder.setInput(new StringReader(encoded.getFragment()));

            return delegate.decode(decoder);
        } catch (XMLStreamException e) {
            throw new UaSerializationException(StatusCodes.Bad_DecodingError, e);
        }
    }

}
