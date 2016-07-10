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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("RegisterNodesResponse")
public class RegisterNodesResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.RegisterNodesResponse;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterNodesResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterNodesResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final NodeId[] _registeredNodeIds;

    public RegisterNodesResponse() {
        this._responseHeader = null;
        this._registeredNodeIds = null;
    }

    public RegisterNodesResponse(ResponseHeader _responseHeader, NodeId[] _registeredNodeIds) {
        this._responseHeader = _responseHeader;
        this._registeredNodeIds = _registeredNodeIds;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public NodeId[] getRegisteredNodeIds() { return _registeredNodeIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RegisterNodesResponse registerNodesResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", registerNodesResponse._responseHeader != null ? registerNodesResponse._responseHeader : new ResponseHeader());
        encoder.encodeArray("RegisteredNodeIds", registerNodesResponse._registeredNodeIds, encoder::encodeNodeId);
    }

    public static RegisterNodesResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        NodeId[] _registeredNodeIds = decoder.decodeArray("RegisteredNodeIds", decoder::decodeNodeId, NodeId.class);

        return new RegisterNodesResponse(_responseHeader, _registeredNodeIds);
    }

    static {
        DelegateRegistry.registerEncoder(RegisterNodesResponse::encode, RegisterNodesResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RegisterNodesResponse::decode, RegisterNodesResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
