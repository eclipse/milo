/*
 * Copyright (c) 2017 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;

public abstract class BuiltinDataTypeCodec<T extends UaStructure> extends GenericDataTypeCodec<T> {

    @Override
    public final T decode(SerializationContext context, UaDecoder decoder) throws UaSerializationException {
        return decode(decoder);
    }

    @Override
    public final void encode(SerializationContext context, T t, UaEncoder encoder) throws UaSerializationException {
        encode(t, encoder);
    }

    protected abstract T decode(UaDecoder decoder) throws UaSerializationException;

    protected abstract void encode(T t, UaEncoder encoder) throws UaSerializationException;

}
