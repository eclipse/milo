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

@UaDataType("SetTriggeringResponse")
public class SetTriggeringResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.SetTriggeringResponse;
    public static final NodeId BinaryEncodingId = Identifiers.SetTriggeringResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.SetTriggeringResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final StatusCode[] _addResults;
    protected final DiagnosticInfo[] _addDiagnosticInfos;
    protected final StatusCode[] _removeResults;
    protected final DiagnosticInfo[] _removeDiagnosticInfos;

    public SetTriggeringResponse() {
        this._responseHeader = null;
        this._addResults = null;
        this._addDiagnosticInfos = null;
        this._removeResults = null;
        this._removeDiagnosticInfos = null;
    }

    public SetTriggeringResponse(ResponseHeader _responseHeader, StatusCode[] _addResults, DiagnosticInfo[] _addDiagnosticInfos, StatusCode[] _removeResults, DiagnosticInfo[] _removeDiagnosticInfos) {
        this._responseHeader = _responseHeader;
        this._addResults = _addResults;
        this._addDiagnosticInfos = _addDiagnosticInfos;
        this._removeResults = _removeResults;
        this._removeDiagnosticInfos = _removeDiagnosticInfos;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    public StatusCode[] getAddResults() { return _addResults; }

    public DiagnosticInfo[] getAddDiagnosticInfos() { return _addDiagnosticInfos; }

    public StatusCode[] getRemoveResults() { return _removeResults; }

    public DiagnosticInfo[] getRemoveDiagnosticInfos() { return _removeDiagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(SetTriggeringResponse setTriggeringResponse, UaEncoder encoder) {
        encoder.encodeSerializable("ResponseHeader", setTriggeringResponse._responseHeader != null ? setTriggeringResponse._responseHeader : new ResponseHeader());
        encoder.encodeArray("AddResults", setTriggeringResponse._addResults, encoder::encodeStatusCode);
        encoder.encodeArray("AddDiagnosticInfos", setTriggeringResponse._addDiagnosticInfos, encoder::encodeDiagnosticInfo);
        encoder.encodeArray("RemoveResults", setTriggeringResponse._removeResults, encoder::encodeStatusCode);
        encoder.encodeArray("RemoveDiagnosticInfos", setTriggeringResponse._removeDiagnosticInfos, encoder::encodeDiagnosticInfo);
    }

    public static SetTriggeringResponse decode(UaDecoder decoder) {
        ResponseHeader _responseHeader = decoder.decodeSerializable("ResponseHeader", ResponseHeader.class);
        StatusCode[] _addResults = decoder.decodeArray("AddResults", decoder::decodeStatusCode, StatusCode.class);
        DiagnosticInfo[] _addDiagnosticInfos = decoder.decodeArray("AddDiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);
        StatusCode[] _removeResults = decoder.decodeArray("RemoveResults", decoder::decodeStatusCode, StatusCode.class);
        DiagnosticInfo[] _removeDiagnosticInfos = decoder.decodeArray("RemoveDiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);

        return new SetTriggeringResponse(_responseHeader, _addResults, _addDiagnosticInfos, _removeResults, _removeDiagnosticInfos);
    }

    static {
        DelegateRegistry.registerEncoder(SetTriggeringResponse::encode, SetTriggeringResponse.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(SetTriggeringResponse::decode, SetTriggeringResponse.class, BinaryEncodingId, XmlEncodingId);
    }

}
