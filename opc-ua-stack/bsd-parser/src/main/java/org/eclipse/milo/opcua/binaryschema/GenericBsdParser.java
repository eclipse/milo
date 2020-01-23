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

import org.eclipse.milo.opcua.binaryschema.parser.BsdParser;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.OpcUaBinaryDataTypeCodec;
import org.opcfoundation.opcua.binaryschema.EnumeratedType;
import org.opcfoundation.opcua.binaryschema.StructuredType;

public class GenericBsdParser extends BsdParser {

    @Override
    protected OpcUaBinaryDataTypeCodec<?> getEnumCodec(EnumeratedType enumeratedType) {
        return new GenericEnumCodec(enumeratedType);
    }

    @Override
    protected OpcUaBinaryDataTypeCodec<?> getStructCodec(StructuredType structuredType) {
        return new GenericStructCodec(structuredType);
    }

}
