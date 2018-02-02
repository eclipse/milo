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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

public class HistoryReadRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.HistoryReadRequest;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final ExtensionObject historyReadDetails;
    protected final TimestampsToReturn timestampsToReturn;
    protected final Boolean releaseContinuationPoints;
    protected final HistoryReadValueId[] nodesToRead;

    public HistoryReadRequest() {
        this.requestHeader = null;
        this.historyReadDetails = null;
        this.timestampsToReturn = null;
        this.releaseContinuationPoints = null;
        this.nodesToRead = null;
    }

    public HistoryReadRequest(RequestHeader requestHeader, ExtensionObject historyReadDetails, TimestampsToReturn timestampsToReturn, Boolean releaseContinuationPoints, HistoryReadValueId[] nodesToRead) {
        this.requestHeader = requestHeader;
        this.historyReadDetails = historyReadDetails;
        this.timestampsToReturn = timestampsToReturn;
        this.releaseContinuationPoints = releaseContinuationPoints;
        this.nodesToRead = nodesToRead;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public ExtensionObject getHistoryReadDetails() { return historyReadDetails; }

    public TimestampsToReturn getTimestampsToReturn() { return timestampsToReturn; }

    public Boolean getReleaseContinuationPoints() { return releaseContinuationPoints; }

    @Nullable
    public HistoryReadValueId[] getNodesToRead() { return nodesToRead; }

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
            .add("HistoryReadDetails", historyReadDetails)
            .add("TimestampsToReturn", timestampsToReturn)
            .add("ReleaseContinuationPoints", releaseContinuationPoints)
            .add("NodesToRead", nodesToRead)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<HistoryReadRequest> {

        @Override
        public Class<HistoryReadRequest> getType() {
            return HistoryReadRequest.class;
        }

        @Override
        public HistoryReadRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            ExtensionObject historyReadDetails = decoder.readExtensionObject("HistoryReadDetails");
            TimestampsToReturn timestampsToReturn = TimestampsToReturn.from(decoder.readInt32("TimestampsToReturn"));
            Boolean releaseContinuationPoints = decoder.readBoolean("ReleaseContinuationPoints");
            HistoryReadValueId[] nodesToRead =
                decoder.readBuiltinStructArray(
                    "NodesToRead",
                    HistoryReadValueId.class
                );

            return new HistoryReadRequest(requestHeader, historyReadDetails, timestampsToReturn, releaseContinuationPoints, nodesToRead);
        }

        @Override
        public void encode(HistoryReadRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeExtensionObject("HistoryReadDetails", value.historyReadDetails);
            encoder.writeInt32("TimestampsToReturn", value.timestampsToReturn != null ? value.timestampsToReturn.getValue() : 0);
            encoder.writeBoolean("ReleaseContinuationPoints", value.releaseContinuationPoints);
            encoder.writeBuiltinStructArray(
                "NodesToRead",
                value.nodesToRead,
                HistoryReadValueId.class
            );
        }
    }

}
