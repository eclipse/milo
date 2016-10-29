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

@UaDataType("HistoryUpdateResult")
public class HistoryUpdateResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryUpdateResult;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryUpdateResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryUpdateResult_Encoding_DefaultXml;

    protected final StatusCode _statusCode;
    protected final StatusCode[] _operationResults;
    protected final DiagnosticInfo[] _diagnosticInfos;

    public HistoryUpdateResult() {
        this._statusCode = null;
        this._operationResults = null;
        this._diagnosticInfos = null;
    }

    public HistoryUpdateResult(StatusCode _statusCode, StatusCode[] _operationResults, DiagnosticInfo[] _diagnosticInfos) {
        this._statusCode = _statusCode;
        this._operationResults = _operationResults;
        this._diagnosticInfos = _diagnosticInfos;
    }

    public StatusCode getStatusCode() { return _statusCode; }

    public StatusCode[] getOperationResults() { return _operationResults; }

    public DiagnosticInfo[] getDiagnosticInfos() { return _diagnosticInfos; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(HistoryUpdateResult historyUpdateResult, UaEncoder encoder) {
        encoder.encodeStatusCode("StatusCode", historyUpdateResult._statusCode);
        encoder.encodeArray("OperationResults", historyUpdateResult._operationResults, encoder::encodeStatusCode);
        encoder.encodeArray("DiagnosticInfos", historyUpdateResult._diagnosticInfos, encoder::encodeDiagnosticInfo);
    }

    public static HistoryUpdateResult decode(UaDecoder decoder) {
        StatusCode _statusCode = decoder.decodeStatusCode("StatusCode");
        StatusCode[] _operationResults = decoder.decodeArray("OperationResults", decoder::decodeStatusCode, StatusCode.class);
        DiagnosticInfo[] _diagnosticInfos = decoder.decodeArray("DiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);

        return new HistoryUpdateResult(_statusCode, _operationResults, _diagnosticInfos);
    }

    static {
        DelegateRegistry.registerEncoder(HistoryUpdateResult::encode, HistoryUpdateResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(HistoryUpdateResult::decode, HistoryUpdateResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
