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
import org.eclipse.milo.opcua.stack.core.types.builtin.DateTime;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("ReadEventDetails")
public class ReadEventDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadEventDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadEventDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadEventDetails_Encoding_DefaultXml;

    protected final UInteger _numValuesPerNode;
    protected final DateTime _startTime;
    protected final DateTime _endTime;
    protected final EventFilter _filter;

    public ReadEventDetails() {
        super();
        this._numValuesPerNode = null;
        this._startTime = null;
        this._endTime = null;
        this._filter = null;
    }

    public ReadEventDetails(UInteger _numValuesPerNode, DateTime _startTime, DateTime _endTime, EventFilter _filter) {
        super();
        this._numValuesPerNode = _numValuesPerNode;
        this._startTime = _startTime;
        this._endTime = _endTime;
        this._filter = _filter;
    }

    public UInteger getNumValuesPerNode() { return _numValuesPerNode; }

    public DateTime getStartTime() { return _startTime; }

    public DateTime getEndTime() { return _endTime; }

    public EventFilter getFilter() { return _filter; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ReadEventDetails readEventDetails, UaEncoder encoder) {
        encoder.encodeUInt32("NumValuesPerNode", readEventDetails._numValuesPerNode);
        encoder.encodeDateTime("StartTime", readEventDetails._startTime);
        encoder.encodeDateTime("EndTime", readEventDetails._endTime);
        encoder.encodeSerializable("Filter", readEventDetails._filter != null ? readEventDetails._filter : new EventFilter());
    }

    public static ReadEventDetails decode(UaDecoder decoder) {
        UInteger _numValuesPerNode = decoder.decodeUInt32("NumValuesPerNode");
        DateTime _startTime = decoder.decodeDateTime("StartTime");
        DateTime _endTime = decoder.decodeDateTime("EndTime");
        EventFilter _filter = decoder.decodeSerializable("Filter", EventFilter.class);

        return new ReadEventDetails(_numValuesPerNode, _startTime, _endTime, _filter);
    }

    static {
        DelegateRegistry.registerEncoder(ReadEventDetails::encode, ReadEventDetails.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ReadEventDetails::decode, ReadEventDetails.class, BinaryEncodingId, XmlEncodingId);
    }

}
