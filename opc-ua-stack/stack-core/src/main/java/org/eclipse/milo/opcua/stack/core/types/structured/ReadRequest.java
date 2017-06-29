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
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

public class ReadRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.ReadRequest;
    public static final NodeId BinaryEncodingId = Identifiers.ReadRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReadRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final Double maxAge;
    protected final TimestampsToReturn timestampsToReturn;
    protected final ReadValueId[] nodesToRead;

    public ReadRequest() {
        this.requestHeader = null;
        this.maxAge = null;
        this.timestampsToReturn = null;
        this.nodesToRead = null;
    }

    public ReadRequest(RequestHeader requestHeader, Double maxAge, TimestampsToReturn timestampsToReturn, ReadValueId[] nodesToRead) {
        this.requestHeader = requestHeader;
        this.maxAge = maxAge;
        this.timestampsToReturn = timestampsToReturn;
        this.nodesToRead = nodesToRead;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public Double getMaxAge() { return maxAge; }

    public TimestampsToReturn getTimestampsToReturn() { return timestampsToReturn; }

    @Nullable
    public ReadValueId[] getNodesToRead() { return nodesToRead; }

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
            .add("MaxAge", maxAge)
            .add("TimestampsToReturn", timestampsToReturn)
            .add("NodesToRead", nodesToRead)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<ReadRequest> {

        @Override
        public Class<ReadRequest> getType() {
            return ReadRequest.class;
        }

        @Override
        public ReadRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            Double maxAge = decoder.readDouble("MaxAge");
            TimestampsToReturn timestampsToReturn = TimestampsToReturn.from(decoder.readInt32("TimestampsToReturn"));
            ReadValueId[] nodesToRead =
                decoder.readBuiltinStructArray(
                    "NodesToRead",
                    ReadValueId.class
                );

            return new ReadRequest(requestHeader, maxAge, timestampsToReturn, nodesToRead);
        }

        @Override
        public void encode(ReadRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeDouble("MaxAge", value.maxAge);
            encoder.writeInt32("TimestampsToReturn", value.timestampsToReturn != null ? value.timestampsToReturn.getValue() : 0);
            encoder.writeBuiltinStructArray(
                "NodesToRead",
                value.nodesToRead,
                ReadValueId.class
            );
        }
    }

}
