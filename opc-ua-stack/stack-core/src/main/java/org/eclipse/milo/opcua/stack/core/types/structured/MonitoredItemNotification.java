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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("MonitoredItemNotification")
public class MonitoredItemNotification implements UaStructure {

    public static final NodeId TypeId = Identifiers.MonitoredItemNotification;
    public static final NodeId BinaryEncodingId = Identifiers.MonitoredItemNotification_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.MonitoredItemNotification_Encoding_DefaultXml;

    protected final UInteger _clientHandle;
    protected final DataValue _value;

    public MonitoredItemNotification() {
        this._clientHandle = null;
        this._value = null;
    }

    public MonitoredItemNotification(UInteger _clientHandle, DataValue _value) {
        this._clientHandle = _clientHandle;
        this._value = _value;
    }

    public UInteger getClientHandle() { return _clientHandle; }

    public DataValue getValue() { return _value; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(MonitoredItemNotification monitoredItemNotification, UaEncoder encoder) {
        encoder.encodeUInt32("ClientHandle", monitoredItemNotification._clientHandle);
        encoder.encodeDataValue("Value", monitoredItemNotification._value);
    }

    public static MonitoredItemNotification decode(UaDecoder decoder) {
        UInteger _clientHandle = decoder.decodeUInt32("ClientHandle");
        DataValue _value = decoder.decodeDataValue("Value");

        return new MonitoredItemNotification(_clientHandle, _value);
    }

    static {
        DelegateRegistry.registerEncoder(MonitoredItemNotification::encode, MonitoredItemNotification.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(MonitoredItemNotification::decode, MonitoredItemNotification.class, BinaryEncodingId, XmlEncodingId);
    }

}
