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

public class NodeReference implements UaStructure {

    public static final NodeId TypeId = Identifiers.NodeReference;
    public static final NodeId BinaryEncodingId = Identifiers.NodeReference_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NodeReference_Encoding_DefaultXml;

    protected final NodeId nodeId;
    protected final NodeId referenceTypeId;
    protected final Boolean isForward;
    protected final NodeId[] referencedNodeIds;

    public NodeReference() {
        this.nodeId = null;
        this.referenceTypeId = null;
        this.isForward = null;
        this.referencedNodeIds = null;
    }

    public NodeReference(NodeId nodeId, NodeId referenceTypeId, Boolean isForward, NodeId[] referencedNodeIds) {
        this.nodeId = nodeId;
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.referencedNodeIds = referencedNodeIds;
    }

    public NodeId getNodeId() { return nodeId; }

    public NodeId getReferenceTypeId() { return referenceTypeId; }

    public Boolean getIsForward() { return isForward; }

    @Nullable
    public NodeId[] getReferencedNodeIds() { return referencedNodeIds; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", nodeId)
            .add("ReferenceTypeId", referenceTypeId)
            .add("IsForward", isForward)
            .add("ReferencedNodeIds", referencedNodeIds)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<NodeReference> {

        @Override
        public Class<NodeReference> getType() {
            return NodeReference.class;
        }

        @Override
        public NodeReference decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            NodeId[] referencedNodeIds = decoder.readArray("ReferencedNodeIds", decoder::readNodeId, NodeId.class);

            return new NodeReference(nodeId, referenceTypeId, isForward, referencedNodeIds);
        }

        @Override
        public void encode(NodeReference value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeNodeId("ReferenceTypeId", value.referenceTypeId);
            encoder.writeBoolean("IsForward", value.isForward);
            encoder.writeArray("ReferencedNodeIds", value.referencedNodeIds, encoder::writeNodeId);
        }
    }

}
