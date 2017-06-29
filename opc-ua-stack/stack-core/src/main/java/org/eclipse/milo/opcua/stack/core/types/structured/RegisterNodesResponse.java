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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class RegisterNodesResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.RegisterNodesResponse;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterNodesResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterNodesResponse_Encoding_DefaultXml;

    protected final ResponseHeader responseHeader;
    protected final NodeId[] registeredNodeIds;

    public RegisterNodesResponse() {
        this.responseHeader = null;
        this.registeredNodeIds = null;
    }

    public RegisterNodesResponse(ResponseHeader responseHeader, NodeId[] registeredNodeIds) {
        this.responseHeader = responseHeader;
        this.registeredNodeIds = registeredNodeIds;
    }

    public ResponseHeader getResponseHeader() { return responseHeader; }

    @Nullable
    public NodeId[] getRegisteredNodeIds() { return registeredNodeIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", responseHeader)
            .add("RegisteredNodeIds", registeredNodeIds)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<RegisterNodesResponse> {

        @Override
        public Class<RegisterNodesResponse> getType() {
            return RegisterNodesResponse.class;
        }

        @Override
        public RegisterNodesResponse decode(UaDecoder decoder) throws UaSerializationException {
            ResponseHeader responseHeader = (ResponseHeader) decoder.readBuiltinStruct("ResponseHeader", ResponseHeader.class);
            NodeId[] registeredNodeIds = decoder.readArray("RegisteredNodeIds", decoder::readNodeId, NodeId.class);

            return new RegisterNodesResponse(responseHeader, registeredNodeIds);
        }

        @Override
        public void encode(RegisterNodesResponse value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("ResponseHeader", value.responseHeader, ResponseHeader.class);
            encoder.writeArray("RegisteredNodeIds", value.registeredNodeIds, encoder::writeNodeId);
        }
    }

}
