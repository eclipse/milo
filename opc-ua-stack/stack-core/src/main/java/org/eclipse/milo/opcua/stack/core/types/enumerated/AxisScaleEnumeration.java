/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.enumerated;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.jetbrains.annotations.Nullable;

public enum AxisScaleEnumeration implements UaEnumeration {
    Linear(0),

    Log(1),

    Ln(2);

    private final int value;

    AxisScaleEnumeration(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static AxisScaleEnumeration from(int value) {
        switch (value) {
            case 0:
                return Linear;
            case 1:
                return Log;
            case 2:
                return Ln;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=12077");
    }

    public static class Codec extends GenericDataTypeCodec<AxisScaleEnumeration> {
        @Override
        public Class<AxisScaleEnumeration> getType() {
            return AxisScaleEnumeration.class;
        }

        @Override
        public AxisScaleEnumeration decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, AxisScaleEnumeration.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           AxisScaleEnumeration value) {
            encoder.writeEnum(null, value);
        }
    }
}
