/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class MonitoredItemNotification extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=806");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=808");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=807");

    private final UInteger clientHandle;

    private final DataValue value;

    public MonitoredItemNotification(UInteger clientHandle, DataValue value) {
        this.clientHandle = clientHandle;
        this.value = value;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public UInteger getClientHandle() {
        return clientHandle;
    }

    public DataValue getValue() {
        return value;
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoredItemNotification> {
        @Override
        public Class<MonitoredItemNotification> getType() {
            return MonitoredItemNotification.class;
        }

        @Override
        public MonitoredItemNotification decode(SerializationContext context, UaDecoder decoder) {
            UInteger clientHandle = decoder.readUInt32("ClientHandle");
            DataValue value = decoder.readDataValue("Value");
            return new MonitoredItemNotification(clientHandle, value);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoredItemNotification value) {
            encoder.writeUInt32("ClientHandle", value.getClientHandle());
            encoder.writeDataValue("Value", value.getValue());
        }
    }
}
