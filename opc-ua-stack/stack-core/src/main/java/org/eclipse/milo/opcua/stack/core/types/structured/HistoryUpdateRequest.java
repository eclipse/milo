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

public class HistoryUpdateRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.HistoryUpdateRequest;
    public static final NodeId BinaryEncodingId = Identifiers.HistoryUpdateRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.HistoryUpdateRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final ExtensionObject[] historyUpdateDetails;

    public HistoryUpdateRequest() {
        this.requestHeader = null;
        this.historyUpdateDetails = null;
    }

    public HistoryUpdateRequest(RequestHeader requestHeader, ExtensionObject[] historyUpdateDetails) {
        this.requestHeader = requestHeader;
        this.historyUpdateDetails = historyUpdateDetails;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public ExtensionObject[] getHistoryUpdateDetails() { return historyUpdateDetails; }

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
            .add("HistoryUpdateDetails", historyUpdateDetails)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<HistoryUpdateRequest> {

        @Override
        public Class<HistoryUpdateRequest> getType() {
            return HistoryUpdateRequest.class;
        }

        @Override
        public HistoryUpdateRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            ExtensionObject[] historyUpdateDetails = decoder.readArray("HistoryUpdateDetails", decoder::readExtensionObject, ExtensionObject.class);

            return new HistoryUpdateRequest(requestHeader, historyUpdateDetails);
        }

        @Override
        public void encode(HistoryUpdateRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeArray("HistoryUpdateDetails", value.historyUpdateDetails, encoder::writeExtensionObject);
        }
    }

}
