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

public class CloseSecureChannelRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CloseSecureChannelRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CloseSecureChannelRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CloseSecureChannelRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;

    public CloseSecureChannelRequest() {
        this.requestHeader = null;
    }

    public CloseSecureChannelRequest(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

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
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CloseSecureChannelRequest> {

        @Override
        public Class<CloseSecureChannelRequest> getType() {
            return CloseSecureChannelRequest.class;
        }

        @Override
        public CloseSecureChannelRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);

            return new CloseSecureChannelRequest(requestHeader);
        }

        @Override
        public void encode(CloseSecureChannelRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
        }
    }

}
