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

public interface DataTypeCodec<T, R, W> {

    /**
     * @return the {@link Class} of the DataType this codec encodes.
     */
    Class<T> getType();

    /**
     * Decode a {@link T} using the provided reader {@link R}.
     *
     * @param context the {@link SerializationContext}.
     * @param reader  the reader {@link R} to decode from.
     * @return a decoded {@link T}.
     */
    T decode(SerializationContext context, R reader) throws UaSerializationException;

    /**
     * Encode a {@link T} using the provided writer {@link W}.
     *
     * @param context the {@link SerializationContext}.
     * @param t       the {@link T} to encode.
     * @param writer  the writer {@link W} to encode to.
     */
    void encode(SerializationContext context, T t, W writer) throws UaSerializationException;

}
