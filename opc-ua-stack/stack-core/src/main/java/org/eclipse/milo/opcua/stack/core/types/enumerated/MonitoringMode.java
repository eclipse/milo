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

public enum MonitoringMode implements UaEnumeration {
    Disabled(0),

    Sampling(1),

    Reporting(2);

    private final int value;

    MonitoringMode(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static MonitoringMode from(int value) {
        switch (value) {
            case 0:
                return Disabled;
            case 1:
                return Sampling;
            case 2:
                return Reporting;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=716");
    }

    public static class Codec extends GenericDataTypeCodec<MonitoringMode> {
        @Override
        public Class<MonitoringMode> getType() {
            return MonitoringMode.class;
        }

        @Override
        public MonitoringMode decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, MonitoringMode.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, MonitoringMode value) {
            encoder.writeEnum(null, value);
        }
    }
}
