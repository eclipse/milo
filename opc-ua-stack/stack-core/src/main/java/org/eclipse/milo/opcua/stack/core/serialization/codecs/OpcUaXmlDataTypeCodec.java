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

package org.eclipse.milo.opcua.stack.core.serialization.codecs;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;

public interface OpcUaXmlDataTypeCodec<T> extends
    DataTypeCodec<T, OpcUaXmlStreamDecoder, OpcUaXmlStreamEncoder> {

    /**
     * Decode a {@link T} using the provided {@link OpcUaXmlStreamDecoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param reader  the {@link OpcUaXmlStreamDecoder} to decode from.
     * @return a decoded {@link T}.
     */
    T decode(SerializationContext context, OpcUaXmlStreamDecoder reader) throws UaSerializationException;

    /**
     * Encode a {@link T} using the provided {@link OpcUaXmlStreamEncoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param value   the value {@link T} to encode.
     * @param writer  the {@link OpcUaXmlStreamEncoder} to encode to.
     */
    void encode(SerializationContext context, T value, OpcUaXmlStreamEncoder writer) throws UaSerializationException;

}
