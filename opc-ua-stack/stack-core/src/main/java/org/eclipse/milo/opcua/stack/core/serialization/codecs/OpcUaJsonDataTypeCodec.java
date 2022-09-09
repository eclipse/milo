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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaJsonDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaJsonEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;

public interface OpcUaJsonDataTypeCodec<T> extends DataTypeCodec<T> {

    @Override
    default T decode(SerializationContext context, UaDecoder decoder) throws UaSerializationException {
        return decode(context, (OpcUaJsonDecoder) decoder);
    }

    @Override
    default void encode(SerializationContext context, UaEncoder encoder, T value) throws UaSerializationException {
        encode(context, (OpcUaJsonEncoder) encoder, value);
    }

    T decode(SerializationContext context, OpcUaJsonDecoder decoder) throws UaSerializationException;

    void encode(SerializationContext context, OpcUaJsonEncoder encoder, T value) throws UaSerializationException;

}
