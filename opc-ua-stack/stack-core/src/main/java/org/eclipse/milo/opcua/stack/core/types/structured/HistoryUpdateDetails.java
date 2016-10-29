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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("HistoryUpdateDetails")
public class HistoryUpdateDetails implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryUpdateDetails;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryUpdateDetails_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryUpdateDetails_Encoding_DefaultXml;

    protected final NodeId _nodeId;

    public HistoryUpdateDetails() {
        this._nodeId = null;
    }

    public HistoryUpdateDetails(NodeId _nodeId) {
        this._nodeId = _nodeId;
    }

    public NodeId getNodeId() { return _nodeId; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(HistoryUpdateDetails historyUpdateDetails, UaEncoder encoder) {
        encoder.encodeNodeId("NodeId", historyUpdateDetails._nodeId);
    }

    public static HistoryUpdateDetails decode(UaDecoder decoder) {
        NodeId _nodeId = decoder.decodeNodeId("NodeId");

        return new HistoryUpdateDetails(_nodeId);
    }

    static {
        DelegateRegistry.registerEncoder(HistoryUpdateDetails::encode, HistoryUpdateDetails.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(HistoryUpdateDetails::decode, HistoryUpdateDetails.class, BinaryEncodingId, XmlEncodingId);
    }

}
