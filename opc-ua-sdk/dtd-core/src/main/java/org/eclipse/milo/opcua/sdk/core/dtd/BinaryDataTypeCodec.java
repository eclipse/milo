/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core.dtd;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.DataTypeCodec;

public interface BinaryDataTypeCodec extends DataTypeCodec {

    @Override
    default Object decode(SerializationContext context, UaDecoder decoder) throws UaSerializationException {
        return decode(context, (OpcUaBinaryStreamDecoder) decoder);
    }

    @Override
    default void encode(SerializationContext context, UaEncoder encoder, Object value) throws UaSerializationException {
        encode(context, (OpcUaBinaryStreamEncoder) encoder, value);
    }

    /**
     * Decode an Object using the provided {@link OpcUaBinaryStreamDecoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param decoder the {@link OpcUaBinaryStreamDecoder} to decode from.
     * @return a decoded Object.
     */
    Object decode(SerializationContext context, OpcUaBinaryStreamDecoder decoder) throws UaSerializationException;

    /**
     * Encode an Object using the provided {@link OpcUaBinaryStreamEncoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param encoder the {@link OpcUaBinaryStreamEncoder} to encode to.
     * @param value   the Object to encode.
     */
    void encode(SerializationContext context, OpcUaBinaryStreamEncoder encoder, Object value) throws UaSerializationException;

    static BinaryDataTypeCodec from(DataTypeCodec codec) {
        return new BinaryDataTypeCodecImpl(codec);
    }

    class BinaryDataTypeCodecImpl implements BinaryDataTypeCodec {

        private final DataTypeCodec codec;

        public BinaryDataTypeCodecImpl(DataTypeCodec codec) {
            this.codec = codec;
        }

        @Override
        public Class<?> getType() {
            return codec.getType();
        }

        @Override
        public Object decode(SerializationContext context, OpcUaBinaryStreamDecoder decoder) throws UaSerializationException {
            return codec.decode(context, decoder);
        }

        @Override
        public void encode(SerializationContext context, OpcUaBinaryStreamEncoder encoder, Object value) throws UaSerializationException {
            codec.encode(context, encoder, value);
        }

    }

}
