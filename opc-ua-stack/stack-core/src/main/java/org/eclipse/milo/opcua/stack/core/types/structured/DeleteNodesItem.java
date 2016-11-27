/*
 * Copyright (c) 2016 Kevin Herron
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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("DeleteNodesItem")
public class DeleteNodesItem implements UaStructure {

    public static final NodeId TypeId = Identifiers.DeleteNodesItem;
    public static final NodeId BinaryEncodingId = Identifiers.DeleteNodesItem_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.DeleteNodesItem_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final Boolean _deleteTargetReferences;

    public DeleteNodesItem() {
        this._nodeId = null;
        this._deleteTargetReferences = null;
    }

    public DeleteNodesItem(NodeId _nodeId, Boolean _deleteTargetReferences) {
        this._nodeId = _nodeId;
        this._deleteTargetReferences = _deleteTargetReferences;
    }

    public NodeId getNodeId() { return _nodeId; }

    public Boolean getDeleteTargetReferences() { return _deleteTargetReferences; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("NodeId", _nodeId)
            .add("DeleteTargetReferences", _deleteTargetReferences)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<DeleteNodesItem> {
        @Override
        public DeleteNodesItem decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            Boolean _deleteTargetReferences = reader.readBoolean();

            return new DeleteNodesItem(_nodeId, _deleteTargetReferences);
        }

        @Override
        public void encode(SerializationContext context, DeleteNodesItem encodable, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(encodable._nodeId);
            writer.writeBoolean(encodable._deleteTargetReferences);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<DeleteNodesItem> {
        @Override
        public DeleteNodesItem decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            Boolean _deleteTargetReferences = reader.readBoolean("DeleteTargetReferences");

            return new DeleteNodesItem(_nodeId, _deleteTargetReferences);
        }

        @Override
        public void encode(SerializationContext context, DeleteNodesItem encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeBoolean("DeleteTargetReferences", encodable._deleteTargetReferences);
        }
    }

}
