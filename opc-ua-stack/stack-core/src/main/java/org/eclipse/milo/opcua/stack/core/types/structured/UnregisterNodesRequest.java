/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.OpcUaDataTypeManager;
import org.eclipse.milo.opcua.stack.core.serialization.UaRequestMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("UnregisterNodesRequest")
public class UnregisterNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.UnregisterNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.UnregisterNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.UnregisterNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final NodeId[] _nodesToUnregister;

    public UnregisterNodesRequest() {
        this._requestHeader = null;
        this._nodesToUnregister = null;
    }

    public UnregisterNodesRequest(RequestHeader _requestHeader, NodeId[] _nodesToUnregister) {
        this._requestHeader = _requestHeader;
        this._nodesToUnregister = _nodesToUnregister;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public NodeId[] getNodesToUnregister() { return _nodesToUnregister; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("RequestHeader", _requestHeader)
            .add("NodesToUnregister", _nodesToUnregister)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<UnregisterNodesRequest> {
        @Override
        public UnregisterNodesRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            NodeId[] _nodesToUnregister = reader.readArray(reader::readNodeId, NodeId.class);

            return new UnregisterNodesRequest(_requestHeader, _nodesToUnregister);
        }

        @Override
        public void encode(SerializationContext context, UnregisterNodesRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeArray(encodable._nodesToUnregister, writer::writeNodeId);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<UnregisterNodesRequest> {
        @Override
        public UnregisterNodesRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", reader);
            NodeId[] _nodesToUnregister = reader.readArray("NodesToUnregister", reader::readNodeId, NodeId.class);

            return new UnregisterNodesRequest(_requestHeader, _nodesToUnregister);
        }

        @Override
        public void encode(SerializationContext context, UnregisterNodesRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "RequestHeader", encodable._requestHeader, writer);
            writer.writeArray("NodesToUnregister", encodable._nodesToUnregister, writer::writeNodeId);
        }
    }

}
