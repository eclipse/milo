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

import com.google.common.base.MoreObjects;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.UaSerializationException;
import org.eclipse.milo.opcua.stack.core.serialization.UaDecoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaEncoder;
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codecs.BuiltinDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class DeleteReferencesItem implements UaStructure {

    public static final NodeId TypeId = Identifiers.DeleteReferencesItem;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteReferencesItem_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteReferencesItem_Encoding_DefaultXml;

    protected final NodeId sourceNodeId;
    protected final NodeId referenceTypeId;
    protected final Boolean isForward;
    protected final ExpandedNodeId targetNodeId;
    protected final Boolean deleteBidirectional;

    public DeleteReferencesItem() {
        this.sourceNodeId = null;
        this.referenceTypeId = null;
        this.isForward = null;
        this.targetNodeId = null;
        this.deleteBidirectional = null;
    }

    public DeleteReferencesItem(NodeId sourceNodeId, NodeId referenceTypeId, Boolean isForward, ExpandedNodeId targetNodeId, Boolean deleteBidirectional) {
        this.sourceNodeId = sourceNodeId;
        this.referenceTypeId = referenceTypeId;
        this.isForward = isForward;
        this.targetNodeId = targetNodeId;
        this.deleteBidirectional = deleteBidirectional;
    }

    public NodeId getSourceNodeId() { return sourceNodeId; }

    public NodeId getReferenceTypeId() { return referenceTypeId; }

    public Boolean getIsForward() { return isForward; }

    public ExpandedNodeId getTargetNodeId() { return targetNodeId; }

    public Boolean getDeleteBidirectional() { return deleteBidirectional; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("SourceNodeId", sourceNodeId)
            .add("ReferenceTypeId", referenceTypeId)
            .add("IsForward", isForward)
            .add("TargetNodeId", targetNodeId)
            .add("DeleteBidirectional", deleteBidirectional)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DeleteReferencesItem> {

        @Override
        public Class<DeleteReferencesItem> getType() {
            return DeleteReferencesItem.class;
        }

        @Override
        public DeleteReferencesItem decode(UaDecoder decoder) throws UaSerializationException {
            NodeId sourceNodeId = decoder.readNodeId("SourceNodeId");
            NodeId referenceTypeId = decoder.readNodeId("ReferenceTypeId");
            Boolean isForward = decoder.readBoolean("IsForward");
            ExpandedNodeId targetNodeId = decoder.readExpandedNodeId("TargetNodeId");
            Boolean deleteBidirectional = decoder.readBoolean("DeleteBidirectional");

            return new DeleteReferencesItem(sourceNodeId, referenceTypeId, isForward, targetNodeId, deleteBidirectional);
        }

        @Override
        public void encode(DeleteReferencesItem value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("SourceNodeId", value.sourceNodeId);
            encoder.writeNodeId("ReferenceTypeId", value.referenceTypeId);
            encoder.writeBoolean("IsForward", value.isForward);
            encoder.writeExpandedNodeId("TargetNodeId", value.targetNodeId);
            encoder.writeBoolean("DeleteBidirectional", value.deleteBidirectional);
        }
    }

}
