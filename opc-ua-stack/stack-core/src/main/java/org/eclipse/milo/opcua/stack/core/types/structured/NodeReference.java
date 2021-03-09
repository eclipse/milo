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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.GenericDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class NodeReference extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=580");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=582");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=581");

    public static final ExpandedNodeId JSON_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=15203");

    private final NodeId nodeId;

    private final NodeId referenceTypeId;

    private final Boolean isForward;

    private final NodeId[] referencedNodeIds;

    public NodeReference(NodeId nodeId, NodeId referenceTypeId, Boolean isForward,
                         NodeId[] referencedNodeIds) {
        this.nodeId = nodeId;
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.referencedNodeIds = referencedNodeIds;
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

    public NodeId getNodeId() {
        return nodeId;
    }

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public Boolean getIsForward() {
        return isForward;
    }

    public NodeId[] getReferencedNodeIds() {
        return referencedNodeIds;
    }

    public static final class Codec extends GenericDataTypeCodec<NodeReference> {
        @Override
        public Class<NodeReference> getType() {
            return NodeReference.class;
        }

        @Override
        public NodeReference decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            NodeId[] referencedNodeIds = decoder.readNodeIdArray("ReferencedNodeIds");
            return new NodeReference(nodeId, referenceTypeId, isForward, referencedNodeIds);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, NodeReference value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeBoolean("IsForward", value.getIsForward());
            encoder.writeNodeIdArray("ReferencedNodeIds", value.getReferencedNodeIds());
        }
    }
}
