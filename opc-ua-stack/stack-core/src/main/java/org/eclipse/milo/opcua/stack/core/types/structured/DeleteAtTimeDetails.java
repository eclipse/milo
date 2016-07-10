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

@UaDataType("DeleteAtTimeDetails")
public class DeleteAtTimeDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.DeleteAtTimeDetails;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteAtTimeDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteAtTimeDetails_Encoding_DefaultXml;

    protected final DateTime[] _reqTimes;

    public DeleteAtTimeDetails() {
        super(null);
        this._reqTimes = null;
    }

    public DeleteAtTimeDetails(NodeId _nodeId, DateTime[] _reqTimes) {
        super(_nodeId);
        this._reqTimes = _reqTimes;
    }

    public DateTime[] getReqTimes() { return _reqTimes; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(DeleteAtTimeDetails deleteAtTimeDetails, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", deleteAtTimeDetails._nodeId);
        encoder.encodeArray("ReqTimes", deleteAtTimeDetails._reqTimes, encoder::encodeDateTime);
    }

    public static DeleteAtTimeDetails decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        DateTime[] _reqTimes = decoder.decodeArray("ReqTimes", decoder::decodeDateTime, DateTime.class);

        return new DeleteAtTimeDetails(_nodeId, _reqTimes);
    }

    static {
        DelegateRegistry.registerEncoder(DeleteAtTimeDetails::encode, DeleteAtTimeDetails.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(DeleteAtTimeDetails::decode, DeleteAtTimeDetails.class, BinaryEncodingId, XmlEncodingId);
    }

}
