/*
 * Copyright (c) 2024 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.sdk.core.types.json;

import org.eclipse.milo.opcua.sdk.core.typetree.DataType;
import org.eclipse.milo.opcua.sdk.core.typetree.DataTypeTree;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.encoding.EncodingContext;
import org.eclipse.milo.opcua.stack.core.encoding.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.encoding.UaDecoder;
import org.eclipse.milo.opcua.stack.core.encoding.UaEncoder;

public class JsonStructCodec extends GenericDataTypeCodec<JsonStruct> {

    private final DataType dataType;
    private final DataTypeTree dataTypeTree;

    public JsonStructCodec(DataType dataType, DataTypeTree dataTypeTree) {
        this.dataType = dataType;
        this.dataTypeTree = dataTypeTree;
    }

    @Override
    public Class<JsonStruct> getType() {
        return JsonStruct.class;
    }

    @Override
    public JsonStruct decodeType(EncodingContext context, UaDecoder decoder) throws UaSerializationException {
        return null;
    }

    @Override
    public void encodeType(EncodingContext context, UaEncoder encoder, JsonStruct value) throws UaSerializationException {

    }
    
}
