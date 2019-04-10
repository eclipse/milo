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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;

public abstract class BuiltinDataTypeCodec<T extends UaStructure> extends GenericDataTypeCodec<T> {

    @Override
    public final T decode(SerializationContext context, UaDecoder decoder) throws UaSerializationException {
        return decode(decoder);
    }

    @Override
    public final void encode(SerializationContext context, UaEncoder encoder, T value) throws UaSerializationException {
        encode(value, encoder);
    }

    protected abstract T decode(UaDecoder decoder) throws UaSerializationException;

    protected abstract void encode(T t, UaEncoder encoder) throws UaSerializationException;

}
