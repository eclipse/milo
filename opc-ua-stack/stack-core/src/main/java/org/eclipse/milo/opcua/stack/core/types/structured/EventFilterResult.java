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
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@UaDataType("EventFilterResult")
public class EventFilterResult extends MonitoringFilterResult {

    public static final NodeId TypeId = Identifiers.EventFilterResult;
    public static final NodeId BinaryEncodingId = Identifiers.EventFilterResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventFilterResult_Encoding_DefaultXml;

    protected final StatusCode[] _selectClauseResults;
    protected final DiagnosticInfo[] _selectClauseDiagnosticInfos;
    protected final ContentFilterResult _whereClauseResult;

    public EventFilterResult() {
        super();
        this._selectClauseResults = null;
        this._selectClauseDiagnosticInfos = null;
        this._whereClauseResult = null;
    }

    public EventFilterResult(StatusCode[] _selectClauseResults, DiagnosticInfo[] _selectClauseDiagnosticInfos, ContentFilterResult _whereClauseResult) {
        super();
        this._selectClauseResults = _selectClauseResults;
        this._selectClauseDiagnosticInfos = _selectClauseDiagnosticInfos;
        this._whereClauseResult = _whereClauseResult;
    }

    public StatusCode[] getSelectClauseResults() { return _selectClauseResults; }

    public DiagnosticInfo[] getSelectClauseDiagnosticInfos() { return _selectClauseDiagnosticInfos; }

    public ContentFilterResult getWhereClauseResult() { return _whereClauseResult; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }


    public static void encode(EventFilterResult eventFilterResult, UaEncoder encoder) {
        encoder.encodeArray("SelectClauseResults", eventFilterResult._selectClauseResults, encoder::encodeStatusCode);
        encoder.encodeArray("SelectClauseDiagnosticInfos", eventFilterResult._selectClauseDiagnosticInfos, encoder::encodeDiagnosticInfo);
        encoder.encodeSerializable("WhereClauseResult", eventFilterResult._whereClauseResult != null ? eventFilterResult._whereClauseResult : new ContentFilterResult());
    }

    public static EventFilterResult decode(UaDecoder decoder) {
        StatusCode[] _selectClauseResults = decoder.decodeArray("SelectClauseResults", decoder::decodeStatusCode, StatusCode.class);
        DiagnosticInfo[] _selectClauseDiagnosticInfos = decoder.decodeArray("SelectClauseDiagnosticInfos", decoder::decodeDiagnosticInfo, DiagnosticInfo.class);
        ContentFilterResult _whereClauseResult = decoder.decodeSerializable("WhereClauseResult", ContentFilterResult.class);

        return new EventFilterResult(_selectClauseResults, _selectClauseDiagnosticInfos, _whereClauseResult);
    }

    static {
        DelegateRegistry.registerEncoder(EventFilterResult::encode, EventFilterResult.class, BinaryEncodingId, XmlEncodingId);
        DelegateRegistry.registerDecoder(EventFilterResult::decode, EventFilterResult.class, BinaryEncodingId, XmlEncodingId);
    }

}
