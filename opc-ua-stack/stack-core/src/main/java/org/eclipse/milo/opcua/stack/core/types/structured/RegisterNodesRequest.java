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

public class RegisterNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RegisterNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final NodeId[] nodesToRegister;

    public RegisterNodesRequest() {
        this.requestHeader = null;
        this.nodesToRegister = null;
    }

    public RegisterNodesRequest(RequestHeader requestHeader, NodeId[] nodesToRegister) {
        this.requestHeader = requestHeader;
        this.nodesToRegister = nodesToRegister;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public NodeId[] getNodesToRegister() { return nodesToRegister; }

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
            .add("NodesToRegister", nodesToRegister)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RegisterNodesRequest> {

        @Override
        public Class<RegisterNodesRequest> getType() {
            return RegisterNodesRequest.class;
        }

        @Override
        public RegisterNodesRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            NodeId[] nodesToRegister = decoder.readArray("NodesToRegister", decoder::readNodeId, NodeId.class);

            return new RegisterNodesRequest(requestHeader, nodesToRegister);
        }

        @Override
        public void encode(RegisterNodesRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeArray("NodesToRegister", value.nodesToRegister, encoder::writeNodeId);
        }
    }

}
