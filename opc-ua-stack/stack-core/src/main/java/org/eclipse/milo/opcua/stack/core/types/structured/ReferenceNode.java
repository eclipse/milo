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
import org.eclipse.milo.opcua.stack.core.serialization.UaStructure;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcBinaryStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlDataTypeCodec;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamReader;
import org.eclipse.milo.opcua.stack.core.serialization.codec.OpcXmlStreamWriter;
import org.eclipse.milo.opcua.stack.core.serialization.codec.SerializationContext;
import org.eclipse.milo.opcua.stack.core.types.UaDataType;
import org.eclipse.milo.opcua.stack.core.types.builtin.ExpandedNodeId;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;

@UaDataType("ReferenceNode")
public class ReferenceNode implements UaStructure {

    public static final NodeId TypeId = Identifiers.ReferenceNode;
    public static final NodeId BinaryEncodingId = Identifiers.ReferenceNode_Encoding_DefaultBinary;
    public static final NodeId XmlEncodingId = Identifiers.ReferenceNode_Encoding_DefaultXml;

    protected final NodeId _referenceTypeId;
    protected final Boolean _isInverse;
    protected final ExpandedNodeId _targetId;

    public ReferenceNode() {
        this._referenceTypeId = null;
        this._isInverse = null;
        this._targetId = null;
    }

    public ReferenceNode(NodeId _referenceTypeId, Boolean _isInverse, ExpandedNodeId _targetId) {
        this._referenceTypeId = _referenceTypeId;
        this._isInverse = _isInverse;
        this._targetId = _targetId;
    }

    public NodeId getReferenceTypeId() { return _referenceTypeId; }

    public Boolean getIsInverse() { return _isInverse; }

    public ExpandedNodeId getTargetId() { return _targetId; }

    @Override
    public NodeId getTypeId() { return TypeId; }

    @Override
    public NodeId getBinaryEncodingId() { return BinaryEncodingId; }

    @Override
    public NodeId getXmlEncodingId() { return XmlEncodingId; }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
            .add("ReferenceTypeId", _referenceTypeId)
            .add("IsInverse", _isInverse)
            .add("TargetId", _targetId)
            .toString();
    }

    public static class BinaryCodec implements OpcBinaryDataTypeCodec<ReferenceNode> {
        @Override
        public ReferenceNode decode(SerializationContext context, OpcBinaryStreamReader reader) throws UaSerializationException {
            NodeId _referenceTypeId = reader.readNodeId();
            Boolean _isInverse = reader.readBoolean();
            ExpandedNodeId _targetId = reader.readExpandedNodeId();

            return new ReferenceNode(_referenceTypeId, _isInverse, _targetId);
        }

        @Override
        public void encode(SerializationContext context, ReferenceNode value, OpcBinaryStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId(value._referenceTypeId);
            writer.writeBoolean(value._isInverse);
            writer.writeExpandedNodeId(value._targetId);
        }
    }

    public static class XmlCodec implements OpcXmlDataTypeCodec<ReferenceNode> {
        @Override
        public ReferenceNode decode(SerializationContext context, OpcXmlStreamReader reader) throws UaSerializationException {
            NodeId _referenceTypeId = reader.readNodeId("ReferenceTypeId");
            Boolean _isInverse = reader.readBoolean("IsInverse");
            ExpandedNodeId _targetId = reader.readExpandedNodeId("TargetId");

            return new ReferenceNode(_referenceTypeId, _isInverse, _targetId);
        }

        @Override
        public void encode(SerializationContext context, ReferenceNode encodable, OpcXmlStreamWriter writer) throws UaSerializationException {
            writer.writeNodeId("ReferenceTypeId", encodable._referenceTypeId);
            writer.writeBoolean("IsInverse", encodable._isInverse);
            writer.writeExpandedNodeId("TargetId", encodable._targetId);
        }
    }

}
