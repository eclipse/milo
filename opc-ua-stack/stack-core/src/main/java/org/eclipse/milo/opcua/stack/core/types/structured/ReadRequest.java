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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class ReadRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=629");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=631");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=630");

    private final RequestHeader requestHeader;

    private final Double maxAge;

    private final TimestampsToReturn timestampsToReturn;

    private final ReadValueId[] nodesToRead;

    public ReadRequest(RequestHeader requestHeader, Double maxAge,
                       TimestampsToReturn timestampsToReturn, ReadValueId[] nodesToRead) {
        this.requestHeader = requestHeader;
        this.maxAge = maxAge;
        this.timestampsToReturn = timestampsToReturn;
        this.nodesToRead = nodesToRead;
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

    public Double getMaxAge() {
        return maxAge;
    }

    public TimestampsToReturn getTimestampsToReturn() {
        return timestampsToReturn;
    }

    public ReadValueId[] getNodesToRead() {
        return nodesToRead;
    }

    public static final class Codec extends GenericDataTypeCodec<ReadRequest> {
        @Override
        public Class<ReadRequest> getType() {
            return ReadRequest.class;
        }

        @Override
        public ReadRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            Double maxAge = decoder.readDouble("MaxAge");
            TimestampsToReturn timestampsToReturn = decoder.readEnum("TimestampsToReturn", TimestampsToReturn.class);
            ReadValueId[] nodesToRead = (ReadValueId[]) decoder.readStructArray("NodesToRead", ReadValueId.TYPE_ID);
            return new ReadRequest(requestHeader, maxAge, timestampsToReturn, nodesToRead);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ReadRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeDouble("MaxAge", value.getMaxAge());
            encoder.writeEnum("TimestampsToReturn", value.getTimestampsToReturn());
            encoder.writeStructArray("NodesToRead", value.getNodesToRead(), ReadValueId.TYPE_ID);
        }
    }
}
