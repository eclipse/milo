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
public class BrowseNextRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=531");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=533");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=532");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15186");

    private final RequestHeader requestHeader;

    private final Boolean releaseContinuationPoints;

    private final ByteString[] continuationPoints;

    public BrowseNextRequest(RequestHeader requestHeader, Boolean releaseContinuationPoints,
                             ByteString[] continuationPoints) {
        this.requestHeader = requestHeader;
        this.releaseContinuationPoints = releaseContinuationPoints;
        this.continuationPoints = continuationPoints;
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

    public Boolean getReleaseContinuationPoints() {
        return releaseContinuationPoints;
    }

    public ByteString[] getContinuationPoints() {
        return continuationPoints;
    }

    public static final class Codec extends GenericDataTypeCodec<BrowseNextRequest> {
        @Override
        public Class<BrowseNextRequest> getType() {
            return BrowseNextRequest.class;
        }

        @Override
        public BrowseNextRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            Boolean releaseContinuationPoints = decoder.readBoolean("ReleaseContinuationPoints");
            ByteString[] continuationPoints = decoder.readByteStringArray("ContinuationPoints");
            return new BrowseNextRequest(requestHeader, releaseContinuationPoints, continuationPoints);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, BrowseNextRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeBoolean("ReleaseContinuationPoints", value.getReleaseContinuationPoints());
            encoder.writeByteStringArray("ContinuationPoints", value.getContinuationPoints());
        }
    }
}
