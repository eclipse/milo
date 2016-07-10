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

@UaDataType("TransferSubscriptionsResponse")
public class TransferSubscriptionsResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.TransferSubscriptionsResponse;
    public static final NodeId BinaryEncodingId = Identifiers.TransferSubscriptionsResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TransferSubscriptionsResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final TransferResult[] _results;
    protected final DiagnosticInfo[] _diagnosticInfos;

    public TransferSubscriptionsResponse() {
        this._responseHeader = null;
        this._results = null;
        this._diagnosticInfos = null;
    }

    public TransferSubscriptionsResponse(ResponseHeader _responseHeader, TransferResult[] _results, DiagnosticInfo[] _diagnosticInfos) {
        this._responseHeader = _responseHeader;
        this._results = _results;
        this._diagnosticInfos = _diagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public TransferResult[] getResults() { return _results; }

    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(TransferSubscriptionsResponse transferSubscriptionsResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", transferSubscriptionsResponse._responseHeader != null ? transferSubscriptionsResponse._responseHeader : new ResponseHeader());
        encoder.encodeArray("Results", transferSubscriptionsResponse._results, encoder::encodeSerializable);
        encoder.encodeArray("DiagnosticInfos", transferSubscriptionsResponse._diagnosticInfos, encoder::encodeDiagnosticInfo);
    }

    public static TransferSubscriptionsResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        TransferResult[] _results = decoder.decodeArray("Results", decoder::decodeSerializable, TransferResult.class);
        DiagnosticInfo[] _diagnosticInfos = decoder.decodeArray("DiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);

        return new TransferSubscriptionsResponse(_responseHeader, _results, _diagnosticInfos);
    }

    static {
        DelegateRegistry.registerEncoder(TransferSubscriptionsResponse::encode, TransferSubscriptionsResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(TransferSubscriptionsResponse::decode, TransferSubscriptionsResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
