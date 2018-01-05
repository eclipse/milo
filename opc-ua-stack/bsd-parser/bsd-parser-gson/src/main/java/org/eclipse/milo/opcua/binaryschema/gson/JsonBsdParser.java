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

package org.eclipse.milo.opcua.binaryschema.gson;

import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.opcfoundation.opcua.binaryschema.EnumeratedType;
import org.opcfoundation.opcua.binaryschema.StructuredType;

public final class JsonBsdParser extends BsdParser {

    @Override
    protected OpcUaBinaryDataTypeCodec<?> getEnumCodec(EnumeratedType enumeratedType) {
        return new JsonEnumCodec();
    }

    @Override
    protected OpcUaBinaryDataTypeCodec<?> getStructCodec(StructuredType structuredType) {
        return new JsonStructureCodec(structuredType);
    }

}
