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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class ReadEventDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadEventDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadEventDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadEventDetails_Encoding_DefaultXml;

    protected final UInteger numValuesPerNode;
    protected final DateTime startTime;
    protected final DateTime endTime;
    protected final EventFilter filter;

    public ReadEventDetails() {
        super();
        this.numValuesPerNode = null;
        this.startTime = null;
        this.endTime = null;
        this.filter = null;
    }

    public ReadEventDetails(UInteger numValuesPerNode, DateTime startTime, DateTime endTime, EventFilter filter) {
        super();
        this.numValuesPerNode = numValuesPerNode;
        this.startTime = startTime;
        this.endTime = endTime;
        this.filter = filter;
    }

    public UInteger getNumValuesPerNode() { return numValuesPerNode; }

    public DateTime getStartTime() { return startTime; }

    public DateTime getEndTime() { return endTime; }

    public EventFilter getFilter() { return filter; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NumValuesPerNode", numValuesPerNode)
            .add("StartTime", startTime)
            .add("EndTime", endTime)
            .add("Filter", filter)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReadEventDetails> {

        @Override
        public Class<ReadEventDetails> getType() {
            return ReadEventDetails.class;
        }

        @Override
        public ReadEventDetails decode(UaDecoder decoder) throws UaSerializationException {
            UInteger numValuesPerNode = decoder.readUInt32("NumValuesPerNode");
            DateTime startTime = decoder.readDateTime("StartTime");
            DateTime endTime = decoder.readDateTime("EndTime");
            EventFilter filter = (EventFilter) decoder.readBuiltinStruct("Filter", EventFilter.class);

            return new ReadEventDetails(numValuesPerNode, startTime, endTime, filter);
        }

        @Override
        public void encode(ReadEventDetails value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeUInt32("NumValuesPerNode", value.numValuesPerNode);
            encoder.writeDateTime("StartTime", value.startTime);
            encoder.writeDateTime("EndTime", value.endTime);
            encoder.writeBuiltinStruct("Filter", value.filter, EventFilter.class);
        }
    }

}
