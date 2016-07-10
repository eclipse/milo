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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

@UaDataType("CancelResponse")
public class CancelResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.CancelResponse;
    public static final NodeId BinaryEncodingId = Identifiers.CancelResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CancelResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final UInteger _cancelCount;

    public CancelResponse() {
        this._responseHeader = null;
        this._cancelCount = null;
    }

    public CancelResponse(ResponseHeader _responseHeader, UInteger _cancelCount) {
        this._responseHeader = _responseHeader;
        this._cancelCount = _cancelCount;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public UInteger getCancelCount() { return _cancelCount; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(CancelResponse cancelResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", cancelResponse._responseHeader != null ? cancelResponse._responseHeader : new ResponseHeader());
        encoder.encodeUInt32("CancelCount", cancelResponse._cancelCount);
    }

    public static CancelResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        UInteger _cancelCount = decoder.decodeUInt32("CancelCount");

        return new CancelResponse(_responseHeader, _cancelCount);
    }

    static {
        DelegateRegistry.registerEncoder(CancelResponse::encode, CancelResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(CancelResponse::decode, CancelResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
