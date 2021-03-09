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
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class MonitoredItemModifyRequest extends Structure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=755");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=757");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=756");

    private final UInteger monitoredItemId;

    private final MonitoringParameters requestedParameters;

    public MonitoredItemModifyRequest(UInteger monitoredItemId,
                                      MonitoringParameters requestedParameters) {
        this.monitoredItemId = monitoredItemId;
        this.requestedParameters = requestedParameters;
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

    public UInteger getMonitoredItemId() {
        return monitoredItemId;
    }

    public MonitoringParameters getRequestedParameters() {
        return requestedParameters;
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoredItemModifyRequest> {
        @Override
        public Class<MonitoredItemModifyRequest> getType() {
            return MonitoredItemModifyRequest.class;
        }

        @Override
        public MonitoredItemModifyRequest decode(SerializationContext context, UaDecoder decoder) {
            UInteger monitoredItemId = decoder.readUInt32("MonitoredItemId");
            MonitoringParameters requestedParameters = (MonitoringParameters) decoder.readStruct("RequestedParameters", MonitoringParameters.TYPE_ID);
            return new MonitoredItemModifyRequest(monitoredItemId, requestedParameters);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoredItemModifyRequest value) {
            encoder.writeUInt32("MonitoredItemId", value.getMonitoredItemId());
            encoder.writeStruct("RequestedParameters", value.getRequestedParameters(), MonitoringParameters.TYPE_ID);
        }
    }
}
