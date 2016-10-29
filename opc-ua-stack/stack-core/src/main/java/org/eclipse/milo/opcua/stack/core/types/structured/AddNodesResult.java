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
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("AddNodesResult")
public class AddNodesResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.AddNodesResult;
    public static final NodeId BinaryEncodingId = Identifiers.AddNodesResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddNodesResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final NodeId _addedNodeId;

    public AddNodesResult() {
        this._statusCode = null;
        this._addedNodeId = null;
    }

    public AddNodesResult(StatusCode _statusCode, NodeId _addedNodeId) {
        this._statusCode = _statusCode;
        this._addedNodeId = _addedNodeId;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public NodeId getAddedNodeId() { return _addedNodeId; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(AddNodesResult addNodesResult, UaEncoder encoder) {
        encoder.encodeStatusCode("StatusCode", addNodesResult._statusCode);
        encoder.encodeNodeId("AddedNodeId", addNodesResult._addedNodeId);
    }

    public static AddNodesResult decode(UaDecoder decoder) {
        StatusCode _statusCode = decoder.decodeStatusCode("StatusCode");
        NodeId _addedNodeId = decoder.decodeNodeId("AddedNodeId");

        return new AddNodesResult(_statusCode, _addedNodeId);
    }

    static {
        DelegateRegistry.registerEncoder(AddNodesResult::encode, AddNodesResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(AddNodesResult::decode, AddNodesResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
