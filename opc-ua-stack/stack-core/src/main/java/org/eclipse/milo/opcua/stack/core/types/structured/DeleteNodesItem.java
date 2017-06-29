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
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

public class DeleteNodesItem implements UaStructure {

    public static final NodeId TypeId = Identifiers.DeleteNodesItem;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteNodesItem_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteNodesItem_Encoding_DefaultXml;

    protected final NodeId nodeId;
    protected final Boolean deleteTargetReferences;

    public DeleteNodesItem() {
        this.nodeId = null;
        this.deleteTargetReferences = null;
    }

    public DeleteNodesItem(NodeId nodeId, Boolean deleteTargetReferences) {
        this.nodeId = nodeId;
        this.deleteTargetReferences = deleteTargetReferences;
    }

    public NodeId getNodeId() { return nodeId; }

    public Boolean getDeleteTargetReferences() { return deleteTargetReferences; }

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
            .add("DeleteTargetReferences", deleteTargetReferences)
            .toString();
    }

    public static class Codec extends BuiltinDataTypeCodec<DeleteNodesItem> {

        @Override
        public Class<DeleteNodesItem> getType() {
            return DeleteNodesItem.class;
        }

        @Override
        public DeleteNodesItem decode(UaDecoder decoder) throws UaSerializationException {
            NodeId nodeId = decoder.readNodeId("NodeId");
            Boolean deleteTargetReferences = decoder.readBoolean("DeleteTargetReferences");

            return new DeleteNodesItem(nodeId, deleteTargetReferences);
        }

        @Override
        public void encode(DeleteNodesItem value, UaEncoder encoder) throws UaSerializationException {
            encoder.writeNodeId("NodeId", value.nodeId);
            encoder.writeBoolean("DeleteTargetReferences", value.deleteTargetReferences);
        }
    }

}
