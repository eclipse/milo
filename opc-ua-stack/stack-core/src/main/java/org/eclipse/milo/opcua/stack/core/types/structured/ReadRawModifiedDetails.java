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

@UaDataType("ReadRawModifiedDetails")
public class ReadRawModifiedDetails extends HistoryReadDetails {

    public static final NodeId TypeId = Identifiers.ReadRawModifiedDetails;
    public static final NodeId BinaryEncodingId = Identifiers.ReadRawModifiedDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadRawModifiedDetails_Encoding_DefaultXml;

    protected final Boolean _isReadModified;
    protected final DateTime _startTime;
    protected final DateTime _endTime;
    protected final UInteger _numValuesPerNode;
    protected final Boolean _returnBounds;

    public ReadRawModifiedDetails() {
        super();
        this._isReadModified = null;
        this._startTime = null;
        this._endTime = null;
        this._numValuesPerNode = null;
        this._returnBounds = null;
    }

    public ReadRawModifiedDetails(Boolean _isReadModified, DateTime _startTime, DateTime _endTime, UInteger _numValuesPerNode, Boolean _returnBounds) {
        super();
        this._isReadModified = _isReadModified;
        this._startTime = _startTime;
        this._endTime = _endTime;
        this._numValuesPerNode = _numValuesPerNode;
        this._returnBounds = _returnBounds;
    }

    public Boolean getIsReadModified() { return _isReadModified; }

    public DateTime getStartTime() { return _startTime; }

    public DateTime getEndTime() { return _endTime; }

    public UInteger getNumValuesPerNode() { return _numValuesPerNode; }

    public Boolean getReturnBounds() { return _returnBounds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ReadRawModifiedDetails readRawModifiedDetails, UaEncoder encoder) {
        encoder.encodeBoolean("IsReadModified", readRawModifiedDetails._isReadModified);
        encoder.encodeDateTime("StartTime", readRawModifiedDetails._startTime);
        encoder.encodeDateTime("EndTime", readRawModifiedDetails._endTime);
        encoder.encodeUInt32("NumValuesPerNode", readRawModifiedDetails._numValuesPerNode);
        encoder.encodeBoolean("ReturnBounds", readRawModifiedDetails._returnBounds);
    }

    public static ReadRawModifiedDetails decode(UaDecoder decoder) {
        Boolean _isReadModified = decoder.decodeBoolean("IsReadModified");
        DateTime _startTime = decoder.decodeDateTime("StartTime");
        DateTime _endTime = decoder.decodeDateTime("EndTime");
        UInteger _numValuesPerNode = decoder.decodeUInt32("NumValuesPerNode");
        Boolean _returnBounds = decoder.decodeBoolean("ReturnBounds");

        return new ReadRawModifiedDetails(_isReadModified, _startTime, _endTime, _numValuesPerNode, _returnBounds);
    }

    static {
        DelegateRegistry.registerEncoder(ReadRawModifiedDetails::encode, ReadRawModifiedDetails.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ReadRawModifiedDetails::decode, ReadRawModifiedDetails.class, BinaryEncodingId, XmlEncodingId);
    }

}
