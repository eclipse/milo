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

@UaDataType("DeleteRawModifiedDetails")
public class DeleteRawModifiedDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.DeleteRawModifiedDetails;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteRawModifiedDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteRawModifiedDetails_Encoding_DefaultXml;

    protected final Boolean _isDeleteModified;
    protected final DateTime _startTime;
    protected final DateTime _endTime;

    public DeleteRawModifiedDetails() {
        super(null);
        this._isDeleteModified = null;
        this._startTime = null;
        this._endTime = null;
    }

    public DeleteRawModifiedDetails(NodeId _nodeId, Boolean _isDeleteModified, DateTime _startTime, DateTime _endTime) {
        super(_nodeId);
        this._isDeleteModified = _isDeleteModified;
        this._startTime = _startTime;
        this._endTime = _endTime;
    }

    public Boolean getIsDeleteModified() { return _isDeleteModified; }

    public DateTime getStartTime() { return _startTime; }

    public DateTime getEndTime() { return _endTime; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(DeleteRawModifiedDetails deleteRawModifiedDetails, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", deleteRawModifiedDetails._nodeId);
        encoder.encodeBoolean("IsDeleteModified", deleteRawModifiedDetails._isDeleteModified);
        encoder.encodeDateTime("StartTime", deleteRawModifiedDetails._startTime);
        encoder.encodeDateTime("EndTime", deleteRawModifiedDetails._endTime);
    }

    public static DeleteRawModifiedDetails decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        Boolean _isDeleteModified = decoder.decodeBoolean("IsDeleteModified");
        DateTime _startTime = decoder.decodeDateTime("StartTime");
        DateTime _endTime = decoder.decodeDateTime("EndTime");

        return new DeleteRawModifiedDetails(_nodeId, _isDeleteModified, _startTime, _endTime);
    }

    static {
        DelegateRegistry.registerEncoder(DeleteRawModifiedDetails::encode, DeleteRawModifiedDetails.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(DeleteRawModifiedDetails::decode, DeleteRawModifiedDetails.class, BinaryEncodingId, XmlEncodingId);
    }

}
