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
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class HistoryReadRequest extends Structure implements UaRequestMessage {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=662");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=664");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=663");

    private final RequestHeader requestHeader;

    private final ExtensionObject historyReadDetails;

    private final TimestampsToReturn timestampsToReturn;

    private final Boolean releaseContinuationPoints;

    private final HistoryReadValueId[] nodesToRead;

    public HistoryReadRequest(RequestHeader requestHeader, ExtensionObject historyReadDetails,
                              TimestampsToReturn timestampsToReturn, Boolean releaseContinuationPoints,
                              HistoryReadValueId[] nodesToRead) {
        this.requestHeader = requestHeader;
        this.historyReadDetails = historyReadDetails;
        this.timestampsToReturn = timestampsToReturn;
        this.releaseContinuationPoints = releaseContinuationPoints;
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

    public ExtensionObject getHistoryReadDetails() {
        return historyReadDetails;
    }

    public TimestampsToReturn getTimestampsToReturn() {
        return timestampsToReturn;
    }

    public Boolean getReleaseContinuationPoints() {
        return releaseContinuationPoints;
    }

    public HistoryReadValueId[] getNodesToRead() {
        return nodesToRead;
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryReadRequest> {
        @Override
        public Class<HistoryReadRequest> getType() {
            return HistoryReadRequest.class;
        }

        @Override
        public HistoryReadRequest decode(SerializationContext context, UaDecoder decoder) {
            RequestHeader requestHeader = (RequestHeader) decoder.readStruct("RequestHeader", RequestHeader.TYPE_ID);
            ExtensionObject historyReadDetails = decoder.readExtensionObject("HistoryReadDetails");
            TimestampsToReturn timestampsToReturn = decoder.readEnum("TimestampsToReturn", TimestampsToReturn.class);
            Boolean releaseContinuationPoints = decoder.readBoolean("ReleaseContinuationPoints");
            HistoryReadValueId[] nodesToRead = (HistoryReadValueId[]) decoder.readStructArray("NodesToRead", HistoryReadValueId.TYPE_ID);
            return new HistoryReadRequest(requestHeader, historyReadDetails, timestampsToReturn, releaseContinuationPoints, nodesToRead);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, HistoryReadRequest value) {
            encoder.writeStruct("RequestHeader", value.getRequestHeader(), RequestHeader.TYPE_ID);
            encoder.writeExtensionObject("HistoryReadDetails", value.getHistoryReadDetails());
            encoder.writeEnum("TimestampsToReturn", value.getTimestampsToReturn());
            encoder.writeBoolean("ReleaseContinuationPoints", value.getReleaseContinuationPoints());
            encoder.writeStructArray("NodesToRead", value.getNodesToRead(), HistoryReadValueId.TYPE_ID);
        }
    }
}
