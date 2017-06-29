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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class QueryNextResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.QueryNextResponse;
    public static final NodeId BinaryEncodingId = Identifiers.QueryNextResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryNextResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final QueryDataSet[] queryDataSets;
    protected final ByteString revisedContinuationPoint;

    public QueryNextResponse() {
        this.responseHeader = null;
        this.queryDataSets = null;
        this.revisedContinuationPoint = null;
    }

    public QueryNextResponse(ResponseHeader responseHeader, QueryDataSet[] queryDataSets, ByteString revisedContinuationPoint) {
        this.responseHeader = responseHeader;
        this.queryDataSets = queryDataSets;
        this.revisedContinuationPoint = revisedContinuationPoint;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Nullable
    public QueryDataSet[] getQueryDataSets() { return queryDataSets; }

    public ByteString getRevisedContinuationPoint() { return revisedContinuationPoint; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", responseHeader)
            .add("QueryDataSets", queryDataSets)
            .add("RevisedContinuationPoint", revisedContinuationPoint)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<QueryNextResponse> {

        @Override
        public Class<QueryNextResponse> getType() {
            return QueryNextResponse.class;
        }

        @Override
        public QueryNextResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            QueryDataSet[] queryDataSets =
                decoder.readBuiltinStructArray(
                    "QueryDataSets",
                    QueryDataSet.class
                );
            ByteString revisedContinuationPoint = decoder.readByteString("RevisedContinuationPoint");

            return new QueryNextResponse(responseHeader, queryDataSets, revisedContinuationPoint);
        }

        @Override
        public void encode(QueryNextResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeBuiltinStructArray(
                "QueryDataSets",
                value.queryDataSets,
                QueryDataSet.class
            );
            encoder.writeByteString("RevisedContinuationPoint", value.revisedContinuationPoint);
        }
    }

}
