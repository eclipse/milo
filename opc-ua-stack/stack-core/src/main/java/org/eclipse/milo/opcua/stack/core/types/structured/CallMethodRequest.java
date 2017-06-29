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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

public class CallMethodRequest implements UaStructure {

    public static final NodeId TypeId = Identifiers.CallMethodRequest;
    public static final NodeId BinaryEncodingId = Identifiers.CallMethodRequest_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.CallMethodRequest_Encoding_DefaultXml;

    protected final NodeId objectId;
    protected final NodeId methodId;
    protected final Variant[] inputArguments;

    public CallMethodRequest() {
        this.objectId = null;
        this.methodId = null;
        this.inputArguments = null;
    }

    public CallMethodRequest(NodeId objectId, NodeId methodId, Variant[] inputArguments) {
        this.objectId = objectId;
        this.methodId = methodId;
        this.inputArguments = inputArguments;
    }

    public NodeId getObjectId() { return objectId; }

    public NodeId getMethodId() { return methodId; }

    @Nullable
    public Variant[] getInputArguments() { return inputArguments; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ObjectId", objectId)
            .add("MethodId", methodId)
            .add("InputArguments", inputArguments)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<CallMethodRequest> {

        @Override
        public Class<CallMethodRequest> getType() {
            return CallMethodRequest.class;
        }

        @Override
        public CallMethodRequest decode(UaDecoder decoder) throws UaSerializationException {
            NodeId objectId = decoder.readNodeId("ObjectId");
            NodeId methodId = decoder.readNodeId("MethodId");
            Variant[] inputArguments = decoder.readArray("InputArguments", decoder::readVariant, Variant.class);

            return new CallMethodRequest(objectId, methodId, inputArguments);
        }

        @Override
        public void encode(CallMethodRequest value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("ObjectId", value.objectId);
            encoder.writeNodeId("MethodId", value.methodId);
            encoder.writeArray("InputArguments", value.inputArguments, encoder::writeVariant);
        }
    }

}
