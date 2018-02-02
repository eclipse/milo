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

package org.eclipse.milo.opcua.binaryschema;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaBinaryStreamEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.SerializationContext;
import org.opcfoundation.opcua.binaryschema.EnumeratedType;

public class GenericEnumCodec implements OpcUaBinaryDataTypeCodec<Number> {

    private final EnumeratedType enumeratedType;

    public GenericEnumCodec(EnumeratedType enumeratedType) {
        this.enumeratedType = enumeratedType;
    }

    @Override
    public Class<Number> getType() {
        return Number.class;
    }

    @Override
    public void encode(
        SerializationContext context,
        Number value,
        OpcUaBinaryStreamEncoder encoder) throws UaSerializationException {

        encoder.writeInt32(value.intValue());
    }

    @Override
    public Number decode(
        SerializationContext context,
        OpcUaBinaryStreamDecoder decoder) throws UaSerializationException {

        return decoder.readInt32();
    }

}
