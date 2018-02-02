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

public class CloseSessionRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.CloseSessionRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CloseSessionRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CloseSessionRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final Boolean deleteSubscriptions;

    public CloseSessionRequest() {
        this.requestHeader = null;
        this.deleteSubscriptions = null;
    }

    public CloseSessionRequest(RequestHeader requestHeader, Boolean deleteSubscriptions) {
        this.requestHeader = requestHeader;
        this.deleteSubscriptions = deleteSubscriptions;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    public Boolean getDeleteSubscriptions() { return deleteSubscriptions; }

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
            .add("DeleteSubscriptions", deleteSubscriptions)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CloseSessionRequest> {

        @Override
        public Class<CloseSessionRequest> getType() {
            return CloseSessionRequest.class;
        }

        @Override
        public CloseSessionRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            Boolean deleteSubscriptions = decoder.readBoolean("DeleteSubscriptions");

            return new CloseSessionRequest(requestHeader, deleteSubscriptions);
        }

        @Override
        public void encode(CloseSessionRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeBoolean("DeleteSubscriptions", value.deleteSubscriptions);
        }
    }

}
