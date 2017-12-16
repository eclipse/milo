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
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.UInteger;

public class CancelRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CancelRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CancelRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CancelRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final UInteger requestHandle;

    public CancelRequest() {
        this.requestHeader = null;
        this.requestHandle = null;
    }

    public CancelRequest(RequestHeader requestHeader, UInteger requestHandle) {
        this.requestHeader = requestHeader;
        this.requestHandle = requestHandle;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public UInteger getRequestHandle() { return requestHandle; }

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
            .add("RequestHandle", requestHandle)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CancelRequest> {

        @Override
        public Class<CancelRequest> getType() {
            return CancelRequest.class;
        }

        @Override
        public CancelRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            UInteger requestHandle = decoder.readUInt32("RequestHandle");

            return new CancelRequest(requestHeader, requestHandle);
        }

        @Override
        public void encode(CancelRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeUInt32("RequestHandle", value.requestHandle);
        }
    }

}
