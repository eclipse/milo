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

@UaDataType("CloseSessionResponse")
public class CloseSessionResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.CloseSessionResponse;
    public static final NodeId BinaryEncodingId = Identifiers.CloseSessionResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CloseSessionResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;

    public CloseSessionResponse() {
        this._responseHeader = null;
    }

    public CloseSessionResponse(ResponseHeader _responseHeader) {
        this._responseHeader = _responseHeader;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(CloseSessionResponse closeSessionResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", closeSessionResponse._responseHeader != null ? closeSessionResponse._responseHeader : new ResponseHeader());
    }

    public static CloseSessionResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);

        return new CloseSessionResponse(_responseHeader);
    }

    static {
        DelegateRegistry.registerEncoder(CloseSessionResponse::encode, CloseSessionResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CloseSessionResponse::decode, CloseSessionResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
