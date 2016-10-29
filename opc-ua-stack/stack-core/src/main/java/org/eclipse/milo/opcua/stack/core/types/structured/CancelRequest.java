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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("CancelRequest")
public class CancelRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CancelRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CancelRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CancelRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final UInteger _requestHandle;

    public CancelRequest() {
        this._requestHeader = null;
        this._requestHandle = null;
    }

    public CancelRequest(RequestHeader _requestHeader, UInteger _requestHandle) {
        this._requestHeader = _requestHeader;
        this._requestHandle = _requestHandle;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public UInteger getRequestHandle() { return _requestHandle; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(CancelRequest cancelRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", cancelRequest._requestHeader != null ? cancelRequest._requestHeader : new RequestHeader());
        encoder.encodeUInt32("RequestHandle", cancelRequest._requestHandle);
    }

    public static CancelRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        UInteger _requestHandle = decoder.decodeUInt32("RequestHandle");

        return new CancelRequest(_requestHeader, _requestHandle);
    }

    static {
        DelegateRegistry.registerEncoder(CancelRequest::encode, CancelRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CancelRequest::decode, CancelRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
