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
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@UaDataType("TestStackResponse")
public class TestStackResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.TestStackResponse;
    public static final NodeId BinaryEncodingId = Identifiers.TestStackResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TestStackResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final Variant _output;

    public TestStackResponse() {
        this._responseHeader = null;
        this._output = null;
    }

    public TestStackResponse(ResponseHeader _responseHeader, Variant _output) {
        this._responseHeader = _responseHeader;
        this._output = _output;
    }

    public ResponseHeader getResponseHeader() {
        return _responseHeader;
    }

    public Variant getOutput() {
        return _output;
    }

    @Override
    public NodeId getTypeId() {
        return TypeId;
    }

    @Override
    public NodeId getBinaryEncodingId() {
        return BinaryEncodingId;
    }

    @Override
    public NodeId getXmlEncodingId() {
        return XmlEncodingId;
    }


    public static void encode(TestStackResponse testStackResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", testStackResponse._responseHeader != null ? testStackResponse._responseHeader : new ResponseHeader());
        encoder.encodeVariant("Output", testStackResponse._output);
    }

    public static TestStackResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        Variant _output = decoder.decodeVariant("Output");

        return new TestStackResponse(_responseHeader, _output);
    }

    static {
        DelegateRegistry.registerEncoder(TestStackResponse::encode, TestStackResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(TestStackResponse::decode, TestStackResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
