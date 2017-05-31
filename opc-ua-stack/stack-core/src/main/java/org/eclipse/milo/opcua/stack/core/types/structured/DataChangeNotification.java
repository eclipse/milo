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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("DataChangeNotification")
public class DataChangeNotification extends NotificationData {

    public static final NodeId TypeId = Identifiers.DataChangeNotification;
    public static final NodeId BinaryEncodingId = Identifiers.DataChangeNotification_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DataChangeNotification_Encoding_DefaultXml;

    protected final MonitoredItemNotification[] _monitoredItems;
    protected final DiagnosticInfo[] _diagnosticInfos;

    public DataChangeNotification() {
        super();
        this._monitoredItems = null;
        this._diagnosticInfos = null;
    }

    public DataChangeNotification(MonitoredItemNotification[] _monitoredItems, DiagnosticInfo[] _diagnosticInfos) {
        super();
        this._monitoredItems = _monitoredItems;
        this._diagnosticInfos = _diagnosticInfos;
    }

    @Nullable
    public MonitoredItemNotification[] getMonitoredItems() { return _monitoredItems; }

    @Nullable
    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("MonitoredItems", _monitoredItems)
            .add("DiagnosticInfos", _diagnosticInfos)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DataChangeNotification> {
        @Override
        public DataChangeNotification decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            MonitoredItemNotification[] _monitoredItems =
                reader.readArray(
                    () -> (MonitoredItemNotification) context.decode(
                        MonitoredItemNotification.BinaryEncodingId, reader),
                    MonitoredItemNotification.class
                );
            DiagnosticInfo[] _diagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new DataChangeNotification(_monitoredItems, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, DataChangeNotification encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                encodable._monitoredItems,
                e -> context.encode(MonitoredItemNotification.BinaryEncodingId, e, writer)
            );
            writer.writeArray(encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DataChangeNotification> {
        @Override
        public DataChangeNotification decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            MonitoredItemNotification[] _monitoredItems =
                reader.readArray(
                    "MonitoredItems",
                    f -> (MonitoredItemNotification) context.decode(
                        MonitoredItemNotification.XmlEncodingId, reader),
                    MonitoredItemNotification.class
                );
            DiagnosticInfo[] _diagnosticInfos = reader.readArray("DiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);

            return new DataChangeNotification(_monitoredItems, _diagnosticInfos);
        }

        @Override
        public void encode(SerializationContext context, DataChangeNotification encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray(
                "MonitoredItems",
                encodable._monitoredItems,
                (f, e) -> context.encode(MonitoredItemNotification.XmlEncodingId, e, writer)
            );
            writer.writeArray("DiagnosticInfos", encodable._diagnosticInfos, writer::writeDiagnosticInfo);
        }
    }

}
