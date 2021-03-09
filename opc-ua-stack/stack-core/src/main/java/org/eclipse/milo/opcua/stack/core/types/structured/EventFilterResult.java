/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.DiagnosticInfo;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@EqualsAndHashCode(
    callSuper = true
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class EventFilterResult extends MonitoringFilterResult implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=734");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=736");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=735");

    private final StatusCode[] selectClauseResults;

    private final DiagnosticInfo[] selectClauseDiagnosticInfos;

    private final ContentFilterResult whereClauseResult;

    public EventFilterResult(StatusCode[] selectClauseResults,
                             DiagnosticInfo[] selectClauseDiagnosticInfos, ContentFilterResult whereClauseResult) {
        this.selectClauseResults = selectClauseResults;
        this.selectClauseDiagnosticInfos = selectClauseDiagnosticInfos;
        this.whereClauseResult = whereClauseResult;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public StatusCode[] getSelectClauseResults() {
        return selectClauseResults;
    }

    public DiagnosticInfo[] getSelectClauseDiagnosticInfos() {
        return selectClauseDiagnosticInfos;
    }

    public ContentFilterResult getWhereClauseResult() {
        return whereClauseResult;
    }

    public static final class Codec extends GenericDataTypeCodec<EventFilterResult> {
        @Override
        public Class<EventFilterResult> getType() {
            return EventFilterResult.class;
        }

        @Override
        public EventFilterResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode[] selectClauseResults = decoder.readStatusCodeArray("SelectClauseResults");
            DiagnosticInfo[] selectClauseDiagnosticInfos = decoder.readDiagnosticInfoArray("SelectClauseDiagnosticInfos");
            ContentFilterResult whereClauseResult = (ContentFilterResult) decoder.readStruct("WhereClauseResult", ContentFilterResult.TYPE_ID);
            return new EventFilterResult(selectClauseResults, selectClauseDiagnosticInfos, whereClauseResult);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, EventFilterResult value) {
            encoder.writeStatusCodeArray("SelectClauseResults", value.getSelectClauseResults());
            encoder.writeDiagnosticInfoArray("SelectClauseDiagnosticInfos", value.getSelectClauseDiagnosticInfos());
            encoder.writeStruct("WhereClauseResult", value.getWhereClauseResult(), ContentFilterResult.TYPE_ID);
        }
    }
}
