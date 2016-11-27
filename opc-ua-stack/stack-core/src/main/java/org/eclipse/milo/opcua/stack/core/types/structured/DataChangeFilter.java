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
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger;

@UaDataType("DataChangeFilter")
public class DataChangeFilter extends MonitoringFilter {

    public static final NodeId TypeId = Identifiers.DataChangeFilter;
    public static final NodeId BinaryEncodingId = Identifiers.DataChangeFilter_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DataChangeFilter_Encoding_DefaultXml;

    protected final DataChangeTrigger _trigger;
    protected final UInteger _deadbandType;
    protected final Double _deadbandValue;

    public DataChangeFilter() {
        super();
        this._trigger = null;
        this._deadbandType = null;
        this._deadbandValue = null;
    }

    public DataChangeFilter(DataChangeTrigger _trigger, UInteger _deadbandType, Double _deadbandValue) {
        super();
        this._trigger = _trigger;
        this._deadbandType = _deadbandType;
        this._deadbandValue = _deadbandValue;
    }

    public DataChangeTrigger getTrigger() { return _trigger; }

    public UInteger getDeadbandType() { return _deadbandType; }

    public Double getDeadbandValue() { return _deadbandValue; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Trigger", _trigger)
            .add("DeadbandType", _deadbandType)
            .add("DeadbandValue", _deadbandValue)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DataChangeFilter> {
        @Override
        public DataChangeFilter decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            DataChangeTrigger _trigger = DataChangeTrigger.from(reader.readInt32());
            UInteger _deadbandType = reader.readUInt32();
            Double _deadbandValue = reader.readDouble();

            return new DataChangeFilter(_trigger, _deadbandType, _deadbandValue);
        }

        @Override
        public void encode(SerializationContext context, DataChangeFilter encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeInt32(encodable._trigger != null ? encodable._trigger.getValue() : 0);
            writer.writeUInt32(encodable._deadbandType);
            writer.writeDouble(encodable._deadbandValue);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DataChangeFilter> {
        @Override
        public DataChangeFilter decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            DataChangeTrigger _trigger = DataChangeTrigger.from(reader.readInt32("Trigger"));
            UInteger _deadbandType = reader.readUInt32("DeadbandType");
            Double _deadbandValue = reader.readDouble("DeadbandValue");

            return new DataChangeFilter(_trigger, _deadbandType, _deadbandValue);
        }

        @Override
        public void encode(SerializationContext context, DataChangeFilter encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeInt32("Trigger", encodable._trigger != null ? encodable._trigger.getValue() : 0);
            writer.writeUInt32("DeadbandType", encodable._deadbandType);
            writer.writeDouble("DeadbandValue", encodable._deadbandValue);
        }
    }

}
