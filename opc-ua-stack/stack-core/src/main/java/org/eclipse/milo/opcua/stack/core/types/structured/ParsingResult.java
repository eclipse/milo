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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("ParsingResult")
public class ParsingResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.ParsingResult;
    public static final NodeId BinaryEncodingId = Identifiers.ParsingResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ParsingResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final StatusCode[] _dataStatusCodes;
    protected final DiagnosticInfo[] _dataDiagnosticInfos;

    public ParsingResult() {
        this._statusCode = null;
        this._dataStatusCodes = null;
        this._dataDiagnosticInfos = null;
    }

    public ParsingResult(StatusCode _statusCode, StatusCode[] _dataStatusCodes, DiagnosticInfo[] _dataDiagnosticInfos) {
        this._statusCode = _statusCode;
        this._dataStatusCodes = _dataStatusCodes;
        this._dataDiagnosticInfos = _dataDiagnosticInfos;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public StatusCode[] getDataStatusCodes() { return _dataStatusCodes; }

    public DiagnosticInfo[] getDataDiagnosticInfos() { return _dataDiagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(ParsingResult parsingResult, UaEncoder encoder) {
        encoder.encodeStatusCode("StatusCode", parsingResult._statusCode);
        encoder.encodeArray("DataStatusCodes", parsingResult._dataStatusCodes, encoder::encodeStatusCode);
        encoder.encodeArray("DataDiagnosticInfos", parsingResult._dataDiagnosticInfos, encoder::encodeDiagnosticInfo);
    }

    public static ParsingResult decode(UaDecoder decoder) {
        StatusCode _statusCode = decoder.decodeStatusCode("StatusCode");
        StatusCode[] _dataStatusCodes = decoder.decodeArray("DataStatusCodes", decoder::decodeStatusCode, StatusCode.class);
        DiagnosticInfo[] _dataDiagnosticInfos = decoder.decodeArray("DataDiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);

        return new ParsingResult(_statusCode, _dataStatusCodes, _dataDiagnosticInfos);
    }

    static {
        DelegateRegistry.registerEncoder(ParsingResult::encode, ParsingResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(ParsingResult::decode, ParsingResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
