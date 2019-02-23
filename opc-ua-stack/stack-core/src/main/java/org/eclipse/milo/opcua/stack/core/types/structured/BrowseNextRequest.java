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

import javax.annotation.Nullable;

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class BrowseNextRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.BrowseNextRequest;
    public static final NodeId BinaryEncodingId = Identifiers.BrowseNextRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.BrowseNextRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final Boolean releaseContinuationPoints;
    protected final ByteString[] continuationPoints;

    public BrowseNextRequest() {
        this.requestHeader = null;
        this.releaseContinuationPoints = null;
        this.continuationPoints = null;
    }

    public BrowseNextRequest(RequestHeader requestHeader, Boolean releaseContinuationPoints, ByteString[] continuationPoints) {
        this.requestHeader = requestHeader;
        this.releaseContinuationPoints = releaseContinuationPoints;
        this.continuationPoints = continuationPoints;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public Boolean getReleaseContinuationPoints() { return releaseContinuationPoints; }

    @Nullable
    public ByteString[] getContinuationPoints() { return continuationPoints; }

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
            .add("ReleaseContinuationPoints", releaseContinuationPoints)
            .add("ContinuationPoints", continuationPoints)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<BrowseNextRequest> {

        @Override
        public Class<BrowseNextRequest> getType() {
            return BrowseNextRequest.class;
        }

        @Override
        public BrowseNextRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            Boolean releaseContinuationPoints = decoder.readBoolean("ReleaseContinuationPoints");
            ByteString[] continuationPoints = decoder.readArray("ContinuationPoints", decoder::readByteString, ByteString.class);

            return new BrowseNextRequest(requestHeader, releaseContinuationPoints, continuationPoints);
        }

        @Override
        public void encode(BrowseNextRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBoolean("ReleaseContinuationPoints", value.releaseContinuationPoints);
            encoder.writeArray("ContinuationPoints", value.continuationPoints, encoder::writeByteString);
        }
    }

}
