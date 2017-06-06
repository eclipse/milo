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

package org.eclipse.milo.opcua.stack.core.serialization.codec;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;

public interface OpcBinaryDataTypeCodec<T> extends DataTypeCodec<T, OpcBinaryStreamReader, OpcBinaryStreamWriter> {

    /**
     * Decode a {@link T} using the provided {@link OpcBinaryStreamReader}.
     *
     * @param context the {@link SerializationContext}.
     * @param reader  the {@link OpcBinaryStreamReader} to decode from.
     * @return a decoded {@link T}.
     */
    T decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException;

    /**
     * Encode a {@link T} using the provided {@link OpcBinaryStreamWriter}.
     *
     * @param context   the {@link SerializationContext}.
     * @param encodable the {@link T} to encode.
     * @param writer    the {@link OpcBinaryStreamWriter} to encode to.
     */
    void encode(SerializationContext context, T encodable, OpcBinaryStreamWriter writer) throws UaSerializationException;

}
