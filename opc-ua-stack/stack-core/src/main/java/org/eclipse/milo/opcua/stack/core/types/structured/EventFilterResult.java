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
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
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

    @Nullable
    public StatusCode[] getSelectClauseResults() { return _selectClauseResults; }

    @Nullable
    public DiagnosticInfo[] getSelectClauseDiagnosticInfos() { return _selectClauseDiagnosticInfos; }

    public ContentFilterResult getWhereClauseResult() { return _whereClauseResult; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SelectClauseResults", _selectClauseResults)
            .add("SelectClauseDiagnosticInfos", _selectClauseDiagnosticInfos)
            .add("WhereClauseResult", _whereClauseResult)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<EventFilterResult> {
        @Override
        public EventFilterResult decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            StatusCode[] _selectClauseResults = reader.readArray(reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _selectClauseDiagnosticInfos = reader.readArray(reader::readDiagnosticInfo, DiagnosticInfo.class);
            ContentFilterResult _whereClauseResult = (ContentFilterResult) context.decode(ContentFilterResult.BinaryEncodingId, reader);

            return new EventFilterResult(_selectClauseResults, _selectClauseDiagnosticInfos, _whereClauseResult);
        }

        @Override
        public void encode(SerializationContext context, EventFilterResult encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeArray(encodable._selectClauseResults, writer::writeStatusCode);
            writer.writeArray(encodable._selectClauseDiagnosticInfos, writer::writeDiagnosticInfo);
            context.encode(ContentFilterResult.BinaryEncodingId, encodable._whereClauseResult, writer);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<EventFilterResult> {
        @Override
        public EventFilterResult decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            StatusCode[] _selectClauseResults = reader.readArray("SelectClauseResults", reader::readStatusCode, StatusCode.class);
            DiagnosticInfo[] _selectClauseDiagnosticInfos = reader.readArray("SelectClauseDiagnosticInfos", reader::readDiagnosticInfo, DiagnosticInfo.class);
            ContentFilterResult _whereClauseResult = (ContentFilterResult) context.decode(ContentFilterResult.XmlEncodingId, reader);

            return new EventFilterResult(_selectClauseResults, _selectClauseDiagnosticInfos, _whereClauseResult);
        }

        @Override
        public void encode(SerializationContext context, EventFilterResult encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeArray("SelectClauseResults", encodable._selectClauseResults, writer::writeStatusCode);
            writer.writeArray("SelectClauseDiagnosticInfos", encodable._selectClauseDiagnosticInfos, writer::writeDiagnosticInfo);
            context.encode(ContentFilterResult.XmlEncodingId, encodable._whereClauseResult, writer);
        }
    }

}
