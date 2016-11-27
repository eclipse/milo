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
import org.eclipse.milo.opcua.stack.core.serialization.UaResponseMessage;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("RegisterNodesResponse")
public class RegisterNodesResponse implements UaResponseMessage {

    public static final NodeId TypeId = Identifiers.RegisterNodesResponse;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterNodesResponse_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterNodesResponse_Encoding_DefaultXml;

    protected final ResponseHeader _responseHeader;
    protected final NodeId[] _registeredNodeIds;

    public RegisterNodesResponse() {
        this._responseHeader = null;
        this._registeredNodeIds = null;
    }

    public RegisterNodesResponse(ResponseHeader _responseHeader, NodeId[] _registeredNodeIds) {
        this._responseHeader = _responseHeader;
        this._registeredNodeIds = _registeredNodeIds;
    }

    public ResponseHeader getResponseHeader() { return _responseHeader; }

    @Nullable
    public NodeId[] getRegisteredNodeIds() { return _registeredNodeIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ResponseHeader", _responseHeader)
            .add("RegisteredNodeIds", _registeredNodeIds)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RegisterNodesResponse> {
        @Override
        public RegisterNodesResponse decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            NodeId[] _registeredNodeIds = reader.readArray(reader::readNodeId, NodeId.class);

            return new RegisterNodesResponse(_responseHeader, _registeredNodeIds);
        }

        @Override
        public void encode(SerializationContext context, RegisterNodesResponse encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeArray(encodable._registeredNodeIds, writer::writeNodeId);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RegisterNodesResponse> {
        @Override
        public RegisterNodesResponse decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            ResponseHeader _responseHeader = (ResponseHeader) context.decode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", reader);
            NodeId[] _registeredNodeIds = reader.readArray("RegisteredNodeIds", reader::readNodeId, NodeId.class);

            return new RegisterNodesResponse(_responseHeader, _registeredNodeIds);
        }

        @Override
        public void encode(SerializationContext context, RegisterNodesResponse encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(OpcUaDataTypeManager.BINARY_NAMESPACE_URI, "ResponseHeader", encodable._responseHeader, writer);
            writer.writeArray("RegisteredNodeIds", encodable._registeredNodeIds, writer::writeNodeId);
        }
    }

}
