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

@UaDataType("BrowseNextResponse")
public class BrowseNextResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.BrowseNextResponse;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseNextResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseNextResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final BrowseResult[] _results;
    protected final DiagnosticInfo[] _diagnosticInfos;

    public BrowseNextResponse() {
        this._responseHeader = null;
        this._results = null;
        this._diagnosticInfos = null;
    }

    public BrowseNextResponse(ResponseHeader _responseHeader, BrowseResult[] _results, DiagnosticInfo[] _diagnosticInfos) {
        this._responseHeader = _responseHeader;
        this._results = _results;
        this._diagnosticInfos = _diagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public BrowseResult[] getResults() { return _results; }

    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(BrowseNextResponse browseNextResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", browseNextResponse._responseHeader != null ? browseNextResponse._responseHeader : new ResponseHeader());
        encoder.encodeArray("Results", browseNextResponse._results, encoder::encodeSerializable);
        encoder.encodeArray("DiagnosticInfos", browseNextResponse._diagnosticInfos, encoder::encodeDiagnosticInfo);
    }

    public static BrowseNextResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        BrowseResult[] _results = decoder.decodeArray("Results", decoder::decodeSerializable, BrowseResult.class);
        DiagnosticInfo[] _diagnosticInfos = decoder.decodeArray("DiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);

        return new BrowseNextResponse(_responseHeader, _results, _diagnosticInfos);
    }

    static {
        DelegateRegistry.registerEncoder(BrowseNextResponse::encode, BrowseNextResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(BrowseNextResponse::decode, BrowseNextResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
