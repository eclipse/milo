/*
 * Copyright (c) 2021 the Eclipse Milo Authors
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

public enum OverrideValueHandling implements UaEnumeration {
    Disabled(0),

    LastUsableValue(1),

    OverrideValue(2);

    private final int value;

    OverrideValueHandling(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static OverrideValueHandling from(int value) {
        switch (value) {
            case 0:
                return Disabled;
            case 1:
                return LastUsableValue;
            case 2:
                return OverrideValue;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15874");
    }

    public static class Codec extends GenericDataTypeCodec<OverrideValueHandling> {
        @Override
        public Class<OverrideValueHandling> getType() {
            return OverrideValueHandling.class;
        }

        @Override
        public OverrideValueHandling decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, OverrideValueHandling.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           OverrideValueHandling value) {
            encoder.writeEnum(null, value);
        }
    }
}
