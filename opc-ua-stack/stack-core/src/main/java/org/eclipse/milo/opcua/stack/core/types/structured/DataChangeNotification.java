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

import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.serialization.DelegateRegistry;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
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

    public MonitoredItemNotification[] getMonitoredItems() { return _monitoredItems; }

    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(DataChangeNotification dataChangeNotification, UaEncoder encoder) {
        encoder.encodeArray("MonitoredItems", dataChangeNotification._monitoredItems, encoder::encodeSerializable);
        encoder.encodeArray("DiagnosticInfos", dataChangeNotification._diagnosticInfos, encoder::encodeDiagnosticInfo);
    }

    public static DataChangeNotification decode(UaDecoder decoder) {
        MonitoredItemNotification[] _monitoredItems = decoder.decodeArray("MonitoredItems", decoder::decodeSerializable, MonitoredItemNotification.class);
        DiagnosticInfo[] _diagnosticInfos = decoder.decodeArray("DiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);

        return new DataChangeNotification(_monitoredItems, _diagnosticInfos);
    }

    static {
        DelegateRegistry.registerEncoder(DataChangeNotification::encode, DataChangeNotification.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(DataChangeNotification::decode, DataChangeNotification.class, BinaryEncodingId, XmlEncodingId);
    }

}
