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
public class DeleteReferencesItem extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=385");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=386");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=387");

    private final NodeId sourceNodeId;

    private final NodeId referenceTypeId;

    private final Boolean isForward;

    private final ExpandedNodeId targetNodeId;

    private final Boolean deleteBidirectional;

    public DeleteReferencesItem(NodeId sourceNodeId, NodeId referenceTypeId, Boolean isForward,
                                ExpandedNodeId targetNodeId, Boolean deleteBidirectional) {
        this.sourceNodeId = sourceNodeId;
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.targetNodeId = targetNodeId;
        this.deleteBidirectional = deleteBidirectional;
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

    public NodeId getSourceNodeId() {
        return sourceNodeId;
    }

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public Boolean getIsForward() {
        return isForward;
    }

    public ExpandedNodeId getTargetNodeId() {
        return targetNodeId;
    }

    public Boolean getDeleteBidirectional() {
        return deleteBidirectional;
    }

    public static final class Codec extends GenericDataTypeCodec<DeleteReferencesItem> {
        @Override
        public Class<DeleteReferencesItem> getType() {
            return DeleteReferencesItem.class;
        }

        @Override
        public DeleteReferencesItem decode(SerializationContext context, UaDecoder decoder) {
            NodeId sourceNodeId = decoder.readNodeId("SourceNodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            ExpandedNodeId targetNodeId = decoder.readExpandedNodeId("TargetNodeId");
            Boolean deleteBidirectional = decoder.readBoolean("DeleteBidirectional");
            return new DeleteReferencesItem(sourceNodeId, referenceTypeId, isForward, targetNodeId, deleteBidirectional);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder,
                           DeleteReferencesItem value) {
            encoder.writeNodeId("SourceNodeId", value.getSourceNodeId());
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeBoolean("IsForward", value.getIsForward());
            encoder.writeExpandedNodeId("TargetNodeId", value.getTargetNodeId());
            encoder.writeBoolean("DeleteBidirectional", value.getDeleteBidirectional());
        }
    }
}
