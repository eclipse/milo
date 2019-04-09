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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaXmlStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;

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
     * @param writer  the {@link OpcUaXmlStreamEncoder} to encode to.
     * @param value   the value {@link T} to encode.
     */
    void encode(SerializationContext context, OpcUaXmlStreamEncoder writer, T value) throws UaSerializationException;

}
