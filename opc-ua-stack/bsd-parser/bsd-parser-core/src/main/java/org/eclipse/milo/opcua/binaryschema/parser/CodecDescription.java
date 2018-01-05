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

package org.eclipse.milo.opcua.binaryschema.parser;

import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;

public class CodecDescription {

    private final OpcUaBinaryDataTypeCodec<?> codec;
    private final String description;

    public CodecDescription(OpcUaBinaryDataTypeCodec<?> codec, String description) {
        this.codec = codec;
        this.description = description;
    }

    public OpcUaBinaryDataTypeCodec<?> getCodec() {
        return codec;
    }

    public String getDescription() {
        return description;
    }

}
