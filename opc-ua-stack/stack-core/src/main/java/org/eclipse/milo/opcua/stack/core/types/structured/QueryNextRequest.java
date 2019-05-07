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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
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
public class QueryNextRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=619");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=621");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=620");

    private final RequestHeader requestHeader;

    private final Boolean releaseContinuationPoint;

    private final ByteString continuationPoint;

    public QueryNextRequest(RequestHeader requestHeader, Boolean releaseContinuationPoint,
                            ByteString continuationPoint) {
        this.requestHeader = requestHeader;
        this.releaseContinuationPoint = releaseContinuationPoint;
        this.continuationPoint = continuationPoint;
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

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public Boolean getReleaseContinuationPoint() {
        return releaseContinuationPoint;
    }

    public ByteString getContinuationPoint() {
        return continuationPoint;
    }

    public static final class Codec extends GenericDataTypeCodec<QueryNextRequest> {
        @Override
        public Class<QueryNextRequest> getType() {
            return QueryNextRequest.class;
        }

        @Override
        public QueryNextRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            Boolean releaseContinuationPoint = decoder.readBoolean("ReleaseContinuationPoint");
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");
            return new QueryNextRequest(requestHeader, releaseContinuationPoint, continuationPoint);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, QueryNextRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeBoolean("ReleaseContinuationPoint", value.getReleaseContinuationPoint());
            encoder.writeByteString("ContinuationPoint", value.getContinuationPoint());
        }
    }
}
