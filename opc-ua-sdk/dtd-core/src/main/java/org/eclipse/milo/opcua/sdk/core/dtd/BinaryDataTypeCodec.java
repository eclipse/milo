/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core.dtd;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.binary.OpcUaBinaryDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.binary.OpcUaBinaryEncoder;

public interface BinaryDataTypeCodec extends DataTypeCodec {

    @Override
    default Object decode(SerializationContext context, UaDecoder decoder) throws UaSerializationException {
        return decode(context, (OpcUaBinaryDecoder) decoder);
    }

    @Override
    default void encode(SerializationContext context, UaEncoder encoder, Object value) throws UaSerializationException {
        encode(context, (OpcUaBinaryEncoder) encoder, value);
    }

    /**
     * Decode an Object using the provided {@link OpcUaBinaryDecoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param decoder the {@link OpcUaBinaryDecoder} to decode from.
     * @return a decoded Object.
     */
    Object decode(SerializationContext context, OpcUaBinaryDecoder decoder) throws UaSerializationException;

    /**
     * Encode an Object using the provided {@link OpcUaBinaryEncoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param encoder the {@link OpcUaBinaryEncoder} to encode to.
     * @param value   the Object to encode.
     */
    void encode(SerializationContext context, OpcUaBinaryEncoder encoder, Object value) throws UaSerializationException;

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
        public Object decode(SerializationContext context, OpcUaBinaryDecoder decoder) throws UaSerializationException {
            return codec.decode(context, decoder);
        }

        @Override
        public void encode(SerializationContext context, OpcUaBinaryEncoder encoder, Object value) throws UaSerializationException {
            codec.encode(context, encoder, value);
        }

    }

}
