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
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;
import org.eclipse.milo.opcua.stack.core.types.enumerated.DataChangeTrigger;

public class DataChangeFilter extends MonitoringFilter {

    public static final NodeId TypeId = Identifiers.DataChangeFilter;
    public static final NodeId BinaryEncodingId = Identifiers.DataChangeFilter_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DataChangeFilter_Encoding_DefaultXml;

    protected final DataChangeTrigger trigger;
    protected final UInteger deadbandType;
    protected final Double deadbandValue;

    public DataChangeFilter() {
        super();
        this.trigger = null;
        this.deadbandType = null;
        this.deadbandValue = null;
    }

    public DataChangeFilter(DataChangeTrigger trigger, UInteger deadbandType, Double deadbandValue) {
        super();
        this.trigger = trigger;
        this.deadbandType = deadbandType;
        this.deadbandValue = deadbandValue;
    }

    public DataChangeTrigger getTrigger() { return trigger; }

    public UInteger getDeadbandType() { return deadbandType; }

    public Double getDeadbandValue() { return deadbandValue; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("Trigger", trigger)
            .add("DeadbandType", deadbandType)
            .add("DeadbandValue", deadbandValue)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DataChangeFilter> {

        @Override
        public Class<DataChangeFilter> getType() {
            return DataChangeFilter.class;
        }

        @Override
        public DataChangeFilter decode(UaDecoder decoder) throws UaSerializationException {
            DataChangeTrigger trigger = DataChangeTrigger.from(decoder.readInt32("Trigger"));
            UInteger deadbandType = decoder.readUInt32("DeadbandType");
            Double deadbandValue = decoder.readDouble("DeadbandValue");

            return new DataChangeFilter(trigger, deadbandType, deadbandValue);
        }

        @Override
        public void encode(DataChangeFilter value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeInt32("Trigger", value.trigger != null ? value.trigger.getValue() : 0);
            encoder.writeUInt32("DeadbandType", value.deadbandType);
            encoder.writeDouble("DeadbandValue", value.deadbandValue);
        }
    }

}
