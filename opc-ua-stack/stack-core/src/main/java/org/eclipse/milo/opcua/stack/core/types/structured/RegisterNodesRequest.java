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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("RegisterNodesRequest")
public class RegisterNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RegisterNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final NodeId[] _nodesToRegister;

    public RegisterNodesRequest() {
        this._requestHeader = null;
        this._nodesToRegister = null;
    }

    public RegisterNodesRequest(RequestHeader _requestHeader, NodeId[] _nodesToRegister) {
        this._requestHeader = _requestHeader;
        this._nodesToRegister = _nodesToRegister;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public NodeId[] getNodesToRegister() { return _nodesToRegister; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RegisterNodesRequest registerNodesRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", registerNodesRequest._requestHeader != null ? registerNodesRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("NodesToRegister", registerNodesRequest._nodesToRegister, encoder::encodeNodeId);
    }

    public static RegisterNodesRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        NodeId[] _nodesToRegister = decoder.decodeArray("NodesToRegister", decoder::decodeNodeId, NodeId.class);

        return new RegisterNodesRequest(_requestHeader, _nodesToRegister);
    }

    static {
        DelegateRegistry.registerEncoder(RegisterNodesRequest::encode, RegisterNodesRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RegisterNodesRequest::decode, RegisterNodesRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
