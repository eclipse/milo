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

import java.util.HashMap;
import java.util.Map;

import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.LocalizedText;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumDefinition;
import org.eclipse.milo.opcua.stack.core.types.structured.EnumField;

public class DynamicEnumCodec extends GenericDataTypeCodec<DynamicEnum> {

    private final DataType dataType;
    private final Map<Long, EnumField> fields = new HashMap<>();

    public DynamicEnumCodec(DataType dataType) {
        this.dataType = dataType;

        EnumDefinition definition = (EnumDefinition) dataType.getDataTypeDefinition();

        for (EnumField field : definition.getFields()) {
            fields.put(field.getValue(), field);
        }
    }

    @Override
    public Class<DynamicEnum> getType() {
        return DynamicEnum.class;
    }

    @Override
    public DynamicEnum decodeType(SerializationContext context, UaDecoder decoder) throws UaSerializationException {
        int value = decoder.readInt32(null);

        EnumField field = fields.get((long) value);

        if (field == null) {
            return new DynamicEnum(dataType, null, -1, LocalizedText.NULL_VALUE, LocalizedText.NULL_VALUE);
        } else {
            return new DynamicEnum(dataType, field.getName(), value, field.getDisplayName(), field.getDescription());
        }
    }

    @Override
    public void encodeType(
        SerializationContext context,
        UaEncoder encoder,
        DynamicEnum value
    ) throws UaSerializationException {

        encoder.writeInt32(null, value.getValue());
    }

}
