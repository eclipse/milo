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
public class DeleteNodesItem extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=382");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=383");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=384");

    private final NodeId nodeId;

    private final Boolean deleteTargetReferences;

    public DeleteNodesItem(NodeId nodeId, Boolean deleteTargetReferences) {
        this.nodeId = nodeId;
        this.deleteTargetReferences = deleteTargetReferences;
    }

    @Override
    public ExpandedNodeId getTypeId() {
        return TYPE_ID;
    }

    @Override
    public ExpandedNodeId getXmlEncodingId() {
        return XML_ENCODING_ID;
    }

    @Override
    public ExpandedNodeId getBinaryEncodingId() {
        return BINARY_ENCODING_ID;
    }

    public NodeId getNodeId() {
        return nodeId;
    }

    public Boolean getDeleteTargetReferences() {
        return deleteTargetReferences;
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteNodesItem> {
        @Override
        public Class<DeleteNodesItem> getType() {
            return DeleteNodesItem.class;
        }

        @Override
        public DeleteNodesItem decode(SerializationContext context, UaDecoder decoder) {
            NodeId nodeId = decoder.readNodeId("NodeId");
            Boolean deleteTargetReferences = decoder.readBoolean("DeleteTargetReferences");
            return new DeleteNodesItem(nodeId, deleteTargetReferences);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, DeleteNodesItem value) {
            encoder.writeNodeId("NodeId", value.getNodeId());
            encoder.writeBoolean("DeleteTargetReferences", value.getDeleteTargetReferences());
        }
    }
}
