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

public enum PubSubDiagnosticsCounterClassification implements UaEnumeration {
    Information(0),

    Error(1);

    private final int value;

    PubSubDiagnosticsCounterClassification(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static PubSubDiagnosticsCounterClassification from(int value) {
        switch (value) {
            case 0:
                return Information;
            case 1:
                return Error;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=19730");
    }

    public static class Codec extends GenericDataTypeCodec<PubSubDiagnosticsCounterClassification> {
        @Override
        public Class<PubSubDiagnosticsCounterClassification> getType() {
            return PubSubDiagnosticsCounterClassification.class;
        }

        @Override
        public PubSubDiagnosticsCounterClassification decode(SerializationContext context,
                                                             UaDecoder decoder) {
            return decoder.readEnum(null, PubSubDiagnosticsCounterClassification.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           PubSubDiagnosticsCounterClassification value) {
            encoder.writeEnum(null, value);
        }
    }
}
