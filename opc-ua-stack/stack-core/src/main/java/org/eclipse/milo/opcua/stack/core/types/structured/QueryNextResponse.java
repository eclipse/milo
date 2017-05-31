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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("QueryNextResponse")
public class QueryNextResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.QueryNextResponse;
    public static final NodeId BinaryEncodingId = Identifiers.QueryNextResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryNextResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final QueryDataSet[] _queryDataSets;
    protected final ByteString _revisedContinuationPoint;

    public QueryNextResponse() {
        this._responseHeader = null;
        this._queryDataSets = null;
        this._revisedContinuationPoint = null;
    }

    public QueryNextResponse(ResponseHeader _responseHeader, QueryDataSet[] _queryDataSets, ByteString _revisedContinuationPoint) {
        this._responseHeader = _responseHeader;
        this._queryDataSets = _queryDataSets;
        this._revisedContinuationPoint = _revisedContinuationPoint;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    @Nullable
    public QueryDataSet[] getQueryDataSets() { return _queryDataSets; }

    public ByteString getRevisedContinuationPoint() { return _revisedContinuationPoint; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", _responseHeader)
            .add("QueryDataSets", _queryDataSets)
            .add("RevisedContinuationPoint", _revisedContinuationPoint)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<QueryNextResponse> {
        @Override
        public QueryNextResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.BinaryEncodingId, reader);
            QueryDataSet[] _queryDataSets =
                reader.readArray(
                    () -> (QueryDataSet) context.decode(
                        QueryDataSet.BinaryEncodingId, reader),
                    QueryDataSet.class
                );
            ByteString _revisedContinuationPoint = reader.readByteString();

            return new QueryNextResponse(_responseHeader, _queryDataSets, _revisedContinuationPoint);
        }

        @Override
        public void encode(SerializationContext context, QueryNextResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.BinaryEncodingId, encodable._responseHeader, writer);
            writer.writeArray(
                encodable._queryDataSets,
                e -> context.encode(QueryDataSet.BinaryEncodingId, e, writer)
            );
            writer.writeByteString(encodable._revisedContinuationPoint);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<QueryNextResponse> {
        @Override
        public QueryNextResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(ResponseHeader.XmlEncodingId, reader);
            QueryDataSet[] _queryDataSets =
                reader.readArray(
                    "QueryDataSets",
                    f -> (QueryDataSet) context.decode(
                        QueryDataSet.XmlEncodingId, reader),
                    QueryDataSet.class
                );
            ByteString _revisedContinuationPoint = reader.readByteString("RevisedContinuationPoint");

            return new QueryNextResponse(_responseHeader, _queryDataSets, _revisedContinuationPoint);
        }

        @Override
        public void encode(SerializationContext context, QueryNextResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(ResponseHeader.XmlEncodingId, encodable._responseHeader, writer);
            writer.writeArray(
                "QueryDataSets",
                encodable._queryDataSets,
                (f, e) -> context.encode(QueryDataSet.XmlEncodingId, e, writer)
            );
            writer.writeByteString("RevisedContinuationPoint", encodable._revisedContinuationPoint);
        }
    }

}
