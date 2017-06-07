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

@UaDataType("NodeReference")
public class NodeReference implements UaStructure {

    public static final NodeId TypeId = Identifiers.NodeReference;
    public static final NodeId BinaryEncodingId = Identifiers.NodeReference_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.NodeReference_Encoding_DefaultXml;

    protected final NodeId _nodeId;
    protected final NodeId _referenceTypeId;
    protected final Boolean _isForward;
    protected final NodeId[] _referencedNodeIds;

    public NodeReference() {
        this._nodeId = null;
        this._referenceTypeId = null;
        this._isForward = null;
        this._referencedNodeIds = null;
    }

    public NodeReference(NodeId _nodeId, NodeId _referenceTypeId, Boolean _isForward, NodeId[] _referencedNodeIds) {
        this._nodeId = _nodeId;
        this._referenceTypeId = _referenceTypeId;
        this._isForward = _isForward;
        this._referencedNodeIds = _referencedNodeIds;
    }

    public NodeId getNodeId() { return _nodeId; }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIsForward() { return _isForward; }

    @Nullable
    public NodeId[] getReferencedNodeIds() { return _referencedNodeIds; }

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
            .add("ReferenceTypeId", _referenceTypeId)
            .add("IsForward", _isForward)
            .add("ReferencedNodeIds", _referencedNodeIds)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<NodeReference> {
        @Override
        public NodeReference decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId();
            NodeId _referenceTypeId = reader.readNodeId();
            Boolean _isForward = reader.readBoolean();
            NodeId[] _referencedNodeIds = reader.readArray(reader::readNodeId, NodeId.class);

            return new NodeReference(_nodeId, _referenceTypeId, _isForward, _referencedNodeIds);
        }

        @Override
        public void encode(SerializationContext context, NodeReference value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._nodeId);
            writer.writeNodeId(value._referenceTypeId);
            writer.writeBoolean(value._isForward);
            writer.writeArray(value._referencedNodeIds, writer::writeNodeId);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<NodeReference> {
        @Override
        public NodeReference decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _nodeId = reader.readNodeId("NodeId");
            NodeId _referenceTypeId = reader.readNodeId("ReferenceTypeId");
            Boolean _isForward = reader.readBoolean("IsForward");
            NodeId[] _referencedNodeIds = reader.readArray("ReferencedNodeIds", reader::readNodeId, NodeId.class);

            return new NodeReference(_nodeId, _referenceTypeId, _isForward, _referencedNodeIds);
        }

        @Override
        public void encode(SerializationContext context, NodeReference encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("NodeId", encodable._nodeId);
            writer.writeNodeId("ReferenceTypeId", encodable._referenceTypeId);
            writer.writeBoolean("IsForward", encodable._isForward);
            writer.writeArray("ReferencedNodeIds", encodable._referencedNodeIds, writer::writeNodeId);
        }
    }

}
