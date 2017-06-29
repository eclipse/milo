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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class MonitoredItemModifyRequest implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoredItemModifyRequest;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemModifyRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoredItemModifyRequest_Encoding_DefaultXml;

    protected final UInteger monitoredItemId;
    protected final MonitoringParameters requestedParameters;

    public MonitoredItemModifyRequest() {
        this.monitoredItemId = null;
        this.requestedParameters = null;
    }

    public MonitoredItemModifyRequest(UInteger monitoredItemId, MonitoringParameters requestedParameters) {
        this.monitoredItemId = monitoredItemId;
        this.requestedParameters = requestedParameters;
    }

    public UInteger getMonitoredItemId() { return monitoredItemId; }

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
            .add("MonitoredItemId", monitoredItemId)
            .add("RequestedParameters", requestedParameters)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<MonitoredItemModifyRequest> {

        @Override
        public Class<MonitoredItemModifyRequest> getType() {
            return MonitoredItemModifyRequest.class;
        }

        @Override
        public MonitoredItemModifyRequest decode(UaDecoder decoder) throws UaSerializationException {
            UInteger monitoredItemId = decoder.readUInt32("MonitoredItemId");
            MonitoringParameters requestedParameters = (MonitoringParameters) decoder.readBuiltinStruct("RequestedParameters", MonitoringParameters.class);

            return new MonitoredItemModifyRequest(monitoredItemId, requestedParameters);
        }

        @Override
        public void encode(MonitoredItemModifyRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("MonitoredItemId", value.monitoredItemId);
            encoder.writeBuiltinStruct("RequestedParameters", value.requestedParameters, MonitoringParameters.class);
        }
    }

}
