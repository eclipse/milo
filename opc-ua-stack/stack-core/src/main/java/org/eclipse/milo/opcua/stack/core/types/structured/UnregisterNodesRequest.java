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

public class UnregisterNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.UnregisterNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.UnregisterNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UnregisterNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader requestHeader;
    protected final NodeId[] nodesToUnregister;

    public UnregisterNodesRequest() {
        this.requestHeader = null;
        this.nodesToUnregister = null;
    }

    public UnregisterNodesRequest(RequestHeader requestHeader, NodeId[] nodesToUnregister) {
        this.requestHeader = requestHeader;
        this.nodesToUnregister = nodesToUnregister;
    }

    public RequestHeader getRequestHeader() { return requestHeader; }

    @Nullable
    public NodeId[] getNodesToUnregister() { return nodesToUnregister; }

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
            .add("NodesToUnregister", nodesToUnregister)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<UnregisterNodesRequest> {

        @Override
        public Class<UnregisterNodesRequest> getType() {
            return UnregisterNodesRequest.class;
        }

        @Override
        public UnregisterNodesRequest decode(UaDecoder decoder) throws UaSerializationException {
            RequestHeader requestHeader = (RequestHeader) decoder.readBuiltinStruct("RequestHeader", RequestHeader.class);
            NodeId[] nodesToUnregister = decoder.readArray("NodesToUnregister", decoder::readNodeId, NodeId.class);

            return new UnregisterNodesRequest(requestHeader, nodesToUnregister);
        }

        @Override
        public void encode(UnregisterNodesRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeBuiltinStruct("RequestHeader", value.requestHeader, RequestHeader.class);
            encoder.writeArray("NodesToUnregister", value.nodesToUnregister, encoder::writeNodeId);
        }
    }

}
