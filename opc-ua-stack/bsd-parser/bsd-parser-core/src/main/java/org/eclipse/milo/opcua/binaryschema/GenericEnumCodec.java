/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
