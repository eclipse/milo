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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@UaDataType("CallMethodRequest")
public class CallMethodRequest implements UaStructure {

    public static final NodeId TypeId = Identifiers.CallMethodRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CallMethodRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CallMethodRequest_Encoding_DefaultXml;

    protected final NodeId _objectId;
    protected final NodeId _methodId;
    protected final Variant[] _inputArguments;

    public CallMethodRequest() {
        this._objectId = null;
        this._methodId = null;
        this._inputArguments = null;
    }

    public CallMethodRequest(NodeId _objectId, NodeId _methodId, Variant[] _inputArguments) {
        this._objectId = _objectId;
        this._methodId = _methodId;
        this._inputArguments = _inputArguments;
    }

    public NodeId getObjectId() { return _objectId; }

    public NodeId getMethodId() { return _methodId; }

    @Nullable
    public Variant[] getInputArguments() { return _inputArguments; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ObjectId", _objectId)
            .add("MethodId", _methodId)
            .add("InputArguments", _inputArguments)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<CallMethodRequest> {
        @Override
        public CallMethodRequest decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _objectId = reader.readNodeId();
            NodeId _methodId = reader.readNodeId();
            Variant[] _inputArguments = reader.readArray(reader::readVariant, Variant.class);

            return new CallMethodRequest(_objectId, _methodId, _inputArguments);
        }

        @Override
        public void encode(SerializationContext context, CallMethodRequest encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._objectId);
            writer.writeNodeId(encodable._methodId);
            writer.writeArray(encodable._inputArguments, writer::writeVariant);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<CallMethodRequest> {
        @Override
        public CallMethodRequest decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _objectId = reader.readNodeId("ObjectId");
            NodeId _methodId = reader.readNodeId("MethodId");
            Variant[] _inputArguments = reader.readArray("InputArguments", reader::readVariant, Variant.class);

            return new CallMethodRequest(_objectId, _methodId, _inputArguments);
        }

        @Override
        public void encode(SerializationContext context, CallMethodRequest encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("ObjectId", encodable._objectId);
            writer.writeNodeId("MethodId", encodable._methodId);
            writer.writeArray("InputArguments", encodable._inputArguments, writer::writeVariant);
        }
    }

}
