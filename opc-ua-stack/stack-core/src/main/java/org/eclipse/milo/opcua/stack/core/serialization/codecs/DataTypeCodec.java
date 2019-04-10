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
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;

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
     * @param writer  the writer {@link W} to encode to.
     * @param value   the {@link T} to encode.
     */
    void encode(SerializationContext context, W writer, T value) throws UaSerializationException;

}
