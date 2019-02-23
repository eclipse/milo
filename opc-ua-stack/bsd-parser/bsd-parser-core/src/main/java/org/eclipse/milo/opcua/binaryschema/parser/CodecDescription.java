/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
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
