/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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

