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
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("RegisterServer2Response")
public class RegisterServer2Response implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.RegisterServer2Response;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterServer2Response_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterServer2Response_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final StatusCode[] _configurationResults;
    protected final DiagnosticInfo[] _diagnosticInfos;

    public RegisterServer2Response() {
        this._responseHeader = null;
        this._configurationResults = null;
        this._diagnosticInfos = null;
    }

    public RegisterServer2Response(ResponseHeader _responseHeader, StatusCode[] _configurationResults, DiagnosticInfo[] _diagnosticInfos) {
        this._responseHeader = _responseHeader;
        this._configurationResults = _configurationResults;
        this._diagnosticInfos = _diagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public StatusCode[] getConfigurationResults() { return _configurationResults; }

    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(RegisterServer2Response registerServer2Response, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", registerServer2Response._responseHeader != null ? registerServer2Response._responseHeader : new ResponseHeader());
        encoder.encodeArray("ConfigurationResults", registerServer2Response._configurationResults, encoder::encodeStatusCode);
        encoder.encodeArray("DiagnosticInfos", registerServer2Response._diagnosticInfos, encoder::encodeDiagnosticInfo);
    }

    public static RegisterServer2Response decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        StatusCode[] _configurationResults = decoder.decodeArray("ConfigurationResults", decoder::decodeStatusCode, StatusCode.class);
        DiagnosticInfo[] _diagnosticInfos = decoder.decodeArray("DiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);

        return new RegisterServer2Response(_responseHeader, _configurationResults, _diagnosticInfos);
    }

    static {
        DelegateRegistry.registerEncoder(RegisterServer2Response::encode, RegisterServer2Response.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(RegisterServer2Response::decode, RegisterServer2Response.class, BinaryEncodingId, XmlEncodingId);
    }

}
