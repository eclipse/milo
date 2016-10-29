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

@UaDataType("CallRequest")
public class CallRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CallRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CallRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CallRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final CallMethodRequest[] _methodsToCall;

    public CallRequest() {
        this._requestHeader = null;
        this._methodsToCall = null;
    }

    public CallRequest(RequestHeader _requestHeader, CallMethodRequest[] _methodsToCall) {
        this._requestHeader = _requestHeader;
        this._methodsToCall = _methodsToCall;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    public CallMethodRequest[] getMethodsToCall() { return _methodsToCall; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(CallRequest callRequest, UaEncoder encoder) {
        encoder.encodeSerializable("RequestHeader", callRequest._requestHeader != null ? callRequest._requestHeader : new RequestHeader());
        encoder.encodeArray("MethodsToCall", callRequest._methodsToCall, encoder::encodeSerializable);
    }

    public static CallRequest decode(UaDecoder decoder) {
        RequestHeader _requestHeader = decoder.decodeSerializable("RequestHeader", RequestHeader.class);
        CallMethodRequest[] _methodsToCall = decoder.decodeArray("MethodsToCall", decoder::decodeSerializable, CallMethodRequest.class);

        return new CallRequest(_requestHeader, _methodsToCall);
    }

    static {
        DelegateRegistry.registerEncoder(CallRequest::encode, CallRequest.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CallRequest::decode, CallRequest.class, BinaryEncodingId, XmlEncodingId);
    }

}
