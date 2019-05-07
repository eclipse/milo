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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class HistoryReadResult extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=638");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=640");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=639");

    private final StatusCode statusCode;

    private final ByteString continuationPoint;

    private final ExtensionObject historyData;

    public HistoryReadResult(StatusCode statusCode, ByteString continuationPoint,
                             ExtensionObject historyData) {
        this.statusCode = statusCode;
        this.continuationPoint = continuationPoint;
        this.historyData = historyData;
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

    public StatusCode getStatusCode() {
        return statusCode;
    }

    public ByteString getContinuationPoint() {
        return continuationPoint;
    }

    public ExtensionObject getHistoryData() {
        return historyData;
    }

    public static final class Codec extends GenericDataTypeCodec<HistoryReadResult> {
        @Override
        public Class<HistoryReadResult> getType() {
            return HistoryReadResult.class;
        }

        @Override
        public HistoryReadResult decode(SerializationContext context, UaDecoder decoder) {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");
            ExtensionObject historyData = decoder.readExtensionObject("HistoryData");
            return new HistoryReadResult(statusCode, continuationPoint, historyData);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, HistoryReadResult value) {
            encoder.writeStatusCode("StatusCode", value.getStatusCode());
            encoder.writeByteString("ContinuationPoint", value.getContinuationPoint());
            encoder.writeExtensionObject("HistoryData", value.getHistoryData());
        }
    }
}
