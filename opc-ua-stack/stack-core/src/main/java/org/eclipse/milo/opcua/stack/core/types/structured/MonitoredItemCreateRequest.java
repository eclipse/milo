/*
 * Copyright (c) 2017 Kevin Herron
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v1.0 which accompany this distribution.
 *
 * The Eclipse Public License is available at
 *   http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 *   http://www.eclipse.org/org/documents/edl-v10.html.
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;

public class MonitoredItemCreateRequest implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoredItemCreateRequest;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemCreateRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoredItemCreateRequest_Encoding_DefaultXml;

    protected final ReadValueId itemToMonitor;
    protected final MonitoringMode monitoringMode;
    protected final MonitoringParameters requestedParameters;

    public MonitoredItemCreateRequest() {
        this.itemToMonitor = null;
        this.monitoringMode = null;
        this.requestedParameters = null;
    }

    public MonitoredItemCreateRequest(ReadValueId itemToMonitor, MonitoringMode monitoringMode, MonitoringParameters requestedParameters) {
        this.itemToMonitor = itemToMonitor;
        this.monitoringMode = monitoringMode;
        this.requestedParameters = requestedParameters;
    }

    public ReadValueId getItemToMonitor() { return itemToMonitor; }

    public MonitoringMode getMonitoringMode() { return monitoringMode; }

    public MonitoringParameters getRequestedParameters() { return requestedParameters; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ItemToMonitor", itemToMonitor)
            .add("MonitoringMode", monitoringMode)
            .add("RequestedParameters", requestedParameters)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<MonitoredItemCreateRequest> {

        @Override
        public Class<MonitoredItemCreateRequest> getType() {
            return MonitoredItemCreateRequest.class;
        }

        @Override
        public MonitoredItemCreateRequest decode(UaDecoder decoder) throws UaSerializationException {
            ReadValueId itemToMonitor = (ReadValueId) decoder.readBuiltinStruct("ItemToMonitor", ReadValueId.class);
            MonitoringMode monitoringMode = MonitoringMode.from(decoder.readInt32("MonitoringMode"));
            MonitoringParameters requestedParameters = (MonitoringParameters) decoder.readBuiltinStruct("RequestedParameters", MonitoringParameters.class);

            return new MonitoredItemCreateRequest(itemToMonitor, monitoringMode, requestedParameters);
        }

        @Override
        public void encode(MonitoredItemCreateRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ItemToMonitor", value.itemToMonitor, ReadValueId.class);
            encoder.writeInt32("MonitoringMode", value.monitoringMode != null ? value.monitoringMode.getValue() : 0);
            encoder.writeBuiltinStruct("RequestedParameters", value.requestedParameters, MonitoringParameters.class);
        }
    }

}
