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
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;

@EqualsAndHashCode(
    callSuper = false
)
@SuperBuilder(
    toBuilder = true
)
@ToString
public class AddReferencesItem extends Structure implements UaStructure {
    public static final ExpandedNodeId TYPE_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=379");

    public static final ExpandedNodeId XML_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=380");

    public static final ExpandedNodeId BINARY_ENCODING_ID = ExpandedNodeId.parse("nsu=http://opcfoundation.org/UA/;i=381");

    private final NodeId sourceNodeId;

    private final NodeId referenceTypeId;

    private final Boolean isForward;

    private final String targetServerUri;

    private final ExpandedNodeId targetNodeId;

    private final NodeClass targetNodeClass;

    public AddReferencesItem(NodeId sourceNodeId, NodeId referenceTypeId, Boolean isForward,
                             String targetServerUri, ExpandedNodeId targetNodeId, NodeClass targetNodeClass) {
        this.sourceNodeId = sourceNodeId;
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.targetServerUri = targetServerUri;
        this.targetNodeId = targetNodeId;
        this.targetNodeClass = targetNodeClass;
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

    public String getTargetServerUri() {
        return targetServerUri;
    }

    public ExpandedNodeId getTargetNodeId() {
        return targetNodeId;
    }

    public NodeClass getTargetNodeClass() {
        return targetNodeClass;
    }

    public static final class Codec extends GenericDataTypeCodec<AddReferencesItem> {
        @Override
        public Class<AddReferencesItem> getType() {
            return AddReferencesItem.class;
        }

        @Override
        public AddReferencesItem decode(SerializationContext context, UaDecoder decoder) {
            NodeId sourceNodeId = decoder.readNodeId("SourceNodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            String targetServerUri = decoder.readString("TargetServerUri");
            ExpandedNodeId targetNodeId = decoder.readExpandedNodeId("TargetNodeId");
            NodeClass targetNodeClass = decoder.readEnum("TargetNodeClass", NodeClass.class);
            return new AddReferencesItem(sourceNodeId, referenceTypeId, isForward, targetServerUri, targetNodeId, targetNodeClass);
        }

        @Override
        public void encode(SerializationContext context, UaEncoder encoder, AddReferencesItem value) {
            encoder.writeNodeId("SourceNodeId", value.getSourceNodeId());
            encoder.writeNodeId("ReferenceTypeId", value.getReferenceTypeId());
            encoder.writeBoolean("IsForward", value.getIsForward());
            encoder.writeString("TargetServerUri", value.getTargetServerUri());
            encoder.writeExpandedNodeId("TargetNodeId", value.getTargetNodeId());
            encoder.writeEnum("TargetNodeClass", value.getTargetNodeClass());
        }
    }
}
