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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class QueryNextRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.QueryNextRequest;
    public static final NodeId BinaryEncodingId = Identifiers.QueryNextRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.QueryNextRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final Boolean releaseContinuationPoint;
    protected final ByteString continuationPoint;

    public QueryNextRequest() {
        this.requestHeader = null;
        this.releaseContinuationPoint = null;
        this.continuationPoint = null;
    }

    public QueryNextRequest(RequestHeader requestHeader, Boolean releaseContinuationPoint, ByteString continuationPoint) {
        this.requestHeader = requestHeader;
        this.releaseContinuationPoint = releaseContinuationPoint;
        this.continuationPoint = continuationPoint;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public Boolean getReleaseContinuationPoint() { return releaseContinuationPoint; }

    public ByteString getContinuationPoint() { return continuationPoint; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", requestHeader)
            .add("ReleaseContinuationPoint", releaseContinuationPoint)
            .add("ContinuationPoint", continuationPoint)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<QueryNextRequest> {

        @Override
        public Class<QueryNextRequest> getType() {
            return QueryNextRequest.class;
        }

        @Override
        public QueryNextRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            Boolean releaseContinuationPoint = decoder.readBoolean("ReleaseContinuationPoint");
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");

            return new QueryNextRequest(requestHeader, releaseContinuationPoint, continuationPoint);
        }

        @Override
        public void encode(QueryNextRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBoolean("ReleaseContinuationPoint", value.releaseContinuationPoint);
            encoder.writeByteString("ContinuationPoint", value.continuationPoint);
        }
    }

}
