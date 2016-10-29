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

@UaDataType("AddNodesRequest")
public class AddNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.AddNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.AddNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.AddNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final AddNodesItem[] _nodesToAdd;

    public AddNodesRequest() {
        this._requestHeader = null;
        this._nodesToAdd = null;
    }

    public AddNodesRequest(RequestHeader _requestHeader, AddNodesItem[] _nodesToAdd) {
        this._requestHeader = _requestHeader;
        this._nodesToAdd = _nodesToAdd;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public AddNodesItem[] getNodesToAdd() { return _nodesToAdd; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(AddNodesRequest addNodesRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", addNodesRequest._requestHeader != null ? addNodesRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("NodesToAdd", addNodesRequest._nodesToAdd, encoder::encodeSerializable);
    }

    public static AddNodesRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        AddNodesItem[] _nodesToAdd = decoder.decodeArray("NodesToAdd", decoder::decodeSerializable, AddNodesItem.class);

        return new AddNodesRequest(_requestHeader, _nodesToAdd);
    }

    static {
        DelegateRegistry.registerEncoder(AddNodesRequest::encode, AddNodesRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(AddNodesRequest::decode, AddNodesRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
