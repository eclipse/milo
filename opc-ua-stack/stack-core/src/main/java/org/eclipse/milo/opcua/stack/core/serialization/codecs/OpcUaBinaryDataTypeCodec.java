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

public interface OpcUaBinaryDataTypeCodec<T> extends
    DataTypeCodec<T, OpcUaBinaryStreamDecoder, OpcUaBinaryStreamEncoder> {

    /**
     * Decode a {@link T} using the provided {@link OpcUaBinaryStreamDecoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param reader  the {@link OpcUaBinaryStreamDecoder} to decode from.
     * @return a decoded {@link T}.
     */
    T decode(SerializationContext context, OpcUaBinaryStreamDecoder reader) throws UaSerializationException;

    /**
     * Encode a {@link T} using the provided {@link OpcUaBinaryStreamEncoder}.
     *
     * @param context the {@link SerializationContext}.
     * @param value   the {@link T} to encode.
     * @param writer  the {@link OpcUaBinaryStreamEncoder} to encode to.
     */
    void encode(SerializationContext context, T value, OpcUaBinaryStreamEncoder writer) throws UaSerializationException;

}
