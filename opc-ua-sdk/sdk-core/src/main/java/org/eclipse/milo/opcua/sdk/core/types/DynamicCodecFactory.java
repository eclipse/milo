/*
 * Copyright (c) 2022 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.sdk.core.types;

import org.eclipse.milo.opcua.stack.core.encoding.DataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.structured.DataTypeDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.StructureDefinition;

public class DynamicCodecFactory {

    public static DataTypeCodec create(DataType dataType, DataTypeTree dataTypeTree) {
        DataTypeDefinition definition = dataType.getDataTypeDefinition();

        if (definition instanceof EnumDefinition) {
            return new DynamicOptionSetCodec(dataType);
        } else if (definition instanceof StructureDefinition) {
            return new DynamicStructCodec(dataType, dataTypeTree);
        } else {
            throw new RuntimeException("unknown DataTypeDefinition: " + definition);
        }
    }

}
