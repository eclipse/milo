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
public class ReferenceNode extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=285");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=287");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=286");

    private final NodeId referenceTypeId;

    private final Boolean isInverse;

    private final ExpandedNodeId targetId;

    public ReferenceNode(NodeId referenceTypeId, Boolean isInverse, ExpandedNodeId targetId) {
        this.referenceTypeId = referenceTypeId;
        this.isInverse = isInverse;
        this.targetId = targetId;
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

    public NodeId getReferenceTypeId() {
        return referenceTypeId;
    }

    public Boolean getIsInverse() {
        return isInverse;
    }

    public ExpandedNodeId getTargetId() {
        return targetId;
    }

    public static final class Codec extends GenericDataTypeCodec<ReferenceNode> {
        @Override
        public Class<ReferenceNode> getType() {
            return ReferenceNode.class;
        }

        @Override
        public ReferenceNode decode(SerializationContext context, UaDecoder decoder) {
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isInverse = decoder.readBoolean("IsInverse");
            ExpandedNodeId targetId = decoder.readExpandedNodeId("TargetId");
            return new ReferenceNode(referenceTypeId, isInverse, targetId);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, ReferenceNode value) {
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeBoolean("IsInverse", value.getIsInverse());
            encoder.writeExpandedNodeId("TargetId", value.getTargetId());
        }
    }
}
