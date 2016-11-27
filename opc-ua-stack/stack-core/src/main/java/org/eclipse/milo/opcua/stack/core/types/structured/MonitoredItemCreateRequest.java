/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.MonitoringMode;

@UaDataType("MonitoredItemCreateRequest")
public class MonitoredItemCreateRequest implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoredItemCreateRequest;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemCreateRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoredItemCreateRequest_Encoding_DefaultXml;

    protected final ReadValueId _itemToMonitor;
    protected final MonitoringMode _monitoringMode;
    protected final MonitoringParameters _requestedParameters;

    public MonitoredItemCreateRequest() {
        this._itemToMonitor = null;
        this._monitoringMode = null;
        this._requestedParameters = null;
    }

    public MonitoredItemCreateRequest(ReadValueId _itemToMonitor, MonitoringMode _monitoringMode, MonitoringParameters _requestedParameters) {
        this._itemToMonitor = _itemToMonitor;
        this._monitoringMode = _monitoringMode;
        this._requestedParameters = _requestedParameters;
    }

    public ReadValueId getItemToMonitor() { return _itemToMonitor; }

    public MonitoringMode getMonitoringMode() { return _monitoringMode; }

    public MonitoringParameters getRequestedParameters() { return _requestedParameters; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ItemToMonitor", _itemToMonitor)
            .add("MonitoringMode", _monitoringMode)
            .add("RequestedParameters", _requestedParameters)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<MonitoredItemCreateRequest> {
        @Override
        public MonitoredItemCreateRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ReadValueId _itemToMonitor = (ReadValueId) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ReadValueId", reader);
            MonitoringMode _monitoringMode = MonitoringMode.from(reader.readInt32());
            MonitoringParameters _requestedParameters = (MonitoringParameters) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "MonitoringParameters", reader);

            return new MonitoredItemCreateRequest(_itemToMonitor, _monitoringMode, _requestedParameters);
        }

        @Override
        public void encode(SerializationContext context, MonitoredItemCreateRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ReadValueId", encodable._itemToMonitor, writer);
            writer.writeInt32(encodable._monitoringMode != null ? encodable._monitoringMode.getValue() : 0);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "MonitoringParameters", encodable._requestedParameters, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<MonitoredItemCreateRequest> {
        @Override
        public MonitoredItemCreateRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ReadValueId _itemToMonitor = (ReadValueId) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ReadValueId", reader);
            MonitoringMode _monitoringMode = MonitoringMode.from(reader.readInt32("MonitoringMode"));
            MonitoringParameters _requestedParameters = (MonitoringParameters) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "MonitoringParameters", reader);

            return new MonitoredItemCreateRequest(_itemToMonitor, _monitoringMode, _requestedParameters);
        }

        @Override
        public void encode(SerializationContext context, MonitoredItemCreateRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ReadValueId", encodable._itemToMonitor, writer);
            writer.writeInt32("MonitoringMode", encodable._monitoringMode != null ? encodable._monitoringMode.getValue() : 0);
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "MonitoringParameters", encodable._requestedParameters, writer);
        }
    }

}
