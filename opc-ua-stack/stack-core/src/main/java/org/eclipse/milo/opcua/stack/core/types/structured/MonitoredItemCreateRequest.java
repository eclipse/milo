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
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class MonitoredItemCreateRequest extends Structure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=743");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=745");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=744");

    private final ReadValueId itemToMonitor;

    private final MonitoringMode monitoringMode;

    private final MonitoringParameters requestedParameters;

    public MonitoredItemCreateRequest(ReadValueId itemToMonitor, MonitoringMode monitoringMode,
                                      MonitoringParameters requestedParameters) {
        this.itemToMonitor = itemToMonitor;
        this.monitoringMode = monitoringMode;
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

    public ReadValueId getItemToMonitor() {
        return itemToMonitor;
    }

    public MonitoringMode getMonitoringMode() {
        return monitoringMode;
    }

    public MonitoringParameters getRequestedParameters() {
        return requestedParameters;
    }

    public static final class Codec extends GenericDataTypeCodec<MonitoredItemCreateRequest> {
        @Override
        public Class<MonitoredItemCreateRequest> getType() {
            return MonitoredItemCreateRequest.class;
        }

        @Override
        public MonitoredItemCreateRequest decode(SerializationContext context, UaDecoder decoder) {
            ReadValueId itemToMonitor = (ReadValueId) decoder.readStruct("ItemToMonitor", ReadValueId.TYPE_ID);
            MonitoringMode monitoringMode = decoder.readEnum("MonitoringMode", MonitoringMode.class);
            MonitoringParameters requestedParameters = (MonitoringParameters) decoder.readStruct("RequestedParameters", MonitoringParameters.TYPE_ID);
            return new MonitoredItemCreateRequest(itemToMonitor, monitoringMode, requestedParameters);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           MonitoredItemCreateRequest value) {
            encoder.writeStruct("ItemToMonitor", value.getItemToMonitor(), ReadValueId.TYPE_ID);
            encoder.writeEnum("MonitoringMode", value.getMonitoringMode());
            encoder.writeStruct("RequestedParameters", value.getRequestedParameters(), MonitoringParameters.TYPE_ID);
        }
    }
}
