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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ByteString;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExtensionObject;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.StatusCode;

public class HistoryReadResult implements UaStructure {

    public static final NodeId TypeId = Identifiers.HistoryReadResult;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryReadResult_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryReadResult_Encoding_DefaultXml;

    protected final StatusCode statusCode;
    protected final ByteString continuationPoint;
    protected final ExtensionObject historyData;

    public HistoryReadResult() {
        this.statusCode = null;
        this.continuationPoint = null;
        this.historyData = null;
    }

    public HistoryReadResult(StatusCode statusCode, ByteString continuationPoint, ExtensionObject historyData) {
        this.statusCode = statusCode;
        this.continuationPoint = continuationPoint;
        this.historyData = historyData;
    }

    public StatusCode getStatusCode() { return statusCode; }

    public ByteString getContinuationPoint() { return continuationPoint; }

    public ExtensionObject getHistoryData() { return historyData; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("StatusCode", statusCode)
            .add("ContinuationPoint", continuationPoint)
            .add("HistoryData", historyData)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<HistoryReadResult> {

        @Override
        public Class<HistoryReadResult> getType() {
            return HistoryReadResult.class;
        }

        @Override
        public HistoryReadResult decode(UaDecoder decoder) throws UaSerializationException {
            StatusCode statusCode = decoder.readStatusCode("StatusCode");
            ByteString continuationPoint = decoder.readByteString("ContinuationPoint");
            ExtensionObject historyData = decoder.readExtensionObject("HistoryData");

            return new HistoryReadResult(statusCode, continuationPoint, historyData);
        }

        @Override
        public void encode(HistoryReadResult value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeStatusCode("StatusCode", value.statusCode);
            encoder.writeByteString("ContinuationPoint", value.continuationPoint);
            encoder.writeExtensionObject("HistoryData", value.historyData);
        }
    }

}
