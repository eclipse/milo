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

@UaDataType("GetEndpointsResponse")
public class GetEndpointsResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.GetEndpointsResponse;
    public static final NodeId BinaryEncodingId = Identifiers.GetEndpointsResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.GetEndpointsResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final EndpointDescription[] _endpoints;

    public GetEndpointsResponse() {
        this._responseHeader = null;
        this._endpoints = null;
    }

    public GetEndpointsResponse(ResponseHeader _responseHeader, EndpointDescription[] _endpoints) {
        this._responseHeader = _responseHeader;
        this._endpoints = _endpoints;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public EndpointDescription[] getEndpoints() { return _endpoints; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(GetEndpointsResponse getEndpointsResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", getEndpointsResponse._responseHeader != null ? getEndpointsResponse._responseHeader : new ResponseHeader());
        encoder.encodeArray("Endpoints", getEndpointsResponse._endpoints, encoder::encodeSerializable);
    }

    public static GetEndpointsResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        EndpointDescription[] _endpoints = decoder.decodeArray("Endpoints", decoder::decodeSerializable, EndpointDescription.class);

        return new GetEndpointsResponse(_responseHeader, _endpoints);
    }

    static {
        DelegateRegistry.registerEncoder(GetEndpointsResponse::encode, GetEndpointsResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(GetEndpointsResponse::decode, GetEndpointsResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
