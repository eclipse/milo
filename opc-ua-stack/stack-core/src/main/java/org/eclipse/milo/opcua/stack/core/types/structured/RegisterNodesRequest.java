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

@UaDataType("RegisterNodesRequest")
public class RegisterNodesRequest implements UaRequestMessage {

    public static final NodeId TypeId = Identifiers.RegisterNodesRequest;
    public static final NodeId BinaryEncodingId = Identifiers.RegisterNodesRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.RegisterNodesRequest_Encoding_DefaultXml;

    protected final RequestHeader _requestHeader;
    protected final NodeId[] _nodesToRegister;

    public RegisterNodesRequest() {
        this._requestHeader = null;
        this._nodesToRegister = null;
    }

    public RegisterNodesRequest(RequestHeader _requestHeader, NodeId[] _nodesToRegister) {
        this._requestHeader = _requestHeader;
        this._nodesToRegister = _nodesToRegister;
    }

    public RequestHeader getRequestHeader() { return _requestHeader; }

    @Nullable
    public NodeId[] getNodesToRegister() { return _nodesToRegister; }

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
            .add("NodesToRegister", _nodesToRegister)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<RegisterNodesRequest> {
        @Override
        public RegisterNodesRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.BinaryEncodingId, reader);
            NodeId[] _nodesToRegister = reader.readArray(reader::readNodeId, NodeId.class);

            return new RegisterNodesRequest(_requestHeader, _nodesToRegister);
        }

        @Override
        public void encode(SerializationContext context, RegisterNodesRequest value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.BinaryEncodingId, value._requestHeader, writer);
            writer.writeArray(value._nodesToRegister, writer::writeNodeId);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<RegisterNodesRequest> {
        @Override
        public RegisterNodesRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            RequestHeader _requestHeader = (RequestHeader) context.decode(RequestHeader.XmlEncodingId, reader);
            NodeId[] _nodesToRegister = reader.readArray("NodesToRegister", reader::readNodeId, NodeId.class);

            return new RegisterNodesRequest(_requestHeader, _nodesToRegister);
        }

        @Override
        public void encode(SerializationContext context, RegisterNodesRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            context.encode(RequestHeader.XmlEncodingId, encodable._requestHeader, writer);
            writer.writeArray("NodesToRegister", encodable._nodesToRegister, writer::writeNodeId);
        }
    }

}
