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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class QueryNextResponse extends Structure implements UaResponseMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=622");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=624");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=623");

    private final ResponseHeader responseHeader;

    private final QueryDataSet[] queryDataSets;

    private final ByteString revisedContinuationPoint;

    public QueryNextResponse(ResponseHeader responseHeader, QueryDataSet[] queryDataSets,
                             ByteString revisedContinuationPoint) {
        this.responseHeader = responseHeader;
        this.queryDataSets = queryDataSets;
        this.revisedContinuationPoint = revisedContinuationPoint;
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

    public ResponseHeader getResponseHeader() {
        return responseHeader;
    }

    public QueryDataSet[] getQueryDataSets() {
        return queryDataSets;
    }

    public ByteString getRevisedContinuationPoint() {
        return revisedContinuationPoint;
    }

    public static final class Codec extends GenericDataTypeCodec<QueryNextResponse> {
        @Override
        public Class<QueryNextResponse> getType() {
            return QueryNextResponse.class;
        }

        @Override
        public QueryNextResponse decode(SerializationContext context, UaDecoder decoder) {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readStruct("ResponseHeader", ResponseHeader.TYPE_ID);
            QueryDataSet[] queryDataSets = (QueryDataSet[]) decoder.readStructArray("QueryDataSets", QueryDataSet.TYPE_ID);
            ByteString revisedContinuationPoint = decoder.readByteString("RevisedContinuationPoint");
            return new QueryNextResponse(responseHeader, queryDataSets, revisedContinuationPoint);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, QueryNextResponse value) {
            encoder.writeStruct("ResponseHeader", value.getResponseHeader(), ResponseHeader.TYPE_ID);
            encoder.writeStructArray("QueryDataSets", value.getQueryDataSets(), QueryDataSet.TYPE_ID);
            encoder.writeByteString("RevisedContinuationPoint", value.getRevisedContinuationPoint());
        }
    }
}
