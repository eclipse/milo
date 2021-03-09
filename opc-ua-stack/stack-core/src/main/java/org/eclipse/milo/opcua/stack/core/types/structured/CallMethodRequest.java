/*
 * Copyright (c) 2019 the Eclipse Milo Authors
 *
 * This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 */

package org.eclipse.milo.opcua.stack.core.types.structured;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import org.eclipse.milo.opcua.stack.core.serialization.SerializationContext;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.Variant;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class CallMethodRequest extends Structure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=704");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=706");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=705");

    private final NodeId objectId;

    private final NodeId methodId;

    private final Variant[] inputArguments;

    public CallMethodRequest(NodeId objectId, NodeId methodId, Variant[] inputArguments) {
        this.objectId = objectId;
        this.methodId = methodId;
        this.inputArguments = inputArguments;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    public NodeId getObjectId() {
        return objectId;
    }

    public NodeId getMethodId() {
        return methodId;
    }

    public Variant[] getInputArguments() {
        return inputArguments;
    }

    public static final class Codec extends GenericDataTypeCodec<CallMethodRequest> {
        @Override
        public Class<CallMethodRequest> getType() {
            return CallMethodRequest.class;
        }

        @Override
        public CallMethodRequest decode(SerializationContext context, UaDecoder decoder) {
            NodeId objectId = decoder.readNodeId("ObjectId");
            NodeId methodId = decoder.readNodeId("MethodId");
            Variant[] inputArguments = decoder.readVariantArray("InputArguments");
            return new CallMethodRequest(objectId, methodId, inputArguments);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, CallMethodRequest value) {
            encoder.writeNodeId("ObjectId", value.getObjectId());
            encoder.writeNodeId("MethodId", value.getMethodId());
            encoder.writeVariantArray("InputArguments", value.getInputArguments());
        }
    }
}
