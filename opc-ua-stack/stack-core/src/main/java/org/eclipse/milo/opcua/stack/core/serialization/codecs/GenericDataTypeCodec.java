/*
 * Copyright (c) 2022 the Eclipse Milo Authors
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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaJsonDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaJsonEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;

public abstract class GenericDataTypeCodec<T> implements DataTypeCodec<T> {

    /**
     * @return a derived instance of {@link OpcUaBinaryDataTypeCodec} that handles serialization of {@link T}.
     */
    public final OpcUaBinaryDataTypeCodec<T> asBinaryCodec() {
        return new GenericBinaryDataTypeCodec<>(this);
    }

    /**
     * @return a derived instance of {@link OpcUaXmlDataTypeCodec} that handles serialization of {@link T}.
     */
    public final OpcUaXmlDataTypeCodec<T> asXmlCodec() {
        return new GenericXmlDataTypeCodec<>(this);
    }

    /**
     * @return a derived instance of {@link OpcUaJsonDataTypeCodec} that handles serialization of {@link T}.
     */
    public final OpcUaJsonDataTypeCodec<T> asJsonCodec() {
        return new GenericJsonDataTypeCodec<>(this);
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
            SerializationContext context, OpcUaBinaryStreamEncoder writer, T value) throws UaSerializationException {

            codec.encode(context, writer, value);
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
            SerializationContext context, OpcUaXmlStreamEncoder writer, T value) throws UaSerializationException {

            codec.encode(context, writer, value);
        }

    }

    private static class GenericJsonDataTypeCodec<T> implements OpcUaJsonDataTypeCodec<T> {

        private final GenericDataTypeCodec<T> codec;

        public GenericJsonDataTypeCodec(GenericDataTypeCodec<T> codec) {
            this.codec = codec;
        }

        @Override
        public Class<T> getType() {
            return codec.getType();
        }

        @Override
        public T decode(SerializationContext context, OpcUaJsonDecoder decoder) throws UaSerializationException {
            return codec.decode(context, decoder);
        }

        @Override
        public void encode(SerializationContext context, OpcUaJsonEncoder encoder, T value) throws UaSerializationException {
            codec.encode(context, encoder, value);
        }

    }

}

