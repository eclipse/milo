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

import javax.annotation.Nullable;

import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEnumeration;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

public enum ExceptionDeviationFormat implements UaEnumeration {
    AbsoluteValue(0),

    PercentOfValue(1),

    PercentOfRange(2),

    PercentOfEURange(3),

    Unknown(4);

    private final int value;

    ExceptionDeviationFormat(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static ExceptionDeviationFormat from(int value) {
        switch (value) {
            case 0:
                return AbsoluteValue;
            case 1:
                return PercentOfValue;
            case 2:
                return PercentOfRange;
            case 3:
                return PercentOfEURange;
            case 4:
                return Unknown;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=890");
    }

    public static class Codec extends GenericDataTypeCodec<ExceptionDeviationFormat> {
        @Override
        public Class<ExceptionDeviationFormat> getType() {
            return ExceptionDeviationFormat.class;
        }

        @Override
        public ExceptionDeviationFormat decode(SerializationContext context, UaDecoder decoder) {
            return ExceptionDeviationFormat.from(decoder.readInt32(null));
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           ExceptionDeviationFormat value) {
            encoder.writeInt32(null, value.getValue());
        }
    }
}
