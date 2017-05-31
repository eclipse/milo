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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
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

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ClientHandle", _clientHandle)
            .add("Value", _value)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<MonitoredItemNotification> {
        @Override
        public MonitoredItemNotification decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            UInteger _clientHandle = reader.readUInt32();
            DataValue _value = reader.readDataValue();

            return new MonitoredItemNotification(_clientHandle, _value);
        }

        @Override
        public void encode(SerializationContext context, MonitoredItemNotification encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32(encodable._clientHandle);
            writer.writeDataValue(encodable._value);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<MonitoredItemNotification> {
        @Override
        public MonitoredItemNotification decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            UInteger _clientHandle = reader.readUInt32("ClientHandle");
            DataValue _value = reader.readDataValue("Value");

            return new MonitoredItemNotification(_clientHandle, _value);
        }

        @Override
        public void encode(SerializationContext context, MonitoredItemNotification encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeUInt32("ClientHandle", encodable._clientHandle);
            writer.writeDataValue("Value", encodable._value);
        }
    }

}
