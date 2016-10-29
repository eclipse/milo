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
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("DeleteEventDetails")
public class DeleteEventDetails extends HistoryUpdateDetails {

    public static final NodeId TypeId = Identifiers.DeleteEventDetails;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteEventDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteEventDetails_Encoding_DefaultXml;

    protected final ByteString[] _eventIds;

    public DeleteEventDetails() {
        super(null);
        this._eventIds = null;
    }

    public DeleteEventDetails(NodeId _nodeId, ByteString[] _eventIds) {
        super(_nodeId);
        this._eventIds = _eventIds;
    }

    public ByteString[] getEventIds() { return _eventIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(DeleteEventDetails deleteEventDetails, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", deleteEventDetails._nodeId);
        encoder.encodeArray("EventIds", deleteEventDetails._eventIds, encoder::encodeByteString);
    }

    public static DeleteEventDetails decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");
        ByteString[] _eventIds = decoder.decodeArray("EventIds", decoder::decodeByteString, ByteString.class);

        return new DeleteEventDetails(_nodeId, _eventIds);
    }

    static {
        DelegateRegistry.registerEncoder(DeleteEventDetails::encode, DeleteEventDetails.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(DeleteEventDetails::decode, DeleteEventDetails.class, BinaryEncodingId, XmlEncodingId);
    }

}
