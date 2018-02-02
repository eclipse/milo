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
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class FindServersOnNetworkRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.FindServersOnNetworkRequest;
    public static final NodeId BinaryEncodingId = Identifiers.FindServersOnNetworkRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.FindServersOnNetworkRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger startingRecordId;
    protected final UInteger maxRecordsToReturn;
    protected final String[] serverCapabilityFilter;

    public FindServersOnNetworkRequest() {
        this.requestHeader = null;
        this.startingRecordId = null;
        this.maxRecordsToReturn = null;
        this.serverCapabilityFilter = null;
    }

    public FindServersOnNetworkRequest(RequestHeader requestHeader, UInteger startingRecordId, UInteger maxRecordsToReturn, String[] serverCapabilityFilter) {
        this.requestHeader = requestHeader;
        this.startingRecordId = startingRecordId;
        this.maxRecordsToReturn = maxRecordsToReturn;
        this.serverCapabilityFilter = serverCapabilityFilter;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public UInteger getStartingRecordId() { return startingRecordId; }

    public UInteger getMaxRecordsToReturn() { return maxRecordsToReturn; }

    @Nullable
    public String[] getServerCapabilityFilter() { return serverCapabilityFilter; }

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
            .add("StartingRecordId", startingRecordId)
            .add("MaxRecordsToReturn", maxRecordsToReturn)
            .add("ServerCapabilityFilter", serverCapabilityFilter)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<FindServersOnNetworkRequest> {

        @Override
        public Class<FindServersOnNetworkRequest> getType() {
            return FindServersOnNetworkRequest.class;
        }

        @Override
        public FindServersOnNetworkRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger startingRecordId = decoder.readUInt32("StartingRecordId");
            UInteger maxRecordsToReturn = decoder.readUInt32("MaxRecordsToReturn");
            String[] serverCapabilityFilter = decoder.readArray("ServerCapabilityFilter", decoder::readString, String.class);

            return new FindServersOnNetworkRequest(requestHeader, startingRecordId, maxRecordsToReturn, serverCapabilityFilter);
        }

        @Override
        public void encode(FindServersOnNetworkRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeUInt32("StartingRecordId", value.startingRecordId);
            encoder.writeUInt32("MaxRecordsToReturn", value.maxRecordsToReturn);
            encoder.writeArray("ServerCapabilityFilter", value.serverCapabilityFilter, encoder::writeString);
        }
    }

}
