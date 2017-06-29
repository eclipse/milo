/*
 * Copyright (c) 2017 Kevin Herron
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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class EventFilterResult extends MonitoringFilterResult {

    public static final NodeId TypeId = Identifiers.EventFilterResult;
    public static final NodeId BinaryEncodingId = Identifiers.EventFilterResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.EventFilterResult_Encoding_DefaultXml;

    protected final StatusCode[] selectClauseResults;
    protected final DiagnosticInfo[] selectClauseDiagnosticInfos;
    protected final ContentFilterResult whereClauseResult;

    public EventFilterResult() {
        super();
        this.selectClauseResults = null;
        this.selectClauseDiagnosticInfos = null;
        this.whereClauseResult = null;
    }

    public EventFilterResult(StatusCode[] selectClauseResults, DiagnosticInfo[] selectClauseDiagnosticInfos, ContentFilterResult whereClauseResult) {
        super();
        this.selectClauseResults = selectClauseResults;
        this.selectClauseDiagnosticInfos = selectClauseDiagnosticInfos;
        this.whereClauseResult = whereClauseResult;
    }

    @Nullable
    public StatusCode[] getSelectClauseResults() { return selectClauseResults; }

    @Nullable
    public DiagnosticInfo[] getSelectClauseDiagnosticInfos() { return selectClauseDiagnosticInfos; }

    public ContentFilterResult getWhereClauseResult() { return whereClauseResult; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SelectClauseResults", selectClauseResults)
            .add("SelectClauseDiagnosticInfos", selectClauseDiagnosticInfos)
            .add("WhereClauseResult", whereClauseResult)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<EventFilterResult> {

        @Override
        public Class<EventFilterResult> getType() {
            return EventFilterResult.class;
        }

        @Override
        public EventFilterResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode[] selectClauseResults = decoder.readArray("SelectClauseResults", decoder::readStatusCode, StatusCode.class);
            DiagnosticInfo[] selectClauseDiagnosticInfos = decoder.readArray("SelectClauseDiagnosticInfos", decoder::readDiagnosticInfo, DiagnosticInfo.class);
            ContentFilterResult whereClauseResult = (ContentFilterResult) decoder.readBuiltinStruct("WhereClauseResult", ContentFilterResult.class);

            return new EventFilterResult(selectClauseResults, selectClauseDiagnosticInfos, whereClauseResult);
        }

        @Override
        public void encode(EventFilterResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeArray("SelectClauseResults", value.selectClauseResults, encoder::writeStatusCode);
            encoder.writeArray("SelectClauseDiagnosticInfos", value.selectClauseDiagnosticInfos, encoder::writeDiagnosticInfo);
            encoder.writeBuiltinStruct("WhereClauseResult", value.whereClauseResult, ContentFilterResult.class);
        }
    }

}
