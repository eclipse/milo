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

package org.eclipse.milo.opcua.stack.core.serialization.codecs;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;

public abstract class GenericDataTypeCodec<T> implements DataTypeCodec<T, UaDecoder, UaEncoder> {

    public final OpcUaBinaryDataTypeCodec<T> asBinaryCodec() {
        return new GenericBinaryDataTypeCodec<>(this);
    }

    public final OpcUaXmlDataTypeCodec<T> asXmlCodec() {
        return new GenericXmlDataTypeCodec<>(this);
    }

    private static class GenericBinaryDataTypeCodec<T> implements OpcUaBinaryDataTypeCodec<T> {

        private final GenericDataTypeCodec<T> codec;

        public GenericBinaryDataTypeCodec(GenericDataTypeCodec<T> codec) {
            this.codec = codec;
        }

        @Override
        public Class<T> getType() {
            return codec.getType();
        }

        @Override
        public T decode(SerializationContext context, OpcUaBinaryStreamDecoder reader) throws UaSerializationException {
            return codec.decode(context, reader);
        }

        @Override
        public void encode(
            SerializationContext context, T value, OpcUaBinaryStreamEncoder writer) throws UaSerializationException {

            codec.encode(context, value, writer);
        }

    }

    private static class GenericXmlDataTypeCodec<T> implements OpcUaXmlDataTypeCodec<T> {

        private final GenericDataTypeCodec<T> codec;

        public GenericXmlDataTypeCodec(GenericDataTypeCodec<T> codec) {
            this.codec = codec;
        }

        @Override
        public Class<T> getType() {
            return codec.getType();
        }

        @Override
        public T decode(SerializationContext context, OpcUaXmlStreamDecoder reader) throws UaSerializationException {
            return codec.decode(context, reader);
        }

        @Override
        public void encode(
            SerializationContext context, T value, OpcUaXmlStreamEncoder writer) throws UaSerializationException {

            codec.encode(context, value, writer);
        }

    }

}

