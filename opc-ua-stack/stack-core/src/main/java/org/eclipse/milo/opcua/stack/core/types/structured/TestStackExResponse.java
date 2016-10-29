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

@UaDataType("TestStackExResponse")
public class TestStackExResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.TestStackExResponse;
    public static final NodeId BinaryEncodingId = Identifiers.TestStackExResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TestStackExResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final CompositeTestType _output;

    public TestStackExResponse() {
        this._responseHeader = null;
        this._output = null;
    }

    public TestStackExResponse(ResponseHeader _responseHeader, CompositeTestType _output) {
        this._responseHeader = _responseHeader;
        this._output = _output;
    }

    public ResponseHeader getResponseHeader() {
        return _responseHeader;
    }

    public CompositeTestType getOutput() {
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


    public static void encode(TestStackExResponse testStackExResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", testStackExResponse._responseHeader != null ? testStackExResponse._responseHeader : new ResponseHeader());
        encoder.encodeSerializable("Output", testStackExResponse._output != null ? testStackExResponse._output : new CompositeTestType());
    }

    public static TestStackExResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        CompositeTestType _output = decoder.decodeSerializable("Output", CompositeTestType.class);

        return new TestStackExResponse(_responseHeader, _output);
    }

    static {
        DelegateRegistry.registerEncoder(TestStackExResponse::encode, TestStackExResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(TestStackExResponse::decode, TestStackExResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
