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

public enum BrokerTransportQualityOfService implements UaEnumeration {
    NotSpecified(0),

    BestEffort(1),

    AtLeastOnce(2),

    AtMostOnce(3),

    ExactlyOnce(4);

    private final int value;

    BrokerTransportQualityOfService(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }

    @Nullable
    public static BrokerTransportQualityOfService from(int value) {
        switch (value) {
            case 0:
                return NotSpecified;
            case 1:
                return BestEffort;
            case 2:
                return AtLeastOnce;
            case 3:
                return AtMostOnce;
            case 4:
                return ExactlyOnce;
            default:
                return null;
        }
    }

    public static ExpandedNodeId getTypeId() {
        return ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15008");
    }

    public static class Codec extends GenericDataTypeCodec<BrokerTransportQualityOfService> {
        @Override
        public Class<BrokerTransportQualityOfService> getType() {
            return BrokerTransportQualityOfService.class;
        }

        @Override
        public BrokerTransportQualityOfService decode(SerializationContext context, UaDecoder decoder) {
            return decoder.readEnum(null, BrokerTransportQualityOfService.class);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           BrokerTransportQualityOfService value) {
            encoder.writeEnum(null, value);
        }
    }
}
