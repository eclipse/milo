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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class TranslateBrowsePathsToNodeIdsRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=552");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=554");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=553");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15193");

    private final RequestHeader requestHeader;

    private final BrowsePath[] browsePaths;

    public TranslateBrowsePathsToNodeIdsRequest(RequestHeader requestHeader,
                                                BrowsePath[] browsePaths) {
        this.requestHeader = requestHeader;
        this.browsePaths = browsePaths;
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

    public BrowsePath[] getBrowsePaths() {
        return browsePaths;
    }

    public static final class Codec extends GenericDataTypeCodec<TranslateBrowsePathsToNodeIdsRequest> {
        @Override
        public Class<TranslateBrowsePathsToNodeIdsRequest> getType() {
            return TranslateBrowsePathsToNodeIdsRequest.class;
        }

        @Override
        public TranslateBrowsePathsToNodeIdsRequest decode(SerializationContext context,
                                                           UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            BrowsePath[] browsePaths = (BrowsePath[]) decoder.readStructArray("BrowsePaths", BrowsePath.TYPE_ID);
            return new TranslateBrowsePathsToNodeIdsRequest(requestHeader, browsePaths);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           TranslateBrowsePathsToNodeIdsRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeStructArray("BrowsePaths", value.getBrowsePaths(), BrowsePath.TYPE_ID);
        }
    }
}
