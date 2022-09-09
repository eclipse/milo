/*
 * Copyright (c) 2021 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.serialization.codecs;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;

public interface OpcUaXmlDataTypeCodec extends DataTypeCodec {

    @Override
    default Object decode(SerializationContext context, UaDecoder decoder) throws UaSerializationException {
        return decode(context, (OpcUaXmlStreamDecoder) decoder);
    }

    @Override
    default void encode(SerializationContext context, UaEncoder encoder, Object value) throws UaSerializationException {
        encode(context, (OpcUaXmlStreamEncoder) encoder, value);
    }

    /**
     * Decode a {@link T} using the provided {@link OpcUaXmlStreamDecoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param decoder the {@link OpcUaXmlStreamDecoder} to decode from.
     * @return a decoded {@link T}.
     */
    Object decode(SerializationContext context, OpcUaXmlStreamDecoder decoder) throws UaSerializationException;

    /**
     * Encode a {@link T} using the provided {@link OpcUaXmlStreamEncoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param encoder the {@link OpcUaXmlStreamEncoder} to encode to.
     * @param value   the value {@link T} to encode.
     */
    void encode(SerializationContext context, OpcUaXmlStreamEncoder encoder, Object value) throws UaSerializationException;

}
