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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class TranslateBrowsePathsToNodeIdsRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.TranslateBrowsePathsToNodeIdsRequest;
    public static final NodeId BinaryEncodingId = Identifiers.TranslateBrowsePathsToNodeIdsRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.TranslateBrowsePathsToNodeIdsRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final BrowsePath[] browsePaths;

    public TranslateBrowsePathsToNodeIdsRequest() {
        this.requestHeader = null;
        this.browsePaths = null;
    }

    public TranslateBrowsePathsToNodeIdsRequest(RequestHeader requestHeader, BrowsePath[] browsePaths) {
        this.requestHeader = requestHeader;
        this.browsePaths = browsePaths;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public BrowsePath[] getBrowsePaths() { return browsePaths; }

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
            .add("BrowsePaths", browsePaths)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<TranslateBrowsePathsToNodeIdsRequest> {

        @Override
        public Class<TranslateBrowsePathsToNodeIdsRequest> getType() {
            return TranslateBrowsePathsToNodeIdsRequest.class;
        }

        @Override
        public TranslateBrowsePathsToNodeIdsRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            BrowsePath[] browsePaths =
                decoder.readBuiltinStructArray(
                    "BrowsePaths",
                    BrowsePath.class
                );

            return new TranslateBrowsePathsToNodeIdsRequest(requestHeader, browsePaths);
        }

        @Override
        public void encode(TranslateBrowsePathsToNodeIdsRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBuiltinStructArray(
                "BrowsePaths",
                value.browsePaths,
                BrowsePath.class
            );
        }
    }

}
